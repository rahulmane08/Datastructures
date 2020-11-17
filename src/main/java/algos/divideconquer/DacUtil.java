package algos.divideconquer;

public class DacUtil {

    public static int min(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException();
        }
        return minUtils(arr, 0, arr.length - 1);
    }

    private static int minUtils(int[] arr, int left, int right) {
        if (left >= right) {
            return arr[left];
        }
        int mid = left + (right - left) / 2;
        int lmin = minUtils(arr, left, mid);
        int rmin = minUtils(arr, mid + 1, right);
        return Math.min(lmin, rmin);
    }
}
