package list;

public class DNode {
    public Item info;
    public DNode next, prev;
    
    public DNode(Item info, DNode prev, DNode next) {
        this.info = info;
        this.prev = prev;
        this.next = next;
    }
    
    public DNode(Item info) {
        this(info, null, null);
    }
}
