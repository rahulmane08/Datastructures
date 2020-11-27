package algos.dynamicprogramming.unboundedknapsack;

public class UnboundedKnapsack {

    public static void main(String[] args) {
        TopDown topDown = new TopDown();
        System.out.println(topDown.solve(new int[]{15, 20, 50}, new int[]{1, 2, 3}, 5));
        BottomUp bottomUp = new BottomUp();
        System.out.println(bottomUp.solve(new int[]{15, 20, 50}, new int[]{1, 2, 3}, 5));
    }

    public static class TopDown {
        public int solve(int[] profits, int[] weights, int capacity) {
            if (profits == null || profits.length == 0 || weights == null || weights.length == 0) {
                return 0;
            }
            int n = profits.length;
            Integer[][] dp = new Integer[n][capacity + 1];
            return solve(profits, weights, capacity, 0, dp);
        }

        private int solve(int[] profits, int[] weights, int capacity, int index, Integer[][] dp) {
            if (capacity <= 0 || index >= profits.length) {
                return 0;
            }

            if (dp[index][capacity] != null) {
                return dp[index][capacity];
            }

            int profit1 = 0, profit2 = 0;

            profit1 = solve(profits, weights, capacity, index + 1, dp);
            if (weights[index] <= capacity) {
                profit2 = profits[index] +
                        solve(profits, weights, capacity - weights[index], index, dp);
            }
            dp[index][capacity] = Math.max(profit1, profit2);
            return dp[index][capacity];
        }
    }

    public static class BottomUp {
        public int solve(int[] profits, int[] weights, int capacity) {
            if (profits == null || profits.length == 0 || weights == null || weights.length == 0) {
                return 0;
            }

            int n = profits.length;
            int[][] dp = new int[n][capacity + 1];

            for (int i = 0; i < n; i++) {
                for (int c = 1; c <= capacity; c++) {
                    int profit1 = 0, profit2 = 0;
                    if (i > 0) {
                        profit1 = dp[i - 1][c];
                    }

                    if (weights[i] <= c) {
                        profit2 = profits[i] + dp[i][c - weights[i]];
                    }
                    dp[i][c] = Math.max(profit1, profit2);
                }
            }
            return dp[n - 1][capacity];
        }
    }
}
