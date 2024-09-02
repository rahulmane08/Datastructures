package leetcode.medium;

import datastructures.pq.MinPriorityQueue;

/**
 * Input: a[] = {20, -5, -1}
 * k = 3
 * Output: 14
 * Explanation: All sum of contiguous
 * subarrays are (20, 15, 14, -5, -6, -1)
 * so the 3rd largest sum is 14.
 * <p>
 * Input: a[] = {10, -10, 20, -40}
 * k = 6
 * Output: -10
 * Explanation: The 6th largest sum among
 * sum of all contiguous subarrays is -10.
 */
public class KthLargestContiguousSum {

  public int findKthLargestContiguousSum(int[] arr, int k) {
    if (arr == null) {
      return -1;
    }

    MinPriorityQueue<Integer> minPq = new MinPriorityQueue<>(k);
    for (int i = 0; i < arr.length; i++) {
      int sum = arr[i];
      addIfPossible(k, minPq, sum);
      for (int j = i + 1; j < arr.length; j++) {
        sum += arr[j];
        addIfPossible(k, minPq, sum);
      }
    }
    return minPq.peek();
  }

  void addIfPossible(int k, MinPriorityQueue<Integer> minPq, int x) {
    if (minPq.getSize() < k) {
      minPq.add(x);
    } else {
      if (minPq.peek() < x) {
        minPq.poll();
        minPq.add(x);
      }
    }
  }
}
