package leetcode.graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import leetcode.graph.Graph;

public class GraphTraversal {

  //DFS
  public static void dfs(int[][] edges, boolean directed) {
    Graph graph = new Graph(directed);
    Arrays.stream(edges).forEach(graph::addEdge);
    dfs(graph);
  }

  public static void dfs(Graph graph) {
    HashSet<Integer> visited = new HashSet<>();
    for (Integer vertex : graph.getVertexes()) {
      dfs(graph, vertex, visited);
    }
  }

  public static void dfs(Graph graph, Integer curr, HashSet<Integer> visited) {
    if (visited.contains(curr)) {
      return;
    }
    visited.add(curr);
    for (Integer neighbor : graph.getNeighbors(curr)) {
      dfs(graph, neighbor, visited);
    }
  }

  //BFS
  public static void bfs(int[][] edges, boolean directed) {
    Graph graph = new Graph(directed);
    Arrays.stream(edges).forEach(graph::addEdge);
    bfs(graph);
  }

  public static void bfs(Graph graph) {
    HashSet<Integer> visited = new HashSet<>();
    for (Integer vertex : graph.getVertexes()) {
      bfs(graph, vertex, visited);
    }
  }

  public static void bfs(Graph graph, Integer vertex, HashSet<Integer> visited) {
    if (visited.contains(vertex)) {
      return;
    }
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
