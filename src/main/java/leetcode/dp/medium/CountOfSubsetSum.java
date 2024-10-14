package leetcode.dp.medium;

public class CountOfSubsetSum {

  public static void main(String[] args) {
    int[] nums = {1, 2, 3, 7};
    CountOfSubsetSum util = new CountOfSubsetSum();
    System.out.println(util.countSubsetSum(nums, 6));
    System.out.println(util.countSubsetSum(nums, 1));
    System.out.println(util.countSubsetSum(nums, 2));
    System.out.println(util.countSubsetSum(nums, 3));
    System.out.println(util.countSubsetSum(nums, 7));
    System.out.println(util.countSubsetSum(nums, 0));
    System.out.println(util.countSubsetSum(nums, 13));
    System.out.println(util.countSubsetSum(nums, 14));
  }

  public int countSubsetSum(int[] nums, int target) {
    int[][] dp = new int[nums.length][target + 1];
    for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < target + 1; j++) {
        dp[i][j] = -1;
      }
    }
    return topDown(nums, target, 0, dp);
  }

  private int topDown(int[] nums, int target, int index, int[][] dp) {
    if (target == 0) {
      return 1;
    }

    if (target < 0 || index == nums.length) {
      return 0;
    }

    if (dp[index][target] == -1) {
      dp[index][target] = topDown(nums, target - nums[index], index + 1, dp) + topDown(nums, target, index + 1, dp);
    }
    return dp[index][target];
  }
}
