package leetcode.greedy.medium;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Example 1:
 * <p>
 * Input: num = "444947137"
 * Output: "7449447"
 * Explanation:
 * Use the digits "4449477" from "444947137" to form the palindromic integer "7449447".
 * It can be shown that "7449447" is the largest palindromic integer that can be formed.
 * Example 2:
 * <p>
 * Input: num = "00009"
 * Output: "9"
 * Explanation:
 * It can be shown that "9" is the largest palindromic integer that can be formed.
 * Note that the integer returned should not contain leading zeroes.
 */
public class LongestPalindromicNumber {
  public static void main(String[] args) {
    LongestPalindromicNumber util = new LongestPalindromicNumber();
    System.out.println(util.largestPalindromic("444947137"));
    System.out.println(util.largestPalindromic("000099"));
  }

  public String largestPalindromic(String num) {
    Map<Character, Integer> frequencies = new HashMap<>(); //S(n)
    int i = 0;
    for (; i < num.length() - 1 && num.charAt(i) == '0'; i++) ; // O(n)

    for (; i < num.length(); i++) {
      frequencies.compute(num.charAt(i), (digit, freq) -> freq == null ? 1 : freq + 1); // O(n)
    }
    String left = "";
    String right = "";
    String middle = "";
    PriorityQueue<Character> singleElementsQ = new PriorityQueue<>(Comparator.reverseOrder());
    PriorityQueue<int[]> multiElementsQ = new PriorityQueue<>(Comparator.comparingInt((int[] a) -> a[0]).reversed());
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
}
