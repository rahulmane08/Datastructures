package leetcode.misc.medium.intervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

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
    System.out.println(canAttendMeetings1(intevals));
  }

  public static boolean canAttendMeetings1(int[][] intervals) {
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
    for (int i = 0; i < intervals.length - 1; i++) {
      if (intervals[i][0] > intervals[i + 1][0]) {
        return false;
      }
    }
    return true;
  }

  // TLE
  public static boolean canAttendMeetings(int[][] intervals) {
    if (intervals == null || intervals.length == 0) {
      return true;
    }
    Stack<int[]> stack = new Stack<>();
    for (int[] curr : intervals) {
      merge(stack, curr);
    }
    return stack.size() == intervals.length;
  }

  private static void merge(Stack<int[]> stack, int[] curr) {
    if (stack.isEmpty() || stack.peek()[1] <= curr[0]) {
      stack.push(curr);
      return;
    }

    int[] top = stack.pop();
    if (curr[1] <= top[0]) {
      merge(stack, curr);
    } else {
      top = new int[] {Math.min(curr[0], top[0]), Math.max(curr[1], top[1])};
    }
    merge(stack, top);
  }
}
