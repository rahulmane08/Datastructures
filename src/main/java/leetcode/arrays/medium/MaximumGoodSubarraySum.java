package leetcode.arrays.medium;

import java.util.LinkedHashMap;
import java.util.Map;

public class MaximumGoodSubarraySum {
  /**
   * nums = [1,2,3,4,5,6], k = 1
   * nums = [-1,3,2,4,5], k = 3
   *
   * @param nums
   * @param k
   * @return
   */
  public long maximumSubarraySum(int[] nums, int k) {
    long maxSubarraySum = getMaxSubarraySum(nums, k);
    return maxSubarraySum != Long.MIN_VALUE ? maxSubarraySum : 0;
  }

  private void calculateMaximumSubarraySumTLE(int[] nums, int k, int start, int end, int arraySum, int[] max) {
    if (end < nums.length && Math.abs(nums[end] - nums[start]) == k) {
      max[0] = Math.max(arraySum + nums[end], max[0]);
    }
    if (end < nums.length) {
      calculateMaximumSubarraySumTLE(nums, k, start, end + 1, arraySum + nums[end], max);
    }
    if (start < end) {
      calculateMaximumSubarraySumTLE(nums, k, start + 1, end, arraySum - nums[start], max);
    }
  }

  private long getMaxSubarraySum(int[] nums, int k) {
    long maxSubarraySum = Long.MIN_VALUE;
    Map<Integer, Long> prefixSum = new LinkedHashMap<>();
    int currentSum = 0;
    for (int num : nums) {
      int finalCurrentSum = currentSum;
      prefixSum.compute(num, (n, sum) -> sum == null ? finalCurrentSum : Math.min(sum, finalCurrentSum));
      currentSum += num;
      int lower = num - k;
      int higher = num + k;
      if (prefixSum.containsKey(lower)) {
        maxSubarraySum = Math.max(maxSubarraySum, currentSum - prefixSum.get(lower));
      }
      if (prefixSum.containsKey(higher)) {
        maxSubarraySum = Math.max(maxSubarraySum, currentSum - prefixSum.get(higher));
      }
    }
    return maxSubarraySum;
  }

  public static void main(String[] args) {
    MaximumGoodSubarraySum util = new MaximumGoodSubarraySum();
    int[] nums = new int[] {1, 2, 3, 4, 5, 6};
    System.out.println(util.maximumSubarraySum(nums, 1));
    nums = new int[] {-1, 3, 2, 4, 5};
    System.out.println(util.maximumSubarraySum(nums, 3));
    nums = new int[] {-1, -2, -3, -4};
    System.out.println(util.maximumSubarraySum(nums, 2));
    nums = new int[] {1, 5};
    System.out.println(util.maximumSubarraySum(nums, 2));
  }
}
