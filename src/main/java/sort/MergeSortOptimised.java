package sort;

public class MergeSortOptimised {
  private static final int INSERTIONSORT_THRESHOLD = 7;

  static public void sort(int[] a) {
    mergeSort(a, 0, a.length);
  }

  private static void mergeSort(int[] arr, int low, int high) {
    if (low < high) {
      int length = high - low;
      // Insertion sort on smallest arrays
      if (length < INSERTIONSORT_THRESHOLD) {
        InsertionSort.sort(arr);
        return;
      }
      int mid = (low + high) >>> 1;
      mergeSort(arr, low, mid);
      mergeSort(arr, mid, high);

      if (arr[mid - 1] <= arr[mid]) {
        return;
      }

      merge(arr, low, mid, high);
    }

  }

  private static void merge(int[] arr, int low, int mid, int high) {
    int size1 = mid - low + 1;
    int size2 = high - mid;

    int[] L = new int[size1];
    int[] R = new int[size2];

    System.arraycopy(arr, low, L, 0, size1);
    for (int j = 0; j < size2; j++) {
      R[j] = arr[mid + 1 + j];
    }

    int i = 0, j = 0, k = low;
    while (i < size1 && j < size2) {
      if (L[i] < R[j]) {
        arr[k++] = L[i++];
      } else {
        arr[k++] = R[j++];
      }
    }
    while (i < size1) {
      arr[k++] = L[i++];
    }
    while (j < size2) {
      arr[k++] = R[j++];
    }
  }


}
