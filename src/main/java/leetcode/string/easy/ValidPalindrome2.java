package leetcode.string.easy;

public class ValidPalindrome2 {

  /**
   * acbc[d]a
   * a[d]cbca
   *
   * @param s
   * @return
   */
  public boolean validPalindrome(String s) {
    int start = 0;
    int end = s.length() - 1;
    boolean deleted = false;
    while (start < end) {
      if (s.charAt(start) != s.charAt(end)) {
        if (deleted) {
          return false;
        }
        if (s.charAt(start + 1) == s.charAt(end)) {
          deleted = true;
          start++;
        } else if (s.charAt(start) == s.charAt(end - 1)) {
          deleted = true;
          end--;
        } else {
          return false;
        }
      } else {
        start++;
        end--;
      }
    }
    return true;
  }

  /**
   * Example 1:
   * <p>
   * Input: s = "aba"
   * Output: true
   * Example 2:
   * <p>
   * Input: s = "abca"
   * Output: true
   * Explanation: You could delete the character 'c'.
   * Example 3:
   * <p>
   * Input: s = "abc"
   * Output: false
   *
   *
   * @param s
   * @return
   */
  public boolean validPalindrome1(String s) {
    int start = 0;
    int end = s.length() - 1;
    boolean deleted = false;
    while (start < end) {
      if (s.charAt(start) != s.charAt(end)) {
        if (deleted) {
          return false;
        } else {
          start++;
          deleted = true;
        }
      } else {
        start++;
        end--;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    ValidPalindrome2 util = new ValidPalindrome2();
    System.out.println(util.validPalindrome1("aba"));
    System.out.println(util.validPalindrome1("abxda"));
    System.out.println(util.validPalindrome1("abda"));
    System.out.println(util.validPalindrome1("eeccccbebaeeabebccceea"));
  }
}
