package leetcode.trees.medium;

import leetcode.trees.TreeNode;

/**
 * https://leetcode.com/problems/add-one-row-to-tree/description/
 */
public class AddOneRowAtGivenDepth {
  public TreeNode addOneRow(TreeNode root, int val, int depth) {
    return compute(root, val, depth, true);
  }

  public TreeNode compute(TreeNode root, int val, int depth, boolean left) {
    if (root == null && depth != 1) {
      return null;
    }
    if (depth == 1) {
      TreeNode newNode = new TreeNode(val);
      if (left) {
        newNode.left = root;
      } else {
        newNode.right = root;
      }
      return newNode;
    }
    root.left = compute(root.left, val, depth - 1, true);
    root.right = compute(root.right, val, depth - 1, false);
    return root;
  }

  public static void main(String[] args) {
    TreeNode _1 = new TreeNode(1);
    TreeNode _2 = new TreeNode(2);
    TreeNode _3 = new TreeNode(3);
    TreeNode _4 = new TreeNode(4);
    TreeNode _5 = new TreeNode(5);
    _1.left = _2;
    _1.right = _3;
    _2.left = _4;
    _2.right = _5;
  }
}
