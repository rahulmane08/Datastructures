package leetcode.binarysearch.medium;

import java.util.Arrays;

public class CapacityToShipWithingDdays {
  public int shipWithinDays(int[] weights, int days) {
    int minCapacity = -1; // [1,2,3,4,5]

    int low = Arrays.stream(weights).max().getAsInt();
    int high = Arrays.stream(weights).sum();
    while (low <= high) {
      int mid = (low + high) >>> 1;
      int daysTaken = calculateDaysToShip(weights, mid);
      if (daysTaken <= days) {
        minCapacity = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return minCapacity;
  }

  // [1,2,3,4,5]
  private int calculateDaysToShip(int[] weights, int capacity) {
    int days = 0;
    for (int i = 0, currentCapacity = 0; i < weights.length; ) {
      if (currentCapacity + weights[i] <= capacity) {
        currentCapacity += weights[i++];
      } else {
        currentCapacity = 0;
        days++;
      }
    }
    return days + 1;
  }

  public static void main(String[] args) {
    CapacityToShipWithingDdays util = new CapacityToShipWithingDdays();
    System.out.println(util.shipWithinDays(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5));
  }
}
