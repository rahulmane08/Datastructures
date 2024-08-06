package leetcode.medium.intervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRooms2 {

    public static int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> minPQ = new PriorityQueue<>(Comparator.naturalOrder());
        minPQ.add(intervals[0][1]);
        for (int i=1; i<intervals.length; i++) {
            int [] curr = intervals[i];
            if (curr[0] >= minPQ.peek()) {
                minPQ.poll();
            }
            minPQ.offer(curr[1]);
        }
        return minPQ.size();
    }
}
