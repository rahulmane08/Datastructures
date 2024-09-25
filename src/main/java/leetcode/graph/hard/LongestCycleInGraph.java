package leetcode.graph.hard;

import java.util.HashSet;

/**
 * https://leetcode.com/problems/longest-cycle-in-a-graph/description/?envType=problem-list-v2&envId=topological-sort&difficulty=HARD
 * <p>
 * [1, 2, 0] -> 3
 * [1, 2, -1] -> 0
 */
public class LongestCycleInGraph {
  public int longestCycle(int[] edges) {
    HashSet<Integer> visited = new HashSet<>();
    int longestCycle = 0;
    for (int fromVertex = 0; fromVertex < edges.length; fromVertex++) {
      if (!visited.contains(fromVertex)) {
        visited.add(fromVertex);
        longestCycle = Math.max(longestCycle, getCycleLengthUsingDfs(edges, fromVertex, visited));
      }
    }
    return longestCycle;
  }


  private int getCycleLengthUsingDfs(int[] edges, int vertex,
                                     HashSet<Integer> visited) {
    int neighbor = edges[vertex];
    int length = 0;
    while (neighbor != -1 && neighbor != vertex && !visited.contains(neighbor)) {
      length++;
      neighbor = edges[neighbor];
    }
    if (neighbor != vertex) {
      return 0;
    }
    return length + 1;
  }

  public static void main(String[] args) {
    LongestCycleInGraph util = new LongestCycleInGraph();
    System.out.println(util.longestCycle(new int[] {1, 2, 0}));
    System.out.println(util.longestCycle(new int[] {1, 2, -1}));
    System.out.println(util.longestCycle(new int[] {3, 3, 4, 2, 3}));
  }
}
