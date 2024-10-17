package leetcode.dp.medium;

import java.util.Arrays;

public class CoinChange2 {
  public int change(int amount, int[] coins) {
    int[][] dp = new int[coins.length][amount + 1];
    for (int i = 0; i < coins.length; i++) {
      Arrays.fill(dp[i], -1);
    }
    return topDown(coins, amount, 0, dp);
  }

  private int topDown(int[] coins, int amount, int index, int[][] dp) {
    if (amount == 0) {
      return 1;
    }
    if (amount < 0 || index == coins.length) {
      return 0;
    }
    if (dp[index][amount] == -1) {
      dp[index][amount] =
          topDown(coins, amount - coins[index], index, dp) + topDown(coins, amount, index + 1, dp);
    }
    return dp[index][amount];
  }

  public static void main(String[] args) {
    CoinChange2 util = new CoinChange2();
    System.out.println(util.change(5, new int[] {1, 2, 5}));
    System.out.println(util.change(3, new int[] {1, 2, 5}));
  }
}
