package sorting;

import static pq.HeapUtils.maxHeapify;

import utils.Swapper;

public class HeapSort {
    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            maxHeapify(arr, n, i);
        for (int i = n - 1; i >= 0; i--) {
            Swapper.swap(arr, i, 0);
            maxHeapify(arr, i, 0);
        }
    }
}
