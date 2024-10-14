package leetcode.binarysearch.medium;

import java.util.Arrays;

public class FindClosestElementsInSortedArray {
  public static void main(String[] args) {
    int[] nums = new int[] {1, 2, 3, 4, 6, 7};
    FindClosestElementsInSortedArray util = new FindClosestElementsInSortedArray();
    System.out.println(Arrays.toString(util.findClosestElementsInSortedArray(nums, 5)));
    System.out.println(Arrays.toString(util.findClosestElementsInSortedArray(nums, 6)));
    System.out.println(Arrays.toString(util.findClosestElementsInSortedArray(nums, 8)));
    System.out.println(Arrays.toString(util.findClosestElementsInSortedArray(nums, 0)));
  }

  int[] findClosestElementsInSortedArray(int[] nums, int target) {
    if (nums == null) {
      return null;
    }
    int[] closest = new int[] {-1, -1};
    compute(nums, 0, nums.length - 1, target, closest);
    return closest;
  }

  void compute(int[] nums, int low, int high, int target, int[] closest) {
    if (low > high) {
      return;
    }
    int mid = (low + high) >>> 1;
    if (nums[mid] == target) {
      closest[0] = closest[1] = target;

    } else if (target < nums[mid]) {
      closest[1] = nums[mid];
      compute(nums, low, mid - 1, target, closest);
    } else {
      closest[0] = nums[mid];
      compute(nums, mid + 1, high, target, closest);
    }
  }
}
