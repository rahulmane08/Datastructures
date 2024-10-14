package leetcode.dp.easy;

import java.util.Map;

public class ClimbStairs {
  public static void main(String[] args) {
    ClimbStairs util = new ClimbStairs();
    System.out.println(util.climbStairs(0));
  }

  public int climbStairs(int n) {
    // return topDown(n, new HashMap<>());
    return bottomUp(n);
  }

  private int topDown(int n, Map<Integer, Integer> dp) {
    if (n == 0) {
      return 1; // reached the top.
    }

    if (n < 0) {
      return 0;
    }

    if (!dp.containsKey(n)) {
      dp.put(n, topDown(n - 1, dp) + topDown(n - 2, dp));
    }
    return dp.get(n);
  }

  private int bottomUp(int n) {
    if (n == 0) {
      return 1;
    }

    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
  }
}
