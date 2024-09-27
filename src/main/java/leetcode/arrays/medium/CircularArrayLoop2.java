package leetcode.arrays.medium;

import java.util.HashSet;

public class CircularArrayLoop2 {
  public boolean circularArrayLoop(int[] nums) {
    int n = nums.length;
    int slow = 0;
    int fast = 0;
    HashSet<Integer> visited = new HashSet<>();
    for (int i = 0; i < n; i++) {
      boolean direction = nums[i] >= 0;
      if (!visited.contains(i)) {
        while (true) {
          slow = getNextIndex(nums, slow, direction);
          if (slow == -1) {
            break; // cycle found
          }

          fast = getNextIndex(nums, fast, direction);
          if (fast == -1) {
            break; // cycle found
          }

          fast = getNextIndex(nums, fast, direction);
          if (fast == -1) {
            break; // cycle found
          }

          if (fast == slow) {
            return true;
          }
          visited.add(slow);
          visited.add(fast);
        }
      }
    }
    return false;
  }

  private int getNextIndex(int[] nums, int index, boolean direction) {
    boolean currDirection = nums[index] >= 0;
    if (direction != currDirection) {
      return -1;
    }
    int n = nums.length;
    int nextIndex = (index + nums[index]) % n;
    if (nextIndex < 0) {
      nextIndex += n;
    }
    if (index == nextIndex) {
      return -1; // self cycle.
    }
    return nextIndex;
  }

  public static void main(String[] args) {
    CircularArrayLoop2 util = new CircularArrayLoop2();
    System.out.println(util.circularArrayLoop(new int[] {-1, -2, -3, -4, -5, 6}));
  }
}
