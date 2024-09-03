package leetcode.trees.medium;

import java.util.LinkedList;
import java.util.Queue;
import leetcode.trees.TreeNode;

public class LevelWithMaxSum {
  public int maxLevelSum(TreeNode root) {
    if (root == null) {
      return -1;
    }
    int maxLevel = 1;
    int maxSum = root.val;
    TreeNode marker = new TreeNode();

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    queue.offer(marker);
    int levelSum = 0;
    int level = 1;
    while (!queue.isEmpty()) {
      TreeNode curr = queue.poll();
      levelSum += curr.val;
      if (curr == marker) {
        // end of current level
        if (levelSum > maxSum) {
          maxLevel = level;
          maxSum = levelSum;
        }
        levelSum = 0;
        level++;
        if (!queue.isEmpty()) {
          queue.offer(marker); // mark the end of next level
        }
        continue;
      }

      if (curr.left != null) {
        queue.offer(curr.left);
      }
      if (curr.right != null) {
        queue.offer(curr.right);
      }
    }
    return maxLevel;
  }

  public static void main(String[] args) {

  }
}
