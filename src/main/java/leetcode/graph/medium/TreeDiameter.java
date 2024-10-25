package leetcode.graph.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import leetcode.graph.FarthestNode;
import leetcode.graph.FarthestNodeUtil;

public class TreeDiameter {
  public int treeDiameter(int[][] edges) {
    if (edges == null || edges.length == 0) {
      return 0;
    }
    Map<Integer, List<Integer>> graph = new HashMap<>();

    Arrays.stream(edges).forEach(e -> {
      graph.compute(e[0], (v, list) -> list == null ? new ArrayList<>() : list).add(e[1]);
      graph.compute(e[1], (v, list) -> list == null ? new ArrayList<>() : list).add(e[0]);
    });

    FarthestNodeUtil util = new FarthestNodeUtil(graph);
    FarthestNode farthestNode = util.findFarthestNode(graph.keySet().stream().findFirst().get());
    farthestNode = util.findFarthestNode(farthestNode.getFarthestNode());
    return farthestNode.getDepth();
  }

  /* gives TLE
  public int treeDiameter1(int[][] edges) {
    Map<Integer, List<Integer>> graph = new HashMap<>();

    Arrays.stream(edges).forEach(e -> {
      graph.compute(e[0], (v, list) -> list == null ? new ArrayList<>() : list).add(e[1]);
      graph.compute(e[1], (v, list) -> list == null ? new ArrayList<>() : list).add(e[0]);
    });
    int diameter = 0;
    for (Integer vertex : graph.keySet()) {
      diameter = Math.max(diameter, diameter(graph, -1, vertex));
    }
    return diameter;
  }

  private int diameter(Map<Integer, List<Integer>> graph, Integer parent, Integer vertex) {
    List<Integer> neighbors = graph.get(vertex);

    // 1 -> 2 -> 3. for vertex = 3, parent will be 2 and neighbor of 3 is also do, so we dont need to go back
    // else this will run in infinite loop.
    if (neighbors.size() == 1 && neighbors.contains(parent)) {
      return 0;
    }
    int diameter = 0;
    for (Integer neighbor : neighbors) {
      if (neighbor != parent) { // making sure we dont go back to parent neighbor to avoid infinite loop.
        diameter = Math.max(diameter, diameter(graph, vertex, neighbor));
      }
    }
    return 1 + diameter;
  }*/

  public static void main(String[] args) {
    TreeDiameter util = new TreeDiameter();
    int[][] edges = new int[][] {{0, 1}, {1, 2}, {0, 3}, {3, 4}, {2, 5}, {3, 6}};
    System.out.println(util.treeDiameter(edges));
  }
}
