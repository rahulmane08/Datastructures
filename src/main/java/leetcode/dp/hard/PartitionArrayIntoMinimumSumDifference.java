package leetcode.dp.hard;

import java.util.Arrays;

public class PartitionArrayIntoMinimumSumDifference {
  public int minimumDifference(int[] nums) {
    int sum = Arrays.stream(nums).map(Math::abs).sum();
    Integer[][] dp = new Integer[nums.length][sum + 1];
    return Math.abs(topDown(nums, 0, 0, 0, dp));
  }

  private int topDown(int[] nums, int sum1, int sum2, int index, Integer[][] dp) {
    if (index == nums.length) {
      return sum1 - sum2;
    }

    if (dp[index][Math.abs(sum1)] == null) {
      int leftSum = topDown(nums, sum1 + nums[index], sum2, index + 1, dp);
      int rightSum = topDown(nums, sum1, sum2 + nums[index], index + 1, dp);
      dp[index][Math.abs(sum1)] = Math.min(leftSum, rightSum);
    }
    return dp[index][Math.abs(sum1)];
  }

  public static void main(String[] args) {
    PartitionArrayIntoMinimumSumDifference util = new PartitionArrayIntoMinimumSumDifference();
    System.out.println(util.minimumDifference(new int[] {-36, 36}));
  }
}
