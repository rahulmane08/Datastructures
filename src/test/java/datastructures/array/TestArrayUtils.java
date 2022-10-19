package datastructures.array;

import static datastructures.array.ArrayUtils.ArrangementUtils.*;
import static datastructures.array.ArrayUtils.concatenateArrayToLargestNumber;
import static datastructures.array.ArrayUtils.findMajorityElement;
import static datastructures.array.ArrayUtils.findMaxJMinusI;
import static datastructures.array.ArrayUtils.largestContiguousSubArray;
import static datastructures.array.ArrayUtils.totalRainWaterTrapped;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import datastructures.array.ArrayUtils.FindIndexEqualsElementInSortedArrayUtil;
import datastructures.matrix.MatrixUtils;
import org.junit.Test;

public class TestArrayUtils {

    private static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test_FindIndexEqualsElementInSortedArrayUtil() {
        FindIndexEqualsElementInSortedArrayUtil util = new FindIndexEqualsElementInSortedArrayUtil();
        assertEquals(4, util.find(new int[]{2, 2, 3, 3, 4}));
        assertEquals(3, util.find(new int[]{2, 2, 3, 3, 3}));
        assertEquals(2, util.find(new int[]{2, 2, 2, 2, 2}));
    }

    @Test
    public void test_printSpiralOrder() {
        int[][] arr = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        MatrixUtils.PrintUtils.printZigzagOrder(arr);
    }

    @Test
    public void test_printCombinations() {
        ArrayUtils.printCombinations(new int[]{1, 2, 3, 4, 5}, 2);
    }

    @Test
    public void test_findMedianSortedArrays() {
        System.out.println(ArrayUtils.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
    }

    @Test
    public void test_reverse() {
        int[] a = new int[]{5, 6, 7, 8};
        ArrayUtils.reverse(a);
        System.out.println(Arrays.toString(a));
        ArrayUtils.reverse(a, 2, 3);
        System.out.println(Arrays.toString(a));
    }

    @Test
    public void test_largestContiguousSubArray() {
        largestContiguousSubArray(new int[]{-2, -3, 4, -1, -2, 1, 5, -3});
        largestContiguousSubArray(new int[]{-2, -3, 4, 1, -2, -1, 5, -3});
        largestContiguousSubArray(new int[]{-2, -3});
        largestContiguousSubArray(new int[]{11, -2, -3, -10});
        largestContiguousSubArray(new int[]{-2, 11, -3, -10});
        largestContiguousSubArray(new int[]{-2, -1});
        largestContiguousSubArray(new int[]{-2, -1, 1, 2, 3, 10});
        largestContiguousSubArray(new int[]{10, 7, 8, -2, -1, 1, 2});
        largestContiguousSubArray(new int[]{10, 7, 8, -2, -1, 2});
    }

    @Test
    public void test_concatenateArrayToLargestNumber() {
        assertEquals("6054854654", concatenateArrayToLargestNumber(new Integer[]{54, 546, 548, 60}));
        assertEquals("998764543431",
                concatenateArrayToLargestNumber(new Integer[]{1, 34, 3, 98, 9, 76, 45, 4}));
        assertEquals("550110",
                concatenateArrayToLargestNumber(new Integer[]{50, 5, 10, 1}));
    }

    @Test
    public void test_findMajorityElement() {
        int[] arr = {3, 3, 4, 2, 4, 4, 2, 4, 4};
        int i = findMajorityElement(arr);
        assertEquals(4, arr[i]);

        arr = new int[]{3, 3, 4, 2, 4, 4, 2, 4};
        i = findMajorityElement(arr);
        assertEquals(-1, i);
    }

    @Test
    public void test_findMaxJMinusI() {
        // System.out.println(findMaxJMinusI(new int[]{12, 11, 10, 9, 8, 7}));
        System.out.println(findMaxJMinusI(new int[]{34, 8, 10, 3, 2, 80, 30, 33, 1}));
    }

    @Test
    public void test_moveAllZeroesAtEnd() {
        int arr[] = {1, 2, 0, 4, 3, 0, 5, 0};
        moveAllZeroesAtEnd(arr);
        print(arr);
    }

    @Test
    public void test_moveAllZeroesAtEndUsingTwoPointers() {
        int arr[] = {1, 2, 0, 4, 3, 0, 5, 0};
        moveAllZeroesAtEndUsingTwoPointers(arr);
        print(arr);

        arr = new int[]{1, 2, 0, 4,0, 0, 3, 0, 5, 0};
        moveAllZeroesAtEndUsingTwoPointers(arr);
        print(arr);
    }

    @Test
    public void test_sortAsPerIndexedArray() {
        int[] order = new int[]{3, 0, 4, 1, 2};
        int[] arr = new int[]{50, 40, 70, 60, 90};
        reorderAsPerIndexedArray(arr, order);
        print(arr);
        print(order);
    }

    @Test
    public void test_totalRainWaterTrapped() {
        System.out.println(totalRainWaterTrapped(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    @Test
    public void test_rearrageAlternatePositiveAndNegative() {

    }

    @Test
    public void test_leftRotateByReversal() {
        int [] arr = {1,2,3,4,5,6,7};
        ArrayUtils.RotationUtils.leftRotateByReversal(arr, 4, 1, 6);
        System.out.println(Arrays.toString(arr));
    }
}
