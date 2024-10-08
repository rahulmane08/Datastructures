package leetcode.trees.easy;

import leetcode.trees.TreeNode;

public class InvertTree {
  public TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return null;
    }
    TreeNode left = invertTree(root.left);
    TreeNode right = invertTree(root.right);
    root.right = left;
    root.left = right;
    return root;
  }
}
