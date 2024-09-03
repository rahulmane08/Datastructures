package leetcode.trees.easy;

import leetcode.trees.TreeNode;

public class SumOfLeftLeaves {
  private int sum;

  public int sumOfLeftLeaves(TreeNode root) {
    compute(root, null);
    return sum;
  }

  void compute(TreeNode root, TreeNode parent) {
    if (root == null) {
      return;
    }
    if (root.left == null && root.right == null && parent != null && parent.left == root) {
      this.sum += root.val;
      return;
    }
    compute(root.left, root);
    compute(root.right, root);
  }
}
