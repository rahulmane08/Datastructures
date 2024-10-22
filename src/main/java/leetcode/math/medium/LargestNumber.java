package leetcode.math.medium;

import java.util.Arrays;
import java.util.Comparator;

public class LargestNumber {
  public String largestNumber(int[] nums) {
    Integer[] integers = Arrays.stream(nums).boxed().toArray(Integer[]::new);
    Comparator<Integer> tComparator = (a, b) -> {
      String num1 = "" + a + b;
      String num2 = "" + b + a;
      return num2.compareTo(num1);
    };
    Arrays.sort(integers, tComparator);
    if (integers[0] == 0) {
      return "0";
    }
    StringBuilder output = new StringBuilder();
    for (int i : integers) {
      output.append(i);
    }
    return output.toString();
  }

  public static void main(String[] args) {
    LargestNumber util = new LargestNumber();
    System.out.println(util.largestNumber(new int[] {10, 2}));
    System.out.println(util.largestNumber(new int[] {3, 30, 34, 5, 9}));
  }
}
