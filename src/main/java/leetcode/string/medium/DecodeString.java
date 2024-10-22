package leetcode.string.medium;


import java.util.Stack;

public class DecodeString {
  public String decodeString(String s) {
    Stack<String> stack = new Stack<>();
    for (char c : s.toCharArray()) {
      if (c == ']') {
        String stringToRepeat = "";
        while (!stack.peek().equals("[")) {
          stringToRepeat = stack.pop() + stringToRepeat;
        }
        stack.pop(); // remove '['
        int timesToRepeat = Integer.parseInt(stack.pop());
        StringBuilder repeatedString = new StringBuilder();
        for (int i = 0; i < timesToRepeat; i++) {
          repeatedString.append(stringToRepeat);
        }
        stack.push(repeatedString.toString());
      } else if (Character.isDigit(c)) {
        if (stack.isEmpty() || !Character.isDigit(stack.peek().charAt(0))) {
          stack.push(String.valueOf(c));
        } else {
          stack.push(stack.pop() + c);
        }
      } else {
        stack.push(String.valueOf(c));
      }
    }
    String decodedString = "";
    while (!stack.isEmpty()) {
      decodedString = stack.pop() + decodedString;
    }
    return decodedString;
  }

  public static void main(String[] args) {
    DecodeString util = new DecodeString();
    System.out.println(util.decodeString("3[a]bc"));
    System.out.println(util.decodeString("3[a2[x]]bc"));
    System.out.println(util.decodeString("p3[a2[xy]]bc"));
  }
}
