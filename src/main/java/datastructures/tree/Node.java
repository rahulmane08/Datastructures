package datastructures.tree;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return data == node.data;
    }

    @Override
    public int hashCode() {
        return data;
    }

    @Override
    public String toString() {
        return "Node [data=" + data + "]";
    }
}
