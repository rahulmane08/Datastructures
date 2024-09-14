package leetcode.backtracking.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayPermutations {
  public static void main(String[] args) {
    ArrayPermutations util = new ArrayPermutations();
    System.out.println(util.permute(new Integer[] {1, 2, 3}));
  }

  /**
   * T(n) = n*T(n-1) + 1
   * @param nums
   * @return
   */
  public List<List<Integer>> permute(Integer[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    permute(nums, result, 0, nums.length - 1);
    return result;
  }

  private void permute(Integer[] nums, List<List<Integer>> result, int left, int right) {
    if (left == right) {
      result.add(Arrays.asList(nums));
      return;
    }

    for (int i = left; i <= right; i++) {
      // swap
      swap(nums, left, i);
      // fix the left and move ahead.
      permute(nums, result, left + 1, right);
      // undo swap
      swap(nums, left, i);
    }
  }

  private void swap(Integer[] nums, int i, int j) {
    if (i == j) {
      return;
    }
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }
}
