package leetcode.binarysearch.medium;

public class SearchInRotatedArray {

  public static void main(String[] args) {
    SearchInRotatedArray util = new SearchInRotatedArray();
    int[] nums = new int[] {1, 2, 3, 5, 6, 7};
    System.out.println(util.search(nums, 4));
    nums = new int[] {5, 6, 7, 1, 2, 3};
    System.out.println(util.search(nums, 1));
    nums = new int[] {3, 5, 6, 7, 1, 2};
    System.out.println(util.search(nums, 1));
    nums = new int[] {3, 5, 6, 7, 1, 2};
    System.out.println(util.search(nums, 4));
    nums = new int[] {1};
    System.out.println(util.search(nums, 1));
    nums = new int[] {1};
    System.out.println(util.search(nums, 4));
    nums = new int[] {3, 1};
    System.out.println(util.search(nums, 1));
  }

  public int search(int[] nums, int target) {
    int pivot = findPivot(nums, 0, nums.length - 1);
    if (pivot == -1) {
      // entire array is sorted
      return search(nums, 0, nums.length - 1, target);
    }
    int index = search(nums, 0, pivot - 1, target);
    if (index != -1) {
      return index;
    }
    return search(nums, pivot, nums.length - 1, target);
  }

  int search(int[] nums, int low, int high, int target) {
    if (low > high) {
      return -1;
    }
    int mid = low + ((high - low) / 2);
    if (nums[mid] == target) {
      return mid;
    } else if (target < nums[mid]) {
      return search(nums, low, mid - 1, target);
    }
    return search(nums, mid + 1, high, target);
  }

  int findPivot(int[] nums, int low, int high) {
    if (low > high) {
      return -1;
    }
    if (nums[low] > nums[high]) {
      int mid = low + ((high - low) / 2);
      if (mid - 1 > low && nums[mid - 1] > nums[mid]) {
        return mid;
      }

      if (mid + 1 < high && nums[mid] > nums[mid + 1]) {
        return mid + 1;
      }

      int leftPivot = findPivot(nums, low, mid - 1);
      int rightPivot = findPivot(nums, mid + 1, high);
      if (leftPivot == -1 && rightPivot == -1) {
        return mid;
      }
      if (leftPivot != -1) {
        return leftPivot;
      }
      return rightPivot;
    }
    return -1;
  }

  /**
   * Input: nums = [4,5,6,7,0,1,2], target = 0
   * Output: 4
   * @param nums
   * @param target
   * @return
   */
  public int searchInRotatedArray1(int[] nums, int target) {
    int low = 0;
    int high = nums.length - 1;
    while (low <= high) {
      int mid = (low + high) >>> 1;
      if (nums[mid] == target) {
        return mid;
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
