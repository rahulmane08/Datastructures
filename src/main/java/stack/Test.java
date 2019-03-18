package stack;

import static stack.StackUtils.deleteMiddle;
import static stack.StackUtils.findPermutationsGreaterThanEqualToOriginalNumber;
import static stack.StackUtils.findPrevGreaterOrSmallerElement;
import static stack.StackUtils.sort;

import java.util.Arrays;
import java.util.Stack;

public class Test {
    public static void main(String[] args) {
        stack.Stack<Integer> s = new stack.Stack<>(10);
        for (int i = 0; i < 10; i++)
            s.push(i);
        System.out.println(s.size());
        System.out.println(s.pop());
        System.out.println(s.size());
        s.push(20);
        //  s.push(21);

        System.out.println(ExpressionUtils.checkIfBalancedExpression("((A+B)+{23*98})"));

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 10; i++)
            stack.push(i);
        System.out.println(stack);
        StackUtils.reversify(stack);
        System.out.println(stack);

        int[] stockPrices = new int[]{100, 80, 60, 70, 60, 75, 85, 120};
        int[] span = StackUtils.findSpan(stockPrices);
        for (int i = 0; i < stockPrices.length; i++)
            System.out.println(stockPrices[i] + ":" + span[i]);

        System.out.println(Arrays.toString(findPrevGreaterOrSmallerElement(new int[]{2, 1, 8}, false)));
        System.out.println(Arrays.toString(findPrevGreaterOrSmallerElement(new int[]{1, 20, 3, 4, 5}, true)));

        System.out.println("=====Stack sorting=========");
        Stack<Integer> stack1 = new Stack<>();
        stack1.push(10);
        stack1.push(2);
        stack1.push(3);
        System.out.println("Before sorting: " + stack1);
        sort(stack1);
        System.out.println("After sorting: " + stack1);

        System.out.println("=====Stack delete middle=========");
        deleteMiddle(stack1);
        System.out.println("After mid delete: " + stack1);
        deleteMiddle(stack1);
        System.out.println("After mid delete: " + stack1);
        deleteMiddle(stack1);
        System.out.println("After mid delete: " + stack1);

        System.out.println("Total numbers whose perms are >= actual: " +
                findPermutationsGreaterThanEqualToOriginalNumber(50));
    }
}
