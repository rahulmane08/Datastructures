package leetcode.graph.easy;

import static leetcode.graph.CycleDetectionUtil.detectCycle;

/**
 * 1. Check if no cycles.
 * 2. Start from vertex and check if all vertices are traced.
 */
public class CheckIfGraphIsTree {
  public boolean check(int[][] edges, boolean directed) {
    if (!detectCycle(edges, directed)) {
      return false;
    }
    return new CheckIfGraphIsConnected().check(edges, directed);
  }
}
