package leetcode.backtracking.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> output = new ArrayList<>();
    combinationSum(candidates, output, new HashSet<>(), new ArrayList<>(), 0, target);
    return output;
  }
/*
  private void combinationSum1(int[] candidates,
                               int sum,
                               int index,
                               int[] combination,
                               int[] checker,
                               List<List<Integer>> result) {
    if (sum == 0 && !contains(combination, checker)) {
      addToResult(combination, result);
      update(combination, checker);
      return;
    }

    if (sum < 0 || index == candidates.length) {
      return;
    }

    int elem = candidates[index];
    combination[elem]++; // include current element.
    combinationSum1(candidates, sum - elem, index + 1, combination, checker, result);
    combination[elem]++; // backtrack and exclude current element.
    combinationSum1(candidates, sum, index + 1, combination, checker, result);
  }

  boolean contains(int[] source, int[] target) {
    for (int i = 0; i < source.length; i++) {
      if (source[i] != 0 && target[i] == 0) {
        return false;
      }
    }
    return true;
  }

  void update(int[] source, int[] target) {
    for (int i = 0; i < source.length; i++) {
      target[i] = source[i];
    }
  }

  void addToResult(int[] combination, List<List<Integer>> result) {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < combination.length; i++) {
      if (combination[i] != 0) {
        list.add(i);
      }
    }
    result.add(list);
  }*/

  private void combinationSum(int[] candidates,
                              List<List<Integer>> output,
                              Set<String> existing,
                              List<Integer> current, int index, int sum) {
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
    current.add(candidates[index]);
    combinationSum(candidates, output, existing, current, index + 1, sum - candidates[index]);
    current.remove(current.size() - 1);
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
