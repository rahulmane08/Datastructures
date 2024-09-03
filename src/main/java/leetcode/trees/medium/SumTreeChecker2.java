package leetcode.trees.medium;

import datastructures.tree.Node;
import datastructures.tree.TreeUtils;

/**
 *        10
 *     4      6
 *   1   3   2   4
 *
 *   Each non leaf node is sum of immediate children.
 */
public class SumTreeChecker2 {
  private boolean check = true;

  public SumTreeChecker2(Node root) {
    checkSum(root);
  }

  private int checkSum(Node root) {
    if (root == null) {
      return 0;
    }
    if (TreeUtils.isLeaf(root)) {
      return root.data;
    }
    int leftSum = checkSum(root.left);
    int rightSum = checkSum(root.right);
    if (check) {
      check = (root.data == leftSum + rightSum);
    }
    return root.data;
  }

  public boolean isCheck() {
    return check;
  }
}
