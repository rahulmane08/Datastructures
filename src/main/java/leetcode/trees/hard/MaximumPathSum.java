package leetcode.trees.hard;

import java.util.Arrays;
import leetcode.trees.ArrayToTreeUtil;
import leetcode.trees.TreeNode;

/**
 * root = [-10,9,20,null,null,15,7]
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
 */
public class MaximumPathSum {

  private int maxSum = Integer.MIN_VALUE;

  public int maxPathSum(TreeNode root) {
    compute(root);
    return maxSum;
  }

  private int compute(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int leftMaxSum = compute(root.left); // 15
    int rightMaxSum = compute(root.right); // 7
    int leftPathSumIncludingRoot = leftMaxSum + root.val; // 35
    int rightPathSumIncludingRoot = rightMaxSum + root.val; // 27
    int allPathSumIncludingRoot = root.val + leftMaxSum + rightMaxSum; // 42
    int currentMaxSum =
        Math.max(root.val,
            Math.max(allPathSumIncludingRoot, Math.max(leftPathSumIncludingRoot, rightPathSumIncludingRoot))); // 42
    maxSum = Math.max(maxSum, currentMaxSum); // 42
    return Math.max(root.val, Math.max(leftPathSumIncludingRoot, rightPathSumIncludingRoot)); // 35
  }

  private int max(int... nums) {
    return Arrays.stream(nums).max().getAsInt();
  }

  public static void main(String[] args) {
    Integer[] arr = new Integer[] {-10, 9, 20, null, null, 15, 7};
    System.out.println(new MaximumPathSum().maxPathSum(ArrayToTreeUtil.compute(arr)));
    arr = new Integer[] {2, -1, -2};
    System.out.println(new MaximumPathSum().maxPathSum(ArrayToTreeUtil.compute(arr)));
  }
}
