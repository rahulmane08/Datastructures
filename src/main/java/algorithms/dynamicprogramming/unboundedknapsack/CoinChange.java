package algorithms.dynamicprogramming.unboundedknapsack;

public class CoinChange {

  public static void main(String[] args) {
    int[] denominations = {1, 2, 3};
    TopDown topDown = new TopDown();
    System.out.println(topDown.solve(denominations, 5));
    BottomUp bottomUp = new BottomUp();
    System.out.println(bottomUp.solve(denominations, 5));
  }

  public static class TopDown {
    public int solve(int[] denominations, int amount) {
      if (denominations == null || denominations.length == 0) {
        return 0;
      }
      int n = denominations.length;
      Integer[][] dp = new Integer[n][amount + 1];
      return solve(denominations, amount, 0, dp);
    }

    public int solve(int[] denominations, int amount, int i, Integer[][] dp) {
      if (amount == 0) {
        return 1;
      }

      if (i >= denominations.length) {
        return 0; // no such combination is formed
      }

      if (dp[i][amount] != null) {
        return dp[i][amount];
      }

      int c1 = 0;
      if (denominations[i] <= amount) {
        c1 = solve(denominations, amount - denominations[i], i, dp);
      }
      int c2 = solve(denominations, amount, i + 1, dp);
      dp[i][amount] = c1 + c2;
      return dp[i][amount];
    }
  }

  public static class BottomUp {
    public int solve(int[] denominations, int amount) {
      if (denominations == null || denominations.length == 0) {
        return 0;
      }
      int n = denominations.length;
      int[][] dp = new int[n][amount + 1];

      // with all 1
      for (int i = 0; i < n; i++) {
        dp[i][0] = 1; // amount = 0 can be formed by selecting no counts = 1 combination irresp of deno
      }

      for (int i = 0; i < n; i++) {
        for (int s = 1; s <= amount; s++) {
          if (i > 0) {
            dp[i][s] = dp[i - 1][s];
          }
          if (denominations[i] <= s) {
            dp[i][s] += dp[i][s - denominations[i]];
          }
        }
      }

      return dp[n - 1][amount];
    }
  }
}
