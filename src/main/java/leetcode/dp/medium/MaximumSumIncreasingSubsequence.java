package leetcode.dp.medium;

import java.util.HashMap;
import java.util.Map;

public class MaximumSumIncreasingSubsequence {

  public static void main(String[] args) {
    MaximumSumIncreasingSubsequence util = new MaximumSumIncreasingSubsequence();
    System.out.println(util.maxSum(new int[] {4, 1, 2, 10}));
  }

  public int maxSum(int[] nums) {
    return solveTopDown(nums, -1, 0, 0, new HashMap<>());
  }

  int solveTopDown(int[] nums, int prev, int curr, int sum, Map<String, Integer> dp) {
    if (curr == nums.length) {
      return sum;
    }
    String key = "" + prev + curr;
    if (!dp.containsKey(key)) {
      int sum1 = Integer.MIN_VALUE;
      if (prev == -1 || nums[prev] < nums[curr]) {
        sum1 = solveTopDown(nums, curr, curr + 1, sum + nums[curr], dp);
      }
      int sum2 = solveTopDown(nums, prev, curr + 1, sum, dp);
      dp.put(key, Math.max(sum1, sum2));
    }
    return dp.get(key);
  }
}
