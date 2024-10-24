package leetcode.graph;

import datastructures.advanced.DisjointSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class CycleDetectionUtil {

  public static boolean detectCycle(int[][] edges, boolean directed) {
    if (directed) {
      return GraphColoringUtil.detectCycleInDG(edges);
    }
    return detectCycleInUDG(edges);
  }

  // ============================= UN-DIRECTED GRAPHS=======================================
  // DisjointSets
  public static boolean detectCycleInUDG(int[][] edges) {
    DisjointSet<Integer> disjointSet = new DisjointSet();
    Arrays.stream(edges).forEach(edge -> {
      disjointSet.makeSet(edge[0]);
      disjointSet.makeSet(edge[1]);
    });
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

  // BFS
  public static boolean detectCycleInUDGUsingBfs(int[][] edges) {
    HashSet<Integer> visited = new HashSet<>();
    Graph graph = new Graph(false);
    Arrays.stream(edges).forEach(graph::addEdge);
    for (Integer curr : graph.getVertexes()) {
      if (!visited.contains(curr)) {
        if (detectCycleInUDGUsingBfs(graph, curr, visited)) {
          return true;
        }
      }
    }
    return false;
  }

  public static boolean detectCycleInUDGUsingBfs(Graph graph, Integer vertex, HashSet<Integer> visited) {
    Queue<int[]> bfs = new LinkedList<>();
    bfs.offer(new int[] {vertex, -1}); // set the first vertex's parent as -1.
    visited.add(vertex);
    while (!bfs.isEmpty()) {
      int[] currentElem = bfs.poll();
      int curr = currentElem[0];
      int parent = currentElem[1];
      for (Integer neighbor : graph.getNeighbors(curr)) {
        if (!visited.contains(neighbor)) {
          bfs.offer(new int[] {neighbor, curr});
          visited.add(neighbor);
        } else if (neighbor != parent) {
          return true;
        }
      }
    }
    return false;
  }

  // ============================= DIRECTED GRAPHS=======================================

  /**
   * This algo works only for DG.
   */
  public static class GraphColoringUtil {
    public static boolean detectCycleInDG(int[][] edges) {
      Graph graph = new Graph(edges, true);
      HashSet<Integer> whiteSet = new HashSet<>();
      HashSet<Integer> greySet = new HashSet<>();
      HashSet<Integer> blackSet = new HashSet<>();

      graph.getVertexes().forEach(whiteSet::add);
      for (Integer curr : new ArrayList<>(whiteSet)) {
        if (detectCycleInDGUtil(graph, curr, whiteSet, greySet, blackSet)) {
          return true;
        }
      }
      return false;
    }

    private static boolean detectCycleInDGUtil(Graph graph,
                                               Integer curr,
                                               HashSet<Integer> whiteSet,
                                               HashSet<Integer> greySet,
                                               HashSet<Integer> blackSet) {
      // move to greySet indicating, start of DFS.
      moveSet(curr, whiteSet, greySet);
      for (Integer neighbor : graph.getNeighbors(curr)) {
        // completely visited node, skip it.
        if (blackSet.contains(neighbor)) {
          continue;
        }

        // found a node who is undergoing a dfs, hence its a cycle.
        if (greySet.contains(neighbor)) {
          return true;
        }

        // break the DFS if a cycle is found.
        if (detectCycleInDGUtil(graph, neighbor, whiteSet, greySet, blackSet)) {
          return true;
        }
      }
      // move to greySet indicating, end of DFS.
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
      return topSort.size() != graph.size();
    }
  }

  public static void main(String[] args) {
    int[][] edges = {{1, 2}, {2, 3}};
    System.out.println("Case 1: graph with no cycle, graph: " + Arrays.deepToString(edges));
    System.out.println("UDG DisjoinSet: " + detectCycle(edges, false)); // DJ
    System.out.println("DG GraphColoring: " + detectCycle(edges, true)); // GC
    System.out.println("DG KahnsAlgo: " + KahnsAlgo.detectCycleInDG(edges)); // KA
    System.out.println();

    edges = new int[][] {{1, 2}, {2, 3}, {3, 1}};
    System.out.println("Case 2: graph with cycle, graph: " + Arrays.deepToString(edges));
    System.out.println("UDG DisjoinSet: " + detectCycle(edges, false)); // DJ
    System.out.println("DG GraphColoring: " + detectCycle(edges, true)); // GC
    System.out.println("DG KahnsAlgo: " + KahnsAlgo.detectCycleInDG(edges)); // KA
    System.out.println();

    edges = new int[][] {{1, 2}, {2, 3}, {1, 3}};
    System.out.println(
        "Case 3: Graph with no cycle in DG, but becomes cyclic in UDG, graph: " + Arrays.deepToString(edges));
    System.out.println("UDG DisjoinSet: " + detectCycle(edges, false)); // DJ
    System.out.println("DG GraphColoring: " + detectCycle(edges, true)); // GC
    System.out.println("DG KahnsAlgo: " + KahnsAlgo.detectCycleInDG(edges)); // KA
    System.out.println();

    edges = new int[][] {{1, 2}, {2, 3}, {3, 3}};
    System.out.println("Case 4: Graph with a self edge, graph: " + Arrays.deepToString(edges));
    System.out.println("UDG DisjoinSet: " + detectCycle(edges, false)); // DJ
    System.out.println("DG GraphColoring: " + detectCycle(edges, true)); // GC
    System.out.println("DG KahnsAlgo: " + KahnsAlgo.detectCycleInDG(edges)); // KA
    System.out.println();
  }
}
