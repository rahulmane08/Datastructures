package leetcode.medium.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {

  public static void main(String[] args) {
    int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
    System.out.println(Arrays.deepToString(merge1(intervals)));
  }

  public static int[][] merge(int[][] intervals) {
    if (intervals == null || intervals.length < 2) {
      return intervals;
    }
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

    List<int[]> result = new ArrayList<>();
    result.add(intervals[0]);
    int index = 0;
    for (int i = 1; i < intervals.length; i++) {
      int[] curr = intervals[i];
      int[] prev = result.get(index);
      if (isOverlapping(curr, prev)) {
        result.remove(index);
        // merge
        curr = new int[] {Math.min(prev[0], curr[0]), Math.max(prev[1], curr[1])};
        result.add(curr);
      } else {
        result.add(curr);
        index++;
      }
    }
    return result.toArray(new int[0][2]);
  }

  static public int[][] merge1(int[][] intervals) {
    if (intervals.length < 2) {
      return intervals;
    }
    final int maxValue = 18 + 1;
    final int[] starts = new int[maxValue + 1];
    final int[] ends = new int[maxValue + 1];

    int maxSeen = 0;
    for (int i = 0; i < intervals.length; i++) {
      starts[intervals[i][0]]++;
      ends[intervals[i][1]]++;
      maxSeen = Math.max(maxSeen, intervals[i][1]);
    }

    int layers = 0, currentStart = 0;
    final List<int[]> result = new ArrayList<>();

    for (int value = 0; value <= maxSeen; value++) {

      if (starts[value] != 0) {
        if (layers == 0) {
          currentStart = value;
        }
        layers += starts[value];
      }

      if (ends[value] != 0) {
        layers -= ends[value];
        if (layers == 0) {
          result.add(new int[] {currentStart, value});
        }
      }

    }

    return result.toArray(new int[0][]);
  }

  public static boolean isOverlapping(int[] curr, int[] next) {
    return (next[0] <= curr[0] && curr[0] <= next[1])
        || (next[0] <= curr[1] && curr[1] <= next[1])
        || (curr[0] <= next[0] && next[0] <= curr[1])
        || (curr[0] <= next[1] && next[1] <= curr[1]);
  }
}
