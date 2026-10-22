import java.util.LinkedList;

// ==================================================================================================================================
//  IntQueue.java — Queue cho kieu int (dung cho thuat toan Graph BFS) trong CSD201
//  => Dung LinkedList<Integer> ben trong — don gian, toc do cao.
//  => Dung noi bo trong Graph.java (BFS).
// ==================================================================================================================================
//
//  [MUC LUC & SEARCH KEYS]
//    ||____ADD______   -> [B] enqueue(), enqueueMany()
//    ||___DELETE____   -> [B] dequeue()
//    ||____PEEK_____   -> [B] front(), peek(), rear()
//    ||___SEARCH____   -> [C] contains()
//    ||___DISPLAY___   -> [C] traverse(), ftraverse()
//
//    [A] Co ban      : isEmpty(), size(), clear()
//    [B] Thao tac Queue : enqueue(), enqueueMany(), dequeue(), front(), peek(), rear()
//    [C] Tien ich    : traverse(), ftraverse(), contains()
//
//  CAU TRUC NHO:
//    Enqueue: them vao CUOI (addLast)   => FIFO: First In, First Out
//    Dequeue: lay tu DAU  (removeFirst)
//
// ==================================================================================================================================

public class IntQueue {
    private LinkedList<Integer> list;
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
    //  [A] CO BAN
    // ==================================================================

    /** Tao Queue rong. */
    public IntQueue() {
        list = new LinkedList<Integer>();
    }

    /** Tra ve true neu Queue rong. */
    public boolean isEmpty() { return list.isEmpty(); }

    /** Tra ve so phan tu trong Queue. */
    public int size() { return list.size(); }

    /** Xoa toan bo Queue. */
    public void clear() { list.clear(); }

    // ==================================================================
    //  [B] THAO TAC QUEUE
    // ==================================================================

    /**
     * [B1] Enqueue — Them int x vao CUOI hang doi.
     * Goi: queue.enqueue(5);
     */
    public void enqueue(int x) {
        list.addLast(x); // Them vao cuoi
    }

    /** [B1b] EnqueueMany — Them nhieu int vao Queue. */
    public void enqueueMany(int[] arr) {
        if (arr == null) return;
        for (int x : arr) enqueue(x);
    }

    /**
     * [B2] Dequeue — Xoa va tra ve int o DAU hang.
     * Tra ve -1 neu rong.
     * Goi: int val = queue.dequeue();
     */
    public int dequeue() {
        if (isEmpty()) return -1;
        return list.removeFirst();
    }

    /**
     * [B3] Front — Xem int o DAU hang ma KHONG xoa.
     * Tra ve -1 neu rong.
     * Goi: int val = queue.front();
     */
    public int front() {
        if (isEmpty()) return -1;
        return list.getFirst();
    }

    /** [B3] Alias cho front(). */
    public int peek() { return front(); }

    /** [B4] Xem int o CUOI hang ma KHONG xoa. */
    public int rear() {
        if (isEmpty()) return -1;
        return list.getLast();
    }

    // ==================================================================
    //  [C] TIEN ICH
    // ==================================================================

    /**
     * [C1] Duyet Queue tu dau den cuoi, tra ve String, moi phan tu cach nhau 1 khoang trang.
     * Goi: String s = queue.traverse();
     */
    public String traverse() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) sb.append(" ");
        }
        return sb.toString();
    }

    /**
     * [C2] Kiem tra Queue co chua int x hay khong.
     * Goi: queue.contains(5)
     */
    public boolean contains(int x) {
        return list.contains(x);
    }

    /** [C3] Ghi toan bo Queue vao outputBuffer, cach nhau 1 khoang trang. */
    public void ftraverse() {
        for (int i = 0; i < list.size(); i++) {
            print(list.get(i));
            if (i < list.size() - 1) print(" ");
        }
        println();
    }
}
