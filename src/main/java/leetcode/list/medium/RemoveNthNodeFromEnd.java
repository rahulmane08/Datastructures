package leetcode.list.medium;

import leetcode.list.ListNode;

public class RemoveNthNodeFromEnd {

  public ListNode removeNthFromEnd(ListNode head, int n) {
    int[] count = new int[] {n};
    return compute(head, count);
  }

  ListNode compute(ListNode head, int[] count) {
    if (head == null) {
      return null;
    }
    ListNode next = compute(head.next, count);
    if (--count[0] == 0) {
      return next;
    }
    head.next = next;
    return head;
  }
}
