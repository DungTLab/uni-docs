import java.util.LinkedList;

// ==================================================================================================================================
//  IntStack.java — Stack cho kieu int (dung cho thuat toan Graph DFS/BFS) trong CSD201
//  => Dung LinkedList<Integer> ben trong — don gian, toc do cao.
//  => Dung noi bo trong Graph.java (DFS iterative).
// ==================================================================================================================================
//
//  [MUC LUC & SEARCH KEYS]
//    ||____ADD______   -> [B] push(), pushMany()
//    ||___DELETE____   -> [B] pop()
//    ||____PEEK_____   -> [B] peek(), top(), bottom()
//    ||___SEARCH____   -> [C] contains()
//    ||___DISPLAY___   -> [C] traverse(), ftraverse()
//
//    [A] Co ban  : isEmpty(), size(), clear()
//    [B] Thao tac Stack : push(), pushMany(), pop(), peek(), top(), bottom()
//    [C] Tien ich : traverse(), ftraverse(), contains()
//
//  CAU TRUC NHO:
//    Push: them vao DAU (addFirst)    => index 0 la dinh Stack
//    Pop : lay tu DAU (removeFirst)
//    LIFO: Last In, First Out
//
// ==================================================================================================================================

public class IntStack {
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

    /** Tao Stack rong. */
    public IntStack() {
        list = new LinkedList<Integer>();
    }

    /** Tra ve true neu Stack rong. */
    public boolean isEmpty() { return list.isEmpty(); }

    /** Tra ve so phan tu trong Stack. */
    public int size() { return list.size(); }

    /** Xoa toan bo Stack. */
    public void clear() { list.clear(); }

    // ==================================================================
    //  [B] THAO TAC STACK
    // ==================================================================

    /**
     * [B1] Push — Them int x len DINH Stack.
     * Goi: stack.push(5);
     */
    public void push(int x) {
        list.addFirst(x); // Index 0 = dinh Stack
    }

    /** [B1b] PushMany — Them nhieu int vao Stack. */
    public void pushMany(int[] arr) {
        if (arr == null) return;
        for (int x : arr) push(x);
    }

    /**
     * [B2] Pop — Xoa va tra ve int o DINH Stack.
     * Tra ve -1 neu rong.
     * Goi: int val = stack.pop();
     */
    public int pop() {
        if (isEmpty()) return -1;
        return list.removeFirst();
    }

    /**
     * [B3] Peek — Xem int o DINH Stack ma KHONG xoa.
     * Tra ve -1 neu rong.
     * Goi: int val = stack.peek();
     */
    public int peek() {
        if (isEmpty()) return -1;
        return list.getFirst();
    }

    /** [B3] Alias cho peek(). */
    public int top() { return peek(); }

    /** [B4] Xem int o DAY Stack. */
    public int bottom() {
        if (isEmpty()) return -1;
        return list.getLast();
    }

    // ==================================================================
    //  [C] TIEN ICH
    // ==================================================================

    /**
     * [C1] Duyet Stack tu dinh xuong day, tra ve String, moi phan tu cach nhau 1 khoang trang.
     * Goi: String s = stack.traverse();
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
     * [C2] Kiem tra Stack co chua int x hay khong.
     * Goi: stack.contains(5)
     */
    public boolean contains(int x) {
        return list.contains(x);
    }

    /** [C3] Ghi toan bo Stack vao outputBuffer, cach nhau 1 khoang trang. */
    public void ftraverse() {
        for (int i = 0; i < list.size(); i++) {
            print(list.get(i));
            if (i < list.size() - 1) print(" ");
        }
        println();
    }
}
