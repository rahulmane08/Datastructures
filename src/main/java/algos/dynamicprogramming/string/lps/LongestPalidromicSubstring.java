package algos.dynamicprogramming.string.lps;

public class LongestPalidromicSubstring {
    public int solveTopDown(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        int n = str.length();
        int[][] dp = new int[n][n];
        return solveRecursively(str, 0, n-1, dp);
    }

    private int solveRecursively(String str, int start, int end, int[][] dp) {
        if (start > end) {
            return 0;
        }

        if (start == end) {
            return 1; //single character is always a palindrome
        }

        if (dp[start][end] != 0) {
            return dp[start][end];
        }

        if(str.charAt(start) == str.charAt(end)) {
            if (end - start == 1) {
                // 2 character string
                dp[start][end] = 2;
            } else {
                int residueLength = solveRecursively(str, start + 1, end - 1, dp);
                if (residueLength == end - start - 1) {
                    dp[start][end] = 2 + residueLength;
                }
            }
        } else {
            int left = solveRecursively(str, start + 1, end, dp);
            int right = solveRecursively(str, start, end - 1, dp);
            dp[start][end] = Math.max(left, right);
        }
        return dp[start][end];
    }

    public int solveBottomUp(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        int n = str.length();
        int[][] dp = new int[n][n];
        int maxLength = 1;

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1; //single character is always a palindrome
        }

        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; i < n - gap; i++, j++) {
                if (gap == 0) {
                    dp[i][j] = 1;
                } else {
                    if (str.charAt(i) == str.charAt(j)) {
                        dp[i][j] = 2 + dp[i + 1][j - 1];
                    }
                }
                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        LongestPalidromicSubstring util = new LongestPalidromicSubstring();
        String str = "babccbha";
        System.out.println(util.solveTopDown(str));
        System.out.println(util.solveBottomUp(str));
    }
}
