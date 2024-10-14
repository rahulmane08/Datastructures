package leetcode.backtracking.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/letter-case-permutation/
 * <p>
 * "3z4c"
 * "3Z4C"
 * "3Z4c"
 * <p>
 * T(n) = 2T(n - 1) + 1 , a = 2, b = 1
 * O(n) = 2 ^ n , case 1.3 decreasing function.
 */
public class LetterCasePermutation {
  public static void main(String[] args) {
    LetterCasePermutation util = new LetterCasePermutation();
    System.out.println(util.letterCasePermutation("3z4c"));
  }

  public List<String> letterCasePermutation(String s) {
    List<String> results = new ArrayList<>();
    compute(s.toCharArray(), 0, results);
    return results;
  }

  void compute(char[] chars, int index, List<String> result) {
    if (index == chars.length) {
      result.add(new String(chars));
      return;
    }
    // only for alphabets do backtracking + combinations
    if (Character.isAlphabetic(chars[index])) {
      chars[index] = changeCase(chars[index]);
      compute(chars, index + 1, result);
      chars[index] = changeCase(chars[index]);
    }
    compute(chars, index + 1, result);
  }

  char changeCase(char c) {
    return Character.isLowerCase(c) ? Character.toUpperCase(c) : Character.toLowerCase(c);
  }
}
