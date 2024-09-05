package leetcode.trees.medium;

import leetcode.trees.TreeNode;

public class DeepestLeavesSum {

  private int deepestLevel = -1;
  private int sum = 0;

  public int deepestLeavesSum(TreeNode root) {
    compute(root, 0);
    return sum;
  }

  void compute(TreeNode root, int level) {
    if (root == null) {
      return;
    }
    if (root.left == null && root.left == null) {
      if (level == deepestLevel) {
        sum += root.val;
      } else if (level > deepestLevel) {
        sum = root.val;
        deepestLevel = level;
      }
    }
    compute(root.left, level + 1);
    compute(root.right, level + 1);
  }
}
