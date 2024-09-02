package leetcode.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/find-median-from-data-stream/
 */
public class MedianFinder {

  private final PriorityQueue<Integer> maxHeap;
  private final PriorityQueue<Integer> minHeap;
  private double median;

  public MedianFinder() {
    this.minHeap = new PriorityQueue<>(Comparator.naturalOrder());
    this.maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    this.median = 0d;
  }

  public static void main(String[] args) {
    MedianFinder util = new MedianFinder();
    util.addNum(1);
    System.out.println(util.findMedian());
    util.addNum(2);
    System.out.println(util.findMedian());
    util.addNum(3);
    System.out.println(util.findMedian());
  }

  public void addNum(int num) {
    if (maxHeap.size() == minHeap.size()) {

      // at the begining heaps are empty
      if (maxHeap.size() == 0) {
        maxHeap.offer(num);
        median = num;
        return;
      }

      if (median < num) {
        maxHeap.offer(minHeap.poll());
        minHeap.offer(num);
        median = maxHeap.peek();
      } else {
        minHeap.offer(maxHeap.poll());
        maxHeap.offer(num);
        median = minHeap.peek();
      }
    } else if (maxHeap.size() > minHeap.size()) {
      if (median < num) {
        minHeap.offer(num);
      } else {
        minHeap.offer(maxHeap.poll());
        maxHeap.offer(num);
      }
      median = (Double.valueOf(maxHeap.peek()) + Double.valueOf(minHeap.peek())) / 2;
    } else {
      if (median < num) {
        maxHeap.offer(num);
      } else {
        maxHeap.offer(minHeap.poll());
        minHeap.offer(num);
      }
      median = (Double.valueOf(maxHeap.peek()) + Double.valueOf(minHeap.peek())) / 2;
    }
  }

  public double findMedian() {
    return median;
  }
}
