package leetcode.intervals.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {

  public static void main(String[] args) {
    int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
    System.out.println(Arrays.deepToString(merge1(intervals)));
    intervals = new int[][] {{1, 3}};
    System.out.println(Arrays.deepToString(merge1(intervals)));
  }

  /**
   * T(n) = O(n)
   * S(n) = O(n)
   *
   * @param intervals
   * @return
   */
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

  public static int[][] merge1(int[][] intervals) {
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
    List<int[]> result = new ArrayList<>();
    int[] prev = intervals[0];
    for (int i = 0; i < intervals.length; i++) {
      int[] curr = intervals[i];
      if (isOverlapping(prev, curr)) {
        prev = new int[] {Math.min(prev[0], curr[0]), Math.max(prev[1], curr[1])};
      } else {
        result.add(prev);
        prev = curr;
      }
    }
    result.add(prev);
    return result.toArray(new int[0][]);
  }

  public static boolean isOverlapping(int[] curr, int[] next) {
    return (next[0] <= curr[0] && curr[0] <= next[1])
        || (next[0] <= curr[1] && curr[1] <= next[1])
        || (curr[0] <= next[0] && next[0] <= curr[1])
        || (curr[0] <= next[1] && next[1] <= curr[1]);
  }
}
