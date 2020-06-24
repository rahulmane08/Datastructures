package stack;

import java.util.Stack;

public class StackUtils {
    /**
     * Recursively pop each element until stack is empty
     * for each poppped element in that call stack call another recursive method insertAtBottom
     * that inserts the element at the bottom of stack recursively
     *
     * @param s
     * @param <T>
     */
    public static <T> void reversify(Stack<T> s) {
        if (s == null || s.isEmpty())
            return;
        T elem = s.pop();
        reversify(s);
        insertAtBottom(s, elem);
    }

    private static <T> void insertAtBottom(Stack<T> s, T elem) {
        if (s.isEmpty()) {
            s.push(elem);
            return;
        }
        T curr = s.pop();
        insertAtBottom(s, elem);
        s.push(curr);
    }

    /**
     * The span Si of the stock’s price on a given day i is defined as the maximum number of consecutive days
     * just before the given day,for which the price of the stock on the current day is less than or equal to its price
     * on the given day.
     * For example, if an array of 7 days prices is given as {100, 80, 60, 70, 60, 75, 85},
     * then the span values for corresponding 7 days are        {1, 1,  1,  2,  1,  4,  6}
     * Solution:
     * 1. push 0th element in stack and initialize span[0] = 1 , as it wont have a prev day
     * 2. for each day = i check stock price of peek element in stack is less than current and if yes then pop , until stack is empty or a greater stock price is present.
     * 3. update span[i] = i - s.peek() // i + 1 if s is empty;
     * 4. push i to stack
     *
     * @param arr
     * @return
     */
    static public int[] findSpan(int[] arr) {
        Stack<Integer> s = new Stack<>();
        int[] span = new int[arr.length];
        span[0] = 1;
        s.push(0);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1])
                span[i] = 1;
            else {
                span[i] = 1;
                while (!s.isEmpty() && arr[i] >= arr[s.peek()])
                    span[i] += span[s.pop()];
            }
            s.push(i);
        }
        return span;
    }

    /**
     * - n=30
     * - for i=1 to 9
     * - put i in stack
     * - then for 1 , put 1*10+(1->9) in stack and print and add to stack if its less than n
     * - stack will have (11 -> 19)
     * - (11->19)*10+(1->9) are all greater than n , so skip
     *
     * @param n
     * @return
     */
    static public int findPermutationsGreaterThanEqualToOriginalNumber(int n) {
        int count = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int number = 0;
        while (!stack.isEmpty()) {
            int base = stack.pop();
            int lsd = base % 10;
            for (int i = lsd; i <= 9; i++) {
                number = 10 * base + i;
                if (number != 0 && number < n) {
                    stack.push(number);
                    System.out.println("Number = " + number);
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * @param arr
     * @param greater
     * @return
     */
    static public int[] findPrevGreaterOrSmallerElement(int[] arr, boolean greater) {
        int[] pgse = new int[arr.length];
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!s.isEmpty() && (greater ? arr[s.peek()] <= arr[i] : arr[s.peek()] >= arr[i]))
                s.pop();
            if (s.isEmpty())
                pgse[i] = -1;
            else
                pgse[i] = arr[s.peek()];
            s.push(i);
        }
        return pgse;
    }

    /**
     * Next Greater Element
     * Given an array, print the Next Greater Element (NGE) for every element.
     * The Next greater Element for an element x is the first greater element on the right side of x in array.
     * Elements for which no greater element exist, consider next greater element as -1.
     * For the input array [4, 5, 2, 25}, the next greater elements for each element are as follows.
     * Element       NGE
     * 4      -->   5
     * 5      -->   25
     * 2      -->   25
     * 25     -->   -1
     *
     * @param arr
     * @param greater
     * @return
     */
    static public int[] findNextGreaterOrSmallerElement(int[] arr, boolean greater) {
        int[] ngse = new int[arr.length];
        Stack<Integer> s = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && (greater ? arr[s.peek()] <= arr[i] : arr[s.peek()] >= arr[i]))
                s.pop();
            if (s.isEmpty())
                ngse[i] = -1;
            else
                ngse[i] = arr[s.peek()];
            s.push(i);
        }
        return ngse;
    }

    static public int[] findNextSmallerToNextGreater(int[] arr) {
        int[] result = new int[arr.length];
        int[] nge = findNextGreaterOrSmallerElement(arr, true);
        int[] nse = findNextGreaterOrSmallerElement(arr, false);
        for (int i = 0; i < nge.length; i++) {
            result[i] = -1;
            if (nge[i] != -1 && nse[nge[i]] != -1)
                result[i] = arr[nse[nge[i]]];
        }
        return result;
    }

    /**
     * start reverse
     * if the top of stack has element lesser than current then pop until greater element is found or stack is empty
     * if stack is empty,set result = 0
     * if stack is not empty, then result = 1 + result[top element index]
     * push the current index
     *
     * @param arr
     * @return
     */
    static public int[] findNumberOfNextGreaterElements(int[] arr) {
        if (arr == null)
            return null;
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                result[i] = 0;
            } else {
                result[i] = 1 + result[stack.peek()];
            }
            stack.push(i);
        }
        return result;
    }

    /**
     * 1. calculate mid
     * 2. pop the top and recursively call the deleteMiddle , push the top back
     * 3. if current recursion round == mid , end the recursion.
     *
     * @param stack
     */
    static public void deleteMiddle(Stack<Integer> stack) {
        if (stack == null)
            return;
        int mid = (stack.size() / 2) + 1;
        deleteMiddle(stack, mid);
    }

    static private void deleteMiddle(Stack<Integer> stack, int mid) {
        if (mid == 0)
            return;
        int top = stack.pop();
        --mid;
        deleteMiddle(stack, mid);
        if (mid > 0)
            stack.push(top);
    }

    // stack sorting
    static public void sortUsingTempStack(Stack<Integer> stack) {
        if (stack.isEmpty())
            return;
        Stack<Integer> tempStack = new Stack<>();
        while (!stack.isEmpty()) {
            tempStack.push(stack.pop());
        }

        while (!tempStack.isEmpty()) {
            int curr = tempStack.pop();
            while (!stack.isEmpty() && curr > stack.peek()) {
                int min = stack.pop();
                tempStack.push(min);
            }
            stack.push(curr);
        }
    }

    static public void sort(Stack<Integer> stack) {
        if (stack.isEmpty())
            return;
        Integer curr = stack.pop();
        sort(stack);
        sortedInsert(stack, curr);
    }

    static private void sortedInsert(Stack<Integer> s, int elem) {
        if (s.isEmpty() || elem <= s.peek()) {
            s.push(elem);
            return;
        }
        int top = s.pop();
        sortedInsert(s, elem);
        s.push(top);
    }

    /**
     * - since it a SLL, we cant move backwards and hence we need to use a stack.
     * - compute the length of the string = n
     * - traverse the list till int(n/2) and push it onto stack
     * - if n is odd then ignore the n/2th element , if its even then dont
     * - for each next element of SLL, pop from the stack and check if the popped element is same, if not return false
     * - continue this until EOS.
     *
     * @param str
     * @return
     */
    static public boolean isPalindrome(String str) {
        char[] characters = str.toCharArray();
        int length = characters.length;
        Stack<Character> stack = new Stack<>();
        int i = 0;
        for (; i < length / 2; i++)
            stack.push(characters[i]);
        if (length % 2 != 0) ++i;
        for (; i < characters.length; i++)
            if (characters[i] != stack.pop())
                break;
        return stack.isEmpty();
    }


    /**
     * Input : 1 1 1 1 0 1 1 1 1 1
     * Output : 24
     * For {1, 1, 1, 1, 0, 1, 1, 1, 1, 1} all element are same except 0. So only for zero their exist greater element
     * and for others it will be zero. for zero, on left 4th element is closest and greater than zero
     * and on right 6th element is closest and greater. so maximum
     * product will be 4*6 = 24.
     * <p>
     * Input : 5 4 3 4 5
     * Output : 8
     * For {5, 4, 3, 4, 5}, L[] = {0, 1, 2, 1, 0} and R[]
     * = {0, 5, 4, 5, 0},
     * LRProduct = {0, 5, 8, 5, 0} and max in this is 8.
     *
     * @param arr
     * @return
     */
    static int maxProductOfGreaterLeftAndRight(int[] arr) {
        if (arr == null || arr.length == 1) {
            return 0;
        }
        int product = 0;
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= arr.length - 1; i++) {
            while (!stack.isEmpty() && arr[i] >= arr[stack.peek()])
                stack.pop();
            if (stack.isEmpty())
                left[i] = 0;
            else
                left[i] = stack.peek() + 1;
            stack.push(i);
        }
        stack.clear();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] >= arr[stack.peek()])
                stack.pop();
            if (stack.isEmpty())
                right[i] = 0;
            else
                right[i] = stack.peek() + 1;
            stack.push(i);
        }

        for (int i = 0; i <= arr.length - 1; i++) {
            int curr = left[i] * right[i];
            if (product < curr)
                product = curr;
        }
        return product;
    }

    static public Stack<Integer> getMinStack(Stack<Integer> stack) {
        Stack<Integer> minStack = new Stack<>();
        fillMinStack(stack, minStack);
        return minStack;
    }

    static private void fillMinStack(Stack<Integer> stack, Stack<Integer> minStack) {
        if (stack.isEmpty()) {
            return;
        }
        int top = stack.pop();
        fillMinStack(stack, minStack);
        populateMinStack(minStack, top);
        stack.push(top);
    }

    static private void populateMinStack(Stack<Integer> minStack, int elem) {
        if (minStack.isEmpty() || elem < minStack.peek()) {
            minStack.push(elem);
        } else {
            minStack.push(minStack.peek());
        }
    }

    static public <T> void printStack(Stack<T> stack) {
        if (stack == null || stack.isEmpty())
            return;
        T elem = stack.pop();
        printStack(stack);
        System.out.println(elem);
        stack.push(elem);
    }

}
