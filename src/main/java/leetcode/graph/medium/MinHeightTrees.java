package leetcode.graph.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/minimum-height-trees/?envType=problem-list-v2&envId=topological-sort
 */
public class MinHeightTrees {
  public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    List<Integer> result = new ArrayList<>();
    Map<Integer, List<Integer>> graph = new HashMap<>();
    for (int[] edge : edges) {
      graph.compute(edge[0], (vertex, list) -> list == null ? new ArrayList<>() : list).add(edge[1]);
      graph.compute(edge[1], (vertex, list) -> list == null ? new ArrayList<>() : list).add(edge[0]);
    }
    int minHeight = Integer.MAX_VALUE;
    for (Integer curr : graph.keySet()) {
      int vertexHeight = compute(graph, curr, new HashSet<>());
      System.out.println("vertex: " + curr + ", minHeight: " + vertexHeight);
      if (vertexHeight == minHeight) {
        result.add(curr);
      } else if (vertexHeight < minHeight) {
        minHeight = vertexHeight;
        result.clear();
        result.add(curr);
      }
    }
    if (result.isEmpty()) {
      result.add(0);
    }
    return result;
  }

  int compute(Map<Integer, List<Integer>> graph, Integer curr, HashSet<Integer> visited) {
    if (visited.contains(curr)) {
      return Integer.MAX_VALUE;
    }
    visited.add(curr);
    int minHeight = Integer.MAX_VALUE;
    for (Integer neighbor : graph.get(curr)) {
      int neighborHeight = compute(graph, neighbor, visited);
      minHeight = Math.min(minHeight, neighborHeight);
    }
    return minHeight == Integer.MAX_VALUE ? 0 : 1 + minHeight;
  }

  public static void main(String[] args) {
    int[][] edges = {{1, 0}, {1, 2}, {1, 3}};
    MinHeightTrees util = new MinHeightTrees();
    System.out.println(util.findMinHeightTrees(4, edges));
    edges = new int[][] {{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};
    System.out.println(util.findMinHeightTrees(6, edges));
    edges = new int[][] {};
    System.out.println(util.findMinHeightTrees(1, edges));
    edges = new int[][] {{0, 1}, {0, 2}, {0, 3}, {3, 4}, {4, 5}};
    System.out.println(util.findMinHeightTrees(1, edges));
  }
}
