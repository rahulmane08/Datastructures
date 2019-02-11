package stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class TestStack {

    private stack.Stack<Integer> s = new Stack<>(10);
    private java.util.Stack<Integer> testStack = new java.util.Stack<>();

    @Before
    public void init() {
        for (int i = 0; i < 10; i++) {
            s.push(i);
            testStack.push(i);
        }
    }

    @Test
    public void testStack() {
        assertEquals(s.size(), 10);
        assertEquals(s.pop().intValue(), 9);
        assertEquals(s.pop().intValue(), 8);
    }

    @Test
    public void test_reversify() {
        StackUtils.reversify(testStack);
        assertEquals(testStack.pop().intValue(), 0);
    }

    @Test
    public void test_evaluatePostfixExpression() {
        double result = ExpressionUtils.evaluatePostfixExpression("12+");
        assertTrue(result == 3);
        result = ExpressionUtils.evaluatePostfixExpression("1342/+*");
        assertTrue(result == 5);
    }

    @Test
    public void test_checkIfBalancedExpression() {
        assertTrue(ExpressionUtils.checkIfBalancedExpression("(1+2*(6/3))"));
        assertFalse(ExpressionUtils.checkIfBalancedExpression(")()"));
    }

    @Test
    public void test_stockSpan() {
        int[] span = StackUtils.findSpan(new int[]{100, 80, 60, 70, 60, 75, 85});
        int[] expected = new int[]{1, 1, 1, 2, 1, 4, 6};
        assertTrue(Arrays.equals(span, expected));
    }

    @Test
    public void test_findNextGreaterOrSmallerElement() {
        int[] arr = {4, 5, 2, 25};
        int[] expected = {5, 25, 25, -1};
        int[] result = StackUtils.findNextGreaterOrSmallerElement(arr, true);
        assertTrue(Arrays.equals(result, expected));
    }

    @Test
    public void test_findNumberOfNextGreaterElements() {
        int[] arr = {4, 5, 2, 25};
        int[] expected = {2, 1, 1, 0};
        int[] result = StackUtils.findNumberOfNextGreaterElements(arr);
        assertTrue(Arrays.equals(result, expected));

        arr = new int[]{4, 3, 2, 1};
        expected = new int[]{0, 0, 0, 0};
        result = StackUtils.findNumberOfNextGreaterElements(arr);
        assertTrue(Arrays.equals(result, expected));

        arr = new int[]{1, 2, 3, 4};
        expected = new int[]{3, 2, 1, 0};
        result = StackUtils.findNumberOfNextGreaterElements(arr);
        assertTrue(Arrays.equals(result, expected));
    }

    @Test
    public void test_maxProductOfGreaterLeftAndRight() {
        int[] arr = {5, 4, 3, 4, 5};
        assertEquals(8, StackUtils.maxProductOfGreaterLeftAndRight(arr));
        arr = new int[]{1, 1, 1, 1, 0, 1, 1, 1, 1, 1};
        assertEquals(24, StackUtils.maxProductOfGreaterLeftAndRight(arr));
    }

    @Test
    public void test_getMinStack() {
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        stack.push(3);
        stack.push(1);
        stack.push(2);
        stack.push(4);
        java.util.Stack<Integer> minStack = StackUtils.getMinStack(stack);
        System.out.println(minStack);

        stack.clear();
        for (int i = 1; i < 5; i++)
            stack.push(i);
        minStack = StackUtils.getMinStack(stack);
        System.out.println(minStack);

        stack.clear();
        for (int i = 5; i > 0; i--)
            stack.push(i);
        minStack = StackUtils.getMinStack(stack);
        System.out.println(minStack);
    }

    @Test
    public void test_sortStack() {
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        stack.push(3);
        stack.push(1);
        stack.push(2);
        stack.push(4);
        StackUtils.sort(stack);
        System.out.println(stack);
    }

    @Test
    public void test_sortStackUsingTempStack() {
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        stack.push(3);
        stack.push(1);
        stack.push(2);
        stack.push(4);
        StackUtils.sortUsingTempStack(stack);
        stack.forEach(System.out::println);
    }

    @Test
    public void test_deleteMiddle() {
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        for (int i = 1; i <= 5; i++)
            stack.push(i);
        StackUtils.deleteMiddle(stack);
        assertFalse(stack.contains(3));

        stack.clear();
        stack.push(1);
        StackUtils.deleteMiddle(stack);
        assertTrue(stack.isEmpty());
    }
}
