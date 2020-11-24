package search;

public class BinarySearch {
    static public boolean search(int[] arr, int left, int right, int elem) {
        if (left > right)
            return false;
        int mid = (left + right) >>> 1;
        if (arr[mid] == elem)
            return true;
        else if (elem < arr[mid])
            return search(arr, left, mid - 1, elem);
        else
            return search(arr, mid + 1, right, elem);
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
