package leetcode.dp.medium;

import java.util.Map;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/description/
 */
public class LongestIncreasingSubsequence {

  public int lengthOfLIS(int[] nums) {
    return solveBottomUp(nums);
  }

  int solveTopDown(int[] nums, int prev, int curr, Map<String, Integer> dp) {
    if (curr == nums.length) {
      return 0;
    }
    String key = "" + prev + curr;
    if (!dp.containsKey(key)) {
      int included = 0;
      if (prev == -1 || nums[prev] < nums[curr]) {
        included = 1 + solveTopDown(nums, curr, curr + 1, dp);
      }
      int excluded = solveTopDown(nums, prev, curr + 1, dp);
      dp.put(key, Math.max(included, excluded));
    }
    return dp.get(key);
  }

  /**
   * 4 2 3 1 5 6
   * 1 1 2 1 3 4
   * <p>
   * dp[i] = 1 + dp[i - 1] when nums[i] >= nums[i-1]
   * dp[i] = dp[i -1] otherwise
   *
   * @param nums
   * @return
   */
  int solveBottomUp(int[] nums) {
    int max = 1;
    int n = nums.length;
    int[] dp = new int[n];
    for (int i = 0; i < n; i++) {
      dp[i] = 1;
      for (int j = 0; j < i; j++) {
        if (nums[j] < nums[i] && dp[i] <= dp[j]) {
          dp[i] = 1 + dp[j];
        }
      }
      max = Math.max(max, dp[i]);
    }
    return max;
  }

  public static void main(String[] args) {
    LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
    int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
    System.out.println(lis.lengthOfLIS(nums));
  }
}
