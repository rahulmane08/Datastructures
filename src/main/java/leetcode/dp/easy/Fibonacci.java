package leetcode.dp.easy;

import java.util.Map;

public class Fibonacci {
  public static void main(String[] args) {
    Fibonacci util = new Fibonacci();
    for (int i = 2; i <= 5; i++) {
      System.out.println(util.fib(i));
    }
  }

  public int fib(int n) {
    return bottomUp(n);
  }

  /**
   * T(n) = 2T(n-1) + 1;
   * O(n) = 2^n
   *
   * @param n
   * @return
   */
  private int topDown(int n, Map<Integer, Integer> dp) {
    if (n < 2) {
      return n;
    }
    if (!dp.containsKey(n)) {
      dp.put(n, topDown(n - 1, dp) + topDown(n - 2, dp));
    }
    return dp.get(n);
  }

  private int bottomUp(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 0;
    dp[1] = 1;
    for (int i = 2; i < dp.length; i++) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
  }
}
