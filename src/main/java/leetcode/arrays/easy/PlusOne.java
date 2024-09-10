package leetcode.arrays.easy;

public class PlusOne {
  public int[] plusOne(int[] digits) {
    if (digits == null || digits.length == 0) {
      return digits;
    }
    int carry = 1;
    int n = digits.length;
    for (int i = n - 1; i >= 0; i--) {
      int result = digits[i] + carry;
      digits[i] = result % 10;
      carry = (int) (result / 10);
      if (carry == 0) {
        break;
      }
    }
    if (carry == 1) {
      int[] result = new int[n + 1];
      result[0] = 1;
      for (int i = 1; i < n; i++) {
        result[i] = digits[i - 1];
      }
      return result;
    }
    return digits;
  }
}
