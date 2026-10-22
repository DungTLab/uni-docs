package q2;
/**
 * BSTNode — Node cua Binary Search Tree.
 *
 * Dung cho BSTree.java. Moi node gom:
 *   info  : du lieu chua (Object — Tiger, Cape, Order, ...)
 *   left  : con TRAI
 *   right : con PHAI
 *
 * CACH SU DUNG:
 *   BSTNode node = new BSTNode(myObject);
 *   node.left  = new BSTNode(leftObject);
 *   node.right = new BSTNode(rightObject);
 */
class BSTNode {
    Object info;
    BSTNode left, right;

    /** Tao node rong. */
    BSTNode() {
        info = null;
        left = right = null;
    }

    /** Tao node voi gia tri x, khong co con. */
    BSTNode(Object x) {
        info = x;
        left = right = null;
    }

    /** Tao node voi gia tri x va 2 con cho truoc. */
    BSTNode(Object x, BSTNode left, BSTNode right) {
        this.info = x;
        this.left = left;
        this.right = right;
    }
}
