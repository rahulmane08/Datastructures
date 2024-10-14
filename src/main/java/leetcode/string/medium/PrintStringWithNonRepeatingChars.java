package leetcode.string.medium;

import interfaces.Important;
import interfaces.Medium;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class PrintStringWithNonRepeatingChars {

  public static void main(String[] args) {
    PrintStringWithNonRepeatingChars util = new PrintStringWithNonRepeatingChars();
    System.out.println(util.printStringWithNonRepeatingChars("aaabc"));
    System.out.println(util.printStringWithNonRepeatingChars("aaabb"));
    System.out.println(util.printStringWithNonRepeatingChars("aa"));
    System.out.println(util.printStringWithNonRepeatingChars("aaaabc"));
  }

  /**
   * Input: aaabc
   * Output: abaca
   * <p>
   * Input: aaabb
   * Output: ababa
   * <p>
   * Input: aa
   * Output: Not Possible
   * <p>
   * Input: aaaabc
   * Output: Not Possible
   *
   * @param str
   */
  @Medium
  @Important
  public String printStringWithNonRepeatingChars(String str) {
    Map<Character, Integer> charFreq = new HashMap<>();
    for (int i = 0; i < str.length(); i++) {
      charFreq.compute(str.charAt(i), (k, v) -> v == null ? 1 : v + 1);
    }
    Comparator<Map.Entry<Character, Integer>> frequencyComparator = Comparator.comparingInt(e -> e.getValue());
    java.util.PriorityQueue<Map.Entry<Character, Integer>> maxPq =
        new java.util.PriorityQueue<>(frequencyComparator.reversed());

    // Max Heap based on number of occurences.
    charFreq.entrySet().forEach(maxPq::offer);

    String output = "";
    Map.Entry<Character, Integer> prevEntry = null;
    while (!maxPq.isEmpty()) {
      Map.Entry<Character, Integer> currEntry = maxPq.poll();
      Character curr = currEntry.getKey();
      output += curr;
      if (prevEntry != null && prevEntry.getValue() > 0) {
        maxPq.offer(prevEntry);
      }
      currEntry.setValue(currEntry.getValue() - 1);
      prevEntry = currEntry;
    }
    if (output.length() != str.length()) {
      return "Not possible";
    }
    return output;
  }
}
