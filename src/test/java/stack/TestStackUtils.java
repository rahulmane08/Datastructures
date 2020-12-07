package stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static stack.ExpressionUtils.maxDepthOfBalancedParentheses;
import static stack.ExpressionUtils.maxLengthOfBalancedParentheses;
import static stack.ExpressionUtils.removeBrackets;
import static stack.StackUtils.checkPairWiseConsecutive;
import static stack.StackUtils.countPatternOccurences;
import static stack.StackUtils.countPatternOccurences1;
import static stack.StackUtils.countPermutationsGreaterThanEqualToOriginalNumber;
import static stack.StackUtils.decodeStringByCount;
import static stack.StackUtils.deleteBottomKElements;
import static stack.StackUtils.deleteMiddle;
import static stack.StackUtils.findMinimumNumberForGivenSequence;
import static stack.StackUtils.findNextGreaterOrSmallerElement;
import static stack.StackUtils.findNextGreaterOrSmallerFrequencyElement;
import static stack.StackUtils.maxHistogramArea;
import static stack.StackUtils.minHistogramArea;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class TestStackUtils {

    private final stack.Stack<Integer> s = new Stack<>(10);
    private final java.util.Stack<Integer> testStack = new java.util.Stack<>();

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
        assertFalse(ExpressionUtils.checkIfBalancedExpression("(()"));
    }

    @Test
    public void test_stockSpan() {
        int[] span = StackUtils.findStockSpan(new int[]{100, 80, 60, 70, 60, 75, 85});
        int[] expected = new int[]{1, 1, 1, 2, 1, 4, 6};
        assertTrue(Arrays.equals(span, expected));
    }

    @Test
    public void test_findNextGreaterOrSmallerElement() {
        int[] arr = {4, 5, 2, 25, 60, 20, 30};
        int[] expected = {5, 25, 25, 60, -1, 30, -1};
        int[] result = findNextGreaterOrSmallerElement(arr, true);
        assertTrue(Arrays.equals(result, expected));

        expected = new int[]{2, 2, -1, 20, 20, -1, -1};
        result = findNextGreaterOrSmallerElement(arr, false);
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

        arr = new int[]{1, 2, 3, 6, 5};
        expected = new int[]{4, 3, 2, 0, 0};
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
        deleteMiddle(stack);
        assertFalse(stack.contains(3));

        stack.clear();
        stack.push(1);
        deleteMiddle(stack);
        assertTrue(stack.isEmpty());
    }


    @Test
    public void test_isPalindrome() {
        String s = "abcd";
        assertFalse(StackUtils.isPalindrome(s));

        s = "";
        assertFalse(StackUtils.isPalindrome(s));

        s = null;
        assertFalse(StackUtils.isPalindrome(s));

        s = "abba";
        assertTrue(StackUtils.isPalindrome(s));

        s = "abcba";
        assertTrue(StackUtils.isPalindrome(s));

        s = "a";
        assertTrue(StackUtils.isPalindrome(s));

        s = "aa";
        assertTrue(StackUtils.isPalindrome(s));
    }

    @Test
    public void test_mergedIntervals() {
        int[][] intervals = new int[5][2];
        intervals[0] = new int[]{1, 5};
        intervals[1] = new int[]{10, 12};
        intervals[2] = new int[]{15, 17};
        intervals[3] = new int[]{3, 6};
        intervals[4] = new int[]{2, 4};
        int[][] mergeIntervals = StackUtils.mergeIntervals(intervals);
        System.out.println("For intervals: ");
        for (int i = 0; i < intervals.length; i++) {
            System.out.println(Arrays.toString(intervals[i]));
        }
        System.out.println("Merged intervals: ");
        for (int i = 0; i < mergeIntervals.length; i++) {
            System.out.println(Arrays.toString(mergeIntervals[i]));
        }

        intervals[0] = new int[]{1, 5};
        intervals[1] = new int[]{10, 12};
        intervals[2] = new int[]{15, 17};
        intervals[3] = new int[]{3, 10};
        intervals[4] = new int[]{2, 4};
        mergeIntervals = StackUtils.mergeIntervals(intervals);
        System.out.println("For intervals: ");
        for (int i = 0; i < intervals.length; i++) {
            System.out.println(Arrays.toString(intervals[i]));
        }
        System.out.println("Merged intervals: ");
        for (int i = 0; i < mergeIntervals.length; i++) {
            System.out.println(Arrays.toString(mergeIntervals[i]));
        }

        intervals[0] = new int[]{1, 5};
        intervals[1] = new int[]{10, 12};
        intervals[2] = new int[]{15, 17};
        intervals[3] = new int[]{3, 10};
        intervals[4] = new int[]{5, 15};
        mergeIntervals = StackUtils.mergeIntervals(intervals);
        System.out.println("For intervals: ");
        for (int i = 0; i < intervals.length; i++) {
            System.out.println(Arrays.toString(intervals[i]));
        }
        System.out.println("Merged intervals: ");
        for (int i = 0; i < mergeIntervals.length; i++) {
            System.out.println(Arrays.toString(mergeIntervals[i]));
        }

        intervals = new int[4][2];
        intervals[0] = new int[]{6, 8};
        intervals[1] = new int[]{1, 9};
        intervals[2] = new int[]{2, 4};
        intervals[3] = new int[]{4, 7};
        mergeIntervals = StackUtils.mergeIntervals(intervals);
        System.out.println("For intervals: ");
        for (int i = 0; i < intervals.length; i++) {
            System.out.println(Arrays.toString(intervals[i]));
        }
        System.out.println("Merged intervals: ");
        for (int i = 0; i < mergeIntervals.length; i++) {
            System.out.println(Arrays.toString(mergeIntervals[i]));
        }
    }

    @Test
    public void test_maxDepthOfBalancedParentheses() {
        String S = "(a(b)(c)(d(e(f)g)h)I(j(k)l)m)";
        assertEquals(4, maxDepthOfBalancedParentheses(S, false));
        S = "( p((q)) ((s)t) )";
        //assertEquals(3, maxDepthOfBalancedParentheses(S));
        S = "";
        //assertEquals(0, maxDepthOfBalancedParentheses(S));
        S = "b) (c) ()";
        //assertEquals(-1, maxDepthOfBalancedParentheses(S));
        S = "(b) ((c) ()";
        //assertEquals(-1, maxDepthOfBalancedParentheses(S));
    }

    @Test
    public void test_maxLengthOfBalancedParentheses() {
        String s = "((())) ) (((())))";
        assertEquals(8, maxLengthOfBalancedParentheses(s, false));
        assertEquals(-1, maxLengthOfBalancedParentheses(s, true));
        s = ")()())";
        assertEquals(4, maxLengthOfBalancedParentheses(s, false));
        assertEquals(-1, maxLengthOfBalancedParentheses(s, true));
        s = "()(()))))";
        assertEquals(6, maxLengthOfBalancedParentheses(s, false));
        assertEquals(-1, maxLengthOfBalancedParentheses(s, true));
        s = "((()))(())";
        assertEquals(10, maxLengthOfBalancedParentheses(s, false));
        assertEquals(10, maxLengthOfBalancedParentheses(s, true));
        s = "((((((())";
        assertEquals(4, maxLengthOfBalancedParentheses(s, false));
        assertEquals(-1, maxLengthOfBalancedParentheses(s, true));
    }

    @Test
    public void test_removeBrackets() {
        String s = "a-(b+c)";
        assertEquals("a-b+c", removeBrackets(s));
        s = "a-(b-c-(d+e))-f";
        assertEquals("a-b-c-d+e-f", removeBrackets(s));
    }

    @Test
    public void test_checkPairWiseConsecutive() {
        int[] arr = new int[]{4, 5, -2, -3, 11, 10, 5, 6, 20};
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        Arrays.stream(arr).forEach(stack::push);
        assertTrue(checkPairWiseConsecutive(stack));
        arr = new int[]{4, 6, 6, 7, 4, 3};
        stack.clear();
        Arrays.stream(arr).forEach(stack::push);
        assertFalse(checkPairWiseConsecutive(stack));
    }

    @Test
    public void test_decodeStringByCount1() {
        assertEquals("bcacabcacabcacad", decodeStringByCount("3[b2[ca]]d"));
        assertEquals("dabcacabcacabcacabc", decodeStringByCount("da3[b2[ca]]bc"));
    }

    @Test
    public void test_countPermutationsGreaterThanEqualToOriginalNumber() {
        assertEquals(14, countPermutationsGreaterThanEqualToOriginalNumber(15));
    }

    @Test
    public void test_checkIfDuplicateParentheses() {
        assertTrue(ExpressionUtils.checkIfDuplicateParentheses("((a+b)+((c+d)))"));
        assertTrue(ExpressionUtils.checkIfDuplicateParentheses("(((a+(b)))+(c+d))"));
        assertFalse(ExpressionUtils.checkIfDuplicateParentheses("((a+b)+(c+d))"));
    }

    @Test
    public void test_findMinimumNumberForGivenSequence() {
        assertEquals("12", findMinimumNumberForGivenSequence("I"));
        assertEquals("21", findMinimumNumberForGivenSequence("D"));
        assertEquals("123", findMinimumNumberForGivenSequence("II"));
        assertEquals("321", findMinimumNumberForGivenSequence("DD"));
        assertEquals("21435", findMinimumNumberForGivenSequence("DIDI"));
        assertEquals("126543", findMinimumNumberForGivenSequence("IIDDD"));
        assertEquals("321654798", findMinimumNumberForGivenSequence("DDIDDIID"));
    }

    @Test
    public void test_maxMinHistogramArea() {
        assertEquals(12, maxHistogramArea(new int[]{6, 2, 5, 4, 5, 1, 6}));
        assertEquals(1, minHistogramArea(new int[]{6, 2, 5, 4, 5, 1, 6}));
    }

    @Test
    public void test_findNextGreaterOrSmallerFrequencyElement() {
        assertTrue(Arrays.equals(new int[]{-1, -1, 1, 2, 2, 1, -1},
                findNextGreaterOrSmallerFrequencyElement(new int[]{1, 1, 2, 3, 4, 2, 1}, true)));
    }

    @Test
    public void test_countPatternOccurences() {
        assertEquals(3, countPatternOccurences("BABABCABCC", "ABC"));
        assertEquals(0, countPatternOccurences("BABABCABCC", "ABCD"));
        assertEquals(3, countPatternOccurences("BABABCABCC", "AB"));
        assertEquals(4, countPatternOccurences("BABABCABCC", "B"));
    }

    @Test
    public void test_countPatternOccurences1() {
        assertEquals(3, countPatternOccurences1("BABABCABCC", "ABC"));
        assertEquals(0, countPatternOccurences1("BABABCABCC", "ABCD"));
        assertEquals(3, countPatternOccurences1("BABABCABCC", "AB"));
        assertEquals(4, countPatternOccurences1("BABABCABCC", "B"));
    }

    @Test
    public void test_deleteFirstKElements() {
        java.util.Stack stack = new java.util.Stack();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        deleteBottomKElements(stack, 4);
        assertEquals(6, stack.size());
        System.out.println(stack);
        deleteBottomKElements(stack, 4);
        assertEquals(2, stack.size());
        System.out.println(stack);
        deleteBottomKElements(stack, 4);
        assertEquals(0, stack.size());
        System.out.println(stack);
    }
}
