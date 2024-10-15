package leetcode.greedy.medium;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MaximalScoreAfterKOperations {
  public long maxKelements(int[] nums, int k) {
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    for (int i = 0; i < nums.length; i++) {
      maxHeap.offer(nums[i]);
    }

    int ops = 0;
    int score = 0;
    while (!maxHeap.isEmpty() && ops < k) {
      int max = maxHeap.poll();
      score += max;
      max = ceil(max, 3);
      if (max != 1) {
        maxHeap.offer(max);
      }
      ops++;
    }
    return score;
  }

  private int ceil(int x, int y) {
    if (x % y == 0) {
      return x / y;
    }
    return (x / y) + 1;
  }

  public static void main(String[] args) {
    MaximalScoreAfterKOperations util = new MaximalScoreAfterKOperations();
    System.out.println(util.maxKelements(new int[] {672579538, 806947365, 854095676, 815137524}, 3));
  }
}
