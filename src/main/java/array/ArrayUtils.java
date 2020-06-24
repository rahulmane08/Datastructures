package array;

import static java.lang.Math.abs;

import java.util.Arrays;
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
        Comparator<Integer> comparator = (o1, o2) -> {
            int digits1 = Utils.countDigits(o1);
            int digits2 = Utils.countDigits(o2);
            if (digits1 != digits2) {
                int msd1, msd2;
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
        };
        java.util.Arrays.sort(arr, comparator);
        for (int i : arr)
            result.append(String.valueOf(i));
        return result.toString();
    }

    public static String concatenateArrayToLargestNumber1(Integer[] arr) {
        Arrays.parallelSort(arr, (e1, e2) -> {
            String x = String.valueOf(e1);
            String y = String.valueOf(e2);
            return -(x + y).compareTo(y + x);
        });
        StringBuilder result = new StringBuilder();
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
    static public void leftRotate(int[] arr, int d) {
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
     *
     * @param arr
     * @param d
     */
    static public void leftRotateByGCD(int[] arr, int d) {
        leftRotateByGCD(arr, d, 0, arr.length);
    }

    static public void leftRotateByGCD(int[] arr, int d, int start) {
        leftRotateByGCD(arr, d, start, arr.length);
    }

    static public void leftRotateByGCD(int[] arr, int d, int start, int end) {
        if (arr == null || start > end || start < 0 || end < 0) return;
        int n = end - start;
        int gcd = Math.gcd(n, d);
        for (int i = start; i < start + (d % n); i++) {
            int k = gcd == 1 ? start : i;
            int temp = arr[k];
            while (k < end) {
                int next = k + gcd;
                if (next >= end) {
                    arr[k] = temp;
                } else {
                    arr[k] = arr[next];
                }
                k = next;
            }
        }
    }


    static public void leftRotateByReversal(int[] arr, int d) {
        if (arr == null)
            return;
        leftRotateByReversal(arr, d, 0, arr.length - 1);
    }

    static public void leftRotateByReversal(int[] arr, int d, int startIndex, int endIndex) {
        if (arr == null)
            return;
        int n = endIndex - startIndex + 1;
        d = d % n;
        reverse(arr, startIndex, d - 1);
        reverse(arr, startIndex + d, endIndex);
        reverse(arr, startIndex, endIndex);
    }

    static public void rightRotateByReversal(int[] arr, int d) {
        if (arr == null)
            return;
        rightRotateByReversal(arr, d, 0, arr.length - 1);
    }

    public static void rightRotateByReversal(int[] arr, int d, int startIndex, int endIndex) {
        if (arr == null)
            return;
        int n = endIndex - startIndex + 1;
        d = d % n;
        reverse(arr, endIndex - d + 1, endIndex);
        reverse(arr, startIndex, endIndex - d);
        reverse(arr, startIndex, endIndex);
    }

    static public void reverse(int[] arr) {
        reverse(arr, 0, arr.length - 1);
    }

    static public void reverse(int[] arr, int start, int end) {
        if (arr == null || start > end || start < 0 || end < 0) return;
        int mid = start + (end - start) / 2;
        int i = start;
        int j = end;
        while (i <= mid && i <= j) {
            swap(arr, i++, j--);
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

    public static void swap(int[] arr, int i, int j) {
        if (arr == null)
            return;
        int n = arr.length;
        if (i > n || i < 0 || j > n || j < 0)
            return;
        if (i == j)
            return;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void largestContiguousSubArray(int[] arr) {
        if (arr == null)
            return;
        int startIndex = 0;
        int endIndex = 0;
        int sum = arr[0];
        int maxSum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum = sum + arr[i];
            if (sum >= maxSum) {
                maxSum = sum;
                endIndex = i;
            }
            if (arr[i] > maxSum) {
                startIndex = i;
                maxSum = sum = arr[i];
            }
            if (endIndex < startIndex)
                endIndex = startIndex;
        }
        System.out.printf("For array:%s: Largest contiguous startIndex=%d, endIndex=%d, sum=%d%n",
                Arrays.toString(arr), startIndex, endIndex, maxSum);
    }

    public static int findMajorityElement(int[] arr) {
        if (arr == null)
            throw new IllegalArgumentException("null array");
        Arrays.sort(arr);
        int n = arr.length;
        int i = 1;
        int j = n - 2;
        int countLeft = 1;
        int countRight = 1;
        int totalCount;
        while (i < j) {
            if (arr[i - 1] == arr[i])
                countLeft++;
            else
                countLeft = 1;
            if (arr[j] == arr[j + 1])
                countRight++;
            else
                countRight++;
            i++;
            j--;
        }

        if (arr[i - 1] == arr[i])
            countLeft++;
        else
            countLeft = 1;

        if (arr[j] == arr[j + 1])
            countRight++;
        else
            countRight++;

        if (i == j)
            totalCount = countLeft + countRight - 1;
        else
            totalCount = countLeft + countRight - 2;
        if (totalCount <= n / 2)
            throw new RuntimeException("No majority element found");
        return arr[i];
    }

    public static int findMajorityElement1(int[] arr) {
        if (arr == null)
            throw new IllegalArgumentException("null array");
        int n = arr.length;
        int i = 1;
        int j = n - 2;
        int countLeft = 1;
        int countRight = 1;
        int left = arr[0];
        int right = arr[n - 1];
        int prevLeft = left;
        int prevRight = right;
        while (i <= j) {
            if (arr[i - 1] == arr[i]) {
                countLeft++;
            } else {
                countLeft = 1;
                left = arr[i];
            }

            if (arr[j] == arr[j + 1]) {
                countRight++;
            } else {
                countRight++;
                right = arr[j];
            }
            i++;
            j--;
        }
        // case1: element found in left half
        if (countLeft > countRight) {
            while (i < n) {
                if (arr[i++] == left && ++countLeft > n / 2)
                    return left;
            }
        } else if (countLeft < countRight) {
            while (j > -1) {
                if (arr[j--] == left && ++countRight > n / 2)
                    return right;
            }
        } else {
            if (countLeft + countRight - 1 > n / 2)
                return left;
        }
        throw new IllegalArgumentException("No majority element found");
    }

    /**
     * since all elements lie between 0 and N-1
     * Negate the element at arr[abs(i)] if its positive.
     * If arr[abs(i)] is negative then its a repetition.
     *
     * @param arr
     */
    public static void printRepeatingElementsBetween0AndN(int[] arr) {
        if (arr == null)
            return;
        int n = arr.length;
        String errorMsg = String.format("All elements must be between %d and %d", 0, n - 1);
        for (int i : arr) {
            int j = abs(i);
            if (j < 0 || j >= n)
                throw new IllegalArgumentException(errorMsg);
            if (arr[j] >= 0)
                arr[j] = -arr[j];
            else
                System.out.print(j + " ");
        }
        System.out.println();
    }

    /**
     * Input:  arr[] = {2, 1, 5, 6, 3}, k = 3
     * Output: 1
     * <p>
     * Explanation:
     * To bring elements 2, 1, 3 together, swap
     * element '5' with '3' such that final array
     * will be-
     * arr[] = {2, 1, 3, 6, 5}
     * <p>
     * Input:  arr[] = {2, 7, 9, 5, 8, 7, 4}, k = 5
     * Output: 2
     *
     * @param arr
     * @param k
     */
    public static int minSwapsToGetElementsLessThanOrEqualToKTogether(int[] arr, int k) {
        if (arr == null)
            return -1;
        int n = arr.length;
        int i = 0;
        int j = n - 1;
        int swaps = 0;
        while (i < j) {
            while (arr[i] <= k)
                i++;
            while (arr[j] > k)
                j--;
            if (i >= j)
                break;
            ArrayUtils.swap(arr, i, j);
            i++;
            j--;
            swaps++;
        }
        return swaps;
    }

    public static void alternateMinMax(int[] arr) {
        if (arr == null)
            return;
        Arrays.sort(arr);
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 1) {
                rightRotateByReversal(arr, 1, i, n - 1);
            }
        }
    }

    public static void doubleValidNumberAndReplaceWith0AtTheEnd(int[] arr) {
        if (arr == null)
            return;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] != 0 && arr[i] == arr[i + 1]) {
                arr[i] = 2 * arr[i];
                arr[i + 1] = 0;
            }
        }
        ArrayArrangementUtils.moveAllZeroesAtEnd(arr);
    }

    /**
     * Given an array arr[], find the maximum j – i such that arr[j] > arr[i].
     * Input: {34, 8, 10, 3, 2, 80, 30, 33, 1}
     * Output: 6  (j = 7, i = 1)
     *
     * @param arr
     */
    public static void findMaxJMinusI(int[] arr) {
        if (arr == null)
            return;

    }

    /**
     * {1 ,2, 2, 4}
     * o/p: 2
     */
    public static class FindIndexEqualsElementInSortedArrayUtil {
        private int index = -1;

        public int find(int[] arr) {
            if (arr != null) {
                find(arr, 0, arr.length - 1);
            }
            return index;
        }

        private boolean find(int[] arr, int low, int high) {

            if (low >= high)
                return false;

            if (high < arr[low] || arr[high] < low) {
                return false;
            }

            if (low == arr[low]) {
                index = low;
                return true;
            }

            if (high == arr[high]) {
                index = high;
                return true;
            }
            int mid = (low + high) / 2;
            return find(arr, low, mid) || find(arr, mid + 1, high);
        }
    }

    /**
     * Input: arr[] = {1, 2}, x = 1, y = 2
     * Output: Minimum distance between 1 and 2 is 1.
     *
     * Input: arr[] = {3, 4, 5}, x = 3, y = 5
     * Output: Minimum distance between 3 and 5 is 2.
     *
     * Input: arr[] = {3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3}, x = 3, y = 6
     * Output: Minimum distance between 3 and 6 is 4.
     *
     * Input: arr[] = {2, 5, 3, 5, 4, 4, 2, 3}, x = 3, y = 2
     * Output: Minimum distance between 3 and 2 is 1.
     * @param arr
     * @param x
     * @param y
     * @return
     */
    public static int findMinDistBetweenTwoElements(int[] arr, int x, int y) {
        if (arr == null)
            return -1;
        int minDist = Integer.MAX_VALUE;
        int xIndex = -1, yIndex = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) {
                xIndex = i;
            }
            if (arr[i] == y) {
                yIndex = i;
            }
            if (xIndex != -1 && yIndex != -1 && abs(xIndex - yIndex) < minDist ) {
                minDist = abs(xIndex - yIndex);
            }
        }
        return minDist;
    }

}
