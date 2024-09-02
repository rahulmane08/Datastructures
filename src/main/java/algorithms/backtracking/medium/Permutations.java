package algorithms.backtracking.medium;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
  public static void main(String[] args) {
    Permutations util = new Permutations();
    System.out.println(util.permute(new int[] {1, 2, 3}));
  }

  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    permute(nums, result, 0, nums.length - 1);
    return result;
  }

  private void permute(int[] nums, List<List<Integer>> result, int left, int right) {
    if (left == right) {
      addToResult(nums, result);
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

  private void addToResult(int[] nums, List<List<Integer>> result) {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      list.add(nums[i]);
    }
    result.add(list);
  }

  private void swap(int[] nums, int i, int j) {
    if (i == j) {
      return;
    }
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }
}
