package leetcode.arrays.easy;

public class RemoveElement {
  public int removeElement(int[] nums, int val) {
    if (nums == null) {
      return -1;
    }
    int i = 0;
    int j = nums.length - 1;
    while (i <= j) {
      if (nums[i] != val) {
        i++;
        continue;
      }

      if (nums[j] == val) {
        j--;
        continue;
      }

      swap(nums, i, j);
      i++;
      j--;
    }
    return j + 1;
  }

  void swap(int[] nums, int i, int j) {
    if (i != j) {
      int temp = nums[i];
      nums[i] = nums[j];
      nums[j] = temp;
    }
  }
}
