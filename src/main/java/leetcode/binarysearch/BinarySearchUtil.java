package leetcode.binarysearch;

import lombok.ToString;

public class BinarySearchUtil {

  @ToString
  static class SearchInsertPositionUtil {
    final int[] nums;
    int low;
    int high;
    int mid;
    boolean found;

    SearchInsertPositionUtil(int[] nums) {
      low = 0;
      high = nums.length - 1;
      this.nums = nums;
    }

    public int search(int elem) {
      while (low <= high) {
        mid = (low + high) >>> 1;
        if (nums[mid] == elem) {
          found = true;
          return mid;
        } else if (elem < nums[mid]) {
          high = mid - 1;
        } else {
          low = mid + 1;
        }
      }
      return low;
    }
  }

  @ToString
  static class LowerBoundUtil {
    final int[] nums;
    int low;
    int high;
    int mid;

    LowerBoundUtil(int[] nums) {
      low = 0;
      high = nums.length - 1;
      this.nums = nums;
    }

    public int lowerBound(int elem) {
      while (low <= high) {
        mid = (low + high) >>> 1;
        if (elem <= nums[mid]) {
          high = mid - 1;
        } else {
          low = mid + 1;
        }
      }
      return low;
    }
  }

  @ToString
  static class UpperBoundUtil {
    final int[] nums;
    int low;
    int high;
    int mid;

    UpperBoundUtil(int[] nums) {
      low = 0;
      high = nums.length - 1;
      this.nums = nums;
    }

    public int upperBound(int elem) {
      while (low <= high) {
        mid = (low + high) >>> 1;
        if (elem < nums[mid]) {
          high = mid - 1;
        } else {
          low = mid + 1;
        }
      }
      return low;
    }
  }

  public static void main(String[] args) {
    System.out.println("Even length array");
    int[] nums = new int[] {1, 4, 5, 7, 10, 14};
    searchInsertPosition(nums, 0);
    searchInsertPosition(nums, 3);
    searchInsertPosition(nums, 12);
    searchInsertPosition(nums, 20);
    System.out.println("========================================================================");

    System.out.println("Odd length array");
    nums = new int[] {1, 4, 5, 7, 10, 14, 18};
    searchInsertPosition(nums, 0);
    searchInsertPosition(nums, 3);
    searchInsertPosition(nums, 12);
    searchInsertPosition(nums, 20);
    System.out.println("========================================================================");


    nums = new int[] {1, 1, 1, 3, 5, 8, 15, 19, 19, 19};
    lowerBound(nums, 0);
    lowerBound(nums, 1);
    lowerBound(nums, 5);
    lowerBound(nums, 25);
    lowerBound(nums, 19);
    System.out.println("========================================================================");

    upperBound(nums, 0);
    upperBound(nums, 1);
    upperBound(nums, 5);
    upperBound(nums, 25);
    upperBound(nums, 19);
  }

  private static void searchInsertPosition(int[] nums, int elem) {
    SearchInsertPositionUtil util = new SearchInsertPositionUtil(nums);
    int pos = util.search(elem);
    System.out.printf("elem  = %s, pos = %s, util = %s%n", elem, pos, util);
  }

  private static void lowerBound(int[] nums, int elem) {
    LowerBoundUtil util = new LowerBoundUtil(nums);
    int pos = util.lowerBound(elem);
    System.out.printf("elem  = %s, lowerBound = %s, util = %s%n", elem, pos, util);
  }

  private static void upperBound(int[] nums, int elem) {
    UpperBoundUtil util = new UpperBoundUtil(nums);
    int pos = util.upperBound(elem);
    System.out.printf("elem  = %s, upperBound = %s, util = %s%n", elem, pos, util);
  }
}
