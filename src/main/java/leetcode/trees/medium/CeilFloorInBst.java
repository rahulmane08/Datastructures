package leetcode.trees.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import leetcode.trees.TreeNode;

/**
 * https://leetcode.com/problems/closest-nodes-queries-in-a-binary-search-tree/description/
 */
public class CeilFloorInBst {
  public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
    List<List<Integer>> result = new ArrayList<>();
    for (Integer x : queries) {
      result.add(closestNodes(root, x));
    }
    return result;
  }

  public List<Integer> closestNodes(TreeNode root, Integer x) {
    Integer[] result = new Integer[] {-1, -1};
    while (root != null) {
      if (root.val == x) {
        result[0] = x;
        result[1] = x;
        break;
      } else if (x < root.val) {
        result[1] = root.val;
        root = root.left;
      } else {
        result[0] = root.val;
        root = root.right;
      }
    }
    return Arrays.asList(result);
  }

  private Integer floor(TreeNode root, Integer x) {
    if (root == null) {
      return -1;
    }
    if (root.val == x) {
      return root.val;
    }

    Integer floor;
    if (x < root.val) {
      floor = floor(root.left, x);
    } else {
      floor = floor(root.right, x);
    }

    if (floor == -1 && root.val < x) {
      floor = root.val;
    }
    return floor;
  }

  private Integer ceil(TreeNode root, Integer x) {
    if (root == null) {
      return -1;
    }
    if (root.val == x) {
      return root.val;
    }

    Integer ceil;
    if (x < root.val) {
      ceil = ceil(root.left, x);
    } else {
      ceil = ceil(root.right, x);
    }

    if (ceil == -1 && root.val > x) {
      ceil = root.val;
    }
    return ceil;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(7);
    TreeNode _4 = new TreeNode(4);
    TreeNode _10 = new TreeNode(10);
    root.left = _4;
    root.right = _10;
    TreeNode _2 = new TreeNode(2);
    TreeNode _6 = new TreeNode(6);
    _4.left = _2;
    _4.right = _6;
    TreeNode _9 = new TreeNode(9);
    TreeNode _11 = new TreeNode(11);
    _10.left = _9;
    _10.right = _11;

    CeilFloorInBst util = new CeilFloorInBst();
    System.out.println(util.closestNodes(root, Arrays.asList(3, 6, 8, 12)));
  }
}
