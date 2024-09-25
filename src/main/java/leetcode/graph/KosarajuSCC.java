package leetcode.graph;

import static leetcode.graph.TopologicalSortUtil.topSortDfs;
import static leetcode.graph.TopologicalSortUtil.topSortDfsUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class KosarajuSCC {
  public List<Stack<Integer>> getAllStronglyConnectedComponents(Graph graph) {
    Stack<Integer> topSort = new Stack<>();
    topSortDfs(graph, new HashSet<>(), topSort);
    Graph reversedGraph = graph.reverse();
    HashSet<Integer> visited = new HashSet<>();
    List<Stack<Integer>> strongyConnectedComponents = new ArrayList<>();
    while (!topSort.isEmpty()) {
      topSort = new Stack<>();
      topSortDfsUtil(reversedGraph, topSort.pop(), visited, topSort);
      strongyConnectedComponents.add(topSort);
    }
    return strongyConnectedComponents;
  }
}
