package leetcode.misc.medium.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/insert-interval/description/
 */
public class InsertInterval {

  public static void main(String[] args) {
    InsertInterval util = new InsertInterval();

    System.out.println(Arrays.deepToString(util.insert(new int[][] {{1, 3}, {6, 9}}, new int[] {2, 5})));
    System.out.println(Arrays.deepToString(util.insert(new int[][] {{1, 2}, {6, 9}}, new int[] {3, 5})));
    System.out.println(Arrays.deepToString(util.insert(new int[][] {{3, 5}, {6, 9}}, new int[] {1, 2})));
    System.out.println(Arrays.deepToString(util.insert(new int[][] {}, new int[] {2, 5})));
  }

  /**
   * T(n) = n
   * Case 1: newInterval is the smallest and no merge
   * Case 2: newInterval is the largest and no merge
   * Case 3: newInterval is in between and no merge
   * Case 4: newInterval is in between and merged
   *
   * @param intervals
   * @param newInterval
   * @return
   */
  public int[][] insert(int[][] intervals, int[] newInterval) {
    if (intervals == null || intervals.length == 0) {
      if (newInterval != null) {
        return new int[][] {newInterval};
      }
      return null;
    }

    List<int[]> result = new ArrayList<>();

    for (int[] curr : intervals) {
      if (newInterval != null) {
        if (curr[1] < newInterval[0]) { // Case3
          // curr is smaller than newInterval, just add.
          result.add(curr);
        } else if (newInterval[1] < curr[0]) { // Case1
          // newInterval is smaller than curr, add it, no more merging is needed.
          result.add(newInterval);
          result.add(curr);
          newInterval = null;
        } else {
          //  merge Case4
          newInterval = new int[] {
              Math.min(curr[0], newInterval[0]),
              Math.max(curr[1], newInterval[1])
          };
        }
      } else {
        // Case2
        result.add(curr);
      }
    }

    if (newInterval != null) {
      result.add(newInterval);
    }
    return result.toArray(new int[0][]);
  }
}
