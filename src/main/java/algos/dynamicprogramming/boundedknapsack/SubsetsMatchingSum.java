package algos.dynamicprogramming.boundedknapsack;

import java.util.Arrays;

public class SubsetsMatchingSum {

    public static class TopDown {
        boolean [] solution;

        public boolean solve(int [] arr, int sum) {
            if (arr == null) {
                return false;
            }

            Boolean [][] dp = new Boolean[arr.length][sum + 1];
            solution = new boolean[arr.length];
            return solve(arr, sum, 0, dp);
        }

        private boolean solve(int[] arr, int sum, int i, Boolean[][] dp) {
            if (sum == 0) {
                return true;
            }

            if (sum < 0 || i >= arr.length) {
                return false;
            }

            if (dp[i][sum] != null) {
                return dp[i][sum];
            }

            if (solve(arr, sum - arr[i], i + 1, dp)) {
                solution[i] = true;
                dp[i][sum] = true;
                return true;
            }

            dp[i][sum] = solve(arr, sum, i + 1, dp);
            return dp[i][sum];
        }

        public boolean[] getSolution() {
            return solution;
        }
    }

    public static class BottomUp {

        public boolean solve(int[] arr, int sum) {
            if (arr == null) {
                return false;
            }

            int n = arr.length;
            boolean [][] dp = new boolean[n][sum + 1];
            for (int i = 0; i < n; i++) {
                dp[i][0] = true;
            }

            for (int i = 1; i < n; i++) {
                for (int c = 1; c <= sum; c++) {
                    if (dp[i-1][c]) {
                        dp[i][c] = true;
                    } else if (arr[i] <= c){
                        dp[i][c] = dp[i-1][c - arr[i]];
                    }
                }
            }
            return dp[n-1][sum];
        }
    }

    public static void main(String[] args) {
        TopDown topDown = new TopDown();
        int [] arr = {1,2,3,4};
        System.out.printf("TD, possible : %s, solution: %s %n ",
                topDown.solve(arr, 7), Arrays.toString(topDown.getSolution()));

        BottomUp bottomUp = new BottomUp();
        System.out.printf("BU, possible : %s %n ",
                bottomUp.solve(arr, 7));
    }
}
