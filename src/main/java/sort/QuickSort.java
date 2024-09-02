package sort;

import java.util.Arrays;
import utils.Swapper;

/**
 * arr: {5, 3, 2, 6, 4, 2, 7, 4}
 * mid = 3, pivot = arr[mid] = 6
 *  - swap(5, 4) : {4, 3, 2, 6*, 4, 2, 7, 5}
 *  - swap(6, 2) : {4, 3, 2, 2*, 4, 6, 7, 5}
 *
 *  {4, 3*, 2, 2}
 *  mid = 1, pivot = arr[mid] = 3
 *  - swap(4,2) : {2, 3*, 2, 4}
 *  - swap(3,2) : {2, 2*, 3, 4}
 */
public class QuickSort {
    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    /**
     * Time complexity
     * Dividing function : T(n) = 2T(n/2) + n -> k = 1, p = 0, a = 2, b = 2. log(a) = 1. Case 2.1 -> n*log(n)
     * @param arr
     * @param low
     * @param high
     */
    void sort(int[] arr, int low, int high) {
        if (low > high) {
            return;
        }
        int mid = (low + high) >>> 1;
        int pivot = arr[mid];
        int i = low, j = high;
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
        sort(arr, low, j);
        sort(arr, i, high);
    }

    public static void main(String[] args) {
        int[] arr = new int[] {5, 3, 2, 6, 4, 2, 7, 4};
        QuickSort util = new QuickSort();
        util.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
