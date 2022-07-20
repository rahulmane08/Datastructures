package search;

public class TernarySearch {
    /**
     * T(n) = T(n/3) + 1
     * Applying master theorem : a = 1, b = 3, k = 0, p = 0
     * log a base b = k = 0, case 3.1 -> O (N^K x (Log N)^(P+1)) = O(log N) to base 3
     * @param arr
     * @param left
     * @param right
     * @param elem
     * @return
     */
    static public boolean search(int[] arr, int left, int right, int elem) {
        if (left > right) {
            return false;
        }

        int mid1 = (left + right) / 3;
        int mid2 = mid1 + (left + right) / 3;

        if (arr[mid1] == elem || arr[mid2] == elem) {
            return true;
        }

        if (elem < arr[mid1]) {
            return search(arr, left, mid1 - 1, elem);
        } else if (arr[mid2] < elem) {
            return search(arr, mid2 + 1, right, elem);
        } else {
            return search(arr, mid1 + 1, mid2 - 1, elem);
        }
    }
}
