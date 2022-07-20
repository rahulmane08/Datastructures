package search;

import interfaces.DivideAndConquer;

/**
 * best case: root element of tree = O(1)
 * worst case : element not found or found at the leaf node = O(log n)
 */
@DivideAndConquer
public class BinarySearch {
    /**
     * T(n) = T(n/2) + 1
     * Applying master theorem : a = 1, b = 2, k = 0, p = 0
     * log a base b = k = 0, case 3.1 -> O (N^K x (Log N)^(P+1)) = O(log N)
     * @param arr
     * @param left
     * @param right
     * @param elem
     * @return
     */
    static public boolean search(int[] arr, int left, int right, int elem) {
        if (left > right)
            return false;
        int mid = (left + right) >>> 1;
        if (arr[mid] == elem)
            return true;
        else if (elem < arr[mid])
            return search(arr, left, mid - 1, elem); // T(n/2)
        else
            return search(arr, mid + 1, right, elem); // T(n/2)
    }

    static public boolean search(int[] arr, int elem) {
        return search(arr, 0, arr.length - 1, elem);
    }

    static public boolean searchIteratively(int[] arr, int elem) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int low = 0;
        int high = arr.length - 1;
        boolean found = false;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == elem) {
                found = true;
                break;
            } else if (arr[mid] < elem) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return found;
    }
}
