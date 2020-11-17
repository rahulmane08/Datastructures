package sort;

import utils.Swapper;

public class QuickSort {
    static public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    static public void sort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) >>> 1;
        int pivot = arr[mid];
        int i = left, j = right;
        while (i <= j) {
            // find first element higher than pivot
            while (arr[i] < pivot) {
                i++;
            }
            // find first element lower than pivot
            while (pivot < arr[j]) {
                j--;
            }
            // swap such elements
            if (i <= j) {
                Swapper.swap(arr, i, j);
                i++;
                j--;
            }
        }
        sort(arr, left, j);
        sort(arr, i, right);
    }
}
