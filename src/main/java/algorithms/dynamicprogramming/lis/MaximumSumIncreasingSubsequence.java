package algorithms.dynamicprogramming.lis;

import java.util.HashMap;
import java.util.Map;

public class MaximumSumIncreasingSubsequence {
  public static void main(String[] args) {
    TopDown topDown = new TopDown();
    BottomUp bottomUp = new BottomUp();
    int[] arr = {4, 1, 2, 6, 10, 1, 12};
    System.out.println(topDown.solve(arr));
    System.out.println(bottomUp.solve(arr));
  }

  public static class TopDown {
    public int solve(int[] arr) {
      if (arr == null || arr.length == 0) {
        return 0;
      }
      Map<String, Integer> dp = new HashMap<>();
      return solve(arr, -1, 0, 0, dp);
    }

    private int solve(int[] arr, int prev, int curr, int sum, Map<String, Integer> dp) {
      if (curr == arr.length) {
        return sum;
      }

      String key = prev + "-" + curr + "-" + sum;
      Integer maxSum = dp.get(key);
      if (maxSum != null) {
        return maxSum;
      }

      int c1 = 0;
      if (prev == -1 || arr[prev] <= arr[curr]) {
        c1 = solve(arr, curr, curr + 1, sum + arr[curr], dp);
      }
      int c2 = solve(arr, prev, curr + 1, sum, dp);
      maxSum = Math.max(c1, c2);
      dp.put(key, maxSum);
      return maxSum;
    }
  }

  public static class BottomUp {
    public int solve(int[] arr) {
      if (arr == null || arr.length == 0) {
        return 0;
      }

      int n = arr.length;
      int[] dp = new int[n];
      int maxSum = arr[0];
      for (int i = 0; i < n; i++) {
        dp[i] = arr[i];
        for (int j = 0; j < i; j++) {
          if (arr[j] <= arr[i] && dp[i] < dp[j] + arr[i]) {
            dp[i] = dp[j] + arr[i];
            maxSum = Math.max(maxSum, dp[i]);
          }
        }
      }
      return maxSum;
    }
  }
}
