package leetcode.dp.medium;

import java.util.HashMap;
import java.util.Map;

public class PartitionArrayWithMinDifference {
  public int minimumDifference(int[] nums) {
    Map<String, Integer> dp = new HashMap<>();
    return Math.abs(topDown(nums, 0, 0, 0, dp));
  }

  private int topDown(int[] nums, int index, int sum1, int sum2, Map<String, Integer> dp) {
    if (index == nums.length) {
      return Math.abs(sum1 - sum2);
    }
    String key = "" + index + sum1;
    if (!dp.containsKey(key)) {
      dp.put(key, Math.min(
          topDown(nums, index + 1, sum1 + nums[index], sum2, dp),
          topDown(nums, index + 1, sum1, sum2 + nums[index], dp))
      );
    }
    return dp.get(key);
  }

  public static void main(String[] args) {
    int[] nums = new int[] {3, 9, 7, 3};
    PartitionArrayWithMinDifference util = new PartitionArrayWithMinDifference();
    System.out.println(util.minimumDifference(nums));
    nums = new int[] {2, -1, 0, 4, -2, -9};
    System.out.println(util.minimumDifference(nums));
  }
}
