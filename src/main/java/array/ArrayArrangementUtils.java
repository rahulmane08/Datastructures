package array;

import static array.ArrayUtils.rightRotateByReversal;

import java.util.Arrays;

public class ArrayArrangementUtils {

    public static void rearrageAlternateMaxAndMin(int[] arr) {
        if (arr == null)
            return;
        Arrays.sort(arr);
        int n = arr.length;
        int mid = (n % 2 == 0) ? n / 2 : (n / 2) + 1;
        int end = (n % 2 == 0) ? n - 2 : n - 1;
        for (int i = 0; i < mid;) {
            int j = end - i;
            ArrayUtils.swap(arr, i, j);
            i = i + 2;
        }
    }

    /**
     * Input:  arr[] = {1, 2, 3, -4, -1, 4}
     * Output: arr[] = {-4, 1, -1, 2, 3, 4}
     *
     * Input:  arr[] = {-5, -2, 5, 2, 4, 7, 1, 8, 0, -8}
     * output: arr[] = {-5, 5, -2, 2, -8, 4, 7, 1, 8, 0}
     * @param arr
     */
    public static void rearrageAlternatePositiveAndNegative(int[] arr) {
        if (arr == null)
            return;
        int n = arr.length;
        for (int i = 0; i < n;) {
            int j = i+1;
            if (i%2==0){
                if (arr[i]>=0) {
                    while (j<n && arr[j] >=0)
                        j++;
                    if (j >= n)
                        break;
                    rightRotateByReversal(arr, 1, i, j);
                }
            } else {
                if (arr[i]<0) {
                    while (j<n && arr[j] <0)
                        j++;
                    if (j >= n)
                        break;
                    rightRotateByReversal(arr, 1, i, j);
                }
            }
            i++;
        }
    }

    /**
     * Input :  arr[] = {1, 2, 0, 4, 3, 0, 5, 0};
     * Output : arr[] = {1, 2, 4, 3, 5, 0, 0};
     * <p>
     * Input : arr[]  = {1, 2, 0, 0, 0, 3, 6};
     * Output : arr[] = {1, 2, 3, 6, 0, 0, 0};
     *
     * @param arr
     */
    public static void moveAllZeroesAtEnd(int[] arr) {
        if (arr == null)
            return;
        int n = arr.length;
        int lastNonZeroIndex = n - 1;
        int startIndex = 0;
        while (startIndex < lastNonZeroIndex) {
            if (arr[startIndex] == 0) {
                while (arr[lastNonZeroIndex] == 0)
                    lastNonZeroIndex--;
                ArrayUtils.leftRotateByReversal(arr, 1, startIndex, lastNonZeroIndex);
            } else
                startIndex++;
        }
    }

    /**
     * Input:  arr[]   = [50, 40, 70, 60, 90]
     *         index[] = [3,  0,  4,  1,  2]
     * Output: arr[]   = [40, 60, 90, 50, 70]
     *         index[] = [0,  1,  2,  3,   4]
     * @param arr
     * @param indexes
     */
    public static void sortAsPerIndexedArray(int [] arr, int [] indexes) {
        if (arr == null || indexes == null || arr.length != indexes.length)
            return;
        for (int i = 0; i< indexes.length; i++) {
            while (indexes[i] != i) {
                int oldIndex = indexes[indexes[i]];
                int oldElement = arr[indexes[i]];

                indexes[indexes[i]] = indexes[i];
                arr[indexes[i]] = arr[i];

                indexes[i] = oldIndex;
                arr[i] = oldElement;
            }
        }
    }
}
