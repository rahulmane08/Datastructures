package leetcode.dp.medium;

import java.util.HashMap;
import java.util.Map;

public class BestTeamWithNoConflicts {
  public int bestTeamScore(int[] scores, int[] ages) {
    Map<String, Integer> dp = new HashMap<>();
    return topDown(scores, ages, -1, 0, 0, dp);
  }

  private int topDown(int[] scores, int[] ages, int prev, int curr, int sum, Map<String, Integer> dp) {
    if (curr == scores.length) {
      return sum;
    }
    String key = "" + prev + curr;
    if (!dp.containsKey(key)) {
      int leftSum = Integer.MIN_VALUE;
      if (prev == -1 || (ages[prev] < ages[curr] && scores[prev] < scores[curr]) || ages[prev] == ages[curr]) {
        leftSum = topDown(scores, ages, curr, curr + 1, sum + scores[curr], dp);
      }
      int rightSum = topDown(scores, ages, prev, curr + 1, sum, dp);
      dp.put(key, Math.max(leftSum, rightSum));
    }
    return dp.get(key);
  }

  public static void main(String[] args) {
    BestTeamWithNoConflicts util = new BestTeamWithNoConflicts();
    System.out.println(util.bestTeamScore(new int[] {4, 5, 6, 5}, new int[] {2, 1, 2, 1}));
  }
}
