package leetcode.trees.easy;

import datastructures.tree.Node;

public class AddTrees {
  public Node addTrees(Node root1, Node root2) {
    if (root1 == null && root2 == null) {
      return null;
    }
    if (root2 == null) {
      return new Node(root1.data);
    }
    if (root1 == null) {
      return new Node(root2.data);
    }
    Node mergedNode = new Node(root1.data + root2.data);
    mergedNode.left = addTrees(root1.left, root2.left);
    mergedNode.right = addTrees(root1.right, root2.right);
    return mergedNode;
  }
}
