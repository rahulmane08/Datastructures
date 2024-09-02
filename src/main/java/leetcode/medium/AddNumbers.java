package leetcode.medium;

import java.util.Scanner;

public class AddNumbers {

  public static String addNumbers(String n1, String n2) {
    if (n1 == null && n2 == null) {
      return null;
    }

    if (n1 == null) {
      return n2;
    }

    if (n2 == null) {
      return n1;
    }

    int carry = 0;
    StringBuilder result = new StringBuilder();

    int i = n1.length() - 1;
    int j = n2.length() - 1;
    while (i > -1 && j > -1) {
      char x = n1.charAt(i--);
      char y = n2.charAt(j--);
      carry = add(x, y, carry, result);
    }

    while (i > -1) {
      char x = n1.charAt(i--);
      carry = add(x, '0', carry, result);
    }

    while (j > -1) {
      char y = n2.charAt(j--);
      carry = add('0', y, carry, result);
    }
    result.reverse();
    if (carry == 1) {
      return "1" + result;
    }
    return result.toString();
  }

  private static int add(char x, char y, int carry, StringBuilder result) {
    if (x == ',' || y == ',') {
      result.append(',');
      return 0;
    }
    int res = Integer.parseInt(String.valueOf(x)) + Integer.parseInt(String.valueOf(y)) + carry;
    result.append(res % 10);
    return res / 10;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String a;
    a = in.next();
    String b;
    b = in.next();

    System.out.println(addNumbers("1,123", "99"));
  }
}
