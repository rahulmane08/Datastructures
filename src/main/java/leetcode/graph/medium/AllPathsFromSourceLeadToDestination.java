package leetcode.graph.medium;

import java.util.ArrayList;
import java.util.Arrays;
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
  }

  public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    Map<Integer, Integer> inDegrees = new HashMap<>();

    Arrays.stream(edges)
        .forEach(edge -> {
          graph.compute(edge[0], (v, list) -> list == null ? new ArrayList<>() : list).add(edge[1]);
          inDegrees.compute(edge[1], (v, degree) -> degree == null ? 1 : degree + 1);
        });
    return compute(graph, source, destination, new HashSet<>());
  }

  // wrong
  boolean compute(Map<Integer, List<Integer>> graph, int curr, int destination, HashSet<Integer> visited) {
    if (visited.contains(curr)) {
      return false;
    }

    visited.add(curr);
    List<Integer> neighbors = graph.getOrDefault(curr, new ArrayList<>());
    if (neighbors.isEmpty()) {
      return curr == destination;
    }

    for (Integer neighbor : neighbors) {
      if (!compute(graph, neighbor, destination, visited)) {
        return false;
      }
    }
    return true;
  }
}
