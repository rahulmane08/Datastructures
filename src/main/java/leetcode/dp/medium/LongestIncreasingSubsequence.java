package leetcode.dp.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/description/
 */
public class LongestIncreasingSubsequence {

  public int lengthOfLIS(int[] nums) {
    return solveTopDown(nums, -1, 0, new HashMap<>());
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

  public static void main(String[] args) {
    LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
    int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
    System.out.println(lis.lengthOfLIS(nums));
  }
}
