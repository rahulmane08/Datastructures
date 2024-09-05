package leetcode.trees.hard;

import interfaces.Hard;
import interfaces.Important;
import java.util.HashMap;
import java.util.Map;
import leetcode.trees.TreeNode;
import utils.Utils;

/**
 * ``````````` 50  max(217, 100 + 5 + 9 + 90) = 254
 * ``````` ``/      \
 * ``````` 8 (108)       2 (99)
 * ``````/   \        /     \
 * ````3(103) 5 (5)  9 (9)    90 (90)
 * ``/
 * 100 (100)
 */
@Important
@Hard
public class MaxSumOfNonAdjacentNodes {
  private final Map<Integer, Integer> nodeSums = new HashMap<>(); // memoization
  private int maxSum = Integer.MIN_VALUE;

  public MaxSumOfNonAdjacentNodes(TreeNode root) {
    this.maxSum = computeMaxSum(root);
  }

  public int getMaxSum() {
    return maxSum;
  }

  private int sumOfGrandChildren(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int leftGrandChildrenSum = 0;
    if (root.left != null) {
      leftGrandChildrenSum = computeMaxSum(root.left.left) + computeMaxSum(root.left.right);
    }

    int rightGrandChildrenSum = 0;
    if (root.right != null) {
      rightGrandChildrenSum = computeMaxSum(root.right.left) + computeMaxSum(root.right.right);
    }

    return leftGrandChildrenSum + rightGrandChildrenSum;
  }

  private int computeMaxSum(TreeNode root) {
    if (root == null) {
      return 0;
    }

    if (nodeSums.containsKey(root.val)) {
      return nodeSums.get(root.val);
    }

    int rootAndGrandChildrenSum = root.val + sumOfGrandChildren(root);
    int childrenSum = computeMaxSum(root.left) + computeMaxSum(root.right);
    nodeSums.put(root.val, Utils.max(rootAndGrandChildrenSum, childrenSum));
    System.out.printf("Node: %d, maxSum: %d%n", root.val, nodeSums.get(root.val));
    return nodeSums.get(root.val);
  }
}
