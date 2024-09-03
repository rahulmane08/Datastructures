package leetcode.trees.easy;

import com.sun.source.tree.Tree;
import datastructures.tree.TreeUtils;
import java.util.ArrayList;
import java.util.List;
import leetcode.trees.TreeNode;

public class AllRootToLeafPaths {
  public List<String> binaryTreePaths(TreeNode root) {
    List<String> paths = new ArrayList<>();
    compute(root, "", paths);
    return paths;
  }

  void compute(TreeNode root, String path, List<String> paths) {
    if (root == null) {
      return;
    }

    String currPath = path;
    if (!currPath.isEmpty()) {
      currPath += "->" ;
    }
    currPath += root.val;

    if (root.left == null && root.right == null) {
      paths.add(currPath);
      return;
    }

    compute(root.left, currPath, paths);
    compute(root.right, currPath, paths);
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    TreeNode _2 = new TreeNode(2);
    _2.right = new TreeNode(5);
    root.left = _2;
    root.right = new TreeNode(3);
    AllRootToLeafPaths util = new AllRootToLeafPaths();
    System.out.println(util.binaryTreePaths(root));
  }
}
