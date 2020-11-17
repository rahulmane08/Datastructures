package tree;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree implements Cloneable {
    public Node root;

    public BinaryTree() {
        super();
    }


    /**
     * Tree BFS and add node when either left or right is empty of current node and exit BFS.
     *
     * @param data
     */
    public void insert(int data) {
        Node newNode = new Node(null, null, data);
        if (root == null) {
            root = newNode;
            return;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            if (curr.left != null) {
                queue.offer(curr.left);
            } else {
                curr.left = newNode;
                return;
            }

            if (curr.right != null) {
                queue.offer(curr.right);
            } else {
                curr.right = newNode;
                return;
            }
        }
    }

    /**
     * 1. deepestNode will store the current node of BFS.
     * 2. Do BFS and locate the nodeToDelete, checking node.data == data
     * 3. Complete BFS and deepestNode points to rightmost node of last level.
     * 4. Copy data of deepestNode into nodeToDelete, nullify deepestNode.
     *
     * @param data
     */
    public void delete(int data) {
        if (root == null)
            return;
        Node nodeToDelete = null, deepestNode = null, deepestParent = null;
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            deepestNode = queue.poll();
            if (nodeToDelete == null && deepestNode.data == data)
                nodeToDelete = deepestNode;
            if (deepestNode.left != null) {
                deepestParent = deepestNode;
                queue.offer(deepestNode.left);
            }
            if (deepestNode.right != null) {
                deepestParent = deepestNode;
                queue.offer(deepestNode.right);
            }
        }
        if (nodeToDelete == null)
            return; //the node with the data to delete is not found in tree
        System.out.println("node to delete = " + nodeToDelete);
        System.out.println("deepest node = " + deepestNode);
        nodeToDelete.data = deepestNode.data;
        if (deepestParent.left == deepestNode) {
            deepestParent.left = null;
        } else {
            deepestParent.right = null;
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        BinaryTree clone = new BinaryTree();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(this.root);
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            clone.insert(curr.data);
            if (curr.left != null) queue.offer(curr.left);
            if (curr.right != null) queue.offer(curr.right);
        }
        return clone;
    }
}
