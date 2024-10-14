package leetcode.dp.medium;

import java.util.Arrays;

/**
 * Maximum sum of non adjacent elements
 */
public class HouseThief {
  public static void main(String[] args) {
    HouseThief util = new HouseThief();
    System.out.println(util.rob(new int[] {1, 2, 3, 1}));
    System.out.println(util.rob(new int[] {2, 7, 9, 3, 1}));
    System.out.println(util.rob(new int[] {1, 2, 1, 1}));
  }

  public int rob(int[] nums) {
    int[] dp = new int[nums.length];
    Arrays.fill(dp, -1);
    return topDown(nums, 0, dp);
  }

  private int topDown(int[] nums, int index, int[] dp) {
    if (index >= nums.length) {
      return 0;
    }
    if (dp[index] == -1) {
      int sum1 = nums[index] + topDown(nums, index + 2, dp); // include current, try next non adjacent node.
      int sum2 = topDown(nums, index + 1, dp); // exclude current, try next node.
      dp[index] = Math.max(sum1, sum2);
    }
    return dp[index];
  }
}
