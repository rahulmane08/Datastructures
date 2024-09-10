package leetcode.misc.medium.intervals;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Example 1:
 * <p>
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 * Example 2:
 * <p>
 * Input: intervals = [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
 * Example 3:
 * <p>
 * Input: intervals = [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 */
public class EraseOverlappingIntervals {

  public static void main(String[] args) {
    EraseOverlappingIntervals util = new EraseOverlappingIntervals();
    System.out.println(util.eraseOverlapIntervals1(new int[][] {{1, 2}, {2, 3}, {3, 4}, {1, 3}}));
  }

  public int eraseOverlapIntervals(int[][] intervals) {
    if (intervals == null) {
      return 0;
    }

    int n = intervals.length;
    int countOfOverlappingIntervals = 0;
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
    int[] prev = intervals[0];
    for (int i = 1; i < n; i++) {
      int[] curr = intervals[i];
      if (prev[1] > curr[0]) { // two intervals are overlapping.
        countOfOverlappingIntervals++; // increment the count

        // to choose which one to remove
        // case1 : if the prev is totally eclipsing the current one, erase prev as its the larger interval.
        // case2: if they partially overlap then we consider the prev, and erase curr.
        if (prev[1] > curr[1]) {
          // the previous interval eclipses the current interval totally, remove this from consideration
          prev = curr;
        }
      } else {
        // non overlapping intervals, keep moving forward
        prev = curr;
      }
    }
    return countOfOverlappingIntervals;
  }

  public int eraseOverlapIntervals1(int[][] intervals) {
    int minEnd = intervals[0][1];
    int maxEnd = intervals[0][1];

    for (int[] interval : intervals) {
      minEnd = Math.min(minEnd, interval[1]);
      maxEnd = Math.max(maxEnd, interval[1]);
    }

    int[] intervalStartPoints = new int[maxEnd - minEnd + 2];
    for (int[] interval : intervals) {
      int start = interval[0] - minEnd + 1;
      int end = interval[1] - minEnd + 1;
      intervalStartPoints[end] = Math.max(start, intervalStartPoints[end]);
    }

    int nonOverlapIntervalsCount = 1;
    int lastNonOverlappingEnd = 1;
    for (int i = 2; i < intervalStartPoints.length; i++) {
      if (lastNonOverlappingEnd <= intervalStartPoints[i]) {
        nonOverlapIntervalsCount++;
        lastNonOverlappingEnd = i;
      }
    }

    return intervals.length - nonOverlapIntervalsCount;
  }
}
