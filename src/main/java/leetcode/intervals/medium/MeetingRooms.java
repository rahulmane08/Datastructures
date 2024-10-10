package leetcode.intervals.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array of meeting time intervals where intervals[i] = [starti, endi],
 * determine if a person could attend all meetings.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: false
 * Example 2:
 * <p>
 * Input: intervals = [[7,10],[2,4]]
 * Output: true
 */
public class MeetingRooms {

  public static void main(String[] args) {
    int[][] intevals = {{13, 15}, {1, 13}};
    System.out.println(canAttendMeetings(intevals));
  }

  public static boolean canAttendMeetings(int[][] intervals) {
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
    for (int i = 0; i < intervals.length - 1; i++) {
      if (intervals[i][1] > intervals[i + 1][0]) {
        return false;
      }
    }
    return true;
  }
}
