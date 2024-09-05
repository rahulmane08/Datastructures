package leetcode.trees;

public class ArrayToTreeUtil {

  public static TreeNode compute(Integer[] arr) {
    return compute(arr, 0, arr.length);
  }

  public static TreeNode compute(Integer[] arr, int index, int n) {
    if (index >= n) {
      return null;
    }
    if (arr[index] == null) {
      return null;
    }
    TreeNode root = new TreeNode(arr[index]);
    root.left = compute(arr, 2 * index + 1, n);
    root.right = compute(arr, 2 * index + 2, n);
    return root;
  }
}
