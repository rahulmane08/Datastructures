package leetcode.binarysearch.medium;

public class SearchInRotatedArray2 {
  public int search(int[] nums, int target) {
    int low = 0;
    int high = nums.length - 1;
    while (low <= high) {
      int mid = (low + high) >>> 1;
      if (nums[mid] == target) {
        return mid;
      }

      // shrink the search space
      if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
        low++;
        high--;
        continue;
      }

      if (nums[low] <= nums[mid]) {
        // left half is sorted
        // check if target lies in that range
        if (nums[low] <= target && target <= nums[mid]) {
          high = mid - 1; // yes check in the sorted left space.
        } else {
          low = mid + 1; // move to unsorted right space.
        }
      } else {
        // right half is sorted
        // check if target lies in that range
        if (nums[mid] <= target && target <= nums[high]) {
          low = mid + 1; // yes check in the sorted right space.
        } else {
          high = mid - 1; // move to unsorted left space.
        }
      }
    }
    return -1;
  }
}
