package leetcode.intervals.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DivideIntervalsIntoMinGroups {
  public int minGroups(int[][] intervals) {
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    for (int[] interval : intervals) {
      if (!maxHeap.isEmpty() && maxHeap.peek() < interval[0]) {
        maxHeap.poll();
      }
      maxHeap.offer(interval[1]);
    }
    return maxHeap.size();
  }

  public static void main(String[] args) {
    DivideIntervalsIntoMinGroups util = new DivideIntervalsIntoMinGroups();
    System.out.println(util.minGroups(new int[][] {{5, 10}, {6, 8}, {1, 5}, {2, 3}, {1, 10}}));
  }
}
