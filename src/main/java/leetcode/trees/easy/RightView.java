package leetcode.trees.easy;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import leetcode.trees.TreeNode;

public class RightView {

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
    System.out.println(new RightView().rightView(_1));
  }

  public List<Integer> rightView(TreeNode root) {
    Map<Integer, Integer> levels = new TreeMap<>();
    compute(root, 0, levels);
    return levels.values().stream().toList();
  }

  void compute(TreeNode root, int level, Map<Integer, Integer> levels) {
    if (root == null) {
      return;
    }
    levels.putIfAbsent(level, root.val);
    compute(root.right, level + 1, levels);
    compute(root.left, level + 1, levels);
  }
}
