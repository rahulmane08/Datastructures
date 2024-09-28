package leetcode.dp.medium;

import java.util.Arrays;

/**
 * Maximum sum of non adjacent elements
 */
public class HouseThief {
  public int rob(int[] nums) {
    int[] dp = new int[nums.length];
    Arrays.fill(dp, -1);
    return topDown(nums, 0, 0, dp);
  }

  private int topDown(int[] nums, int index, int sum, int[] dp) {
    if (index >= nums.length) {
      return sum;
    }
    if (dp[index] == -1) {
      dp[index] = Math.max(topDown(nums, index + 2, sum + nums[index], dp),
          topDown(nums, index + 1, sum, dp));
    }
    return dp[index];
  }

  public static void main(String[] args) {
    HouseThief util = new HouseThief();
    System.out.println(util.rob(new int[] {1, 2, 3, 1}));
    System.out.println(util.rob(new int[] {2, 7, 9, 3, 1}));
  }
}
