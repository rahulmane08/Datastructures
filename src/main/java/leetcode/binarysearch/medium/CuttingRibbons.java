package leetcode.binarysearch.medium;

import java.util.Arrays;

public class CuttingRibbons {
  public int maxLength(int[] ribbons, int k) {
    Arrays.sort(ribbons);
    int index = Arrays.binarySearch(ribbons, k);
    if (index == -1) {
      index = 0;
    }
    if (index < 0) {
      return 0;
    }
    int totalRibbons = 0;
    while (index < ribbons.length) {
      totalRibbons += (ribbons[index++] / k);
    }
    return totalRibbons;
  }

  public static void main(String[] args) {
    CuttingRibbons util = new CuttingRibbons();
    System.out.println(util.maxLength(new int[] {9,7,5}, 3));
  }
}
