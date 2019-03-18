package array;

import java.util.Comparator;
import java.util.Stack;

import math.Math;
import utils.Utils;

public class ArrayUtils {
    static public void countPairsEqualToN(int[] arr, int N) {
        java.util.Arrays.sort(arr);// nlog(n)
        int end = arr.length - 1, start = 0;
        for (; arr[end] >= N; end--) {
        }
        int count = 0;
        while (start < end) {
            int sum = arr[start] + arr[end];
            if (sum == N) {
                ++count;
                ++start;
                --end;
            } else if (sum > N)
                --end;
            else
                ++start;
        }
        System.out.println("Total Pairs = " + count);
    }

    static public void splitIntoEqualSumSubArrays(int[] arr) {
        java.util.Arrays.sort(arr); // nlog(n)
        int sum = 0;
        for (int i : arr)
            sum += i;
        int halfSum = sum / 2;
        int end = 0;
        for (end = 0; end < arr.length; end++) {
            sum = sum - arr[end];
            if (sum < halfSum) {
                System.out.println("no equal sum sub arrays found");
                return;
            } else if (sum == halfSum) {
                break;
            }
        }
        System.out.println(java.util.Arrays.toString(java.util.Arrays.copyOfRange(arr, 0, end + 1)));
        System.out.println(java.util.Arrays.toString(java.util.Arrays.copyOfRange(arr, end + 1, arr.length - 1)));
    }

    static public void printNonRepeatingElement(int[] arr) {
        int elem = 0;
        for (int i : arr)
            elem = elem ^ i;
        System.out.println("dupe = " + elem);
    }

    static public void printXOR(int[] arr) {
        int xorSum = 0;
        for (int i : arr)
            xorSum ^= i;
        int[] xor = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            xor[i] = arr[i] ^ xorSum;
        System.out.println(java.util.Arrays.toString(xor));
    }

    static public void toSubArraysWithEqualSum(int[] arr) {
        int end = 0;
        int sum = 0;
        int n = arr.length;
        for (int i : arr)
            sum += i;
        int halfSum = sum / 2;
        for (int i = 0; i < n; i++) {
            sum = sum - arr[i];
            if (sum == halfSum) {
                end = i;
                break;
            } else if (sum < arr[i]) {
                System.out.println("no subarrays");
                return;
            }
        }
        System.out.println(
                "first subarray = " + java.util.Arrays.toString(java.util.Arrays.copyOfRange(arr, 0, end + 1)));
        System.out.println(
                "second subarray = " + java.util.Arrays.toString(java.util.Arrays.copyOfRange(arr, end + 1, n)));
    }

    static public int[][] createSnakeAndLadderPattern(int n) {
        int[][] board = new int[n][n];
        boolean odd = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int elem = 5 * i + j + 1;
                if (!odd)
                    board[i][j] = elem;
                else
                    board[i][n - j - 1] = elem;
            }
            odd = !odd;
        }
        return board;
    }

    static public int[] findMinUnsortedSubArrayToSortEntireArray(int[] arr) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;
        for (int i = 0; i < n; i++)
            if (arr[i] > arr[i + 1]) {
                start = i;
                break;
            }
        for (int i = n - 1; i >= 0; i--)
            if (arr[i] < arr[i - 1]) {
                end = i;
                break;
            }
        int min, max;
        min = max = arr[start];
        for (int i = start; i <= end; i++) {
            if (arr[i] < min)
                min = arr[i];
            if (max < arr[i])
                max = arr[i];
        }
        for (int i = 0; i < start; i++) {
            if (arr[i] > min) {
                start = i;
                break;
            }
        }
        for (int i = n - 1; i > end; i--) {
            if (arr[i] < max) {
                end = i;
                break;
            }
        }
        return java.util.Arrays.copyOfRange(arr, start, end + 1);
    }

    static public int[] merge(int[] a, int[] b) {
        if (a == null && b == null)
            return null;
        if (a == null)
            return b;
        if (b == null)
            return a;
        int m = a.length;
        int n = b.length;
        if (m > 1)
            for (int i = 1, j; i < a.length; i++) if (a[i] < a[i - 1]) return null;
        if (n > 1)
            for (int i = 1, j; i < b.length; i++) if (b[i] < b[i - 1]) return null;

        int k = m + n;
        int[] merged = new int[k];

        int i, j, idx;
        i = j = idx = 0;

        while (i < m && j < n) {
            if (a[i] < b[j])
                merged[idx++] = a[i++];
            else if (a[i] > b[j])
                merged[idx++] = b[j++];
        }
        if (i < k)
            while (i < m)
                merged[idx++] = a[i++];
        if (j < k)
            while (j < n)
                merged[idx++] = b[j++];

        return merged;
    }

    public static String concatenateArrayToLargestNumber(Integer[] arr) {
        StringBuilder result = new StringBuilder();
        Comparator<Integer> comparator = new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                int digits1 = Utils.countDigits(o1);
                int digits2 = Utils.countDigits(o2);
                if (digits1 != digits2) {
                    int msd1 = o1, msd2 = o2;
                    do {
                        msd1 = Utils.mostSignificantDigit(o1, digits1--);
                        msd2 = Utils.mostSignificantDigit(o2, digits2--);
                    }
                    while (msd1 == msd2);
                    if (msd1 > msd2)
                        return -1;
                    else if (msd1 < msd2)
                        return 1;
                    else
                        return 0;

                }
                return o2.compareTo(o1);
            }
        };
        java.util.Arrays.sort(arr, comparator);
        for (int i : arr)
            result.append(String.valueOf(i));
        return result.toString();
    }

    /**
     * Let us take the same example arr[] = [1, 2, 3, 4, 5, 6, 7], d = 2
     * Rotate arr[] by one 2 times
     * We get [2, 3, 4, 5, 6, 7, 1] after first rotation and [ 3, 4, 5, 6, 7, 1, 2] after second rotation.
     *
     * @param arr
     * @param d
     */
    static public void rotate(int[] arr, int d) {
        if (arr == null) return;
        int n = arr.length;
        d = d % n;
        for (int i = 0; i < d; i++) {
            int first = arr[0];
            int j = 0;
            for (; j < n - 1; j++) {
                arr[j] = arr[j + 1];
            }
            arr[j] = first;
        }
    }

    /**
     * Let us take the same example arr[] = [1, 2, 3, 4, 5, 6, 7, 8], d = 2
     * Rotate arr[] by one 2 times
     * gcd (8, 2) = 2
     * two loops
     * first pass : [3, 2, 5, 4, 7, 6, 1, 8]
     * second pass : [3, 4, 5, 6, 7, 8, 1, 2]
     * @param arr
     * @param d
     */
    static public void rotateEfficiently(int [] arr, int d) {
        if (arr == null) return;
        int n = arr.length;
        int gcd = Math.gcd(n, d);
        for (int i = 0; i< gcd ; i++) {
            int temp = arr [i];
            int k = i;
            while (k < n) {
                int next = k + gcd;
                if (next >= n){
                    arr [k] = temp;
                } else {
                    arr[k] = arr[next];
                }
                k = next;
            }
        }
    }

    /**
     * Find if there is a subset of a given set with a given sum.
     * Input:  set[] = {3, 34, 4, 12, 5, 2, 11}, sum = 9
     * Output:  True  subset= {4, 5}
     *
     * @param arr
     * @param K
     */
    static public void subsetWhoseSumMatchesN(int[] arr, int K) {
        if (arr == null || arr.length == 0)
            return;
        Stack<Integer> subset = new Stack<>();
        if (findSubsetWhoseSumMatchesN(arr, 0, arr.length, K, subset)) {
            System.out.print("subset found: [ ");
            while (!subset.isEmpty())
                System.out.printf("%d ", subset.pop());
            System.out.println("]");
        }
    }

    static private boolean findSubsetWhoseSumMatchesN(int[] arr, int currIndex, int n, int K, Stack<Integer> path) {
        if (currIndex == n && K != 0)
            return false;
        if (K == 0)
            return true;
        int curr = arr[currIndex];
        if (curr > K)
            return findSubsetWhoseSumMatchesN(arr, currIndex + 1, n, K, path);
        boolean elementSkipped = findSubsetWhoseSumMatchesN(arr, currIndex + 1, n, K, path);
        if (elementSkipped)
            return true;
        boolean elementConsidered =
                findSubsetWhoseSumMatchesN(arr, currIndex + 1, n, K - curr, path);
        if (elementConsidered)
            path.push(curr);
        return elementConsidered;
    }
}
