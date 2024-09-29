package leetcode.graph;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class DjikstraShortestPath {

  private static final int INF = Integer.MAX_VALUE;

  public Map<Integer, Integer> shortestPath(int[][] edges, boolean directed, Integer source) {
    WeightedGraph graph = new WeightedGraph(edges, false);

    // Map that records the distances. Initialize it as all vertexes lie at INF dist from source.
    Map<Integer, Integer> distances = graph.getVertexes().stream().collect(Collectors.toMap(v -> v, d -> INF));

    // Create a min PQ that keeps track of visited nodes with their current distances from source vertex.
    PriorityQueue<int[]> visitedNodesByCurrentDistances = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

    // add the source in PQ and update its distance as 0.
    distances.put(source, 0);
    visitedNodesByCurrentDistances.offer(new int[] {source, 0});

    while (!visitedNodesByCurrentDistances.isEmpty()) {
      // get the next min visited by distances from src.
      int[] node = visitedNodesByCurrentDistances.poll();
      int currVertex = node[0];
      int currVertexWeightFromSource = distances.get(currVertex);

      for (int[] neighborNode : graph.getNeighbors(currVertex)) {
        int neighborVertex = neighborNode[0];
        int edgeWeight = neighborNode[1];
        int newNeighborWeightFromSource = currVertexWeightFromSource + edgeWeight;

        int currentNeighborWeightFromSource = distances.get(neighborVertex);

        if (newNeighborWeightFromSource < currentNeighborWeightFromSource) {
          distances.put(neighborVertex, newNeighborWeightFromSource);
          visitedNodesByCurrentDistances.offer(new int[] {neighborVertex, newNeighborWeightFromSource});
        }
      }
    }
    return distances;
  }


  public static void main(String[] args) {
    /**
     * (4)       (1)
     * 1 -------- 2------- 3
     * |          |
     * |(1)       |(1)
     * 4----------5
     * (1)
     */
    int[][] edges = {{1, 2, 4}, {2, 3, 1}, {1, 4, 1}, {4, 5, 1}, {5, 2, 1}};
    DjikstraShortestPath util = new DjikstraShortestPath();
    System.out.println(util.shortestPath(edges, false, 1));

    /**
     * (4)       (1)
     * 1 -------- 2------- 3
     * |
     * |(1)
     * 4----------5
     * (1)
     */
    edges = new int[][] {{1, 2, 4}, {2, 3, 1}, {1, 4, 1}, {4, 5, 1}};
    System.out.println(util.shortestPath(edges, false, 1));

    /**
     * (4)       (1)
     * 1 -------- 2------- 3
     * |   \(1)
     * |(1)  \
     * 4----------5
     * (1)
     */
    edges = new int[][] {{1, 2, 4}, {2, 3, 1}, {1, 4, 1}, {4, 5, 1}, {5, 1, 1}};
    System.out.println(util.shortestPath(edges, false, 1));
  }
}
