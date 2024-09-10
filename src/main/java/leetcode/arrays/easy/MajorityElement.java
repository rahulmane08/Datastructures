package leetcode.arrays.easy;

public class MajorityElement {
  public int majorityElement(int[] nums) {
    if (nums == null || nums.length == 0) {
      return -1;
    }

    int n = nums.length;
    int majorityElement = nums[0];
    int vote = 1;
    for (int i = 1; i < n; i++) {
      if (nums[i] != majorityElement) {
        if (--vote == 0) {
          vote = 1;
          majorityElement = nums[i];
        }
      } else {
        vote++;
      }
    }

    int occurence = 0;
    for (int curr : nums) {
      if (curr == majorityElement && ++occurence > n / 2) {
        return majorityElement;
      }
    }
    return -1;
  }
}
