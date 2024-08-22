package datastructures.tree;

public class BinarySearchTree extends BinaryTree {

    public BinarySearchTree(Node root) {
        super();
        this.root = root;
    }


    public BinarySearchTree() {
        super();
    }

    public BinarySearchTree(int[] sortedArray) {
        root = BSTUtils.createBalancedBST(sortedArray);
    }

    public void insert(int data) {
        if (this.root == null) {
            this.root = new Node(data);
            return;
        }
        insert(this.root, data);
    }

    /**
     * Time Complexity:
     *  T(n) = T(n/2) + 1 , log(1) = 0, k = 0, p = 0 -> Case 2.1 = log(n)
     *
     * @param root
     * @param data
     * @return
     */
    private Node insert(Node root, int data) {
        if (root == null) {
            return new Node(null, null, data);
        }

        if (root.data > data)
            root.left = insert(root.left, data);
        else if (root.data < data)
            root.right = insert(root.right, data);

        return root;
    }

    public Node min() {
        return min(this.root);
    }

    // O(log(n))
    public Node min(Node root) {
        Node node;
        for (node = root; node != null && node.left != null; node = node.left) ;
        return node;
    }

    public Node max() {
        return max(this.root);
    }

    // O(log(n))
    public Node max(Node root) {
        Node node;
        for (node = root; node != null && node.left != null; node = node.left) ;
        return node;
    }

    // log(n)
    public Node search(int data) {
        return search(this.root, data);
    }

    private Node search(Node root, int data) {
        if (root == null)
            return null;
        if (root.data == data)
            return root;
        else if (data < root.data)
            return search(root.left, data);
        else
            return search(root.right, data);

    }

    public void delete(int data) {
        this.delete(root, data);
    }

    /**
     * @param root
     * @param data
     * @return
     */
    private Node delete(Node root, int data) {
        if (root == null)
            return null;
        if (root.data == data)
            return root;
        Node nodeToDelete = null;
        if (data < root.data) {
            nodeToDelete = delete(root.left, data);
            root.left = nodeToDelete;
        } else if (root.data < data) {
            nodeToDelete = delete(root.right, data);
            root.right = nodeToDelete;
        }

        if (nodeToDelete == null)
            return null;

        if (TreeUtils.isLeaf(nodeToDelete))
            nodeToDelete = null;
        else if (nodeToDelete.left != null && nodeToDelete.right != null) {
            Node successor = min(nodeToDelete.right);
            root.data = successor.data;
            return delete(nodeToDelete.right, successor.data);
        } else if (nodeToDelete.left != null) {
            root.left = nodeToDelete.left;
            nodeToDelete.left = null;
        } else if (nodeToDelete.right != null) {
            root.right = nodeToDelete.right;
            nodeToDelete.right = null;
        }

        return root;
    }
}
