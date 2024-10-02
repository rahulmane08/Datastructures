package leetcode.graph.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-cycle-in-a-graph/description/?envType=problem-list-v2&envId=topological-sort&difficulty=HARD
 * <p>
 * [1, 2, 0] -> 3
 * [1, 2, -1] -> 0
 */
public class LongestCycleInGraph {
  public int longestCycle(int[] edges) {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    for (int i = 0; i < edges.length; i++) {
      if (edges[i] != -1) {
        graph.compute(i, (v, list) -> list == null ? new ArrayList<>() : list).add(edges[i]);
        graph.compute(edges[i], (v, list) -> list == null ? new ArrayList<>() : list);
      } else {
        graph.compute(i, (v, list) -> list == null ? new ArrayList<>() : list);
      }
    }

    int[] max = new int[] {-1};
    HashSet<Integer> visited = new HashSet<>();
    for (int vertex : graph.keySet()) {
      if (!visited.contains(vertex)) {
        Map<Integer, Integer> visitTimes = new HashMap<>();
        dfsCycleLength(graph, visited, vertex, visitTimes, 1, max);
      }
    }
    return max[0];
  }


  private void dfsCycleLength(Map<Integer, List<Integer>> graph,
                              HashSet<Integer> visited, int vertex,
                              Map<Integer, Integer> visitTimes,
                              int visitTime,
                              int[] max) {
    visited.add(vertex);
    visitTimes.put(vertex, visitTime);
    int cycleLengths = -1;
    for (int neighbor : graph.get(vertex)) {
      if (visitTimes.containsKey(neighbor)) {
        // cycle found.
        cycleLengths = Math.max(cycleLengths, visitTime - visitTimes.get(neighbor) + 1);
        continue;
      }
      if (!visited.contains(neighbor)) {
        dfsCycleLength(graph, visited, neighbor, visitTimes, visitTime + 1, max);
      }
    }
    max[0] = Math.max(max[0], cycleLengths);
  }

  public static void main(String[] args) {
    LongestCycleInGraph util = new LongestCycleInGraph();
    System.out.println(util.longestCycle(new int[] {1, 2, 0}));
    System.out.println(util.longestCycle(new int[] {1, 2, -1}));
    System.out.println(util.longestCycle(new int[] {3, 3, 4, 2, 5, 3}));
    /**
     *  1-----|
     *       |
     *  0 -> 3 - > 2
     *       |    |
     *       5 <- 4
     */
  }
}
