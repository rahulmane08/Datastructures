package leetcode.trees.medium;

import datastructures.tree.Node;
import datastructures.tree.TreeUtils;
import interfaces.Important;

/**
 * POST ORDER
 */
@Important
public class InternalNodeWithOneChildChecker {
  private final Node root;
  private boolean check = false;

  public InternalNodeWithOneChildChecker(Node root) {
    this.root = root;
    this.check = checkIfEachInternalHasOneChild(root);
  }

  private boolean checkIfEachInternalHasOneChild(Node root) {
    if (root == null || TreeUtils.isLeaf(root)) {
      return true;
    }
    if (!checkIfEachInternalHasOneChild(root.left)) {
      return false;
    }
    if (!checkIfEachInternalHasOneChild(root.right)) {
      return false;
    }
    if (this.root.equals(root)) // root is not an internal node, so skip
    {
      return true;
    }

    int children = 0;
    if (root.left != null) {
      children++;
    }
    if (root.right != null) {
      children++;
    }
    return children % 2 == 1;
  }

  public boolean isCheck() {
    return check;
  }
}
