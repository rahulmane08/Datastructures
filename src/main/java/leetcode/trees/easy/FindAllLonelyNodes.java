package leetcode.trees.easy;

import java.util.ArrayList;
import java.util.List;
import leetcode.trees.TreeNode;

/**
 * https://leetcode.com/problems/find-all-the-lonely-nodes/description/
 */
public class FindAllLonelyNodes {
  public List<Integer> getLonelyNodes(TreeNode root) {
    List<Integer> lonelyNodes = new ArrayList<>();
    lonelyNodeUtil(root, lonelyNodes, 0);
    return lonelyNodes;
  }

  private void lonelyNodeUtil(TreeNode root, List<Integer> lonelyNodes, int childCount) {
    if (root == null) {
      return;
    }

    if (isLeaf(root)) {
      if (childCount == 1) {
        lonelyNodes.add(root.val);
      }
      return;
    }

    int children = 0;
    if (root.left != null) {
      children++;
    }
    if (root.right != null) {
      children++;
    }
    lonelyNodeUtil(root.left, lonelyNodes, children);
    lonelyNodeUtil(root.right, lonelyNodes, children);
  }

  private boolean isLeaf(TreeNode root) {
    return root.left == null && root.right == null;
  }
}
