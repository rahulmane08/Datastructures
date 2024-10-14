package leetcode.trees.medium;

import java.util.ArrayList;
import java.util.List;
import leetcode.trees.TreeNode;

public class FirstRootToLeafPath {
  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    TreeNode _2 = new TreeNode(2);
    _2.right = new TreeNode(5);
    root.left = _2;
    root.right = new TreeNode(3);
    FirstRootToLeafPath util = new FirstRootToLeafPath();
    System.out.println(util.firstRootToLeafTreePath(root));
  }

  public String firstRootToLeafTreePath(TreeNode root) {
    List<String> paths = new ArrayList<>();
    compute(root, "", paths);
    return paths.get(0);
  }

  boolean compute(TreeNode root, String path, List<String> paths) {
    if (root == null) {
      return false;
    }

    String currPath = path;
    if (!currPath.isEmpty()) {
      currPath += "->";
    }
    currPath += root.val;

    if (root.left == null && root.right == null) {
      paths.add(currPath);
      return true;
    }

    return compute(root.left, currPath, paths) || compute(root.right, currPath, paths);
  }
}
