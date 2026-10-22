/**
 * Node — Node cua Singly Linked List (danh sach lien ket don).
 *
 * Dung cho: MyList, MyStack, MyQueue, CircularLinkedList.
 * info  : du lieu chua (Object — Tiger, Cape, Order, Drum, ...)
 * next  : tro den node ke tiep
 *
 * CACH SU DUNG:
 *   Node p = new Node(myObject);       // tao node, next = null
 *   Node p = new Node(myObject, head); // tao node, next = head (addFirst)
 */
class Node {
    Object info;
    Node next;

    /** Tao node rong. */
    Node() {
    }

    /** Tao node voi gia tri x va con tro next = p. */
    Node(Object x, Node p) {
        info = x;
        next = p;
    }

    /** Tao node voi gia tri x, next = null. */
    Node(Object x) {
        this(x, null);
    }
}
