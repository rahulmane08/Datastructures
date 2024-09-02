package datastructures.stack;

import interfaces.Hard;
import interfaces.Important;
import interfaces.Medium;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * Infix -> PostFix
 * Prefix -> Infix
 * Prefix -> Postfix
 * Postfix -> Infix
 * Postfix -> Prefix
 */
public class ExpressionUtils {
  private static final List<Character> operators = Arrays.asList('/', '-', '+', '*');
  private static final List<Character> parenthesis = Arrays.asList('(', ')', '{', '}', '[', ']');
  static HashMap<String, Integer> priortyMap = new HashMap<>();
  static HashMap<Character, Character> parenthesisPairs = new HashMap<>();

  static {
    ExpressionUtils.priortyMap.put("+", 1);
    ExpressionUtils.priortyMap.put("-", 1);
    ExpressionUtils.priortyMap.put("/", 2);
    ExpressionUtils.priortyMap.put("*", 2);
    ExpressionUtils.priortyMap.put("(", 1);
    ExpressionUtils.priortyMap.put(")", 1);

    ExpressionUtils.parenthesisPairs.put(')', '(');
    ExpressionUtils.parenthesisPairs.put('}', '{');
    ExpressionUtils.parenthesisPairs.put(']', '[');
  }

  private static int checkPriority(String operator1, String operator2) {
    return (priortyMap.get(operator1)).compareTo(priortyMap.get(operator2));
  }

  private static int checkPriority(char operator1, char operator2) {
    return checkPriority(String.valueOf(operator1), String.valueOf(operator2));
  }

  private static boolean isOperand(char operand) {
    return !priortyMap.containsKey(String.valueOf(operand));
  }

  private static boolean isParenthesisPairs(char p1, char p2) {
    return ((parenthesisPairs.get(p1).equals(p2)) || (parenthesisPairs.get(p2).equals(p1)));
  }

  @Important
  @Medium
  public static String infixToPostfix(String infix) {
    if (infix == null || infix.length() == 0) {
      return null;
    }

    Stack<Character> stack = new Stack<>();
    String postfix = "";
    for (int i = 0; i < infix.length(); i++) {
      char ch = infix.charAt(i);
      if (isOperand(ch)) {
        postfix += ch;
      } else if (ch == ')') {
        for (char top = stack.pop(); top != '('; ) {
          postfix += top;
          top = stack.pop();
        }
      } else {
        // if there are higher or equal priority operators already in stack, add those to postfix
        while (!stack.isEmpty() && stack.peek() != '(' && checkPriority(stack.peek(), ch) >= 0) {
          postfix += stack.pop();
        }
        stack.push(ch);
      }
    }
    while (!stack.isEmpty()) {
      postfix += stack.pop();
    }
    return postfix;
  }

  /**
   * - read the expression
   * - if operand push to stack
   * - if operator , do two pops, compute op1 + operator + op2, push the result in stack
   * - continue until expression is over
   * - finally there will be only one element in stack that will be the result
   *
   * @param exp
   * @return
   */
  static public double evaluatePostfixExpression(String exp) {
    if (exp == null) {
      return 0;
    }
    Stack<Double> s = new Stack<>();
    for (int i = 0; i < exp.length(); i++) {
      char t = exp.charAt(i);
      if (isOperand(t)) {
        s.push(Double.parseDouble(String.valueOf(t)));
      } else {
        double o1 = s.pop();
        double o2 = s.pop();
        double result = compute(o2, o1, t);
        s.push(result);
      }
    }
    return s.pop();
  }

  static private double compute(double a, double b, char operator) {
    double result = 0;
    switch (operator) {
      case '+':
        result = a + b;
        break;
      case '-':
        result = a - b;
        break;
      case '*':
        result = a * b;
        break;
      case '/':
        result = a / b;
        break;
      default:
        break;
    }
    return result;
  }

  /**
   * - read the prefix string reverse
   * - if char is operand then push to stack
   * - if its operator then
   * op1 = s.pop
   * op2 = s.pop
   * exp = (op1 + operator + op2)
   * push exp to stack
   * - finally there will be only one element in stack that will be the infix expression
   *
   * @param prefixExp
   * @return
   */
  static public String prefixToInfix(String prefixExp) {
    if (prefixExp == null) {
      return null;
    }
    Stack<String> stack = new Stack<>();
    for (int i = prefixExp.length() - 1; i >= 0; i--) {
      char ch = prefixExp.charAt(i);
      if (isOperand(ch)) {
        stack.push(String.valueOf(ch));
      } else {
        String op1 = stack.pop();
        String op2 = stack.pop();
        String exp = String.format("(%s%s%s)", op1, ch, op2);
        stack.push(exp);
      }
    }
    return stack.pop();
  }

  /**
   * - read the prefix string reverse
   * - if char is operand then push to stack
   * - if its operator then
   * op1 = s.pop
   * op2 = s.pop
   * exp = op1 + op2 + operator
   * push exp to stack
   * - finally there will be only one element in stack that will be the infix expression
   *
   * @return
   */
  static public String prefixToPostfix(String prefixExp) {
    if (prefixExp == null) {
      return null;
    }
    Stack<String> stack = new Stack<>();
    for (int i = prefixExp.length() - 1; i >= 0; i--) {
      char ch = prefixExp.charAt(i);
      if (isOperand(ch)) {
        stack.push(String.valueOf(ch));
      } else {
        String op1 = stack.pop();
        String op2 = stack.pop();
        String exp = String.format("%s%s%s", op1, op2, ch);
        stack.push(exp);
      }
    }
    return stack.pop();
  }

  /**
   * - read the postfix string
   * - if char is operand then push to stack
   * - if its operator then
   * op1 = s.pop
   * op2 = s.pop
   * exp = (op2 + operator + op1)
   * push exp to stack
   * - finally there will be only one element in stack that will be the infix expression
   *
   * @param postfixExp
   * @return
   */
  static public String postfixToInfix(String postfixExp) {
    if (postfixExp == null) {
      return null;
    }
    Stack<String> stack = new Stack<>();
    for (int i = 0; i < postfixExp.length(); i--) {
      char ch = postfixExp.charAt(i);
      if (isOperand(ch)) {
        stack.push(String.valueOf(ch));
      } else {
        String op1 = stack.pop();
        String op2 = stack.pop();
        String exp = String.format("(%s%s%s)", op2, ch, op1);
        stack.push(exp);
      }
    }
    return stack.pop();
  }

  /**
   * - read the prefix string reverse
   * - if char is operand then push to stack
   * - if its operator then
   * op1 = s.pop
   * op2 = s.pop
   * exp = operator + op2 + op1
   * push exp to stack
   * - finally there will be only one element in stack that will be the infix expression
   *
   * @return
   */
  static public String postfixToPrefix(String postfix) {
    if (postfix == null) {
      return null;
    }
    Stack<String> stack = new Stack<>();
    for (char ch : postfix.toCharArray()) {
      if (isOperand(ch)) {
        stack.push(String.valueOf(ch));
      } else {
        String op1 = stack.pop();
        String op2 = stack.pop();
        String exp = String.format("%s%s%s", ch, op2, op1);
        stack.push(exp);
      }
    }
    return stack.pop();
  }

  /**
   * read the strings by character
   * if char is a parenthesis only
   * - if its a ending brace, then return false if stack is empty OR if stacks top is NOT a starting brace
   * - if its a starting brace, push it into stack
   * if entire string is processed then return true.
   *
   * @param expression
   * @return
   */
  static public boolean checkIfBalancedExpression(String expression) {
    if (null == expression) {
      return false;
    }
    char[] elements = expression.toCharArray();
    Stack<Character> stack = new Stack<>();
    for (char ch : elements) {
      if (parenthesis.contains(ch)) {
        if (parenthesisPairs.containsKey(ch)) { //if its ending brace ) ] }
          if ((stack.isEmpty() || !isParenthesisPairs(ch, stack.pop()))) {
            return false;
          }
        } else {
          stack.push(ch);
        }
      }
    }

    return stack.isEmpty();
  }

  /**
   * Below expressions have duplicate parenthesis -
   * ((a+b)+((c+d)))
   * The subexpression "c+d" is surrounded by two
   * pairs of brackets.
   * <p>
   * (((a+(b)))+(c+d))
   * The subexpression "a+(b)" is surrounded by two
   * pairs of brackets.
   * <p>
   * (((a+(b))+c+d))
   * The whole expression is surrounded by two
   * pairs of brackets.
   * <p>
   * Below expressions don't have any duplicate parenthesis -
   * ((a+b)+(c+d))
   * No subsexpression is surrounded by duplicate
   * brackets.
   * <p>
   * ((a+(b))+(c+d))
   * No subsexpression is surrounded by duplicate
   * brackets.
   *
   * @param expression
   * @return
   */
  @Important
  @Medium
  public static boolean checkIfDuplicateParentheses(String expression) {
    if (expression != null && !expression.isEmpty()) {
      Stack<Character> stack = new Stack<>();
      for (int i = 0; i < expression.length(); i++) {
        char c = expression.charAt(i);
        if (c == ')') {
          int elementsInBraces = 0;
          for (char top = stack.pop(); top != '('; ) {
            top = stack.pop();
            elementsInBraces++;
          }
          if (elementsInBraces == 0) {
            // dupe pair will have no operator/operands in between as it was popped by another pair
            return true;
          }
        } else {
          stack.push(c);
        }
      }
    }
    return false;
  }

  @Important
  @Medium
  public static String removeDuplicateParentheses(String expression) {
    if (expression == null || expression.isEmpty()) {
      return null;
    }
    Stack<String> stack = new Stack<>();
    for (int i = 0; i < expression.length(); i++) {
      char c = expression.charAt(i);
      if (c == ')') {
        if (stack.isEmpty()) {
          return expression;
        }

        int elementsInBraces = 0;
        String elements = "";
        for (String top = stack.pop(); !"(".equals(top); top = stack.pop()) {
          elementsInBraces++;
          elements = top + elements;
        }

        if (elementsInBraces > 1) {
          elements = String.format("%s%s%s", "(", elements, ")");
        }
        stack.push(elements);
      } else {
        stack.push(String.valueOf(c));
      }
    }
    String result = "";
    while (!stack.isEmpty()) {
      result = stack.pop() + result;
    }
    return result;
  }

  /**
   * Input : S = "(a(b)(c)(d(e(f)(x)g)h)I(j(k)l)m)";
   * Output : 4
   * <p>
   * Input : S = "( p((q)) ((s)t) )";
   * Output : 3
   * <p>
   * Input : S = "";
   * Output : 0
   * <p>
   * Input : S = "b) (c) ()";
   * Output : -1
   * <p>
   * Input : S = "(b) ((c) ()"
   * Output : -1
   *
   * @param expression
   * @return
   */
  @Important
  @Hard
  //TODO: Fix this!!
  public static int maxDepthOfBalancedParentheses(String expression, boolean checkValidExpression) {
    if (expression == null || expression.isEmpty()) {
      return 0;
    }
    // "(a(b)(c)(d(e(f)(x)g)h)I(j(k)l)m)"
    int pairCount = 0;
    int unbalancedCount = 0;
    int maxDepth = 0;
    Character prev = null;
    for (int i = 0; i < expression.length(); i++) {
      char c = expression.charAt(i);
      if (c == '(') {
        unbalancedCount++;
        if (prev == '(') {
          pairCount++;
        } else {
          pairCount--;
        }
        prev = c;
      } else if (c == ')') {
        if (unbalancedCount == 0) {
          // found a bare ) in the string
          if (checkValidExpression) {
            return -1;
          }
          continue;
        }
        unbalancedCount--;
        pairCount++;
        if (maxDepth < pairCount / 2) {
          maxDepth = pairCount / 2;
        }
        prev = c;
      }
    }
    if (checkValidExpression && unbalancedCount != 0) // expression is balanced check
    {
      return -1;
    }
    return maxDepth;
  }

  /**
   * Input : ((())) ) (((())))
   * Output : 8
   * Explanation : (((())))
   * <p>
   * Input: )()())
   * Output : 4
   * Explanation: ()()
   * <p>
   * Input:  ()(()))))
   * Output: 6
   * Explanation:  ()(())
   *
   * @param expression
   * @return
   */
  @Medium
  @Important
  public static int maxLengthOfBalancedParentheses(String expression, boolean checkValidExpression) {
    if (expression == null || expression.isEmpty()) {
      return 0;
    }

    int pairCount = 0;
    int unbalancedCount = 0;
    int maxLength = 0;
    for (int i = 0; i < expression.length(); i++) {
      char c = expression.charAt(i);
      if (c == '(') {
        unbalancedCount++;
      } else if (c == ')') {
        if (unbalancedCount == 0) {
          // found a bare ) in the string
          if (checkValidExpression) {
            return -1;
          }
          pairCount = 0; // reset the pairCount
          continue;
        }
        // found a pair
        unbalancedCount--;
        pairCount = pairCount + 2;
        if (maxLength < pairCount) {
          maxLength = pairCount;
        }
      }
    }
    if (checkValidExpression && unbalancedCount != 0) {
      return -1;
    }
    return maxLength;
  }

  public static String removeBrackets(String expression) {
    if (expression == null || !expression.contains("(") || !expression.contains(")")) {
      return expression;
    }
    int unbalancedCount = 0;
    String result = "";
    for (int i = 0; i < expression.length(); i++) {
      char c = expression.charAt(i);
      if (c == '(') {
        unbalancedCount++;
      } else if (c == ')') {
        unbalancedCount--;
      } else {
        result += c;
      }
    }
    if (unbalancedCount != 0) {
      return null;
    }
    return result;
  }
}
