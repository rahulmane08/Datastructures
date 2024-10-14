package leetcode.arrays.medium;

import java.util.Arrays;

/**
 * [1,2 4,6,7], t = 5
 */
public class MinimumTimeToCompleteTrips {
  public static void main(String[] args) {
    MinimumTimeToCompleteTrips util = new MinimumTimeToCompleteTrips();
    System.out.println(util.minimumTime(new int[] {1, 2, 3}, 5));
  }

  public long minimumTime(int[] time, int totalTrips) {
    Arrays.sort(time);

    long lowTime = 1;
    long highTime = (long) time[0] * totalTrips; // Use realistic highTime value
    long minTime = 0;

    while (lowTime <= highTime) {
      long mid = lowTime + (highTime - lowTime) / 2;
      long count = 0;

      for (int i = 0; i < time.length; i++) {
        count += mid / time[i];
      }

      if (count >= totalTrips) {
        minTime = mid;
        highTime = mid - 1;
      } else {
        lowTime = mid + 1;
      }
    }

    return minTime;  // Return minTime directly as long
  }
}
