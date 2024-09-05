package string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static leetcode.string.medium.StringUtils.combinations;
import static leetcode.string.medium.StringUtils.lengthOfLongestPalindrome;
import static leetcode.string.medium.StringUtils.lengthOfLongestSubstringTwoDistinct;
import static leetcode.string.medium.StringUtils.lengthOfLongestSubstringWithNonRepeatingCharacters;
import static leetcode.string.medium.StringUtils.longestPalindrome;
import static leetcode.string.medium.StringUtils.minWindow;

import leetcode.string.medium.StringUtils;
import org.junit.Test;

public class TestStringUtils {

  @Test
  public void test_removeKdigits() {
    StringBuilder res = new StringBuilder();
    String num;
    int k;
    num = "1432219";
    k = 3;
    StringUtils.buildLowestNumberRec(res, num, k);
    assertEquals("1219", res.toString());

    res = new StringBuilder();
    num = "10200";
    k = 1;
    StringUtils.buildLowestNumberRec(res, num, k);
    System.out.println(res);


    res = new StringBuilder();
    num = "1431229";
    k = 4;
    StringUtils.buildLowestNumberRec(res, num, k);
    System.out.println(res);
  }

  @Test
  public void test_palindrome() {
    String str = "abba";
    assertTrue(StringUtils.isPalindrome(str));
    str = "ababa";
    assertTrue(StringUtils.isPalindrome(str));
    str = "abbab";
    assertFalse(StringUtils.isPalindrome(str));
  }

  @Test
  public void test_buildLowestNumberRec() {
    System.out.println(StringUtils.buildLowestNumberRec("4325043", 3));
  }

  @Test
  public void test_lengthOfLongestSubstringWithNonRepeatingCharacters() {
    System.out.println(lengthOfLongestSubstringWithNonRepeatingCharacters("abcabcbb"));
    System.out.println(lengthOfLongestSubstringWithNonRepeatingCharacters("abcc"));
    System.out.println(lengthOfLongestSubstringWithNonRepeatingCharacters("aab"));

  }

  @Test
  public void test_LengthOflongestPalindrome() {
    System.out.println(lengthOfLongestPalindrome("babad"));
    System.out.println(lengthOfLongestPalindrome("cbbd"));
  }

  @Test
  public void test_longestPalindrome() {
    System.out.println(longestPalindrome("babad"));
    System.out.println(longestPalindrome("cbbd"));
  }

  @Test
  public void test_combinations() {
    combinations("abcd");
  }

  @Test
  public void test_minWindow() {
    System.out.println(minWindow("ADOBECODEBANC", "ABC"));
  }

  @Test
  public void test_lengthOfLongestSubstringTwoDistinct() {
    System.out.println(lengthOfLongestSubstringTwoDistinct(("ccaabbaba")));
    System.out.println(lengthOfLongestSubstringTwoDistinct(("bacc")));
  }
}
