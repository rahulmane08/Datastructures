package leetcode.string.medium;

/**
 * https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/480/
 */
public class FirstUniqueCharacterInString {

  public static void main(String[] args) {
    FirstUniqueCharacterInString firstUniqueCharacterInString = new FirstUniqueCharacterInString();
    System.out.println(firstUniqueCharacterInString.firstUniqChar("aabbc"));
  }

  public int firstUniqChar(String s) {
    int[] arr = new int[26];
    for (char c : s.toCharArray()) {
      arr[c - 'a']++;
    }
    for (int i = 0; i < s.length(); i++) {
      if (arr[s.charAt(i) - 'a'] == 1) {
        return i;
      }
    }
    return -1;
  }
}
