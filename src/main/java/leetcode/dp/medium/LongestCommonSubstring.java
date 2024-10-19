package leetcode.dp.medium;

import java.util.HashMap;
import java.util.Map;

public class LongestCommonSubstring {
  public int lengthOfLcs(String s1, String s2) {
    Map<String, Integer> dp = new HashMap<>();
    return topDown(s1, s2, 0, 0, 0, dp);
  }

  private int topDown(String s1, String s2, int index1, int index2, int length, Map<String, Integer> dp) {
    if (index1 == s1.length() || index2 == s2.length()) {
      return length;
    }
    String key = "" + index1 + index2;
    if (!dp.containsKey(key)) {
      int maxLength = 0;
      if (s1.charAt(index1) == s2.charAt(index2)) {
        maxLength = topDown(s1, s2, index1 + 1, index2 + 1, length + 1, dp);
      }
      maxLength = Math.max(maxLength,
          Math.max(topDown(s1, s2, index1 + 1, index2, length, dp),
              topDown(s1, s2, index1, index2 + 1, length, dp)));

      dp.put(key, maxLength);
    }
    return dp.get(key);
  }

  public static void main(String[] args) {
    LongestCommonSubstring lcs = new LongestCommonSubstring();
    System.out.println(lcs.lengthOfLcs("bcdx123", "cdy123"));
  }
}
