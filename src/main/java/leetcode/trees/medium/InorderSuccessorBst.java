package leetcode.trees.medium;

import leetcode.trees.TreeNode;

public class InorderSuccessorBst {
  public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    if (root == null) {
      return null;
    }

    if (root == p) {
      return min(p.right);
    }
    TreeNode successor = null;
    if (p.val < root.val) {
      successor = inorderSuccessor(root.left, p);
    } else {
      successor = inorderSuccessor(root.right, p);
    }

    if (successor == null && root.val > p.val) {
      successor = root;
    }
    return successor;
  }

  TreeNode min(TreeNode root) {
    TreeNode min = root;
    for (; min != null && min.left != null; min = min.left) ;
    return min;
  }
}
