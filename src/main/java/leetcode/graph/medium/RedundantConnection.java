package leetcode.graph.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import datastructures.advanced.DisjointSet;

/**
 * https://leetcode.com/problems/redundant-connection/
 */
public class RedundantConnection {
  Map<Integer, Integer> parents = new HashMap<>();

  public int[] findRedundantConnection(int[][] edges) {
    DisjointSet<Integer> disjointSet = new DisjointSet();
    Arrays.stream(edges).forEach(edge -> {
      disjointSet.makeSet(edge[0]);
      disjointSet.makeSet(edge[1]);
    });

    for (int[] edge : edges) {
      int vertex1 = edge[0];
      int vertex2 = edge[1];
      Integer parent1 = disjointSet.findSet(vertex1);
      Integer parent2 = disjointSet.findSet(vertex2);
      if (parent1 == parent2) {
        return new int[] {vertex1, vertex2};
      }
      disjointSet.union(vertex1, vertex2);
    }
    return new int[0];
  }

  public static void main(String[] args) {
    int[][] edges = new int[][] {{1, 2}, {1, 3}, {2, 3}};
    RedundantConnection redundantConnection = new RedundantConnection();
    System.out.println(Arrays.toString(redundantConnection.findRedundantConnection(edges)));
    edges = new int[][] {{1, 2}, {2, 3}, {1, 3}};
    System.out.println(Arrays.toString(redundantConnection.findRedundantConnection(edges)));
    edges = new int[][] {{1, 2}, {2, 3}, {3, 4}};
    System.out.println(Arrays.toString(redundantConnection.findRedundantConnection(edges)));
    edges = new int[][] {{1, 2}, {2, 3}, {3, 4}, {1, 4}};
    System.out.println(Arrays.toString(redundantConnection.findRedundantConnection(edges)));
  }
}
