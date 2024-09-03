package leetcode.trees.easy;

import leetcode.trees.TreeNode;

public class PrintKthLevelNodes {
  public void printKthLevelNodes(TreeNode root, int k) {
    if (root == null || k < 0) {
      return;
    }
    if (k == 0) {
      System.out.print(root.val + " ");
      return;
    }
    printKthLevelNodes(root.left, k - 1);
    printKthLevelNodes(root.right, k - 1);
  }
}
