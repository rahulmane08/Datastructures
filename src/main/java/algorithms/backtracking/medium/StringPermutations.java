package algorithms.backtracking.medium;

import leetcode.string.medium.StringUtils;

public class StringPermutations {
  public static void main(String[] args) {
    String str = "ABC";
    System.out.printf("Permutations of %s = %s%n", str, StringUtils.permute(str));
  }
}
