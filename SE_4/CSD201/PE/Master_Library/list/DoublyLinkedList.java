package list;

public class DoublyLinkedList {
    DNode head, tail;

    public DoublyLinkedList() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addFirst(Item x) {
        DNode p = new DNode(x, null, head);
        if (isEmpty()) {
            head = tail = p;
        } else {
            head.prev = p;
            head = p;
        }
    }

    public void addLast(Item x) {
        DNode p = new DNode(x, tail, null);
        if (isEmpty()) {
            head = tail = p;
        } else {
            tail.next = p;
            tail = p;
        }
    }

    public void removeFirst() {
        if (isEmpty()) return;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
    }

    public void removeLast() {
        if (isEmpty()) return;
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
    }

    public String printForward() {
        StringBuilder sb = new StringBuilder();
        DNode p = head;
        while (p != null) {
            sb.append("(").append(p.info.name).append(",").append(p.info.price).append(") ");
            p = p.next;
        }
        return sb.toString().trim();
    }

    public String printBackward() {
        StringBuilder sb = new StringBuilder();
        DNode p = tail;
        while (p != null) {
            sb.append("(").append(p.info.name).append(",").append(p.info.price).append(") ");
            p = p.prev;
        }
        return sb.toString().trim();
    }
}
