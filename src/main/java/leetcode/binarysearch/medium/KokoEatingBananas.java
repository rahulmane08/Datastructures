package leetcode.binarysearch.medium;

import java.util.Arrays;

/**
 * Example 1:
 * <p>
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 * Example 2:
 * <p>
 * Input: piles = [30,11,23,4,20], h = 5
 * Output: 30
 * Example 3:
 * <p>
 * Input: piles = [30,11,23,4,20], h = 6
 * Output: 23
 */
public class KokoEatingBananas {
  public int minEatingSpeed(int[] piles, int h) {
    if (piles.length == 1) {
      return (int) Math.ceil(Double.valueOf(piles[0]) / h);
    }
    int low = 1;
    int high = Arrays.stream(piles).max().getAsInt();
    int minSpeed = 0;
    while (low <= high) {
      int mid = (low + high) >>> 1;
      int totalHours = hoursToEatBanana(piles, mid);
      if (totalHours <= h) {
        minSpeed = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return minSpeed;
  }

  private int hoursToEatBanana(int[] piles, int speed) {
    double hours = 0;
    for (double bananas : piles) {
      hours += Math.ceil(bananas / speed);
    }
    return (int) hours;
  }

  public static void main(String[] args) {
    KokoEatingBananas util = new KokoEatingBananas();
    System.out.println(util.minEatingSpeed(new int[] {3, 6, 7, 11}, 8));
    System.out.println(util.minEatingSpeed(new int[] {30,11,23,4,20}, 5));
    System.out.println(util.minEatingSpeed(new int[] {30,11,23,4,20}, 6));
    System.out.println(util.minEatingSpeed(new int[] {312884470}, 312884469));
  }
}
