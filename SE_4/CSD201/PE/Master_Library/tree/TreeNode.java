package tree;

public class TreeNode {
    Item info;
    TreeNode left, right;

    public TreeNode() {
    }

    public TreeNode(Item info) {
        this.info = info;
        this.left = null;
        this.right = null;
    }
}
