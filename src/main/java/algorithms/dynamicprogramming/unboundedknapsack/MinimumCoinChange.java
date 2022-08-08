package algorithms.dynamicprogramming.unboundedknapsack;

public class MinimumCoinChange {

    public static void main(String[] args) {
        int[] deno = {1, 5, 6, 8};
        TopDown topDown = new TopDown();
        System.out.println(topDown.solve(deno, 11));
        BottomUp bottomUp = new BottomUp();
        System.out.println(bottomUp.solve(deno, 11));
    }

    public static class TopDown {
        public int solve(int[] deno, int amount) {
            if (deno == null || deno.length == 0 || amount < 0) {
                return -1;
            }

            int n = deno.length;
            int[][] dp = new int[n][amount + 1];
            for (int i = 0; i < n; i++) {
                for (int c = 1; c <= amount; c++) {
                    dp[i][c] = Integer.MAX_VALUE;
                }
            }

            return solve(deno, amount, 0, dp);
        }

        private int solve(int[] deno, int amount, int index, int[][] dp) {
            if (amount <= 0) {
                return 0;
            }

            if (index >= deno.length) {
                return Integer.MAX_VALUE;
            }

            if (dp[index][amount] != Integer.MAX_VALUE) {
                return dp[index][amount];
            }

            int count1 = Integer.MAX_VALUE;
            if (deno[index] <= amount) {
                count1 = solve(deno, amount - deno[index], index, dp);
                if (count1 != Integer.MAX_VALUE) {
                    count1 = 1 + count1;
                }
            }
            int count2 = solve(deno, amount, index + 1, dp);
            dp[index][amount] = Math.min(count1, count2);
            return dp[index][amount];
        }
    }

    public static class BottomUp {
        public int solve(int[] deno, int amount) {
            if (deno == null || deno.length == 0 || amount < 0) {
                return -1;
            }

            int n = deno.length;
            int[][] dp = new int[n][amount + 1];
            for (int i = 0; i < n; i++) {
                for (int c = 1; c <= amount; c++) {
                    dp[i][c] = Integer.MAX_VALUE;
                }
            }

            for (int i = 0; i < n; i++) {
                for (int c = 1; c <= amount; c++) {
                    if (i > 0) {
                        dp[i][c] = dp[i - 1][c];
                    }
                    if (deno[i] <= c) {
                        if (dp[i][c - deno[i]] != Integer.MAX_VALUE) {
                            dp[i][c] = Math.min(dp[i][c], 1 + dp[i][c - deno[i]]);
                        }
                    }
                }
            }
            return dp[n - 1][amount];
        }
    }
}
