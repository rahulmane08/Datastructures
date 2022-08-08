package algorithms.dynamicprogramming.boundedknapsack;

public class CountSubsetsMatchingSum {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int sum = 3;
        TopDown topDown = new TopDown();
        BottomUp bottomUp = new BottomUp();
        System.out.printf("TD: #subsets = %s %n", topDown.solve(arr, sum));
        System.out.printf("TD: #subsets = %s %n", bottomUp.solve(arr, sum));
    }

    public static class TopDown {
        public int solve(int[] arr, int sum) {
            if (arr == null) {
                return -1;
            }

            int n = arr.length;
            Integer[][] dp = new Integer[n][sum + 1];
            return solve(arr, sum, 0, dp);
        }

        private int solve(int[] arr, int sum, int i, Integer[][] dp) {
            if (sum == 0) {
                return 1;
            }

            if (i >= arr.length) {
                return 0;
            }

            if (dp[i][sum] != null) {
                return dp[i][sum];
            }

            int count1 = 0;
            if (arr[i] <= sum) {
                count1 = solve(arr, sum - arr[i], i + 1, dp);
            }
            int count2 = solve(arr, sum, i + 1, dp);
            dp[i][sum] = count1 + count2;
            return dp[i][sum];
        }
    }

    public static class BottomUp {
        public int solve(int[] arr, int sum) {
            if (arr == null) {
                return -1;
            }

            int n = arr.length;
            int[][] dp = new int[n][sum + 1];

            // subset with 0 sum can always be formed by empty set.
            for (int i = 0; i < n; i++) {
                dp[i][0] = 1;
            }

            for (int s = 0; s < sum; s++) {
                dp[0][s] = arr[0] == s ? 1 : 0;
            }

            for (int i = 1; i < n; i++) {
                for (int s = 1; s <= sum; s++) {
                    dp[i][s] = dp[i - 1][s];
                    if (arr[i] <= sum) {
                        dp[i][s] = dp[i - 1][s] + dp[i - 1][s - arr[i]];
                    }
                }
            }
            return dp[n - 1][sum];
        }
    }
}
