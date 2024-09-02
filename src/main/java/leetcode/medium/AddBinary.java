package leetcode.medium;

public class AddBinary {

  public static void main(String[] args) {
    System.out.println(addBinary("11", "1"));
  }

  public static String addBinary(String a, String b) {
    if (a == null && b == null) {
      return null;
    }
    if (a == null) {
      return b;
    }
    if (b == null) {
      return a;
    }
    int n1 = a.length();
    int n2 = b.length();
    StringBuilder result = new StringBuilder();
    char carry = '0';
    int i = n1 - 1, j = n2 - 1;
    while (i > -1 && j > -1) {
      char x = a.charAt(i);
      char y = b.charAt(j);
      carry = addToResult(result, add("" + carry + x + y));
      i--;
      j--;
    }

    while (i > -1) {
      char x = a.charAt(i);
      carry = addToResult(result, add("" + carry + x + "0"));
      i--;
    }

    while (j > -1) {
      char y = b.charAt(j);
      carry = addToResult(result, add("" + carry + "0" + y));
      j--;
    }

    if (carry == '1') {
      return carry + result.toString();
    }
    return result.toString();
  }

  private static char addToResult(StringBuilder result, String res) {
    if (res.length() == 2) {
      result.append(res.charAt(1));
      return res.charAt(0);
    } else {
      result.append(res.charAt(0));
      return '0';
    }
  }

  private static String add(String expression) {
    int count1s = 0;
    for (int i = 0; i < expression.length(); i++) {
      if (expression.charAt(i) == '1') {
        count1s++;
      }
    }
    if (count1s == 2) {
      return "10";
    } else if (count1s == 1) {
      return "01";
    } else {
      return "00";
    }
  }
}
