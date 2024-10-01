package leetcode.binarysearch.easy;

import java.util.Arrays;

public class FindRange {
  /**
   * [1,2, 6,7,8, 9] , target = 5.
   * <p>
   * finds ceil floor indexes
   *
   * @param nums
   * @param target
   * @return
   */
  public int[] findRange(int[] nums, int target) {
    int[] range = {-1, -1};
    populateUsingBinarySearch(nums, target, 0, nums.length - 1, range);
    return range;
  }

  private void populateUsingBinarySearch(int[] nums, int target, int low, int high, int[] range) {
    if (low > high) {
      return;
    }
    int mid = (low + high) >>> 1;
    if (nums[mid] == target) {
      range[0] = range[1] = mid;
    } else if (target < nums[mid]) {
      range[1] = mid;
      populateUsingBinarySearch(nums, target, low, mid - 1, range);
    } else {
      range[0] = mid;
      populateUsingBinarySearch(nums, target, mid + 1, high, range);
    }
  }

  public static void main(String[] args) {
    int[] nums = {1, 2, 6, 7, 8, 9};
    FindRange util = new FindRange();
    System.out.println(Arrays.toString(util.findRange(nums, 3)));
    System.out.println(Arrays.toString(util.findRange(nums, 0)));
    System.out.println(Arrays.toString(util.findRange(nums, 10)));
    System.out.println(Arrays.toString(util.findRange(nums, 6)));
    System.out.println(Arrays.toString(util.findRange(nums, 5)));
  }
}
