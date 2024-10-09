package leetcode.string.medium;

import java.util.HashMap;
import java.util.Map;

public class PermutationInString {

  /**
   * Example 1:
   * <p>
   * Input: s1 = "ab", s2 = "eidbaooo"
   * Output: true
   * Explanation: s2 contains one permutation of s1 ("ba").
   * Example 2:
   * <p>
   * Input: s1 = "ab", s2 = "eidboaoo"
   * Output: false
   *
   * @param f1
   * @param f2
   * @return s1 = abc , s2 = abde
   */
  public boolean checkInclusion1(String s1, String s2) {
    Map<Character, Integer> checker = new HashMap<>();
    for (char c : s1.toCharArray()) {
      checker.put(c, 0);
    }

    int M = s1.length();
    int i = 0;
    while (i < s2.length()) {
      int windowStart = i - M + 1;
      if (windowStart > 0) {
        char charLeavingWindow = s2.charAt(windowStart - 1);
        adjustFrequencies(checker, charLeavingWindow, false);
      }

      char charEnteringWindow = s2.charAt(i++);
      int windowLength = adjustFrequencies(checker, charEnteringWindow, true);
      if (windowLength == s1.length()) {
        return true;
      }
    }
    return false;
  }

  private int adjustFrequencies(Map<Character, Integer> checker, char c, boolean increment) {
    if (checker.containsKey(c)) {
      checker.compute(c, (x, freq) -> {
        if (increment) {
          freq++;
        } else if (freq != 0) {
          freq--;
        }
        return freq;
      });
    }
    return (int) checker.entrySet().stream().filter(e -> e.getValue() != 0).count();
  }

  public static void main(String[] args) {
    PermutationInString util = new PermutationInString();
    System.out.println(util.checkInclusion1("adc", "adxcda"));
  }
}