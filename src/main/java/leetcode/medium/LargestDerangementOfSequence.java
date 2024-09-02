package leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://www.geeksforgeeks.org/largest-derangement-sequence/
 */
public class LargestDerangementOfSequence {

  public void derange(int[] arr) {
    if (arr == null) {
      return;
    }
    Comparator<int[]> comparator = Comparator.comparingInt(a -> a[1]);
    PriorityQueue<int[]> maxPq = new PriorityQueue<>(comparator.reversed());

    for (int i = 0; i < arr.length; i++) {
      maxPq.offer(new int[] {i, arr[i]}); // n*log(n)
    }

    int[] prev = null;
    int index = 0;
    while (!maxPq.isEmpty()) { //n*log(n)
      int[] curr = maxPq.poll();
      if (curr[0] != index) {
        arr[index++] = curr[1];
        if (prev != null) {
          arr[index++] = prev[1];
        }
      } else {
        prev = curr;
      }
    }
  }

  public static void main(String[] args) {
    LargestDerangementOfSequence util = new LargestDerangementOfSequence();
    int[] arr = new int[] {5, 4, 3, 2, 1};
    util.derange(arr);
    System.out.println(Arrays.toString(arr));

    arr = new int[] {1, 2, 3};
    util.derange(arr);
    System.out.println(Arrays.toString(arr));
  }
}
