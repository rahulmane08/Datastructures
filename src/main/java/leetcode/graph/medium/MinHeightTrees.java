package leetcode.graph.medium;

import static java.lang.Integer.MAX_VALUE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/minimum-height-trees/?envType=problem-list-v2&envId=topological-sort
 */
public class MinHeightTrees {
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

  public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    List<Integer> result = new ArrayList<>();
    Map<Integer, List<Integer>> graph = new HashMap<>();
    for (int[] edge : edges) {
      graph.compute(edge[0], (vertex, list) -> list == null ? new ArrayList<>() : list).add(edge[1]);
      graph.compute(edge[1], (vertex, list) -> list == null ? new ArrayList<>() : list).add(edge[0]);
    }
    int minHeight = MAX_VALUE;
    for (Integer curr : graph.keySet()) {
      int vertexHeight = calculateMinHeightTreeForVertex(graph, curr, new HashSet<>());
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

  int calculateMinHeightTreeForVertex(Map<Integer, List<Integer>> graph,
                                      Integer vertex,
                                      HashSet<Integer> visited) {
    visited.add(vertex);
    int height = MAX_VALUE;
    for (int neighbor : graph.get(vertex)) {
      if (!visited.contains(neighbor)) {
        height = Math.min(height, calculateMinHeightTreeForVertex(graph, neighbor, visited));
      }
    }
    return height != MAX_VALUE ? 1 + height : 0;
  }
}
