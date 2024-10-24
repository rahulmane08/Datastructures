package leetcode.graph.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class AllPathsFromSourceLeadToDestination {

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

  public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
    Map<Integer, List<Integer>> graph = new HashMap<>();

    Arrays.stream(edges)
        .forEach(edge -> {
          graph.compute(edge[0], (v, list) -> list == null ? new ArrayList<>() : list).add(edge[1]);
          graph.compute(edge[1], (v, list) -> list == null ? new ArrayList<>() : list);
        });
    return leadsToDestination1(graph, source, destination, new HashSet<>(), new HashSet<>());
  }

  /**
   * 1-----> 2-----> 3*
   * 3
   * /   |   \
   * 1  --  2 --- 4
   * |
   * 5
   *
   * @param graph
   * @param curr
   * @param destination
   * @param ongoing
   * @param completed
   * @return
   */
  boolean leadsToDestination(Map<Integer, List<Integer>> graph,
                             int curr,
                             int destination,
                             HashSet<Integer> ongoing,
                             HashSet<Integer> completed) {
    ongoing.add(curr);
    boolean leadsToDestination = true;
    if (graph.getOrDefault(curr, Collections.emptyList()).isEmpty()) {
      // there can be other destination nodes, so need to make sure its the right one.
      leadsToDestination = (curr == destination);
    } else {
      for (int neighbor : graph.get(curr)) {
        //
        if (completed.contains(neighbor)) {
          leadsToDestination = true; // found a node which has a path to destination.
          break;
        }

        // ongoing checks for cycle.
        if (ongoing.contains(neighbor) || !leadsToDestination(graph, neighbor, destination, ongoing, completed)) {
          leadsToDestination = false;
          break; // cycle present
        }
      }
    }

    ongoing.remove(curr); // backtrack
    completed.add(curr); // nodes finishing are the ones who have a path till destination.
    return leadsToDestination; // check terminal node is destination
  }

  /**
   * This fails when destination has a self cycle.
   */
  boolean leadsToDestination1(Map<Integer, List<Integer>> graph,
                              int source,
                              int destination,
                              HashSet<Integer> ongoing,
                              HashSet<Integer> completed) {
    if (source == destination) {
      return true;
    } else {
      ongoing.add(source);
      List<Integer> neighbors = graph.get(source);
      if (neighbors.isEmpty()) {
        return source == destination;
      }

      for (Integer neighbor : neighbors) {
        if (completed.contains(neighbor)) {
          break;
        }
        if (ongoing.contains(neighbor) || !leadsToDestination1(graph, neighbor, destination, ongoing, completed)) {
          return false;
        }
      }
      completed.add(source);
      return true;
    }
  }
}
