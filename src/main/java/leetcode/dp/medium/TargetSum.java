package leetcode.dp.medium;

import java.util.HashMap;
import java.util.Map;

public class TargetSum {
  public int findTargetSumWays(int[] nums, int target) {
    Map<String, Integer> dp = new HashMap<>();
    return topDown(nums, target, 0, dp);
  }

  private int topDown(int[] nums, int target, int index, Map<String, Integer> dp) {
    if (index == nums.length) { // entire array consumed
      if (target == 0) {
        return 1; // we hit the target sum, hence return 1 possible combination.
      }
      return 0; // no such combination
    }

    String key = "" + index + target;
    if (!dp.containsKey(key)) {
      dp.put(key,
          topDown(nums, target + nums[index], index + 1, dp) + topDown(nums, target - nums[index], index + 1, dp));
    }

    return dp.get(key);
  }

  public static void main(String[] args) {
    int[] nums = {1, 1, 1, 1, 1};
    TargetSum util = new TargetSum();
    System.out.println(util.findTargetSumWays(nums, 3));
    System.out.println(util.findTargetSumWays(nums, 5));
    System.out.println(util.findTargetSumWays(nums, 10));
    System.out.println(util.findTargetSumWays(new int[] {1}, 1));
  }
}
