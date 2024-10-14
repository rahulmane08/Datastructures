package leetcode.trees.easy;

import leetcode.trees.TreeNode;

public class TreeCheckIfSubtree {
  public boolean isSubtree(TreeNode root, TreeNode subRoot) {
    if (root == null && subRoot == null) {
      return true;
    }

    if (root == null && subRoot != null) {
      return false;
    }

    if (root != null && subRoot == null) {
      return true;
    }

    if (root.val == subRoot.val) {
      return isSubtree(root.left, subRoot.left) && isSubtree(root.right, subRoot.right);
    }
    return isSubtree(root.left, subRoot.left) || isSubtree(root.right, subRoot.right);
  }
}
