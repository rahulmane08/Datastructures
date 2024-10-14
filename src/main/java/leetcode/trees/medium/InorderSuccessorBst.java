package leetcode.trees.medium;

import leetcode.trees.TreeNode;

/**
 * 5
 * 3         8
 * <p>
 * 1    4    7      10
 * <p>
 * p = 3, min(3.right) = 4
 * p = 2, 5.left -> 3.left -> 1.right -> null -> 1 (no) -> 3 (yes 3 > 2) = 3
 * p = 0, 5.left -> 3.left -> 1.left -> null -> 1(no) -> 3 (no) -> 5 (no) = null
 * p = 6, 5.right -> 8.left -> 7.left -> null -> 7(yes) = 7.
 */

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
