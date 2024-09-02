package sort;

import java.util.Arrays;

public class CountingSort {
  public static void main(String[] args) {
    int[] arr = new int[] {5, 3, 2, 6, 4, 2, 7, 4};
    CountingSort util = new CountingSort();
    util.sort(arr, 7);
    System.out.println(Arrays.toString(arr));
  }

  public void sort(int[] arr, int k) {
    if (arr == null) {
      return;
    }
    int[] count = new int[k + 1];
    for (int i : arr) {
      count[i]++;
    }
    int index = 0;
    for (int i = 0; i < count.length; i++) {
      if (count[i] != 0) {
        int times = count[i];
        for (int j = 0; j < times; j++) {
          arr[index++] = i;
        }
      }
    }
  }
}
