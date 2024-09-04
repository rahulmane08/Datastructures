package leetcode.trees.easy;

import java.util.concurrent.atomic.AtomicInteger;
import leetcode.trees.TreeNode;

public class Diameter {

  private final AtomicInteger diameter = new AtomicInteger(-1);

  public static void main(String[] args) {
    TreeNode _1 = new TreeNode(1);
    TreeNode _2 = new TreeNode(2);
    TreeNode _3 = new TreeNode(3);
    TreeNode _4 = new TreeNode(4);
    TreeNode _5 = new TreeNode(5);
    _1.left = _2;
    _1.right = _3;
    _2.left = _4;
    _2.right = _5;
    Diameter util = new Diameter();
    System.out.println(util.diameterOfBinaryTree(_1));
  }

  public int diameterOfBinaryTree(TreeNode root) {
    compute(root);
    return diameter.get();
  }

  int compute(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftHeight = compute(root.left);
    int rightHeight = compute(root.right);
    int nodeHeight = 1 + Math.max(leftHeight, rightHeight);
    int nodeDiameter = leftHeight + rightHeight;
    this.diameter.set(Math.max(this.diameter.get(), nodeDiameter));
    return nodeHeight;
  }
}
