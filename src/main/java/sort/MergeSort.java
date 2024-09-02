package sort;

import java.util.Arrays;

public class MergeSort {
  public static void sort(int[] arr) {
    if (arr == null || arr.length == 0) {
      return;
    }
    int n = arr.length;
    sort(arr, 0, n - 1);
  }

  public static void sort(int[] arr, int left, int right) {
    if (left >= right) {
      return;
    }
    int mid = left + (right - left) / 2; // partition
    sort(arr, left, mid);
    sort(arr, mid + 1, right);

    // merge the sorted halves
    MergeUtil.merge(arr, left, mid, right);
  }

  public static void main(String[] args) {
    int[] arr = {5, 4, 3, 2, 1};
    sort(arr);
    System.out.println(Arrays.toString(arr));
  }
}
