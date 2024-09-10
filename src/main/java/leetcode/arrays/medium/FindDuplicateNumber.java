package leetcode.arrays.medium;

public class FindDuplicateNumber {
  public int findDuplicate(int[] nums) {
    int dupe = -1;
    for (int i = 0; i < nums.length; i++) {
      int j = Math.abs(nums[i]);
      if (nums[j] < 0) {
        dupe = j;
        break;
      } else {
        nums[j] = -nums[j];
      }
    }
    return dupe;
  }
}
