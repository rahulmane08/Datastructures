package leetcode.graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class GraphTraversal {

  //DFS

  /**
   * T(n) = O(V+E) for DG
   * T(n) = O(V+2E) for DG
   *
   * @param edges
   * @param directed
   */
  public static void dfs(int[][] edges, boolean directed) {
    Graph graph = new Graph(directed);
    Arrays.stream(edges).forEach(graph::addEdge);
    dfs(graph);
  }

  public static void dfs(Graph graph) {
    HashSet<Integer> visited = new HashSet<>();
    for (Integer vertex : graph.getVertexes()) {
      if (!visited.contains(vertex)) {
        dfs(graph, vertex, visited);
      }
    }
  }

  public static void dfs(Graph graph, Integer curr, HashSet<Integer> visited) {
    visited.add(curr);
    for (Integer neighbor : graph.getNeighbors(curr)) {
      if (!visited.contains(neighbor)) {
        dfs(graph, neighbor, visited);
      }
    }
  }

  //BFS

  /**
   * T(n) = O(V+E) for DG
   * T(n) = O(V+2E) for DG
   *
   * @param edges
   * @param directed
   */
  public static void bfs(int[][] edges, boolean directed) {
    Graph graph = new Graph(directed);
    Arrays.stream(edges).forEach(graph::addEdge);
    bfs(graph);
  }

  public static void bfs(Graph graph) {
    HashSet<Integer> visited = new HashSet<>();
    for (Integer vertex : graph.getVertexes()) {
      if (!visited.contains(vertex)) {
        bfs(graph, vertex, visited);
      }
    }
  }

  public static void bfs(Graph graph, Integer vertex, HashSet<Integer> visited) {
    Queue<Integer> bfs = new LinkedList<>();
    bfs.offer(vertex);
    visited.add(vertex);
    while (!bfs.isEmpty()) {
      Integer curr = bfs.poll();
      for (Integer neighbor : graph.getNeighbors(curr)) {
        if (!visited.contains(neighbor)) {
          bfs.offer(neighbor);
          visited.add(neighbor);
        }
      }
    }
  }
}
