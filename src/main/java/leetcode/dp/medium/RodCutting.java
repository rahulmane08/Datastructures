package leetcode.dp.medium;

import java.util.Arrays;

public class RodCutting {
  public int maxRevenue(int[] rods, int[] prices, int length) {
    int[][] dp = new int[rods.length][length + 1];
    for (int i = 0; i < rods.length; i++) {
      Arrays.fill(dp[i], -1);
    }
    return topDown(rods, prices, length, 0, dp);
  }

  /**
   * lengths = [1, 3, 4]
   * prices = [2, 7, 8]
   * length = 4
   *
   * @param rods
   * @param prices
   * @param length
   * @param index
   * @param dp
   * @return
   */
  private int topDown(int[] rods, int[] prices, int length, int index, int[][] dp) {
    if (length == 0 || index == rods.length) {
      return 0;
    }

    if (dp[index][length] == -1) {
      int left = 0;
      if (rods[index] <= left) {
        left = prices[index] + topDown(rods, prices, length - rods[index], index, dp);
      }
      int right = topDown(rods, prices, length, index + 1, dp);
      dp[index][length] = Math.max(left, right);
    }
    return dp[index][length];
  }
}
