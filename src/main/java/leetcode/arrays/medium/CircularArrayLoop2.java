package leetcode.arrays.medium;

import java.util.HashSet;

public class CircularArrayLoop2 {
  public boolean circularArrayLoop(int[] nums) {
    int n = nums.length;
    int slow = 0;
    int fast = 0;
    HashSet<Integer> visited = new HashSet<>();
    for (int i = 0; i < n; i++) {
      if (!visited.contains(i)) {
        while (true) {
          slow = getNextIndex(nums, slow);
          if (slow == -1) {
            break; // cycle found
          }

          fast = getNextIndex(nums, fast);
          if (fast == -1) {
            return false; // cycle found
          }

          fast = getNextIndex(nums, fast);
          if (fast == -1) {
            return false; // cycle found
          }

          if (fast == slow) {
            break;
          }
          visited.add(slow);
          visited.add(fast);
        }
      }
    }
    return true;
  }

  private int getNextIndex(int[] nums, int index) {
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
    System.out.println(util.circularArrayLoop(new int[] {-2, 1, -1, -2, -2}));
  }
}
