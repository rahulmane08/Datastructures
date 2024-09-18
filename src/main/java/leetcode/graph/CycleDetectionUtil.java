package leetcode.graph;

import static leetcode.graph.CycleDetectionUtil.GraphColoringUtil.detectCycleInDGUsingGraphColoring;
import static leetcode.graph.CycleDetectionUtil.KahnsAlgo.detectCycleInDG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class CycleDetectionUtil {

  public static boolean detectCycleInUDG(int[][] edges) {
    Set<Integer> vertexes = new HashSet<>();
    Arrays.stream(edges).forEach(edge -> {
      vertexes.add(edge[0]);
      vertexes.add(edge[1]);
    });
    DisjointSet disjointSet = new DisjointSet(vertexes);
    for (int[] edge : edges) {
      int parent1 = disjointSet.findSet(edge[0]);
      int parent2 = disjointSet.findSet(edge[1]);
      if (parent1 == parent2) {
        return true;
      }
      disjointSet.union(edge[0], edge[1]);
    }
    return false;
  }

  public static boolean detectCycle(int[][] edges, boolean directed) {
    if (directed) {
      return detectCycleInDGUsingGraphColoring(edges);
    }
    return detectCycleInUDG(edges);
  }

  public static class GraphColoringUtil {
    public static boolean detectCycleInDGUsingGraphColoring(int[][] edges) {
      Graph graph = new Graph(edges, true);
      HashSet<Integer> whiteSet = new HashSet<>();
      HashSet<Integer> greySet = new HashSet<>();
      HashSet<Integer> blackSet = new HashSet<>();

      graph.getVertexes().forEach(whiteSet::add);
      for (Integer curr : new ArrayList<>(whiteSet)) {
        if (!detectCycleInDGUtil(graph, curr, whiteSet, greySet, blackSet)) {
          return false;
        }
      }
      return true;
    }

    private static boolean detectCycleInDGUtil(Graph graph,
                                               Integer curr,
                                               HashSet<Integer> whiteSet,
                                               HashSet<Integer> greySet,
                                               HashSet<Integer> blackSet) {
      moveSet(curr, whiteSet, greySet);
      for (Integer neighbor : graph.getNeighbors(curr)) {
        if (blackSet.contains(neighbor)) {
          continue;
        }

        if (greySet.contains(neighbor)) {
          return true;
        }

        if (detectCycleInDGUtil(graph, neighbor, whiteSet, greySet, blackSet)) {
          return true;
        }
      }
      moveSet(curr, greySet, blackSet);
      return false;
    }

    private static void moveSet(Integer curr, HashSet<Integer> source, HashSet<Integer> target) {
      source.remove(curr);
      target.add(curr);
    }
  }

  /**
   * Employs BFS.
   */
  public static class KahnsAlgo {
    public static boolean detectCycleInDG(int[][] edges) {
      Graph graph = new Graph(edges, true);
      Queue<Integer> topSort = TopologicalSortUtil.KahnsAlgo.topSortBfs(graph);
      return topSort.size() != graph.getVertexCount();
    }
  }

  public static void main(String[] args) {
    int[][] edges = {{1, 2}, {2, 3}};
    System.out.println("Case 1: graph with no cycle, graph: " + Arrays.deepToString(edges));
    System.out.println("UDG DisjoinSet: " + detectCycle(edges, false)); // DJ
    System.out.println("DG GraphColoring: " + detectCycle(edges, true)); // GC
    System.out.println("DG KahnsAlgo: " + detectCycleInDG(edges)); // KA
    System.out.println();

    edges = new int[][] {{1, 2}, {2, 3}, {3, 1}};
    System.out.println("Case 2: graph with cycle, graph: " + Arrays.deepToString(edges));
    System.out.println("UDG DisjoinSet: " + detectCycle(edges, false)); // DJ
    System.out.println("DG GraphColoring: " + detectCycle(edges, true)); // GC
    System.out.println("DG KahnsAlgo: " + detectCycleInDG(edges)); // KA
    System.out.println();

    edges = new int[][] {{1, 2}, {2, 3}, {1, 3}};
    System.out.println(
        "Case 3: Graph with no cycle in DG, but becomes cyclic in UDG, graph: " + Arrays.deepToString(edges));
    System.out.println("UDG DisjoinSet: " + detectCycle(edges, false)); // DJ
    System.out.println("DG GraphColoring: " + detectCycle(edges, true)); // GC
    System.out.println("DG KahnsAlgo: " + detectCycleInDG(edges)); // KA
    System.out.println();

    edges = new int[][] {{1, 2}, {2, 3}, {3, 3}};
    System.out.println("Case 4: Graph with a self edge, graph: " + Arrays.deepToString(edges));
    System.out.println("UDG DisjoinSet: " + detectCycle(edges, false)); // DJ
    System.out.println("DG GraphColoring: " + detectCycle(edges, true)); // GC
    System.out.println("DG KahnsAlgo: " + detectCycleInDG(edges)); // KA
    System.out.println();
  }
}
