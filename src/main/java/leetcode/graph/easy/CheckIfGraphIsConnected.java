package leetcode.graph.easy;

import datastructures.advanced.DisjointSet;

public class CheckIfGraphIsConnected {

  public boolean check(int[][] edges) {
    DisjointSet<Integer> set = new DisjointSet<>();
    for (int[] edge : edges) {
      set.makeSet(edge[0]);
      set.makeSet(edge[1]);
    }

    for (int[] edge : edges) {
      Integer p1 = set.findSet(edge[0]);
      Integer p2 = set.findSet(edge[1]);
      if (p1 == p2) {
        return false;
      }
      set.union(edge[0], edge[1]);
    }
    return set.getCount() == 1;
  }
}
