package leetcode.dp;

import java.util.HashMap;
import java.util.Map;

public class DpUtil {

  /**
   * T(n) = 2T(n-1) + 1
   * case 1.3 -> a ^ (n/b) = 2^n
   *
   * @param nums
   * @param prefix
   * @param index
   */
  public void traverse(int[] nums, String prefix, int index) {
    if (index == nums.length) {
      System.out.println(prefix);
      return;
    }
    traverse(nums, prefix + nums[index], index + 1);
    traverse(nums, prefix, index + 1);
  }

  public int maxCount(int[] nums, int currIndex) {
    if (currIndex == nums.length) {
      return 0;
    }
    int included = 1 + maxCount(nums, currIndex + 1);
    int excluded = maxCount(nums, currIndex + 1);
    int max = Math.max(included, excluded);
    System.out.printf("index = [%s]: %s%n", currIndex, max);
    return max;
  }

  public int maxCount(int[] nums, int currIndex, Map<Integer, Integer> dp) {
    if (currIndex == nums.length) {
      return 0;
    }

    if (!dp.containsKey(currIndex)) {
      int included = 1 + maxCount(nums, currIndex + 1, dp);
      int excluded = maxCount(nums, currIndex + 1, dp);
      int max = Math.max(included, excluded);
      System.out.printf("index = [%s]: %s%n", currIndex, max);
      dp.put(currIndex, max);
    }
    return dp.get(currIndex);
  }

  public int maxSum(int[] nums, int currIndex, Map<Integer, Integer> dp) {
    if (currIndex == nums.length) {
      return 0;
    }

    if (!dp.containsKey(currIndex)) {
      int included = nums[currIndex] + maxSum(nums, currIndex + 1, dp);
      int excluded = maxSum(nums, currIndex + 1, dp);
      int max = Math.max(included, excluded);
      System.out.printf("index = [%s]: %s%n", currIndex, max);
      dp.put(currIndex, max);
    }
    return dp.get(currIndex);
  }

  /**
   * [1,2,3]
   * @param nums
   */
  void subArraySums(int[] nums) {
    int start = -1;
    int end = -1;
    int max = Integer.MIN_VALUE;
    for(int i = 0; i < nums.length; i++) {
      int sum = 0;
      for (int j = i; j < nums.length; j++) {
        sum += nums[j];
        System.out.printf("[%s,%s] = %s%n", i, j, sum);
        if (sum > max) {
          max = sum;
          start = i;
          end = j;
        }
      }
    }
    System.out.printf("[%s,%s] , max = %s%n", start, end, max);
  }

  public static void main(String[] args) {
    int[] nums = {1, 2, -3, 4};
    DpUtil util = new DpUtil();
    util.traverse(nums, "", 0);
    System.out.println(util.maxCount(nums, 0, new HashMap<>()));
    System.out.println(util.maxSum(nums, 0, new HashMap<>()));

    nums = new int[] {1,2,3};
    util.subArraySums(nums);
  }
}
