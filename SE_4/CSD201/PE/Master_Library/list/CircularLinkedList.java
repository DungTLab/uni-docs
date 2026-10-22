package list;

public class CircularLinkedList {
    Node head, tail;

    public CircularLinkedList() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addFirst(Item x) {
        Node p = new Node(x);
        if (isEmpty()) {
            head = tail = p;
            p.next = head; // circular
        } else {
            p.next = head;
            head = p;
            tail.next = head;
        }
    }

    public void addLast(Item x) {
        Node p = new Node(x);
        if (isEmpty()) {
            head = tail = p;
            p.next = head; // circular
        } else {
            tail.next = p;
            tail = p;
            tail.next = head; // link tail back to head
        }
    }

    public void removeFirst() {
        if (isEmpty()) return;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            tail.next = head;
        }
    }

    public void rotate() {
        if (isEmpty() || head == tail) return;
        tail = head;
        head = head.next;
    }
}
