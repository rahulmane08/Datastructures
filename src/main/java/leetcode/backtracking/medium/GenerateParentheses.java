package leetcode.backtracking.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/generate-parentheses/
 */
public class GenerateParentheses {
  public static void main(String[] args) {
    GenerateParentheses util = new GenerateParentheses();
    System.out.println(util.generateParenthesis(3));
  }

  public List<String> generateParenthesis(int n) {
    List<String> output = new ArrayList<>();
    generateParenthesis(n, 0, 0, "", output);
    return output;
  }

  /**
   * T(n) = 2T(n - 1) + 1 , a = 2, b = 1
   * O(n) = 2 ^ n , case 1.3 decreasing function.
   *
   * @param n
   * @param left
   * @param right
   * @param current
   * @param output
   */
  public void generateParenthesis(int n, int left, int right, String current, List<String> output) {
    if (current.length() == n * 2) {
      output.add(current);
      return;
    }
    if (left < n) {
      generateParenthesis(n, left + 1, right, current + "(", output);
    }
    if (right < left) {
      generateParenthesis(n, left, right + 1, current + ")", output);
    }
  }
}
