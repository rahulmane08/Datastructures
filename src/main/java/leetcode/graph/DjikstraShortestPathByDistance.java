package leetcode.graph;

import static java.util.stream.Collectors.toMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import lombok.Data;

public class DjikstraShortestPathByDistance {
  private static final int INF = Integer.MAX_VALUE;

  @Data
  private class Node {
    private final int vertex;
    int distanceFromSource = INF;
    int parent = -1;

    public Node(int vertex) {
      this.vertex = vertex;
    }
  }

  public Map<Integer, Node> shortestPath(int[][] edges, Integer source) {
    Graph graph = new Graph(edges, false);
    Map<Integer, Node> shortestDistanceInfo = graph.getVertexes().stream().collect(toMap(vertex -> vertex, Node::new));
    Node sourceNode = shortestDistanceInfo.compute(source, (vertex, node) -> {
      node.distanceFromSource = 0;
      return node;
    });
    PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(Node::getDistanceFromSource));
    minHeap.offer(sourceNode);

    while (!minHeap.isEmpty()) {
      Node currNode = minHeap.poll();
      for (int neighbor : graph.getNeighbors(currNode.vertex)) {
        Node neighborNode = shortestDistanceInfo.get(neighbor);
        if (currNode.distanceFromSource + 1 < neighborNode.distanceFromSource) {
          neighborNode.distanceFromSource = currNode.distanceFromSource + 1;
          neighborNode.parent = currNode.vertex;
          minHeap.offer(neighborNode);
        }
      }
    }
    return shortestDistanceInfo;
  }

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
    DjikstraShortestPathByDistance util = new DjikstraShortestPathByDistance();
    System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(util.shortestPath(edges, 1)));
  }
}
