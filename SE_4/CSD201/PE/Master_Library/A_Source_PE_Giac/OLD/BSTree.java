import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Predicate;

// ============================================================================================================================
//  BSTree.java — Thu vien TONG HOP cho CSD201 (Binary Search Tree)
//  => MOI FUNCTION DA DUOC IMPLEMENT DAY DU. KHONG CO STUB / SKELETON.
//  => KHONG dung System.out.println(). Tat ca ham hien thi deu RETURN String.
//  => Dung Comparator<Object> de lam viec voi MOI kieu du lieu (Tiger, Order, Cape, ...).
// ============================================================================================================================
//
//  [HOW TO USE — CACH SU DUNG]
//
//  // 1. So sanh theo truong int (VD: Tiger.sound)
//  BSTree tree = new BSTree((a, b) -> ((Tiger) a).getSound() - ((Tiger) b).getSound());
//
//  // 2. So sanh theo truong String (VD: Order.orderID)
//  BSTree tree = new BSTree((a, b) -> {
//      String ka = ((Order) a).getOrderID();
//      String kb = ((Order) b).getOrderID();
//      return ka.compareTo(kb);
//  });
//
//  // 3. Chen du lieu
//  tree.insert(new Tiger(5, "Sora"));
//
//  // 4. Duyet va in ra file
//  tree.fInOrder(tree.root, raf);
//
//  // 5. Tim theo dieu kien
//  String result = tree.inOrderConditional(tree.root, o -> ((Tiger)o).getRate() < 6);
//
// ============================================================================================================================
//  MUC LUC — CTRL+F tim nhanh theo so muc
// ============================================================================================================================
//
//  [A] KHOI TAO & CO BAN
//       A1. Constructor, isEmpty, clear
//
//  [B] CHEN (INSERT)
//       B1. insert          — Chen lap (khong de quy)
//       B2. insertRec       — Chen de quy
//       B3. insertAVL       — Chen tu dong can bang (AVL)
//
//  [C] TIM KIEM (SEARCH)
//       C1. search          — Tim theo Comparator
//       C2. searchByString  — Tim theo String key
//       C3. searchByInt     — Tim theo int key
//       C4. contains        — Kiem tra ton tai
//       C5. findMin/findMax — Tim min/max cua toan cay
//       C6. findMinNode/findMaxNode — Tim node min/max trong 1 nhanh (helper)
//
//  [D] XOA (DELETE)
//       D1. remove          — Xoa dung In-Order Successor (CHUAN, hay gap nhat)
//       D2. deleteByCopying — Xoa bang cach Copy (In-Order Predecessor)
//       D3. deleteByMerging — Xoa bang cach Merge (noi 2 nhanh lai)
//       D4. removeConditional — Xoa tat ca node thoa man dieu kien
//
//  [E] DUYET CAY — TRA VE STRING (TRAVERSALS -> String)
//       E1. preOrder        — Pre-Order (Root-L-R)
//       E2. inOrder         — In-Order tang dan (L-Root-R)
//       E3. postOrder       — Post-Order (L-R-Root)
//       E4. breadthFirst    — BFS / Level-Order (dung Queue)
//       E5. reverseInOrder  — In-Order giam dan (R-Root-L)
//       E6. preOrderConditional   — Pre-Order co loc dieu kien
//       E7. inOrderConditional    — In-Order co loc dieu kien
//       E8. postOrderConditional  — Post-Order co loc dieu kien
//       E9. breadthFirstConditional — BFS co loc dieu kien
//       E10. reverseInOrderConditional — Reverse In-Order co loc dieu kien
//       E11. reverseTraversingGTE    — Reverse In-Order, chi lay node >= V (int)
//       E12. reverseTraversingGTEOptimized — Tuong tu nhung co cat canh (pruning)
//       E13. inOrderIterative  — In-Order khong de quy (dung Stack)
//       E14. preOrderIterative — Pre-Order khong de quy
//       E15. postOrderIterative — Post-Order khong de quy (dung 2 Stack)
//       E16. zigzagTraversal   — Duyet Zigzag theo tung muc
//
//  [F] DUYET CAY — GHI RA FILE / BUFFER (TRAVERSALS -> RAF/StringBuilder)
//       F1. fvisit          — Ghi 1 node ra file/buffer (helper)
//       F2. fPreOrder       — Pre-Order ghi ra file/buffer
//       F3. fInOrder        — In-Order ghi ra file/buffer
//       F4. fPostOrder      — Post-Order ghi ra file/buffer
//       F5. fBreadthFirst   — BFS ghi ra file/buffer
//       F6. fReverseInOrder — Reverse In-Order ghi ra file/buffer
//       F7. fPreOrderConditional  — Pre-Order co dieu kien, ghi ra file/buffer
//       F8. fInOrderConditional   — In-Order co dieu kien, ghi ra file/buffer
//       F9. fPostOrderConditional — Post-Order co dieu kien, ghi ra file/buffer
//       F10. fBreadthFirstConditional — BFS co dieu kien, ghi ra file/buffer
//       F11. fReverseInOrderConditional — Reverse In-Order co dieu kien, ghi ra file/buffer
//
//  [G] THONG TIN CAY (TREE PROPERTIES)
//       G1.  height             - Chieu cao cay
//       G2.  countNodes         - Dem tong so node
//       G3.  countLeaves        - Dem so la (leaf node)
//       G3b. fprintLeaves       - Ghi cac node la (leaf node)
//       G4.  countInternalNodes - Dem so node trong (co it nhat 1 con)
//       G5.  countOneChildNodes — Dem so node chi co 1 con
//       G6.  countTwoChildNodes — Dem so node co dung 2 con
//       G7.  countConditional   — Dem so node thoa man dieu kien
//       G8.  levelOfNode / depth — Tim level/depth cua 1 node
//       G9.  widthAtLevel       — So node o 1 level cu the
//       G10. maxWidth           — Chieu rong lon nhat cua toan cay
//       G11. sumAllNodes        — Tong gia tri cac node
//       G12. sumAtLevel         — Tong gia tri cac node o 1 level
//       G13. countInRange       — Dem node trong khoang [min, max] (String key)
//       G14. countInRangeInt    — Dem node trong khoang [min, max] (int key)
//
//  [H] PHAN TICH & KIEM TRA CAY (TREE ANALYSIS)
//       H1. isBalanced   — Kiem tra cay can bang (AVL property)
//       H2. isBST        — Kiem tra tinh hop le BST
//       H3. isFullTree   — Kiem tra Full Binary Tree
//       H4. isAVL        — Kiem tra AVL (goi isBalanced)
//       H5. getBalanceFactor — He so can bang tai 1 node
//
//  [I] QUAN HE NODE (NODE RELATIONSHIPS)
//       I1. findParent     — Tim cha cua 1 node
//       I2. findSibling    — Tim anh/em cua 1 node
//       I3. findLCA        — Lowest Common Ancestor (To tien chung gan nhat)
//       I4. findSuccessor  — In-Order Successor (node ke tiep lon hon)
//       I5. findPredecessor — In-Order Predecessor (node ke tiep nho hon)
//       I6. findKthSmallest — Phan tu nho thu K
//       I7. findKthLargest  — Phan tu lon thu K
//       I8. pathToNode     — Duong di tu root den 1 node
//       I9. getAncestors   — Danh sach to tien cua 1 node
//
//  [J] PHEP QUAY (ROTATIONS)
//       J1. rotateLeft          — Quay trai tai node p
//       J2. rotateRight         — Quay phai tai node p
//       J3. rotateLeftAtNode    — Quay trai tai node co key X
//       J4. rotateRightAtNode   — Quay phai tai node co key X
//       J5. rotateLeftAtRightSon  — Quay trai tai con phai cua node X
//       J6. rotateRightAtLeftSon  — Quay phai tai con trai cua node X
//
//  [K] BIEN DOI CAY (TREE TRANSFORMATION)
//       K1. mirror         — Lat guong toan bo cay
//       K2. balance        — Can bang lai BST (inOrder + buildBalanced)
//       K3. buildBalanced  — Xay dung cay can bang tu ArrayList sorted (helper)
//       K4. buildFromSortedArray — Xay dung BST can bang tu mang da sort
//       K5. buildFromArray       — Xay dung BST tu mang chua sort
//
//  [L] CAC HAM NANG CAO KHAC (ADVANCED / BONUS)
//       L1.  diameter           — Duong kinh cay (so canh dai nhat)
//       L2.  printLevel         — In tat ca node o 1 level
//       L3.  toSortedArray      — Chuyen BST thanh mang da sort
//       L4.  collectInOrder     — Thu thap tat ca node vao ArrayList (helper)
//       L5.  collectConditional — Thu thap node thoa man dieu kien
//       L6.  findMaxConditional — Max trong cac node thoa man dieu kien
//       L7.  findMinConditional — Min trong cac node thoa man dieu kien
//       L8.  flattenToSortedList — Chuyen BST thanh Sorted Linked List
//       L9.  printTree          — In cay dang hinh anh (debug)
//       L10. maximumPathSum     — Duong di co tong gia tri lon nhat
//       L11. countPathsWithSumGTE — Dem duong di root-to-leaf co tong >= target
//       L12. preOrderWithLevel  — PreOrder kem level cua moi node
//       L13. preOrderWithHeight — PreOrder kem height cua moi node
//       L14. preOrderWithArrayIndex - PreOrder kem array index (2i+1, 2i+2)
//
//  [M] SO SANH CAY (TREE COMPARISONS)
//       M1. isSameTree     - Kiem tra 2 cay co giong het nhau
//       M2. isSubtree      - Kiem tra cay nay co la con cua cay kia
//
//  [N] PHAN LOAI CAY NANG CAO (TREE CLASSIFICATIONS)
//       N1. isCompleteTree - Kiem tra Complete Binary Tree
//       N2. isPerfectTree  - Kiem tra Perfect Binary Tree
//
//  [O] XOA HANG LOAT (MASS DELETIONS / PRUNING)
//       O1. deleteLeaves           - Xoa toan bo la
//       O2. deleteNodesWithOneChild- Xoa toan bo node co 1 con
//       O3. deleteOutsideRange     - Cat tia cac node ngoai khoang [min, max]
//
//  [P] PHAN TICH DUONG DI & QUAN HE (PATHS & RELATIVES)
//       P1. isCousin               - Kiem tra 2 node la anh em ho
//       P2. printAllRootToLeafPaths- In tat ca duong di tu root den leaf
//
//  [Q] TIM KIEM CAN CHUNG (PROXIMITY SEARCH)
//       Q1. closestValue           - Tim node co gia tri gan nhat voi target
//
// ============================================================================================================================

class BSTree {

    BSTNode root;
    Comparator<Object> comparator;

    String fi = "input.txt"; // Ten file dau vao (thay theo de thi)
    String fo = "output.txt"; // Ten file dau ra (thay theo de thi)
    StringBuilder outputBuffer = new StringBuilder();

    // setOutputBuffer - Cho phhep dung chung outputBuffer voi Q1.java de de dang tich hop
    void setOutputBuffer(StringBuilder sb) {
        this.outputBuffer = sb;
    }

    // getOutput - Lay toan bo noi dung tu outputBuffer duoi dang String
    String getOutput() {
        return outputBuffer.toString();
    }

    // clearOutput - Xoa sach noi dung cua outputBuffer
    void clearOutput() {
        outputBuffer.setLength(0);
    }

    // Helper output - dung nhu System.out nhung ghi vao outputBuffer
    void print(Object o) { outputBuffer.append(o); }
    void println(Object o) { outputBuffer.append(o).append("\n"); }
    void println() { outputBuffer.append("\n"); }

    // setFile - doi ten file theo tham so truyen vao (format de thi)
    void setFile(String[] args) {
        if (args.length >= 2) { fi = args[0]; fo = args[1]; }
    }

    // printResult - Ghi outputBuffer ra file dau ra
    void printResult() {
        try {
            java.io.FileWriter fw = new java.io.FileWriter(fo);
            fw.write(outputBuffer.toString().trim());
            fw.flush();
            fw.close();
        } catch (java.io.IOException e) {}
    }

    // ============================================================================================================================
    //  [A] KHOI TAO & CO BAN
    // ============================================================================================================================

    /**
     * [A1] Constructor voi Comparator tuy chinh — CACH DUNG CHUAN.
     * Vi du: new BSTree((a, b) -> ((Tiger)a).getSound() - ((Tiger)b).getSound())
     */
    BSTree(Comparator<Object> comparator) {
        this.root = null;
        this.comparator = comparator;
    }

    /**
     * [A1] Constructor mac dinh — dung Comparable.compareTo().
     * Phu hop khi chuyen truc tiep String hoac Integer vao cay.
     */
    /**
     * [A2] Constructor mac dinh.
     * Mac dinh ep kieu sang Integer. Neu truyen kieu khong phai Integer se bao loi nhanh.
     */
    BSTree() {
        this.root = null;
        this.comparator = (a, b) -> Integer.compare((Integer) a, (Integer) b);
    }

    /** [A1] Kiem tra cay rong. */
    boolean isEmpty() {
        return root == null;
    }

    /** [A1] Xoa toan bo cay. */
    void clear() {
        root = null;
    }

    // ============================================================================================================================
    //  [B] CHEN (INSERT)
    // ============================================================================================================================

    /**
     * [B1] Chen phan tu x vao BST (lap, khong de quy).
     * - Neu key trung: khong chen (giu nguyen).
     * - De cap nhat khi trung: sua dong `return;` thanh `p.info = x; return;`
     */
    void insert(Object x) {
        if (root == null) {
            root = new BSTNode(x);
            return;
        }
        BSTNode p = root;
        BSTNode parent = null;
        while (p != null) {
            int cmp = comparator.compare(x, p.info);
            if (cmp == 0) return; // Trung key: bo qua
            parent = p;
            p = (cmp < 0) ? p.left : p.right;
        }
        // Gan node moi vao dung vi tri
        BSTNode newNode = new BSTNode(x);
        if (comparator.compare(x, parent.info) < 0) parent.left = newNode;
        else parent.right = newNode;
    }

    /**
     * [B2] Chen de quy — tra ve root moi sau khi chen.
     * Dung khi de yeu cau viet ham chen de quy.
     * Goi: root = tree.insertRec(root, x);
     */
    BSTNode insertRec(BSTNode p, Object x) {
        if (p == null) return new BSTNode(x);
        int cmp = comparator.compare(x, p.info);
        if (cmp < 0) p.left = insertRec(p.left, x);
        else if (cmp > 0) p.right = insertRec(p.right, x);
        // cmp == 0: trung key, khong chen
        return p;
    }

    /**
     * [B3] Chen voi tu dong can bang AVL.
     * Xu ly du 4 truong hop: LL, RR, LR, RL.
     * Goi: tree.insertAVL(x);
     */
    void insertAVL(Object x) {
        root = insertAVLRec(root, x);
    }

    // Helper de quy cho insertAVL
    BSTNode insertAVLRec(BSTNode node, Object x) {
        if (node == null) return new BSTNode(x);
        int cmp = comparator.compare(x, node.info);
        if (cmp < 0) node.left = insertAVLRec(node.left, x);
        else if (cmp > 0) node.right = insertAVLRec(node.right, x);
        else return node; // Trung key: khong chen

        int balance = getBalanceFactor(node);
        // LL Case: quay phai
        if (balance > 1 && comparator.compare(x, node.left.info) < 0) return rotateRight(node);
        // RR Case: quay trai
        if (balance < -1 && comparator.compare(x, node.right.info) > 0) return rotateLeft(node);
        // LR Case: quay trai tren con trai, roi quay phai
        if (balance > 1 && comparator.compare(x, node.left.info) > 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        // RL Case: quay phai tren con phai, roi quay trai
        if (balance < -1 && comparator.compare(x, node.right.info) < 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    // ============================================================================================================================
    //  [C] TIM KIEM (SEARCH)
    // ============================================================================================================================

    /**
     * [C1] Tim kiem bang Comparator da cau hinh.
     * Tra ve Object tim duoc hoac null.
     * Vi du: tree.search(new Tiger(5, "")) // tim theo sound = 5
     */
    Object search(Object key) {
        BSTNode p = root;
        while (p != null) {
            int cmp = comparator.compare(key, p.info);
            if (cmp == 0) return p.info;
            p = (cmp < 0) ? p.left : p.right;
        }
        return null;
    }

    /**
     * [C2] Tim kiem theo String key.
     * Vi du: tree.searchByString("O001", o -> ((Order)o).getOrderID())
     * Chu y: ham nay dung BST search dua vao Comparator String rieng,
     *        NEU cay khong duoc build bang String Comparator thi ket qua co the sai.
     */
    Object searchByString(String key, java.util.function.Function<Object, String> extractKey) {
        BSTNode p = root;
        while (p != null) {
            String nodeKey = extractKey.apply(p.info);
            int cmp = key.compareTo(nodeKey);
            if (cmp == 0) return p.info;
            p = (cmp < 0) ? p.left : p.right;
        }
        return null;
    }

    /**
     * [C3] Tim kiem theo int key.
     * Vi du: tree.searchByInt(5, t -> ((Tiger)t).getSound())
     */
    Object searchByInt(int key, java.util.function.Function<Object, Integer> extractKey) {
        BSTNode p = root;
        while (p != null) {
            int nodeKey = extractKey.apply(p.info);
            int cmp = Integer.compare(key, nodeKey);
            if (cmp == 0) return p.info;
            p = (cmp < 0) ? p.left : p.right;
        }
        return null;
    }

    /**
     * [C4] Kiem tra mot gia tri co ton tai trong cay khong.
     * Vi du: if (tree.contains(new Tiger(5, ""))) { ... }
     */
    boolean contains(Object key) {
        return search(key) != null;
    }

    /**
     * [C5] Tim phan tu NHO NHAT cua toan cay (node trai nhat).
     * Tra ve null neu cay rong.
     */
    Object findMin() {
        if (root == null) return null;
        BSTNode p = root;
        while (p.left != null) p = p.left;
        return p.info;
    }

    /**
     * [C5] Tim phan tu LON NHAT cua toan cay (node phai nhat).
     * Tra ve null neu cay rong.
     */
    Object findMax() {
        if (root == null) return null;
        BSTNode p = root;
        while (p.right != null) p = p.right;
        return p.info;
    }

    /**
     * [C6] Helper: Tim BSTNode nho nhat trong cay con goc p.
     * Dung noi bo boi ham Delete (lay in-order successor).
     */
    BSTNode findMinNode(BSTNode p) {
        if (p == null) return null;
        while (p.left != null) p = p.left;
        return p;
    }

    /**
     * [C6] Helper: Tim BSTNode lon nhat trong cay con goc p.
     * Dung noi bo boi ham Delete (lay in-order predecessor).
     */
    BSTNode findMaxNode(BSTNode p) {
        if (p == null) return null;
        while (p.right != null) p = p.right;
        return p;
    }

    // ============================================================================================================================
    //  [D] XOA (DELETE)
    // ============================================================================================================================

    /**
     * [D1] XOA CHUAN — dung In-Order Successor (node nho nhat cay con PHAI).
     * Day la cach pho bien nhat trong cac de thi.
     *
     * Cac truong hop:
     *   - Node la leaf (khong con)   => xoa truc tiep.
     *   - Node chi co 1 con          => noi cha voi con.
     *   - Node co 2 con              => thay bang successor (min cua cay con phai),
     *                                   roi xoa successor do.
     */
    void remove(Object key) {
        root = removeNode(root, key);
    }

    // Helper de quy cho remove
    BSTNode removeNode(BSTNode p, Object key) {
        if (p == null) return null;
        int cmp = comparator.compare(key, p.info);
        if (cmp < 0) {
            p.left = removeNode(p.left, key);
        } else if (cmp > 0) {
            p.right = removeNode(p.right, key);
        } else {
            // Tim thay node can xoa
            if (p.left == null) return p.right;   // Chi co con phai (hoac la leaf)
            if (p.right == null) return p.left;    // Chi co con trai
            // Co ca 2 con: lay successor (min cua nhanh phai)
            BSTNode successor = findMinNode(p.right);
            p.info = successor.info;
            p.right = removeNode(p.right, successor.info);
        }
        return p;
    }

    /**
     * [D2] XOA BANG COPYING — dung In-Order Predecessor (node lon nhat cay con TRAI).
     *
     * Cac buoc:
     *   1. Tim node can xoa va parent cua no.
     *   2. Neu node co 2 con: tim predecessor (rightmost cua cay con trai),
     *      copy info len, roi chuyen sang xoa predecessor (gio chi con <= 1 con).
     *   3. Noi parent voi con cua node vua bi "chuyen cho xoa".
     *
     * NOTE: Day la dang bai thuong gap trong CSD201 PE.
     */
    void deleteByCopying(Object key) {
        BSTNode node = root;
        BSTNode prev = null;
        // Buoc 1: Tim node can xoa
        while (node != null) {
            int cmp = comparator.compare(key, node.info);
            if (cmp == 0) break;
            prev = node;
            node = (cmp < 0) ? node.left : node.right;
        }
        if (node == null) return; // Khong tim thay

        // Buoc 2: Xu ly truong hop 2 con — chuyen ve truong hop <= 1 con
        if (node.left != null && node.right != null) {
            BSTNode predecessorParent = node;
            BSTNode predecessor = node.left;
            while (predecessor.right != null) {
                predecessorParent = predecessor;
                predecessor = predecessor.right;
            }
            node.info = predecessor.info; // Copy data
            prev = predecessorParent;
            node = predecessor;           // Bay gio xoa predecessor (chi co toi da 1 con)
        }

        // Buoc 3: Noi parent voi con (node hien tai chi co toi da 1 con)
        BSTNode child = (node.left != null) ? node.left : node.right;
        if (prev == null) root = child;
        else if (prev.left == node) prev.left = child;
        else prev.right = child;
    }

    /**
     * [D3] XOA BANG MERGING — noi cay con trai va phai lai.
     *
     * Cac buoc (khi co 2 con):
     *   1. Lay cay con trai (left subtree) de lam replacement.
     *   2. Di toi node PHAI NHAT cua cay con trai (rightmost).
     *   3. Gan cay con phai (right subtree) cua node vua xoa vao phai cua rightmost.
     *   4. Replacement la cay con trai.
     */
    void deleteByMerging(Object key) {
        BSTNode node = root;
        BSTNode prev = null;
        // Tim node can xoa
        while (node != null) {
            int cmp = comparator.compare(key, node.info);
            if (cmp == 0) break;
            prev = node;
            node = (cmp < 0) ? node.left : node.right;
        }
        if (node == null) return;

        BSTNode replacement;
        if (node.left == null) {
            replacement = node.right; // Khong co con trai: thay bang con phai
        } else if (node.right == null) {
            replacement = node.left;  // Khong co con phai: thay bang con trai
        } else {
            // Co 2 con: merge
            BSTNode rightmost = node.left;
            while (rightmost.right != null) rightmost = rightmost.right;
            rightmost.right = node.right; // Noi cay con phai cua node vao phai cua rightmost
            replacement = node.left;      // Replacement la cay con trai
        }

        if (prev == null) root = replacement;
        else if (prev.left == node) prev.left = replacement;
        else prev.right = replacement;
    }

    /**
     * [D4] Xoa tat ca cac node thoa man dieu kien.
     * Vi du: tree.removeConditional(o -> ((Tiger)o).getRate() < 3)
     */
    void removeConditional(Predicate<Object> condition) {
        // Thu thap truoc, xoa sau (tranh thay doi cay khi dang duyet)
        ArrayList<Object> toRemove = collectConditional(root, condition);
        for (Object obj : toRemove) remove(obj);
    }

    // ============================================================================================================================
    //  [E] DUYET CAY — TRA VE STRING (TRAVERSALS -> String)
    // ============================================================================================================================

    String preOrder(BSTNode p) {
        if (p == null) return "";
        String res = p.info.toString();
        String left = preOrder(p.left);
        String right = preOrder(p.right);
        if (!left.isEmpty()) res += ", " + left;
        if (!right.isEmpty()) res += ", " + right;
        return res;
    }

    /**
     * [E2] In-Order: Left   Root   Right - ket qua tang dan.
     * Goi: String s = tree.inOrder(tree.root);
     */
    String inOrder(BSTNode p) {
        if (p == null) return "";
        String res = "";
        String left = inOrder(p.left);
        if (!left.isEmpty()) res += left + ", ";
        res += p.info.toString();
        String right = inOrder(p.right);
        if (!right.isEmpty()) res += ", " + right;
        return res;
    }

    /**
     * [E3] Post-Order: Left   Right   Root.
     * Goi: String s = tree.postOrder(tree.root);
     */
    String postOrder(BSTNode p) {
        if (p == null) return "";
        String res = "";
        String left = postOrder(p.left);
        String right = postOrder(p.right);
        if (!left.isEmpty()) res += left + ", ";
        if (!right.isEmpty()) res += right + ", ";
        res += p.info.toString();
        return res;
    }

    /**
     * [E4] BFS / Level-Order - duyet theo chieu rong, dung Queue.
     * Goi: String s = tree.breadthFirst();
     */
    String breadthFirst() {
        if (root == null) return "";
        ArrayList<String> list = new ArrayList<>();
        Queue<BSTNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BSTNode p = queue.poll();
            list.add(p.info.toString());
            if (p.left != null) queue.add(p.left);
            if (p.right != null) queue.add(p.right);
        }
        return String.join(", ", list);
    }

    /**
     * [E5] Reverse In-Order: Right → Root → Left — ket qua giam dan.
     * Hay dung khi de yeu cau "in giam dan" hoac "tim phan tu lon nhat".
     * Goi: String s = tree.reverseInOrder(tree.root);
     */
    String reverseInOrder(BSTNode p) {
        if (p == null) return "";
        String res = "";
        String right = reverseInOrder(p.right);
        if (!right.isEmpty()) res += right + ", ";
        res += p.info.toString();
        String left = reverseInOrder(p.left);
        if (!left.isEmpty()) res += ", " + left;
        return res;
    }

    /**
     * [E6] Pre-Order co loc — chi lay node thoa man condition.
     * Vi du: tree.preOrderConditional(tree.root, o -> ((Tiger)o).getRate() < 6)
     */
    String preOrderConditional(BSTNode p, Predicate<Object> condition) {
        if (p == null) return "";
        String res = condition.test(p.info) ? p.info.toString() : "";
        String left = preOrderConditional(p.left, condition);
        String right = preOrderConditional(p.right, condition);
        if (!left.isEmpty()) { if (!res.isEmpty()) res += ", "; res += left; }
        if (!right.isEmpty()) { if (!res.isEmpty()) res += ", "; res += right; }
        return res;
    }

    /**
     * [E7] In-Order co loc — ket qua tang dan, chi lay node thoa man condition.
     * Vi du: tree.inOrderConditional(tree.root, o -> ((Tiger)o).getSound() > 3)
     */
    String inOrderConditional(BSTNode p, Predicate<Object> condition) {
        if (p == null) return "";
        String res = "";
        String left = inOrderConditional(p.left, condition);
        if (!left.isEmpty()) res += left;
        if (condition.test(p.info)) {
            if (!res.isEmpty()) res += ", ";
            res += p.info.toString();
        }
        String right = inOrderConditional(p.right, condition);
        if (!right.isEmpty()) {
            if (!res.isEmpty()) res += ", ";
            res += right;
        }
        return res;
    }

    /**
     * [E8] Post-Order co loc.
     */
    String postOrderConditional(BSTNode p, Predicate<Object> condition) {
        if (p == null) return "";
        String res = "";
        String left = postOrderConditional(p.left, condition);
        String right = postOrderConditional(p.right, condition);
        if (!left.isEmpty()) res += left;
        if (!right.isEmpty()) {
            if (!res.isEmpty()) res += ", ";
            res += right;
        }
        if (condition.test(p.info)) {
            if (!res.isEmpty()) res += ", ";
            res += p.info.toString();
        }
        return res;
    }

    /**
     * [E9] BFS co loc — duyet theo muc, chi lay node thoa man condition.
     * Vi du: tree.breadthFirstConditional(o -> ((Tiger)o).getRate() >= 7)
     */
    String breadthFirstConditional(Predicate<Object> condition) {
        if (root == null) return "";
        ArrayList<String> list = new ArrayList<>();
        Queue<BSTNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BSTNode p = queue.poll();
            if (condition.test(p.info)) list.add(p.info.toString());
            if (p.left != null) queue.add(p.left);
            if (p.right != null) queue.add(p.right);
        }
        return String.join(", ", list);
    }

    /**
     * [E10] Reverse In-Order co loc — giam dan, chi lay node thoa man condition.
     */
    String reverseInOrderConditional(BSTNode p, Predicate<Object> condition) {
        if (p == null) return "";
        String res = "";
        String right = reverseInOrderConditional(p.right, condition);
        if (!right.isEmpty()) res += right;
        if (condition.test(p.info)) {
            if (!res.isEmpty()) res += ", ";
            res += p.info.toString();
        }
        String left = reverseInOrderConditional(p.left, condition);
        if (!left.isEmpty()) {
            if (!res.isEmpty()) res += ", ";
            res += left;
        }
        return res;
    }

    /**
     * [E11] Reverse In-Order, chi lay cac node co INT key >= V.
     * De thi: "Duyet nguoc, in cac node co gia tri >= V".
     * Vi du: tree.reverseTraversingGTE(5, t -> ((Tiger)t).getSound())
     */
    String reverseTraversingGTE(int V, java.util.function.Function<Object, Integer> extractIntKey) {
        return reverseTraversingGTE(root, V, extractIntKey);
    }

    String reverseTraversingGTE(BSTNode p, int V, java.util.function.Function<Object, Integer> extractIntKey) {
        if (p == null) return "";
        String res = "";
        String right = reverseTraversingGTE(p.right, V, extractIntKey);
        if (!right.isEmpty()) res += right;
        if (extractIntKey.apply(p.info) >= V) {
            if (!res.isEmpty()) res += ", ";
            res += p.info.toString();
        }
        String left = reverseTraversingGTE(p.left, V, extractIntKey);
        if (!left.isEmpty()) {
            if (!res.isEmpty()) res += ", ";
            res += left;
        }
        return res;
    }

    /**
     * [E12] Tuong tu E11 nhung co cat canh (pruning) — hieu qua hon voi BST lon.
     * Khi nodeKey < V thi khong can duyet nhanh TRAI nua (BST: trai < goc).
     */
    String reverseTraversingGTEOptimized(int V, java.util.function.Function<Object, Integer> extractIntKey) {
        return reverseTraversingGTEOptimized(root, V, extractIntKey);
    }

    String reverseTraversingGTEOptimized(BSTNode p, int V, java.util.function.Function<Object, Integer> extractIntKey) {
        if (p == null) return "";
        String res = "";
        String right = reverseTraversingGTEOptimized(p.right, V, extractIntKey);
        if (!right.isEmpty()) res += right;
        int nodeKey = extractIntKey.apply(p.info);
        if (nodeKey >= V) {
            if (!res.isEmpty()) res += ", ";
            res += p.info.toString();
            String left = reverseTraversingGTEOptimized(p.left, V, extractIntKey);
            if (!left.isEmpty()) res += ", " + left;
        }
        return res;
    }

    /**
     * [E13] In-Order KHONG de quy — dung Stack.
     * Dung khi de cam su dung de quy.
     */
    String inOrderIterative() {
        ArrayList<String> list = new ArrayList<>();
        java.util.Stack<BSTNode> stack = new java.util.Stack<>();
        BSTNode current = root;
        while (current != null || !stack.isEmpty()) {
            // Di den tan cung nhanh trai
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            list.add(current.info.toString());
            current = current.right;
        }
        return String.join(", ", list);
    }

    /**
     * [E14] Pre-Order KHONG de quy — dung Stack.
     */
    String preOrderIterative() {
        if (root == null) return "";
        ArrayList<String> list = new ArrayList<>();
        java.util.Stack<BSTNode> stack = new java.util.Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BSTNode p = stack.pop();
            list.add(p.info.toString());
            // Push phai truoc, trai sau (Stack LIFO: trai se duoc xu ly truoc)
            if (p.right != null) stack.push(p.right);
            if (p.left != null) stack.push(p.left);
        }
        return String.join(", ", list);
    }

    /**
     * [E15] Post-Order KHONG de quy — dung 2 Stack.
     */
    String postOrderIterative() {
        if (root == null) return "";
        ArrayList<String> list = new ArrayList<>();
        java.util.Stack<BSTNode> s1 = new java.util.Stack<>();
        java.util.Stack<BSTNode> s2 = new java.util.Stack<>();
        s1.push(root);
        while (!s1.isEmpty()) {
            BSTNode p = s1.pop();
            s2.push(p);
            if (p.left != null) s1.push(p.left);
            if (p.right != null) s1.push(p.right);
        }
        while (!s2.isEmpty()) list.add(s2.pop().info.toString());
        return String.join(", ", list);
    }

    /**
     * [E16] Duyet Zigzag (Level 0: L->R, Level 1: R->L, ...).
     * Dung 2 Stack, dao chieu duyet theo tung muc.
     */
    String zigzagTraversal() {
        if (root == null) return "";
        ArrayList<String> list = new ArrayList<>();
        java.util.Stack<BSTNode> curLevel = new java.util.Stack<>();
        java.util.Stack<BSTNode> nextLevel = new java.util.Stack<>();
        boolean leftToRight = true;
        curLevel.push(root);
        while (!curLevel.isEmpty()) {
            BSTNode p = curLevel.pop();
            list.add(p.info.toString());
            if (leftToRight) {
                if (p.left != null) nextLevel.push(p.left);
                if (p.right != null) nextLevel.push(p.right);
            } else {
                if (p.right != null) nextLevel.push(p.right);
                if (p.left != null) nextLevel.push(p.left);
            }
            if (curLevel.isEmpty()) {
                leftToRight = !leftToRight;
                java.util.Stack<BSTNode> temp = curLevel;
                curLevel = nextLevel;
                nextLevel = temp;
            }
        }
        return String.join(", ", list);
    }

    // ============================================================================================================================
    //  [F] DUYET CAY — GHI RA FILE (TRAVERSALS -> RandomAccessFile)
    // ============================================================================================================================

    /**
     * [F1] Helper: Ghi 1 node ra file (RandomAccessFile).
     * Tat ca cac ham fXxx deu goi ham nay.
     */
    void fvisit(BSTNode p, RandomAccessFile f) throws Exception {
        if (p != null) f.writeBytes(p.info.toString() + "\r\n");
    }

    /** [F1b] Helper: Ghi 1 node ra outputBuffer. */
    void fvisit(BSTNode p) {
        if (p != null) println(p.info.toString());
    }

    /** [F2] Pre-Order -> File. */
    void fPreOrder(BSTNode p, RandomAccessFile f) throws Exception { String s = preOrder(p); if(!s.isEmpty()) f.writeBytes(s + "\r\n"); }

    /** Pre-Order -> outputBuffer. */
    void fPreOrder(BSTNode p) { String s = preOrder(p); if(!s.isEmpty()) println(s); }

    /** [F3] In-Order -> File (tang dan). */
    void fInOrder(BSTNode p, RandomAccessFile f) throws Exception { String s = inOrder(p); if(!s.isEmpty()) f.writeBytes(s + "\r\n"); }

    /** In-Order -> outputBuffer. */
    void fInOrder(BSTNode p) { String s = inOrder(p); if(!s.isEmpty()) println(s); }

    /** [F4] Post-Order -> File. */
    void fPostOrder(BSTNode p, RandomAccessFile f) throws Exception { String s = postOrder(p); if(!s.isEmpty()) f.writeBytes(s + "\r\n"); }

    /** Post-Order -> outputBuffer. */
    void fPostOrder(BSTNode p) { String s = postOrder(p); if(!s.isEmpty()) println(s); }

    /** [F5] BFS / Level-Order -> File. */
    void fBreadthFirst(RandomAccessFile f) throws Exception { String s = breadthFirst(); if(!s.isEmpty()) f.writeBytes(s + "\r\n"); }

    /** BFS / Level-Order -> outputBuffer. */
    void fBreadthFirst() { String s = breadthFirst(); if(!s.isEmpty()) println(s); }

    /** [F6] Reverse In-Order -> File (giam dan). */
    void fReverseInOrder(BSTNode p, RandomAccessFile f) throws Exception { String s = reverseInOrder(p); if(!s.isEmpty()) f.writeBytes(s + "\r\n"); }

    /** Reverse In-Order -> outputBuffer. */
    void fReverseInOrder(BSTNode p) { String s = reverseInOrder(p); if(!s.isEmpty()) println(s); }

    /** [F7] Pre-Order co dieu kien -> File. */
    void fPreOrderConditional(BSTNode p, RandomAccessFile f, Predicate<Object> condition) throws Exception { String s = preOrderConditional(p, condition); if(!s.isEmpty()) f.writeBytes(s + "\r\n"); }

    /** Pre-Order co dieu kien -> outputBuffer. */
    void fPreOrderConditional(BSTNode p, Predicate<Object> condition) { String s = preOrderConditional(p, condition); if(!s.isEmpty()) println(s); }

    /** [F8] In-Order co dieu kien -> File (ket qua tang dan). */
    void fInOrderConditional(BSTNode p, RandomAccessFile f, Predicate<Object> condition) throws Exception { String s = inOrderConditional(p, condition); if(!s.isEmpty()) f.writeBytes(s + "\r\n"); }

    /** In-Order co dieu kien -> outputBuffer. */
    void fInOrderConditional(BSTNode p, Predicate<Object> condition) { String s = inOrderConditional(p, condition); if(!s.isEmpty()) println(s); }

    /** [F9] Post-Order co dieu kien -> File. */
    void fPostOrderConditional(BSTNode p, RandomAccessFile f, Predicate<Object> condition) throws Exception { String s = postOrderConditional(p, condition); if(!s.isEmpty()) f.writeBytes(s + "\r\n"); }

    /** Post-Order co dieu kien -> outputBuffer. */
    void fPostOrderConditional(BSTNode p, Predicate<Object> condition) { String s = postOrderConditional(p, condition); if(!s.isEmpty()) println(s); }

    /** [F10] BFS co dieu kien -> File. */
    void fBreadthFirstConditional(RandomAccessFile f, Predicate<Object> condition) throws Exception { String s = breadthFirstConditional(condition); if(!s.isEmpty()) f.writeBytes(s + "\r\n"); }

    /** BFS co dieu kien -> outputBuffer. */
    void fBreadthFirstConditional(Predicate<Object> condition) { String s = breadthFirstConditional(condition); if(!s.isEmpty()) println(s); }

    /** [F11] Reverse In-Order co dieu kien -> File. */
    void fReverseInOrderConditional(BSTNode p, RandomAccessFile f, Predicate<Object> condition) throws Exception { String s = reverseInOrderConditional(p, condition); if(!s.isEmpty()) f.writeBytes(s + "\r\n"); }

    /** Reverse In-Order co dieu kien -> outputBuffer. */
    void fReverseInOrderConditional(BSTNode p, Predicate<Object> condition) { String s = reverseInOrderConditional(p, condition); if(!s.isEmpty()) println(s); }

    // ============================================================================================================================
    //  [G] THONG TIN CAY (TREE PROPERTIES)
    // ============================================================================================================================

    /**
     * [G1] Chieu cao cua toan bo cay.
     * Quy uoc: cay rong = -1, cay chi co root = 0.
     */
    int height() { return height(root); }

    int height(BSTNode p) {
        if (p == null) return -1;
        return 1 + Math.max(height(p.left), height(p.right));
    }

    /** [G2] Dem TONG SO node trong cay. */
    int countNodes() { return countNodes(root); }

    int countNodes(BSTNode p) {
        if (p == null) return 0;
        return 1 + countNodes(p.left) + countNodes(p.right);
    }

    /** [G3] Dem so node LA (leaf) — node khong co con nao. */
    int countLeaves() { return countLeaves(root); }

    int countLeaves(BSTNode p) {
        if (p == null) return 0;
        if (p.left == null && p.right == null) return 1; // La leaf
        return countLeaves(p.left) + countLeaves(p.right);
    }

    /** [G3b] Duyet va ghi ra cac node LA (leaf). (Trai -> Phai) */
    void fprintLeaves() {
        ArrayList<String> list = new ArrayList<>();
        collectLeaves(root, list);
        String s = String.join(", ", list);
        if (!s.isEmpty()) println(s);
    }
    void collectLeaves(BSTNode p, ArrayList<String> list) {
        if (p == null) return;
        if (p.left == null && p.right == null) {
            list.add(p.info.toString());
            return;
        }
        collectLeaves(p.left, list);
        collectLeaves(p.right, list);
    }

    /** [G4] Dem so node TRONG (internal) — co it nhat 1 con. */
    int countInternalNodes() { return countInternalNodes(root); }

    int countInternalNodes(BSTNode p) {
        if (p == null) return 0;
        if (p.left == null && p.right == null) return 0; // Leaf: khong phai internal
        return 1 + countInternalNodes(p.left) + countInternalNodes(p.right);
    }

    /** [G5] Dem so node chi co dung 1 con (chi left HOAC chi right). */
    int countOneChildNodes() { return countOneChildNodes(root); }

    int countOneChildNodes(BSTNode p) {
        if (p == null) return 0;
        int count = ((p.left != null) ^ (p.right != null)) ? 1 : 0; // XOR: 1 trong 2
        return count + countOneChildNodes(p.left) + countOneChildNodes(p.right);
    }

    /** [G6] Dem so node co ca 2 con. */
    int countTwoChildNodes() { return countTwoChildNodes(root); }

    int countTwoChildNodes(BSTNode p) {
        if (p == null) return 0;
        int count = (p.left != null && p.right != null) ? 1 : 0;
        return count + countTwoChildNodes(p.left) + countTwoChildNodes(p.right);
    }

    /**
     * [G7] Dem so node THOA MAN dieu kien.
     * Vi du: tree.countConditional(o -> ((Tiger)o).getRate() < 6)
     */
    int countConditional(Predicate<Object> condition) { return countConditional(root, condition); }

    int countConditional(BSTNode p, Predicate<Object> condition) {
        if (p == null) return 0;
        int count = condition.test(p.info) ? 1 : 0;
        return count + countConditional(p.left, condition) + countConditional(p.right, condition);
    }

    /**
     * [G8] Tim level/depth cua 1 node. Root o level 0.
     * Tra ve -1 neu khong tim thay.
     */
    int levelOfNode(Object key) {
        BSTNode p = root;
        int level = 0;
        while (p != null) {
            int cmp = comparator.compare(key, p.info);
            if (cmp == 0) return level;
            p = (cmp < 0) ? p.left : p.right;
            level++;
        }
        return -1;
    }

    /** [G8] Alias: depth = levelOfNode. */
    int depth(Object key) { return levelOfNode(key); }

    /** [G9] So node tai mot level cu the. Root o level 0. */
    int widthAtLevel(int level) { return widthAtLevel(root, level); }

    int widthAtLevel(BSTNode p, int level) {
        if (p == null) return 0;
        if (level == 0) return 1;
        return widthAtLevel(p.left, level - 1) + widthAtLevel(p.right, level - 1);
    }

    /** [G10] Chieu rong lon nhat trong toan cay (so node nhieu nhat o 1 level). */
    int maxWidth() {
        int h = height();
        int maxW = 0;
        for (int i = 0; i <= h; i++) {
            int w = widthAtLevel(i);
            if (w > maxW) maxW = w;
        }
        return maxW;
    }

    /**
     * [G11] Tong gia tri cac node (voi ham trich xuat int).
     * Vi du: tree.sumAllNodes(t -> ((Tiger)t).getSound())
     */
    int sumAllNodes(java.util.function.Function<Object, Integer> extractValue) {
        return sumAllNodes(root, extractValue);
    }

    int sumAllNodes(BSTNode p, java.util.function.Function<Object, Integer> extractValue) {
        if (p == null) return 0;
        return extractValue.apply(p.info) + sumAllNodes(p.left, extractValue) + sumAllNodes(p.right, extractValue);
    }

    /**
     * [G11] Phien ban don gian: node chhua Integer truc tiep.
     */
    int sumAllNodes() { return sumAllNodes(root); }

    int sumAllNodes(BSTNode p) {
        if (p == null) return 0;
        int val = (Integer) p.info; // Mac dinh la Integer
        return val + sumAllNodes(p.left) + sumAllNodes(p.right);
    }

    /**
     * [G12] Tong gia tri cac node tai mot level cu the.
     * Vi du: tree.sumAtLevel(2, t -> ((Tiger)t).getSound())
     */
    int sumAtLevel(int level, java.util.function.Function<Object, Integer> extractValue) {
        return sumAtLevel(root, level, extractValue);
    }

    int sumAtLevel(BSTNode p, int level, java.util.function.Function<Object, Integer> extractValue) {
        if (p == null) return 0;
        if (level == 0) return extractValue.apply(p.info);
        return sumAtLevel(p.left, level - 1, extractValue) + sumAtLevel(p.right, level - 1, extractValue);
    }

    /**
     * [G13] Dem node co STRING key trong khoang [minKey, maxKey].
     * Vi du: tree.countInRange("O001", "O050", o -> ((Order)o).getOrderID())
     */
    int countInRange(String minKey, String maxKey, java.util.function.Function<Object, String> extractKey) {
        return countInRange(root, minKey, maxKey, extractKey);
    }

    int countInRange(BSTNode p, String minKey, String maxKey, java.util.function.Function<Object, String> extractKey) {
        if (p == null) return 0;
        String key = extractKey.apply(p.info);
        int count = (key.compareTo(minKey) >= 0 && key.compareTo(maxKey) <= 0) ? 1 : 0;
        // Cat canh
        if (key.compareTo(minKey) > 0) count += countInRange(p.left, minKey, maxKey, extractKey);
        if (key.compareTo(maxKey) < 0) count += countInRange(p.right, minKey, maxKey, extractKey);
        return count;
    }

    /**
     * [G14] Dem node co INT key trong khoang [minKey, maxKey].
     * Vi du: tree.countInRangeInt(3, 8, t -> ((Tiger)t).getSound())
     */
    int countInRangeInt(int minKey, int maxKey, java.util.function.Function<Object, Integer> extractKey) {
        return countInRangeInt(root, minKey, maxKey, extractKey);
    }

    int countInRangeInt(BSTNode p, int minKey, int maxKey, java.util.function.Function<Object, Integer> extractKey) {
        if (p == null) return 0;
        int key = extractKey.apply(p.info);
        int count = (key >= minKey && key <= maxKey) ? 1 : 0;
        if (key > minKey) count += countInRangeInt(p.left, minKey, maxKey, extractKey);
        if (key < maxKey) count += countInRangeInt(p.right, minKey, maxKey, extractKey);
        return count;
    }

    // ============================================================================================================================
    //  [H] PHAN TICH & KIEM TRA CAY (TREE ANALYSIS)
    // ============================================================================================================================

    /**
     * [H1] Kiem tra can bang: |height(left) - height(right)| <= 1 tai MOI node.
     * (Day la AVL property)
     */
    boolean isBalanced() { return isBalanced(root); }

    boolean isBalanced(BSTNode p) {
        if (p == null) return true;
        if (Math.abs(height(p.left) - height(p.right)) > 1) return false;
        return isBalanced(p.left) && isBalanced(p.right);
    }

    /**
     * [H2] Kiem tra tinh hop le BST: moi node trai < cha < moi node phai.
     * Dung bound [min, max] de kiem tra tat ca cac node.
     */
    boolean isBST() { return isBST(root, null, null); }

    boolean isBST(BSTNode p, Object min, Object max) {
        if (p == null) return true;
        if (min != null && comparator.compare(p.info, min) <= 0) return false;
        if (max != null && comparator.compare(p.info, max) >= 0) return false;
        return isBST(p.left, min, p.info) && isBST(p.right, p.info, max);
    }

    /**
     * [H3] Kiem tra Full Binary Tree: moi node hoac la LEAF, hoac co dung 2 CON.
     * Node co 1 con => khong phai Full Binary Tree.
     */
    boolean isFullTree() { return isFullTree(root); }

    boolean isFullTree(BSTNode p) {
        if (p == null) return true;
        if (p.left == null && p.right == null) return true;           // Leaf: OK
        if (p.left != null && p.right != null) return isFullTree(p.left) && isFullTree(p.right); // 2 con: OK
        return false; // 1 con: khong phai Full
    }

    /**
     * [H4] Kiem tra AVL: moi node co |BF| <= 1.
     * isAVL() == isBalanced() — giu lai 2 ten de lam quen cu phap de thi.
     */
    boolean isAVL() { return isBalanced(); }

    boolean isAVL(BSTNode p) { return isBalanced(p); }

    /**
     * [H5] He so can bang (Balance Factor) tai 1 node.
     * BF = height(left) - height(right).
     * AVL: -1 <= BF <= 1.
     */
    int getBalanceFactor(BSTNode p) {
        if (p == null) return 0;
        return height(p.left) - height(p.right);
    }

    // ============================================================================================================================
    //  [I] QUAN HE NODE (NODE RELATIONSHIPS)
    // ============================================================================================================================

    /**
     * [I1] Tim NODE CHA cua node co key.
     * Tra ve null neu la root hoac khong tim thay.
     */
    BSTNode findParent(Object key) {
        if (root == null || comparator.compare(key, root.info) == 0) return null;
        BSTNode parent = null;
        BSTNode p = root;
        while (p != null) {
            int cmp = comparator.compare(key, p.info);
            if (cmp == 0) return parent;
            parent = p;
            p = (cmp < 0) ? p.left : p.right;
        }
        return null;
    }

    /**
     * [I2] Tim NODE ANH/EM (sibling) — node cung cha.
     * Tra ve null neu la root, hoac cha khong co node anh/em.
     */
    Object findSibling(Object key) {
        BSTNode parent = findParent(key);
        if (parent == null) return null;
        if (parent.left != null && comparator.compare(parent.left.info, key) == 0)
            return parent.right != null ? parent.right.info : null;
        if (parent.right != null && comparator.compare(parent.right.info, key) == 0)
            return parent.left != null ? parent.left.info : null;
        return null;
    }

    /**
     * [I3] Lowest Common Ancestor (LCA) — To tien chung THAP NHAT cua 2 node.
     * Trong BST: LCA la node dau tien ma key1 va key2 phan ra 2 nhanh khac nhau.
     * Vi du: tree.findLCA(key1, key2)
     */
    Object findLCA(Object key1, Object key2) {
        BSTNode lca = findLCA(root, key1, key2);
        return lca != null ? lca.info : null;
    }

    BSTNode findLCA(BSTNode p, Object key1, Object key2) {
        if (p == null) return null;
        int cmp1 = comparator.compare(key1, p.info);
        int cmp2 = comparator.compare(key2, p.info);
        if (cmp1 < 0 && cmp2 < 0) return findLCA(p.left, key1, key2);  // Ca 2 ben trai
        if (cmp1 > 0 && cmp2 > 0) return findLCA(p.right, key1, key2); // Ca 2 ben phai
        return p; // Phan ra 2 nhanh, hoac 1 trong 2 chinh la p => day la LCA
    }

    /**
     * [I4] In-Order Successor — node KE TIEP (nho nhat lon hon key).
     * Thuat toan: di phai 1 buoc, lay node trai nhat; hoac theo duong len.
     */
    Object findSuccessor(Object key) {
        BSTNode successor = null;
        BSTNode p = root;
        while (p != null) {
            int cmp = comparator.compare(key, p.info);
            if (cmp < 0) {
                successor = p; // p co the la successor (nam ben phai cua key)
                p = p.left;
            } else {
                p = p.right; // Di sang phai de tim gia tri lon hon key
            }
        }
        return successor != null ? successor.info : null;
    }

    /**
     * [I5] In-Order Predecessor — node TRUOC DO (lon nhat nho hon key).
     */
    Object findPredecessor(Object key) {
        BSTNode predecessor = null;
        BSTNode p = root;
        while (p != null) {
            int cmp = comparator.compare(key, p.info);
            if (cmp > 0) {
                predecessor = p; // p co the la predecessor
                p = p.right;
            } else {
                p = p.left;
            }
        }
        return predecessor != null ? predecessor.info : null;
    }

    /**
     * [I6] Tim phan tu NHO THU K (1-indexed).
     * k=1 => phan tu nho nhat; k=n => phan tu lon nhat.
     * Tra ve null neu k khong hop le.
     */
    Object findKthSmallest(int k) {
        ArrayList<Object> list = new ArrayList<>();
        collectInOrder(root, list);
        if (k < 1 || k > list.size()) return null;
        return list.get(k - 1);
    }

    /**
     * [I7] Tim phan tu LON THU K (1-indexed).
     * k=1 => phan tu lon nhat; k=n => phan tu nho nhat.
     */
    Object findKthLargest(int k) {
        ArrayList<Object> list = new ArrayList<>();
        collectInOrder(root, list);
        if (k < 1 || k > list.size()) return null;
        return list.get(list.size() - k);
    }

    /**
     * [I8] Duong di tu root den node co key.
     * Tra ve chuoi dang "5 -> 3 -> 4" hoac "Not found".
     */
    String pathToNode(Object key) {
        StringBuilder result = new StringBuilder();
        BSTNode p = root;
        while (p != null) {
            result.append(p.info.toString());
            int cmp = comparator.compare(key, p.info);
            if (cmp == 0) return result.toString();
            result.append(" -> ");
            p = (cmp < 0) ? p.left : p.right;
        }
        return "Not found";
    }

    /**
     * [I9] Lay danh sach tat ca TO TIEN cua 1 node (tu root den cha cua no).
     * Tra ve ArrayList rong neu khong tim thay node.
     * Vi du: tree.getAncestors(key) -> [root, ..., parent]
     */
    ArrayList<Object> getAncestors(Object key) {
        ArrayList<Object> ancestors = new ArrayList<>();
        BSTNode p = root;
        while (p != null) {
            int cmp = comparator.compare(key, p.info);
            if (cmp == 0) return ancestors; // Tim thay: tra ve danh sach to tien da thu thap
            ancestors.add(p.info);          // Them node hien tai vao danh sach to tien
            p = (cmp < 0) ? p.left : p.right;
        }
        return new ArrayList<>(); // Khong tim thay
    }

    // ============================================================================================================================
    //  [J] PHEP QUAY (ROTATIONS)
    // ============================================================================================================================

    /**
     * [J1] Quay TRAI tai node p.
     *
     *     p               q
     *    / \             / \
     *   A   q    =>    p   C
     *      / \        / \
     *     B   C      A   B
     *
     * Tra ve root moi (q). Phai gan lai vao cha cua p o ben ngoai.
     */
    BSTNode rotateLeft(BSTNode p) {
        if (p == null || p.right == null) return p;
        BSTNode q = p.right;
        p.right = q.left;
        q.left = p;
        return q; // q la root moi
    }

    /**
     * [J2] Quay PHAI tai node p.
     *
     *       p            q
     *      / \          / \
     *     q   C   =>  A   p
     *    / \              / \
     *   A   B            B   C
     *
     * Tra ve root moi (q).
     */
    BSTNode rotateRight(BSTNode p) {
        if (p == null || p.left == null) return p;
        BSTNode q = p.left;
        p.left = q.right;
        q.right = p;
        return q; // q la root moi
    }

    /**
     * [J3] Quay TRAI tai chinh NODE co key (cap nhat lien ket voi cha).
     * Vi du: tree.rotateLeftAtNode(5)
     */
    void rotateLeftAtNode(Object key) {
        if (root == null) return;
        if (comparator.compare(key, root.info) == 0) { root = rotateLeft(root); return; }
        BSTNode parent = null, node = root;
        while (node != null) {
            int cmp = comparator.compare(key, node.info);
            if (cmp == 0) break;
            parent = node;
            node = (cmp < 0) ? node.left : node.right;
        }
        if (node == null) return;
        BSTNode rotated = rotateLeft(node);
        if (parent.left == node) parent.left = rotated;
        else parent.right = rotated;
    }

    /**
     * [J4] Quay PHAI tai chinh NODE co key (cap nhat lien ket voi cha).
     */
    void rotateRightAtNode(Object key) {
        if (root == null) return;
        if (comparator.compare(key, root.info) == 0) { root = rotateRight(root); return; }
        BSTNode parent = null, node = root;
        while (node != null) {
            int cmp = comparator.compare(key, node.info);
            if (cmp == 0) break;
            parent = node;
            node = (cmp < 0) ? node.left : node.right;
        }
        if (node == null) return;
        BSTNode rotated = rotateRight(node);
        if (parent.left == node) parent.left = rotated;
        else parent.right = rotated;
    }

    /**
     * [J5] Quay TRAI tai CON PHAI cua node co key.
     * De thi: "Rotate left at the right son of node X"
     */
    void rotateLeftAtRightSon(Object key) {
        BSTNode node = root;
        while (node != null) {
            int cmp = comparator.compare(key, node.info);
            if (cmp == 0) break;
            node = (cmp < 0) ? node.left : node.right;
        }
        if (node == null || node.right == null) return;
        node.right = rotateLeft(node.right); // Quay trai tai con phai
    }

    /**
     * [J6] Quay PHAI tai CON TRAI cua node co key.
     * De thi: "Rotate right at the left son of node X"
     */
    void rotateRightAtLeftSon(Object key) {
        BSTNode node = root;
        while (node != null) {
            int cmp = comparator.compare(key, node.info);
            if (cmp == 0) break;
            node = (cmp < 0) ? node.left : node.right;
        }
        if (node == null || node.left == null) return;
        node.left = rotateRight(node.left); // Quay phai tai con trai
    }

    // ============================================================================================================================
    //  [K] BIEN DOI CAY (TREE TRANSFORMATION)
    // ============================================================================================================================

    /**
     * [K1] Lat guong toan bo cay: hoan doi left va right tai moi node.
     * Luu y: sau khi mirror, cay KHONG CON la BST hop le!
     */
    void mirror() { mirror(root); }

    void mirror(BSTNode p) {
        if (p == null) return;
        BSTNode temp = p.left; p.left = p.right; p.right = temp; // Hoan doi
        mirror(p.left);
        mirror(p.right);
    }

    /**
     * [K2] Can bang lai BST (Rebalance).
     * Buoc 1: Thu thap In-Order (sap xep tang dan).
     * Buoc 2: Xay dung lai cay can bang tu mang da sort.
     */
    void balance() {
        ArrayList<Object> sorted = new ArrayList<>();
        collectInOrder(root, sorted);
        root = buildBalanced(sorted, 0, sorted.size() - 1);
    }

    /**
     * [K3] Helper: xay dung cay can bang tu mang sorted[left..right].
     * Chon phan tu giua lam root, de quy cho 2 nua.
     */
    BSTNode buildBalanced(ArrayList<Object> sorted, int left, int right) {
        if (left > right) return null;
        int mid = (left + right) / 2;
        BSTNode node = new BSTNode(sorted.get(mid));
        node.left = buildBalanced(sorted, left, mid - 1);
        node.right = buildBalanced(sorted, mid + 1, right);
        return node;
    }

    /**
     * [K4] Xay dung BST CAN BANG tu mang DA SORT.
     * Vi du: tree.buildFromSortedArray(new Integer[]{1,2,3,4,5})
     */
    void buildFromSortedArray(Object[] sorted) {
        ArrayList<Object> list = new ArrayList<>();
        for (Object o : sorted) list.add(o);
        root = buildBalanced(list, 0, list.size() - 1);
    }

    /**
     * [K5] Xay dung BST tu mang CHUA SORT.
     * Chen tung phan tu theo thu tu BST thong thuong.
     */
    void buildFromArray(Object[] arr) {
        clear();
        for (Object o : arr) insert(o);
    }

    // ============================================================================================================================
    //  [L] CAC HAM NANG CAO KHAC (ADVANCED / BONUS)
    // ============================================================================================================================

    /**
     * [L1] Duong kinh cay (Diameter) — duong di co NHIEU CANH NHAT giua 2 node bat ky.
     * duong kinh qua node p = height(left) + height(right) + 2.
     */
    int diameter() { return diameter(root); }

    int diameter(BSTNode p) {
        if (p == null) return 0;
        int throughP = height(p.left) + height(p.right) + 2; // Canh qua p
        return Math.max(throughP, Math.max(diameter(p.left), diameter(p.right)));
    }

    /**
     * [L2] In tat ca node o mot LEVEL cu the.
     * Root o level 0.
     */
    String printLevel(int level) { return printLevel(root, level); }

    String printLevel(BSTNode p, int level) {
        if (p == null) return "";
        if (level == 0) return p.info.toString() + "\n";
        return printLevel(p.left, level - 1) + printLevel(p.right, level - 1);
    }

    /**
     * [L3] Chuyen BST thanh mang Object[] da sap xep tang dan.
     */
    Object[] toSortedArray() {
        ArrayList<Object> list = new ArrayList<>();
        collectInOrder(root, list);
        return list.toArray();
    }

    /**
     * [L4] Helper: Thu thap tat ca node theo In-Order vao ArrayList.
     * Duoc goi boi nhieu ham khac (findKth, balance, toSortedArray,...).
     */
    void collectInOrder(BSTNode p, ArrayList<Object> list) {
        if (p == null) return;
        collectInOrder(p.left, list);
        list.add(p.info);
        collectInOrder(p.right, list);
    }

    /**
     * [L5] Thu thap cac node THOA MAN DIEU KIEN vao ArrayList (theo In-Order).
     * Vi du: tree.collectConditional(tree.root, o -> ((Tiger)o).getRate() < 6)
     */
    ArrayList<Object> collectConditional(BSTNode p, Predicate<Object> condition) {
        ArrayList<Object> result = new ArrayList<>();
        collectConditionalHelper(p, condition, result);
        return result;
    }

    void collectConditionalHelper(BSTNode p, Predicate<Object> condition, ArrayList<Object> result) {
        if (p == null) return;
        collectConditionalHelper(p.left, condition, result);
        if (condition.test(p.info)) result.add(p.info);
        collectConditionalHelper(p.right, condition, result);
    }

    /**
     * [L6] Tim phan tu LON NHAT trong so cac node thoa man dieu kien.
     * (Lay phan tu cuoi cung trong In-Order duyet co dieu kien)
     */
    Object findMaxConditional(Predicate<Object> condition) {
        ArrayList<Object> list = collectConditional(root, condition);
        return list.isEmpty() ? null : list.get(list.size() - 1);
    }

    /**
     * [L7] Tim phan tu NHO NHAT trong so cac node thoa man dieu kien.
     */
    Object findMinConditional(Predicate<Object> condition) {
        ArrayList<Object> list = collectConditional(root, condition);
        return list.isEmpty() ? null : list.get(0);
    }

    /**
     * [L8] Chuyen BST thanh Sorted Doubly Linked List (dung left lam prev, right lam next).
     * Tra ve head cua list.
     * Chu y: Sau khi goi ham nay, cau truc BST bi pha huy.
     */
    BSTNode flattenToSortedList() {
        ArrayList<Object> list = new ArrayList<>();
        collectInOrder(root, list);
        if (list.isEmpty()) return null;
        BSTNode head = new BSTNode(list.get(0));
        BSTNode current = head;
        for (int i = 1; i < list.size(); i++) {
            current.right = new BSTNode(list.get(i));
            current.right.left = current;
            current = current.right;
        }
        return head;
    }

    /**
     * [L9] In cay dang so do cay (dung cho debug, kiem tra cau truc).
     * Goi: System.out.print(tree.printTree())
     */
    String printTree() { return printTree(root, "", true); }

    String printTree(BSTNode p, String prefix, boolean isRight) {
        if (p == null) return "";
        return printTree(p.right, prefix + (isRight ? "    " : "|   "), true)
             + prefix + "+-- " + p.info.toString() + "\n"
             + printTree(p.left, prefix + (isRight ? "    " : "|   "), false);
    }

    /**
     * [L10] Maximum Path Sum — duong di co TONG GIA TRI LON NHAT.
     * (Duong di co the bat dau va ket thuc o bat ky node nao)
     * Vi du: tree.maximumPathSum(t -> ((Tiger)t).getSound())
     */
    int maxPathSumVal;
    int maximumPathSum(java.util.function.Function<Object, Integer> extractValue) {
        maxPathSumVal = Integer.MIN_VALUE;
        maxPathDown(root, extractValue);
        return maxPathSumVal;
    }

    int maxPathDown(BSTNode p, java.util.function.Function<Object, Integer> extractValue) {
        if (p == null) return 0;
        int val = extractValue.apply(p.info);
        int left = Math.max(0, maxPathDown(p.left, extractValue));
        int right = Math.max(0, maxPathDown(p.right, extractValue));
        maxPathSumVal = Math.max(maxPathSumVal, left + right + val);
        return Math.max(left, right) + val;
    }

    /**
     * [L11] Dem so duong di root-to-leaf co TONG >= target.
     * Vi du: tree.countPathsWithSumGTE(20, t -> ((Tiger)t).getSound())
     */
    int countPathsWithSumGTE(int target, java.util.function.Function<Object, Integer> extractValue) {
        return countPathsWithSumGTE(root, target, 0, extractValue);
    }

    int countPathsWithSumGTE(BSTNode p, int target, int currentSum, java.util.function.Function<Object, Integer> extractValue) {
        if (p == null) return 0;
        currentSum += extractValue.apply(p.info);
        if (p.left == null && p.right == null) return (currentSum >= target) ? 1 : 0; // Leaf
        return countPathsWithSumGTE(p.left, target, currentSum, extractValue)
             + countPathsWithSumGTE(p.right, target, currentSum, extractValue);
    }

    /**
     * [L12] Pre-Order in kem LEVEL cua moi node.
     * Vi du output: "Tiger5 [Level: 1]"
     * Goi: tree.preOrderWithLevel()  (root bat dau o level 1)
     */
    String preOrderWithLevel() { return preOrderWithLevel(root, 1); }

    String preOrderWithLevel(BSTNode p, int level) {
        if (p == null) return "";
        return p.info.toString() + " [Level: " + level + "]\n"
             + preOrderWithLevel(p.left, level + 1)
             + preOrderWithLevel(p.right, level + 1);
    }

    /**
     * [L13] Pre-Order in kem HEIGHT cua moi node.
     * Vi du output: "Tiger5 [Height: 2]"
     */
    String preOrderWithHeight() { return preOrderWithHeight(root); }

    String preOrderWithHeight(BSTNode p) {
        if (p == null) return "";
        return p.info.toString() + " [Height: " + height(p) + "]\n"
             + preOrderWithHeight(p.left)
             + preOrderWithHeight(p.right);
    }

    /**
     * [L14] Pre-Order in kem ARRAY INDEX (2i+1 trai, 2i+2 phai).
     * Rat hay xuat hien trong de thi PE: "in kem vi tri trong mang."
     * Vi du: tree.preOrderWithArrayIndex(0) => root o index 0.
     */
    String preOrderWithArrayIndex(int rootIndex) { return preOrderWithArrayIndex(root, rootIndex); }

    String preOrderWithArrayIndex(BSTNode p, int index) {
        if (p == null) return "";
        return p.info.toString() + " [Index: " + index + "]\n"
             + preOrderWithArrayIndex(p.left, 2 * index + 1)
             + preOrderWithArrayIndex(p.right, 2 * index + 2);
    }

    // ==================================================================
    // [M] INTEGER CONVENIENCE METHODS (DEFAULTS)
    // ==================================================================

    boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    int countPrimes() { return countConditional(x -> isPrime((Integer) x)); }
    int countEvens() { return countConditional(x -> (Integer) x % 2 == 0); }
    int countOdds() { return countConditional(x -> (Integer) x % 2 != 0); }

    int sumPrimes() { return sumAllNodes(root, x -> isPrime((Integer) x) ? (Integer) x : 0); }
    int sumEvens() { return sumAllNodes(root, x -> ((Integer) x % 2 == 0) ? (Integer) x : 0); }

    void fInOrderPrimes() { fInOrderConditional(root, x -> isPrime((Integer) x)); }
    void fInOrderEvens() { fInOrderConditional(root, x -> (Integer) x % 2 == 0); }

    void removeFirstPrime() {
        ArrayList<Object> primes = collectConditional(root, x -> isPrime((Integer) x));
        if (!primes.isEmpty()) remove(primes.get(0));
    }

    // ============================================================================================================================
    //  [M] SO SANH CAY (TREE COMPARISONS)
    // ============================================================================================================================

    /**
     * [M1] Kiem tra hai cay co cau truc va du lieu giong nhau khong.
     */
    boolean isSameTree(BSTNode t1, BSTNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return t1.info.equals(t2.info) && isSameTree(t1.left, t2.left) && isSameTree(t1.right, t2.right);
    }

    /**
     * [M2] Kiem tra cay t2 co phai la con cua cay t1 khong.
     */
    boolean isSubtree(BSTNode t1, BSTNode t2) {
        if (t2 == null) return true; // Cay rong luon la con
        if (t1 == null) return false;
        if (isSameTree(t1, t2)) return true;
        return isSubtree(t1.left, t2) || isSubtree(t1.right, t2);
    }

    // ============================================================================================================================
    //  [N] PHAN LOAI CAY NANG CAO (TREE CLASSIFICATIONS)
    // ============================================================================================================================

    /**
     * [N1] Kiem tra cay nhi phan hoan chinh (Complete Binary Tree).
     */
    boolean isCompleteTree() {
        if (root == null) return true;
        Queue<BSTNode> queue = new LinkedList<>();
        queue.add(root);
        boolean end = false; 
        while (!queue.isEmpty()) {
            BSTNode p = queue.poll();
            if (p == null) {
                end = true;
            } else {
                if (end) return false; 
                queue.add(p.left);
                queue.add(p.right);
            }
        }
        return true;
    }

    /**
     * [N2] Kiem tra cay nhi phan hoan hao (Perfect Binary Tree).
     */
    boolean isPerfectTree() {
        int d = depthLeftmost(root);
        return isPerfectTree(root, d, 0);
    }

    int depthLeftmost(BSTNode p) {
        int d = 0;
        while (p != null) { d++; p = p.left; }
        return d;
    }

    boolean isPerfectTree(BSTNode p, int d, int level) {
        if (p == null) return true;
        if (p.left == null && p.right == null) return (d == level + 1); 
        if (p.left == null || p.right == null) return false; 
        return isPerfectTree(p.left, d, level + 1) && isPerfectTree(p.right, d, level + 1);
    }

    // ============================================================================================================================
    //  [O] XOA HANG LOAT (MASS DELETIONS / PRUNING)
    // ============================================================================================================================

    /**
     * [O1] Xoa tat ca cac node la.
     */
    void deleteLeaves() {
        root = deleteLeaves(root);
    }

    BSTNode deleteLeaves(BSTNode p) {
        if (p == null) return null;
        if (p.left == null && p.right == null) return null; 
        p.left = deleteLeaves(p.left);
        p.right = deleteLeaves(p.right);
        return p;
    }

    /**
     * [O2] Xoa tat ca node co dung 1 con (Noi chau len ong).
     */
    void deleteNodesWithOneChild() {
        root = deleteNodesWithOneChild(root);
    }

    BSTNode deleteNodesWithOneChild(BSTNode p) {
        if (p == null) return null;
        p.left = deleteNodesWithOneChild(p.left);
        p.right = deleteNodesWithOneChild(p.right);
        if (p.left != null && p.right == null) return p.left; 
        if (p.left == null && p.right != null) return p.right; 
        return p;
    }

    /**
     * [O3] Xoa tat ca node co Integer key ngoai khoang [min, max].
     */
    void deleteOutsideRange(int min, int max) {
        root = deleteOutsideRange(root, min, max);
    }

    BSTNode deleteOutsideRange(BSTNode p, int min, int max) {
        if (p == null) return null;
        p.left = deleteOutsideRange(p.left, min, max);
        p.right = deleteOutsideRange(p.right, min, max);
        int val = (Integer) p.info;
        if (val < min) return p.right; 
        if (val > max) return p.left; 
        return p;
    }

    // ============================================================================================================================
    //  [P] PHAN TICH DUONG DI & QUAN HE (PATHS & RELATIVES)
    // ============================================================================================================================

    /**
     * [P1] Kiem tra hai node co phai anh em ho (cung level, khac cha).
     */
    boolean isCousin(Object val1, Object val2) {
        if (root == null) return false;
        return levelOfNode(val1) == levelOfNode(val2) && !isSibling(root, val1, val2);
    }

    boolean isSibling(BSTNode p, Object val1, Object val2) {
        if (p == null) return false;
        if (p.left != null && p.right != null) {
            boolean match1 = comparator.compare(p.left.info, val1) == 0 && comparator.compare(p.right.info, val2) == 0;
            boolean match2 = comparator.compare(p.left.info, val2) == 0 && comparator.compare(p.right.info, val1) == 0;
            if (match1 || match2) return true;
        }
        return isSibling(p.left, val1, val2) || isSibling(p.right, val1, val2);
    }

    /**
     * [P2] In ra tat ca duong di tu root den leaf.
     */
    String printAllRootToLeafPaths() {
        ArrayList<String> allPaths = new ArrayList<>();
        ArrayList<String> currentPath = new ArrayList<>();
        collectAllPaths(root, currentPath, allPaths);
        return String.join(", ", allPaths);
    }

    void collectAllPaths(BSTNode p, ArrayList<String> currentPath, ArrayList<String> allPaths) {
        if (p == null) return;
        currentPath.add(p.info.toString());
        if (p.left == null && p.right == null) {
            allPaths.add(String.join("->", currentPath));
        } else {
            collectAllPaths(p.left, currentPath, allPaths);
            collectAllPaths(p.right, currentPath, allPaths);
        }
        currentPath.remove(currentPath.size() - 1);
    }

    // ============================================================================================================================
    //  [Q] TIM KIEM CAN CHUNG (PROXIMITY SEARCH)
    // ============================================================================================================================

    /**
     * [Q1] Tim node co gia tri int gan nhat voi target.
     */
    int closestValue(int target) {
        if (root == null) throw new IllegalStateException("Tree is empty");
        return closestValue(root, target, (Integer) root.info);
    }

    int closestValue(BSTNode p, int target, int closest) {
        if (p == null) return closest;
        int val = (Integer) p.info;
        if (Math.abs(target - val) < Math.abs(target - closest)) closest = val;
        if (target < val) return closestValue(p.left, target, closest);
        else if (target > val) return closestValue(p.right, target, closest);
        else return closest; // Da tim thay bang target
    }

    // ============================================================================================================================
    //  [R] CÁC THUẬT TOÁN ĐỈNH CAO (ADVANCED EXAM 40% EXPANSION)
    // ============================================================================================================================

    /**
     * [R2] Diameter of Tree - Đường kính của cây (khoảng cách lớn nhất giữa 2 leaf).
     */
    int getDiameter() {
        int[] diameter = new int[1];
        calculateDiameter(root, diameter);
        return diameter[0];
    }

    int calculateDiameter(BSTNode p, int[] diameter) {
        if (p == null) return 0;
        int leftHeight = calculateDiameter(p.left, diameter);
        int rightHeight = calculateDiameter(p.right, diameter);
        
        diameter[0] = Math.max(diameter[0], leftHeight + rightHeight + 1);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // ============================================================================================================================
    //  [S] SERIALIZATION & DESERIALIZATION (LƯU & KHÔI PHỤC CẤU TRÚC)
    // ============================================================================================================================

    /**
     * [S1] Chuyen cay thanh mot chuoi String duy nhat (Level-order) de luu tru
     */
    String serialize() {
        if (root == null) return "null";
        StringBuilder sb = new StringBuilder();
        Queue<BSTNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            BSTNode p = q.poll();
            if (p == null) {
                sb.append("null,");
            } else {
                sb.append(p.info.toString()).append(",");
                q.add(p.left);
                q.add(p.right);
            }
        }
        return sb.toString();
    }

    // ============================================================================================================================
    //  [T] TÍNH NĂNG TÍCH HỢP TỪ PE_PHUC (THƯỜNG GẶP TRONG ĐỀ THI)
    // ============================================================================================================================

    /**
     * [T1] Analyze Node - Bóc tách và hiển thị thông tin Node.
     */
    String analyzeNode(Object target) {
        BSTNode p = root;
        BSTNode parent = null;
        int level = 0;
        
        while (p != null) {
            int cmp = comparator.compare(p.info, target);
            if (cmp == 0) break;
            parent = p;
            level++;
            if (cmp > 0) p = p.left;
            else p = p.right;
        }
        
        if (p == null) return "Node not found.";
        
        int h = height(p);
        int size = countNodes(p);
        String type = (parent == null) ? "Root" : (p.left == null && p.right == null ? "Leaf" : "Internal Node");
        
        return "Node: " + p.info + 
               "\nParent: " + (parent != null ? parent.info : "NULL") +
               "\nLeft Child: " + (p.left != null ? p.left.info : "NULL") +
               "\nRight Child: " + (p.right != null ? p.right.info : "NULL") +
               "\nLevel: " + level + 
               "\nHeight: " + h + 
               "\nSubtree Size: " + size +
               "\nType: " + type;
    }

    /**
     * [T2] Kiểm tra cây hợp lệ (BST)
     */
    boolean isBST(BSTNode p, int min, int max) {
        if (p == null) return true;
        int val = (Integer) p.info;
        if (val < min || val > max) return false;
        return isBST(p.left, min, val - 1) && isBST(p.right, val + 1, max);
    }

    /**
     * [T3] Max Width - Đếm bề ngang rộng nhất của cây.
     */
    int getMaxWidth() {
        if (root == null) return 0;
        int maxWidth = 0;
        Queue<BSTNode> q = new LinkedList<>();
        q.add(root);
        
        while (!q.isEmpty()) {
            int count = q.size();
            maxWidth = Math.max(maxWidth, count);
            for (int i = 0; i < count; i++) {
                BSTNode p = q.poll();
                if (p.left != null) q.add(p.left);
                if (p.right != null) q.add(p.right);
            }
        }
        return maxWidth;
    }

    /**
     * [T4] Lấy các Node ở một Level cụ thể (0-based)
     */
    String getNodesAtLevel(int targetLevel) {
        StringBuilder sb = new StringBuilder();
        getNodesAtLevelUtil(root, targetLevel, sb);
        return sb.length() > 0 ? sb.toString().trim() : "No node";
    }

    void getNodesAtLevelUtil(BSTNode p, int target, StringBuilder sb) {
        if (p == null) return;
        if (target == 0) {
            sb.append(p.info.toString()).append(" ");
        } else {
            getNodesAtLevelUtil(p.left, target - 1, sb);
            getNodesAtLevelUtil(p.right, target - 1, sb);
        }
    }

    /**
     * [T5] Tính tổng các giá trị bên trong Subtree của một Node
     */
    int sumSubtree(Object target) {
        BSTNode p = root;
        while (p != null) {
            int cmp = comparator.compare(p.info, target);
            if (cmp == 0) break;
            if (cmp > 0) p = p.left;
            else p = p.right;
        }
        if (p == null) return -1;
        return sumSubtreeUtil(p);
    }

    int sumSubtreeUtil(BSTNode p) {
        if (p == null) return 0;
        return (Integer) p.info + sumSubtreeUtil(p.left) + sumSubtreeUtil(p.right);
    }

    /**
     * [T6] Build Expression Tree từ Postfix (Dành cho cây kiểu String)
     */
    BSTNode buildExpressionTree(String postfix) {
        java.util.Stack<BSTNode> stack = new java.util.Stack<>();
        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);
            if (c == ' ') continue;
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                BSTNode right = stack.pop();
                BSTNode left = stack.pop();
                BSTNode node = new BSTNode(c);
                node.left = left;
                node.right = right;
                stack.push(node);
            } else {
                stack.push(new BSTNode(c));
            }
        }
        return stack.isEmpty() ? null : stack.pop();
    }

    String getInfixExpression(BSTNode p) {
        if (p == null) return "";
        if (p.left == null && p.right == null) return p.info.toString();
        return "(" + getInfixExpression(p.left) + p.info + getInfixExpression(p.right) + ")";
    }

    /**
     * [T7] Build Balanced BST từ một Sorted Array.
     */
    void buildBalancedBST(Object[] sortedArr) {
        root = buildBalancedBSTUtil(sortedArr, 0, sortedArr.length - 1);
    }

    BSTNode buildBalancedBSTUtil(Object[] arr, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        BSTNode node = new BSTNode(arr[mid]);
        node.left = buildBalancedBSTUtil(arr, start, mid - 1);
        node.right = buildBalancedBSTUtil(arr, mid + 1, end);
        return node;
    }

    /**
     * [T8] Hậu duệ, Khoảng cách (Relatives & Distance)
     */
    String getDescendants(Object target) {
        BSTNode p = root;
        while (p != null) {
            int cmp = comparator.compare(p.info, target);
            if (cmp == 0) break;
            if (cmp > 0) p = p.left;
            else p = p.right;
        }
        if (p == null || (p.left == null && p.right == null)) return "No descendants";
        StringBuilder sb = new StringBuilder();
        preOrderAppend(p.left, sb);
        preOrderAppend(p.right, sb);
        return sb.toString().trim();
    }

    void preOrderAppend(BSTNode p, StringBuilder sb) {
        if (p != null) {
            sb.append(p.info.toString()).append(" ");
            preOrderAppend(p.left, sb);
            preOrderAppend(p.right, sb);
        }
    }

    int getDistance(Object val1, Object val2) {
        BSTNode lca = findLCA(root, val1, val2);
        if (lca == null) return -1;
        return getLevelFromNode(lca, val1, 0) + getLevelFromNode(lca, val2, 0);
    }

    int getLevelFromNode(BSTNode p, Object target, int level) {
        if (p == null) return -1;
        int cmp = comparator.compare(p.info, target);
        if (cmp == 0) return level;
        if (cmp > 0) return getLevelFromNode(p.left, target, level + 1);
        return getLevelFromNode(p.right, target, level + 1);
    }
}
