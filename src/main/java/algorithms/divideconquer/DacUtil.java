package algorithms.divideconquer;

import interfaces.DivideAndConquer;

@DivideAndConquer
public class DacUtil {

  public static int min(int[] arr) {
    if (arr == null || arr.length == 0) {
      throw new IllegalArgumentException();
    }
    return minUtils(arr, 0, arr.length - 1);
  }

  /**
   * Time complexity = 2T(n/2) = 1 -> Masters theorem case 1 -> O(n ^ log 2) = O(n)
   *
   * @param arr
   * @param left
   * @param right
   * @return
   */
  private static int minUtils(int[] arr, int left, int right) {
    if (left >= right) {
      return arr[left];
    }
    int mid = left + (right - left) / 2;
    int lmin = minUtils(arr, left, mid); // T(n/2)
    int rmin = minUtils(arr, mid + 1, right); // T(n/2)
    return Math.min(lmin, rmin);
  }
}
