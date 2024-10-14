package leetcode.backtracking.medium;

/**
 * https://leetcode.com/problems/permutation-in-string/description/
 * <p>
 * This solution gives TLE, check {@link leetcode.string.medium.PermutationInString}
 */
public class PermutationInStringTLE {
  public static void main(String[] args) {
    PermutationInStringTLE util = new PermutationInStringTLE();
    System.out.println(util.checkInclusion("ab", "eidbaooo"));
    System.out.println(util.checkInclusion("ab", "eidboaoo"));
  }

  public boolean checkInclusion(String s1, String s2) {
    return check(s2, s1.toCharArray(), 0, s1.length() - 1);
  }

  boolean check(String target, char[] chars, int start, int end) {
    if (start == end && target.contains(new String(chars))) {
      return true;
    }
    for (int i = start; i <= end; i++) {
      swap(chars, i, start);
      boolean check = check(target, chars, start + 1, end);
      swap(chars, i, start);
      if (check) {
        return true;
      }
    }
    return false;
  }

  void swap(char[] chars, int i, int j) {
    if (i != j) {
      char temp = chars[i];
      chars[i] = chars[j];
      chars[j] = temp;
    }
  }
}