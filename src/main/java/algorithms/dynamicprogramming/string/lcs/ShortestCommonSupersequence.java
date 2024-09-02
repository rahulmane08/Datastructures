package algorithms.dynamicprogramming.string.lcs;

public class ShortestCommonSupersequence {

  public static class TopDown {
    public int solve(String str1, String str2) {
      if (str1 == null && str2 == null) {
        return 0;
      }
      Integer[][] dp = new Integer[str1.length()][str2.length()];
      return solve(str1, str2, 0, 0, dp);
    }

    private int solve(String str1, String str2, int index1, int index2, Integer[][] dp) {
      if (index1 == str1.length()) {
        return str2.length() - index2;
      }

      if (index2 == str2.length()) {
        return str1.length() - index1;
      }

      if (dp[index1][index2] != null) {
        return dp[index1][index2];
      }

      if (str1.charAt(index1) == str2.charAt(index2)) {
        return 1 + solve(str1, str2, index1 + 1, index2 + 1, dp);
      }

      int left = 1 + solve(str1, str2, index1 + 1, index2, dp);
      int right = 1 + solve(str1, str2, index1, index2 + 1, dp);
      dp[index1][index2] = 1 + Math.min(left, right);
      return dp[index1][index2];
    }
  }

  public static class BottomUp {
    private final String supersequence = "";
    private int length = 0;

    public BottomUp(String str1, String str2) {
      solve(str1, str2);
    }

    private void solve(String str1, String str2) {
      if (str1 == null && str2 == null) {
        return;
      }

      int m = str1.length();
      int n = str2.length();
      int[][] dp = new int[m + 1][n + 1];

      for (int j = 1; j <= n; j++) {
        dp[0][j] = j;
      }

      for (int i = 1; i <= m; i++) {
        dp[i][0] = i;
      }

      for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
          if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
            dp[i][j] = 1 + dp[i - 1][j - 1];
          } else {
            dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
          }
        }
      }

      this.length = dp[m][n];
    }
  }
}
