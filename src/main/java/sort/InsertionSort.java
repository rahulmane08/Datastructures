package sort;

/**
 * If we take a closer look at the insertion sort code, we can notice that every iteration of while loop reduces one inversion.
 * The while loop executes only if i > j and arr[i] < arr[j].
 * Therefore total number of while loop iterations (For all values of i) is same as number of inversions.
 * Therefore overall time complexity of the insertion sort is O(n + f(n)) where f(n) is inversion count.
 * If the inversion count is O(n), then the time complexity of insertion sort is O(n).
 * In worst case, there can be n*(n-1)/2 inversions.
 * The worst case occurs when the array is sorted in reverse order. So the worst case time complexity of insertion sort is O(n2).
 * Best case: O(n)
 * Worst case: O(n^2)
 */
public class InsertionSort {
    static public void sort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            for (; j >= 0 && key < arr[j]; arr[j + 1] = arr[j--]) ;
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        InsertionSort.sort(new int[]{5,4,3,2,1});
    }
}