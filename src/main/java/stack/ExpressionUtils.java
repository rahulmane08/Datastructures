package stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class ExpressionUtils {
    static HashMap<String, Integer> priortyMap = new HashMap<>();
    static HashMap<Character, Character> parenthesisPairs = new HashMap<>();
    private static List<Character> operators = Arrays.asList(new Character[]{'/', '-', '+', '*'});
    private static List<Character> parenthesis = Arrays.asList(new Character[]{'(', ')', '{', '}', '[', ']'});

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
        return checkPriority("" + operator1, "" + operator2);
    }

    private static boolean isOperand(char operand) {
        return !priortyMap.containsKey("" + operand);
    }

    private static boolean isParenthesisPairs(char p1, char p2) {
        return ((parenthesisPairs.get(p1).equals(p2)) || (parenthesisPairs.get(p2).equals(p1)));
    }

    /**
     * Infix to postfix:
     * - In infix and postfix expressions the order of the operands remain the same, however the order of operators changes .
     * - We will use only one stack that will contain only the operators and (.
     * - THe postfix expression doesnt contain any parentheses.
     * - algo:
     * - parse each character in the input string and store in t`
     * if t=operand, then output
     * else if t=')', then pop and output all elements in stack until ( is popped
     * else if t='(', then push it into stack
     * else if t= operator , then pop until all same/higher priority operators than t or until ( is popped.
     *
     * @param infixExp
     */
    public static String infixToPostfix(String infixExp) {
        if (infixExp == null)
            return null;
        char[] characters = infixExp.toCharArray();
        java.util.Stack<Character> s = new Stack<>();
        String output = "";
        for (char t : characters) {

            // if its an operand then print
            if (isOperand(t))
                output += t;
                // if its a right brace, then pop and print until ( is encountered/
                // pop ( as well but dont print
            else if (t == ')') {
                for (char x = s.pop(); x != '('; ) {
                    output += x;
                    x = s.pop();
                }
            } else {
                // if its a ( then push, this means ) is coming ahead
                if (t == '(')
                    s.push(t);
                else {
                    // its a operator
                    if (s.isEmpty())
                        s.push(t);
                    else {
                        do {
                            char y = s.pop();
                            if (y == '(' || checkPriority(y, t) < 0) {
                                s.push(y);
                                break;
                            } else {
                                output += y;
                            }
                        } while (!s.isEmpty());
                        s.push(t);
                    }

                }
            }
        }
        while (!s.isEmpty()) {
            output += s.pop();
        }
        return output;
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
        char[] characters = exp.toCharArray();
        Stack<Double> s = new Stack<>();
        for (char t : characters) {
            if (isOperand(t))
                s.push(Double.parseDouble("" + t));
            else {
                double o1 = s.pop();
                double o2 = s.pop();
                double result = compute(o2, o1, t);
                s.push(result);
            }
        }
        return Double.valueOf(s.pop());
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
        char[] chars = prefixExp.toCharArray();
        Stack<String> stack = new Stack<>();
        for (int i = chars.length - 1; i >= 0; i--) {
            if (isOperand(chars[i])) {
                stack.push(String.valueOf(chars[i]));
            } else {
                String op1 = stack.pop();
                String op2 = stack.pop();
                String exp = String.format("(%s%s%s)", op1, String.valueOf(chars[i]), op2);
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
        if (prefixExp == null)
            return null;
        char[] chars = prefixExp.toCharArray();
        Stack<String> stack = new Stack<>();
        for (int i = chars.length - 1; i >= 0; i--) {
            char x = chars[i];
            if (isOperand(x)) {
                stack.push(String.valueOf(x));
            } else {
                String op1 = stack.pop();
                String op2 = stack.pop();
                String exp = String.format("%s%s%s", op1, op2, String.valueOf(chars[i]));
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
        for (char x : postfixExp.toCharArray()) {
            if (isOperand(x)) {
                stack.push(String.valueOf(x));
            } else {
                String op1 = stack.pop();
                String op2 = stack.pop();
                String exp = String.format("(%s%s%s)", op2, String.valueOf(x), op1);
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
        if (postfix == null)
            return null;
        Stack<String> stack = new Stack<>();
        for (char x : postfix.toCharArray()) {
            if (isOperand(x)) {
                stack.push(String.valueOf(x));
            } else {
                String op1 = stack.pop();
                String op2 = stack.pop();
                String exp = String.format("%s%s%s", String.valueOf(x), op2, op1);
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
        if (null == expression)
            return false;
        char[] elements = expression.toCharArray();
        Stack<Character> charStack = new Stack<>();
        for (char c : elements) {
            if (parenthesis.contains(c)) {
                if (parenthesisPairs.containsKey(c)) {
                    if (charStack.isEmpty() || !isParenthesisPairs(c, charStack.pop()))
                        return false;
                } else
                    charStack.push(c);
            }
        }

        return true;
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
}
