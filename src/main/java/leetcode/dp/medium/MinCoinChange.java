package leetcode.dp.medium;

/**
 * https://leetcode.com/problems/coin-change/description/
 */
public class MinCoinChange {
  public int coinChange(int[] coins, int amount) {
    Integer[][] dp = new Integer[coins.length][amount + 1];
    int minCoins = solve(coins, amount, 0, 0, dp);
    return minCoins != Integer.MAX_VALUE ? minCoins : -1;
  }

  int solve(int[] coins, int amount, int index, int count, Integer[][] dp) {
    if (amount == 0) {
      return 0;
    }

    if (index == coins.length || amount < 0) {
      return Integer.MAX_VALUE;
    }

    if (dp[index][amount] == null) {
      int count1 = solve(coins, amount - coins[index], index, count + 1, dp);
      if (count1 != Integer.MAX_VALUE) {
        count1++;
      }
      int count2 = solve(coins, amount, index + 1, count, dp);
      dp[index][amount] = Math.min(count1, count2);
    }
    return dp[index][amount];
  }

  public static void main(String[] args) {
    MinCoinChange coinChange = new MinCoinChange();
    System.out.println(coinChange.coinChange(new int[] {1, 2}, 6));
  }
}
