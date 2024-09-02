package algorithms.dynamicprogramming.boundedknapsack;

public class SubsetWithEqualSum {

  public static void main(String[] args) {
    SubsetWithEqualSum ps = new SubsetWithEqualSum();
    int[] num = {1, 2, 3, 4};
    System.out.println(ps.solveBottomUp(num));
    System.out.println(ps.solveTopDown(num));
    num = new int[] {1, 1, 3, 4, 7};
    System.out.println(ps.solveBottomUp(num));
    System.out.println(ps.solveTopDown(num));
    num = new int[] {2, 3, 4, 6};
    System.out.println(ps.solveBottomUp(num));
    System.out.println(ps.solveTopDown(num));
  }

  public boolean solveBottomUp(int[] num) {
    int n = num.length;
    // find the total sum
    int sum = 0;
    for (int i = 0; i < n; i++) {
      sum += num[i];
    }

    // if 'sum' is a an odd number, we can't have two subsets with same total
    if (sum % 2 != 0) {
      return false;
    }

    // we are trying to find a subset of given numbers that has a total sum of ‘sum/2’.
    sum /= 2;

    boolean[][] dp = new boolean[n][sum + 1];

    // populate the sum=0 column, as we can always have '0' sum without including any element
    for (int i = 0; i < n; i++) {
      dp[i][0] = true;
    }

    // with only one number, we can form a subset only when the required sum is equal to its value
    for (int s = 1; s <= sum; s++) {
      dp[0][s] = (num[0] == s);
    }

    // process all subsets for all sums
    for (int i = 1; i < n; i++) {
      for (int s = 1; s <= sum; s++) {
        // if we can get the sum 's' without the number at index 'i'
        if (dp[i - 1][s]) {
          dp[i][s] = dp[i - 1][s];
        } else if (s >= num[i]) { // else if we can find a subset to get the remaining sum
          dp[i][s] = dp[i - 1][s - num[i]];
        }
      }
    }

    // the bottom-right corner will have our answer.
    return dp[n - 1][sum];
  }

  public boolean solveTopDown(int[] num) {
    int n = num.length;
    // find the total sum
    int sum = 0;
    for (int i = 0; i < n; i++) {
      sum += num[i];
    }

    // if 'sum' is a an odd number, we can't have two subsets with same total
    if (sum % 2 != 0) {
      return false;
    }

    Boolean[][] dp = new Boolean[num.length][sum / 2 + 1];
    return solveRecursively(num, sum / 2, 0, dp);
  }

  private boolean solveRecursively(int[] num, int sum, int index, Boolean[][] dp) {
    if (sum == 0) {
      return true;
    }

    if (index >= num.length) {
      return false;
    }

    if (dp[index][sum] != null) {
      return dp[index][sum];
    }

    if (num[index] <= sum) {
      if (solveRecursively(num, sum - num[index], index + 1, dp)) {
        dp[index][sum] = true;
        return true;
      }
    }
    dp[index][sum] = solveRecursively(num, sum, index + 1, dp);
    return dp[index][sum];
  }
}
