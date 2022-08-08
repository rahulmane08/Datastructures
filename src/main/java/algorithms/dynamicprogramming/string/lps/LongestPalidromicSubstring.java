package algorithms.dynamicprogramming.string.lps;

public class LongestPalidromicSubstring {

    public static void main(String[] args) {
        TopDown topDown = new TopDown();
        BottomUp bottomUp = new BottomUp();
        String str = "cddpd";
        System.out.println(topDown.solve(str));
        System.out.println(bottomUp.solve(str) + "," + bottomUp.getCount());
    }

    public static class TopDown {
        public int solve(String str) {
            if (str == null || str.isEmpty()) {
                return 0;
            }

            int n = str.length();
            int[][] dp = new int[n][n];
            return solve(str, 0, n - 1, dp);
        }

        private int solve(String str, int start, int end, int[][] dp) {
            if (start > end) {
                return 0;
            }

            if (start == end) {
                return 1; //single character is always a palindrome
            }

            if (dp[start][end] != 0) {
                return dp[start][end];
            }

            if (str.charAt(start) == str.charAt(end)) {
                if (end - start == 1) {
                    // 2 character string
                    dp[start][end] = 2;
                } else {
                    int residueLength = solve(str, start + 1, end - 1, dp);
                    if (residueLength == end - start - 1) {
                        dp[start][end] = 2 + residueLength;
                    }
                }
            } else {
                int left = solve(str, start + 1, end, dp);
                int right = solve(str, start, end - 1, dp);
                dp[start][end] = Math.max(left, right);
            }
            return dp[start][end];
        }

    }

    public static class BottomUp {
        private int count;

        public int solve(String str) {
            if (str == null || str.isEmpty()) {
                return 0;
            }

            int n = str.length();
            int[][] dp = new int[n][n];
            int maxLength = 1;

            for (int gap = 0; gap < n; gap++) {
                for (int i = 0, j = gap; i < n - gap; i++, j++) {
                    if (gap == 0) {
                        count++;
                        dp[i][j] = 1; //single character is always a palindrome
                    } else {
                        if (str.charAt(i) == str.charAt(j)) {
                            count++;
                            dp[i][j] = 2 + dp[i + 1][j - 1];
                        }
                    }
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
            return maxLength;
        }

        public int getCount() {
            return count;
        }
    }
}
