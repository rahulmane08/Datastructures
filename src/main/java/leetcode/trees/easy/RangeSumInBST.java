package leetcode.trees.easy;

import java.util.concurrent.atomic.AtomicInteger;
import leetcode.trees.TreeNode;

public class RangeSumInBST {
  public int rangeSumBST(TreeNode root, int low, int high) {
    if (root == null) {
      return -1;
    }
    AtomicInteger sum = new AtomicInteger(0);
    compute(root, low, high, sum);
    return sum.get();
  }

  private void compute(TreeNode root, int low, int high, AtomicInteger sum) {
    if (root == null) {
      return;
    }
    if (root.val < low && root.val < high) {
      compute(root.right, low, high, sum);
    } else if (root.val > low && root.val > high) {
      compute(root.left, low, high, sum);
    } else {
      sum.set(sum.get() + root.val);
      compute(root.left, low, high, sum);
      compute(root.right, low, high, sum);
    }
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(7);
    TreeNode _4 = new TreeNode(4);
    TreeNode _10 = new TreeNode(10);
    root.left = _4;
    root.right = _10;
    TreeNode _2 = new TreeNode(2);
    TreeNode _6 = new TreeNode(6);
    _4.left = _2;
    _4.right = _6;
    TreeNode _9 = new TreeNode(9);
    TreeNode _11 = new TreeNode(11);
    _10.left = _9;
    _10.right = _11;

    RangeSumInBST util = new RangeSumInBST();
    System.out.println(util.rangeSumBST(root, 1, 7));
  }
}
