package leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
  public final Map<Integer, Integer> inDegrees;
  public final Map<Integer, Integer> outDegrees;
  private final boolean directed;
  private final int[][] edges;
  private final Map<Integer, List<Integer>> adjacencyList;

  public Graph(boolean directed) {
    this(new int[][] {}, directed);
  }

  public Graph(int[][] edges, boolean directed) {
    this.directed = directed;
    this.edges = edges;
    this.adjacencyList = new HashMap<>();
    this.inDegrees = new HashMap<>();
    this.outDegrees = new HashMap<>();
    Arrays.stream(edges).forEach(this::addEdge);
  }

  /**
   * Space Complexity = O(2E) , since for every edge we are storing the nodes twice.
   * @param edge
   */
  public void addEdge(int[] edge) {
    if (directed) {
      adjacencyList.compute(edge[0], (v, list) -> list == null ? new ArrayList<>() : list).add(edge[1]);
      adjacencyList.compute(edge[1], (v, list) -> list == null ? new ArrayList<>() : list);
      inDegrees.compute(edge[0], (v, degree) -> degree == null ? 0 : degree);
      inDegrees.compute(edge[1], (v, degree) -> degree == null ? 1 : degree + 1);
      outDegrees.compute(edge[0], (v, degree) -> degree == null ? 1 : degree + 1);
      outDegrees.compute(edge[1], (v, degree) -> degree == null ? 0 : degree);
    } else {
      adjacencyList.compute(edge[0], (v, list) -> list == null ? new ArrayList<>() : list).add(edge[1]);
      adjacencyList.compute(edge[1], (v, list) -> list == null ? new ArrayList<>() : list).add(edge[0]);
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

  public Map<Integer, List<Integer>> getAdjacencyList() {
    return adjacencyList;
  }

  public boolean isDirected() {
    return directed;
  }

  public List<Integer> getNeighbors(Integer vertex) {
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

  public int getVertexCount() {
    return adjacencyList.keySet().size();
  }

  public Graph reverse() {
    if (!isDirected()) {
      return this;
    }
    Graph graph = new Graph(true);
    for (int[] edge : edges) {
      graph.addEdge(new int[] {edge[1], edge[0]});
    }
    return graph;
  }
}
