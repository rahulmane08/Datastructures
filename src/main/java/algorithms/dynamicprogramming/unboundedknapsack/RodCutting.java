package algorithms.dynamicprogramming.unboundedknapsack;

import java.util.Arrays;

public class RodCutting {

  public static void main(String[] args) {
    TopDown topDown = new TopDown();
    BottomUp bottomUp = new BottomUp();

    int[] lengths = {1, 2, 3, 4, 5};
    int[] prices = {2, 6, 7, 10, 13};

    System.out.printf("TD: price = %s%n", topDown.solve(lengths, prices, 5));
    System.out.printf("BU: price = %s, solution = %s%n",
        bottomUp.solve(lengths, prices, 5), Arrays.toString(bottomUp.getSolution()));
  }

  public static class TopDown {
    private int[] solution;

    public int solve(int[] lengths, int[] prices, int totalLength) {
      // base checks
      if (totalLength <= 0 || prices.length == 0 || prices.length != lengths.length) {
        return 0;
      }

      int n = prices.length;
      Integer[][] dp = new Integer[n][totalLength + 1];
      this.solution = new int[n];
      return solve(lengths, prices, totalLength, 0, dp);
    }

    private int solve(int[] lengths, int[] prices, int totalLength, int i, Integer[][] dp) {
      if (totalLength <= 0 || i >= prices.length) {
        return 0;
      }

      if (dp[i][totalLength] != null) {
        return dp[i][totalLength];
      }

      int price1 = 0;
      if (lengths[i] <= totalLength) {
        price1 = prices[i] + solve(lengths, prices, totalLength - lengths[i], i, dp);
      }
      int price2 = solve(lengths, prices, totalLength, i + 1, dp);
      return Math.max(price1, price2);
    }
  }

  public static class BottomUp {
    private int[] solution;

    public int solve(int[] lengths, int[] prices, int totalLength) {
      // base checks
      if (totalLength <= 0 || prices.length == 0 || prices.length != lengths.length) {
        return 0;
      }

      int n = lengths.length;
      int[][] dp = new int[n][totalLength + 1];
      this.solution = new int[totalLength];

      // process all rod lengths for all prices
      for (int i = 0; i < n; i++) {
        for (int len = 1; len <= totalLength; len++) {
          int p1 = 0, p2 = 0;
          if (lengths[i] <= len) {
            p1 = prices[i] + dp[i][len - lengths[i]];
          }
          if (i > 0) {
            p2 = dp[i - 1][len];
          }
          dp[i][len] = Math.max(p1, p2);
        }
      }

      for (int i = prices.length - 1, j = totalLength; i > 0; ) {
        if (dp[i][j] != dp[i - 1][j]) {
          j = j - lengths[i];
          solution[i]++;
        } else {
          i--;
        }
        if (i == 0) {
          solution[i] = 1;
        }
      }

      // maximum price will be at the bottom-right corner.
      return dp[n - 1][totalLength];
    }

    public int[] getSolution() {
      return solution;
    }
  }
}
