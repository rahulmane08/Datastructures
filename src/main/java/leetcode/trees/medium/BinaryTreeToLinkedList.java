package leetcode.trees.medium;

import java.util.ArrayList;
import java.util.List;
import leetcode.trees.TreeNode;

public class BinaryTreeToLinkedList {
  public void flatten(TreeNode root) {
    List<TreeNode> preorderList = new ArrayList<>();
    populate(root, preorderList);
    for (int i = 0; i < preorderList.size() - 1; i++) {
      TreeNode curr = preorderList.get(i);
      curr.right = preorderList.get(i + 1);
      curr.left = null;
    }
  }

  private void populate(TreeNode root, List<TreeNode> preorderList) {
    if (root == null) {
      return;
    }
    preorderList.add(root);
    populate(root.left, preorderList);
    populate(root.right, preorderList);
  }
}
