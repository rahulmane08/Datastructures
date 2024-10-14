package leetcode.graph;

import static leetcode.graph.GraphUtil.vertexesWithInDegree;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class TopologicalSortUtil {

  /**
   * This algo works for DG and UDG.
   * Each node is traversed only once, thru the outgoing edges.
   * T(n) = O(V+E)
   *
   * @param edges
   * @param directed
   * @return
   */
  public static Stack<Integer> topSortDfs(int[][] edges, boolean directed) {
    Stack<Integer> topSort = new Stack<>();
    HashSet<Integer> visited = new HashSet<>();
    Graph graph = new Graph(edges, directed);
    topSortDfs(graph, visited, topSort);
    return topSort;
  }

  public static void topSortDfs(Graph graph, HashSet<Integer> visited, Stack<Integer> topSort) {
    for (Integer vertex : graph.getVertexes()) {
      if (!visited.contains(vertex)) {
        topSortDfsUtil(graph, vertex, visited, topSort);
      }
    }
  }

  public static void topSortDfsUtil(Graph graph, Integer curr, HashSet<Integer> visited, Stack<Integer> topSort) {
    visited.add(curr);
    for (Integer neighbor : graph.getNeighbors(curr)) {
      if (!visited.contains(neighbor)) {
        topSortDfsUtil(graph, neighbor, visited, topSort);
      }
    }
    topSort.push(curr);
  }

  public static void main(String[] args) {
    int[][] edges = {{1, 2}, {2, 3}};
    System.out.println("Case 1: graph with no cycle, graph: " + Arrays.deepToString(edges));
    System.out.println("UDG dfs (stack): " + topSortDfs(edges, false));
    System.out.println("DG dfs (stack): " + topSortDfs(edges, true));
    System.out.println("DG bfs (queue): " + KahnsAlgo.topSortBfs(edges));
    System.out.println();

    edges = new int[][] {{1, 2}, {2, 3}, {3, 1}};
    System.out.println("Case 2: graph with cycle, graph: " + Arrays.deepToString(edges));
    System.out.println("UDG dfs (stack): " + topSortDfs(edges, false));
    System.out.println("DG dfs (stack): " + topSortDfs(edges, true));
    System.out.println("DG bfs (queue): " + KahnsAlgo.topSortBfs(edges));
    System.out.println();

    edges = new int[][] {{1, 2}, {2, 3}, {1, 3}};
    System.out.println(
        "Case 3: Graph with no cycle in DG, but becomes cyclic in UDG, graph: " + Arrays.deepToString(edges));
    System.out.println("UDG dfs (stack): " + topSortDfs(edges, false));
    System.out.println("DG dfs (stack): " + topSortDfs(edges, true));
    System.out.println("DG bfs (queue): " + KahnsAlgo.topSortBfs(edges));
    System.out.println();

    edges = new int[][] {{1, 2}, {2, 3}, {3, 3}};
    System.out.println("Case 4: Graph with a self edge, graph: " + Arrays.deepToString(edges));
    System.out.println("UDG dfs (stack): " + topSortDfs(edges, false));
    System.out.println("DG dfs (stack): " + topSortDfs(edges, true));
    System.out.println("DG bfs (queue): " + KahnsAlgo.topSortBfs(edges));
    System.out.println();
  }

  /**
   * This algo works only for DAG as the for UDG we will never have the vertex with inDegree = 0.
   * Each node is traversed only once, thru the outgoing edges.
   * S(n) = O(n) for the graph.
   * T(n) = O(V+E)
   */
  public static class KahnsAlgo {
    public static Queue<Integer> topSortBfs(int[][] edges) {
      return topSortBfs(new Graph(edges, true));
    }

    public static Queue<Integer> topSortBfs(Graph graph) {
      // initialize the indegrees
      Map<Integer, Integer> inDegrees = graph.getInDegrees();
      // get the indegree = 0 vertexes.
      Map<Integer, Integer> zeroInDegreeMap = vertexesWithInDegree(graph, 0);
      Queue<Integer> traversalQueue = new LinkedList<>(zeroInDegreeMap.keySet());
      Queue<Integer> topSort = new LinkedList<>();
      while (!traversalQueue.isEmpty()) {
        Integer current = traversalQueue.poll();
        for (Integer neighbor : graph.getNeighbors(current)) {
          Integer neighborInDegree = inDegrees.compute(neighbor, (v, degree) -> degree - 1);
          if (neighborInDegree == 0) {
            traversalQueue.offer(neighbor);
          }
        }
        topSort.offer(current);
      }
      return topSort;
    }
  }
}
