package leetcode.greedy.medium;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LongestPalindromicNumber {
  public String largestPalindromic(String num) {
    Map<Character, Integer> frequencies = new HashMap<>(); //S(n)
    int i = 0;
    for (; i < num.length() - 1 && num.charAt(i) == '0'; i++) ;

    for (; i < num.length(); i++) {
      frequencies.compute(num.charAt(i), (digit, freq) -> freq == null ? 1 : freq + 1); //T(n)
    }
    String left = "";
    String right = "";
    String middle = "";
    PriorityQueue<Character> singleElementsQ = new PriorityQueue<>(Comparator.reverseOrder());
    Comparator<int[]> comparator = Comparator.comparingInt(a -> a[0]);
    PriorityQueue<int[]> multiElementsQ = new PriorityQueue<>(comparator.reversed());
    for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
      int digit = entry.getKey() - '0';
      Integer freq = entry.getValue();
      if (freq == 1) {
        singleElementsQ.offer(entry.getKey());
      } else {
        multiElementsQ.offer(new int[] {digit, freq});
      }
    }

    while (!multiElementsQ.isEmpty()) {
      int[] highest = multiElementsQ.poll();
      char c = (char) (highest[0] + '0');
      int freq = highest[1];
      while (freq > 1) {
        left = left + c;
        right = c + right;
        freq -= 2;
      }

      if (freq == 1) {
        singleElementsQ.offer(c);
      }
    }

    if (!singleElementsQ.isEmpty()) {
      middle = "" + singleElementsQ.poll();
    }
    return left + middle + right;
  }

  public static void main(String[] args) {
    LongestPalindromicNumber util = new LongestPalindromicNumber();
    System.out.println(util.largestPalindromic("444947137"));
    System.out.println(util.largestPalindromic("000099"));
  }
}
