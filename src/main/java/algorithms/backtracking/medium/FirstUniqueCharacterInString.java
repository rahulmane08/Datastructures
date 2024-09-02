package algorithms.backtracking.medium;

/**
 * https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/480/
 */
public class FirstUniqueCharacterInString {

  public static void main(String[] args) {
    FirstUniqueCharacterInString firstUniqueCharacterInString = new FirstUniqueCharacterInString();
    System.out.println(firstUniqueCharacterInString.firstUniqChar("aabb"));
  }

  public int firstUniqChar(String s) {
    int[] arr = new int[25];
    for (char c : s.toCharArray()) {
      int index = (c - 97);
      arr[index]++;
    }
    for (int i = 0; i < s.length(); i++) {
      int index = (s.charAt(i) - 97);
      if (arr[index] == 1) {
        return i;
      }
    }
    return -1;
  }
}
