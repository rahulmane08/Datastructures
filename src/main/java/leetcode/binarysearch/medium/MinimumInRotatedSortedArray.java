package leetcode.binarysearch.medium;

/**
 * Input: nums = [4,5,6,7,8,0,1,2]
 */
public class MinimumInRotatedSortedArray {
  public int findMin(int[] nums) {
    if (nums == null || nums.length == 0) {
      return -1;
    }

    if (nums[0] <= nums[nums.length - 1]) {
      // already sorted
      return nums[0];
    }

    return nums[findPivot(nums)];
  }

  private int findPivot(int[] nums) {
    int low = 0;
    int high = nums.length - 1;
    while (low <= high) {
      int mid = (low + high) >>> 1;
      if (checkLeftPivot(nums, mid)) {
        return mid;
      } else if (checkRightPivot(nums, mid)) {
        return mid + 1;
      }

      if (nums[low] <= nums[mid]) {
        // left space is sorted, answer lies in right space
        low = mid + 1;
      } else {
        // right space is sorted, answer lies in left space.
        high = mid - 1;
      }
    }
    return -1;
  }

  private boolean checkLeftPivot(int nums[], int index) {
    if (index - 1 > -1 && nums[index - 1] > nums[index]) {
      return true;
    }
    return false;
  }

  private boolean checkRightPivot(int nums[], int index) {
    if (index + 1 < nums.length && nums[index] > nums[index + 1]) {
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    int[] nums = new int[] {4, 5, 6, 7, 8, 0, 1, 2};
    MinimumInRotatedSortedArray util = new MinimumInRotatedSortedArray();
    System.out.println(util.findMin(nums));
    System.out.println(util.findMin(new int[] {3, 4, 5, 1, 2}));
  }
}