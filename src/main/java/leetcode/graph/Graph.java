package leetcode.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph<T> {
  public final boolean directed;
  private final Map<T, List<T>> vertexAndEdges;
  public final Map<T, Integer> inDegrees;
  public final Map<T, Integer> outDegrees;

  public Graph(boolean directed, int [][] edges) {
    this.directed = directed;
    this.vertexAndEdges = new HashMap<>();
    this.inDegrees = new HashMap<>();
    this.outDegrees = new HashMap<>();

    /*Arrays.stream(edges).forEach(edge -> {
      vertexAndEdges.compute(edge[1], (v1))
    });*/
  }
}
