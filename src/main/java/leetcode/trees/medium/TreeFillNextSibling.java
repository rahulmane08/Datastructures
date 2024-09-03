package leetcode.trees.medium;

import java.util.LinkedList;
import java.util.Queue;
import leetcode.trees.Node;

public class TreeFillNextSibling {

  public Node connect(Node root) {
    if (root == null) {
      return null;
    }
    Node marker = new Node();
    Queue<Node> queue = new LinkedList<>();
    queue.offer(root);
    queue.offer(marker);
    while (!queue.isEmpty()) {
      Node curr = queue.poll();
      if (curr == marker && !queue.isEmpty()) {
        // end of current level
        queue.offer(marker); // mark the end of next level
        continue;
      }
      if (!queue.isEmpty() && queue.peek() != marker) {
        curr.next = queue.peek();
      }
      if (curr.left != null) {
        queue.offer(curr.left);
      }
      if (curr.right != null) {
        queue.offer(curr.right);
      }
    }
    return root;
  }
}
