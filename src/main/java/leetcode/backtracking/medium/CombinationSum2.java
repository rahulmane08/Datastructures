package leetcode.backtracking.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * https://leetcode.com/problems/combination-sum-ii/
 * Constraints:
 * <p>
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 */
public class CombinationSum2 {
  public static void main(String[] args) {
    CombinationSum2 util = new CombinationSum2();
    System.out.println(util.combinationSum(new int[] {10, 1, 2, 7, 6, 1, 5}, 8));
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
    combinationSum(candidates, output, new HashSet<>(), new Stack<>(), 0, target);
    return output;
  }

  private void combinationSum(int[] candidates,
                              List<List<Integer>> output,
                              Set<String> existing,
                              Stack<Integer> current, int index, int sum) {
    if (sum == 0) {
      ArrayList<Integer> copy = new ArrayList<>(current);
      if (exists(existing, copy)) {
        output.add(copy);
      }
      return;
    }
    if (sum < 0 || index >= candidates.length) {
      return;
    }
    current.push(candidates[index]);
    combinationSum(candidates, output, existing, current, index + 1, sum - candidates[index]);
    current.pop();
    combinationSum(candidates, output, existing, current, index + 1, sum);
  }

  private boolean exists(Set<String> existing, List<Integer> current) {
    current.sort(Integer::compareTo);
    String s = "";
    for (int x : current) {
      s += x;
    }
    return existing.add(s);
  }
}
