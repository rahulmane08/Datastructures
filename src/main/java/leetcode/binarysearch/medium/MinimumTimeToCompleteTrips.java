package leetcode.binarysearch.medium;

import java.util.Arrays;

public class MinimumTimeToCompleteTrips {
  public long minimumTime(int[] time, int totalTrips) {
    if (time.length == 1) {
      return time[0] * totalTrips;
    }
    long low = 1; // min time is always 1
    long high = Arrays.stream(time).min().getAsInt() * totalTrips; // max time to complete all trips is sum of all times.
    long minTime = 0;
    while (low <= high) {
      long mid = (low + high) >>> 1;
      if (canCompleteTotalTrips(time, totalTrips, mid)) {
        minTime = mid;
        high = mid - 1; // since we need to find the min.
      } else {
        low = mid + 1;
      }
    }
    return minTime;
  }

  public boolean canCompleteTotalTrips(int[] times, long totalTrips, long maxTime) {
    long currentTrips = 0;
    for (int busTime : times) {
      currentTrips += maxTime / busTime;
    }
    return currentTrips >= totalTrips;
  }

  public static void main(String[] args) {
    MinimumTimeToCompleteTrips util = new MinimumTimeToCompleteTrips();
    System.out.println(util.minimumTime(new int[] {1, 2, 3}, 6));
    System.out.println(util.minimumTime(new int[] {9, 3, 10, 5}, 2));
  }
}
