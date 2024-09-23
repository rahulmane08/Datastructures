package leetcode.string.medium;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithRepeatingCharacters {
  public int lengthOfLongestSubstring(String s) {
    if (s == null || s.isEmpty()) {
      return 0;
    }
    int start = 0;
    int end = 0;
    int maxLength = 0;
    Set<Character> set = new HashSet<>();
    while (end < s.length()) {
      char right = s.charAt(end);
      if (!set.contains(right)) {
        set.add(right);
        maxLength = Math.max(maxLength, end - start + 1);
        end++;
      } else {
        while (start < end && set.contains(right)) {
          set.remove(s.charAt(start++));
        }
      }
    }
    return maxLength;
  }

  public static void main(String[] args) {
    LongestSubstringWithRepeatingCharacters util = new LongestSubstringWithRepeatingCharacters();
    System.out.println(util.lengthOfLongestSubstring("pwwkew"));
  }
}
