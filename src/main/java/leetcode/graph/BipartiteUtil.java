package leetcode.graph;

import static java.util.Arrays.deepToString;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BipartiteUtil {

  public boolean isBipartiteUsingBfs(int[][] edges) {
    Graph graph = new Graph(false);
    Arrays.stream(edges).forEach(graph::addEdge);
    Map<Integer, Integer> vertexColors = new HashMap<>(); // 0 - white, 1 - black
    for (Integer vertex : graph.getVertexes()) {
      if (!vertexColors.containsKey(vertex)) {
        if (!isBipartiteUsingBfs(graph, vertex, vertexColors)) {
          return false;
        }
      }
    }
    return true;
  }

  public boolean isBipartiteUsingBfs(Graph graph, Integer vertex, Map<Integer, Integer> vertexColors) {
    Queue<Integer> bfs = new LinkedList<>();
    bfs.offer(vertex);
    vertexColors.put(vertex, 0);
    while (!bfs.isEmpty()) {
      Integer curr = bfs.poll();
      Integer color = vertexColors.get(curr);
      for (Integer neighbor : graph.getNeighbors(curr)) {
        if (!vertexColors.containsKey(neighbor)) {
          vertexColors.put(neighbor, 1 - color);
          bfs.offer(neighbor);
        } else if (vertexColors.get(neighbor) == color) {
          return false;
        }
      }
    }
    return true;
  }

  public boolean isBipartiteUsingDfs(int[][] edges) {
    Graph graph = new Graph(false);
    Arrays.stream(edges).forEach(graph::addEdge);
    Map<Integer, Integer> vertexColors = new HashMap<>(); // 0 - white, 1 - black
    for (Integer vertex : graph.getVertexes()) {
      if (!vertexColors.containsKey(vertex)) {
        if (!isBipartiteUsingDfs(graph, vertex, 0, vertexColors)) {
          return false;
        }
      }
    }
    return true;
  }

  public boolean isBipartiteUsingDfs(Graph graph, Integer vertex, Integer color, Map<Integer, Integer> vertexColors) {
    vertexColors.put(vertex, color);
    for (Integer neighbor : graph.getNeighbors(vertex)) {
      if (!vertexColors.containsKey(neighbor)) {
        if (!isBipartiteUsingDfs(graph, neighbor, 1 - color, vertexColors)) {
          return false;
        }
      } else if (vertexColors.get(neighbor) == color) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    BipartiteUtil util = new BipartiteUtil();
    int[][] edges = new int[][] {{1, 2}, {2, 3}, {2, 4}};
    System.out.printf("Graph with no cycle: %s, isBipartiteUsingBfs: %s%n", deepToString(edges),
        util.isBipartiteUsingBfs(edges));
    System.out.printf("Graph with no cycle: %s, isBipartiteUsingDfs: %s%n", deepToString(edges),
        util.isBipartiteUsingDfs(edges));


    edges = new int[][] {{1, 2}, {2, 3}, {3, 4}, {4, 1}};
    System.out.printf("Graph with even cycle: %s, isBipartiteUsingBfs: %s%n", deepToString(edges),
        util.isBipartiteUsingBfs(edges));
    System.out.printf("Graph with even cycle: %s, isBipartiteUsingDfs: %s%n", deepToString(edges),
        util.isBipartiteUsingDfs(edges));

    edges = new int[][] {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 1}};
    System.out.printf("Graph with odd cycle: %s, isBipartiteUsingBfs: %s%n", deepToString(edges),
        util.isBipartiteUsingBfs(edges));
    System.out.printf("Graph with odd cycle: %s, isBipartiteUsingDfs: %s%n", deepToString(edges),
        util.isBipartiteUsingDfs(edges));

    edges = new int[][] {{1, 2}, {2, 3}, {3, 3}};
    System.out.printf("Graph with self cycle: %s, isBipartiteUsingBfs: %s%n", deepToString(edges),
        util.isBipartiteUsingBfs(edges));
    System.out.printf("Graph with self cycle: %s, isBipartiteUsingDfs: %s%n", deepToString(edges),
        util.isBipartiteUsingDfs(edges));
  }
}
