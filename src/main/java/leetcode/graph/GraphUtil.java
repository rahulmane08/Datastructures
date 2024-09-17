package leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class GraphUtil {
  public static Stack<Integer> topSort(Map<Integer, List<Integer>> graph) {
    Stack<Integer> topSort = new Stack<>();
    HashSet<Integer> whiteSet = new HashSet<>();
    HashSet<Integer> greySet = new HashSet<>();
    HashSet<Integer> blackSet = new HashSet<>();

    graph.keySet().forEach(whiteSet::add);

    for (Integer curr : graph.keySet()) {
      if (!topSortUtil(graph, curr, whiteSet, greySet, blackSet, topSort)) {
        return new Stack<>();
      }
    }
    return topSort;
  }

  private static boolean topSortUtil(Map<Integer, List<Integer>> graph,
                                     Integer curr,
                                     HashSet<Integer> whiteSet,
                                     HashSet<Integer> greySet,
                                     HashSet<Integer> blackSet,
                                     Stack<Integer> topSort) {

    moveSet(curr, whiteSet, greySet);
    for (Integer neighbor : graph.getOrDefault(curr, new ArrayList<>())) {
      if (blackSet.contains(neighbor)) {
        continue;
      }

      if (greySet.contains(neighbor)) {
        return false;
      }

      if (!topSortUtil(graph, neighbor, whiteSet, greySet, blackSet, topSort)) {
        return false;
      }
    }
    moveSet(curr, greySet, blackSet);
    topSort.push(curr);
    return true;
  }

  static void moveSet(Integer curr, HashSet<Integer> source, HashSet<Integer> target) {
    source.remove(curr);
    target.add(curr);
  }

  /**
   * T(n) = O(V)
   * Space = O(V+E)
   *
   * @param edges
   * @return
   */
  public static Map<Integer, List<Integer>> createGraph(int[][] edges) {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    Arrays.stream(edges)
        .forEach(edge -> graph.compute(edge[0], (v, list) -> list == null ? new ArrayList<>() : list).add(edge[1]));
    return graph;
  }
}
