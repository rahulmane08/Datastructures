package leetcode.math.medium;

/**
 * https://leetcode.com/problems/string-to-integer-atoi/description/
 */
public class Atoi {

  /**
   * Lets suppose the max value of an integer is 333
   * maxValue = 33
   * maxLsd = 3
   * <p>
   * so if the input is 421
   * i = 0, curr = 4, result = 4
   * i = 1, curr = 2, result = 42 > maxValue , fail
   *
   * @param s
   * @return
   */
  public int myAtoi(String s) {
    int n = s.length();
    int sign = 1;
    int result = 0;
    int i = 0;
    for (; i < n && s.charAt(i) == ' '; i++) ; // skip

    if (i < n && s.charAt(i) == '+') {
      sign = 1;
      i++;
    } else if (i < n && s.charAt(i) == '-') {
      sign = -1;
      i++;
    }

    int maxValue = Integer.MAX_VALUE / 10;
    int maxLSD = Integer.MAX_VALUE % 10;

    for (; i < n && Character.isDigit(s.charAt(i)); i++) {
      int digit = s.charAt(i) - '0';

      if (result > maxValue || (result == maxValue && digit > maxLSD)) {
        return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE; // or throw NumberFormatException.
      }
      result = 10 * result + digit;
    }

    return sign * result;
  }

  public static void main(String[] args) {
    Atoi atoi = new Atoi();
    System.out.println(atoi.myAtoi("123"));
    System.out.println(atoi.myAtoi(Integer.MAX_VALUE + "2"));
    System.out.println(Integer.parseInt(Integer.MAX_VALUE + "2"));
  }
}
