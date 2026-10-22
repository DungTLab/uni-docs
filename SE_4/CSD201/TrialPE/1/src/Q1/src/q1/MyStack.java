package q1;

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
//    ||____PEEK_____   -> [B] peek() / top()
//    ||___SEARCH____   -> [D] contains()
//    ||___DISPLAY___   -> [C] traverse(), ftraverse()
//    ||___REVERSE___   -> [E] reverse()
//
//    [A] Khoi tao & Co ban : isEmpty(), size(), clear()
//    [B] Them / Lay phan tu: push(), pushMany(), pop(), peek() / top()
//    [C] Duyet / Ghi file  : traverse(), ftraverse()
//    [D] Tim kiem          : contains()
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
        int count = 0;
        while (p != null) {
            sb.append(p.info.toString());
            count++;
            if (p.next != null) sb.append(",");
            p = p.next;
        }
        if(count==0){
            sb.append("EMPTY");
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

    /**
     * [E3] Chuyen Stack thanh mang Object[] (phan tu dinh o index 0).
     * Goi: Object[] arr = stack.toArray();
     */
    public Object[] toArray() {
        Object[] arr = new Object[length];
        Node p = top;
        for (int i = 0; i < length; i++) {
            arr[i] = p.info;
            p = p.next;
        }
        return arr;
    }
}
