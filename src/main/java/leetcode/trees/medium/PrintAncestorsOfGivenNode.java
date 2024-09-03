package leetcode.trees.medium;

import leetcode.trees.TreeNode;

public class PrintAncestorsOfGivenNode {
  public boolean printAncestorsOfGivenNode(TreeNode root, int data) {
    if (root == null) {
      return false;
    }
    if (data == root.val) {
      return true;
    }
    if (printAncestorsOfGivenNode(root.left, data) || printAncestorsOfGivenNode(root.right, data)) {
      System.out.println(root.val);
      return true;
    }
    return false;
  }
}
