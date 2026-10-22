// ==================================================================================================================================
//  DoublyLinkedList.java — Danh sach lien ket DOI CHIEU (Doubly Linked List) cho CSD201
//  => Dung Object + DNode (co ca prev va next): hoat dong voi moi kieu du lieu.
//  => head: node dau; tail: node cuoi.
// ==================================================================================================================================
//
//  [MUC LUC & SEARCH KEYS]
//    ||____ADD______   -> [B] addFirst(), addLast(), insertAt()
//    ||___DELETE____   -> [C] removeFirst(), removeLast(), deleteAt(), deleteNode()
//    ||___SORT______   -> [E] sort(), insertionSort()
//    ||___SEARCH____   -> [F] getNodeAt(), get(), getFirst(), getLast(), contains(), indexOf()
//    ||___DISPLAY___   -> [D] printForward(), printBackward(), fprintForward(), fprintBackward()
//    ||___REVERSE___   -> [G] reverse()
//
//    [A] Khoi tao & Co ban   : isEmpty(), size(), clear()
//    [B] Them phan tu        : addFirst(), addLast(), insertAt()
//    [C] Xoa phan tu         : removeFirst(), removeLast(), deleteAt(), deleteNode()
//    [D] Duyet in ra String  : printForward(), printBackward(), fprintForward(), fprintBackward()
//    [E] Sap xep (Sort)      : sort(), insertionSort()
//    [F] Truy cap / Tim kiem  : getNodeAt(), get(), getFirst(), getLast(), contains(), indexOf()
//    [G] Dao nguoc           : reverse()
//
// ==================================================================================================================================

public class DoublyLinkedList {
    DNode head, tail;
    private StringBuilder outputBuffer = new StringBuilder();

    // setOutputBuffer - Cho phhep dung chung outputBuffer voi Q1.java de de dang tich hop
    public void setOutputBuffer(StringBuilder sb) {
        this.outputBuffer = sb;
    }

    // Helper output
    private void print(Object o) { outputBuffer.append(o); }
    private void println(Object o) { outputBuffer.append(o).append("\n"); }
    private void println() { outputBuffer.append("\n"); }

    // ==================================================================
    //  [A] KHOI TAO & CO BAN
    // ==================================================================

    /** Tao danh sach rong. */
    public DoublyLinkedList() {
        head = tail = null;
    }

    /** Tra ve true neu danh sach rong. */
    public boolean isEmpty() {
        return head == null;
    }

    /** Tra ve so phan tu trong danh sach. */
    public int size() {
        int count = 0;
        DNode p = head;
        while (p != null) {
            count++;
            p = p.next;
        }
        return count;
    }

    /** Reset danh sach ve rong. */
    public void clear() {
        head = tail = null;
    }

    // ==================================================================
    //  [B] THEM PHAN TU
    // ==================================================================

    /**
     * [B1] Them node vao DAU danh sach.
     * Truoc: head -> [A] <-> [B] <-> [C]
     * Sau:   head -> [X] <-> [A] <-> [B] <-> [C]
     */
    public void addFirst(Object x) {
        DNode p = new DNode(x, null, head); // prev=null, next=head cu
        if (isEmpty()) {
            head = tail = p;
        } else {
            head.prev = p; // Head cu tro nguoc lai node moi
            head = p;
        }
    }

    /**
     * [B2] Them node vao CUOI danh sach.
     * Truoc: [A] <-> [B] <-> [C] <- tail
     * Sau:   [A] <-> [B] <-> [C] <-> [X] <- tail
     */
    public void addLast(Object x) {
        DNode p = new DNode(x, tail, null); // prev=tail cu, next=null
        if (isEmpty()) {
            head = tail = p;
        } else {
            tail.next = p; // Tail cu tro toi node moi
            tail = p;
        }
    }

    /** [B3] Chen node tai vi tri index (0-indexed). */
    public void insertAt(int index, Object x) {
        if (index <= 0 || isEmpty()) {
            addFirst(x);
            return;
        }
        int sz = size();
        if (index >= sz) {
            addLast(x);
            return;
        }
        DNode curr = getNodeAt(index);
        DNode prevNode = curr.prev;
        DNode newNode = new DNode(x, prevNode, curr);
        prevNode.next = newNode;
        curr.prev = newNode;
    }

    // ==================================================================
    //  [C] XOA PHAN TU
    // ==================================================================

    /**
     * [C1] Xoa va tra ve phan tu DAU danh sach.
     * Tra ve null neu rong.
     */
    public Object removeFirst() {
        if (isEmpty()) return null;
        Object data = head.info;
        if (head == tail) {
            head = tail = null;         // Chi con 1 phan tu
        } else {
            head = head.next;
            head.prev = null;           // Head moi khong co cha
        }
        return data;
    }

    /**
     * [C2] Xoa va tra ve phan tu CUOI danh sach.
     * Tra ve null neu rong.
     */
    public Object removeLast() {
        if (isEmpty()) return null;
        Object data = tail.info;
        if (head == tail) {
            head = tail = null;         // Chi con 1 phan tu
        } else {
            tail = tail.prev;
            tail.next = null;           // Tail moi khong co con
        }
        return data;
    }

    /** [C3] Xoa node tai vi tri index (0-indexed). */
    public void deleteAt(int index) {
        if (isEmpty() || index < 0) return;
        if (index == 0) {
            removeFirst();
            return;
        }
        int sz = size();
        if (index >= sz - 1) {
            removeLast();
            return;
        }
        DNode target = getNodeAt(index);
        deleteNode(target);
    }

    /** [C4] Xoa mot node cu the. */
    public void deleteNode(DNode target) {
        if (target == null || isEmpty()) return;
        if (target == head) {
            removeFirst();
            return;
        }
        if (target == tail) {
            removeLast();
            return;
        }
        target.prev.next = target.next;
        target.next.prev = target.prev;
    }

    // ==================================================================
    //  [D] DUYET IN RA STRING
    // ==================================================================

    /**
     * [D1] Duyet XUOI (head -> tail), tra ve String, moi phan tu cach nhau 1 khoang trang.
     * Goi: String s = list.printForward();
     */
    public String printForward() {
        StringBuilder sb = new StringBuilder();
        DNode p = head;
        while (p != null) {
            sb.append(p.info.toString()).append(" ");
            p = p.next;
        }
        return sb.toString().trim();
    }

    /**
     * [D2] Duyet NGUOC (tail -> head), tra ve String, moi phan tu cach nhau 1 khoang trang.
     * Goi: String s = list.printBackward();
     */
    public String printBackward() {
        StringBuilder sb = new StringBuilder();
        DNode p = tail;
        while (p != null) {
            sb.append(p.info.toString()).append(" ");
            p = p.prev;
        }
        return sb.toString().trim();
    }

    /** [D3] Ghi duyet xuoi vao outputBuffer. */
    public void fprintForward() {
        DNode p = head;
        while (p != null) {
            print(p.info.toString() + " ");
            p = p.next;
        }
        println();
    }

    /** [D4] Ghi duyet nguoc vao outputBuffer. */
    public void fprintBackward() {
        DNode p = tail;
        while (p != null) {
            print(p.info.toString() + " ");
            p = p.prev;
        }
        println();
    }

    // ==================================================================
    //  [E] SAP XEP (SORT)
    // ==================================================================

    /** [E1] Sap xep Selection Sort. */
    public void sort(java.util.Comparator comp, boolean ascending) {
        if (isEmpty() || head.next == null) return;
        for (DNode i = head; i.next != null; i = i.next) {
            DNode selected = i;
            for (DNode j = i.next; j != null; j = j.next) {
                boolean betterFound = ascending
                    ? comp.compare(j.info, selected.info) < 0
                    : comp.compare(j.info, selected.info) > 0;
                if (betterFound) selected = j;
            }
            if (selected != i) {
                Object temp = i.info;
                i.info = selected.info;
                selected.info = temp;
            }
        }
    }

    /** [E2] Sap xep bang Insertion Sort (on dinh, O(n^2)). */
    public void insertionSort(java.util.Comparator comp, boolean ascending) {
        if (isEmpty() || head.next == null) return;
        DNode current = head.next;
        while (current != null) {
            Object key = current.info;
            DNode p = current.prev;
            while (p != null) {
                boolean swapNeeded = ascending 
                    ? comp.compare(p.info, key) > 0 
                    : comp.compare(p.info, key) < 0;
                if (!swapNeeded) break;
                p.next.info = p.info;
                p = p.prev;
            }
            if (p == null) head.info = key;
            else p.next.info = key;
            
            current = current.next;
        }
    }

    // ==================================================================
    //  [F] TRUY CAP / TIM KIEM
    // ==================================================================

    /** [F1] Lay Node tai index (0-indexed). */
    public DNode getNodeAt(int index) {
        if (index < 0) return null;
        DNode p = head;
        int i = 0;
        while (p != null) {
            if (i == index) return p;
            p = p.next;
            i++;
        }
        return null;
    }

    /** [F2] Lay data tai index. */
    public Object get(int index) {
        DNode p = getNodeAt(index);
        return p != null ? p.info : null;
    }

    /** [F3] Lay data cua node DAU. */
    public Object getFirst() {
        return isEmpty() ? null : head.info;
    }

    /** [F4] Lay data cua node CUOI. */
    public Object getLast() {
        return isEmpty() ? null : tail.info;
    }

    /** [F5] Kiem tra phan tu co ton tai (equals). */
    public boolean contains(Object x) {
        return indexOf(x) != -1;
    }

    /** [F6] Tim index dau tien cua phan tu (equals), -1 neu khong co. */
    public int indexOf(Object x) {
        if (isEmpty() || x == null) return -1;
        DNode p = head;
        int index = 0;
        while (p != null) {
            if (x.equals(p.info)) return index;
            p = p.next;
            index++;
        }
        return -1;
    }

    // ==================================================================
    //  [G] DAO NGUOC
    // ==================================================================

    /** [G1] Dao nguoc DLL tai cho. */
    public void reverse() {
        if (isEmpty() || head.next == null) return;
        DNode curr = head;
        DNode temp = null;
        while (curr != null) {
            temp = curr.prev;
            curr.prev = curr.next;
            curr.next = temp;
            curr = curr.prev; // curr.prev gio la next cu
        }
        if (temp != null) {
            tail = head;
            head = temp.prev; // temp.prev gio la head moi
        }
    }
}
