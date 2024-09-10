package leetcode.string.easy;

/**
 * https://leetcode.com/problems/longest-common-prefix/description/
 */
public class LongestCommonPrefix {
  public static void main(String[] args) {
    LongestCommonPrefix l = new LongestCommonPrefix();
    System.out.println(l.longestCommonPrefix(new String[] {"flower", "flow", "flight"}));
  }

  public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) {
      return "";
    }

    String lcp = "";

    int min = 201;
    for (String str : strs) {
      min = Math.min(str.length(), min);
    }

    outer:
    for (int i = 0; i < min; i++) {
      for (int j = 1; j < strs.length; j++) {
        if (strs[j - 1].charAt(i) != strs[j].charAt(i)) {
          break outer;
        }
      }
      lcp += strs[0].charAt(i);
    }
    return lcp;
  }
}