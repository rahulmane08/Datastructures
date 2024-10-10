package leetcode.arrays.easy;

public class RemoveElement {
  public int removeElement(int[] nums, int val) {
    if (nums == null) {
      return -1;
    }
    int start = 0;
    int end = nums.length - 1;
    while (start <= end) {
      if (nums[start] != val) {
        start++;
        continue;
      }

      if (nums[end] == val) {
        end--;
        continue;
      }

      swap(nums, start, end);
      start++;
      end--;
    }
    return end + 1;
  }

  void swap(int[] nums, int i, int j) {
    if (i != j) {
      int temp = nums[i];
      nums[i] = nums[j];
      nums[j] = temp;
    }
  }
}
