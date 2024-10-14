package leetcode.stack.medium;

import java.util.Stack;

public class DICode {
  public static void main(String[] args) {
    DICode util = new DICode();
    System.out.println(util.smallestNumber("ID"));
    System.out.println(util.smallestNumber("IDD"));
  }

  public String smallestNumber(String pattern) {
    if (pattern == null || pattern.isEmpty()) {
      return pattern;
    }

    Stack<Integer> stack = new Stack<>();
    int count = 1;
    stack.push(count++);
    for (int i = 0; i < pattern.length(); i++) {
      sortedInsert(pattern, stack, count++, i);
    }

    // ouput
    StringBuilder output = new StringBuilder();
    while (!stack.isEmpty()) {
      output.append(stack.pop());
    }
    return output.reverse().toString();
  }

  private void sortedInsert(String pattern, Stack<Integer> stack, int count, int index) {
    if (stack.isEmpty() ||
        (pattern.charAt(index) == 'I' && stack.peek() < count) ||
        (pattern.charAt(index) == 'D' && stack.peek() > count)) {
      stack.push(count);
      return;
    }
    int top = stack.pop();
    sortedInsert(pattern, stack, count, index - 1);
    stack.push(top);
  }
}
