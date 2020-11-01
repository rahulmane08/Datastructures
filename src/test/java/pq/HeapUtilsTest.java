package pq;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static pq.HeapUtils.KthSmallestLargestUtil.kthLargestUsingMinHeap;
import static pq.HeapUtils.KthSmallestLargestUtil.kthSmallestUsingMaxHeap;
import static pq.HeapUtils.printStringWithNonRepeatingChars;

import java.util.Arrays;

import org.junit.Test;
import pq.HeapUtils.PrintKMaxSumsOfTwoEquallySizedArraysUtil;

public class HeapUtilsTest {

    @Test
    public void test_kthLargest() {
        Integer[] arr = new Integer[]{1,2,3,4,5,6};
        assertEquals(3, kthLargestUsingMinHeap(arr, 4));
        assertEquals(6, kthLargestUsingMinHeap(arr, 1));
        assertEquals(1, kthLargestUsingMinHeap(arr, 6));
    }

    @Test
    public void test_kthSmallest() {
        Integer[] arr = new Integer[]{1,2,3,4,5,6};
        assertEquals(4, kthSmallestUsingMaxHeap(arr, 4));
        assertEquals(1, kthSmallestUsingMaxHeap(arr, 1));
        assertEquals(6, kthSmallestUsingMaxHeap(arr, 6));
    }

    @Test
    public void test_largestDerangement() {
        int [] arr = {56, 21, 42, 67, 23, 74};
        HeapUtils.largestDerangement(arr);
        assertTrue(Arrays.equals(new int[] {74, 67, 56, 42, 21, 23}, arr));
        arr = new int[] {5, 4, 3, 2, 1};
        HeapUtils.largestDerangement(arr);
        assertTrue(Arrays.equals(new int[] {4, 5, 2, 1, 3}, arr));
    }

    @Test
    public void test_smallestDerangement() {
        int [] arr = {56, 21, 42, 67, 23, 74};
        HeapUtils.smallestDerangement(arr);
        assertTrue(Arrays.equals(new int[] {21, 23, 56, 42, 74, 67}, arr));
        arr = new int[] {1,2,3,4,5};
        HeapUtils.smallestDerangement(arr);
        assertTrue(Arrays.equals(new int[] {2, 1, 4, 5, 3}, arr));
    }

    @Test
    public void test_PrintKMaxSumsOfTwoEquallySizedArraysUtil() {
        PrintKMaxSumsOfTwoEquallySizedArraysUtil util =
                new PrintKMaxSumsOfTwoEquallySizedArraysUtil(new int[]{3, 2}, new int[]{1, 4}, 2);
        System.out.println(util.getResult());

        util = new PrintKMaxSumsOfTwoEquallySizedArraysUtil(new int[]{4, 2, 5, 1}, new int[]{8, 0, 3, 5}, 4);
        System.out.println(util.getResult());
    }

    @Test
    public void test_printMedian1() {
        int[] arr = new int[] {5, 15, 1, 3};
        HeapUtils.printMedian1(arr);
    }

    @Test
    public void test_printStringWithNonRepeatingChars() {
        printStringWithNonRepeatingChars("aaabc");
        printStringWithNonRepeatingChars("aaaabc");
    }
}
