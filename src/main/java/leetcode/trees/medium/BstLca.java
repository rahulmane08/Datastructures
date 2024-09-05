package leetcode.trees.medium;

import leetcode.trees.TreeNode;

public class BstLca {
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) {
      return null;
    }

    if ((p.val <= root.val && root.val <= q.val) || (q.val <= root.val && root.val <= p.val)) {
      return root;
    }

    if (p.val < root.val && q.val < root.val) {
      return lowestCommonAncestor(root.left, p, q);
    }
    return lowestCommonAncestor(root.right, p, q);
  }
}
