package leetcode.misc.medium;

/**
 * https://leetcode.com/problems/zigzag-conversion/
 */
public class StringZigZag {
  public static void main(String[] args) {
    StringZigZag s = new StringZigZag();
    System.out.println(s.convert("AB", 1));
  }

  public String convert(String s, int numRows) {
    if (s == null || s.length() == 0 || numRows == 1) {
      return s;
    }
    int n = s.length();
    String[] arr = new String[numRows];

    boolean forward = true;
    int level = -1;
    for (int i = 0; i < n; i++) {
      if (level == numRows - 1) {
        forward = false;
      } else if (level == 0) {
        forward = true;
      }

      if (forward) {
        level++;
      } else {
        level--;
      }

      if (arr[level] == null) {
        arr[level] = "";
      }
      arr[level] += s.charAt(i);
    }

    StringBuilder output = new StringBuilder();
    for (String str : arr) {
      output.append(str);
    }
    return output.toString();
  }
}
