package algorithms.dynamicprogramming.string.lps;

public class LongestPalindromicSubsequence {

    public static void main(String[] args) {
        TopDown topDown = new TopDown();
        BottomUp bottomUp = new BottomUp();
        String str = "babccaba";
        System.out.println(topDown.solve(str));
        System.out.println(bottomUp.solve(str));
    }

    public static class TopDown {
        public int solve(String str) {
            if (str == null || str.isEmpty()) {
                return 0;
            }

            int n = str.length();
            Integer[][] dp = new Integer[n][n];
            return solve(str, 0, n - 1, dp);
        }

        private int solve(String str, int start, int end, Integer[][] dp) {
            if (start > end) {
                return 0;
            }

            if (start == end) {
                return 1; //single character is always a palindrome
            }

            if (dp[start][end] != null) {
                return dp[start][end];
            }

            if (str.charAt(start) == str.charAt(end)) {
                dp[start][end] = 2 + solve(str, start + 1, end - 1, dp);
            } else {
                int left = solve(str, start + 1, end, dp);
                int right = solve(str, start, end - 1, dp);
                dp[start][end] = Math.max(left, right);
            }
            return dp[start][end];
        }
    }

    public static class BottomUp {
        public int solve(String str) {
            if (str == null || str.isEmpty()) {
                return 0;
            }

            int n = str.length();
            int[][] dp = new int[n][n];

            for (int gap = 0; gap < n; gap++) {
                for (int i = 0, j = gap; i < n - gap; i++, j++) {
                    if (gap == 0) {
                        dp[i][j] = 1; //single character is always a palindrome
                    } else {
                        if (str.charAt(i) == str.charAt(j)) {
                            dp[i][j] = 2 + dp[i + 1][j - 1];
                        } else {
                            dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                        }
                    }
                }
            }
            return dp[0][n - 1];
        }
    }
}
