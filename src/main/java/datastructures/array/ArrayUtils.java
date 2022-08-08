package datastructures.array;

import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.util.*;

import interfaces.*;
import math.Math;

public class ArrayUtils {

    /**
     * https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/727/
     *
     * @param nums
     * @return
     */
    @LCEasy
    static public int removeDuplicates(int[] nums) {
        /**
         * 1 1 1 2 3 3 4 4
         *
         * 1 2 2 2 3 3 4 4
         *
         * 1 2 3 3 3 3 4 4
         *
         * 1 2 3 4 4 4 4 4
         */
        int i = 0;
        int j;
        for (; i + 1 < nums.length; i++) {
            if (nums[i] == nums[i + 1]) {
                for (j = i + 1; j + 1 < nums.length && nums[j] == nums[j + 1]; j++) ;
                if (j == nums.length - 1) {
                    break;
                }
                int greater = nums[j + 1];
                for (; j >= i + 1; j--) {
                    nums[j] = greater;
                }
            }
        }
        return i + 1;
    }

    @LCEasy
    static public int twoSum(int[] arr, int N) {
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
            } else if (sum > N) {
                --end;
            } else {
                ++start;
            }
        }
        System.out.println("Total Pairs = " + count);
        return count;
    }

    @LCEasy
    static public int twoSumWithHashing(int[] arr, int sum) {
        Map<Integer, Integer> map = new HashMap();
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int diff = sum - arr[i];
            int j = map.getOrDefault(diff, -1);
            if (j != -1 && j != i) {
                System.out.printf("Found pair: ( %d, %d) %n)", arr[i], arr[j]);
                count++;
            }
            map.put(arr[j], j);
        }
        return count;
    }

    static public int threeSum(int[] arr, int sum) {
        java.util.Arrays.sort(arr);// nlog(n)
        int count = 0;
        int fixed, left, right;
        for (int i = 0; i < arr.length; i++) {
            fixed = i;
            left = i + 1;
            right = arr.length - 1;

            while (left < right) {
                int curSum = arr[fixed] + arr[left] + arr[right];
                if (curSum == sum) {
                    System.out.printf("Triplet (%d,%d,%d)%n", arr[fixed], arr[left], arr[right]);
                    left++;
                    right--;
                    count++;
                } else if (curSum > sum) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        System.out.println("Total Pairs = " + count);
        return count;
    }

    static public int threeSumWithHashing(int[] arr, int sum) {
        HashSet<Integer> dupes = new HashSet<>();
        Map<Integer, Integer> complements = new HashMap<>();
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (dupes.contains(arr[i])) {
                continue;
            }
            dupes.add(arr[i]);
            for (int j = i + 1; j < arr.length; j++) {
                int complement = sum - (arr[i] + arr[j]);
                if (complements.getOrDefault(complements, -1) == i) {
                    System.out.printf("Triplet (%d,%d,%d)%n", arr[i], arr[j], complement);
                    count++;
                }
                complements.put(arr[j], i);
            }
        }
        return count;
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

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] merged = merge(nums1, nums2);

        if (merged == null) {
            return 0;
        }

        int i = 0, j = merged.length - 1;
        for (; i < j; i++, j--) ;
        double median = (merged[i] + merged[j]) / 2d;
        return median;
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

    public static String concatenateArrayToLargestNumber(Integer[] arr) {
        Arrays.sort(arr, (e1, e2) -> {
            String x = String.valueOf(e1);
            String y = String.valueOf(e2);
            return (y + x).compareTo(x + y);
        });
        StringBuilder result = new StringBuilder();
        for (int i : arr)
            result.append(i);
        return result.toString();
    }

    static public void reverse(int[] arr) {
        reverse(arr, 0, arr.length - 1);
    }

    static public void reverse(int[] arr, int start, int end) {
        if (arr == null || start > end || start < 0 || end < 0) return;
        int i = start, j = end;
        while (i < j) {
            swap(arr, i++, j--);
        }
    }

    /**
     * Find if there is a subset of a given set with a given sum.
     * Input:  set[] = {3, 34, 4, 12, 5, 2, 11}, sum = 9
     * Output:  True  subset= {4, 5}
     *
     * @param arr
     * @param N
     */
    @Important
    @Hard
    @DynamicProgramming
    static public void subsetWhoseSumMatchesN(int[] arr, int N) {
        if (arr == null || arr.length == 0)
            return;
        Stack<Integer> subset = new Stack<>();
        if (findSubsetWhoseSumMatchesN(arr, 0, arr.length, N, subset)) {
            System.out.print("subset found: [ ");
            while (!subset.isEmpty())
                System.out.printf("%d ", subset.pop());
            System.out.println("]");
        }
    }

    static private boolean findSubsetWhoseSumMatchesN(int[] arr, int currIndex, int n, int N, Stack<Integer> path) {
        if (currIndex == n && N != 0)
            return false;
        if (N == 0)
            return true;
        int curr = arr[currIndex];
        if (curr > N)
            return findSubsetWhoseSumMatchesN(arr, currIndex + 1, n, N, path);
        boolean elementSkipped = findSubsetWhoseSumMatchesN(arr, currIndex + 1, n, N, path);
        if (elementSkipped)
            return true;
        boolean elementConsidered =
                findSubsetWhoseSumMatchesN(arr, currIndex + 1, n, N - curr, path);
        if (elementConsidered)
            path.push(curr);
        return elementConsidered;
    }

    @Important
    @Medium
    public static void largestContiguousSubArray(int[] arr) {
        if (arr == null)
            return;
        int start = 0;
        int end = 0;
        int currSum = arr[0];
        int maxSum = arr[0];

        for (int i = 1; i < arr.length; i++) {
            currSum += arr[i];

            if (maxSum <= currSum) {
                maxSum = currSum;
                end = i;
            }

            for (; start + 1 <= end && currSum - arr[start] > currSum; ) {
                currSum = currSum - arr[start++];
                maxSum = currSum;
            }
        }
        System.out.printf("For array:%s: Largest contiguous start=%d, end=%d, maxSum=%d%n",
                Arrays.toString(arr), start, end, maxSum);
    }

    /**
     * Given an array arr[], find the maximum j – i such that arr[j] > arr[i].
     * Input: {34, 8, 10, 3, 2, 80, 30, 33, 1}
     * Output: 6  (j = 7, i = 1)
     *
     * @param arr
     */
    @Important
    @Hard
    public static int findMaxJMinusI(int[] arr) {
        int n = arr.length;
        int maxDiff;
        int i, j;

        int[] RMax = new int[n];
        int[] LMin = new int[n];

        /* Construct LMin[] such that LMin[i] stores the minimum value
           from (arr[0], arr[1], ... arr[i]) */
        LMin[0] = arr[0];
        for (i = 1; i < n; ++i) {
            LMin[i] = Math.min(arr[i], LMin[i - 1]);
        }

        /* Construct RMax[] such that RMax[j] stores the maximum value
           from (arr[j], arr[j+1], ..arr[n-1]) */
        RMax[n - 1] = arr[n - 1];
        for (j = n - 2; j >= 0; --j) {
            RMax[j] = Math.max(arr[j], RMax[j + 1]);
        }

        /* Traverse both arrays from left to right to find optimum j - i
           This process is similar to merge() of MergeSort */
        i = 0;
        j = 0;
        maxDiff = -1;
        while (j < n && i < n) {
            if (LMin[i] < RMax[j]) {
                maxDiff = Math.max(maxDiff, j - i);
                j = j + 1;
            } else {
                i = i + 1;
            }
        }

        return maxDiff;
    }

    /**
     * A majority element in an array A[] of size n is an element that appears more than n/2 times
     * (and hence there is at most one such element).
     * <p>
     * MOORES VOTING ALGO:
     *
     * @param arr
     * @return
     */
    @Important
    @Medium
    public static int findMajorityElement(int[] arr) {
        if (arr == null)
            throw new IllegalArgumentException("null array");

        int n = arr.length;
        int count = 1;
        int majorityIndex = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] == arr[majorityIndex]) {
                count++;
            } else {
                if (--count == 0) {
                    majorityIndex = i;
                    count = 1;
                }
            }
        }

        if (count > n / 2) {
            return majorityIndex;
        }

        count = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == arr[majorityIndex]) {
                count++;
            }
        }

        if (count > n / 2) {
            return majorityIndex;
        }

        return -1;
    }

    /**
     * since all elements lie between 0 and N-1
     * Negate the element at arr[abs(i)] if its positive.
     * If arr[abs(i)] is negative then its a repetition.
     *
     * @param arr
     */
    @Important
    @Medium
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
                RotationUtils.rightRotateByReversal(arr, 1, i, n - 1);
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
        ArrangementUtils.moveAllZeroesAtEnd(arr);
    }

    /**
     * Input: arr[] = {1, 2}, x = 1, y = 2
     * Output: Minimum distance between 1 and 2 is 1.
     * <p>
     * Input: arr[] = {3, 4, 5}, x = 3, y = 5
     * Output: Minimum distance between 3 and 5 is 2.
     * <p>
     * Input: arr[] = {3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3}, x = 3, y = 6
     * Output: Minimum distance between 3 and 6 is 4.
     * <p>
     * Input: arr[] = {2, 5, 3, 5, 4, 4, 2, 3}, x = 3, y = 2
     * Output: Minimum distance between 3 and 2 is 1.
     *
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
            if (xIndex != -1 && yIndex != -1) {
                minDist = min(minDist, abs(xIndex - yIndex));
            }
        }
        return minDist;
    }

    public static void printCombinations(int[] arr, int r) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int n = arr.length;
        if (r > n) {
            System.out.println("No combinations possible");
            return;
        }
        combinationUtil(arr, 0, n - 1, 0, r, new int[r]);
    }

    /**
     * [1,2,3]
     * 1 -- combinationUtil(arr, 1, 2, 1, 2, [1])
     * -- 2 -- combinationUtil(arr, 1, 2, 1, 2, [1])
     *
     * @param arr
     * @param start
     * @param end
     * @param index
     * @param r
     * @param data
     */
    private static void combinationUtil(int[] arr, int start, int end, int index, int r, int[] data) {
        if (index == r) {
            System.out.println(Arrays.toString(data));
            return;
        }
        for (int i = start; i <= end; i++) {
            data[index] = arr[i];
            combinationUtil(arr, i + 1, end, index + 1, r, data);
        }
    }

    @Important
    @Medium
    public static int containerWithMaxWater(int[] arr) {
        if (arr == null) {
            return 0;
        }
        int start = 0;
        int end = arr.length - 1;
        int capacity = 0;
        while (start < end) {
            capacity = Math.max(capacity, Math.min(arr[start], arr[end]) * (end - start));
            if (arr[start] < arr[end]) {
                start++;
            } else {
                end--;
            }
        }
        return capacity;
    }

    @Important
    @Medium
    public static int totalRainWaterTrapped(int[] arr) {
        if (arr == null) {
            return 0;
        }

        int n = arr.length;
        int[] maxWaterCapacity = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, arr[i]);
            maxWaterCapacity[i] = max;
        }

        max = 0;
        for (int i = n - 1; i >= 0; i--) {
            max = Math.max(max, arr[i]);
            maxWaterCapacity[i] = Math.min(max, maxWaterCapacity[i]);
        }

        int totalCapacity = 0;
        for (int i = 0; i < n; i++) {
            totalCapacity += (maxWaterCapacity[i] - arr[i]);
        }
        return totalCapacity;
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

    public static class ArrangementUtils {

        public static void rearrageAlternateMaxAndMin(int[] arr) {
            if (arr == null)
                return;
            Arrays.sort(arr);
            int n = arr.length;
            int mid = (n % 2 == 0) ? n / 2 : (n / 2) + 1;
            int end = (n % 2 == 0) ? n - 2 : n - 1;
            for (int i = 0; i < mid; ) {
                int j = end - i;
                swap(arr, i, j);
                i = i + 2;
            }
        }

        /**
         * Input:  arr[] = {1, 2, 3, -4, -1, 4}
         * Output: arr[] = {-4, 1, -1, 2, 3, 4}
         * <p>
         * Input:  arr[] = {-5, -2, 5, 2, 4, 7, 1, 8, 0, -8}
         * output: arr[] = {-5, 5, -2, 2, -8, 4, 7, 1, 8, 0}
         *
         * @param arr
         */
        @Important
        public static void rearrageAlternatePositiveAndNegative(int[] arr) {
            if (arr == null)
                return;
            int n = arr.length;
            for (int i = 0; i < n - 1; i++) {
                int j = i + 1;
                if (i % 2 == 0) {
                    if (arr[i] >= 0) {
                        while (j < n && arr[j] >= 0)
                            j++;
                        if (j >= n)
                            break;
                        RotationUtils.rightRotateByReversal(arr, 1, i, j);
                    }
                } else {
                    if (arr[i] < 0) {
                        while (j < n && arr[j] < 0)
                            j++;
                        if (j >= n)
                            break;
                        RotationUtils.rightRotateByReversal(arr, 1, i, j);
                    }
                }
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
                    RotationUtils.leftRotateByReversal(arr, 1, startIndex, lastNonZeroIndex);
                }
                startIndex++;
            }
        }

        public static void moveAllZeroesAtEndUsingTwoPointers(int[] nums) {
            if (nums == null || nums.length == 0) {
                return;
            }
            int x = 0; // tracking 0 element index
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) {
                    if (nums[x] != 0) {
                        x = i;
                    }
                } else {
                    if (nums[x] == 0) {
                        nums[x] = nums[i];
                        nums[i] = 0;
                        x++;
                    }
                }
            }
        }

        /**
         * Input:  arr[]   = [50, 40, 70, 60, 90]
         * index[] = [3,  0,  4,  1,  2]
         * Output: arr[]   = [40, 60, 90, 50, 70]
         *
         * @param arr
         * @param indexes
         */
        @Important
        @Medium
        public static void sortAsPerIndexedArray(int[] arr, int[] indexes) {
            if (arr == null || indexes == null || arr.length != indexes.length)
                return;
            for (int i = 0; i < indexes.length; i++) {
                while (indexes[i] != i) {
                    int currentElement = arr[i];
                    int currentElementTargedIndex = indexes[i]; // index to put current element
                    int targetElement = arr[currentElementTargedIndex]; // element at the target indexed
                    int targetElementsTargetIndex = indexes[currentElementTargedIndex]; // index of the element at target index.

                    // swap current index + element with targets index + element
                    indexes[currentElementTargedIndex] = currentElementTargedIndex; // copy current elemt to target
                    arr[currentElementTargedIndex] = currentElement; // copy current index to target

                    indexes[i] = targetElementsTargetIndex;
                    arr[i] = targetElement;
                }
            }
        }
    }

    public static class RotationUtils {

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
            reverse(arr, startIndex, startIndex + d - 1);
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
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> result = new ArrayList();
        int m = nums1.length;
        int n = nums2.length;
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0, j = 0;

        for (; i < m && j < n;) {
            if(nums1[i] < nums2[j]) {
                i++;
            } else if (nums2[j] < nums1[i]) {
                j++;
            } else {
                result.add(nums1[i]);
                i++;
                j++;
            }
        }
        return result.stream().mapToInt(x -> x).toArray();
    }
}
