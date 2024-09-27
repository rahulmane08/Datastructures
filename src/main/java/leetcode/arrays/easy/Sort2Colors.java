package leetcode.arrays.easy;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Sort2Colors {
  /**
   * [0, 1, 0, 0, 1, 0, 1, 0]
   *
   * @param nums
   */
  public void sortColors(int[] nums) {
    int n = nums.length;
    int start = 0;
    int curr = 0;
    while (curr < n) {
      if (nums[start] == 0) {
        start++;
      } else if (nums[start] == 1 && nums[curr] == 0) {
        swap(nums, start, curr);
        start++;
      }
      curr++;
    }
  }

  private void swap(int[] arr, int i, int j) {
    if (i != j) {
      int temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
    }
  }

  public static void main(String[] args) {
    Sort2Colors util = new Sort2Colors();
    int[] nums = new int[] {0, 1, 0, 0, 1, 0, 1, 0};
    util.sortColors(nums);
    System.out.println(Arrays.toString(nums));
  }
}
