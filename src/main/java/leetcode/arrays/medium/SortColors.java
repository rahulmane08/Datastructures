package leetcode.arrays.medium;

public class SortColors {
  public static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public void sortColors(int[] nums) {
    int p0 = 0;
    int p2 = nums.length - 1;
    int curr = 0;

    while (curr <= p2) {
      if (nums[curr] == 2) {
        swap(nums, curr, p2--);
      } else if (nums[curr] == 0) {
        swap(nums, curr++, p0++);
      } else {
        curr++;
      }
    }
  }
}
