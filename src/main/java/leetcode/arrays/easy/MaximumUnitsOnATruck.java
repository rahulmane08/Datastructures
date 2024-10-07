package leetcode.arrays.easy;

import java.util.Arrays;
import java.util.Comparator;

public class MaximumUnitsOnATruck {
  public int maximumUnits(int[][] boxTypes, int truckSize) {
    Arrays.sort(boxTypes, Comparator.comparingInt((int[] a) -> a[1]).reversed());
    int totalUnits = 0;
    int index = 0;
    while (truckSize > 0 && index < boxTypes.length) {
      totalUnits += (Math.min(boxTypes[index][0], truckSize) * boxTypes[index][1]);
      truckSize -= boxTypes[index][0];
      index++;
    }
    return totalUnits;
  }

  public static void main(String[] args) {
    MaximumUnitsOnATruck util = new MaximumUnitsOnATruck();
    System.out.println(util.maximumUnits(new int[][] {{1, 3}, {2, 2}, {3, 1}}, 4));
  }
}
