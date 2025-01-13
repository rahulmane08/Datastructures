package leetcode.graph.easy;

/**
 * 1. Check if no cycles.
 * 2. Start from vertex and check if all vertices are traced.
 */
public class CheckIfGraphIsTree {
  public boolean check(int[][] edges) {
    return new CheckIfGraphIsConnected().check(edges);
  }
}
