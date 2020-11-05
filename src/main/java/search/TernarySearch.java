package search;

public class TernarySearch {
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
