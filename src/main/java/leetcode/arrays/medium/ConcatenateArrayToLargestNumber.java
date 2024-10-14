package leetcode.arrays.medium;

import java.util.Arrays;
import java.util.Comparator;

public class ConcatenateArrayToLargestNumber {
  public static void main(String[] args) {
    Integer[] nums = new Integer[] {50, 0, 5};
    ConcatenateArrayToLargestNumber util = new ConcatenateArrayToLargestNumber();
    util.concatenateToLargestNumber(nums);
    System.out.println(Arrays.toString(nums));
  }

  void concatenateToLargestNumber(Integer[] nums) {
    Arrays.sort(nums, (a, b) -> {
      String x = "" + a + b;
      String y = "" + b + a;
      Comparator<String> comparator = Comparator.reverseOrder();
      return comparator.compare(x, y);
    });
  }
}
