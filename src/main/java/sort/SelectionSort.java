package sort;

import utils.Swapper;

public class SelectionSort {
    static public void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) { // n + 1
            int minIdx = i;
            for (int j = i + 1; j < n; j++) // n
                if (arr[j] < arr[minIdx])
                    minIdx = j;
            if (minIdx != i)
                Swapper.swap(arr, minIdx, i);
        }
    }
}
