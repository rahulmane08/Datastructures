package leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WeightedGraph {
  public final Map<Integer, Integer> inDegrees;
  public final Map<Integer, Integer> outDegrees;
  private final boolean directed;
  private final int[][] edges;
  private final Map<Integer, List<int[]>> adjacencyList;

  public WeightedGraph(boolean directed) {
    this(new int[][] {}, directed);
  }

  public WeightedGraph(int[][] edges, boolean directed) {
    this.directed = directed;
    this.edges = edges;
    this.adjacencyList = new HashMap<>();
    this.inDegrees = new HashMap<>();
    this.outDegrees = new HashMap<>();
    Arrays.stream(edges).forEach(this::addEdge);
  }

  /**
   * Space Complexity = O(2E) , since for every edge we are storing the nodes twice.
   *
   * @param edge
   */
  public void addEdge(int[] edge) {
    if (directed) {
      adjacencyList.compute(edge[0], (v, list) -> list == null ? new ArrayList<>() : list)
          .add(new int[] {edge[1], edge[2]});
      adjacencyList.compute(edge[1], (v, list) -> list == null ? new ArrayList<>() : list);
      inDegrees.compute(edge[0], (v, degree) -> degree == null ? 0 : degree);
      inDegrees.compute(edge[1], (v, degree) -> degree == null ? 1 : degree + 1);
      outDegrees.compute(edge[0], (v, degree) -> degree == null ? 1 : degree + 1);
      outDegrees.compute(edge[1], (v, degree) -> degree == null ? 0 : degree);
    } else {
      adjacencyList.compute(edge[0], (v, list) -> list == null ? new ArrayList<>() : list)
          .add(new int[] {edge[1], edge[2]});
      adjacencyList.compute(edge[1], (v, list) -> list == null ? new ArrayList<>() : list)
          .add(new int[] {edge[0], edge[2]});
      inDegrees.compute(edge[0], (v, degree) -> degree == null ? 1 : degree + 1);
      inDegrees.compute(edge[1], (v, degree) -> degree == null ? 1 : degree + 1);
      outDegrees.compute(edge[0], (v, degree) -> degree == null ? 1 : degree + 1);
      outDegrees.compute(edge[1], (v, degree) -> degree == null ? 1 : degree + 1);
    }
  }

  public int[][] getEdges() {
    return edges;
  }

  public Set<Integer> getVertexes() {
    return adjacencyList.keySet();
  }

  public Map<Integer, List<int[]>> getAdjacencyList() {
    return adjacencyList;
  }

  public boolean isDirected() {
    return directed;
  }

  public List<int[]> getNeighbors(Integer vertex) {
    return adjacencyList.getOrDefault(vertex, new ArrayList<>());
  }

  public Integer getInDegrees(Integer vertex) {
    return inDegrees.getOrDefault(vertex, 0);
  }

  public Integer getOutDegrees(Integer vertex) {
    return outDegrees.getOrDefault(vertex, 0);
  }

  public Map<Integer, Integer> getInDegrees() {
    return new HashMap<>(inDegrees);
  }

  public Map<Integer, Integer> getOutDegrees() {
    return new HashMap<>(outDegrees);
  }

  public int size() {
    return adjacencyList.keySet().size();
  }

  public WeightedGraph reverse() {
    if (!isDirected()) {
      return this;
    }
    WeightedGraph graph = new WeightedGraph(true);
    for (int[] edge : edges) {
      graph.addEdge(new int[] {edge[1], edge[0], edge[2]});
    }
    return graph;
  }
}
