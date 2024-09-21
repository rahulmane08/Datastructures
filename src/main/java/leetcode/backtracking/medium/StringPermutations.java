package leetcode.backtracking.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringPermutations {
  public static void main(String[] args) {
    String str = "AAC";
    System.out.printf("Permutations of %s = %s%n", str, new StringPermutations().permute(str));
  }

  public List<String> permute(String str) {
    List<String> permutations = new ArrayList<>();
    compute(str.toCharArray(), 0, str.length() - 1, permutations);
    return permutations;
  }

  void compute(char[] chars, int fixed, int end, List<String> permutations) {
    if (fixed == end) {
      permutations.add(Arrays.toString(chars));
      System.out.println(permutations);
      return;
    }
    for (int i = fixed; i <= end; i++) {
      swap(chars, i, fixed);
      compute(chars, fixed + 1, end, permutations);
      swap(chars, i, fixed);
    }
  }

  void swap(char[] chars, int i, int j) {
    if (i != j) {
      char temp = chars[i];
      chars[i] = chars[j];
      chars[j] = temp;
    }
  }
}
