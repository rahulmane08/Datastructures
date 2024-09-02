package algorithms.backtracking.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
