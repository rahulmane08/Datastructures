package sort;

import datastructures.array.ArrayUtils;

public class BubbleSort {
  static public void sort(int[] arr) {
    int n = arr.length;
    boolean swapped;
    for (int i = 0; i < n; i++) {
      swapped = false;
      for (int j = 0; j < n - i - 1; j++) {
        if (arr[j] > arr[j + 1]) {
          ArrayUtils.swap(arr, j, j + 1);
          swapped = true;
        }
      }
      if (!swapped) {
        break;
      }
    }
  }

  static public void sortRecursively(int[] arr) {
    int n = arr.length;
    sortRecursively(arr, n);
  }

  /**
   * T(n) = T(n-1) + n
   * Decreasing function case2: n x Second term = O(n^2)
   *
   * @param arr
   * @param n
   */
  private static void sortRecursively(int[] arr, int n) {
    if (n == 0 || n == 1) {
      return;
    }
    for (int i = 0; i < n; i++) {
      if (arr[i] > arr[i + 1]) {
        ArrayUtils.swap(arr, i, i + 1);
      }
    }
    sortRecursively(arr, n - 1);
  }
}
