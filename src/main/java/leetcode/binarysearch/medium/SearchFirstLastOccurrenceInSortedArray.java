package leetcode.binarysearch.medium;

public class SearchFirstLastOccurrenceInSortedArray {
  public int[] searchRange(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return new int[] {-1, -1};
    }
    int lowerBound = lowerBound(nums, target);
    if (lowerBound == nums.length || nums[lowerBound] != target) {
      return new int[] {-1, -1};
    }
    int upperBound = upperBound(nums, target);
    return new int[] {lowerBound, upperBound - 1};
  }

  private int lowerBound(int [] nums, int target) {
    int low = 0;
    int high = nums.length - 1;
    while (low <= high) {
      int mid = (low + high) >>> 1;
      if (target <= nums[mid]) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return low;
  }

  private int upperBound(int [] nums, int target) {
    int low = 0;
    int high = nums.length - 1;
    while (low <= high) {
      int mid = (low + high) >>> 1;
      if (target < nums[mid]) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return low;
  }
}
