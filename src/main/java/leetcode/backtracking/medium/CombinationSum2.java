package leetcode.backtracking.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/combination-sum-ii/
 * Constraints:
 * <p>
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 * <p>
 * Decreasing function : 2T(n-1) + 1
 * Case 2.2 : a^n/b = 2^n
 */
public class CombinationSum2 {
  public static void main(String[] args) {
    CombinationSum2 util = new CombinationSum2();
    System.out.println(util.combinationSum(new int[] {10, 1, 2, 7, 6, 1, 5}, 8));
  }

  /**
   * T(n) = 2T(n - 1) + 1 , a = 2, b = 1
   * O(n) = 2 ^ n , case 1.3 decreasing function.
   *
   * @param candidates
   * @param target
   * @return
   */
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    Arrays.sort(candidates);
    List<List<Integer>> output = new ArrayList<>();
    // combinationSum(candidates, output, new Stack<>(), 0, target);
    combinationSum2(candidates, output, new HashSet<>(), "", 0, target);
    return output;
  }

  private void combinationSum(int[] candidates, List<List<Integer>> output, Stack<Integer> current, int index,
                              int sum) {
    if (sum == 0) {
      output.add(new ArrayList<>(current));
      return;
    }

    // sum goes to negative but more elements in the array.
    // sum is positive but array is exhausted.
    if (sum < 0 || index >= candidates.length) {
      return;
    }
    current.push(candidates[index]);
    combinationSum(candidates, output, current, index, sum - candidates[index]); // include curr
    current.pop(); // backtrack
    combinationSum(candidates, output, current, index + 1, sum); // exclude curr
  }

  private void combinationSum2(int[] candidates,
                               List<List<Integer>> output,
                               HashSet<String> answers,
                               String answer,
                               int index,
                               int sum) {
    if (sum == 0 && !answers.contains(answer)) {
      answers.add(answer);
      String[] digits = answer.split(",");
      List<Integer> solution = new ArrayList<>();
      for (int i = 0; i < digits.length; i++) {
        solution.add(Integer.parseInt(digits[i]));
      }
      output.add(solution);
    }

    if (sum < 0 || index == candidates.length) {
      return;
    }

    // include
    combinationSum2(candidates, output, answers,
        answer + candidates[index] + ",", index + 1, sum - candidates[index]);
    // exclude
    combinationSum2(candidates, output, answers, answer, index + 1, sum);
  }
}
