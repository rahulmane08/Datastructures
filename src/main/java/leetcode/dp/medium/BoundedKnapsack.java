package leetcode.dp.medium;

/**
 * Let’s say you have a knapsack capacity of 5 and a list of items with weights and values as follows:
 * <p>
 * weights = [1, 2, 3, 5]
 * <p>
 * values = [10, 5, 4, 8]
 * <p>
 * There are four ways of storing items in the knapsack,
 * such that the combined weight of stored items is less than or equal to the knapsack’s capacity.
 * <p>
 * Item of weight 1 and weight 2, with a total value of 15.
 * Item of weight 1 and weight 3, with a total value of 14.
 * Item of weight 2 and weight 3, with a total value of 9.
 * Item of weight 5, with a value of 8.
 * Though all of the combinations described above are valid,
 * we need to select the one with the maximum value.
 * Hence, we will select items with weights 1 and 2, as they give us the maximum value of 15.
 * <p>
 * Problem: Find the combination of items that can fit in the knapsack of capacity = 5, such that sum of prices is max.
 */
public class BoundedKnapsack {
  public static void main(String[] args) {
    int[] weights = {1, 2, 3, 5};
    int[] costs = {10, 5, 4, 8};
    BoundedKnapsack util = new BoundedKnapsack();
    System.out.println(util.findMax(weights, costs, 5));
    System.out.println(util.findMax(weights, costs, 10));
    System.out.println(util.findMax(weights, costs, 10));
  }

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

  /**
   * T(n) = O(n)
   *
   * @param weights
   * @param costs
   * @param capacity
   * @param index
   * @param dp
   * @return
   */
  private int topDown(int[] weights, int[] costs, int capacity, int index, int[][] dp) {
    if (index == weights.length) {
      return 0;
    }
    if (dp[index][capacity] == -1) {
      int leftMax = 0;
      if (weights[index] < capacity) {
        leftMax = costs[index] + topDown(weights, costs, capacity - weights[index], index + 1, dp); // include.
      }
      int rightMax = topDown(weights, costs, weights[index], index + 1, dp); // exclude
      dp[index][capacity] = Math.max(leftMax, rightMax);
    }
    return dp[index][capacity];
  }
}