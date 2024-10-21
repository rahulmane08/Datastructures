package leetcode.binarysearch.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RangeSearch {

  public boolean search(int[][] ranges, int[] target) {
    Arrays.sort(ranges, Comparator.comparingInt(a -> a[0]));
    int low = 0;
    int high = ranges.length - 1;
    while (low <= high) {
      int mid = (low + high) >>> 1;
      int[] midInterval = ranges[mid];
      if (isOverlapping(midInterval, target)) {
        return true;
      } else if (target[1] < midInterval[0]) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return false;
  }

  private boolean isOverlapping(int[] curr, int[] next) {
    return (next[0] <= curr[0] && curr[0] <= next[1])
        || (next[0] <= curr[1] && curr[1] <= next[1])
        || (curr[0] <= next[0] && next[0] <= curr[1])
        || (curr[0] <= next[1] && next[1] <= curr[1]);
  }

  public List<int[]> getOverlappingIntervals(int[][] ranges, int[] target) {
    Arrays.sort(ranges, Comparator.comparingInt(a -> a[0]));
    List<Integer> overlappingIntervalIndexes = getOverlappingIntervals(ranges, target, 0, ranges.length - 1);
    return overlappingIntervalIndexes.stream().map(i -> ranges[i]).collect(Collectors.toList());
  }

  private List<Integer> getOverlappingIntervals(int[][] ranges, int[] target, int low, int high) {
    List<Integer> result = new ArrayList<>();
    while (low <= high) {
      int mid = (low + high) >>> 1;
      int[] midInterval = ranges[mid];
      if (isOverlapping(midInterval, target)) {
        result.add(mid);

        if (midInterval[0] <= target[0] && target[1] <= midInterval[1]) {
          //subset
          break;
        }

        if (target[0] < midInterval[0]) {
          high = mid - 1;
        } else {
          low = mid + 1;
        }
      } else if (target[1] < midInterval[0]) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    int[][] ranges = new int[][] {{17, 19}, {1, 3}, {6, 10}, {12, 15}};
    RangeSearch util = new RangeSearch();
    System.out.println(util.search(ranges, new int[] {2, 5}));
    System.out.println(util.search(ranges, new int[] {4, 5}));

    List<int[]> overlappingIntervals = util.getOverlappingIntervals(ranges, new int[] {2, 5});
    overlappingIntervals.forEach(interval -> System.out.println(Arrays.toString(interval)));
    System.out.println("====");
    overlappingIntervals = util.getOverlappingIntervals(ranges, new int[] {2, 7});
    overlappingIntervals.forEach(interval -> System.out.println(Arrays.toString(interval)));
    System.out.println("====");
    overlappingIntervals = util.getOverlappingIntervals(ranges, new int[] {7, 9});
    overlappingIntervals.forEach(interval -> System.out.println(Arrays.toString(interval)));
    System.out.println("====");
    overlappingIntervals = util.getOverlappingIntervals(ranges, new int[] {5, 11});
    overlappingIntervals.forEach(interval -> System.out.println(Arrays.toString(interval)));
    System.out.println("====");
    overlappingIntervals = util.getOverlappingIntervals(ranges, new int[] {5, 13});
    overlappingIntervals.forEach(interval -> System.out.println(Arrays.toString(interval)));
  }
}
