package leetcode.string.medium;

import interfaces.Important;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StringUtils {

  public static String buildLowestNumberRec(String str, int n) {
    StringBuilder res = new StringBuilder();
    buildLowestNumberRec(res, str, n);
    return res.toString();
  }

  public static void buildLowestNumberRec(StringBuilder res, String str, int n) {
    // If there are 0 characters to remove from str,
    // append everything to result
    if (n == 0) {
      res.append(str);
      return;
    }

    int len = str.length();

    // If there are more characters to
    // remove than string length,
    // then append nothing to result
    if (len <= n) {
      return;
    }

    // Find the smallest character among
    // first (n+1) characters of str.
    int minIndex = 0;
    for (int i = 1; i <= n; i++) {
      if (str.charAt(i) < str.charAt(minIndex)) {
        minIndex = i;
      }
    }

    // Append the smallest character to result
    res.append(str.charAt(minIndex));

    // substring starting from
    // minIndex+1 to str.length() - 1.
    String new_str = str.substring(minIndex + 1);

    // Recur for the above substring
    // and n equals to n-minIndex
    buildLowestNumberRec(res, new_str, n - minIndex);
  }

  public static boolean isPalindrome(String str) {
    if (str == null || str.isEmpty()) {
      return false;
    }
    char[] chars = str.toCharArray();
    int i = 0, j = str.length() - 1;
    while (i < j) {
      if (chars[i++] != chars[j--]) {
        return false;
      }
    }
    return true;
  }

  public static int compare(String str1, String str2) {
    return compare(str1, str2, str1.length(), str2.length(), 0);
  }

  private static int compare(String str1, String str2, int size1, int size2, int index) {
    if (str1 == null && str2 == null) {
      return 0;
    }
    return -1;
  }

  public static List<String> permute(String str) {
    if (str == null || str.length() == 0) {
      return Collections.emptyList();
    }
    List<String> permutations = new ArrayList<>();
    permuteUtil(str.toCharArray(), 0, str.length() - 1, permutations);
    return permutations;
  }

  /**
   * ABC
   * [A]                 [B]                 [C]
   * [AB]  [AC]           [BA] [BC]          [CB]  [CA]
   * [ABC]     [ACB]      [BAC]    [BCA]     [CBA]     [CAB]
   *
   * @param result
   * @param charArray
   * @param start
   * @param end
   */
  private static void permuteUtil(char[] charArray, int start, int end, List<String> result) {
    if (start == end) {
      result.add(new String(charArray));
    }
    for (int i = start; i <= end; i++) {
      swap(charArray, start, i);
      permuteUtil(charArray, start + 1, end, result);
      swap(charArray, start, i);
    }
  }

  public static void combinations(String str) {
    combinations(str, 0, "");
  }

  private static void combinations(String str, int start, String prefix) {
    if (start == str.length()) {
      if (!prefix.isEmpty()) {
        System.out.println(prefix);
      }
      return;
    }
    combinations(str, start + 1, prefix + str.charAt(start));
    combinations(str, start + 1, prefix);
  }

  public static void swap(char[] charArray, int i, int j) {
    if (i == j) {
      return;
    }
    char temp = charArray[i];
    charArray[i] = charArray[j];
    charArray[j] = temp;
  }

  /**
   * s = abcabcd , ans = 3 (abc)
   *
   * @param s
   * @return
   */
  @Important
  public static int lengthOfLongestSubstringWithNonRepeatingCharacters(String s) {
    if (s == null) {
      return 0;
    }
    int count = 0;
    int start = 0;
    Set<Character> visited = new HashSet<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (visited.contains(c)) {
        do {
          visited.remove(s.charAt(start++));
        } while (visited.contains(c));
      }
      visited.add(c);
      count = Math.max(count, i - start + 1);
    }
    return count;
  }

  public static int lengthOfLongestPalindrome(String s) {
    int maxLength = 0;
    for (int i = 0; i < s.length(); i++) {
      int even = countSameForwards(s, i) + 1;
      int odd = 2 * countSameBothWays(s, i - 1, i + 1) + 1;
      int current = Math.max(even, odd);
      maxLength = Math.max(maxLength, current);
    }
    return maxLength;
  }

  private static int countSameForwards(String s, int index) {
    int count = 0;
    for (; index < s.length() - 1 && s.charAt(index) == s.charAt(index + 1); index++, count++) ;
    return count;
  }

  private static int countSameBothWays(String s, int left, int right) {
    int count = 0;
    for (; left >= 0 && right < s.length() && s.charAt(left--) == s.charAt(right++); count++) ;
    return count;
  }

  public static String longestPalindrome(String s) {
    String palindrome = "";
    for (int i = 0; i < s.length(); i++) {
      int index = i;
      for (; index < s.length() - 1 && s.charAt(index) == s.charAt(index + 1); index++) ;
      String forwards = s.substring(i, index - i);

      String bothways = String.valueOf(s.charAt(i));
      for (int left = i, right = i;
           left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right); left--, right++) {
        bothways = s.charAt(left) + bothways + s.charAt(right);
      }

      String current = "";
      if (forwards.length() > bothways.length()) {
        current = forwards;
      } else {
        current = bothways;
      }

      if (current.length() > palindrome.length()) {
        palindrome = current;
      }
    }
    return palindrome;
  }

  public static String minWindow(String s, String t) {
    int n = s.length();
    HashSet<Character> characters = new HashSet<>();
    Map<Character, Integer> map = new HashMap<>();
    int minCount = n + 1;
    String minSubstr = "";

    for (int i = 0; i < t.length(); i++) {
      characters.add(t.charAt(i));
    }

    for (int i = 0, start = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (!characters.contains(c)) {
        continue;
      }

      map.compute(c, (k, v) -> v == null ? 1 : v + 1);
      if (map.size() == characters.size()) {
        // keep moving start ahead until the t remains totally matched.

        int freq = 0;
        for (char startChar = s.charAt(start); (freq = map.getOrDefault(startChar, 0)) != 1; ) {
          if (freq > 1) {
            map.put(startChar, freq - 1);
          }
          startChar = s.charAt(++start);
        }

        if (minCount > i - start + 1) {
          minCount = i - start + 1;
          minSubstr = s.substring(start, start + minCount);
          // there cant be a string minimum that t.
          if (minCount == t.length()) {
            return minSubstr;
          }
        }

        // make it unmatched again.
        map.remove(s.charAt(start++));
      }
    }

    if (minCount != n + 1) {
      return minSubstr;
    }
    return null;
  }

  public static int lengthOfLongestSubstringTwoDistinct(String s) {
    if (s == null || s.isEmpty()) {
      return 0;
    }

    int n = s.length();
    int maxCount = 0;
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0, start = 0; i < n; i++) {
      char c = s.charAt(i);
      if (map.size() < 2 || (map.size() == 2 && map.containsKey(c))) {
        map.compute(c, (k, v) -> v == null ? 1 : v + 1);
      } else {
        maxCount = Math.max(maxCount, i - start);
        do {
          char startChar = s.charAt(start++);
          int count = map.get(startChar);
          if (count == 1) {
            map.remove(startChar);
          } else {
            map.compute(startChar, (k, v) -> v - 1);
          }
        } while (map.size() != 1);
        map.put(c, 1);
      }
    }
    maxCount = Math.max(maxCount, map.values().stream().reduce(0, Integer::sum));
    return maxCount;
  }

  public List<String> findAmbigramicStrings(List<String> inputs,
                                            Map<Character, Character> ambigramicEquivalents) {
    if (inputs == null || inputs.size() == 0) {
      return null;
    }

    List<String> results = new ArrayList<>();
    for (String str : inputs) {
      String result = "";
      int n = str.length();
      for (int i = 0; i < n; i++) {
        if (ambigramicEquivalents.containsKey(str.charAt(i))) {
          result += ambigramicEquivalents.get(str.charAt(i));
        } else {
          break;
        }
      }
      if (result.length() == n) {
        boolean isAmbigram = true;
        for (int i = 0; i < n; i++) {
          if (str.charAt(i) != result.charAt(n - 1 - i)) {
            isAmbigram = false;
            break;
          }
        }
        if (isAmbigram) {
          results.add(str);
        }
      }
    }
    return results;
  }
}
