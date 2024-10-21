package leetcode.design.hard;

import java.util.ArrayList;
import java.util.List;

public class RangeModule {
  private List<int[]> ranges;

  public RangeModule() {
    this.ranges = new ArrayList<>();
  }

  public void addRange(int left, int right) {
    int[] newInterval = {left, right};
    if (ranges.isEmpty()) {
      ranges.add(newInterval);
      return;
    }

    List<Integer> overlappingIntervals = getOverlappingIntervals(newInterval);
    if (overlappingIntervals.isEmpty()) {
      // newInterval either lies at the start or end
      if (newInterval[1] < ranges.get(0)[0]) {
        ranges.add(0, newInterval);
      } else {
        ranges.add(newInterval);
      }
    } else {
      // overlapping intervals found
      for (int overlappingIndex : overlappingIntervals) {
        int[] existing = ranges.remove(overlappingIndex);
        newInterval = merge(existing, newInterval);
      }
      ranges.add(overlappingIntervals.get(0), newInterval);
    }
  }

  public boolean queryRange(int left, int right) {
    int[] newInterval = {left, right};
    int low = 0;
    int high = ranges.size() - 1;
    while (low <= high) {
      int mid = (low + high) >>> 1;
      int[] midInterval = ranges.get(mid);
      if (isOverlapping(midInterval, newInterval)) {
        return true;
      } else if (newInterval[1] < midInterval[0]) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return false;
  }

  public void removeRange(int left, int right) {

  }

  private List<Integer> getOverlappingIntervals(int[] interval) {
    List<Integer> result = new ArrayList<>();
    int low = 0;
    int high = ranges.size() - 1;
    while (low <= high) {
      int mid = (low + high) >>> 1;
      int[] midInterval = ranges.get(mid);
      if (isOverlapping(midInterval, interval)) {
        result.add(mid);

        if (midInterval[0] <= interval[0] && interval[1] <= midInterval[1]) {
          //subset
          break;
        }

        if (interval[0] < midInterval[0]) {
          high = mid - 1;
        } else {
          low = mid + 1;
        }
      } else if (interval[1] < midInterval[0]) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return result;
  }

  private int[] merge(int[] curr, int[] next) {
    return new int[] {Math.min(curr[0], next[0]), Math.max(curr[1], next[1])};
  }

  private boolean isOverlapping(int[] curr, int[] next) {
    return (next[0] <= curr[0] && curr[0] <= next[1])
        || (next[0] <= curr[1] && curr[1] <= next[1])
        || (curr[0] <= next[0] && next[0] <= curr[1])
        || (curr[0] <= next[1] && next[1] <= curr[1]);
  }
}