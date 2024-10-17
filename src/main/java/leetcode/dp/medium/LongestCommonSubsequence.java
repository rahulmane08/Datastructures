package leetcode.dp.medium;

import java.util.HashMap;
import java.util.Map;

public class LongestCommonSubsequence {
  public int lcs(String s1, String s2) {
    Map<String, Integer> dp = new HashMap<>();
    return topDown(s1, s2, 0, 0, dp);
  }

  private int topDown(String s1, String s2, int index1, int index2, Map<String, Integer> dp) {
    if (index1 == s1.length() || index2 == s2.length()) {
      return 0;
    }
    String key = "" + index1 + index2;
    if (!dp.containsKey(key)) {
      int count;
      if (s1.charAt(index1) == s2.charAt(index2)) {
        count = 1 + topDown(s1, s2, index1 + 1, index2 + 1, dp);
      } else {
        count = Math.max(topDown(s1, s2, index1 + 1, index2, dp), topDown(s1, s2, index1, index2 + 1, dp));
      }
      dp.put(key, count);
    }
    return dp.get(key);
  }

  public static void main(String[] args) {
    LongestCommonSubsequence lcs = new LongestCommonSubsequence();
    System.out.println(lcs.lcs("bcdx123", "cd"));
  }
}
