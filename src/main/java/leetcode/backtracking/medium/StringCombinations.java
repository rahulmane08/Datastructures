package leetcode.backtracking.medium;

import java.util.ArrayList;
import java.util.List;

public class StringCombinations {
  public List<String> combinations(String str) {
    List<String> combinations = new ArrayList<>();
    compute(str.toCharArray(), 0, "", combinations);
    return combinations;
  }

  void compute(char[] chars, int index, String prefix, List<String> combinations) {
    if (index == chars.length) {
      combinations.add(prefix);
      return;
    }
    // include
    compute(chars, index + 1, prefix + chars[index], combinations);
    // exclude
    compute(chars, index + 1, prefix, combinations);
  }

  public List<String> sameLengthCombinations(String str) {
    List<String> combinations = new ArrayList<>();
    computeSameLength(str.toCharArray(), 0, "", combinations);
    return combinations;
  }

  void computeSameLength(char[] chars, int index, String prefix, List<String> combinations) {
    if (index == chars.length) {
      if (prefix.length() == chars.length) {
        combinations.add(prefix);
      }
      return;
    }
    computeSameLength(chars, index + 1, prefix + chars[index], combinations);
    computeSameLength(chars, index + 1, prefix, combinations);
  }

  public static void main(String[] args) {
    StringCombinations util = new StringCombinations();
    System.out.println(util.combinations("ABC"));
    System.out.println(util.sameLengthCombinations("ABC"));
  }
}
