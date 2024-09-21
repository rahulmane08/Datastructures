package leetcode.backtracking.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/combination-sum/description/
 */
public class CombinationSum {
  public static void main(String[] args) {
    CombinationSum util = new CombinationSum();
    System.out.println(util.combinationSum(new int[] {2, 3, 6, 7}, 7));
  }

  /**
   * T(n) = 2T(n - 1) + 1 , a = 2, b = 1
   * O(n) = 2 ^ n , case 1.3 decreasing function.
   * @param candidates
   * @param target
   * @return
   */
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> output = new ArrayList<>();
    combinationSum(candidates, output, new Stack<>(), 0, target);
    return output;
  }

  private void combinationSum(int[] candidates, List<List<Integer>> output, Stack<Integer> current, int index, int sum) {
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
}
