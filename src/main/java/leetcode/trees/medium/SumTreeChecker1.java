package leetcode.trees.medium;

import datastructures.tree.Node;
import datastructures.tree.TreeUtils;

/**
 * 20
 * 4      6
 * 1   3   2   4
 * <p>
 * Each non leaf node is sum of all children.
 */
public class SumTreeChecker1 {
  private boolean check = true;

  public SumTreeChecker1(Node root) {
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
    return 2 * root.data;
  }

  public boolean isCheck() {
    return check;
  }
}
