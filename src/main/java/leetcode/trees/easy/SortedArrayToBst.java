package leetcode.trees.easy;

import leetcode.trees.TreeNode;

public class SortedArrayToBst {
  public TreeNode sortedArrayToBST(int[] nums) {
    if (nums == null) {
      return null;
    }
    return sortedArrayToBST(nums, 0, nums.length - 1);
  }

  TreeNode sortedArrayToBST(int[] nums, int left, int right) {
    if (left > right) {
      return null;
    }
    int mid = (left + right) >>> 1;
    TreeNode root = new TreeNode(nums[mid]);
    root.left = sortedArrayToBST(nums, left, mid - 1);
    root.right = sortedArrayToBST(nums, mid + 1, right);
    return root;
  }
}
