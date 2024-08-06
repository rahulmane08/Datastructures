package algorithms.backtracking.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum/description/
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> output = new ArrayList<>();
        combinationSum(candidates, output, new ArrayList<>(), 0, target);
        return output;
    }

    private void combinationSum(int[] candidates, List<List<Integer>> output, List<Integer> current, int index, int sum) {
        if (sum == 0) {
            output.add(new ArrayList<>(current));
            return;
        }
        if (sum < 0 || index >= candidates.length) {
            return;
        }
        current.add(candidates[index]);
        combinationSum(candidates, output, current, index, sum - candidates[index]);
        current.remove(current.size() - 1);
        combinationSum(candidates, output, current, index + 1, sum);
    }

    public static void main(String[] args) {
        CombinationSum util = new CombinationSum();
        System.out.println(util.combinationSum(new int[]{2, 3, 6, 7}, 7));
    }
}
