package algorithms.dynamicprogramming.string.lcs;

public class LongestCommonSubsequence {
  public static void main(String[] args) {
    BottomUp bottomUp = new BottomUp("longest", "stone");
    System.out.printf("Length = %s, Subsequence = %s%n", bottomUp.getLength(), bottomUp.getSubsequence());
  }

  public static class BottomUp {
    private int length = 0;
    private String subsequence = "";

    public BottomUp(String str1, String str2) {
      solve(str1, str2);
    }

    private void solve(String str1, String str2) {
      if (str1 == null || str2 == null) {
        return;
      }

      int m = str1.length();
      int n = str2.length();
      int[][] dp = new int[m + 1][n + 1];
      for (int i = 0; i <= m; i++) {
        for (int j = 0; j <= n; j++) {
          if (i == 0 || j == 0) {
            dp[i][j] = 0;
          } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
            dp[i][j] = 1 + dp[i - 1][j - 1];
          } else {
            dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
          }
        }
      }
      this.length = dp[m][n];
      int i = m, j = n;
      while (i > 0 && j > 0) {
        if (dp[i - 1][j] == dp[i][j]) {
          i--;
        } else if (dp[i][j - 1] == dp[i][j]) {
          j--;
        } else if (1 + dp[i - 1][j - 1] == dp[i][j]) {
          this.subsequence = str1.charAt(i - 1) + this.subsequence;
          i--;
          j--;
        }
      }
    }

    public int getLength() {
      return length;
    }

    public String getSubsequence() {
      return subsequence;
    }
  }
}
