package datastructures.pq;

import static datastructures.pq.HeapUtils.KthSmallestLargestUtil.kthLargestUsingMinHeap;
import static datastructures.pq.HeapUtils.KthSmallestLargestUtil.kthSmallestUsingMaxHeap;
import static datastructures.pq.HeapUtils.printStringWithNonRepeatingChars;
import static org.junit.Assert.assertEquals;

import datastructures.pq.HeapUtils.PrintKMaxSumsOfTwoEquallySizedArraysUtil;
import org.junit.Test;

public class HeapUtilsTest {

  @Test
  public void test_kthLargest() {
    Integer[] arr = new Integer[] {1, 2, 3, 4, 5, 6};
    assertEquals(3, kthLargestUsingMinHeap(arr, 4));
    assertEquals(6, kthLargestUsingMinHeap(arr, 1));
    assertEquals(1, kthLargestUsingMinHeap(arr, 6));
  }

  @Test
  public void test_kthSmallest() {
    Integer[] arr = new Integer[] {1, 2, 3, 4, 5, 6};
    assertEquals(4, kthSmallestUsingMaxHeap(arr, 4));
    assertEquals(1, kthSmallestUsingMaxHeap(arr, 1));
    assertEquals(6, kthSmallestUsingMaxHeap(arr, 6));
  }

  @Test
  public void test_PrintKMaxSumsOfTwoEquallySizedArraysUtil() {
    PrintKMaxSumsOfTwoEquallySizedArraysUtil util =
        new PrintKMaxSumsOfTwoEquallySizedArraysUtil(new int[] {3, 2}, new int[] {1, 4}, 2);
    System.out.println(util.getResult());

    util = new PrintKMaxSumsOfTwoEquallySizedArraysUtil(new int[] {4, 2, 5, 1}, new int[] {8, 0, 3, 5}, 4);
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
