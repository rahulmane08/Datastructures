package leetcode.graph.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class AllPathsFromSourceLeadToDestination {

  public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
    Map<Integer, List<Integer>> graph = new HashMap<>();

    Arrays.stream(edges)
        .forEach(edge -> {
          graph.compute(edge[0], (v, list) -> list == null ? new ArrayList<>() : list).add(edge[1]);
          graph.compute(edge[1], (v, list) -> list == null ? new ArrayList<>() : list);
        });
    return leadsToDestination(graph, source, destination, new HashSet<>(), new HashSet<>());
  }

  /**
   * 1-----> 2-----> 3*
   *        3
   *    /   |   \
   * 1  --  2 --- 4
   *        |
   *        5
   *
   * @param graph
   * @param curr
   * @param destination
   * @param ongoing
   * @param visited
   * @return
   */
  boolean leadsToDestination(Map<Integer, List<Integer>> graph,
                             int curr,
                             int destination,
                             HashSet<Integer> ongoing,
                             HashSet<Integer> visited) {
    ongoing.add(curr);
    boolean leadsToDestination = true;
    if (graph.getOrDefault(curr, Collections.emptyList()).isEmpty()) {
      leadsToDestination = (curr == destination);
    } else {
      for (int neighbor : graph.get(curr)) {
        if (visited.contains(neighbor)) {
          continue;
        }

        if (ongoing.contains(neighbor)) {
          return false; // cycle present
        }

        leadsToDestination = leadsToDestination(graph, neighbor, destination, ongoing, visited);
        if (!leadsToDestination) {
          return false;
        }
      }
    }

    ongoing.remove(curr);
    visited.add(curr);
    return leadsToDestination; // check terminal node is destination
  }

  public static void main(String[] args) {
    int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {2, 3}};
    AllPathsFromSourceLeadToDestination util = new AllPathsFromSourceLeadToDestination();
    System.out.println(util.leadsToDestination(4, edges, 0, 3));
    edges = new int[][] {{0, 1}, {0, 3}, {1, 2}, {2, 1}};
    System.out.println(util.leadsToDestination(4, edges, 0, 3));
    edges = new int[][] {{0, 1}, {0, 2}};
    System.out.println(util.leadsToDestination(3, edges, 0, 2));
    edges = new int[][] {{0, 1}, {1, 1}};
    System.out.println(util.leadsToDestination(2, edges, 0, 1));
    edges = new int[][] {{0, 1}, {0, 2}, {1, 3}, {2, 3}, {1, 4}};
    System.out.println(util.leadsToDestination(5, edges, 0, 3));
    edges = new int[][] {{0, 1}, {0, 2}, {1, 3}, {2, 3}, {1, 4}, {4, 3}};
    System.out.println(util.leadsToDestination(6, edges, 0, 3));
    edges = new int[][] {{0, 1}, {0, 2}, {1, 3}, {2, 3}, {1, 2}};
    System.out.println(util.leadsToDestination(5, edges, 0, 3));
  }
}
