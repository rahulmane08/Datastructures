package algorithms.backtracking.medium;

import java.util.Stack;

/**
 * https://leetcode.com/problems/construct-smallest-number-from-di-string/description/
 */
public class ConstructSmallestNumberFromDIString {
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
        if (stack.isEmpty()) {
            stack.push(count);
            return;
        }
        int top = stack.pop();
        if ((pattern.charAt(index) == 'I' && top < count) || (pattern.charAt(index) == 'D' && top > count)) {
            stack.push(top);
            stack.push(count);
            return;
        }
        sortedInsert(pattern, stack, count, index - 1);
        stack.push(top);
    }

    public static void main(String[] args) {
        ConstructSmallestNumberFromDIString util = new ConstructSmallestNumberFromDIString();
        System.out.println(util.smallestNumber("IIIDIDDD"));
    }
}
