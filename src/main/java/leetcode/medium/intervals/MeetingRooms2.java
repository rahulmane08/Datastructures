package leetcode.medium.intervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRooms2 {

  public static void main(String[] args) {
    MeetingRooms2 util = new MeetingRooms2();
    System.out.println(util.minMeetingRooms1(new int[][] {{0, 30}, {5, 10}, {15, 20}}));
  }

  public int minMeetingRooms(int[][] intervals) {
    if (intervals == null || intervals.length == 0) {
      return 0;
    }

    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
    PriorityQueue<Integer> minPQ = new PriorityQueue<>(Comparator.naturalOrder());
    minPQ.add(intervals[0][1]);
    for (int i = 1; i < intervals.length; i++) {
      int[] curr = intervals[i];
      if (curr[0] >= minPQ.peek()) {
        minPQ.poll();
      }
      minPQ.offer(curr[1]);
    }
    return minPQ.size();
  }

  public int minMeetingRooms1(int[][] intervals) {
    PriorityQueue<int[]> rooms = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
    for (int[] curr : intervals) {
      for (; !rooms.isEmpty() && rooms.peek()[1] <= curr[0]; rooms.poll()) ;
      rooms.offer(curr);
    }
    return rooms.size();
  }
}
