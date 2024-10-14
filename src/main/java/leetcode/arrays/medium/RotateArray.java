package leetcode.arrays.medium;

import java.util.Arrays;

public class RotateArray {
  public static void main(String[] args) {
    RotateArray util = new RotateArray();
    int[] nums = {3, 2, 1, 0, 4};
    util.rotateRight(nums, 2);
    System.out.println(Arrays.toString(nums));

    nums = new int[] {3, 2, 1, 0, 4};
    util.rotateRight(nums, 4);
    System.out.println(Arrays.toString(nums));

    nums = new int[] {3, 2, 1, 0, 4};
    util.rotateLeft(nums, 2);
    System.out.println(Arrays.toString(nums));

    nums = new int[] {3, 2, 1, 0, 4};
    util.rotateLeft(nums, 4);
    System.out.println(Arrays.toString(nums));
  }

  public void rotateRight(int[] nums, int k) {
    int n = nums.length;
    if (k < 1 || k == n) {
      return;
    }
    k = k % n;
    int[] temp = new int[k];

    for (int i = n - k, j = 0; i < n; i++, j++) {
      temp[j] = nums[i];
    }

    int i = n - 1;
    for (; i >= k; i--) {
      nums[i] = nums[i - k];
    }

    for (; i > -1; i--) {
      nums[i] = temp[i];
    }
  }

  public void rotateLeft(int[] nums, int k) {
    int n = nums.length;
    if (k < 1 || k == n) {
      return;
    }
    k = k % n;
    int[] temp = new int[k];

    System.arraycopy(nums, 0, temp, 0, k);

    int i = 0;
    for (; i < n - k; i++) {
      nums[i] = nums[i + k];
    }

    for (int j = 0; i < n; i++, j++) {
      nums[i] = temp[j];
    }
  }
}
