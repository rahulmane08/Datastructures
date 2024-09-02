package sort;

import static datastructures.pq.HeapUtils.maxHeapify;

import utils.Swapper;

public class HeapSort {
  static public void sort(Integer[] arr, boolean desc) {
    int n = arr.length;

    //first heapify the array
    for (int i = (n / 2) - 1; i >= 0; i--) {
      maxHeapify(arr, n, i);
    }


    //swap last element with root and then heapify again
    for (int i = n - 1; i >= 0; i--) {
      Swapper.swap(arr, 0, i);
      maxHeapify(arr, i, 0);
    }
  }
}
