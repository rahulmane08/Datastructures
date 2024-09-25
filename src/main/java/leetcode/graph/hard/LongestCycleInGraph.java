package leetcode.graph.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-cycle-in-a-graph/description/?envType=problem-list-v2&envId=topological-sort&difficulty=HARD
 * <p>
 * [1, 2, 0] -> 3
 * [1, 2, -1] -> 0
 */
public class LongestCycleInGraph {
  public int longestCycle(int[] edges) {
    int[] max = new int[] {-1, -1};
    Map<Integer, Integer> visitCount = new HashMap<>();
    for (int fromVertex = 0; fromVertex < edges.length; fromVertex++) {
      if (!visitCount.containsKey(fromVertex)) {
        dfs(edges, fromVertex, visitCount, max, 0);
      }
    }
    return max[1];
  }


  private void dfs(int[] edges,
                   int vertex,
                   Map<Integer, Integer> visitCount,
                   int[] max,
                   int currentVisit) {
    if (visitCount.containsKey(vertex)) {
      int cycleLengthStartingFromVertex = currentVisit - visitCount.get(vertex);
      if (max[1] < cycleLengthStartingFromVertex) {
        max[0] = vertex;
        max[1] = cycleLengthStartingFromVertex;
      }
      return;
    }
    visitCount.put(vertex, currentVisit);
    if (edges[vertex] != -1) {
      dfs(edges, edges[vertex], visitCount, max, currentVisit + 1);
    }
  }

  public static void main(String[] args) {
    LongestCycleInGraph util = new LongestCycleInGraph();
    System.out.println(util.longestCycle(new int[] {1, 2, 0}));
    System.out.println(util.longestCycle(new int[] {1, 2, -1}));
    System.out.println(util.longestCycle(new int[] {3, 3, 4, 2, 5, 3}));
    /**
     *  1
     *    \
     *  0 -> 3 - > 2
     *       |    |
     *       5  - 4
     */
  }
}
