package leetcode.binarysearch.medium;

import java.util.Arrays;

/**
 * Example 1:
 * <p>
 * Input: bloomDay = [1,10,3,10,2], m = 3, k = 1
 * Output: 3
 * Explanation: Let us see what happened in the first three days. x means flower bloomed and _ means flower did not bloom in the garden.
 * We need 3 bouquets each should contain 1 flower.
 * After day 1: [x, _, _, _, _]   // we can only make one bouquet.
 * After day 2: [x, _, _, _, x]   // we can only make two bouquets.
 * After day 3: [x, _, x, _, x]   // we can make 3 bouquets. The answer is 3.
 * Example 2:
 * <p>
 * Input: bloomDay = [1,10,3,10,2], m = 3, k = 2
 * Output: -1
 * Explanation: We need 3 bouquets each has 2 flowers, that means we need 6 flowers.
 * We only have 5 flowers so it is impossible to get the needed bouquets and we return -1.
 * Example 3:
 * <p>
 * Input: bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
 * Output: 12
 * Explanation: We need 2 bouquets each should have 3 flowers.
 * Here is the garden after the 7 and 12 days:
 * After day 7: [x, x, x, x, _, x, x]
 * We can make one bouquet of the first three flowers that bloomed.
 * We cannot make another bouquet from the last three flowers that bloomed because they are not adjacent.
 * After day 12: [x, x, x, x, x, x, x]
 * It is obvious that we can make two bouquets in different ways.
 */
public class MinimumDaysForMBouquets {

  /**
   * Total flowers to be bloomed for forming m bouquets = m*k.
   *
   * @param bloomDay
   * @param m
   * @param k
   * @return
   */
  public int minDays(int[] bloomDay, int m, int k) {
    if (bloomDay.length < m * k) {
      return -1; // even if all flowers bloom , m bouquets cant be formed.
    }
    // Input: bloomDay = [1,10,3,10,2], m = 3, k = 1
    int low = Arrays.stream(bloomDay).min().getAsInt();
    int high = Arrays.stream(bloomDay).max().getAsInt();
    int minDays = -1;
    while (low <= high) {
      int mid = (low + high) >>> 1;
      if (canFormBouquets(bloomDay, m, k, mid)) {
        // For the current day we can form m bouquets, greedily check for the min such day.
        minDays = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return minDays;
  }

  private boolean canFormBouquets(int[] bloomDay, int m, int k, int day) {
    for (int i = 0, j = 0; i < bloomDay.length; i++) {
      if (bloomDay[i] <= day) {
        j++;
      } else {
        j = 0;
      }
      if (j == k) {
        m--;
        j = 0;
      }
    }
    return m <= 0;
  }

  public static void main(String[] args) {
    MinimumDaysForMBouquets util = new MinimumDaysForMBouquets();
    System.out.println(util.minDays(new int[] {1, 10, 3, 10, 2}, 3, 1));
    System.out.println(util.minDays(new int[] {1, 10, 3, 10, 2}, 1, 3));
    System.out.println(util.minDays(new int[] {1, 10, 3, 10, 2}, 1, 2));
    System.out.println(util.minDays(new int[] {1, 10, 3, 10, 2}, 3, 2));
  }
}
