package leetcode.medium;

import java.util.Arrays;

public class RemoveDuplicates2 {
  public int removeDuplicates(int[] nums) {
    if (nums == null) {
      return -1;
    }
    int copy = 0;
    int i = 0;
    int j = 0;
    int n = nums.length;
    while (i < n) {
      if (j == n || nums[i] != nums[j]) {
        int times = Math.min(j - i, 2);
        if (j == n) {
          i = n - 1;
        }
        for (int x = 0; x < times; x++) {
          nums[copy++] = nums[i];
        }
        i = j;
      }
      j++;
    }
    return copy - 1;
  }

  public static void main(String[] args) {
    int[] nums = new int[] {1, 1, 1, 2, 3, 3, 3, 4};
    RemoveDuplicates2 util = new RemoveDuplicates2();
    System.out.println(util.removeDuplicates(nums) + ":" + Arrays.toString(nums));
    nums = new int[] {1};
    System.out.println(util.removeDuplicates(nums) + ":" + Arrays.toString(nums));
    nums = new int[] {1, 1, 1};
    System.out.println(util.removeDuplicates(nums) + ":" + Arrays.toString(nums));
  }
}
