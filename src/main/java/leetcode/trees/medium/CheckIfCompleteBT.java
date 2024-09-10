package leetcode.trees.medium;

import leetcode.trees.TreeNode;

public class CheckIfCompleteBT {
  public boolean checkIfStrictTree(TreeNode root) {
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
}
