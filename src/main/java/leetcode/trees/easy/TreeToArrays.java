package leetcode.trees.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import leetcode.trees.TreeNode;

public class TreeToArrays {
  public Integer[] inorderArray(TreeNode root) {
    List<Integer> inorderList = new ArrayList<>();
    fillInorder(root, inorderList);
    return inorderList.toArray(new Integer[inorderList.size()]);
  }

  void fillInorder(TreeNode root, List<Integer> inorderList) {
    if (root == null) {
      return;
    }
    fillInorder(root.left, inorderList);
    inorderList.add(root.val);
    fillInorder(root.right, inorderList);
  }

  public Integer[] preorderArray(TreeNode root) {
    List<Integer> preorderList = new ArrayList<>();
    fillPreorder(root, preorderList);
    return preorderList.toArray(new Integer[preorderList.size()]);
  }

  void fillPreorder(TreeNode root, List<Integer> preorderList) {
    if (root == null) {
      return;
    }
    preorderList.add(root.val);
    fillPreorder(root.left, preorderList);
    fillPreorder(root.right, preorderList);
  }

  public Integer[] postorderArray(TreeNode root) {
    List<Integer> postorderList = new ArrayList<>();
    fillPostorder(root, postorderList);
    return postorderList.toArray(new Integer[postorderList.size()]);
  }

  void fillPostorder(TreeNode root, List<Integer> postorderList) {
    if (root == null) {
      return;
    }
    fillPostorder(root.left, postorderList);
    fillPostorder(root.right, postorderList);
    postorderList.add(root.val);
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
    TreeToArrays util = new TreeToArrays();
    System.out.println(Arrays.toString(util.inorderArray(_1)));
    System.out.println(Arrays.toString(util.preorderArray(_1)));
    System.out.println(Arrays.toString(util.postorderArray(_1)));
  }
}
