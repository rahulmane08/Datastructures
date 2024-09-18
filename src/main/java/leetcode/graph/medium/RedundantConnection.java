package leetcode.graph.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import leetcode.graph.DisjointSet;

/**
 * https://leetcode.com/problems/redundant-connection/
 */
public class RedundantConnection {
  Map<Integer, Integer> parents = new HashMap<>();

  public int[] findRedundantConnection(int[][] edges) {
    Set<Integer> vertexes = new HashSet<>();
    Arrays.stream(edges).forEach(edge -> {
      vertexes.add(edge[0]);
      vertexes.add(edge[1]);
    });

    DisjointSet disjointSet = new DisjointSet(vertexes);
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

  /*public int[] findRedundantConnection(int[][] edges) {
    for (int[] edge : edges) {
      int v1 = edge[0];
      int v2 = edge[1];
      Integer parent1 = findParent(v1);
      Integer parent2 = findParent(v2);
      if (parent1 == parent2) {
        return edge;
      }

      if (parent1 == v1 && parent2 == v2) {
        parents.put(v2, v1);
      } else if (parent1 == v1) {
        parents.put(v1, parent2);
      } else if (parent2 == v2){
        parents.put(v2, parent1);
      } else {
        parents.put(v2, parent1);
        parents.put(parent2, parent1);
      }
    }
    return null;
  }

  int findParent(Integer curr) {
    if (parents.get(curr) == curr) {
      return curr;
    }
    if (parents.get(curr) == null) {
      parents.put(curr, curr);
      return curr;
    }
    int parent = findParent(parents.get(curr));
    parents.put(curr, parent);
    return parent;
  }*/

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
