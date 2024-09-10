package leetcode.math.easy;

public class AddBinary {

  public static void main(String[] args) {
    AddBinary util = new AddBinary();
    System.out.println(util.addBinary("11", "11"));
  }

  public String addBinary(String a, String b) {
    if (a == null && b == null) {
      return null;
    }
    if (a == null) {
      return b;
    }
    if (b == null) {
      return a;
    }
    int i = a.length() - 1;
    int j = b.length() - 1;
    String result = "";
    int carry = 0;
    while (i > -1 && j > -1) {
      int x = toDigit(a.charAt(i--));
      int y = toDigit(b.charAt(j--));
      carry = carry + x + y;
      result = (carry % 2) + result;
      carry = carry / 2;
    }

    while (i > -1) {
      int x = toDigit(a.charAt(i--));
      carry = carry + x;
      result = (carry % 2) + result;
      carry = carry / 2;
    }

    while (j > -1) {
      int y = toDigit(b.charAt(j--));
      carry = carry + y;
      result = (carry % 2) + result;
      carry = carry / 2;
    }

    return carry == 1 ? carry + result.toString() : result.toString();
  }

  private int toDigit(char x) {
    return x - '0';
  }
}
