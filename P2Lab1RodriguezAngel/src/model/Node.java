package model;

public class Node {
    protected Object data;
    protected Node left;
    protected Node right;

    public Node(Object value) {
        data = value;
        left = right = null;
    }

    public Node(Node leftBranch, Object value, Node rightBranch) {
        this.data = value;
        this.left = leftBranch;
        this.right = rightBranch;
    }

    // Access operations
    public Object nodeValue() {
        return data;
    }

    public Node leftSubtree() {
        return left;
    }

    public Node rightSubtree() {
        return right;
    }

    public void setNewValue(Object d) {
        data = d;
    }

    public void setLeftBranch(Node n) {
        left = n;
    }

    public void setRightBranch(Node n) {
        right = n;
    }
}
