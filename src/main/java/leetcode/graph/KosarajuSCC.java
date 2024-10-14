package leetcode.graph;

import static leetcode.graph.TopologicalSortUtil.topSortDfs;
import static leetcode.graph.TopologicalSortUtil.topSortDfsUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * * A directed graph is strongly connected if there is a path between all pairs of vertices.
 * * <p>
 * * Its a 2 pass algorithm.
 * * 1. Time Complexity: O(V+E)
 * * 2. Space Complexity: O(V)
 * <p>
 * Kosaraju SCC
 * 1. topsort graph.
 * 2. reverse graph.
 * 3. topSort again based on the finishTime order obtained from step 1. Each vertex topsort will reveal the
 * SCC.
 */
public class KosarajuSCC {
  public static void main(String[] args) {
    int[][] edges = {{1, 2}, {2, 3}, {3, 1}, {1, 4}};
    KosarajuSCC util = new KosarajuSCC();
    System.out.println(util.getAllStronglyConnectedComponents(edges));
  }

  public List<Stack<Integer>> getAllStronglyConnectedComponents(int[][] edges) {
    Graph graph = new Graph(edges, true);
    return getAllStronglyConnectedComponents(graph);
  }

  public List<Stack<Integer>> getAllStronglyConnectedComponents(Graph graph) {
    // topsort
    Stack<Integer> topSort = new Stack<>();
    topSortDfs(graph, new HashSet<>(), topSort);

    // reverse graph.
    Graph reversedGraph = graph.reverse();
    HashSet<Integer> visited = new HashSet<>();

    List<Stack<Integer>> strongyConnectedComponents = new ArrayList<>();
    while (!topSort.isEmpty()) {
      Integer current = topSort.pop();
      if (!visited.contains(current)) {
        Stack<Integer> scc = new Stack<>();
        topSortDfsUtil(reversedGraph, current, visited, scc);
        strongyConnectedComponents.add(scc);
      }
    }
    return strongyConnectedComponents;
  }
}