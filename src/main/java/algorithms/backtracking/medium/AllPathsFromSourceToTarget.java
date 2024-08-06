package algorithms.backtracking.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AllPathsFromSourceToTarget {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        if (graph == null || graph.length == 0) {
            return Collections.emptyList();
        }

        List<List<Integer>> output = new ArrayList<>();
        dfs(graph, output, new ArrayList<>(), 0, graph.length - 1);
        return output;
    }

    public void dfs(int[][] graph, List<List<Integer>> output, List<Integer> current, int start, int end) {
        current.add(start);
        if (start == end) {
            output.add(new ArrayList<>(current));
            current.remove(current.size() - 1);
            return;
        }
        int [] neighbors = graph[start];
        for (int n : neighbors) {
            dfs(graph, output, current, n, end);
        }
        current.remove(current.size() - 1);
    }

    public static void main(String[] args) {
        AllPathsFromSourceToTarget util = new AllPathsFromSourceToTarget();
        System.out.println(util.allPathsSourceTarget(new int[][]{{1,2},{3},{3},{}}));
    }
}
