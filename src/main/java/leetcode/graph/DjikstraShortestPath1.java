package leetcode.graph;

import static java.util.stream.Collectors.toMap;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import lombok.Data;

public class DjikstraShortestPath1 {
  private static final int INF = Integer.MAX_VALUE;

  public Map<Integer, Node> shortestPath(int[][] edges, boolean directed, Integer source) {
    WeightedGraph graph = new WeightedGraph(edges, false); // O(V+E)

    // Map that records the min shortestPathInfo from the source.
    // Initialize it as all vertexes lie at INF dist from source and set the parent as -1.
    Map<Integer, Node> shortestPathInfo =
        graph.getVertexes().stream().collect(toMap(v -> v, d -> new Node(d))); // O(V)

    // Create a min PQ that keeps track of visited nodes with their current shortestPathInfo from source vertex.
    PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(
        Node::getWeightFromSource)); // max = O(E)

    // add the source in PQ and update its distance as 0 and self reference as parent.
    Node start = shortestPathInfo.compute(source, (v, vd) -> {
      vd.weightFromSource = 0;
      return vd;
    });
    minHeap.offer(start);

    while (!minHeap.isEmpty()) {
      Node current = minHeap.poll();
      int currVertex = current.vertex;
      int currVertexWeightFromSource = current.weightFromSource;
      for (int[] neighborNode : graph.getNeighbors(currVertex)) { // For dense graph this can be V - 1
        int neighborVertex = neighborNode[0];
        int edgeWeight = neighborNode[1];
        Node neigborNode = shortestPathInfo.get(neighborVertex);

        if (currVertexWeightFromSource + edgeWeight < neigborNode.weightFromSource) {
          neigborNode.weightFromSource = currVertexWeightFromSource + edgeWeight;
          neigborNode.parent = currVertex;
          shortestPathInfo.put(neighborVertex, neigborNode);
          minHeap.offer(neigborNode); // log(heapSize)
        }
      }
    }
    return shortestPathInfo;
  }

  @Data
  class Node {
    private final int vertex;
    int weightFromSource = INF;
    int parent = -1;

    public Node(int vertex) {
      this.vertex = vertex;
    }
  }
}
