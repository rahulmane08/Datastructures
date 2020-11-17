package algos.backtracking;

import java.util.ArrayList;
import java.util.List;

public class BacktrackingUtil {

    public static List<List<Integer>> getAllSubsets(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        for (int i = 2; i <= 2; i++) {
            subsetUtil(arr, 0, i, 0, res, subset);
        }
        return res;
    }

    private static void subsetUtil(int[] arr, int start, int end, int count, List<List<Integer>> res, List<Integer> subset) {
        if (count == end) {
            res.add(new ArrayList<>(subset));
            return;
        }
        for (int i = start; i < arr.length; i++) {
            subset.add(arr[i]);
            subsetUtil(arr, start + 1, end, count + 1, res, subset);
            subset.remove(subset.size() - 1);
        }
    }
}
