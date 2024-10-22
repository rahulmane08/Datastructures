package leetcode.stack.medium;


import java.util.Stack;

public class MinSwapsToMakeStringBalanced {
  public int minSwaps(String s) {
    Stack<Character> stack = new Stack<>();
    for (char brace : s.toCharArray()) {
      if (!stack.isEmpty() && stack.peek() == '[' && brace == ']') {
        stack.pop();
      } else {
        stack.push(brace);
      }
    }
    int n = stack.size() / 2;
    return n % 2 == 0 ? n / 2 : (n/2) + 1;
  }

  public static void main(String[] args) {
    MinSwapsToMakeStringBalanced util = new MinSwapsToMakeStringBalanced();
    System.out.println(util.minSwaps("][]["));
    System.out.println(util.minSwaps("]]][[["));
    System.out.println(util.minSwaps("][][]["));
    System.out.println(util.minSwaps("[]"));
  }
}
