package algorithms.backtracking;

public class SubsetSum {

  public static boolean solve(int[] weights, int sum) {
    if (weights == null) {
      return false;
    }
    int totalSum = 0;
    for (int i : weights) {
      totalSum += i;
    }
    int N = weights.length;
    int[] solution = new int[N];
    if (solve(solution, weights, 0, 0, totalSum, sum, N)) {
      System.out.println("Found one subset");
      String result = "";
      for (int i = 0; i < N; i++) {
        if (solution[i] == 1) {
          result += " " + weights[i] + " ";
        }
      }
      System.out.printf("{%s} %n", result);
      return true;
    }
    return false;
  }

  private static boolean solve(int[] solution, int[] weights, int start, int currSum, int totalSum, int sum, int N) {
    if (currSum > sum) {
      return false;
    }

    if (currSum == sum) {
      return true;
    }

    for (int i = start; i < N; i++) {
      solution[i] = 1;
      if (solve(solution, weights, start + 1, currSum + weights[i], totalSum - weights[i], sum, N)) {
        return true;
      }
      solution[i] = 0;
      return solve(solution, weights, start + 1, currSum, totalSum - weights[i], sum, N);
    }
    return false;
  }
}
