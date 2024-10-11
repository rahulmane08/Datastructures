package leetcode.greedy.hard;

public class MinimumIncrementsOnSubarraysToFormTargetArray {
  public int minNumberOperations(int[] target) {
    int cnt = 0, prev = 0;

    for (int i = 0; i < target.length; i++) {
      if (target[i] > prev) {
        cnt += (target[i] - prev);
      }
      prev = target[i];
    }
    return cnt;
  }

  public static void main(String[] args) {
    MinimumIncrementsOnSubarraysToFormTargetArray util = new MinimumIncrementsOnSubarraysToFormTargetArray();
    System.out.println(util.minNumberOperations(new int[] {3, 1, 5}));
  }
}
