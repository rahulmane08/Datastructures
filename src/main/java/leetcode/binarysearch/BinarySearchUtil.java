package leetcode.binarysearch;

import java.util.Arrays;
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
  public static class LowerBoundUtil {
    final int[] nums;
    int low;
    int high;
    int mid;

    public LowerBoundUtil(int[] nums) {
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
  public static class UpperBoundUtil {
    final int[] nums;
    int low;
    int high;
    int mid;

    public UpperBoundUtil(int[] nums) {
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

  public static class FindPivotIndexUtil {
    public int findPivotIndex(int[] nums) {
      int low = 0;
      int high = nums.length - 1;
      while (low <= high && nums[low] > nums[high]) {
        int mid = (low + high) >>> 1;
        if (isLeftPivot(nums, mid)) {
          return mid;
        }
        if (isRightPivot(nums, mid)) {
          return mid + 1;
        }

        if (nums[mid + 1] < nums[high]) {
          // right space is sorted.
          high = mid - 1;
        } else {
          low = mid + 1;
        }
      }
      return -1;
    }

    public int findPivotIndexRecursively(int[] nums, int low, int high) {
      if (low <= high && nums[low] > nums[high]) {
        int mid = (low + high) >>> 1;
        if (isLeftPivot(nums, mid)) {
          return mid;
        }
        if (isRightPivot(nums, mid)) {
          return mid + 1;
        }

        int left = findPivotIndexRecursively(nums, low, mid - 1);
        int right = findPivotIndexRecursively(nums, mid + 1, high);
        if (left == -1 && right == -1) {
          return mid;
        }
        return left != -1 ? left : right;
      }
      return -1;
    }

    private boolean isRightPivot(int[] nums, int index) {
      return nums[index] > nums[index + 1];
    }

    private static boolean isLeftPivot(int[] nums, int index) {
      return nums[index - 1] > nums[index];
    }
  }

  public static void main(String[] args) {
    int[] nums = new int[] {1, 1, 1, 3, 5, 8, 15, 19, 19, 19};
    System.out.printf("array : %s, length = %s%n", Arrays.toString(nums), nums.length);
    for (int elem : new int[] {0, 1, 3, 5, 6, 19, 25}) {
      System.out.printf("elem: %s, insertPos: %s, lowerBound:%s, upperBound: %s%n", elem,
          searchInsertPosition(nums, elem), lowerBound(nums, elem), upperBound(nums, elem));
    }
  }

  private static int searchInsertPosition(int[] nums, int elem) {
    SearchInsertPositionUtil util = new SearchInsertPositionUtil(nums);
    return util.search(elem);
  }

  private static int lowerBound(int[] nums, int elem) {
    LowerBoundUtil util = new LowerBoundUtil(nums);
    return util.lowerBound(elem);
  }

  private static int upperBound(int[] nums, int elem) {
    UpperBoundUtil util = new UpperBoundUtil(nums);
    return util.upperBound(elem);
  }
}
