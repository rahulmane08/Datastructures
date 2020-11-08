package algos.dynamicprogramming;

public class LongestCommonSubsequence {

    private int length = 0;
    private String subsequence = "";

    public LongestCommonSubsequence(String str1, String str2) {
        solve(str1, str2);
    }

    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence("longest", "stone");
        System.out.printf("Length = %s, Subsequence = %s%n", lcs.getLength(), lcs.getSubsequence());
    }

    private void solve(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return;
        }

        int m = str1.length();
        int n = str2.length();
        int[][] lcs = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    lcs[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    lcs[i][j] = 1 + lcs[i - 1][j - 1];
                } else {
                    lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
                }
            }
        }
        this.length = lcs[m][n];
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (lcs[i - 1][j] == lcs[i][j]) {
                i--;
            } else if (lcs[i][j - 1] == lcs[i][j]) {
                j--;
            } else if (1 + lcs[i - 1][j - 1] == lcs[i][j]) {
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
