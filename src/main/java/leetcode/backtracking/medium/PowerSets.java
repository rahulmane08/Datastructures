package leetcode.backtracking.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/subsets/
 */
public class PowerSets {
  public static void main(String[] args) {
    PowerSets util = new PowerSets();
    System.out.println(util.subsets(new int[] {1, 2, 3}));
  }

  public List<List<Integer>> subsets(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> subsets = new ArrayList<>();
    find(nums, subsets, new Stack<>(), 0);
    return subsets;
  }

  private void find(int[] nums, List<List<Integer>> subsets, Stack<Integer> stack, int index) {
    if (index == nums.length) {
      subsets.add(new ArrayList<>(stack));
      return;
    }
    // include
    stack.push(nums[index]);
    find(nums, subsets, stack, index + 1);
    stack.pop();
    find(nums, subsets, stack, index + 1);
  }
}
