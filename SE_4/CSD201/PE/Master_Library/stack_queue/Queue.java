package stack_queue;

import java.io.RandomAccessFile;

public class Queue {
    Node front, rear;

    public Queue() {
        front = rear = null;
    }

    public boolean isEmpty() {
        return (front == null);
    }

    public void clear() {
        front = rear = null;
    }

    public void enqueue(Item x) {
        Node p = new Node(x);
        if (isEmpty()) {
            front = rear = p;
        } else {
            rear.next = p;
            rear = p;
        }
    }

    public Item dequeue() {
        if (isEmpty()) return null;
        Item x = front.info;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return x;
    }

    public Item front() {
        if (isEmpty()) return null;
        return front.info;
    }

    public void ftraverse(RandomAccessFile f) throws Exception {
        Node p = front;
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
        Node p = front;
        while (p != null) {
            sb.append("(").append(p.info.name).append(",").append(p.info.price).append(") ");
            p = p.next;
        }
        return sb.toString().trim();
    }
}
