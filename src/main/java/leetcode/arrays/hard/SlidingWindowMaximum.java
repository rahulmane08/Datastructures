package leetcode.arrays.hard;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/sliding-window-maximum/description/
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 */
public class SlidingWindowMaximum {
  public int[] maxSlidingWindow(int[] nums, int k) {
    int n = nums.length;
    int[] result = new int[n - k + 1];

    Deque<Integer> window = new LinkedList<>();
    int index = 0;

    // add the first element as max.
    int i = 0;

    // prepare the first window
    for (; i < k; i++) {
      while (!window.isEmpty() && nums[window.peekLast()] < nums[i]) {
        window.pollLast();
      }
      window.offer(i);
    }
    result[index++] = nums[window.peekFirst()];

    // process the remaining array.
    for (; i < n; i++) {
      // move the window , adjust max
      while (!window.isEmpty() && window.peekFirst() < i - k + 1) {
        window.pollFirst();
      }

      // add the new element to window.
      while (!window.isEmpty() && nums[window.peekLast()] < nums[i]) {
        window.pollLast();
      }
      window.offer(i);
      result[index++] = nums[window.peekFirst()];
    }
    return result;
  }

  public static void main(String[] args) {
    int [] nums = {3, 2, 1, 0, 4};
    SlidingWindowMaximum util = new SlidingWindowMaximum();
    System.out.println(Arrays.toString(util.maxSlidingWindow(nums, 3)));
    System.out.println(Arrays.toString(util.maxSlidingWindow(nums, 2)));
    System.out.println(Arrays.toString(util.maxSlidingWindow(nums, 1)));
  }
}
