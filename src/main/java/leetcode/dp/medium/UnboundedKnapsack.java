package leetcode.dp.medium;

public class UnboundedKnapsack {
  public int findMax(int[] weights, int[] costs, int capacity) {
    int n = weights.length;
    int[][] dp = new int[n][capacity + 1];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j <= capacity; j++) {
        dp[i][j] = -1;
      }
    }
    return topDown(weights, costs, capacity, 0, dp);
  }

  private int topDown(int[] weights, int[] costs, int capacity, int index, int[][] dp) {
    if (index == weights.length) {
      return 0;
    }
    if (dp[index][capacity] == -1) {
      int leftMax = 0;
      if (weights[index] <= capacity) {
        leftMax = costs[index] + topDown(weights, costs, capacity - weights[index], index, dp); // include.
      }
      int rightMax = topDown(weights, costs, weights[index], index + 1, dp); // exclude
      dp[index][capacity] = Math.max(leftMax, rightMax);
    }
    return dp[index][capacity];
  }

  public static void main(String[] args) {
    int[] weights = {1, 2, 3, 5};
    int[] costs = {10, 5, 4, 8};
    UnboundedKnapsack util = new UnboundedKnapsack();
    System.out.println(util.findMax(weights, costs, 5));
    System.out.println(util.findMax(weights, costs, 10));
  }
}
