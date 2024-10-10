package leetcode.graph.easy;

import datastructures.advanced.DisjointSet;

public class CheckIfGraphIsConnected {
  public boolean check(int[][] edges, boolean directed) {
    DisjointSet set = new DisjointSet();
    for (int[] edge : edges) {
      set.union(edge[0], edge[1]);
    }
    return set.getSize() == 1;
  }
}
