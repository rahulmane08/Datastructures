package leetcode.graph.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class NumberOfProvinces {
  public int findCircleNum(int[][] isConnected) {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    for (int i = 0; i < isConnected.length; i++) {
      for (int j = 0; j < isConnected[0].length; j++) {
        if (isConnected[i][j] == 1) {
          if (i == j) {
            graph.compute(i, (vertex, adj) -> adj == null ? new ArrayList<>() : adj);
          } else {
            graph.compute(i, (vertex, adj) -> adj == null ? new ArrayList<>() : adj).add(j);
            graph.compute(j, (vertex, adj) -> adj == null ? new ArrayList<>() : adj).add(1);
          }
        }
      }
    }

    HashSet<Integer> visited = new HashSet<>();
    int totalProvinces = 0;
    for (Integer vertex : graph.keySet()) {
      if (!visited.contains(vertex)) {
        totalProvinces++;
        dfs(graph, visited, vertex);
      }
    }
    return totalProvinces;
  }

  private void dfs(Map<Integer, List<Integer>> graph, HashSet<Integer> visited, Integer vertex) {
    visited.add(vertex);
    for (Integer neighbor : graph.get(vertex)) {
      if (!visited.contains(neighbor)) {
        dfs(graph, visited, neighbor);
      }
    }
  }

  public static void main(String[] args) {
    int[][] isConnected = new int[][] {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
    NumberOfProvinces util = new NumberOfProvinces();
    System.out.println(util.findCircleNum(isConnected));
    isConnected = new int[][] {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
    System.out.println(util.findCircleNum(isConnected));
  }
}
