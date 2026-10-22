/**
 * DNode — Node cua Doubly Linked List (danh sach lien ket doi).
 *
 * Dung cho: DoublyLinkedList.java.
 * info  : du lieu chua (Object)
 * prev  : tro den node TRUOC DO
 * next  : tro den node KE TIEP
 *
 * CACH SU DUNG:
 *   DNode p = new DNode(myObject);               // prev=null, next=null
 *   DNode p = new DNode(myObject, prevNode, nextNode);
 */
class DNode {
    Object info;
    DNode next, prev;

    /** Tao node voi gia tri x, prev = prevNode, next = nextNode. */
    DNode(Object info, DNode prev, DNode next) {
        this.info = info;
        this.prev = prev;
        this.next = next;
    }

    /** Tao node voi gia tri x, prev = null, next = null. */
    DNode(Object info) {
        this(info, null, null);
    }
}
