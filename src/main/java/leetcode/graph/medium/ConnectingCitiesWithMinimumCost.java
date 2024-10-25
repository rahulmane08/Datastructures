package leetcode.graph.medium;

import datastructures.advanced.DisjointSet;
import java.util.Arrays;
import java.util.Comparator;

public class ConnectingCitiesWithMinimumCost {
  public int minimumCost(int n, int[][] connections) {
    // sort array by min weights
    Arrays.sort(connections, Comparator.comparingInt(a -> a[2]));
    int minCost = 0;
    DisjointSet<Integer> set = new DisjointSet<>();
    for (int[] edge : connections) {
      Integer parent1 = set.findSet(edge[0]);
      Integer parent2 = set.findSet(edge[1]);
      if (parent1 == parent2) {
        continue;
      }
      set.union(edge[0], edge[1]);
      minCost += edge[2];
    }

    if (set.getCount() != 1 || n > set.getSize()) {
      return -1;
    }

    return minCost;
  }
}