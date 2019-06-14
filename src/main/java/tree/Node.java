package tree;

public class Node {
    public Node left;
    public Node right;
    public Node nextSibling;
    public int data;
    int height;

    public Node(int data) {
        super();
        this.data = data;
    }

    public Node(Node left, Node right, int data) {
        super();
        this.left = left;
        this.right = right;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node [data=" + data + "]";
    }
}
