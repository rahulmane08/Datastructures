package leetcode.math.medium;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KthLargestNumber {
  public int findKthLargest(int[] nums, int k) {
    if (k > nums.length) {
      return -1;
    }

    PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.naturalOrder());
    for (int i : nums) {
      if (minHeap.size() == k) {
        if (i > minHeap.peek()) {
          minHeap.poll();
          minHeap.offer(i);
        }
      } else {
        minHeap.offer(i);
      }
    }
    return minHeap.peek();
  }

  public static void main(String[] args) {
    KthLargestNumber util = new KthLargestNumber();
    System.out.println(util.findKthLargest(new int[] {3, 2, 1, 5, 6, 4}, 2));
  }
}
