package leetcode.graph;

import datastructures.advanced.BinaryMinHeap;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PrimMST {
  private static final int INF = Integer.MAX_VALUE;

  public List<int[]> mst(int[][] edges) {
    WeightedGraph graph = new WeightedGraph(edges, false);
    BinaryMinHeap<Integer> heap = new BinaryMinHeap<>();
    Map<Integer, int[]> vertexToMinEdgeMap = new LinkedHashMap<>();
    List<int[]> minimumSpanningPath = new ArrayList<>();

    Integer start = null;
    for (int[] edge : edges) {
      if (start == null) {
        start = edge[0];
        heap.add(start, 0);
      } else {
        heap.add(edge[0], INF);
      }
      heap.add(edge[1], INF);
    }

    while (!heap.isEmpty()) {
      Integer vertex = heap.extractMin();
      for (int[] neighbor : graph.getNeighbors(vertex)) {
        int neighborVertex = neighbor[0];
        int edgeWeight = neighbor[1];
        if (edgeWeight < heap.getKeyWeight(neighborVertex)) {
          heap.decreaseKey(neighborVertex, edgeWeight);
          vertexToMinEdgeMap.put(neighborVertex, new int[] {vertex, neighborVertex, edgeWeight});
        }
      }
    }
    return null;
  }
}
