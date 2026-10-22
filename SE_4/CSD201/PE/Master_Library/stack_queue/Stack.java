package stack_queue;

import java.io.RandomAccessFile;

public class Stack {
    Node top;

    public Stack() {
        top = null;
    }

    public boolean isEmpty() {
        return (top == null);
    }

    public void clear() {
        top = null;
    }

    public void push(Item x) {
        Node p = new Node(x);
        if (isEmpty()) {
            top = p;
        } else {
            p.next = top;
            top = p;
        }
    }

    public Item pop() {
        if (isEmpty()) return null;
        Item x = top.info;
        top = top.next;
        return x;
    }

    public Item top() {
        if (isEmpty()) return null;
        return top.info;
    }

    public void ftraverse(RandomAccessFile f) throws Exception {
        Node p = top;
        while (p != null) {
            f.writeBytes(p.info.toString() + " ");
            p = p.next;
        }
        f.writeBytes("\r\n");
    }

    // ==========================================
    // DUYET TRA VE STRING KHI KHONG IN RA MAN HINH
    // ==========================================
    public String traverseToString() {
        StringBuilder sb = new StringBuilder();
        Node p = top;
        while (p != null) {
            sb.append("(").append(p.info.name).append(",").append(p.info.price).append(") ");
            p = p.next;
        }
        return sb.toString().trim();
    }
}
