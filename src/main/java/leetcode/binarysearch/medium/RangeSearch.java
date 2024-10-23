package leetcode.binarysearch.medium;

import java.util.Arrays;
import java.util.Comparator;
import leetcode.binarysearch.BinarySearchUtil;
import leetcode.binarysearch.BinarySearchUtil.LowerBoundUtil;

public class RangeSearch {

  public int search(int[][] ranges, int[] target) {
    Arrays.sort(ranges, Comparator.comparingInt(a -> a[0]));
    int low = 0;
    int high = ranges.length - 1;
    while (low <= high) {
      int mid = (low + high) >>> 1;
      int[] midInterval = ranges[mid];
      if (isOverlapping(midInterval, target)) {
        return mid;
      } else if (target[1] < midInterval[0]) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return -1;
  }

  public int[] getRange(int[][] ranges, int[] target) {
    Arrays.sort(ranges, Comparator.comparingInt(a -> a[0]));
    int[] starts = new int[ranges.length];
    int[] ends = new int[ranges.length];
    for (int i = 0; i < ranges.length; i++) {
      starts[i] = ranges[i][0];
      ends[i] = ranges[i][1];
    }
    int low = new LowerBoundUtil(starts).lowerBound(target[0]);
    int high = new BinarySearchUtil.UpperBoundUtil(starts).upperBound(target[1]);
    // case 1: if target eclipses entire intervals, low = 0, hight = n, hence range = (0, n-1)
    // case 2: if target start is not present in intervals, then low = high = index of next higher element.
    // case 3: if target start or end is present in intervals, low = that index, high = index of next higher element.
    if (low == high && low != 0) {
      // case 2
      low = low - 1;
    }

    return new int[] {low, high - 1};
  }

  private boolean isOverlapping(int[] curr, int[] next) {
    return (next[0] <= curr[0] && curr[0] <= next[1])
        || (next[0] <= curr[1] && curr[1] <= next[1])
        || (curr[0] <= next[0] && next[0] <= curr[1])
        || (curr[0] <= next[1] && next[1] <= curr[1]);
  }

  public static void main(String[] args) {
    int[][] ranges = new int[][] {{1, 3}, {6, 10}, {12, 15}, {17, 19}, {19, 24}};
    // [1,6,12,17] [3, 10, 15, 19]
    RangeSearch util = new RangeSearch();
    System.out.println(Arrays.toString(util.getRange(ranges, new int[] {2, 5}))); // spans multiple intervals
    System.out.println(Arrays.toString(util.getRange(ranges, new int[] {4, 5}))); // lies in the middle doesnt overlap
    System.out.println(Arrays.toString(util.getRange(ranges, new int[] {0, 30}))); // eclipses entire range
    System.out.println(Arrays.toString(util.getRange(ranges, new int[] {0, 13}))); // eclipses left, right in middle
    System.out.println(Arrays.toString(util.getRange(ranges, new int[] {0, 11}))); // eclipses left, right in middle
  }
}
