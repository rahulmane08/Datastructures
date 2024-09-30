package leetcode.graph;

import static java.util.stream.Collectors.toMap;

import java.util.Map;

public class BellmanFordShortestPath {

  private static final int INF = Integer.MAX_VALUE;

  public Map<Integer, int[]> shortestPath(int[][] edges, boolean directed, Integer source) {
    WeightedGraph graph = new WeightedGraph(edges, false); // O(V+E)

    // Map that records the min distanceAndParent from the source.
    // Initialize it as all vertexes lie at INF dist from source.
    Map<Integer, int[]> distanceAndParent =
        graph.getVertexes().stream().collect(toMap(v -> v, d -> new int[] {INF, -1})); // O(V)

    // add the source in PQ and update its distance as 0 and self reference as parent.
    distanceAndParent.put(source, new int[] {0, source});

    int V = graph.size();
    for (int i = 0; i < V - 1; i++) {
      for (int[] edge : graph.getEdges()) {
        int currVertex = edge[0];
        int neighborVertex = edge[1];
        int edgeWeight = edge[2];

        int[] currentNodeInfo = distanceAndParent.get(currVertex);
        int currVertexWeightFromSource = currentNodeInfo[0];
        if (currVertexWeightFromSource == INF) {
          continue;
        }

        int newNeighborWeightFromSource = currVertexWeightFromSource + edgeWeight;
        int[] neighborNodeInfo = distanceAndParent.get(neighborVertex);
        int currentNeighborWeightFromSource = neighborNodeInfo[0];

        if (newNeighborWeightFromSource < currentNeighborWeightFromSource) {
          distanceAndParent.put(neighborVertex, new int[] {newNeighborWeightFromSource, currVertex}); // O(1)
        }
      }
    }

    // Run one more relaxation
    for (int[] edge : graph.getEdges()) {
      int currVertex = edge[0];
      int neighborVertex = edge[1];
      int edgeWeight = edge[2];

      int[] currentNodeInfo = distanceAndParent.get(currVertex);
      int currVertexWeightFromSource = currentNodeInfo[0];
      if (currVertexWeightFromSource == INF) {
        continue;
      }

      int newNeighborWeightFromSource = currVertexWeightFromSource + edgeWeight;
      int[] neighborNodeInfo = distanceAndParent.get(neighborVertex);
      int currentNeighborWeightFromSource = neighborNodeInfo[0];

      if (newNeighborWeightFromSource < currentNeighborWeightFromSource) {
        throw new RuntimeException("Graph has a negative weight cycle and hence solution not possible");
      }
    }
    return distanceAndParent;
  }
}
