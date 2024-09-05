package leetcode.trees.medium;

import leetcode.trees.TreeNode;

/**
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/
 */
public class SumRootToLeafPaths {
  private int totalSum = 0;

  public int sumNumbers(TreeNode root) {
    compute(root, "");
    return totalSum;
  }

  void compute(TreeNode root, String path) {
    if (root == null) {
      return;
    }

    if (root.left == null && root.right == null) {
      totalSum += Integer.parseInt(path + root.val);
      return;
    }

    compute(root.left, path + root.val);
    compute(root.right, path + root.val);
  }
}
