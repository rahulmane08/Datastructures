package algos.dynamicprogramming.string.lcs;

import java.util.HashMap;
import java.util.Map;

public class LongestCommonSubstring {

    public static void main(String[] args) {
        BottomUp lcs = new BottomUp("longest", "stone");
        System.out.printf("Length = %s, Substring = %s%n", lcs.getLength(), lcs.getSubstring());
    }

    public static class TopDown {
        public int solve(String str1, String str2) {
            if (str1 == null || str2 == null) {
                return 0;
            }

            int m = str1.length();
            int n = str2.length();
            Map<String, Integer> dp = new HashMap<>();
            return solve(str1, str2, 0, 0, 0, dp);
        }

        private int solve(String str1, String str2, int index1, int index2, int count, Map<String, Integer> dp) {
            if (index1 == str1.length() || index2 == str1.length()) {
                return count;
            }
            String key = index1 + "-" + index2 + "-" + count;
            Integer length = dp.get(key);

            if (length != null) {
                return length;
            }

            if (str1.charAt(index1) == str2.charAt(index2)) {
                length = 1 + solve(str1, str2, index1 + 1, index2 + 2, count + 1, dp);
            } else {
                length = Math.max(solve(str1, str2, index1 + 1, index2, count, dp),
                        solve(str1, str2, index1, index2 + 1, count, dp));
            }
            dp.put(key, length);
            return length;
        }
    }

    public static class BottomUp {
        private int length = 0;
        private String substring = "";

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
                        if (this.length < dp[i][j]) {
                            this.length = dp[i][j];
                            this.substring += str1.charAt(i - 1);
                        }
                    } else {
                        dp[i][j] = 0;
                    }
                }
            }
        }

        public int getLength() {
            return length;
        }

        public String getSubstring() {
            return substring;
        }
    }
}
