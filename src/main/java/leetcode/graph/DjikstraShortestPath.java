package leetcode.graph;

import static java.util.stream.Collectors.toMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * T(n) = E Log(V)
 */
public class DjikstraShortestPath {

  private static final int INF = Integer.MAX_VALUE;

  public Map<Integer, int[]> shortestPath(int[][] edges, boolean directed, Integer source) {
    WeightedGraph graph = new WeightedGraph(edges, false); // O(V+E)

    // Map that records the min distanceAndParent from the source.
    // Initialize it as all vertexes lie at INF dist from source and set the parent as -1.
    Map<Integer, int[]> distanceAndParent =
        graph.getVertexes().stream().collect(toMap(v -> v, d -> new int[] {INF, -1})); // O(V)

    // Create a min PQ that keeps track of visited nodes with their current distanceAndParent from source vertex.
    PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); // max = O(E)

    // add the source in PQ and update its distance as 0 and self reference as parent.
    distanceAndParent.put(source, new int[] {0, source});
    minHeap.offer(new int[] {source, 0});

    while (!minHeap.isEmpty()) { // O(V)
      // get the next min visited by distanceAndParent from src.
      int[] node = minHeap.poll(); // log(heapSize)
      int currVertex = node[0];
      int[] currentNodeInfo = distanceAndParent.get(currVertex);
      int currVertexWeightFromSource = currentNodeInfo[0];

      for (int[] neighborNode : graph.getNeighbors(currVertex)) { // For dense graph this can be V - 1
        int neighborVertex = neighborNode[0];
        int edgeWeight = neighborNode[1];
        int newNeighborWeightFromSource = currVertexWeightFromSource + edgeWeight;

        int[] neighborNodeInfo = distanceAndParent.get(neighborVertex);
        int currentNeighborWeightFromSource = neighborNodeInfo[0];

        if (newNeighborWeightFromSource < currentNeighborWeightFromSource) {
          distanceAndParent.put(neighborVertex, new int[] {newNeighborWeightFromSource, currVertex}); // O(1)
          minHeap.offer(new int[] {neighborVertex, newNeighborWeightFromSource}); // log(heapSize)
        }
      }
    }
    return distanceAndParent;
  }

  /**
   * T(n) = V * [log(heapSize) + (V-1)log(heapSize)]
   * = V * log(heapSize)[(V-1 + 1] = V^2 (log(heapSize)) // heapSize = V^2 nodes in dense graph
   * = V^2 * 2(logV)
   * = E*log(V) // since in dense graph every vertex is connected to every other, hence total edges = V(V-1) = V^2
   *
   * @param args
   * @throws JsonProcessingException
   */

  public static void main(String[] args) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
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
    System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(util.shortestPath(edges, false, 1)));

    /**
     * (4)       (1)
     * 1 -------- 2------- 3
     * |
     * |(1)
     * 4----------5
     * (1)
     */
    edges = new int[][] {{1, 2, 4}, {2, 3, 1}, {1, 4, 1}, {4, 5, 1}};
    System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(util.shortestPath(edges, false, 1)));

    /**
     * (4)       (1)
     * 1 -------- 2------- 3
     * |   \(1)
     * |(1)  \
     * 4----------5
     * (1)
     */
    edges = new int[][] {{1, 2, 4}, {2, 3, 1}, {1, 4, 1}, {4, 5, 1}, {5, 1, 1}};
    System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(util.shortestPath(edges, false, 1)));
  }
}
