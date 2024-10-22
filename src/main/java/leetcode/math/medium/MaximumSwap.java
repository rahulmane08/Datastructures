package leetcode.math.medium;

public class MaximumSwap {
  public int maximumSwap(int num) {
    if (num / 10 == 0) {
      return num;
    }
    String str = String.valueOf(num);
    char[] chars = str.toCharArray();
    int max = 1;
    for (int i = 1 ; i < chars.length; i++) {
      if (chars[max] < chars[i]) {
        max = i;
      }
    }
    if (chars[max] > chars[0]) {
      char temp = chars[0];
      chars[0] = chars[max];
      chars[max] = temp;
    }
    return Integer.parseInt(new String(chars));
  }

  public static void main(String[] args) {
    MaximumSwap util = new MaximumSwap();
    System.out.println(util.maximumSwap(198));
  }
}
