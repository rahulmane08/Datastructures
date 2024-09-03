package leetcode.trees.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/description/
 */
public class TreeVerticalOrderTraversal {
  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  public List<List<Integer>> verticalTraversal(TreeNode root) {
    Map<Integer, List<Integer>> levels = new TreeMap<>();
    compute(root, 0, levels);
    return new ArrayList<>(levels.values());
  }

  private void compute(TreeNode root, int level, Map<Integer, List<Integer>> levels) {
    if (root == null) {
      return;
    }
    levels.compute(level, (l, n) -> n != null ? n : new ArrayList<>()).add(root.val);
    compute(root.left, level - 1, levels);
    compute(root.right, level + 1, levels);
  }
}
