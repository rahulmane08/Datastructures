package leetcode.arrays.medium;

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
    if (index == -1) {
      index = search(nums, pivot, nums.length - 1, target);
    }
    return index;
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
}
