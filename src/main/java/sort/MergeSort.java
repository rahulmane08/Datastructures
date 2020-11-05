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
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int M = m - l + 1;
        int N = r - m;
        int[] LEFT = new int[M];
        int[] RIGHT = new int[N];

        for (int i = 0; i < M; i++) {
            LEFT[i] = arr[l + i];
        }

        for (int j = 0; j < N; j++) {
            RIGHT[j] = arr[m + 1 + j];
        }

        int i = 0, j = 0, k = l;

        while (i < M && j < N) {
            if (LEFT[i] <= RIGHT[j]) {
                arr[k++] = LEFT[i++];
            } else {
                arr[k++] = RIGHT[j++];
            }
        }

        if (i < M) {
            while (i < M) {
                arr[k++] = LEFT[i++];
            }
        }

        if (j < N) {
            while (j < N) {
                arr[k++] = RIGHT[j++];
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
