package leetcode.greedy.hard;

public class MinimumIncrementsOnSubarraysToFormTargetArray {
  public static void main(String[] args) {
    MinimumIncrementsOnSubarraysToFormTargetArray util = new MinimumIncrementsOnSubarraysToFormTargetArray();
    System.out.println(util.minNumberOperations(new int[] {3, 1, 5}));
  }

  public int minNumberOperations(int[] target) {
    int cnt = 0, prev = 0;

    for (int i = 0; i < target.length; i++) {
      int current = target[i];
      if (current > prev) {
        cnt += (current - prev);
      }
      prev = current;
    }
    return cnt;
  }
}
