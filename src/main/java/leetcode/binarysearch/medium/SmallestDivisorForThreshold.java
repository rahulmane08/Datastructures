package leetcode.binarysearch.medium;

import java.util.Arrays;

public class SmallestDivisorForThreshold {

  // 1, 2, 5, 9
  public int smallestDivisor(int[] nums, int threshold) {
    int low = 1;
    int high = Arrays.stream(nums).max().getAsInt();
    int divisor = 1;
    while (low <= high) {
      int mid = (low + high) >>> 1;
      if (!isCrossingThreshold(nums, threshold, mid)) {
        divisor = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return divisor;
  }

  private boolean isCrossingThreshold(int[] nums, int threshold, int divisor) {
    int sum = 0;
    for (int i : nums) {
      sum += (i % divisor == 0 ? i / divisor : (i / divisor) + 1); // 1, 2, 5, 9
      if (sum > threshold) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    SmallestDivisorForThreshold util = new SmallestDivisorForThreshold();
    System.out.println(util.smallestDivisor(new int[] {1, 2, 5, 9}, 6));
    System.out.println(util.smallestDivisor(new int[] {44, 22, 33, 11, 1}, 5));
  }
}
