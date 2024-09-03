package leetcode.trees.easy;

import datastructures.tree.Node;
import datastructures.tree.TreeUtils;

/**
 * Given a Binary Tree. Return true if, for every node X in the tree other than the leaves,
 * its value is equal to the sum of its left subtree's value and its right subtree's value.
 * Else return false. An empty tree is also a Sum Tree as the sum of an empty tree can be considered to be 0.
 * A leaf node is also considered a Sum Tree.
 */
public class TreeCheckIfSumTree {
  int checkIfSumTreeUtil(Node root) {
    if (root == null) {
      return 0;
    }
    if (TreeUtils.isLeaf(root)) {
      return root.data;
    }
    int left = checkIfSumTreeUtil(root.left);
    int right = checkIfSumTreeUtil(root.right);
    if (root.data != left + right) {
      throw new RuntimeException("Tree is not a sum tree");
    }
    return 2 * root.data;
  }
}
