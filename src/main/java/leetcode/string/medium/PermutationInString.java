package leetcode.string.medium;

public class PermutationInString {
  public boolean checkInclusion(String s1, String s2) {

    if (s1.length() > s2.length()) {
      return false;
    }

    int[] f1 = new int[26];
    int[] f2 = new int[26];

    //"bac", "xyzabc"

    // copy "bac" to f1
    for (int i = 0; i < s1.length(); i++) {
      f1[s1.charAt(i) - 'a']++;
    }

    // copy "xy" to f2 , len(s1) - 1 = 2 number of characters from s2.
    for (int i = 0; i < s1.length() - 1; i++) {
      f2[s2.charAt(i) - 'a']++;
    }

    // f1 contains "bac"
    // f2 contains "xy"

    if (check(f1, f2)) {
      return true;
    }

    for (int i = s1.length() - 1, j = 0; i < s2.length(); i++, j++) {
      // j points to first character in the sliding window of size 3 (len(s1)) , i points to last character
      // capture next character from s2 into f2.
      f2[s2.charAt(i) - 'a']++;

      // check if the array matches now.
      if (check(f1, f2)) {
        return true;
      }

      // remove the first character from sliding window, keep searching.
      f2[s2.charAt(j) - 'a']--;
    }
    // The above loop will run as we keep capturing substrings of length of s2 from s1 until we find a match.
    return false;
  }

  public static boolean check(int[] f1, int[] f2) {
    for (int i = 0; i < 26; i++) {
      if (f1[i] != f2[i]) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    PermutationInString util = new PermutationInString();
    System.out.println(util.checkInclusion("bac", "xyzabc"));
  }
}