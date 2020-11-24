package algos.dynamicprogramming.boundedknapsack;

public class CountSubsetsMatchingSum {

    public static int solveBottomUp(int[] arr, int sum) {
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
            dp[0][s] = arr[0] == s ? 1: 0;
        }

        for (int i = 1; i < n; i++) {
            for (int s = 1; s <= sum; s++) {
                dp[i][s] = dp[i-1][s];
                if (arr[i] <= sum) {
                    dp[i][s] = dp[i-1][s] + dp[i-1][s - arr[i]];
                }
            }
        }
        return dp[n - 1][sum];
    }
}
