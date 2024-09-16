package leetcode.backtracking.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    find(nums, subsets, new ArrayList<>(), 0);
    return subsets;
  }

  private void find(int[] nums, List<List<Integer>> subsets, List<Integer> curr, int index) {
    if (index == nums.length) {
      subsets.add(new ArrayList<>(curr));
      return;
    }
    // include
    curr.add(nums[index]);
    find(nums, subsets, curr, index + 1);
    curr.remove(curr.size() - 1);
    find(nums, subsets, curr, index + 1);
  }
}