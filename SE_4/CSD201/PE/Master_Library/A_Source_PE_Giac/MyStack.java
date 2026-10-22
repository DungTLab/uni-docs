import java.io.*;
import java.util.Comparator;

// ==================================================================================================================================
//  MyStack.java — Stack (Ngan xep) dung Singly Linked List cho CSD201
//  => Dung Object: hoat dong voi moi kieu du lieu.
//  => KHONG dung System.out — tat ca hien thi deu RETURN String.
//  => top: tro den phan tu tren cung (dinh stack).
// ==================================================================================================================================
//
//  [MUC LUC & SEARCH KEYS]
//    ||____ADD______   -> [B] push(), pushMany()
//    ||___DELETE____   -> [B] pop(), [E] remove()
//    ||____PEEK_____   -> [B] peek(), top()
//    ||___SEARCH____   -> [D] contains(), countOccurrences()
//    ||___DISPLAY___   -> [C] traverse(), ftraverse()
//    ||___REVERSE___   -> [E] reverse()
//
//    [A] Khoi tao & Co ban : isEmpty(), size(), length(), clear()
//    [B] Them / Lay phan tu: push(), pushMany(), pop(), peek(), top()
//    [C] Duyet / Ghi file  : traverse(), ftraverse()
//    [D] Tim kiem          : contains(), countOccurrences()
//    [E] Cac thao tac nang cao: reverse(), remove(), toArray()
//
//  CAU TRUC NHO:
//    Push: them vao DAU (top)       — O(1)
//    Pop : lay tu DAU (top)         — O(1)
//    LIFO: Last In, First Out
//
// ==================================================================================================================================

public class MyStack {
    private Node top;    // Node dau tien = dinh Stack
    private int length;  // So phan tu hien tai
    private StringBuilder outputBuffer = new StringBuilder();

    // setOutputBuffer - Cho phhep dung chung outputBuffer voi Q1.java de de dang tich hop
    public void setOutputBuffer(StringBuilder sb) {
        this.outputBuffer = sb;
    }

    // Helper output
    private void print(Object o) { outputBuffer.append(o); }
    private void println(Object o) { outputBuffer.append(o).append("\n"); }

    // ==================================================================
    //  [A] KHOI TAO & CO BAN
    // ==================================================================

    /** Tao Stack rong. */
    public MyStack() {
        top = null;
        length = 0;
    }

    /** Tra ve true neu Stack rong. */
    public boolean isEmpty() {
        return top == null;
    }

    /** Tra ve so phan tu trong Stack. */
    public int size() { return length; }

    /** Alias cho size(). */
    public int length() { return length; }

    /** Xoa toan bo Stack. */
    public void clear() {
        top = null;
        length = 0;
    }

    // ==================================================================
    //  [B] THEM / LAY PHAN TU
    // ==================================================================

    /**
     * [B1] Push — Them phan tu x len DINH Stack.
     * Goi: stack.push(new Tiger(5, "Sora"))
     */
    public void push(Object x) {
        top = new Node(x, top); // node moi.next = top cu
        length++;
    }

    /** [B1b] PushMany — Them nhieu phan tu vao Stack. */
    public void pushMany(Object[] arr) {
        if (arr == null) return;
        for (Object x : arr) push(x);
    }

    /**
     * [B2] Pop — Xoa va tra ve phan tu tren DINH.
     * Tra ve null neu Stack rong.
     * Goi: Object o = stack.pop();
     */
    public Object pop() {
        if (isEmpty()) return null;
        Object data = top.info;
        top = top.next;
        length--;
        return data;
    }

    /**
     * [B3] Peek — Xem phan tu tren DINH ma KHONG xoa.
     * Tra ve null neu Stack rong.
     * Goi: Object o = stack.peek();
     */
    public Object peek() {
        if (isEmpty()) return null;
        return top.info;
    }

    /** [B3] Alias cho peek(). */
    public Object top() { return peek(); }

    // ==================================================================
    //  [C] DUYET / GHI FILE
    // ==================================================================

    /**
     * [C1] Duyet Stack tu DINH xuong DAY, tra ve String.
     * Moi phan tu tren 1 dong.
     * Goi: String s = stack.traverse();
     */
    public String traverse() {
        StringBuilder sb = new StringBuilder();
        Node p = top;
        while (p != null) {
            sb.append(p.info.toString());
            if (p.next != null) sb.append("\n");
            p = p.next;
        }
        return sb.toString();
    }

    /**
     * [C2] Ghi toan bo Stack vao RandomAccessFile (tu dinh xuong day).
     * Goi: stack.ftraverse(raf);
     */
    public void ftraverse(RandomAccessFile f) throws IOException {
        Node p = top;
        while (p != null) {
            f.writeBytes(p.info.toString() + "\r\n");
            p = p.next;
        }
    }

    /** [C3] Ghi toan bo Stack vao outputBuffer. */
    public void ftraverse() {
        Node p = top;
        while (p != null) {
            println(p.info.toString());
            p = p.next;
        }
    }

    // ==================================================================
    //  [D] TIM KIEM
    // ==================================================================

    /**
     * [D1] Kiem tra Stack co chua phan tu ma toString() chua id hay khong.
     * Dung cho cac bai xoa/tim theo ID (String).
     * Goi: stack.contains("T001")
     */
    public boolean contains(String id) {
        Node p = top;
        while (p != null) {
            if (p.info.toString().contains(id)) return true;
            p = p.next;
        }
        return false;
    }

    /** [D2] Kiem tra Stack co chua phan tu x bang equals() hay khong. */
    public boolean contains(Object x) {
        if (x == null) return false;
        Node p = top;
        while (p != null) {
            if (x.equals(p.info)) return true;
            p = p.next;
        }
        return false;
    }

    /** [D3] Dem so lan xuat hien cua x (equals). */
    public int countOccurrences(Object x) {
        if (x == null) return 0;
        int count = 0;
        Node p = top;
        while (p != null) {
            if (x.equals(p.info)) count++;
            p = p.next;
        }
        return count;
    }

    // ==================================================================
    //  [E] CAC THAO TAC NANG CAO
    // ==================================================================

    /**
     * [E1] Xoa phan tu DAU TIEN co toString() chua id.
     * Dung trong cac bai xoa phan tu theo dieu kien ID.
     * Tra ve true neu xoa thanh cong.
     */
    public boolean remove(String id) {
        if (isEmpty()) return false;
        if (top.info.toString().contains(id)) {
            top = top.next;
            length--;
            return true;
        }
        Node prev = top, curr = top.next;
        while (curr != null) {
            if (curr.info.toString().contains(id)) {
                prev.next = curr.next;
                length--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    /** [E1b] Xoa phan tu DAU TIEN bang x (dung equals). */
    public boolean remove(Object x) {
        if (isEmpty() || x == null) return false;
        if (x.equals(top.info)) {
            top = top.next;
            length--;
            return true;
        }
        Node prev = top, curr = top.next;
        while (curr != null) {
            if (x.equals(curr.info)) {
                prev.next = curr.next;
                length--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    /**
     * [E2] Dao nguoc Stack tai cho (dinh cu tro thanh day, day tro thanh dinh).
     * Goi: stack.reverse();
     */
    public void reverse() {
        if (isEmpty() || top.next == null) return;
        Node prev = null, curr = top;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        top = prev;
    }

    public Object[] toArray() {
        Object[] arr = new Object[length];
        Node p = top;
        for (int i = 0; i < length; i++) {
            arr[i] = p.info;
            p = p.next;
        }
        return arr;
    }

    // ==================================================================
    //  [F] TÍNH TOÁN & THỐNG KÊ (MATH & STATS) - Mặc định kiểu Integer
    // ==================================================================
    
    /** Tinh tong tat ca cac phan tu kieu Integer trong Stack. */
    public int sumInteger() {
        int sum = 0;
        Node p = top;
        while (p != null) {
            if (p.info instanceof Integer) {
                sum += (Integer) p.info;
            }
            p = p.next;
        }
        return sum;
    }

    /** Tinh trung binh cong cac phan tu kieu Integer. */
    public double averageInteger() {
        int count = 0;
        int sum = 0;
        Node p = top;
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
    //  [G] TÌM KIẾM AN TOÀN (SAFE SEARCHING - DUNG TEMP STACK)
    // ==================================================================

    /** Tim Node co gia tri Integer max bang cach Pop/Push de dam bao LIFO. */
    public Node findMaxInteger() {
        if (isEmpty()) return null;
        MyStack tempStack = new MyStack();
        Node maxNode = null;
        
        while (!isEmpty()) {
            Object curr = pop();
            if (curr instanceof Integer) {
                if (maxNode == null || (Integer) curr > (Integer) maxNode.info) {
                    maxNode = new Node(curr);
                }
            }
            tempStack.push(curr);
        }
        
        // Phuc hoi Stack
        while (!tempStack.isEmpty()) {
            push(tempStack.pop());
        }
        return maxNode;
    }

    /** Tim Node co gia tri Integer min. */
    public Node findMinInteger() {
        if (isEmpty()) return null;
        MyStack tempStack = new MyStack();
        Node minNode = null;
        
        while (!isEmpty()) {
            Object curr = pop();
            if (curr instanceof Integer) {
                if (minNode == null || (Integer) curr < (Integer) minNode.info) {
                    minNode = new Node(curr);
                }
            }
            tempStack.push(curr);
        }
        
        // Phuc hoi Stack
        while (!tempStack.isEmpty()) {
            push(tempStack.pop());
        }
        return minNode;
    }

    // ==================================================================
    //  [H] XÓA/SỬA CÓ ĐIỀU KIỆN & THAO TÁC NÂNG CAO (PE SPECIFIC)
    // ==================================================================

    /** Dao nguoc K phan tu dau tien (tinh tu dinh - Top). */
    public void reverseTopK(int k) {
        if (isEmpty() || k <= 1 || k > length) return;
        Object[] temp = new Object[k];
        for (int i = 0; i < k; i++) {
            temp[i] = pop();
        }
        for (int i = 0; i < k; i++) {
            push(temp[i]);
        }
    }

    /** Xoa TAT CA phan tu co gia tri > threshold (Dung Stack phu). */
    public void removeAllCondition(int thresholdInteger) {
        MyStack tempStack = new MyStack();
        while (!isEmpty()) {
            Object curr = pop();
            if (curr instanceof Integer) {
                if ((Integer) curr <= thresholdInteger) {
                    tempStack.push(curr);
                }
            } else {
                tempStack.push(curr);
            }
        }
        // Phuc hoi Stack (vi tempStack luu nguoc nen push lai se ra dung chieu cu)
        while (!tempStack.isEmpty()) {
            push(tempStack.pop());
        }
    }

    /** Rut ra phan tu nam o do sau thu K (tinh tu Top, top = 1). */
    public Object popAt(int k) {
        if (k <= 0 || k > length) return null;
        MyStack tempStack = new MyStack();
        Object target = null;
        
        // Pop k-1 phan tu vao tempStack
        for (int i = 1; i < k; i++) {
            tempStack.push(pop());
        }
        
        // Phan tu thu k
        target = pop();
        
        // Phuc hoi
        while (!tempStack.isEmpty()) {
            push(tempStack.pop());
        }
        return target;
    }

    /** Sap xep Stack theo gia tri Integer tang dan (Max nam o dinh). */
    public void sortAscendingInteger() {
        if (isEmpty() || top.next == null) return;
        Object[] arr = toArray();
        java.util.Arrays.sort(arr, new java.util.Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Integer && o2 instanceof Integer) {
                    return Integer.compare((Integer) o1, (Integer) o2);
                }
                return 0; // Bo qua neu khong phai Integer
            }
        });
        clear();
        // push vao mang da sap xep theo dung thu tu tang dan thi max se len dinh
        for (Object item : arr) {
            push(item);
        }
    }
}
