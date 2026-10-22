package tree;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BSTree {
    TreeNode root;

    public BSTree() {
        root = null;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public void clear() {
        root = null;
    }

    // ==========================================
    // 1. HAM INSERT (CHEN)
    // ==========================================

    // Chen x vao cay BST
    public void insert(Item x) {
        if (isEmpty()) {
            root = new TreeNode(x);
            return;
        }
        TreeNode f = null;
        TreeNode p = root;
        while (p != null) {
            if (p.info.price == x.price) {
                System.out.println("Gia tri " + x.price + " da ton tai!");
                return;
            }
            f = p;
            if (x.price < p.info.price) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (x.price < f.info.price) {
            f.left = new TreeNode(x);
        } else {
            f.right = new TreeNode(x);
        }
    }

    // Chen x kem dieu kien (Vd: khong chen neu ten bat dau bang 'B')
    public void insertWithCondition(Item x) {
        if (x.name.charAt(0) == 'B') {
            return; // Khong chen
        }
        insert(x);
    }

    // ==========================================
    // 2. CAC HAM DUYET CAY (TRAVERSALS)
    // ==========================================

    public void fvisit(TreeNode p, RandomAccessFile f) throws Exception {
        if (p != null) f.writeBytes(p.info.toString() + " ");
    }

    // Duyet theo muc (Breadth First)
    public void breadthFirst(RandomAccessFile f) throws Exception {
        if (isEmpty()) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode p = q.poll();
            fvisit(p, f);
            if (p.left != null) q.add(p.left);
            if (p.right != null) q.add(p.right);
        }
        f.writeBytes("\r\n");
    }

    // Bien the PE: Duyet muc, ghi ra file toi da K node co price > X
    public void breadthFirstWithCondition(RandomAccessFile f, int K, int X) throws Exception {
        if (isEmpty()) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int count = 0;
        while (!q.isEmpty()) {
            TreeNode p = q.poll();
            if (p.info.price > X) {
                fvisit(p, f);
                count++;
                if (count == K) break; // Dung ghi khi da du K
            }
            if (p.left != null) q.add(p.left);
            if (p.right != null) q.add(p.right);
        }
        f.writeBytes("\r\n");
    }

    public void preOrder(TreeNode p, RandomAccessFile f) throws Exception {
        if (p == null) return;
        fvisit(p, f);
        preOrder(p.left, f);
        preOrder(p.right, f);
    }

    public void inOrder(TreeNode p, RandomAccessFile f) throws Exception {
        if (p == null) return;
        inOrder(p.left, f);
        fvisit(p, f);
        inOrder(p.right, f);
    }

    public void postOrder(TreeNode p, RandomAccessFile f) throws Exception {
        if (p == null) return;
        postOrder(p.left, f);
        postOrder(p.right, f);
        fvisit(p, f);
    }

    // Luu ket qua duyet vao ArrayList (Rat can thiet de tim Node thu K hoac Balance)
    public void inOrderToList(TreeNode p, ArrayList<TreeNode> list) {
        if (p == null) return;
        inOrderToList(p.left, list);
        list.add(p);
        inOrderToList(p.right, list);
    }

    public void preOrderToList(TreeNode p, ArrayList<TreeNode> list) {
        if (p == null) return;
        list.add(p);
        preOrderToList(p.left, list);
        preOrderToList(p.right, list);
    }

    // ==========================================
    // 3. CAC HAM TIM KIEM (SEARCH & EXTRACTION)
    // ==========================================

    // Tim cha cua mot node p (Rat quan trong khi Xoa hoac Xoay)
    public TreeNode getParent(TreeNode p) {
        if (p == null || p == root) return null;
        TreeNode q = root;
        TreeNode parent = null;
        while (q != null && q != p) {
            parent = q;
            if (p.info.price < q.info.price) {
                q = q.left;
            } else {
                q = q.right;
            }
        }
        return parent; // tra ve null neu p khong ton tai
    }

    // Bien the PE: Tim node thu K (K tu 1) theo thu tu inOrder thoa man dieu kien (co 1 con)
    public TreeNode findNodeWithCondition(int k) {
        ArrayList<TreeNode> list = new ArrayList<>();
        inOrderToList(root, list); // Dump ca cay vao list
        int count = 0;
        for (TreeNode p : list) {
            // Dieu kien: node co dung 1 con
            if ((p.left != null && p.right == null) || (p.left == null && p.right != null)) {
                count++;
                if (count == k) return p;
            }
        }
        return null;
    }

    // ==========================================
    // 4. HAM XOA THEO COPY (DELETE BY COPYING)
    // ==========================================

    // Xoa node p bang cach lay Node lon nhat cua nhanh Trai len the cho
    public void deleteByCopying(TreeNode p) {
        if (p == null) return;
        
        // 1. Truong hop p la la (Leaf)
        if (p.left == null && p.right == null) {
            if (p == root) {
                root = null;
            } else {
                TreeNode parent = getParent(p);
                if (parent.left == p) parent.left = null;
                else parent.right = null;
            }
            return;
        }
        
        // 2. Truong hop p chi co 1 con (Left hoac Right)
        if (p.left != null && p.right == null) {
            if (p == root) {
                root = p.left;
            } else {
                TreeNode parent = getParent(p);
                if (parent.left == p) parent.left = p.left;
                else parent.right = p.left;
            }
            return;
        }
        if (p.left == null && p.right != null) {
            if (p == root) {
                root = p.right;
            } else {
                TreeNode parent = getParent(p);
                if (parent.left == p) parent.left = p.right;
                else parent.right = p.right;
            }
            return;
        }
        
        // 3. Truong hop p co ca 2 con
        // Tim node phai nhat cua nhanh trai (Max cua nhanh trai)
        TreeNode q = p.left;
        while (q.right != null) {
            q = q.right;
        }
        // Copy data
        p.info = q.info;
        // Goi de quy xoa q (luc nay q chac chan chi co toi da 1 con)
        deleteByCopying(q);
    }

    // ==========================================
    // 5. ROTATION & BALANCING (XOAY VA CAN BANG)
    // ==========================================

    // Xoay phai tai p
    public TreeNode rotateRight(TreeNode p) {
        if (p == null || p.left == null) return p;
        TreeNode q = p.left;
        p.left = q.right;
        q.right = p;
        return q;
    }

    // Xoay trai tai p
    public TreeNode rotateLeft(TreeNode p) {
        if (p == null || p.right == null) return p;
        TreeNode q = p.right;
        p.right = q.left;
        q.left = p;
        return q;
    }

    // De PE thuong bao: Xoay phai tai node p nao do. Ta phai gan lai voi Cha cua p
    public void rotateRightUpdateParent(TreeNode p) {
        TreeNode parent = getParent(p);
        TreeNode newRoot = rotateRight(p);
        if (parent == null) {
            root = newRoot;
        } else {
            if (parent.left == p) parent.left = newRoot;
            else parent.right = newRoot;
        }
    }

    // Can bang toan bo cay (DSV Algorithm)
    public void balanceTree() {
        ArrayList<TreeNode> list = new ArrayList<>();
        inOrderToList(root, list); // Array da sort tang dan
        root = null; // Xoa cay
        buildBalance(list, 0, list.size() - 1);
    }

    private void buildBalance(ArrayList<TreeNode> list, int start, int end) {
        if (start > end) return;
        int mid = (start + end) / 2; // Lay diem giua lam goc
        TreeNode p = list.get(mid);
        insert(p.info); // Chen lai vao cay
        buildBalance(list, start, mid - 1); // De quy nua trai
        buildBalance(list, mid + 1, end); // De quy nua phai
    }

    // ==========================================
    // 6. TREE MATH (CHIEU CAO, DEM NODE)
    // ==========================================

    // Chieu cao cua mot node (Neu p la la -> height = 1)
    public int height(TreeNode p) {
        if (p == null) return 0;
        int hl = height(p.left);
        int hr = height(p.right);
        return (hl > hr ? hl : hr) + 1;
    }

    // Tinh level cua node p tren cay (root = 1)
    public int level(TreeNode p) {
        if (p == null) return 0;
        int level = 1;
        TreeNode q = root;
        while (q != null && q != p) {
            if (p.info.price < q.info.price) q = q.left;
            else q = q.right;
            level++;
        }
        if (q == p) return level;
        return 0; // khong thay
    }

    // Dem so node la
    public int countLeaves(TreeNode p) {
        if (p == null) return 0;
        if (p.left == null && p.right == null) return 1;
        return countLeaves(p.left) + countLeaves(p.right);
    }

    // Dem so node co dung 1 con
    public int countNodesWithOneChild(TreeNode p) {
        if (p == null) return 0;
        int count = 0;
        if ((p.left != null && p.right == null) || (p.left == null && p.right != null)) {
            count = 1;
        }
        return count + countNodesWithOneChild(p.left) + countNodesWithOneChild(p.right);
    }

    // ==========================================
    // 7. AVL TREE (CAN BANG DONG)
    // ==========================================

    // Tinh he so can bang (Balance Factor = Height Left - Height Right)
    public int getBalanceFactor(TreeNode p) {
        if (p == null) return 0;
        return height(p.left) - height(p.right);
    }

    // Kiem tra cay co phai la AVL khong (Ham de quy)
    public boolean isAVL(TreeNode p) {
        if (p == null) return true;
        int bf = getBalanceFactor(p);
        if (bf > 1 || bf < -1) return false;
        return isAVL(p.left) && isAVL(p.right);
    }

    // Thuat toan Insert tu dong can bang cua AVL Tree
    public void insertAVL(Item x) {
        root = insertAVLRec(root, x);
    }

    private TreeNode insertAVLRec(TreeNode node, Item x) {
        // 1. Chen nhu BST thong thuong
        if (node == null) {
            return new TreeNode(x);
        }
        if (x.price < node.info.price) {
            node.left = insertAVLRec(node.left, x);
        } else if (x.price > node.info.price) {
            node.right = insertAVLRec(node.right, x);
        } else {
            return node; // Khong cho phep trung
        }

        // 2. Tinh Balance Factor
        int balance = getBalanceFactor(node);

        // 3. Kiem tra 4 truong hop mat can bang

        // Left Left Case
        if (balance > 1 && x.price < node.left.info.price) {
            return rotateRight(node);
        }
        // Right Right Case
        if (balance < -1 && x.price > node.right.info.price) {
            return rotateLeft(node);
        }
        // Left Right Case
        if (balance > 1 && x.price > node.left.info.price) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        // Right Left Case
        if (balance < -1 && x.price < node.right.info.price) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // ==========================================
    // 8. ADVANCED TRAVERSALS & PATH MATH
    // ==========================================

    // Duyet Zigzag (Level 0: L->R, Level 1: R->L)
    public void zigzagTraversal(RandomAccessFile f) throws Exception {
        if (root == null) return;
        Stack<TreeNode> currentLevel = new Stack<>();
        Stack<TreeNode> nextLevel = new Stack<>();
        boolean leftToRight = true;

        currentLevel.push(root);

        while (!currentLevel.isEmpty()) {
            TreeNode node = currentLevel.pop();
            fvisit(node, f);

            if (leftToRight) {
                if (node.left != null) nextLevel.push(node.left);
                if (node.right != null) nextLevel.push(node.right);
            } else {
                if (node.right != null) nextLevel.push(node.right);
                if (node.left != null) nextLevel.push(node.left);
            }

            if (currentLevel.isEmpty()) {
                leftToRight = !leftToRight;
                Stack<TreeNode> temp = currentLevel;
                currentLevel = nextLevel;
                nextLevel = temp;
                f.writeBytes("\r\n"); // Xuong dong moi level
            }
        }
    }

    // Tim Max Path Sum (Duong di co tong price lon nhat)
    int max_path_sum;
    public int maximumPathSum(TreeNode p) {
        max_path_sum = Integer.MIN_VALUE;
        maxPathDown(p);
        return max_path_sum;
    }

    private int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        max_path_sum = Math.max(max_path_sum, left + right + node.info.price);
        return Math.max(left, right) + node.info.price;
    }

    // Tim Diameter cua cay (Duong kinh: So node toi da tren duong di giua 2 la)
    int diameterMax;
    public int diameter(TreeNode p) {
        diameterMax = 0;
        diameterDown(p);
        return diameterMax;
    }

    private int diameterDown(TreeNode node) {
        if (node == null) return 0;
        int left = diameterDown(node.left);
        int right = diameterDown(node.right);
        diameterMax = Math.max(diameterMax, left + right);
        return Math.max(left, right) + 1;
    }

    // ==========================================
    // 9. CAC HAM THUONG GAP TRONG PE (PE EXAM REQUIREMENTS)
    // ==========================================

    // Xoa bang thuat toan Merging (Delete by Merging)
    public void deleteByMerging(TreeNode p) {
        if (p == null) return;
        TreeNode parent = getParent(p);
        
        // 1. Truong hop p la la hoac chi co 1 con
        if (p.left == null || p.right == null) {
            TreeNode child = (p.left != null) ? p.left : p.right;
            if (p == root) {
                root = child;
            } else {
                if (parent.left == p) parent.left = child;
                else parent.right = child;
            }
            return;
        }

        // 2. Truong hop p co ca 2 con
        // Tim node Phai nhat cua nhanh Trai (Max left)
        TreeNode q = p.left;
        while (q.right != null) {
            q = q.right;
        }
        
        // Noi nhanh Phai cua p vao ben Phai cua q
        q.right = p.right;
        
        // Noi nhanh Trai cua p thay the vi tri cua p
        if (p == root) {
            root = p.left;
        } else {
            if (parent.left == p) parent.left = p.left;
            else parent.right = p.left;
        }
    }

    // Xoay tai node thoa man dieu kien (Vd: Node dau tien co 2 con khi duyet preOrder)
    public void rotateRightAtNodeWithCondition() {
        ArrayList<TreeNode> list = new ArrayList<>();
        preOrderToList(root, list);
        for (TreeNode p : list) {
            if (p.left != null && p.right != null) {
                rotateRightUpdateParent(p);
                break; // Chi xoay 1 lan tai node dau tien tim thay
            }
        }
    }

    // Dem so node co dung 2 con
    public int countNodesWithTwoChildren(TreeNode p) {
        if (p == null) return 0;
        int count = 0;
        if (p.left != null && p.right != null) {
            count = 1;
        }
        return count + countNodesWithTwoChildren(p.left) + countNodesWithTwoChildren(p.right);
    }

    // Lay ket qua duyet postOrder vao danh sach de xu ly (vd tim node thu k)
    public void postOrderToList(TreeNode p, ArrayList<TreeNode> list) {
        if (p == null) return;
        postOrderToList(p.left, list);
        postOrderToList(p.right, list);
        list.add(p);
    }

    // ==========================================
    // 10. DUYET TRA VE STRING KHI KHONG IN RA MAN HINH
    // (GUARANTEED TO ONLY RETURN STRING, NO PRINTING)
    // ==========================================

    public String breadthFirstToString() {
        if (isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode p = q.poll();
            sb.append("(").append(p.info.name).append(",").append(p.info.price).append(") ");
            if (p.left != null) q.add(p.left);
            if (p.right != null) q.add(p.right);
        }
        return sb.toString().trim();
    }

    private void preOrderToStringRec(TreeNode p, StringBuilder sb) {
        if (p == null) return;
        sb.append("(").append(p.info.name).append(",").append(p.info.price).append(") ");
        preOrderToStringRec(p.left, sb);
        preOrderToStringRec(p.right, sb);
    }

    public String preOrderToString() {
        StringBuilder sb = new StringBuilder();
        preOrderToStringRec(root, sb);
        return sb.toString().trim();
    }

    private void inOrderToStringRec(TreeNode p, StringBuilder sb) {
        if (p == null) return;
        inOrderToStringRec(p.left, sb);
        sb.append("(").append(p.info.name).append(",").append(p.info.price).append(") ");
        inOrderToStringRec(p.right, sb);
    }

    public String inOrderToString() {
        StringBuilder sb = new StringBuilder();
        inOrderToStringRec(root, sb);
        return sb.toString().trim();
    }

    private void postOrderToStringRec(TreeNode p, StringBuilder sb) {
        if (p == null) return;
        postOrderToStringRec(p.left, sb);
        postOrderToStringRec(p.right, sb);
        sb.append("(").append(p.info.name).append(",").append(p.info.price).append(") ");
    }

    public String postOrderToString() {
        StringBuilder sb = new StringBuilder();
        postOrderToStringRec(root, sb);
        return sb.toString().trim();
    }

    // Duyet BFS co dieu kien va gioi han so luong node (VD: in K node thoa man x.price > X)
    public String breadthFirstConditionToString(java.util.function.Predicate<Item> condition, int maxNodes) {
        if (isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int count = 0;
        while (!q.isEmpty()) {
            TreeNode p = q.poll();
            if (condition.test(p.info)) {
                sb.append("(").append(p.info.name).append(",").append(p.info.price).append(") ");
                count++;
                if (count == maxNodes) break;
            }
            if (p.left != null) q.add(p.left);
            if (p.right != null) q.add(p.right);
        }
        return sb.toString().trim();
    }
    
    // ==========================================
    // 11. DUYET NGUOC (REVERSE IN-ORDER: RIGHT - ROOT - LEFT)
    // ==========================================
    
    // Duyet nguoc va in ra cac node co thuoc tinh >= V (Vd: price >= V)
    // Dinh dang chuoi tra ve giong voi cac ham ToString khac
    private void reverseTraversingGTERec(TreeNode p, double V, StringBuilder sb) {
        if (p == null) return;
        reverseTraversingGTERec(p.right, V, sb);
        if (p.info.price >= V) { // Neu condition phuc tap hon, co the dung Predicate<Item>
            sb.append("(").append(p.info.name).append(",").append(p.info.price).append(") ");
        }
        reverseTraversingGTERec(p.left, V, sb);
    }
    
    public String reverseTraversingGTE(double V) {
        StringBuilder sb = new StringBuilder();
        reverseTraversingGTERec(root, V, sb);
        return sb.toString().trim();
    }
    
    // Duyet nguoc co dieu kien chung (dung lambda hoac Predicate)
    private void reverseInOrderConditionRec(TreeNode p, java.util.function.Predicate<Item> condition, StringBuilder sb) {
        if (p == null) return;
        reverseInOrderConditionRec(p.right, condition, sb);
        if (condition.test(p.info)) {
            sb.append("(").append(p.info.name).append(",").append(p.info.price).append(") ");
        }
        reverseInOrderConditionRec(p.left, condition, sb);
    }
    
    public String reverseInOrderConditionToString(java.util.function.Predicate<Item> condition) {
        StringBuilder sb = new StringBuilder();
        reverseInOrderConditionRec(root, condition, sb);
        return sb.toString().trim();
    }
}
