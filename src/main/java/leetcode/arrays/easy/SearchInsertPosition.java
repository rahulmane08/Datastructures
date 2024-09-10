package leetcode.arrays.easy;

/**
 * https://leetcode.com/problems/search-insert-position/description/
 */
public class SearchInsertPosition {
  public int searchInsert(int[] nums, int target) {
    return searchInsert(nums, target, 0, nums.length - 1);
  }

  private int searchInsert(int[] nums, int target, int low, int high) {
    if (low > high) {
      return -1;
    }

    int mid = low + (high - low) / 2;
    if (nums[mid] == target) {
      return mid;
    }
    if (target < nums[mid]) {
      int left = searchInsert(nums, target, low, mid - 1);
      if (left != -1) {
        return left;
      }
      return mid;
    } else {
      int right = searchInsert(nums, target, mid + 1, high);
      if (right != -1) {
        return right;
      }
      return mid + 1;
    }
  }
}