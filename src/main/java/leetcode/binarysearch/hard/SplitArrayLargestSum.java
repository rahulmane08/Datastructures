package leetcode.binarysearch.hard;

import java.util.Arrays;

/**
 * Given an integer array nums and an integer k,
 * split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.
 * <p>
 * Return the minimized largest sum of the split.
 * <p>
 * A subarray is a contiguous part of the array.
 * <p>
 * Input: nums = [7,2,5,10,8], k = 2
 * Option1 : [7] [2, 5, 10, 8] = max(7, 25) = 25
 * Option2 : [7, 2] [5,1 0, 8] = max(9, 23) = 23
 * Option3 : [7, 2, 5] [10, 8] = max(14, 18) = 18
 * Option4 : [7, 2, 5, 10] [8] = max(24, 8) = 24
 * ===============================================
 * MIN = 18
 */
public class SplitArrayLargestSum {
  public int splitArray(int[] nums, int k) {
    if (k > nums.length) {
      return -1;
    }

    int minSum = 0;
    int low = Arrays.stream(nums).max().getAsInt();
    int high = Arrays.stream(nums).sum();
    while (low <= high) {
      int mid = (low + high) >>> 1;
      int totalArraysForGivenSum = totalArraysForGivenSum(nums, mid);
      if (totalArraysForGivenSum <= k) {
        minSum = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return minSum;
  }

  private int totalArraysForGivenSum(int[] nums, int allowedSum) {
    int totalArrays = 0;
    int currentSum = 0;
    for (int i = 0; i < nums.length; ) {
      if (nums[i] + currentSum <= allowedSum) {
        currentSum += nums[i++];
      } else {
        totalArrays++;
        currentSum = 0;
      }
    }
    return totalArrays + 1;
  }

  public static void main(String[] args) {
    SplitArrayLargestSum util = new SplitArrayLargestSum();
    System.out.println(util.splitArray(new int[] {7, 2, 5, 10, 8}, 2));
  }
}
