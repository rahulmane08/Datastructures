package leetcode.trees.medium;

import leetcode.trees.TreeNode;

public class InorderPredecessorBst {
  TreeNode inorderPredecessor(TreeNode root, int data) {
    if (root == null) {
      return null;
    }
    TreeNode predecessor = null;

    if (data < root.val) {
      predecessor = inorderPredecessor(root.left, data);
    } else if (data > root.val) {
      predecessor = inorderPredecessor(root.right, data);
    } else {
      predecessor = max(root.left);
    }

    if (predecessor == null && root.val < data) {
      predecessor = root;
    }
    return predecessor;
  }

  private TreeNode max(TreeNode root) {
    while (root != null && root.right != null) {
      root = root.right;
    }
    return root;
  }
}
