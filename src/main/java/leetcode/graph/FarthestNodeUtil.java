package leetcode.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class FarthestNodeUtil {
  private final Map<Integer, List<Integer>> graph;

  public FarthestNodeUtil(Map<Integer, List<Integer>> graph) {
    this.graph = graph;
  }

  public FarthestNode findFarthestNode(Integer vertex) {
    FarthestNode farthestNode = new FarthestNode(vertex);
    // dfs(-1, vertex, farthestNode, 0);
    return bfs(vertex);
  }

  private void dfs(Integer parent, Integer vertex, FarthestNode farthestNode, Integer currDepth) {
    if (currDepth > farthestNode.getDepth()) {
      farthestNode.setDepth(currDepth);
      farthestNode.setFarthestNode(vertex);
    }
    for (Integer neighbor : graph.get(vertex)) {
      if (neighbor != parent) {
        dfs(vertex, neighbor, farthestNode, currDepth + 1);
      }
    }
  }

  public FarthestNode bfs(Integer vertex) {
    FarthestNode farthestNode = new FarthestNode(vertex);
    Queue<int[]> bfs = new LinkedList<>();
    bfs.offer(new int[]{vertex, -1, 0, 0});
    while (!bfs.isEmpty()) {
      int[] elem = bfs.poll();
      int curr = elem[0];
      int parent = elem[1];
      int depth = elem[2];

      if (depth > farthestNode.getDepth()) {
        farthestNode.setDepth(depth);
        farthestNode.setFarthestNode(curr);
      }

      for (Integer neighbor : graph.get(curr)) {
        if (neighbor != parent) {
          bfs.offer(new int[] {neighbor, curr, depth + 1});
        }
      }
    }
    return farthestNode;
  }
}
