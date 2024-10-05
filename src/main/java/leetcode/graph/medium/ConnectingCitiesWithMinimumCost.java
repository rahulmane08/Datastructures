package leetcode.graph.medium;

import datastructures.advanced.DisjointSet;
import datastructures.graph.mst.PrimMST;
import java.util.List;
import leetcode.graph.KruskalMST;

public class ConnectingCitiesWithMinimumCost {
  public int minimumCost(int n, int[][] connections) {
    int edges = connections.length;
    if (edges < n - 1) {
      return -1;
    }
    KruskalMST mst = new KruskalMST();
    List<int[]> minPaths = mst.mst(connections);
    return minPaths.stream().map(a -> a[2]).mapToInt(Integer::intValue).sum();
  }
}