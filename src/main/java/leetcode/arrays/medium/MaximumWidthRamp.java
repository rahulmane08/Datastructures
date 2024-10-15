package leetcode.arrays.medium;

import java.util.Stack;

public class MaximumWidthRamp {
  public int maxWidthRamp(int[] nums) {
    Stack<Integer> stack = new Stack<>();
    int maxWidth = 0;
    for (int i = 0; i < nums.length; i++) {
      if (stack.isEmpty() || nums[stack.peek()] > nums[i]) {
        stack.push(i);
      }
    }

    for (int i = nums.length - 1; i > -1; i--) {
      while (!stack.isEmpty() && nums[i] >= nums[stack.peek()]) {
        maxWidth = Math.max(maxWidth, i - stack.pop());
      }
    }
    return maxWidth;
  }
}
