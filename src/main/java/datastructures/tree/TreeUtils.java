package datastructures.tree;

import datastructures.stack.StackUtils;
import interfaces.Hard;
import interfaces.Important;
import interfaces.Medium;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import utils.Utils;


public class TreeUtils {

  /**
   * POST ORDER TRAVERSAL
   * 1. null nodes return MIN_VALUE.
   * 2. for the current node, find the left tree max and right tree max
   * 3. Return max amongst curr, left max and right max.
   * <p>
   * T(n) = 2T(n/2) + 1 => a=2,b=2, log(a) = 1, k = 0, p = 0, Case 1 => O(n) = n.
   *
   * @param root
   * @return
   */
  public static int findMax(Node root) {
    if (root == null) {
      return Integer.MIN_VALUE;
    }
    int max = root.data;
    int left = findMax(root.left);
    int right = findMax(root.right);

    if (left > max) {
      max = left;
    }
    if (right > max) {
      max = right;
    }
    return max;
  }

  /**
   * PRE ORDER
   * T(n) = 2T(n/2) + 1 => a=2,b=2, log(a) = 1, k = 0, p = 0, Case 1 => O(n) = n.
   *
   * @param data
   * @param root
   * @return
   */
  public static boolean exists(int data, Node root) {
    if (root == null) {
      return false;
    }
    return (root.data == data) || exists(data, root.left) || exists(data, root.right);
  }

  /**
   * T(n) = 2T(n/2) + 1 => a=2,b=2, log(a) = 1, k = 0, p = 0, Case 1 => O(n) = n.
   *
   * @param root
   * @return
   */
  public static int size(Node root) {
    if (root == null) {
      return 0;
    }
    return 1 + size(root.left) + size(root.right);
  }

  /**
   * T(n) = 2T(n/2) + 1 => a=2,b=2, log(a) = 1, k = 0, p = 0, Case 1 => O(n) = n.
   *
   * @param root
   * @return
   */
  public static int sum(Node root) {
    if (root == null) {
      return 0;
    }
    return root.data + sum(root.left) + sum(root.right);
  }

  /**
   * O(1)
   *
   * @param root
   * @return
   */
  public static boolean isLeaf(Node root) {
    if (root == null) {
      return false;
    }
    return (root.left == null && root.right == null);
  }


  /**
   * O(n) = n
   *
   * @param root
   * @return
   */
  public static int sizeIterative(Node root) {
    if (root == null) {
      return 0;
    }
    Queue<Node> queue = new ArrayDeque<>();
    int size = 0;
    queue.offer(root);
    while (!queue.isEmpty()) {
      Node curr = queue.poll();
      ++size;
      if (curr.left != null) {
        queue.offer(curr.left);
      }
      if (curr.right != null) {
        queue.offer(curr.right);
      }
    }
    return size;
  }

  public static void deleteTree(BinaryTree tree) {
    if (tree == null || tree.root == null) {
      return;
    }
    deleteTree(tree.root);
    tree.root = null;
  }

  public static void deleteTree(Node root) {
    if (root == null) {
      return;
    }
    deleteTree(root.left);
    deleteTree(root.right);

    //eliminate the references
    root.left = null;
    root.right = null;
  }

  /**
   * POST ORDER
   * T(n) = 2T(n/2) + 1 => a=2,b=2, log(a) = 1, k = 0, p = 0, Case 1 => O(n) = n.
   *
   * @param root
   * @return
   */
  public static int heightRecursive(Node root) {
    if (root == null) {
      return 0;
    }
    int leftHeight = heightRecursive(root.left);
    int rightHeight = heightRecursive(root.right);
    return 1 + Math.max(leftHeight, rightHeight);
  }

  public static int heightInterative(Node root) {
    Node marker = new Node(null, null, Integer.MIN_VALUE);
    if (root == null) {
      return 0;
    }
    int height = 0;
    Queue<Node> queue = new ArrayDeque<>();
    queue.offer(root);
    queue.offer(marker);
    while (!queue.isEmpty()) {
      Node curr = queue.poll();
      if (curr == marker) {
        //we hit the endof current level , increment the level and put a marker for next level end.
        ++height;
        if (!queue.isEmpty()) //if queue is empty then entire tree is traversed,then dont insert marker.
        {
          queue.offer(marker);
        }
        continue;
      }
      if (curr.left != null) {
        queue.offer(curr.left);
      }
      if (curr.right != null) {
        queue.offer(curr.right);
      }
    }
    return height;
  }

  /**
   * 1. if either of the two is the parent of other then that node is LCA
   * 2. traverse into left tree and assign it to left lca
   * 3. traverse into right tree and assign it to right lca
   * 4. leftLca==null or rightLca will be null if condition 2 is satisfied
   * 5. if both are non null which means from the current root the recursion went down
   * and returned the left and right nodes itself,in which case return the current root
   * <p>
   * T(n) = 2T(n/2) + 1 => a=2,b=2, log(a) = 1, k = 0, p = 0, Case 1 => O(n) = n.
   *
   * @param root
   * @param left
   * @param right
   * @return
   */
  public static Node lca(Node root, int left, int right) {
    if (root == null) {
      return null; // Base condition
    }

    //if either of the two is the parent of the other, or we have traversed down to one of the two nodes
    if (root.data == left || root.data == right) {
      return root;
    }

    //find left/right subtree lca
    Node leftLca = lca(root.left, left, right);
    Node rightLca = lca(root.right, left, right);

    // If both of the above calls return Non-NULL, then one key
    // is present in once subtree and other is present in other,
    // So this node is the LCA
    if (leftLca != null && rightLca != null) {
      return root;
    }

    // Only left key present, its the LCA
    if (leftLca != null) {
      return leftLca;
    }

    // Only right key present, its the LCA
    return rightLca;

    // both keys absent.
  }

  /**
   * T(n) = 2T(n/2) + 1 => a=2,b=2, log(a) = 1, k = 0, p = 0, Case 1 => O(n) = n.
   *
   * @param root
   * @param data
   * @return
   */
  @Important
  public static int depth(Node root, int data) {
    if (root == null) {
      return -1;
    }

    if (root.data == data) {
      return 0;
    }

    int depth = depth(root.left, data);
    if (depth != -1) {
      return 1 + depth;
    }
    depth = depth(root.right, data);
    if (depth != -1) {
      return 1 + depth;
    }
    return -1;
  }

  /**
   * level ordered traversals
   * if the currently popped root is a left increment the count
   * O(n) = n
   *
   * @param root
   * @return
   */
  public static int countLeafNodes(Node root) {
    if (root == null) {
      return 0;
    }
    int count = 0;
    Queue<Node> queue = new ArrayDeque<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      Node curr = queue.poll();
      if (isLeaf(curr)) {
        ++count;
      }
      if (curr.left != null) {
        queue.offer(curr.left);
      }
      if (curr.right != null) {
        queue.offer(curr.right);
      }
    }

    return count;
  }

  /**
   * using post order, find left and right leaf nodes and add them.
   * O(n) = n.
   *
   * @param root
   * @return
   */
  public static int countLeafNodesRecursively(Node root) {
    if (root == null) {
      return 0;
    }
    if (isLeaf(root)) {
      return 1;
    }
    int leftLeaves = countLeafNodesRecursively(root.left);
    int rightLeaves = countLeafNodesRecursively(root.right);
    return leftLeaves + rightLeaves;
  }

  public static void image(Node root) {
    if (root == null) {
      return;
    }

    image(root.left);
    image(root.right);

    Node temp = root.right;
    root.right = root.left;
    root.left = temp;

  }

  public static Node image1(Node root) {
    if (root == null) {
      return null;
    }
    Node left = image1(root.left);
    Node right = image1(root.right);
    root.left = right;
    root.right = left;
    return root;
  }

  /**
   * Inorder sequence: D B E A F C
   * Preorder sequence: A B D E C F
   * <p>
   * Idea:
   * 1. In the normal tree construction using the inorder array we use the mid=start+end/2
   * 2. In pre order traversals, it always visits the root first,
   * 3. Hence we consult the preorder array to find the current root and find its index in inorder arr
   * 4. so instead of mid its the index of element in preorder in inorder arr
   *
   * @param inorder
   * @param preorder
   * @return
   */
  @Important
  @Medium
  public static Node createTreeUsingPreAndInorderSequences(Integer[] inorder, Integer[] preorder) {
    if (inorder == null || preorder == null) {
      return null;
    }
    return createTreeUsingPreAndInorderSequences(inorder, preorder, 0, inorder.length - 1, 0);
  }

  private static Node createTreeUsingPreAndInorderSequences(Integer[] inorder, Integer[] preorder, int start, int end,
                                                            int preIndex) {
    if (start > end || preIndex == preorder.length) {
      return null;
    }
    int data = preorder[preIndex];
    Node current = new Node(null, null, data);

    if (start == end) //leaf node
    {
      return current;
    }

    Integer inIndex = Utils.search(inorder, data);
    current.left = createTreeUsingPreAndInorderSequences(inorder, preorder, start, inIndex - 1, preIndex + 1);
    current.right = createTreeUsingPreAndInorderSequences(inorder, preorder, inIndex + 1, end, preIndex + 1);

    return current;
  }

  /**
   * add next pointer to each tree node.
   * do LOT and add current popped element.next = queue.peek()
   * O(n) = n
   *
   * @param root
   */
  public static void fillNextSiblings(Node root) {
    if (root == null) {
      return;
    }
    Node marker = new Node(Integer.MIN_VALUE);
    Queue<Node> queue = new ArrayDeque<>();
    queue.offer(root);
    queue.offer(marker);
    while (!queue.isEmpty()) {
      Node curr = queue.poll();
      if (curr == marker) {
        if (!queue.isEmpty()) {
          queue.offer(marker);
        }
      } else {
        if (queue.peek() != marker) {
          curr.nextSibling = queue.peek();
        }
        if (curr.left != null) {
          queue.offer(curr.left);
        }
        if (curr.right != null) {
          queue.offer(curr.right);
        }
      }
    }
  }

  /**
   * O(n) = n
   *
   * @param root
   */
  public static void printVerticalSum(Node root) {
    Map<Integer, Integer> levelSum = new HashMap<>();
    computeVerticalSum(root, levelSum, 0);
    levelSum.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getKey))
        .forEach(entry -> System.out.format("Level:%d, Sum:%d%n", entry.getKey(), entry.getValue()));
  }

  private static void computeVerticalSum(Node root, Map<Integer, Integer> levelSum, Integer level) {
    if (root == null) {
      return;
    }
    levelSum.compute(level, (k, v) -> v == null ? root.data : root.data + v);
    computeVerticalSum(root.left, levelSum, level - 1);
    computeVerticalSum(root.right, levelSum, level + 1);
  }


  /**
   * 50
   * /     \
   * /         \
   * 7              2
   * / \             /\
   * /     \          /   \
   * 3        5      1      30
   * <p>
   * output:
   * 50
   * /    \
   * /        \
   * 19           31
   * / \           /  \
   * /     \       /      \
   * 14      5     1       30
   *
   * @param root
   */

  public static void convertToSumTree(Node root) {
    if (root == null) {
      return;
    }
    convertToSumTree(root.left);
    convertToSumTree(root.right);
    int childSum = (root.left != null ? root.left.data : 0)
        + (root.right != null ? root.right.data : 0);
    if (root.data < childSum) {
      root.data = childSum;
    } else {
      increment(root, root.data - childSum);
    }
  }

  static private void increment(Node root, int delta) {
    if (root.left != null) {
      int nextDelta = Math.abs(root.left.data - delta);
      root.left.data = delta;
      increment(root.left, nextDelta);
    } else if (root.right != null) {
      int nextDelta = Math.abs(root.right.data - delta);
      root.right.data = delta;
      increment(root.right, nextDelta);
    }
  }

  /**
   * IP:
   * 10
   * /      \
   * -2        6
   * /   \      /  \
   * 8     -4    7    5
   * OP:
   * 20(4-2+12+6)
   * /      \
   * 4(8-4)      12(7+5)
   * /   \      /  \
   * 0      0    0    0
   *
   * @param root
   * @return
   */
  static public int convertToSumTree1(Node root) {
    if (root == null) {
      return 0;
    }
    int data = root.data;
    int left = convertToSumTree1(root.left);
    int right = convertToSumTree1(root.right);
    int sum = left + right;
    root.data = sum;
    return data + sum;
  }

  /**
   * To create Double tree of the given tree, create a new duplicate for each node, and insert the duplicate as the left child of the original node.
   * INPUT
   * 1
   * /   \
   * 2      3
   * /  \
   * 4     5
   * <p>
   * OUTPUT:
   * 1
   * /   \
   * 1      3
   * /      /
   * 2       3
   * /  \
   * 2    5
   * /    /
   * 4   5
   * /
   * 4
   */

  public static void createDoubleTree(Node root) {
    if (root == null) {
      return;
    }
    Node doubleRoot = new Node(root.data);
    doubleRoot.left = root.left;
    root.left = doubleRoot;
    createDoubleTree(doubleRoot.left);
    createDoubleTree(root.right);
  }

  /**
   * Input:
   * Tree 1                     Tree 2
   * 1                         2
   * / \                       / \
   * 3   2                     1   3
   * /                           \   \
   * 5                             4   7
   * Output:
   * Merged tree:
   * 3
   * / \
   * 4   5
   * / \   \
   * 5   4   7
   * <p>
   * O(n) = n.
   *
   * @param root1
   * @param root2
   * @return
   */
  public static Node addTrees(Node root1, Node root2) {
    if (root1 == null && root2 == null) {
      return null;
    }
    if (root2 == null) {
      return new Node(root1.data);
    }
    if (root1 == null) {
      return new Node(root2.data);
    }
    Node mergedNode = new Node(root1.data + root2.data);
    mergedNode.left = addTrees(root1.left, root2.left);
    mergedNode.right = addTrees(root1.right, root2.right);
    return mergedNode;
  }

  public static class CheckerUtils {

    public static boolean checkIfSubTree(Node mainRoot, Node subRoot) {
      if (mainRoot == null && subRoot == null) {
        return true;
      }
      if (subRoot != null && mainRoot == null) {
        return false;
      }
      if (subRoot == null && mainRoot != null) {
        return true;
      }
      if (mainRoot.data == subRoot.data) {
        return checkIfSubTree(mainRoot.left, subRoot.left) && checkIfSubTree(mainRoot.right, subRoot.right);
      }
      return checkIfSubTree(mainRoot.left, subRoot) || checkIfSubTree(mainRoot.right, subRoot);
    }

    /**
     * 26
     * /   \
     * 10     3
     * /    \     \
     * 4      6      3
     *
     * @param root
     * @return
     */
    public static boolean checkIfSumTree(Node root) {
      try {
        checkIfSumTreeUtil(root);
        return true;
      } catch (Exception e) {
        return false;
      }
    }

    private static int checkIfSumTreeUtil(Node root) {
      if (root == null) {
        return 0;
      }
      if (isLeaf(root)) {
        return root.data;
      }
      int left = checkIfSumTreeUtil(root.left);
      int right = checkIfSumTreeUtil(root.right);
      if (root.data != left + right) {
        throw new RuntimeException("Tree is not a sum tree");
      }
      return 2 * root.data;
    }

    public static boolean areTreesIdentical(Node root1, Node root2) {
      if (root1 == null && root2 == null) {
        return true;
      }
      if (root1 == null || root2 == null) {
        return false;
      }
      return ((root1.data == root2.data)
          && areTreesIdentical(root1.left, root2.left)
          && areTreesIdentical(root1.right, root2.right));
    }

    public static boolean areTreesMirrors(Node root1, Node root2) {
      if (root1 == null && root2 == null) {
        return true;
      }
      if (root1 == null || root2 == null) {
        return false;
      }
      return ((root1.data == root2.data)
          && areTreesMirrors(root1.left, root2.right)
          && areTreesMirrors(root1.right, root2.left));
    }

    /**
     * Every non leaf node has left and right child
     * In other words each node can have exactly 0 or 2 children.
     *
     * @param root
     * @return
     */
    static public boolean checkIfStrictTree(Node root) {
      if (root == null) {
        return true;
      }
      int children = 0;
      if (root.left != null) {
        ++children;
      }
      if (root.right != null) {
        ++children;
      }
      return (children % 2 == 0) && checkIfStrictTree(root.left) && checkIfStrictTree(root.right);
    }

    /**
     * POST ORDER
     * AVL tree check
     * AVL property: Difference between left and right height is <= 1.
     *
     * @return
     */
    public static class AVLChecker {

      private boolean check = true;

      public AVLChecker(Node root) {
        checkIfAVLTree(root);
      }

      private int checkIfAVLTree(Node root) {
        if (!check) {
          return -1;
        }
        if (root == null) {
          return 0;
        }
        int leftHeight = checkIfAVLTree(root.left);
        int rightHeight = checkIfAVLTree(root.right);
        int balance = Math.abs(leftHeight - rightHeight);
        check = (balance <= 1);
        return 1 + Math.max(leftHeight, rightHeight);
      }

      public boolean isCheck() {
        return check;
      }
    }

    /**
     * POST ORDER
     */
    @Important
    public static class InternalNodeWithOneChildChecker {
      private final Node root;
      private boolean check = false;

      public InternalNodeWithOneChildChecker(Node root) {
        this.root = root;
        this.check = checkIfEachInternalHasOneChild(root);
      }

      private boolean checkIfEachInternalHasOneChild(Node root) {
        if (root == null || isLeaf(root)) {
          return true;
        }
        if (!checkIfEachInternalHasOneChild(root.left)) {
          return false;
        }
        if (!checkIfEachInternalHasOneChild(root.right)) {
          return false;
        }
        if (this.root.equals(root)) // root is not an internal node, so skip
        {
          return true;
        }

        int children = 0;
        if (root.left != null) {
          children++;
        }
        if (root.right != null) {
          children++;
        }
        return children % 2 == 1;
      }

      public boolean isCheck() {
        return check;
      }
    }

    public static class SumTreeChecker {
      private boolean check = true;

      public SumTreeChecker(Node root) {
        checkSum(root);
      }

      private int checkSum(Node root) {
        if (root == null) {
          return 0;
        }
        if (isLeaf(root)) {
          return root.data;
        }
        int leftSum = checkSum(root.left);
        int rightSum = checkSum(root.right);
        if (check) {
          check = (root.data == leftSum + rightSum);
        }
        return 2 * root.data;
      }

      public boolean isCheck() {
        return check;
      }
    }
  }

  public static class ViewUtils {

    /**
     * Preorder
     *
     * @param root
     */
    @Important
    static public void topView(Node root) {
      TreeMap<Integer, Integer> map = new TreeMap<>();
      topViewUtil(root, 0, map);
      System.out.println("Top view of Tree");
      map.entrySet().stream()
          .sorted(Comparator.comparingInt(Map.Entry::getKey))
          .map(Map.Entry::getValue)
          .forEach(System.out::println);
    }

    private static void topViewUtil(Node root, int vLevel, TreeMap<Integer, Integer> map) {
      if (root == null) {
        return;
      }
      if (!map.containsKey(vLevel)) {
        map.put(vLevel, root.data);
      }
      topViewUtil(root.left, vLevel - 1, map);
      topViewUtil(root.right, vLevel + 1, map);
    }

    // bottom view of a tree
    static public void bottomView(Node root) {
      TreeMap<Integer, Integer> map = new TreeMap<>();
      bottomViewUtil(root, 0, map);
      System.out.println("Bottom view of Tree");
      map.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getKey))
          .map(Map.Entry::getValue)
          .forEach(System.out::println);
    }

    private static void bottomViewUtil(Node root, int vLevel, TreeMap<Integer, Integer> map) {
      if (root == null) {
        return;
      }
      bottomViewUtil(root.left, vLevel - 1, map);
      bottomViewUtil(root.right, vLevel + 1, map);
      if (!map.containsKey(vLevel)) {
        map.put(vLevel, root.data);
      }
    }

    // left side view of a tree
    static public void leftView(Node root) {
      TreeMap<Integer, Integer> map = new TreeMap<>();
      leftViewUtil(root, 0, map);
      System.out.println("Bottom view of Tree");
      map.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getKey))
          .map(Map.Entry::getValue)
          .forEach(System.out::println);
    }

    private static void leftViewUtil(Node root, int hLevel, TreeMap<Integer, Integer> map) {
      if (root == null) {
        return;
      }
      if (!map.containsKey(hLevel)) {
        map.put(hLevel, root.data);
      }
      leftViewUtil(root.left, hLevel + 1, map);
      leftViewUtil(root.right, hLevel + 1, map);
    }

    // right side view of a tree
    static public void rightView(Node root) {
      TreeMap<Integer, Integer> map = new TreeMap<>();
      rightViewUtil(root, 0, map);
      System.out.println("Bottom view of Tree");
      map.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getKey))
          .map(Map.Entry::getValue)
          .forEach(System.out::println);
    }

    private static void rightViewUtil(Node root, int hLevel, TreeMap<Integer, Integer> map) {
      if (root == null) {
        return;
      }
      if (!map.containsKey(hLevel)) {
        map.put(hLevel, root.data);
      }
      rightViewUtil(root.right, hLevel + 1, map);
      rightViewUtil(root.left, hLevel + 1, map);
    }
  }

  public static class PrintBoundaryNodesUtil {
    public static void printLeftBoundaryTopDown(Node root) {
      if (root == null) {
        return;
      }

      if (root.left != null) {
        System.out.print(root.data + " ");
        printLeftBoundaryTopDown(root.left);
      } else if (root.right != null) {
        System.out.print(root.data + " ");
        printLeftBoundaryTopDown(root.right);
      }
    }

    public static void printRightBoundaryBottomUp(Node root) {
      if (root == null) {
        return;
      }

      if (root.right != null) {
        printRightBoundaryBottomUp(root.right);
        System.out.print(root.data + " ");
      } else if (root.left != null) {
        printRightBoundaryBottomUp(root.left);
        System.out.print(root.data + " ");
      }
    }

    public static void printLeavesLeftToRight(Node root) {
      if (root == null) {
        return;
      }

      if (isLeaf(root)) {
        System.out.print(root.data + " ");
      }

      printLeavesLeftToRight(root.left);
      printLeavesLeftToRight(root.right);
    }

    public static void printBoundaryNodes(Node root) {
      System.out.println("=====printBoundaryNodes=====");
      System.out.print(root.data + " ");
      printLeftBoundaryTopDown(root.left);
      printLeavesLeftToRight(root.left);
      printLeavesLeftToRight(root.right);
      printRightBoundaryBottomUp(root.right);
      System.out.println();
    }
  }

  public static class OrderedArrays {
    public static OrderedArrays instance = new OrderedArrays();
    private static int index = 0;

    private OrderedArrays() {
    }

    public static OrderedArrays getInstance() {
      index = 0;
      return instance;
    }

    public int[] toInorderArray(Node root) {
      int[] inorderArr = new int[TreeUtils.size(root)];
      fillInOrderArray(root, inorderArr);
      return inorderArr;
    }

    private void fillInOrderArray(Node root, int[] inorderArr) {
      if (root == null) {
        return;
      }
      fillInOrderArray(root.left, inorderArr);
      inorderArr[index++] = root.data;
      fillInOrderArray(root.right, inorderArr);
    }

    public int[] toPreorderArray(Node root) {
      int[] preOrderArr = new int[TreeUtils.size(root)];
      fillPreOrderArray(root, preOrderArr);
      return preOrderArr;
    }

    private void fillPreOrderArray(Node root, int[] inorderArr) {
      if (root == null) {
        return;
      }
      inorderArr[index++] = root.data;
      fillPreOrderArray(root.left, inorderArr);
      fillPreOrderArray(root.right, inorderArr);
    }

    public int[] toPostorderArray(Node root) {
      int[] postOrderArr = new int[TreeUtils.size(root)];
      fillPostOrderArray(root, postOrderArr);
      return postOrderArr;
    }

    private void fillPostOrderArray(Node root, int[] inorderArr) {
      if (root == null) {
        return;
      }
      fillPostOrderArray(root.left, inorderArr);
      fillPostOrderArray(root.right, inorderArr);
      inorderArr[index++] = root.data;
    }


    // Array to Tree
    public void fillTreeWithInorderArr(Node root, int[] inorder) {
      if (root == null) {
        return;
      }
      fillTreeWithInorderArr(root.left, inorder);
      root.data = inorder[index++];
      fillTreeWithInorderArr(root.right, inorder);
    }

    public void fillTreeWithPreOrderArr(Node root, int[] inorder) {
      if (root == null) {
        return;
      }
      root.data = inorder[index++];
      fillTreeWithPreOrderArr(root.left, inorder);
      fillTreeWithPreOrderArr(root.right, inorder);
    }

    public void fillTreeWithPostOrderArr(Node root, int[] inorder) {
      if (root == null) {
        return;
      }
      fillTreeWithPostOrderArr(root.left, inorder);
      fillTreeWithPostOrderArr(root.right, inorder);
      root.data = inorder[index++];
    }
  }

  public static class Traversals {
    // Using single stack
    public static void inOrderIterative(Node root) {
      Stack<Node> stack = new Stack<>();
      Node curr = root;
      while (curr != null || !stack.isEmpty()) {
        if (curr != null) {
          stack.push(curr);
          curr = curr.left;
        } else {
          curr = stack.pop();
          System.out.print(curr.data + " ");
          curr = curr.right;
        }
      }
      System.out.println();
    }

    // Using single stack
    public static void preOrderIterative(Node root) {
      Stack<Node> stack = new Stack<>();
      Node curr = root;
      while (curr != null || !stack.isEmpty()) {
        if (curr != null) {
          System.out.print(curr.data + " ");
          stack.push(curr);
          curr = curr.left;
        } else {
          curr = stack.pop();
          curr = curr.right;
        }
      }
      System.out.println();
    }

    // Using single stack
    public static void postOrderIterative(Node root) {
      Stack<Node> stack = new Stack<>();
      Node curr = root;
      System.out.println("Post Order");
      while (curr != null || !stack.isEmpty()) {
        if (curr != null) {
          stack.push(curr);
          curr = curr.left;
        } else {
          curr = stack.peek();
          if (curr.right == null) {
            // no right subtree
            do {
              curr = stack.pop();
              System.out.print(curr.data + " ");
            } while (!stack.isEmpty() && curr == stack.peek().right);
            // this will print leaf is left node, but will keep popping parents if its right node.

            if (stack.isEmpty()) {
              break;
            }

            // re-adjust the current to top of stack.
            curr = stack.peek();
          }
          curr = curr.right;
        }
      }
      System.out.println();
    }

    // Using 2 stacks
    public static void postOrderIterative2(Node root) {
      Stack<Node> tempStack = new Stack<>();
      Stack<Node> finalStack = new Stack<>();
      tempStack.push(root);

      while (!tempStack.isEmpty()) {
        root = tempStack.pop();
        finalStack.push(root);
        if (root.left != null) {
          tempStack.push(root.left);
        }
        if (root.right != null) {
          tempStack.push(root.right);
        }
      }
      while (!finalStack.isEmpty()) {
        System.out.println(finalStack.pop());
      }
    }

    public static void inOrderTraversal(Node node) {
      if (node == null) {
        return;
      }
      inOrderTraversal(node.left);
      System.out.println(node.data);
      inOrderTraversal(node.right);
    }

    public static void preOrderTraversal(Node node) {
      if (node == null) {
        return;
      }
      System.out.println(node.data);
      preOrderTraversal(node.left);
      preOrderTraversal(node.right);
    }

    public static void postOrderTraversal(Node node) {
      if (node == null) {
        return;
      }
      postOrderTraversal(node.left);
      postOrderTraversal(node.right);
      System.out.println(node.data);
    }

    public static void levelOrderTraversal(Node root) {
      System.out.println("===LEVEL ORDER TRAVERSAL===");
      if (root == null) {
        return;
      }
      Queue<Node> queue = new ArrayDeque<>();
      queue.offer(root);
      while (!queue.isEmpty()) {
        Node curr = queue.poll();
        System.out.println(curr);
        if (curr.left != null) {
          queue.offer(curr.left);
        }
        if (curr.right != null) {
          queue.offer(curr.right);
        }
      }
    }

    /**
     * use two stacks: currentLevel, nextLevel
     * add root to currentLevel , add its childs to nextLevel
     * pop and print from currentLevel and when currentLevel is empty then assign it to nextLevel and reset nextLevel
     *
     * @param root
     * @param leftToRight
     */
    public static void levelOrderSpiralTraversal(Node root, boolean leftToRight) {
      System.out.println("===LEVEL ORDER SPIRAL TRAVERSAL===");
      if (root == null) {
        return;
      }
      Stack<Node> currentLevel = new Stack<>();
      Stack<Node> nextLevel = new Stack<>();
      currentLevel.push(root);
      while (!currentLevel.isEmpty()) {
        Node curr = currentLevel.pop();
        System.out.println(curr.data);
        if (leftToRight) {
          if (curr.left != null) {
            nextLevel.push(curr.left);
          }
          if (curr.right != null) {
            nextLevel.push(curr.right);
          }
        } else {
          if (curr.right != null) {
            nextLevel.push(curr.right);
          }
          if (curr.left != null) {
            nextLevel.push(curr.left);
          }
        }
        if (currentLevel.isEmpty()) {
          currentLevel = nextLevel;
          nextLevel = new Stack<>();
          leftToRight = !leftToRight;
        }
      }
    }

    public static void verticalTopDownTraversal(Node root) {
      Map<Integer, ArrayList<Integer>> verticalPaths = new TreeMap<>();
      populateVerticalPaths(root, verticalPaths, 0);
      verticalPaths.forEach((level, path) -> {
        System.out.printf("vertical level = %d: nodes = %s%n", level, path);
      });
    }

    private static void populateVerticalPaths(Node root, Map<Integer, ArrayList<Integer>> verticalPaths,
                                              Integer vLevel) {
      if (root == null) {
        return;
      }
      ArrayList<Integer> vPath = verticalPaths.getOrDefault(vLevel, new ArrayList<>());
      vPath.add(root.data);
      verticalPaths.put(vLevel, vPath);
      populateVerticalPaths(root.left, verticalPaths, vLevel - 1);
      populateVerticalPaths(root.right, verticalPaths, vLevel + 1);
    }

    public static void diagonalTraversal(Node root) {
      Map<Integer, ArrayList<Integer>> diagonalPaths = new TreeMap<>();
      populateDiagonalPaths(root, diagonalPaths, 0);
      diagonalPaths.entrySet().stream()
          .sorted(Comparator.comparingInt(Map.Entry::getKey))
          .forEach(e -> System.out.printf("diagonal level = %d: nodes = %s%n", e.getKey(), e.getValue()));
    }

    private static void populateDiagonalPaths(Node root, Map<Integer, ArrayList<Integer>> diagonalPaths, int level) {
      if (root == null) {
        return;
      }
      ArrayList<Integer> dPath = diagonalPaths.getOrDefault(level, new ArrayList<>());
      dPath.add(root.data);
      diagonalPaths.put(level, dPath);
      populateDiagonalPaths(root.left, diagonalPaths, level + 1);
      populateDiagonalPaths(root.right, diagonalPaths, level);
    }

    public static void diagonalTraversalIterative(Node root) {
      if (root == null) {
        return;
      }
      Queue<Node> queue = new ArrayDeque<>();
      Node curr = root;
      while (curr != null || !queue.isEmpty()) {
        if (curr != null) {
          queue.offer(curr);
          curr = curr.right;
        } else {
          curr = queue.poll();
          System.out.println(curr.data);
          curr = curr.left;
        }
      }
    }
  }

  // Traversals using no Stacks
  public static class MorrisTraversals {
    /**
     * current = root
     * while(current!=null)
     * if(current.left==null)
     * print current;
     * current = current.right
     * else
     * predecessor = inorderPredecessor(current, current.data)
     * if(predecessor.right==null)
     * predecessor.right = current; //point the predecssor right to current
     * current = current.left;
     * else
     * predecessor.right = null;
     * print current;
     * current = current.right
     */

    public static void inOrderTraversal(Node root) {
      Node current = root;
      while (current != null) {
        if (current.left == null) {
          System.out.print(current.data + " ");
          current = current.right;
        } else {
          Node predecessor = current.left;
          while (predecessor.right != null && predecessor.right != current) {
            predecessor = predecessor.right;
          }
          if (predecessor.right == null) {
            predecessor.right = current;
            current = current.left;
          } else {
            predecessor.right = null;
            System.out.print(current.data + " ");
            current = current.right;
          }
        }
      }
      System.out.println();
    }


    public static void preOrderTraversal(Node root) {
      Node current = root;
      while (current != null) {
        if (current.left == null) {
          System.out.println(current);
          current = current.right;
        } else {

          Node predecessor = current.left;
          while (predecessor.right != current && predecessor.right != null) {
            predecessor = predecessor.right;
          }
          if (predecessor.right == null) {
            System.out.println(current);
            predecessor.right = current;
            current = current.left;
          } else {
            predecessor.right = null;
            current = current.right;
          }
        }
      }
    }

  }

  /**
   * 1
   * / \
   * 2   3
   * / \
   * 4   5
   * \
   * 6
   * \
   * 7
   * \
   * 8
   * Diameter = 7 (8-7-6-5-2-1-3)
   */
  static public class DiameterOptimised {

    private int diameter = 0;

    public DiameterOptimised(Node root) {
      computeDiameter(root);
    }

    public int getDiameter() {
      return diameter;
    }

    private int computeDiameter(Node root) {
      if (root == null) {
        return 0;
      }

      int rHeight = computeDiameter(root.right);
      int lHeight = computeDiameter(root.left);
      int nodeDiameter = 1 + lHeight + rHeight;
      int nodeHeight = 1 + Math.max(lHeight, rHeight);
      diameter = Math.max(diameter, nodeDiameter);
      return nodeHeight;
    }
  }

  public static class PathQueryUtils {

    /**
     * level order traversal
     * have maxSum,maxLevel,level and currSum
     * if marker is reached, reset the currSum, check currSum>maxSum if yes maxSum = currSum, maxLevel = level
     * else increment the currSum by current node data and push left and right
     *
     * @param root
     * @return
     */
    public static int findLevelWithMaxSum(Node root) {
      if (root == null) {
        return 0;
      }
      Node marker = new Node(null, null, Integer.MIN_VALUE);
      int maxSum = 0, maxLevel = 0;
      int currSum = 0, level = 0;
      Queue<Node> queue = new ArrayDeque<>();
      queue.offer(root);
      queue.offer(marker);
      while (!queue.isEmpty()) {
        Node curr = queue.poll();
        if (curr == marker) {
          if (currSum > maxSum) {
            maxSum = currSum;
            maxLevel = level;
          }
          if (!queue.isEmpty()) {
            queue.offer(marker); //adding marker for next level
          }
          currSum = 0;//reset the currSum for the next level sum
          ++level;
        } else {
          currSum += curr.data;
          if (curr.left != null) {
            queue.offer(curr.left);
          }
          if (curr.right != null) {
            queue.offer(curr.right);
          }
        }
      }
      return maxLevel;
    }

    /**
     * POST ORDER
     * maintain a stack of current level path and push current root
     * if the current node is leaf print stack
     * traverse left and right
     * pop the current from the stack before the stack unwinds onto parent node
     *
     * @param root
     * @param path
     */
    @Important
    public static void printAllRootToLeafPaths(Node root, Stack<Integer> path) {
      if (root == null) {
        return;
      }
      path.push(root.data);
      printAllRootToLeafPaths(root.left, path);
      printAllRootToLeafPaths(root.right, path);
      if (isLeaf(root)) {
        System.out.println("Found a path: ");
        StackUtils.printStack(path);
      }
      path.pop();
    }

    @Important
    public static void printAllRootToLeafPaths(Node root, String path) {
      if (root == null) {
        return;
      }
      printAllRootToLeafPaths(root.left, path + " " + root.data);
      printAllRootToLeafPaths(root.right, path + " " + root.data);
      if (isLeaf(root)) {
        System.out.println("Found a path: " + path);
      }
    }

    @Important
    public static void printAllRootToLeafPathsIterative(Node root) {
      if (root == null) {
        return;
      }
      Stack<Node> stack = new Stack<>();
      Node curr = root;
      while (curr != null || !stack.isEmpty()) {
        if (curr != null) {
          stack.push(curr);
          curr = curr.left;
        } else {
          curr = stack.pop();
          if (curr.right != null) {
            stack.push(curr);
          } else {
            System.out.println("Found a path: ");
            StackUtils.printStack(stack);
          }
          while (!stack.isEmpty() && curr == stack.peek().right) {
            curr = stack.pop();
          }
          curr = curr.right;
        }
      }
    }

    @Important
    public static boolean printFirstRootToLeafPath(Node root, Stack<Integer> path) {
      if (root == null) {
        return false;
      }
      path.push(root.data);
      if (isLeaf(root)) {
        System.out.println("Found a path = " + path);
        return true;
      }
      boolean pathFound =
          printFirstRootToLeafPath(root.left, path) || printFirstRootToLeafPath(root.right, path);
      path.pop();
      return pathFound;
    }

    @Important
    public static void printAllPathsMatchingSum(Node root, Stack<Integer> path, int sum) {
      if (root == null) {
        return;
      }
      path.push(root.data);
      sum = sum - root.data;
      if (isLeaf(root) && sum == 0) {
        System.out.println("Found a path: ");
        StackUtils.printStack(path);
      }
      printAllPathsMatchingSum(root.left, path, sum);
      printAllPathsMatchingSum(root.right, path, sum);
      path.pop();
    }

    @Important
    public static boolean printFirstPathMatchingSum(Node root, Stack<Integer> path, int sum) {
      if (root == null) {
        return false;
      }
      path.push(root.data);
      sum = sum - root.data;
      if (isLeaf(root) && sum == 0) {
        System.out.printf("Found a path: %s%n", path);
        return true;
      }
      boolean pathFound =
          printFirstPathMatchingSum(root.left, path, sum) || printFirstPathMatchingSum(root.right, path, sum);
      path.pop();
      return pathFound;
    }

    /**
     * PRE ORDER
     *
     * @param root
     */
    public static void printAllAncestors(Node root) {
      if (root == null || isLeaf(root)) {
        return;
      }
      System.out.println(root);
      printAllAncestors(root.left);
      printAllAncestors(root.right);
    }

    /**
     * PRE ORDER
     *
     * @param root
     */
    public static void printAllLeaves(Node root) {
      if (root == null) {
        return;
      }
      if (isLeaf(root)) {
        System.out.println(root);
      }
      printAllLeaves(root.left);
      printAllLeaves(root.right);
    }

    /**
     * POST ORDER
     *
     * @param root
     * @param data
     * @return
     */
    @Important
    @Medium
    public static boolean printAncestorsOfGivenNode(Node root, int data) {
      if (root == null) {
        return false;
      }
      if (data == root.data) {
        return true;
      }
      if (printAncestorsOfGivenNode(root.left, data) || printAncestorsOfGivenNode(root.right, data)) {
        System.out.println(root.data);
        return true;
      }
      return false;
    }

    public static void printKthLevelNodes(Node root, int k) {
      if (root == null || k < 0) {
        return;
      }
      if (k == 0) {
        System.out.print(root.data + " ");
        return;
      }
      printKthLevelNodes(root.left, k - 1);
      printKthLevelNodes(root.right, k - 1);
    }

    public static int lengthOfLongestPathOfNode(Node root) {
      if (root == null) {
        return 0;
      }
      int leftHeight = heightRecursive(root.left);
      int rightHeight = heightRecursive(root.right);
      int nodesInLongestPath = leftHeight + rightHeight + 1;
      return nodesInLongestPath;
    }

    public static int distanceBetweenAnyTwoNodes(Node root, int data1, int data2) {
      Node lca = lca(root, data1, data2);
      if (lca == null) {
        return -1;
      }
      int depth1 = depth(lca, data1);
      if (depth1 == -1) {
        return -1;
      }

      int depth2 = depth(lca, data2);
      if (depth2 == -1) {
        return -1;
      }

      return depth1 + depth2;
    }

    /**
     * 3
     * / \
     * 4   5
     * / \   \
     * 5   4   7
     * <p>
     * Op: 345 + 344 + 357 = 1046
     *
     * @return
     */
    @Important
    public static class SumOfRootToLeafPathsUtil {
      private final Stack<Integer> stack = new Stack<>();
      private double sum = 0;

      public SumOfRootToLeafPathsUtil(Node root) {
        compute(root);
      }

      private void compute(Node root) {
        if (root == null) {
          return;
        }
        this.stack.push(root.data);
        if (isLeaf(root)) {
          this.sum = this.sum + StackUtils.toNumber(stack);
        }
        compute(root.left);
        compute(root.right);
        this.stack.pop();
      }

      public double getSum() {
        return sum;
      }
    }

    public static class SumOfRootToLeafPathsUtil1 {
      private final List<String> paths = new ArrayList<>();
      private double sum = 0;

      public SumOfRootToLeafPathsUtil1(Node root) {
        compute(root, "");
        sum = paths.stream().map(Double::parseDouble).reduce(0d, Double::sum);
      }

      private void compute(Node root, String str) {
        if (root == null) {
          return;
        }
        str += root.data;
        if (isLeaf(root)) {
          paths.add(str);
          return;
        }
        compute(root.left, str);
        compute(root.right, str);
      }

      public double getSum() {
        return sum;
      }
    }

    @Important
    public static class MaxSumOfRootToLeafPathsUtil {
      Deque<Integer> pathWithMaxSum = new ArrayDeque<>();
      private int maxSum = 0, sum = 0;

      public MaxSumOfRootToLeafPathsUtil(Node root) {
        Deque<Integer> queue = new ArrayDeque<>();
        computePathWithMaxSum(root, queue);
      }

      private void computePathWithMaxSum(Node root, Deque<Integer> queue) {
        if (root == null) {
          return;
        }
        queue.offer(root.data);
        sum += root.data;
        if (isLeaf(root)) {
          if (maxSum < sum) {
            pathWithMaxSum = new ArrayDeque<>(queue);
            maxSum = sum;
          }
        }
        computePathWithMaxSum(root.left, queue);
        computePathWithMaxSum(root.right, queue);
        sum = sum - root.data;
        queue.pollLast();
      }

      public int getMaxSum() {
        return maxSum;
      }

      public Deque<Integer> getPathWithMaxSum() {
        return pathWithMaxSum;
      }
    }

    @Important
    @Hard
    public static class MaxSumOfAnyPathUtil {
      private int maxSum = Integer.MIN_VALUE;

      public MaxSumOfAnyPathUtil(Node root) {
        compute(root);
      }

      public int getMaxSum() {
        return maxSum;
      }

      private int compute(Node root) {
        if (root == null) {
          return 0;
        }

        int left = compute(root.left);
        int right = compute(root.right);

        int leftSum = root.data + left; // left path
        int rightSum = root.data + right; // right path
        int allSum = root.data + left + right; // entire path

        maxSum = Utils.max(maxSum, allSum);
        return Utils.max(root.data, leftSum, rightSum);
      }
    }

    @Important
    @Hard
    public static class MaxSumOfAny2LeafPathUtil {
      private int maxSum = Integer.MIN_VALUE;

      public MaxSumOfAny2LeafPathUtil(Node root) {
        compute(root);
      }

      public int getMaxSum() {
        return maxSum;
      }

      private int compute(Node root) {
        if (root == null) {
          return 0;
        }

        int left = compute(root.left);
        int right = compute(root.right);

        int leftSum = left + root.data;
        int rightSum = right + root.data;
        int allSum = left + right + root.data;

        maxSum = Utils.max(maxSum, allSum);
        return Utils.max(leftSum, rightSum);
      }
    }

    /**
     * ``````````` 50  max(217, 100 + 5 + 9 + 90) = 254
     * ``````` ``/      \
     * ``````` 8 (108)       2 (99)
     * ``````/   \        /     \
     * ````3(103) 5 (5)  9 (9)    90 (90)
     * ``/
     * 100 (100)
     */
    @Important
    @Hard
    public static class MaxSumOfNonAdjacentNodesUtil {
      private final Map<Integer, Integer> nodeSums = new HashMap<>(); // memoization
      private int maxSum = Integer.MIN_VALUE;

      public MaxSumOfNonAdjacentNodesUtil(Node root) {
        this.maxSum = computeMaxSum(root);
      }

      public int getMaxSum() {
        return maxSum;
      }

      private int sumOfGrandChildren(Node root) {
        if (root == null) {
          return 0;
        }

        int leftGrandChildrenSum = 0;
        if (root.left != null) {
          leftGrandChildrenSum = computeMaxSum(root.left.left) + computeMaxSum(root.left.right);
        }

        int rightGrandChildrenSum = 0;
        if (root.right != null) {
          rightGrandChildrenSum = computeMaxSum(root.right.left) + computeMaxSum(root.right.right);
        }

        return leftGrandChildrenSum + rightGrandChildrenSum;
      }

      private int computeMaxSum(Node root) {
        if (root == null) {
          return 0;
        }

        if (nodeSums.containsKey(root.data)) {
          return nodeSums.get(root.data);
        }

        int rootAndGrandChildrenSum = root.data + sumOfGrandChildren(root);
        int childrenSum = computeMaxSum(root.left) + computeMaxSum(root.right);
        nodeSums.put(root.data, Utils.max(rootAndGrandChildrenSum, childrenSum));
        System.out.printf("Node: %d, maxSum: %d%n", root.data, nodeSums.get(root.data));
        return nodeSums.get(root.data);
      }
    }

    @Important
    public static class SumOfAllLeftLeafNodes {
      private int sum;

      public SumOfAllLeftLeafNodes(Node root) {
        compute(null, root);
      }

      private void compute(Node prev, Node root) {
        if (root == null) {
          return;
        }
        compute(root, root.left);
        if (isLeaf(root) && prev != null && prev.left == root) {
          sum += root.data;
        }
        compute(root, root.right);
      }

      public int getSum() {
        return sum;
      }
    }

    @Important
    public static class DeepestHorrizontalLevelSumUtil {
      private final List<Integer> deepestNodes = new ArrayList<>();
      private int deepestLevel = -1;
      private int deepestNodesSum = 0;

      public DeepestHorrizontalLevelSumUtil(Node root) {
        compute(root, 0);
        this.deepestNodesSum = deepestNodes.stream().reduce(0, Integer::sum);
      }

      private void compute(Node root, int hLevel) {
        if (root == null) {
          return;
        }
        compute(root.left, hLevel + 1);
        compute(root.right, hLevel + 1);
        if (hLevel > this.deepestLevel) {
          this.deepestNodes.clear();
          this.deepestLevel = hLevel;
          this.deepestNodes.add(root.data);
        } else if (hLevel == this.deepestLevel) {
          this.deepestNodes.add(root.data);
        }
      }

      public int getDeepestLevel() {
        return deepestLevel;
      }

      public List<Integer> getDeepestNodes() {
        return deepestNodes;
      }

      public int getDeepestNodesSum() {
        return deepestNodesSum;
      }
    }
  }

  public static class PrintUtils {

    /**
     * Input : Pointer to root of below tree
     * 1
     * /  \
     * 2    3
     * / \   / \
     * 4   5  6  7
     * <p>
     * Output :
     * 7
     * <p>
     * 3
     * <p>
     * 6
     * <p>
     * 1
     * <p>
     * 5
     * <p>
     * 2
     * <p>
     * 4
     *
     * @param root
     */
    public static void printIn2DRotateLeftBy90(Node root) {
      print2DRotateLeftBy90Util(root, "");
    }

    private static void print2DRotateLeftBy90Util(Node root, String space) {
      if (root == null) {
        return;
      }
      print2DRotateLeftBy90Util(root.right, space + "      ");
      System.out.println(space + root.data);
      print2DRotateLeftBy90Util(root.left, space + "      ");
    }

    public static void printIn2DRotateLeftBy90Transpose(Node root) {
      print2DRotateLeftBy90TransposeUtil(root, "");
    }

    private static void print2DRotateLeftBy90TransposeUtil(Node root, String space) {
      if (root == null) {
        return;
      }
      print2DRotateLeftBy90TransposeUtil(root.left, space + "      ");
      System.out.println(space + root.data);
      print2DRotateLeftBy90TransposeUtil(root.right, space + "      ");
    }

  }

  public static class TreeSerializeDeserializeUtil {

    public static List<Integer> serialize(Node root) {
      if (root == null) {
        return null;
      }
      List<Integer> preorderList = new ArrayList<>();
      serializeUtil(root, preorderList);
      return preorderList;
    }

    private static void serializeUtil(Node root, List<Integer> preorderList) {
      if (root == null) {
        preorderList.add(-1);
        return;
      }
      preorderList.add(root.data);
      serializeUtil(root.left, preorderList);
      serializeUtil(root.right, preorderList);
    }

    public static Node deserialize(List<Integer> preorderList) {
      if (preorderList == null || preorderList.isEmpty()) {
        return null;
      }
      return deserializeUtil(preorderList, new AtomicInteger(0));
    }

    private static Node deserializeUtil(List<Integer> preorderList, AtomicInteger i) {
      if (preorderList.get(i.get()) == -1) {
        return null;
      }
      Node root = new Node(preorderList.get(i.get()));
      root.left = deserializeUtil(preorderList, i);
      i.incrementAndGet();
      root.right = deserializeUtil(preorderList, i);
      return root;
    }
  }
}
