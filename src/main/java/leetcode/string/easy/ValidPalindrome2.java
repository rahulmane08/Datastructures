package leetcode.string.easy;

public class ValidPalindrome2 {

  /**
   * acbc[d]a
   * a[d]cbca
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

  public static void main(String[] args) {
    ValidPalindrome2 util = new ValidPalindrome2();
    System.out.println(util.validPalindrome("aba"));
    System.out.println(util.validPalindrome("abxda"));
    System.out.println(util.validPalindrome("abda"));
    System.out.println(util.validPalindrome("eeccccbebaeeabebccceea"));
  }
}
