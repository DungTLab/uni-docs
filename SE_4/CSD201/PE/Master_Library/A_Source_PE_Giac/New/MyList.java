
import java.io.*;
import java.util.*;

// ==================================================================================================================================
//  MyList.java — Singly Linked List TONG HOP cho CSD201
//  => Dung Object: hoat dong voi moi kieu du lieu (Tiger, Cape, Order, Drum, ...).
//  => KHONG dung System.out — tat ca hien thi deu RETURN String.
//  => head: node dau; tail: node cuoi.
// ==================================================================================================================================
//
//  [MUC LUC & SEARCH KEYS]
//    ||____ADD______   -> [B] addFirst(), addLast(), [F] insertAtPosition(), insertBefore(), insertAfter()
//    ||___DELETE____   -> [C] removeFirst(), removeLast(), remove(), deleteNode(), deleteNodeAt(), deleteMax()
//    ||___SORT______   -> [H] sortAll(), sortLastN(), bubbleSort(), insertionSort(), mergeSort()
//    ||___SEARCH____   -> [D] get(), getFirst(), getLast(), getNodeAt(), indexOf(), contains(), search()
//    ||___DISPLAY___   -> [E] traverse(), ftraverse()
//    ||___REVERSE___   -> [I] reverse()
//
//    [A] KHOI TAO & CO BAN        : isEmpty(), size(), clear()
//    [B] THEM PHAN TU             : addFirst(), addLast(), addLast(a,b,c)
//    [C] XOA PHAN TU              : removeFirst(), removeLast(), remove(), deleteNode(), deleteNodeAt()
//    [D] TRUY CAP / TIM KIEM      : get(), getFirst(), getLast(), set(), getNodeAt(), indexOf(), contains(), search()
//    [E] DUYET / GHI FILE         : traverse(), fvisit(), ftraverse()
//    [F] CHEN PHAN TU             : insertAtPosition(), insertBefore(), insertAfter()
//    [G] TIM MAX / MIN            : findMax(), findSecondMax(), deleteMax()
//    [H] SAP XEP (SORT)           : sortAll(), sortLastN(), bubbleSort(), insertionSort(), swap(), mergeSort()
//    [I] BIEN DOI DANH SACH       : reverse(), removeDuplicates(), moveMaxToTail(), moveMinToHead()
//    [J] CHUYEN DOI               : toArray(), loadFromFile(), saveToFile()
//    [K] BO LOC & DEM              : count(), filter()
//    [L] THUAT TOAN NANG CAO      : findMiddle(), hasCycle()
//
// ==================================================================================================================================
public class MyList {

    Node head, tail;
    private StringBuilder outputBuffer = new StringBuilder();

    // setOutputBuffer - Cho phhep dung chung outputBuffer voi Q1.java de de dang tich hop
    public void setOutputBuffer(StringBuilder sb) {
        this.outputBuffer = sb;
    }

    // Helper output
    private void print(Object o) {
        outputBuffer.append(o);
    }

    private void println(Object o) {
        outputBuffer.append(o).append("\n");
    }

    // ==================================================================
    //  [A] KHOI TAO & CO BAN
    // ==================================================================
    /**
     * Tao danh sach rong.
     */
    public MyList() {
        head = tail = null;
    }

    /**
     * Tra ve true neu danh sach rong.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Dem tong so phan tu trong danh sach. O(n). Goi: int n = list.size();
     */
    public int size() {
        int count = 0;
        Node p = head;
        while (p != null) {
            count++;
            p = p.next;
        }
        return count;
    }

    /**
     * Xoa toan bo danh sach.
     */
    public void clear() {
        head = tail = null;
    }

    // ==================================================================
    //  [B] THEM PHAN TU
    // ==================================================================
    /**
     * [B1] Them node vao DAU danh sach. Goi: list.addFirst(new Tiger(5,
     * "Sora"));
     */
    public void addFirst(Object x) {
        Node newNode = new Node(x, head); // next = head cu
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            head = newNode;
        }
    }

    /**
     * [B2] Them node vao CUOI danh sach. Goi: list.addLast(new Tiger(5,
     * "Sora"));
     */
    public void addLast(Object x) {
        Node newNode = new Node(x); // next = null
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    /**
     * [B3] Them phan tu dang (String, int, int) vao cuoi — dung cho loadData de
     * thi. QUAN TRONG: Trong bai thi thuc te, sua thanh: addLast(new TenLop(a,
     * b, c)); Mac dinh: luu dang chuoi "a | b | c" (chi de tham khao).
     */
    public void addLast(String a, int b, int c) {
        // Vi du trong de thi: addLast(new Drum(tower, sound, type));
        // Mac dinh luu dang chuoi:
        addLast(a + " | " + b + " | " + c);
    }

    // ==================================================================
    //  [C] XOA PHAN TU
    // ==================================================================
    /**
     * [C1] Xoa va tra ve phan tu DAU. Tra ve null neu rong. Goi: Object o =
     * list.removeFirst();
     */
    public Object removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Object data = head.info;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
        }
        return data;
    }

    /**
     * [C2] Xoa va tra ve phan tu CUOI. Tra ve null neu rong. Goi: Object o =
     * list.removeLast();
     */
    public Object removeLast() {
        if (isEmpty()) {
            return null;
        }
        Object data = tail.info;
        if (head == tail) {
            head = tail = null;
        } else {
            Node p = head;
            while (p.next != tail) {
                p = p.next;
            }
            p.next = null;
            tail = p;
        }
        return data;
    }

    /**
     * [C3] Xoa phan tu x DAU TIEN tim thay (dung equals()). Tra ve true neu xoa
     * thanh cong, nguoc lai false. Goi: list.remove(myObject);
     */
    public boolean remove(Object x) {
        if (isEmpty() || x == null) {
            return false;
        }
        if (x.equals(head.info)) {
            removeFirst();
            return true;
        }
        Node prev = head, curr = head.next;
        while (curr != null) {
            if (x.equals(curr.info)) {
                prev.next = curr.next;
                if (curr == tail) {
                    tail = prev;
                }
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    /**
     * [C4] Xoa mot NODE cu thu khoi danh sach. Khong lam gi neu khong tim thay.
     * Goi: list.deleteNode(nodeRef);
     */
    public void deleteNode(Node target) {
        if (target == null || isEmpty()) {
            return;
        }
        if (head == target) {
            removeFirst();
            return;
        }
        Node prev = head;
        while (prev.next != null && prev.next != target) {
            prev = prev.next;
        }
        if (prev.next == target) {
            prev.next = target.next;
            if (target == tail) {
                tail = prev;
            }
        }
    }

    /**
     * [C4] Xoa node tai VI TRI pos (0-indexed). Khong lam gi neu pos ngoai pham
     * vi. Goi: list.deleteNodeAt(2); // xoa node thu 3
     */
    public void deleteNodeAt(int pos) {
        if (isEmpty() || pos < 0) {
            return;
        }
        if (pos == 0) {
            removeFirst();
            return;
        }
        Node prev = head;
        int i = 0;
        while (prev.next != null && i < pos - 1) {
            prev = prev.next;
            i++;
        }
        if (prev.next != null) {
            if (prev.next == tail) {
                tail = prev;
            }
            prev.next = prev.next.next;
        }
    }

    // ==================================================================
    //  [D] TRUY CAP / TIM KIEM
    // ==================================================================
    /**
     * [D1a] Lay data cua phan tu DAU.
     */
    public Object getFirst() {
        return isEmpty() ? null : head.info;
    }

    /**
     * [D1b] Lay data cua phan tu CUOI.
     */
    public Object getLast() {
        return isEmpty() ? null : tail.info;
    }

    /**
     * [D1] Lay phan tu tai vi tri index (0-indexed). Tra ve null neu ngoai pham
     * vi. Goi: Object o = list.get(2); // lay phan tu thu 3
     */
    public Object get(int index) {
        if (index < 0) {
            return null;
        }
        Node p = head;
        int i = 0;
        while (p != null) {
            if (i == index) {
                return p.info;
            }
            p = p.next;
            i++;
        }
        return null;
    }

    /**
     * [D2] Thay the phan tu tai index (0-indexed). Khong lam gi neu ngoai pham
     * vi. Goi: list.set(2, newObject);
     */
    public void set(int index, Object x) {
        if (index < 0) {
            return;
        }
        Node p = head;
        int i = 0;
        while (p != null) {
            if (i == index) {
                p.info = x;
                return;
            }
            p = p.next;
            i++;
        }
    }

    /**
     * [D3] Lay NODE tai vi tri pos (0-indexed). Tra ve null neu ngoai pham vi.
     * Dung noi bo cho cac thao tac can tham chieu Node.
     */
    public Node getNodeAt(int pos) {
        if (pos < 0) {
            return null;
        }
        Node p = head;
        int i = 0;
        while (p != null) {
            if (i == pos) {
                return p;
            }
            p = p.next;
            i++;
        }
        return null;
    }

    /**
     * [D4] Kiem tra danh sach co chua x hay khong (dung equals()). Goi:
     * list.contains(myObject)
     */
    public boolean contains(Object x) {
        return indexOf(x) != -1;
    }

    /**
     * [D4b] Tim index cua phan tu x dau tien (dung equals()), tra ve -1 neu
     * khong co.
     */
    public int indexOf(Object x) {
        if (isEmpty() || x == null) {
            return -1;
        }
        Node p = head;
        int index = 0;
        while (p != null) {
            if (x.equals(p.info)) {
                return index;
            }
            p = p.next;
            index++;
        }
        return -1;
    }

    /**
     * [D5] Tim node DAU TIEN co comp.compare(node.info, key) == 0. Tra ve null
     * neu khong tim thay. Vi du: list.search(myKey, (a,b) ->
     * ((Tiger)a).getSound() - ((Tiger)b).getSound())
     */
    public Node search(Object key, Comparator comp) {
        Node p = head;
        while (p != null) {
            if (comp.compare(p.info, key) == 0) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    // ==================================================================
    //  [E] DUYET / GHI FILE
    // ==================================================================
    /**
     * [E1] Duyet danh sach, tra ve String, moi phan tu tren 1 dong. Goi: String
     * s = list.traverse();
     */
    public String traverse() {
        StringBuilder sb = new StringBuilder();
        Node p = head;
        while (p != null) {
            sb.append(p.info.toString());
            if (p.next != null) {
                sb.append("\n");
            }
            p = p.next;
        }
        return sb.toString();
    }

    /**
     * [E2] Ghi 1 node vao RandomAccessFile. Helper cho ftraverse().
     */
    public void fvisit(Node p, RandomAccessFile f) throws IOException {
        if (p != null) {
            f.writeBytes(p.info.toString() + "\r\n");
        }
    }

    /**
     * [E3] Ghi toan bo danh sach vao RandomAccessFile. Goi:
     * list.ftraverse(raf);
     */
    public void ftraverse(RandomAccessFile f) throws IOException {
        Node p = head;
        while (p != null) {
            fvisit(p, f);
            p = p.next;
        }
    }

    /**
     * [E2b] Ghi 1 node vao outputBuffer.
     */
    public void fvisit(Node p) {
        if (p != null) {
            println(p.info.toString());
        }
    }

    /**
     * [E3b] Ghi toan bo danh sach vao outputBuffer.
     */
    public void ftraverse() {
        Node p = head;
        while (p != null) {
            fvisit(p);
            p = p.next;
        }
    }

    // ==================================================================
    //  [F] CHEN PHAN TU (INSERT AT POSITION)
    // ==================================================================
    /**
     * [F1] Chen node tai VI TRI pos (0-indexed). pos <= 0: chen vao dau; pos >=
     * size: chen vao cuoi. Goi: list.insertAtPosition(2, newObject);
     */
    public void insertAtPosition(int pos, Object x) {
        if (pos <= 0 || isEmpty()) {
            addFirst(x);
            return;
        }
        int n = size();
        if (pos >= n) {
            addLast(x);
            return;
        }
        Node prev = head;
        for (int i = 0; i < pos - 1; i++) {
            prev = prev.next;
        }
        prev.next = new Node(x, prev.next); // Nhay vao giua
    }

    /**
     * [F2] Chen node moi TRUOC target node. Khong lam gi neu target khong ton
     * tai trong danh sach.
     */
    public void insertBefore(Node target, Object x) {
        if (target == null || isEmpty()) {
            return;
        }
        if (head == target) {
            addFirst(x);
            return;
        }
        Node prev = head;
        while (prev.next != null && prev.next != target) {
            prev = prev.next;
        }
        if (prev.next == target) {
            prev.next = new Node(x, target);
        }
    }

    /**
     * [F3] Chen node moi SAU target node. Khong lam gi neu target la null.
     */
    public void insertAfter(Node target, Object x) {
        if (target == null) {
            return;
        }
        Node newNode = new Node(x, target.next);
        target.next = newNode;
        if (target == tail) {
            tail = newNode; // Cap nhat tail neu can
        }
    }

    // ==================================================================
    //  [G] TIM MAX / MIN
    // ==================================================================
    /**
     * [G1] Tim node co gia tri LON NHAT theo Comparator. Tra ve null neu danh
     * sach rong. Vi du: Node maxNode = list.findMax((a,b) ->
     * ((Tiger)a).getSound() - ((Tiger)b).getSound())
     */
    public Node findMax(Comparator comp) {
        if (isEmpty()) {
            return null;
        }
        Node maxNode = head, p = head.next;
        while (p != null) {
            if (comp.compare(p.info, maxNode.info) > 0) {
                maxNode = p;
            }
            p = p.next;
        }
        return maxNode;
    }

    /**
     * [G2] Tim node co gia tri LON THU 2 theo Comparator. Tra ve null neu danh
     * sach co it hon 2 phan tu.
     */
    public Node findSecondMax(Comparator comp) {
        if (isEmpty() || head.next == null) {
            return null;
        }
        Node max = head, secondMax = null, p = head.next;
        while (p != null) {
            if (comp.compare(p.info, max.info) > 0) {
                secondMax = max;
                max = p;
            } else if (secondMax == null || comp.compare(p.info, secondMax.info) > 0) {
                if (comp.compare(p.info, max.info) != 0) {
                    secondMax = p;
                }
            }
            p = p.next;
        }
        return secondMax;
    }

    /**
     * [G3] Tim va XOA node co gia tri LON NHAT theo Comparator.
     */
    public void deleteMax(Comparator comp) {
        Node maxNode = findMax(comp);
        if (maxNode != null) {
            deleteNode(maxNode);
        }
    }

    // ==================================================================
    //  [H] SAP XEP (SORT)
    // ==================================================================
    /**
     * [H1] Hoan doi data cua 2 node (swap info). Helper cho cac thuat toan sap
     * xep.
     */
    public void swap(Node a, Node b) {
        if (a == null || b == null || a == b) {
            return;
        }
        Object temp = a.info;
        a.info = b.info;
        b.info = temp;
    }

    /**
     * [H2] Sap xep TOAN BO danh sach bang Selection Sort. ascending=true: tang
     * dan; ascending=false: giam dan. Vi du: list.sortAll((a,b) ->
     * ((Tiger)a).getSound() - ((Tiger)b).getSound(), true)
     */
    public void sortAll(Comparator comp, boolean ascending) {
        if (isEmpty() || head.next == null) {
            return;
        }
        for (Node i = head; i.next != null; i = i.next) {
            Node selected = i;
            for (Node j = i.next; j != null; j = j.next) {
                boolean betterFound = ascending
                        ? comp.compare(j.info, selected.info) < 0
                        : comp.compare(j.info, selected.info) > 0;
                if (betterFound) {
                    selected = j;
                }
            }
            if (selected != i) {
                swap(i, selected);
            }
        }
    }

    /**
     * [H3] Sap xep N PHAN TU CUOI cua danh sach bang Selection Sort. Neu n >=
     * size(): sap xep toan bo. Vi du: list.sortLastN(3, comp, true); // sort 3
     * phan tu cuoi tang dan
     */
    public void sortLastN(int n, Comparator comp, boolean ascending) {
        int sz = size();
        if (sz <= 1 || n <= 0) {
            return;
        }
        if (n > sz) {
            n = sz;
        }
        Node startNode = getNodeAt(sz - n);
        if (startNode == null) {
            return;
        }
        for (Node i = startNode; i != null && i.next != null; i = i.next) {
            Node selected = i;
            for (Node j = i.next; j != null; j = j.next) {
                boolean betterFound = ascending
                        ? comp.compare(j.info, selected.info) < 0
                        : comp.compare(j.info, selected.info) > 0;
                if (betterFound) {
                    selected = j;
                }
            }
            if (selected != i) {
                swap(i, selected);
            }
        }
    }

    /**
     * [H4] Sap xep bang Bubble Sort. ascending=true: tang dan; ascending=false:
     * giam dan.
     */
    public void bubbleSort(Comparator comp, boolean ascending) {
        if (isEmpty() || head.next == null) {
            return;
        }
        boolean swapped;
        do {
            swapped = false;
            Node p = head;
            while (p.next != null) {
                boolean shouldSwap = ascending
                        ? comp.compare(p.info, p.next.info) > 0
                        : comp.compare(p.info, p.next.info) < 0;
                if (shouldSwap) {
                    swap(p, p.next);
                    swapped = true;
                }
                p = p.next;
            }
        } while (swapped);
    }

    /**
     * [H4b] Sap xep bang Insertion Sort (on dinh, O(n^2)).
     */
    public void insertionSort(Comparator comp, boolean ascending) {
        if (isEmpty() || head.next == null) {
            return;
        }
        Node sorted = null;
        Node current = head;
        while (current != null) {
            Node next = current.next;
            if (sorted == null || (ascending
                    ? comp.compare(current.info, sorted.info) <= 0
                    : comp.compare(current.info, sorted.info) >= 0)) {
                current.next = sorted;
                sorted = current;
            } else {
                Node p = sorted;
                while (p.next != null) {
                    boolean condition = ascending
                            ? comp.compare(current.info, p.next.info) > 0
                            : comp.compare(current.info, p.next.info) < 0;
                    if (!condition) {
                        break;
                    }
                    p = p.next;
                }
                current.next = p.next;
                p.next = current;
            }
            current = next;
        }
        head = sorted;
        // Cap nhat tail
        Node p = head;
        while (p != null && p.next != null) {
            p = p.next;
        }
        tail = p;
    }

    /**
     * [H5] Sap xep nhanh bang Merge Sort (O(n log n)) cho danh sach lon.
     */
    public void mergeSort(Comparator comp, boolean ascending) {
        head = mergeSort(head, comp, ascending);
        // Cap nhat lai tail
        if (head == null) {
            tail = null;
        } else {
            Node p = head;
            while (p.next != null) {
                p = p.next;
            }
            tail = p;
        }
    }

    private Node mergeSort(Node h, Comparator comp, boolean ascending) {
        if (h == null || h.next == null) {
            return h;
        }
        Node middle = getMiddle(h);
        Node nextToMiddle = middle.next;
        middle.next = null;
        Node left = mergeSort(h, comp, ascending);
        Node right = mergeSort(nextToMiddle, comp, ascending);
        return sortedMerge(left, right, comp, ascending);
    }

    private Node sortedMerge(Node a, Node b, Comparator comp, boolean ascending) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        Node result = null;
        int cmp = comp.compare(a.info, b.info);
        boolean condition = ascending ? cmp <= 0 : cmp >= 0;
        if (condition) {
            result = a;
            result.next = sortedMerge(a.next, b, comp, ascending);
        } else {
            result = b;
            result.next = sortedMerge(a, b.next, comp, ascending);
        }
        return result;
    }

    private Node getMiddle(Node h) {
        if (h == null) {
            return h;
        }
        Node slow = h, fast = h;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // ==================================================================
    //  [I] BIEN DOI DANH SACH
    // ==================================================================
    /**
     * [I1] Dao nguoc danh sach tai cho. Sau khi dao: head cu tro thanh tail,
     * tail cu tro thanh head. Goi: list.reverse();
     */
    public void reverse() {
        if (isEmpty() || head.next == null) {
            return;
        }
        tail = head;
        Node prev = null, curr = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    /**
     * Hàm chính để gọi từ bên ngoài (Q1.java) Ví dụ gọi:
     * list.reverseKBlocks(k);
     */
    public void reverseKBlocks(int k) {
        if (head == null || k <= 1) {
            return;
        }

        // Gọi hàm đệ quy đổi link
        head = reverseKGroup(head, k);

        // Cập nhật lại con trỏ tail (do head và tail bị thay đổi sau khi reverse)
        Node p = head;
        while (p != null && p.next != null) {
            p = p.next;
        }
        tail = p;
    }

    /**
     * Hàm đệ quy xử lý đảo ngược từng khối K phần tử bằng Links
     */
    private Node reverseKGroup(Node startNode, int k) {
        if (startNode == null) {
            return null;
        }

        Node current = startNode;
        Node next = null;
        Node prev = null;
        int count = 0;

        // 1. Đảo ngược K phần tử hiện tại (hoặc những phần tử còn sót lại nếu < K)
        while (count < k && current != null) {
            next = current.next; // Lưu giữ liên kết node tiếp theo
            current.next = prev; // Trỏ ngược lại node phía trước
            prev = current;      // Dịch prev lên
            current = next;      // Dịch current tới
            count++;
        }

        // 2. Sau khi đảo, startNode giờ trở thành node CÚỐI của khối này.
        // Cần nối con trỏ next của nó với kết quả đảo ngược của khối tiếp theo.
        if (next != null) {
            startNode.next = reverseKGroup(next, k);
        }

        // 3. prev hiện đang đứng ở node ĐẦU của khối vừa bị đảo. Trả về prev.
        return prev;
    }

    /**
     * [I2] Xoa tat ca phan tu TRUNG LAP (giu lai lan xuat hien dau tien). Dung
     * Comparator de xac dinh su bang nhau (compare == 0). Goi:
     * list.removeDuplicates(comp);
     */
    public void removeDuplicates(Comparator comp) {
        if (isEmpty()) {
            return;
        }
        Node current = head;
        while (current != null) {
            Node prev = current, runner = current.next;
            while (runner != null) {
                if (comp.compare(current.info, runner.info) == 0) {
                    prev.next = runner.next;
                    if (runner == tail) {
                        tail = prev;
                    }
                } else {
                    prev = runner;
                }
                runner = prev.next;
            }
            current = current.next;
        }
    }

    /**
     * [I3] Di chuyen node co gia tri LON NHAT ve CUOI danh sach. Goi:
     * list.moveMaxToTail(comp);
     */
    public void moveMaxToTail(Comparator comp) {
        if (isEmpty() || head.next == null) {
            return;
        }
        Node maxNode = findMax(comp);
        if (maxNode != tail) {
            deleteNode(maxNode);
            addLast(maxNode.info);
        }
    }

    /**
     * [I4] Di chuyen node co gia tri NHO NHAT ve DAU danh sach. Goi:
     * list.moveMinToHead(comp);
     */
    public void moveMinToHead(Comparator comp) {
        if (isEmpty() || head.next == null) {
            return;
        }
        Node minNode = head, p = head.next;
        while (p != null) {
            if (comp.compare(p.info, minNode.info) < 0) {
                minNode = p;
            }
            p = p.next;
        }
        if (minNode != head) {
            deleteNode(minNode);
            addFirst(minNode.info);
        }
    }

    // ==================================================================
    //  [J] CHUYEN DOI (CONVERSION / IO)
    // ==================================================================
    /**
     * [J1] Chuyen danh sach thanh mang Object[] (head o index 0). Goi: Object[]
     * arr = list.toArray();
     */
    public Object[] toArray() {
        int n = size();
        Object[] arr = new Object[n];
        Node p = head;
        for (int i = 0; i < n; i++) {
            arr[i] = p.info;
            p = p.next;
        }
        return arr;
    }

    /**
     * [J2] Doc du lieu tu file van ban vao danh sach (moi dong = 1 phan tu
     * String). QUAN TRONG: Trong bai thi thuc te, sua ham nay de parse ra
     * object dung cau. Goi: list.loadFromFile("input.txt");
     */
    public void loadFromFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) {
                addLast(line);
            }
        }
        br.close();
    }

    /**
     * [J3] Luu danh sach ra file bang RandomAccessFile. Goi:
     * list.saveToFile("output.txt");
     */
    public void saveToFile(String filename) throws IOException {
        RandomAccessFile f = new RandomAccessFile(filename, "rw");
        f.setLength(0); // Xoa noi dung cu
        ftraverse(f);
        f.close();
    }

    // ==================================================================
    //  [K] BO LOC & DEM (FILTER & COUNT)
    // ==================================================================
    /**
     * [K1] Dem so node co comp.compare(node.info, key) == 0. Vi du:
     * list.count(keyObj, (a,b) -> ((Tiger)a).getSound() -
     * ((Tiger)b).getSound())
     */
    public int count(Object key, Comparator comp) {
        int count = 0;
        Node p = head;
        while (p != null) {
            if (comp.compare(p.info, key) == 0) {
                count++;
            }
            p = p.next;
        }
        return count;
    }

    /**
     * [K2] Thu thap cac node co comp.compare(node.info, key) == 0 vao danh sach
     * moi. Vi du: MyList result = list.filter(keyObj, comp);
     */
    public MyList filter(Object key, Comparator comp) {
        MyList result = new MyList();
        Node p = head;
        while (p != null) {
            if (comp.compare(p.info, key) == 0) {
                result.addLast(p.info);
            }
            p = p.next;
        }
        return result;
    }

    // ==================================================================
    //  [L] THUAT TOAN NANG CAO
    // ==================================================================
    /**
     * [L1] Tim node o GIUA danh sach (Floyd fast/slow pointer). So le: tra ve
     * chinh xac node giua. So chan: tra ve node giua thu 2 (vi tri n/2). Goi:
     * Node mid = list.findMiddle();
     */
    public Node findMiddle() {
        if (isEmpty()) {
            return null;
        }
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * [L2] Phat hien VONG TRON trong danh sach (Floyd cycle detection). Tra ve
     * true neu co chu ky (cycle) trong danh sach. Goi: boolean cycle =
     * list.hasCycle();
     */
    public boolean hasCycle() {
        if (isEmpty()) {
            return false;
        }
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true; // Gap nhau: co chu ky
            }
        }
        return false;
    }

    // ==================================================================
    //  [M] TÍNH TOÁN & THỐNG KÊ (MATH & STATS) - Mặc định kiểu Integer
    // ==================================================================
    /**
     * Tinh tong tat ca cac phan tu kieu Integer trong List.
     */
    public int sumInteger() {
        int sum = 0;
        Node p = head;
        while (p != null) {
            if (p.info instanceof Integer) {
                sum += (Integer) p.info;
            }
            p = p.next;
        }
        return sum;
    }

    /**
     * Tinh trung binh cong cac phan tu kieu Integer.
     */
    public double averageInteger() {
        int count = 0;
        int sum = 0;
        Node p = head;
        while (p != null) {
            if (p.info instanceof Integer) {
                sum += (Integer) p.info;
                count++;
            }
            p = p.next;
        }
        return count == 0 ? 0 : (double) sum / count;
    }

    // ==================================================================
    //  [N] THAO TÁC NÂNG CAO (ADVANCED MANIPULATION)
    // ==================================================================
    /**
     * Hoan doi du lieu (info) cua 2 Node. An toan khong lam dut lien ket.
     */
    public void swapData(Node p, Node q) {
        if (p == null || q == null) {
            return;
        }
        Object temp = p.info;
        p.info = q.info;
        q.info = temp;
    }

    /**
     * Xoay vong danh sach qua TRAI K buoc (Day K phan tu dau xuong cuoi).
     */
    public void rotateLeft(int k) {
        if (isEmpty() || k <= 0 || head.next == null) {
            return;
        }
        int n = size();
        k = k % n;
        if (k == 0) {
            return;
        }

        Node curr = head;
        for (int i = 1; i < k; i++) {
            curr = curr.next;
        }

        Node kthNode = curr;
        tail.next = head; // Noi cuoi thanh vong
        head = kthNode.next;
        kthNode.next = null; // Ngat vong tai diem K
        tail = kthNode;
    }

    /**
     * Xoay vong danh sach qua PHAI K buoc (Day K phan tu cuoi len dau).
     */
    public void rotateRight(int k) {
        if (isEmpty() || k <= 0 || head.next == null) {
            return;
        }
        int n = size();
        k = k % n;
        if (k == 0) {
            return;
        }
        rotateLeft(n - k); // Quay phai K buoc = Quay trai N - K buoc
    }

    // ==================================================================
    //  [O] CHIA TÁCH & GỘP (SPLIT & MERGE)
    // ==================================================================
    /**
     * Cat danh sach thanh 2 danh sach tai vi tri index (0-based). Tra ve danh
     * sach 2.
     */
    public MyList split(int index) {
        MyList list2 = new MyList();
        if (index <= 0 || index >= size()) {
            return list2;
        }

        Node p = getNodeAt(index - 1); // Tim node truoc cho cat
        list2.head = p.next;
        list2.tail = this.tail;

        p.next = null;
        this.tail = p;
        return list2;
    }

    /**
     * Noi danh sach khac vao cuoi danh sach hien tai.
     */
    public void concat(MyList otherList) {
        if (otherList == null || otherList.isEmpty()) {
            return;
        }
        if (this.isEmpty()) {
            this.head = otherList.head;
            this.tail = otherList.tail;
            return;
        }
        this.tail.next = otherList.head;
        this.tail = otherList.tail;
    }

    // ==================================================================
    //  [P] TÌM KIẾM/XÓA PHỨC TẠP (PE SPECIFIC)
    // ==================================================================
    /**
     * Xoa phan tu thu N tinh tu ĐUÔI danh sach. (Two-pointer technique 1 vong
     * lap)
     */
    public void deleteNthFromEnd(int n) {
        if (isEmpty() || n <= 0) {
            return;
        }
        Node dummy = new Node(new Object());
        dummy.next = head;
        Node fast = dummy;
        Node slow = dummy;

        for (int i = 0; i <= n; i++) {
            if (fast == null) {
                return; // N vuot qua do dai danh sach
            }
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        if (slow.next == null) {
            tail = (slow == dummy) ? null : slow;
        }
        head = dummy.next;
    }

    /**
     * Kiem tra danh sach co doi xung hay khong (Palindrome).
     */
    public boolean isPalindrome() {
        if (isEmpty() || head.next == null) {
            return true;
        }
        Object[] arr = toArray();
        int left = 0, right = arr.length - 1;
        while (left < right) {
            if (!arr[left].equals(arr[right])) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // ============================================================================================================================
    //  [L] TÍNH NĂNG TÍCH HỢP TỪ PE_PHUC 5 PRACTICE EXAMS
    // ============================================================================================================================

    /**
     * [L1] Test 01 - Remove every node whose value is equal to the maximum value among all nodes before it.
     */
    public void removeMaxBefore() {
        if (head == null || head.next == null) return;
        int max = (Integer) head.info;
        Node prev = head;
        Node curr = head.next;
        while (curr != null) {
            int val = (Integer) curr.info;
            if (val == max) {
                prev.next = curr.next;
                if (curr == tail) tail = prev;
                curr = prev.next;
            } else {
                if (val > max) max = val;
                prev = curr;
                curr = curr.next;
            }
        }
    }

    /**
     * [L2] Test 02 - Delete every node whose value appears more than once in the original list.
     */
    public void deleteDuplicatesKeepUnique() {
        if (head == null) return;
        java.util.HashMap<Object, Integer> countMap = new java.util.HashMap<>();
        Node p = head;
        while (p != null) {
            countMap.put(p.info, countMap.getOrDefault(p.info, 0) + 1);
            p = p.next;
        }
        
        Node dummy = new Node(null);
        dummy.next = head;
        Node prev = dummy;
        Node curr = head;
        while (curr != null) {
            if (countMap.get(curr.info) > 1) {
                prev.next = curr.next;
                if (curr == tail) tail = prev;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }
        head = dummy.next;
        if (head == null) tail = null;
    }

    /**
     * [L4] Test 04 - Move all even-valued nodes to the end of the list.
     */
    public void moveEvenToEnd() {
        if (head == null || head.next == null) return;
        Node oddDummy = new Node(null);
        Node evenDummy = new Node(null);
        Node oddTail = oddDummy;
        Node evenTail = evenDummy;
        
        Node curr = head;
        while (curr != null) {
            if ((Integer) curr.info % 2 == 0) {
                evenTail.next = curr;
                evenTail = curr;
            } else {
                oddTail.next = curr;
                oddTail = curr;
            }
            curr = curr.next;
        }
        
        evenTail.next = null;
        oddTail.next = evenDummy.next;
        head = oddDummy.next;
        if (evenDummy.next != null) tail = evenTail;
        else tail = oddTail;
    }

    /**
     * [L5] Test 05 - Delete every node whose value is smaller than the average value.
     */
    public void deleteSmallerThanAverage() {
        if (head == null) return;
        double sum = 0;
        int count = 0;
        Node p = head;
        while (p != null) {
            sum += (Integer) p.info;
            count++;
            p = p.next;
        }
        double avg = sum / count;
        
        Node dummy = new Node(null);
        dummy.next = head;
        Node prev = dummy;
        Node curr = head;
        while (curr != null) {
            if ((Integer) curr.info < avg) {
                prev.next = curr.next;
                if (curr == tail) tail = prev;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }
        head = dummy.next;
        if (head == null) tail = null;
    }
}
