package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class AllInFamily {

  public static void main(String[] args) {
    AllInFamily allInFamily = new AllInFamily();
    int[][] input = {{1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7}, {4, 5}, {4, 8}, {8, 9}, {10, 2}};
    System.out.println(allInFamily.findEarliestAncestor(input, 6));
    System.out.println(allInFamily.findEarliestAncestor(input, 9));
    System.out.println(allInFamily.findEarliestAncestor(input, 4));

    Map<Integer, Set<Integer>> graph = allInFamily.createGraph(input);
    System.out.println(allInFamily.hasCommonAncestor(graph, 6, 9));
    System.out.println(allInFamily.hasCommonAncestor(graph, 6, 4));
    System.out.println(allInFamily.hasCommonAncestor(graph, 6, 3));
    System.out.println(allInFamily.hasCommonAncestor(graph, 3, 9));

    System.out.println();
    System.out.println(allInFamily.hasCommonAncestor1(graph, 6, 9));
    System.out.println(allInFamily.hasCommonAncestor1(graph, 6, 4));
    System.out.println(allInFamily.hasCommonAncestor1(graph, 6, 3));
    System.out.println(allInFamily.hasCommonAncestor1(graph, 3, 9));
  }

  public Node findEarliestAncestor(int[][] input, int node) {
    Map<Integer, Set<Integer>> graph = createGraph(input);
    return find(graph, node);
  }

  private Map<Integer, Set<Integer>> createGraph(int[][] input) {
    Map<Integer, Set<Integer>> graph = new HashMap<>();
    Arrays.stream(input)
        .forEach(arr -> graph.compute(arr[1], (k, v) -> (v == null) ? new HashSet<>() : v).add(arr[0]));
    return graph;
  }

  private Node find(Map<Integer, Set<Integer>> graph, int node) {
    if (!graph.containsKey(node)) {
      return new Node(node, 0);
    }

    Node maxNode = null;
    for (int parent : graph.get(node)) {
      Node curr = find(graph, parent);
      if (maxNode == null) {
        maxNode = curr;
      } else if (maxNode.depth < curr.depth) {
        maxNode = curr;
      }
    }
    return new Node(maxNode.value, maxNode.depth + 1);
  }

  public boolean hasCommonAncestor(Map<Integer, Set<Integer>> graph, int left, int right) {
    List<Integer> leftParents = new ArrayList<>();
    List<Integer> rightParents = new ArrayList<>();
    if (hasCommonAncestor(graph, left, right, leftParents)) {
      return true;
    }
    if (hasCommonAncestor(graph, right, left, rightParents)) {
      return true;
    }
    return leftParents.stream().anyMatch(rightParents::contains);
  }

  private boolean hasCommonAncestor(Map<Integer, Set<Integer>> graph, int parent, int child, List<Integer> ancestors) {
    if (child == parent) {
      return true;
    }

    Set<Integer> parents = graph.get(child);
    if (parents == null) {
      return false;
    }

    ancestors.addAll(parents);
    for (int p : parents) {
      if (hasCommonAncestor(graph, parent, p, ancestors)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasCommonAncestor1(Map<Integer, Set<Integer>> graph, int left, int right) {
    Set<Integer> leftParents = new HashSet<>();
    Stack<Integer> stack = new Stack<>();
    stack.push(left);

    while (!stack.isEmpty()) {
      int curr = stack.pop();
      Set<Integer> parents = graph.getOrDefault(curr, new HashSet<>());
      for (int p : parents) {
        if (p == right) {
          return true;
        }
        stack.push(p);
        leftParents.add(p);
      }
    }

    stack.push(right);
    while (!stack.isEmpty()) {
      int curr = stack.pop();
      Set<Integer> parents = graph.getOrDefault(curr, new HashSet<>());
      for (int p : parents) {
        if (leftParents.contains(p)) {
          return true;
        }
        stack.push(p);
      }
    }
    return false;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  class Node {
    int value;
    int depth;
  }
}
