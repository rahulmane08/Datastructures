package algorithms.backtracking.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/letter-case-permutation/
 */
public class LetterCasePermutation {
  public static void main(String[] args) {
    LetterCasePermutation util = new LetterCasePermutation();
    System.out.println(util.letterCasePermutation("3z4c"));
  }

  public List<String> letterCasePermutation(String s) {
    List<String> output = new ArrayList<>();
    find(s, output, "", 0);
    return output;
  }

  private void find(String s, List<String> output, String prefix, int index) {
    if (index == s.length()) {
      output.add(prefix);
      return;
    }
    char currentChar = s.charAt(index);
    find(s, output, prefix + currentChar, index + 1);
    if (Character.isAlphabetic(currentChar)) {
      char transformed = Character.isLowerCase(currentChar) ?
          Character.toUpperCase(currentChar) : Character.toLowerCase(currentChar);
      find(s, output, prefix + transformed, index + 1);
    }
  }
}
