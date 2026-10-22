// ==================================================================================================================================
//  CircularLinkedList.java — Danh sach lien ket VONG (Circular Linked List) cho CSD201
//  => Dung Object: hoat dong voi moi kieu du lieu.
//  => head: node dau; tail: node cuoi — tail.next luon tro lai head.
// ==================================================================================================================================
//
//  [MUC LUC & SEARCH KEYS]
//    ||____ADD______   -> [B] addFirst(), addLast()
//    ||___DELETE____   -> [C] removeFirst(), removeLast(), deleteAt()
//    ||___SEARCH____   -> [D] contains(), get()
//    ||___DISPLAY___   -> [E] traverse(), ftraverse()
//    ||___ROTATE____   -> [F] rotate()
//
//    [A] Khoi tao & Co ban : isEmpty(), size(), clear()
//    [B] Them phan tu      : addFirst(), addLast()
//    [C] Xoa phan tu       : removeFirst(), removeLast(), deleteAt()
//    [D] Tim kiem          : contains(), get()
//    [E] Duyet in ra       : traverse(), ftraverse()
//    [F] Xoay vong         : rotate()
//
// ==================================================================================================================================

public class CircularLinkedList {
    Node head, tail;
    // Bat bien vong: tail.next == head (khi khong rong)
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
    public CircularLinkedList() {
        head = tail = null;
    }

    /** Tra ve true neu danh sach rong. */
    public boolean isEmpty() {
        return head == null;
    }

    /** Tra ve so phan tu trong danh sach vong. */
    public int size() {
        if (isEmpty()) return 0;
        int count = 0;
        Node p = head;
        do {
            count++;
            p = p.next;
        } while (p != head);
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
     * Sau khi them: head = node moi, tail.next tro ve head moi.
     */
    public void addFirst(Object x) {
        Node p = new Node(x);
        if (isEmpty()) {
            head = tail = p;
            p.next = head; // Vong tron voi chinh no
        } else {
            p.next = head;
            head = p;
            tail.next = head; // Duy tri vong: tail -> head moi
        }
    }

    /**
     * [B2] Them node vao CUOI danh sach.
     * Sau khi them: tail = node moi, tail.next tro ve head.
     */
    public void addLast(Object x) {
        Node p = new Node(x);
        if (isEmpty()) {
            head = tail = p;
            p.next = head; // Vong tron voi chinh no
        } else {
            tail.next = p;
            tail = p;
            tail.next = head; // Duy tri vong: tail -> head
        }
    }

    // ==================================================================
    //  [C] XOA PHAN TU
    // ==================================================================

    /**
     * [C1] Xoa va tra ve phan tu DAU danh sach.
     * Tra ve null neu danh sach rong.
     */
    public Object removeFirst() {
        if (isEmpty()) return null;
        Object data = head.info;
        if (head == tail) {
            // Chi con 1 phan tu
            head = tail = null;
        } else {
            head = head.next;
            tail.next = head; // Duy tri vong sau khi xoa
        }
        return data;
    }

    /** [C2] Xoa va tra ve phan tu CUOI danh sach. */
    public Object removeLast() {
        if (isEmpty()) return null;
        Object data = tail.info;
        if (head == tail) {
            head = tail = null;
        } else {
            Node p = head;
            while (p.next != tail) {
                p = p.next;
            }
            tail = p;
            tail.next = head; // Duy tri vong
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
        Node prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }
        prev.next = prev.next.next;
    }

    // ==================================================================
    //  [D] TIM KIEM
    // ==================================================================

    /** [D1] Kiem tra phan tu co ton tai (bang equals). */
    public boolean contains(Object x) {
        if (isEmpty() || x == null) return false;
        Node p = head;
        do {
            if (x.equals(p.info)) return true;
            p = p.next;
        } while (p != head);
        return false;
    }

    /** [D2] Lay data tai index (0-indexed). */
    public Object get(int index) {
        if (isEmpty() || index < 0) return null;
        Node p = head;
        int i = 0;
        do {
            if (i == index) return p.info;
            p = p.next;
            i++;
        } while (p != head);
        return null;
    }

    // ==================================================================
    //  [E] DUYET / IN RA STRING
    // ==================================================================

    /**
     * [E1] Duyet toan bo danh sach, tra ve String.
     * Chi duyet dung 1 vong (tranh loop vo han khi la vong).
     */
    public String traverse() {
        if (isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        Node p = head;
        do {
            sb.append(p.info.toString());
            p = p.next;
            if (p != head) sb.append("\n");
        } while (p != head);
        return sb.toString();
    }

    /** [E2] Ghi vao outputBuffer. */
    public void ftraverse() {
        if (isEmpty()) return;
        Node p = head;
        do {
            println(p.info.toString());
            p = p.next;
        } while (p != head);
    }

    // ==================================================================
    //  [F] XOAY VONG (ROTATE)
    // ==================================================================

    /**
     * [F1] Xoay danh sach 1 buoc: head cu tro thanh tail, head moi la head.next cu.
     *
     * Truoc: head -> A -> B -> C -> (head)
     * Sau:         head -> B -> C -> A -> (head)
     *
     * Dung trong bai Round Robin scheduling hoac xoay vong.
     */
    public void rotate() {
        if (isEmpty() || head == tail) return;
        tail = head;       // Head cu tro thanh tail moi
        head = head.next;  // Head moi la phan tu thu 2
        // tail.next = head da duoc dam bao vi tail la head cu ma head cu.next = head moi
    }
}
