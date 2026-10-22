package list;

public class Node {
    Item info;
    Node next;

    public Node() {
    }

    public Node(Item info, Node next) {
        this.info = info;
        this.next = next;
    }

    public Node(Item info) {
        this.info = info;
        this.next = null;
    }
}
