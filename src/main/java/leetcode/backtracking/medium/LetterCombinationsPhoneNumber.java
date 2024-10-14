package leetcode.backtracking.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsPhoneNumber {
  private final static Map<Character, char[]> dialPad = new HashMap<>();

  static {
    dialPad.put('2', new char[] {'a', 'b', 'c'});
    dialPad.put('3', new char[] {'d', 'e', 'f'});
    dialPad.put('4', new char[] {'g', 'h', 'i'});
    dialPad.put('5', new char[] {'j', 'k', 'l'});
    dialPad.put('6', new char[] {'m', 'n', 'o'});
    dialPad.put('7', new char[] {'p', 'q', 'r', 's'});
    dialPad.put('8', new char[] {'t', 'u', 'v'});
    dialPad.put('9', new char[] {'w', 'x', 'y', 'z'});
  }

  public static void main(String[] args) {
    LetterCombinationsPhoneNumber util = new LetterCombinationsPhoneNumber();
    System.out.println(util.letterCombinations("67"));
  }

  public List<String> letterCombinations(String digits) {
    if (digits.isEmpty()) {
      return Collections.emptyList();
    }
    List<char[]> alphabets = new ArrayList<>();
    for (char c : digits.toCharArray()) {
      alphabets.add(dialPad.get(c));
    }
    List<String> results = new ArrayList<>();
    compute(alphabets, 0, "", results, digits.length());
    return results;
  }

  void compute(List<char[]> alphabets, int index, String combination, List<String> results, int digits) {
    if (index == digits) {
      results.add(combination);
      return;
    }

    char[] current = alphabets.get(index);
    for (char c : current) {
      compute(alphabets, index + 1, combination + c, results, digits);
    }
  }
}
