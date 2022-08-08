package algorithms.dynamicprogramming.lis;

import java.util.HashMap;
import java.util.Map;

public class LongestIncreasingSubsequence {
    private String subsequence = "";

    public static void main(String[] args) {
        int[] arr = {4, 1, 3, 6, 2, 10};
        LongestIncreasingSubsequence util = new LongestIncreasingSubsequence();
        System.out.println(util.solveBottomUp(arr));
        System.out.println(util.solveTopDown(arr));
    }

    public int solveTopDown(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        Map<String, Integer> dp = new HashMap<>();
        return solveRecursively(arr, -1, 0, dp);
    }

    private int solveRecursively(int[] arr, int prev, int curr, Map<String, Integer> dp) {
        if (curr == arr.length) {
            return 0;
        }

        String key = prev + "-" + curr;
        Integer length = dp.get(key);
        if (length != null) {
            return length;
        }

        int c1 = 0;
        if (prev == -1 || arr[prev] <= arr[curr]) {
            subsequence += arr[curr];
            c1 = 1 + solveRecursively(arr, curr, curr + 1, dp);
        }
        int c2 = solveRecursively(arr, prev, curr + 1, dp);
        length = Math.max(c1, c2);
        dp.put(key, length);
        return length;
    }

    public int solveBottomUp(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;
        int[] dp = new int[n];
        int maxLength = 1;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] <= arr[i]) {
                    dp[i] = 1 + dp[j];
                    maxLength = Math.max(maxLength, dp[i]);
                }
            }
        }
        return maxLength;
    }
}
