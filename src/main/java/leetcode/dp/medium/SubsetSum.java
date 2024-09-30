package leetcode.dp.medium;

public class SubsetSum {
  public boolean checkSubsetSum(int[] nums, int target) {
    Boolean[][] dp = new Boolean[nums.length][target + 1];
    return topDown(nums, target, 0, dp);
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

  public static void main(String[] args) {
    int[] nums = {1, 2, 3, 7};
    SubsetSum util = new SubsetSum();
    System.out.println(util.checkSubsetSum(nums, 6));
    System.out.println(util.checkSubsetSum(nums, 1));
    System.out.println(util.checkSubsetSum(nums, 2));
    System.out.println(util.checkSubsetSum(nums, 3));
    System.out.println(util.checkSubsetSum(nums, 7));
    System.out.println(util.checkSubsetSum(nums, 0));
    System.out.println(util.checkSubsetSum(nums, 13));
    System.out.println(util.checkSubsetSum(nums, 14));

  }
}
