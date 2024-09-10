package leetcode.graph.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/redundant-connection/
 */
public class RedundantConnection {
  Map<Integer, Integer> parents = new HashMap<>();

  public int[] findRedundantConnection(int[][] edges) {
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
  }

  public static void main(String[] args) {
    int[][] edges = new int[][] {{1, 2}, {1, 3}, {2, 3}};
    System.out.println(Arrays.toString(new RedundantConnection().findRedundantConnection(edges)));
    edges = new int[][] {{1, 2}, {2, 3}, {1, 3}};
    System.out.println(Arrays.toString(new RedundantConnection().findRedundantConnection(edges)));
    edges = new int[][] {{1, 2}, {2, 3}, {3, 4}};
    System.out.println(Arrays.toString(new RedundantConnection().findRedundantConnection(edges)));
    edges = new int[][] {{1, 2}, {2, 3}, {3, 4}, {1, 4}};
    System.out.println(Arrays.toString(new RedundantConnection().findRedundantConnection(edges)));
    edges = new int[][] {{3, 4}, {1, 2}, {2, 4}, {3, 5}, {2, 5}};
  }
}
