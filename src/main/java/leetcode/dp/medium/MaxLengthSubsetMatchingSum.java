package leetcode.dp.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class MaxLengthSubsetMatchingSum {
  public List<Integer> maxLengthSubsetSum(int[] nums, int target) {
    Map<String, List<Integer>> dp = new HashMap<>();
    List<Integer> result = new ArrayList<>();
    Stack<Integer> stack = new Stack<>();
    return topDown(nums, target, 0, dp, stack);
  }

  private List<Integer> topDown(int[] nums,
                                int target,
                                int index,
                                Map<String, List<Integer>> dp,
                                Stack<Integer> stack) {
    if (target == 0) {
      return new ArrayList<>(stack);
    }

    if (target < 0 || index == nums.length) {
      return Collections.emptyList();
    }
    String key = "" + index + target;
    if (!dp.containsKey(key)) {
      List<Integer> left = new ArrayList<>();
      if (nums[index] <= target) {
        stack.push(nums[index]);
        left.addAll(topDown(nums, target - nums[index], index + 1, dp, stack));
        stack.pop();
      }
      List<Integer> right = topDown(nums, target, index + 1, dp, stack);
      if (left.size() > right.size()) {
        dp.put(key, left);
      } else {
        dp.put(key, right);
      }
    }
    return dp.get(key);
  }

  public static void main(String[] args) {
    int[] nums = {1, 1, 2, 3, 4, 7};
    MaxLengthSubsetMatchingSum util = new MaxLengthSubsetMatchingSum();
    System.out.println(util.maxLengthSubsetSum(nums, 7));
    System.out.println(util.maxLengthSubsetSum(nums, 100));
  }
}
