package leetcode.trees.hard;

import datastructures.tree.Node;
import datastructures.tree.TreeUtils;

public class DistanceBetweenAnyTwoNodes {
  public int distanceBetweenAnyTwoNodes(Node root, int data1, int data2) {
    Node lca = TreeUtils.lca(root, data1, data2);
    if (lca == null) {
      return -1;
    }
    int depth1 = TreeUtils.depth(lca, data1);
    if (depth1 == -1) {
      return -1;
    }

    int depth2 = TreeUtils.depth(lca, data2);
    if (depth2 == -1) {
      return -1;
    }

    return depth1 + depth2;
  }
}
