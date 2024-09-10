package leetcode.misc.medium.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * -> remove [1,3] to attend 3 meetings
 * <p>
 * Input: intervals = [[1,2],[1,2],[1,2]]
 * -> remove 2 meetings [1,2],[1,2]
 * <p>
 * Input: intervals = [[1,2],[2,3]]
 * -> no meetings to remove
 */
public class AttendMaximumMeetings {
  public static void main(String[] args) {
    AttendMaximumMeetings util = new AttendMaximumMeetings();
    System.out.println(Arrays.deepToString(util.attendMaxMeetings(new int[][] {{1, 3}, {2, 5}, {5, 6}})));
  }

  public int[][] attendMaxMeetings(int[][] meetings) {
    if (meetings == null || meetings.length == 1) {
      return null;
    }
    Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));
    List<int[]> meetingsToRemove = new ArrayList<>();
    int[] prev = meetings[0];
    for (int i = 1; i < meetings[0].length; i++) {
      int[] curr = meetings[i];
      if (prev[1] > curr[0]) {
        // overlapping
        if (prev[1] > curr[1]) {
          // completely overlapping, remove this meeting
          meetingsToRemove.add(prev);
        } else {
          meetingsToRemove.add(curr);
        }
      }
    }
    return meetingsToRemove.toArray(new int[meetingsToRemove.size()][]);
  }
}
