package sort;

public class MergeUtil {
  public static void merge(int[] arr, int l, int m, int r) {
    int M = m - l + 1;
    int N = r - m;
    int[] LEFT = new int[M];
    int[] RIGHT = new int[N];

    System.arraycopy(arr, l, LEFT, 0, M);

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
}
