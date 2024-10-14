package leetcode.dp.medium;

/**
 * sum of combinations
 */
public class RobotUniquePaths {
  private final int[][] moves = {{0, 1}, {1, 0}}; // R,D

  public static void main(String[] args) {
    RobotUniquePaths util = new RobotUniquePaths();
    System.out.println(util.uniquePaths(3, 3));
  }

  public int uniquePaths(int m, int n) {
    Integer[][] dp = new Integer[m][n];
    dp[m - 1][n - 1] = 1; // destination
    return topDown(0, 0, m, n, dp);
  }

  private int topDown(int i, int j, int m, int n, Integer[][] dp) {
    if (i == m - 1 && j == n - 1) {
      return 1;
    }
    if (dp[i][j] == null) {
      int totalPaths = 0;
      for (int[] move : moves) {
        if (isValid(i + move[0], j + move[1], m, n)) {
          totalPaths += topDown(i + move[0], j + move[1], m, n, dp);
        }
      }
      dp[i][j] = totalPaths;
    }
    return dp[i][j];
  }

  private boolean isValid(int i, int j, int m, int n) {
    return 0 <= i && i < m && 0 <= j && j < n;
  }
}
