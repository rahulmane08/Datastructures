package leetcode.binarysearch.medium;

import java.util.Arrays;

public class MagneticForceBetweenTwoBalls {
  public int maxDistance(int[] position, int m) {
    Arrays.sort(position);
    int n = position.length;
    int low = 1; // min dist = 1
    int high = position[n - 1];
    int maxDist = low;
    while (low <= high) {
      int mid = (low + high) >>> 1;
      if (canPlaceBalls(position, m, mid)) {
        maxDist = mid;
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return maxDist;
  }

  private boolean canPlaceBalls(int[] position, int m, int dist) {
    int prev = 0;
    m--;
    for (int i = 1; i < position.length; i++) {
      if (position[i] - position[prev] >= dist) {
        prev = i;
        if (--m <= 0) {
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    MagneticForceBetweenTwoBalls util = new MagneticForceBetweenTwoBalls();
    System.out.println(util.maxDistance(new int[] {1, 2, 3, 4, 7}, 3));
    System.out.println(util.maxDistance(new int[] {5, 4, 3, 2, 1, 1000000000}, 2));
  }
}
