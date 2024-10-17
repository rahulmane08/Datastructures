package leetcode.dp.medium;

import java.util.HashMap;
import java.util.Map;

public class LongestCommonSubsequence2 {
  public String lcs(String s1, String s2) {
    Map<String, String> dp = new HashMap<>();
    return topDown(s1, s2, 0, 0, dp);
  }

  private String topDown(String s1, String s2, int index1, int index2, Map<String, String> dp) {
    if (index1 == s1.length() || index2 == s2.length()) {
      return "";
    }
    String key = "" + index1 + index2;
    if (!dp.containsKey(key)) {
      String lcs;
      if (s1.charAt(index1) == s2.charAt(index2)) {
        lcs = s1.charAt(index1) + topDown(s1, s2, index1 + 1, index2 + 1, dp);
      } else {
        String leftLcs = topDown(s1, s2, index1 + 1, index2, dp);
        String rightLcs = topDown(s1, s2, index1, index2 + 1, dp);
        lcs = leftLcs.length() > rightLcs.length() ? leftLcs : rightLcs;
      }
      dp.put(key, lcs);
    }
    return dp.get(key);
  }

  public static void main(String[] args) {
    LongestCommonSubsequence2 lcs = new LongestCommonSubsequence2();
    System.out.println(lcs.lcs("bcdx123", "cdy123"));
  }
}
