package leetcode.dp.medium;

import java.util.Arrays;

public class MaximumRibbonCut {
  public int maxRibbonCuts(int[] ribbons, int length) {
    int[][] dp = new int[ribbons.length][length + 1];
    for (int i = 0; i < ribbons.length; i++) {
      Arrays.fill(dp[i], -1);
    }

    return topDown(ribbons, length, 0, dp);
  }

  /**
   * [5,3,8] , length = 13
   *
   * @param ribbons
   * @param length
   * @param index
   * @param dp
   * @return
   */
  private int topDown(int[] ribbons, int length, int index, int[][] dp) {
    if (length == 0) {
      return 0;
    }

    if (index == ribbons.length || length < 0) {
      return -1;
    }

    if (dp[index][length] == -1) {
      int left = topDown(ribbons, length - ribbons[index], index, dp);;
      int right = topDown(ribbons, length, index + 1, dp);
      dp[index][length] = 1 + Math.max(left, right);
    }
    return dp[index][length];
  }

  public static void main(String[] args) {
    MaximumRibbonCut util = new MaximumRibbonCut();
    System.out.println(util.maxRibbonCuts(new int[] {1, 2}, 4));
  }
}
