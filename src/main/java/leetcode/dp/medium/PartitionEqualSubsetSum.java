package leetcode.dp.medium;

import java.util.Arrays;

public class PartitionEqualSubsetSum {
  public boolean canPartition(int[] nums) {
    int sum = Arrays.stream(nums).sum();
    if (sum % 2 != 0) {
      return false;
    }
    sum = sum / 2;
    Boolean[][] dp = new Boolean[nums.length][sum + 1];
    return topDown(nums, sum, 0, dp);
  }

  private boolean topDown(int[] nums, int target, int index, Boolean[][] dp) {
    if (target == 0) {
      return true;
    }

    if (target < 0 || index == nums.length) {
      return false;
    }

    if (dp[index][target] == null) {
      dp[index][target] = topDown(nums, target - nums[index], index + 1, dp) || topDown(nums, target, index + 1, dp);
    }
    return dp[index][target];
  }
}
