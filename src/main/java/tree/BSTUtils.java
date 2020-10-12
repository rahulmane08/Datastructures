package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import array.ArrayUtils;
import interfaces.Hard;
import interfaces.Important;
import interfaces.Medium;
import tree.TreeUtils.OrderedArrays;

public class BSTUtils {

    public static Node lca(Node root, int elem1, int elem2) {
        if (root == null)
            return null;
        if (root.data == elem1 || root.data == elem2)
            return root;
        if (elem1 < root.data && elem2 < root.data)
            return lca(root.left, elem1, elem2);
        if (elem1 > root.data && elem2 > root.data)
            return lca(root.right, elem1, elem2);
        return root;
    }

    /**
     * this is a wrong algo
     *
     * @param root
     * @return
     */
    public static boolean isBST_WrongAlgo(Node root) {
        if (root == null)
            return true;
        if (root.left != null && root.left.data > root.data)
            return false;
        if (root.right != null && root.right.data < root.data)
            return false;
        return ((isBST_WrongAlgo(root.left)) && isBST_WrongAlgo(root.right));
    }

    @Important
    public static boolean isBST(Node root) {
        return isBSTUtils(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isBSTUtils(Node root, int min, int max) {
        if (root == null)
            return true;
        int left = min;
        int right = max;
        if (root.left != null)
            left = root.left.data;
        if (root.right != null)
            right = root.right.data;

        return (left < root.data && right > root.data)
                && (isBSTUtils(root.left, min, root.data)
                && (isBSTUtils(root.right, root.data, max)));
    }

    public static Node min(Node root) {
        if (root == null)
            return null;
        while (root.left != null)
            root = root.left;
        return root;
    }

    public static Node max(Node root) {
        if (root == null)
            return null;
        while (root.right != null)
            root = root.right;
        return root;
    }

    public static Node maxRecursive(Node root) {
        if (root == null)
            return null;
        if (root.right == null)
            return root;
        return maxRecursive(root.right);
    }

    public static Node minRecursive(Node root) {
        if (root == null)
            return null;
        if (root.left == null)
            return root;
        return minRecursive(root.left);
    }

    // Balanced BST
    static public Node createBalancedBST(int[] arr) {
        if (arr == null)
            return null;
        Arrays.sort(arr);
        return createBalancedBST(arr, 0, arr.length - 1);
    }

    static private Node createBalancedBST(int[] sortedArr, int left, int right) {
        if (left > right)
            return null;
        int mid = (left + right) / 2;
        Node root = new Node(sortedArr[mid]);
        root.left = createBalancedBST(sortedArr, left, mid - 1);
        root.right = createBalancedBST(sortedArr, mid + 1, right);
        return root;
    }

    // Tree to different linked lists
    static public Node convertToSortedDLL(Node root) {
        if (root == null)
            return null;

        Node curr = root, prev = null, head = null;
        Stack<Node> stack = new Stack<>();
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                if (head == null) {
                    head = curr;
                }
                curr.left = prev;
                if (curr.left != null) {
                    curr.left.right = curr;
                }
                prev = curr;
                curr = curr.right;
            }
        }
        return head;
    }

    static public Node convertToSortedCLL(Node root) {
        if (root == null)
            return null;

        Node curr = root, prev = null, head = null;
        Stack<Node> stack = new Stack<>();
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                if (head == null) {
                    head = curr;
                }
                curr.left = prev;
                if (curr.left != null) {
                    curr.left.right = curr;
                }
                prev = curr;
                curr = curr.right;
            }
        }
        prev.right = head;
        head.left = prev;
        return head;
    }

    static public void printRange(Node root, int k1, int k2, boolean inclusive) {
        if (root == null)
            return;
        printRange(root.left, k1, k2, inclusive);
        boolean check = inclusive ?
                (k1 <= root.data && root.data <= k2) : (k1 < root.data && root.data < k2);
        if (check)
            System.out.println(root.data);
        printRange(root.right, k1, k2, inclusive);
    }

    /**
     *           9
     *        /    \
     *      5       12
     *    /  \    /  \
     *   3    6  11   14
     * Ceil (10): 11
     * Ceil (3): 3
     * Ceil (5): 5
     * Ceil (4): 5
     * Ceil (10): 11
     * Ceil (3): 3
     * Ceil (1): 3
     * Ceil (12): 12
     * Ceil (11): 11
     * Ceil (13): 14
     * Ceil (99): 2147483647
     * Ceil (10): 11
     *
     * @param root
     * @param data
     * @return
     */
    @Important
    public static Integer ceil(Node root, int data) {
        Node ceilNode = inorderSuccessor(root, data);
        if (ceilNode != null) {
            return ceilNode.data;
        }
        return null;
    }

    /**
     * 9
     * /     \
     * 5       12
     * /  \    /  \
     * 3    6  11   14
     * Floor (10): 9
     * Floor (3): 3
     * Floor (5): 5
     * Floor (4): 3
     * Floor (10): 9
     * Floor (3): 3
     * Floor (1): NULL
     * Floor (12): 12
     * Floor (11): 11
     * Floor (13): 13
     * Floor (99): 14
     * Floor (10): 9
     *
     * @param root
     * @param data
     * @return
     */
    @Important
    public static Integer floor(Node root, int data) {
        Node ceilNode = inorderPredecessor(root, data);
        if (ceilNode != null) {
            return ceilNode.data;
        }
        return null;
    }

    /**
     * Stack sortability algo:
     * https://en.wikipedia.org/wiki/Stack-sortable_permutation
     *
     * @param preorder
     * @return
     */
    @Important
    @Hard
    public static boolean checkIfPreorderIsBST(int[] preorder) {
        Stack<Integer> s = new Stack<Integer>();
        int root = Integer.MIN_VALUE;
        for (int i = 0; i < preorder.length; i++) {
            if (preorder[i] <= root)
                return false;
            while (!s.isEmpty() && preorder[i] > s.peek())
                root = s.pop();
            s.push(preorder[i]);
        }

        return true;
    }

    /**
     * convert to DLL and use start pair finding algo using 2 pointers
     *
     * @param root
     * @param n
     */
    public static void printNodeSumEqualsN(Node root, int n) {
        if (root == null)
            return;
        Node first = convertToSortedDLL(root);
        Node last = first;
        while (last.right != null)
            last = last.right;
        while (first != last) {
            if (first.data + last.data == n) {
                System.out.println("Pair found (" + first.data + "," + last.data + ")");
                first = first.right;
                if (first == last)
                    break;
                last = last.left;
            } else if (first.data + last.data > n)
                last = last.left;
            else
                first = first.right;
        }
    }

    /**
     * get inorder[] from the BT
     * sort the inorder[]
     * fill the BT in inorder using the elements from inorder[]
     *
     * @param root
     */
    static public void toBSTofSameStructure(Node root) {
        if (root == null)
            return;
        int[] inorderArr = OrderedArrays.getInstance().toInorderArray(root);
        Arrays.sort(inorderArr);
        OrderedArrays.getInstance().fillTreeWithInorderArr(root, inorderArr);
    }

    public static List<Integer> getCommonNodes(Node root1, Node root2) {
        List<Integer> commonNodes = new ArrayList<>();
        computeCommonNodes(root1, root2, commonNodes);
        return commonNodes;
    }
    private static void computeCommonNodes(Node root1, Node root2, List<Integer> commonNodes) {
        if (root1 == null || root2 == null)
            return;
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        Node curr1 = root1;
        Node curr2 = root2;
        while ((curr1 != null || !stack1.isEmpty())
                && (curr2 != null || !stack2.isEmpty())) {
            if (curr1 != null) {
                stack1.push(curr1);
                curr1 = curr1.left;
            } else if (curr2 != null) {
                stack2.push(curr2);
                curr2 = curr2.left;
            } else {
                curr1 = stack1.pop();
                curr2 = stack2.pop();
                if (curr1.data < curr2.data) {
                    stack2.push(curr2);
                    curr2 = null;
                    curr1 = curr1.right;
                } else if (curr2.data < curr1.data) {
                    stack1.push(curr1);
                    curr1 = null;
                    curr2 = curr2.right;
                } else {
                    commonNodes.add(curr1.data);
                    curr1 = curr1.right;
                    curr2 = curr2.right;
                }
            }
        }
        System.out.println();
    }

    static public void printMergeBSTs(Node root1, Node root2) {
        if (root1 == null && root2 == null)
            return;
        Stack<Node> stack1 = new Stack<>(), stack2 = new Stack<>();
        Node curr1 = root1, curr2 = root2;
        while ((curr1 != null || !stack1.isEmpty()) && (curr2 != null || !stack2.isEmpty())) {
            if (curr1 != null) {
                stack1.push(curr1);
                curr1 = curr1.left;
            } else if (curr2 != null) {
                stack2.push(curr2);
                curr2 = curr2.left;
            } else {
                curr1 = stack1.pop();
                curr2 = stack2.pop();
                if (curr1.data < curr2.data) {
                    System.out.print(curr1.data + " ");
                    stack2.push(curr2);
                    curr2 = null;
                    curr1 = curr1.right;
                } else if (curr2.data < curr1.data) {
                    System.out.print(curr2.data + " ");
                    stack1.push(curr1);
                    curr1 = null;
                    curr2 = curr2.right;
                } else {
                    System.out.print(curr1.data + " ");
                    curr1 = curr1.right;
                    curr2 = curr2.right;
                }
            }
        }
        while (curr1 != null || !stack1.isEmpty()) {
            if (curr1 != null) {
                stack1.push(curr1);
                curr1 = curr1.left;
            } else {
                curr1 = stack1.pop();
                System.out.print(curr1.data + " ");
                curr1 = curr1.right;
            }
        }
        while (curr2 != null || !stack2.isEmpty()) {
            if (curr2 != null) {
                stack2.push(curr2);
                curr2 = curr2.left;
            } else {
                curr2 = stack2.pop();
                System.out.print(curr2.data + " ");
                curr2 = curr2.right;
            }
        }

        System.out.println();
    }

    // merge two bsts.
    public BinarySearchTree merge1(BinarySearchTree bst1, BinarySearchTree bst2) {
        if (bst1 == null && bst2 == null)
            return null;
        if (bst1 == null)
            return bst2;
        if (bst2 == null)
            return bst1;
        int[] inorder1 = OrderedArrays.getInstance().toInorderArray(bst1.root);
        int[] inorder2 = OrderedArrays.getInstance().toInorderArray(bst2.root);
        System.out.println("inorder 1 = " + inorder1);
        System.out.println("inorder 2 = " + inorder2);
        int[] merged = ArrayUtils.merge(inorder1, inorder2);
        Node root = createBalancedBST(merged);
        return new BinarySearchTree(root);
    }

    /**
     * do inorder traversal, when stack starts unwinding, check if k = ++currVisit, if yes then current root is kth smallest
     * else continue with recursion
     *
     * @author rahul
     */

    static public class KthSmallestLargestUtil {
        private int currVisit;
        private Node smallest;
        private Node largest;

        public KthSmallestLargestUtil(Node root, int k) {
            smallest = computeKthSmallest(root, k);
            this.currVisit = 0;
            largest = computeKthLargest(root, k);
        }

        public Node getSmallest() {
            return smallest;
        }

        public Node getLargest() {
            return largest;
        }

        private Node computeKthSmallest(Node root, int k) {
            if (root == null)
                return null;
            Node left = computeKthSmallest(root.left, k);
            if (left != null)
                return left;
            if (k == ++currVisit)
                return root;
            return computeKthSmallest(root.right, k);
        }

        private Node computeKthLargest(Node root, int k) {
            if (root == null)
                return null;
            Node right = computeKthLargest(root.right, k);
            if (right != null)
                return right;
            if (k == ++currVisit)
                return root;
            return computeKthLargest(root.left, k);
        }
    }

    /**
     * 50
     * /      \
     * 30        70
     * /   \      /  \
     * 20    40    60   80
     * <p>
     * The above tree should be modified to following
     * <p>
     * 260
     * /     \
     * 330        150
     * /   \       /  \
     * 350   300    210   80
     *
     * @author rahul
     * 1. First find the tree sum
     * 2. then inorder traversal, from the left most node start setting data = treeSum
     * and follow the recursion with treesum-data
     */
    static public class TreeNodesWithSumOfGreaterNodes {
        int currentSum = 0;

        public void convertToTreeWithMaxNodeSum(Node root) {
            if (root == null)
                return;
            convertToTreeWithMaxNodeSum(root.right);
            root.data = root.data + currentSum;
            currentSum = root.data;
            convertToTreeWithMaxNodeSum(root.left);
        }
    }

    public static Node inorderPredecessor(Node root, int data) {
        if (root == null) {
            return null;
        }
        if (root.data == data) {
            return max(root.left);
        }
        Node predecessor;
        if (data < root.data) {
            predecessor = inorderPredecessor(root.left, data);
        } else {
            predecessor = inorderPredecessor(root.right, data);
        }

        if (predecessor == null && root.data < data) {
            return root;
        }
        return predecessor;
    }

    public static Node inorderSuccessor(Node root, int data) {
        if (root == null) {
            return null;
        }
        if (root.data == data) {
            return min(root.right);
        }
        Node successor;
        if (data < root.data) {
            successor = inorderSuccessor(root.left, data);
        } else {
            successor = inorderSuccessor(root.right, data);
        }

        if (successor == null && root.data > data) {
            return root;
        }
        return successor;
    }

    @Important
    @Medium
    static public class CorrectBST {
        private Node prev;
        private Node first;
        private Node mid;
        private Node second;


        public CorrectBST() {
            super();
        }

        public void correctBst(Node root) {
            findIncorrectNodes(root);
            if (first != null && second != null) // non adjacent are faulty nodes
                swapData(first, second);
            else if (first != null && mid != null) // parent child (adjacent) are faulty nodes
                swapData(first, mid);
        }

        private void swapData(Node x, Node y) {
            int temp = x.data;
            x.data = y.data;
            y.data = temp;
        }

        private void findIncorrectNodes(Node root) {

            if (root == null)
                return;
            findIncorrectNodes(root.left);
            if (prev != null && prev.data > root.data) {
                if (first == null) {
                    first = prev;
                    mid = root;
                } else {
                    second = root;
                }
            }

            prev = root;
            findIncorrectNodes(root.right);
        }
    }

    @Important
    @Hard
    static public class LargestBstInBT {
        private Node largestBstRoot;
        private int heightOfBst = Integer.MIN_VALUE;

        public void findLargestBst(Node root) {
            findLargestBstUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (heightOfBst <= 0)
                System.out.println("NO BST FOUND");
        }

        private int findLargestBstUtil(Node root, int min, int max) {
            if (root == null)
                return 0;
            int leftHt = findLargestBstUtil(root.left, min, root.data);
            int rightHt = findLargestBstUtil(root.right, root.data, max);
            int currHt = 1 + Math.max(leftHt, rightHt);

            int leftData = (root.left != null) ? root.left.data : min;
            int rightData = (root.right != null) ? root.right.data : max;
            boolean checkCurrNodeIsBST = (leftData < root.data && root.data < rightData);

            if (checkCurrNodeIsBST) {
                if (!TreeUtils.isLeaf(root) && this.heightOfBst < currHt) {
                    this.heightOfBst = currHt;
                    this.largestBstRoot = root;
                }
            }
            return currHt;
        }

        public Node getLargestBstRoot() {
            return largestBstRoot;
        }

        public int getHeightOfBst() {
            return heightOfBst;
        }
    }
}
