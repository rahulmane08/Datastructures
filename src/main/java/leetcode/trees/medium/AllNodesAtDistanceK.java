package leetcode.trees.medium;

import static leetcode.trees.ArrayToTreeUtil.compute;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import leetcode.trees.TreeNode;

/**
 * 5
 * 4
 * 3  6
 * 1  2
 * 11   14
 * <p>
 * for Node 3:
 * - childrens at distance = 2 are 11 and 14.
 * - ancestor 5 is at a distance 2.
 * - sibling 6 from common ancestor 4 is at a distance 2.
 */

public class AllNodesAtDistanceK {
  public static void main(String[] args) {
   /* TreeNode root = new TreeNode(0);
    TreeNode _1 = new TreeNode(1);
    TreeNode _2 = new TreeNode(2);
    TreeNode _3 = new TreeNode(3);
    TreeNode _4 = new TreeNode(4);
    TreeNode _5 = new TreeNode(5);
    TreeNode _6 = new TreeNode(6);
    TreeNode _7 = new TreeNode(7);

    root.left = _1;
    _1.left = _2;
    _1.right = _3;
    _2.left = _4;
    _2.right = _5;
    _4.left = _6;
    _5.right = _7;*/

    Integer[] arr = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
    TreeNode root = compute(arr, 0, arr.length - 1);

    AllNodesAtDistanceK util = new AllNodesAtDistanceK();
    //System.out.println(util.distanceK(root, _2, 2));
  }

  public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
    List<Integer> result = new ArrayList<>();
    Stack<TreeNode> ancestors = new Stack<>();
    if (!ancestors.isEmpty()) {
      // ancestors present
      TreeNode parent = ancestors.pop();
      computeForChildren(parent, k - 1, result);

      for (int i = 0; i < k && !ancestors.isEmpty(); i++) {
        TreeNode grandParent = ancestors.pop();
        if (i == k - 1) {
          result.add(grandParent.val);
        }
      }
    }
    // compute for children.
    computeForChildren(target, k, result);
    return result;
  }

  boolean computeAncestorsFor(TreeNode root, TreeNode target, Stack<TreeNode> ancestors) {
    if (root == null) {
      return false;
    }
    if (root.val == target.val) {
      return true;
    }
    ancestors.push(root);
    if (computeAncestorsFor(root.left, target, ancestors) || computeAncestorsFor(root.right, target, ancestors)) {
      return true;
    }
    ancestors.pop();
    return false;
  }

  void computeForChildren(TreeNode root, int k, List<Integer> result) {
    if (root == null || k < 0) {
      return;
    }
    if (k == 0) {
      result.add(root.val);
      return;
    }
    computeForChildren(root.left, k - 1, result);
    computeForChildren(root.right, k - 1, result);
  }
}
