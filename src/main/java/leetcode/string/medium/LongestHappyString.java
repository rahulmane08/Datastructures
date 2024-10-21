package leetcode.string.medium;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LongestHappyString {
  public String longestDiverseString(int a, int b, int c) {
    String happyString = "";
    PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt((int[] arr) -> arr[1]).reversed());
    queue.offer(new int[] {'a', a});
    queue.offer(new int[] {'b', b});
    queue.offer(new int[] {'c', c});

    int[] prev = null;
    while (!queue.isEmpty()) {
      int[] curr = queue.poll();
      char currChar = (char) curr[0];

      if (curr[1] != 0) {
        // max or max remaining frequency
        for (int i = 0; i < 2 && curr[1] > 0; i++, curr[1]--) {
          happyString += currChar;
        }

        // add the previously processed element back to queue, this is stored in prev so that the max element doesnt
        // keep barging.
        if (prev != null) {
          queue.offer(prev);
          prev = null;
        }

        // if the current element has more frequency to be processed.
        if (curr[1] != 0) {
          prev = curr;
        }
      }
    }
    return happyString;
  }

  public static void main(String[] args) {
    LongestHappyString util = new LongestHappyString();
    System.out.println(util.longestDiverseString(7, 1, 0));
    System.out.println(util.longestDiverseString(1, 1, 7));
    System.out.println(util.longestDiverseString(0, 0, 7));
    System.out.println(util.longestDiverseString(0, 0, 0));
    System.out.println(util.longestDiverseString(3, 3, 3));
  }
}
