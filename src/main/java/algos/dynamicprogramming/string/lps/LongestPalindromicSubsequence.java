package algos.dynamicprogramming.string.lps;

public class LongestPalindromicSubsequence {

    public int solveTopDown(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        int n = str.length();
        Integer[][] dp = new Integer[n][n];
        return solveRecursively(str, 0, n-1, dp);
    }

    private int solveRecursively(String str, int start, int end, Integer[][] dp) {
        if (start > end) {
            return 0;
        }

        if (start == end) {
            return 1; //single character is always a palindrome
        }

        if (dp[start][end] != null) {
            return dp[start][end];
        }

        if(str.charAt(start) == str.charAt(end)) {
            dp[start][end] = 2 + solveRecursively(str, start + 1, end - 1, dp);
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
                    } else {
                        dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    }
                }
            }
        }
        return dp[0][n-1];
    }

    public static void main(String[] args) {
        LongestPalindromicSubsequence util = new LongestPalindromicSubsequence();
        String str = "babccabaaaaaaaaaaaa";
        System.out.println(util.solveTopDown(str));
        System.out.println(util.solveBottomUp(str));
    }
}
