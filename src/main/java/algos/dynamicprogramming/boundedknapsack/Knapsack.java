package algos.dynamicprogramming.boundedknapsack;

public class Knapsack {

    public static void main(String[] args) {
        TopDown topDown = new TopDown();
        BottomUp bottomUp = new BottomUp();

        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};
        System.out.println("Total knapsack profit (BU) ---> " + bottomUp.solve(profits, weights, 7));
        System.out.println("Total knapsack profit (TD) ---> " + topDown.solve(profits, weights, 7));

        System.out.println("Total knapsack profit (BU) ---> " + bottomUp.solve(profits, weights, 6));
        System.out.println("Total knapsack profit (TD) ---> " + topDown.solve(profits, weights, 6));
    }

    public static class TopDown {
        public int solve(int[] profits, int[] weights, int capacity) {
            int n = profits.length;
            Integer [][] dp = new Integer[n][capacity + 1];
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
                        solve(profits, weights, capacity - weights[index], index + 1, dp);
            }
            dp[index][capacity] = Math.max(profit1, profit2);
            return dp[index][capacity];
        }
    }

    public static class BottomUp {
        public int solve(int[] profits, int[] weights, int capacity) {
            // basic checks
            if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
                return 0;

            int n = profits.length;
            int[][] dp = new int[n][capacity + 1];

            // populate the capacity=0 columns, with '0' capacity we have '0' profit
            for (int i = 0; i < n; i++)
                dp[i][0] = 0;

            // if we have only one weight, we will take it if it is not more than the capacity
            for (int c = 0; c <= capacity; c++) {
                if (weights[0] <= c)
                    dp[0][c] = profits[0];
            }

            // process all sub-arrays for all the capacities
            for (int i = 1; i < n; i++) {
                for (int c = 1; c <= capacity; c++) {
                    int profit1 = 0, profit2 = 0;
                    // include the item, if it is not more than the capacity
                    if (weights[i] <= c)
                        profit1 = profits[i] + dp[i - 1][c - weights[i]];
                    // exclude the item
                    profit2 = dp[i - 1][c];
                    // take maximum
                    dp[i][c] = Math.max(profit1, profit2);
                }
            }

            // maximum profit will be at the bottom-right corner.
            return dp[n - 1][capacity];
        }
    }



}
