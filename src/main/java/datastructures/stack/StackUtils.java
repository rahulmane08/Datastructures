package datastructures.stack;

import interfaces.Hard;
import interfaces.Important;
import interfaces.Medium;
import java.util.HashMap;
import java.util.Map;
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
  @Important
  public static <T> void reversify(Stack<T> s) {
    if (s == null || s.isEmpty()) {
      return;
    }
    T elem = s.pop();
    reversify(s);
    insertAtBottom(s, elem);
  }

  /**
   * T(n) = T(n-1) + 1
   *
   * @param s
   * @param elem
   * @param <T>
   */
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
  @Important
  static public int[] findStockSpan(int[] arr) {
    if (arr == null || arr.length == 0) {
      return null;
    }
    Stack<Integer> s = new Stack<>();
    int[] span = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      span[i] = 1;
      while (!s.isEmpty() && arr[i] >= arr[s.peek()]) {
        span[i] += span[s.pop()];
      }
      s.push(i);
    }
    return span;
  }

  /**
   * Given a number N, our task is to print those permutations of integer N which are greater than N.
   * <p>
   * Examples:
   * <p>
   * Input: N = 534
   * Output: 543
   * <p>
   * Input: N = 324
   * Output: 342, 423, 432
   * <p>
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
  static public int countPermutationsGreaterThanEqualToOriginalNumber(int n) {
    int count = 0;
    Stack<Integer> stack = new Stack<>();
    stack.push(1);
    int number = 0;
    while (!stack.isEmpty()) {
      int base = stack.pop();
      int lsd = base % 10;
      for (int i = lsd; i <= 9; i++) {
        number = 10 * base + i;
        if (number != 0 && number <= n) {
          stack.push(number);
          System.out.println("Number = " + number);
          count++;
        }
      }
    }

    return count;
  }

  static public int countPermutationsGreaterThanEqualToOriginalNumber1(int n) {
    int count = 0;
    Stack<Integer> stack = new Stack<>();
    for (int i = 1; i <= 9 && i <= n; i++) {
      stack.push(i);
      count++;
      while (!stack.isEmpty()) {
        int top = stack.pop();
        for (int j = top % 10; j <= 9; j++) {
          int x = top * 10 + j;
          if (x <= top) {
            stack.push(x);
            count++;
          }
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
  @Important
  static public int[] findPrevGreaterOrSmallerElement(int[] arr, boolean greater) {
    int[] pgse = new int[arr.length];
    Stack<Integer> s = new Stack<>();
    for (int i = 0; i < arr.length; i++) {
      pgse[i] = -1;
      while (!s.isEmpty() && (greater ? arr[s.peek()] <= arr[i] : arr[s.peek()] >= arr[i])) {
        s.pop();
      }
      if (!s.isEmpty()) {
        pgse[i] = arr[s.peek()];
      }
      s.push(i);
    }
    return pgse;
  }

  static public int[] findPrevGreaterOrSmallerElementIndexes(int[] arr, boolean greater) {
    int[] pgse = new int[arr.length];
    Stack<Integer> s = new Stack<>();
    for (int i = 0; i < arr.length; i++) {
      pgse[i] = -1;
      while (!s.isEmpty() && (greater ? arr[s.peek()] <= arr[i] : arr[s.peek()] >= arr[i])) {
        s.pop();
      }
      if (!s.isEmpty()) {
        pgse[i] = s.peek();
      }
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
  @Important
  static public int[] findNextGreaterOrSmallerElement(int[] arr, boolean greater) {
    int[] ngse = new int[arr.length];
    Stack<Integer> s = new Stack<>();
    for (int i = arr.length - 1; i >= 0; i--) {
      ngse[i] = -1;
      while (!s.isEmpty() && (greater ? arr[i] >= arr[s.peek()] : arr[s.peek()] >= arr[i])) {
        s.pop();
      }
      if (!s.isEmpty()) {
        ngse[i] = arr[s.peek()];
      }
      s.push(i);
    }
    return ngse;
  }

  static public int[] findNextGreaterOrSmallerElementIndexes(int[] arr, boolean greater) {
    int[] ngse = new int[arr.length];
    Stack<Integer> s = new Stack<>();
    for (int i = arr.length - 1; i >= 0; i--) {
      ngse[i] = -1;
      while (!s.isEmpty() && (greater ? arr[i] >= arr[s.peek()] : arr[s.peek()] >= arr[i])) {
        s.pop();
      }
      if (!s.isEmpty()) {
        ngse[i] = s.peek();
      }
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
      if (nge[i] != -1 && nse[nge[i]] != -1) {
        result[i] = arr[nse[nge[i]]];
      }
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
    if (arr == null) {
      return null;
    }
    int n = arr.length;
    int[] result = new int[n];
    Stack<Integer> stack = new Stack<>();
    for (int i = n - 1; i >= 0; i--) {
      while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
        stack.pop();
      }
      if (!stack.isEmpty()) {
        result[i] = 1 + result[stack.peek()];
      }
      stack.push(i);
    }
    return result;
  }

  /**
   * Input : a[] = [1, 1, 2, 3, 4, 2, 1]
   * Output :      [-1, -1, 1, 2, 2, 1, -1]
   * Explanation:
   * Given array a[] = [1, 1, 2, 3, 4, 2, 1]
   * Frequency of each element is: 3, 3, 2, 1, 1, 2, 3
   * Lets calls Next Greater Frequency element as NGF
   * 1. For element a[0] = 1 which has a frequency = 3,
   * As it has frequency of 3 and no other next element
   * has frequency more than 3 so  '-1'
   * 2. For element a[1] = 1 it will be -1 same logic
   * like a[0]
   * 3. For element a[2] = 2 which has frequency = 2,
   * NGF element is 1 at position = 6  with frequency
   * of 3 > 2
   * 4. For element a[3] = 3 which has frequency = 1,
   * NGF element is 2 at position = 5 with frequency
   * of 2 > 1
   * 5. For element a[4] = 4 which has frequency = 1,
   * NGF element is 2 at position = 5 with frequency
   * of 2 > 1
   * 6. For element a[5] = 2 which has frequency = 2,
   * NGF element is 1 at position = 6 with frequency
   * of 3 > 2
   * 7. For element a[6] = 1 there is no element to its
   * right, hence -1
   *
   * @param arr
   * @param greater
   * @return
   */
  public static int[] findNextGreaterOrSmallerFrequencyElement(int[] arr, boolean greater) {
    if (arr == null) {
      return null;
    }
    int n = arr.length;
    int[] result = new int[arr.length];
    Map<Integer, Integer> map = new HashMap<>();
    for (int elem : arr) {
      map.compute(elem, (k, v) -> v == null ? 1 : v + 1);
    }
    Stack<Integer> stack = new Stack<>();
    for (int i = n - 1; i >= 0; i--) {
      result[i] = -1;
      while (!stack.isEmpty()
          &&
          (greater ? map.get(arr[i]) >= map.get(arr[stack.peek()]) : map.get(arr[i]) <= map.get(arr[stack.peek()]))) {
        stack.pop();
      }

      if (!stack.isEmpty()) {
        result[i] = arr[stack.peek()];
      }
      stack.push(i);
    }
    return result;
  }

  /**
   * 1. calculate mid
   * 2. pop the top and recursively call the deleteMiddle , push the top back
   * 3. if current recursion round == mid , end the recursion.
   * O(n) = n
   *
   * @param stack
   */
  @Important
  static public void deleteMiddle(Stack<Integer> stack) {
    if (stack == null || stack.isEmpty()) {
      return;
    }
    int mid = (stack.size() / 2) + 1;
    deleteMiddle(stack, mid, stack.size());
  }

  static public void deleteMiddle(Stack<Integer> stack, int mid, int current) {
    int top = stack.pop();
    if (mid == current) {
      return;
    }
    deleteMiddle(stack, mid, current - 1);
    stack.push(top);
  }

  @Important
  public static int deleteBottomKElements(Stack<Integer> stack, int k) {
    if (stack == null || stack.isEmpty()) {
      return 0;
    }
    int top = stack.pop();
    int visitedCount = deleteBottomKElements(stack, k);
    if (k - visitedCount <= 0) {
      stack.push(top);
    }
    return 1 + visitedCount;
  }

  @Important
  public static int deleteTopKElements(Stack<Integer> stack, int k) {
    if (stack == null || stack.isEmpty()) {
      return 0;
    }
    int top = stack.pop();
    int visitedCount = deleteBottomKElements(stack, k);
    if (k - visitedCount > 0) {
      stack.push(top);
    }
    return 1 + visitedCount;
  }

  // stack sorting using temp stack
  // input: [34, 3, 31, 98, 92, 23]
  @Important
  static public void sortUsingTempStack(Stack<Integer> stack) {
    if (stack.isEmpty()) {
      return;
    }
    Stack<Integer> tempStack = new Stack<>();
    while (!stack.isEmpty()) {
      tempStack.push(stack.pop());
    }
    while (!tempStack.isEmpty()) {
      int curr = tempStack.pop();
      while (!stack.isEmpty() && curr > stack.peek()) {
        tempStack.push(stack.pop());
      }
      stack.push(curr);
    }
  }

  @Important
  static public void sort(Stack<Integer> stack) {
    if (stack.isEmpty()) {
      return;
    }
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
    if (str == null || str.isEmpty()) {
      return false;
    }
    char[] characters = str.toCharArray();
    int length = characters.length;
    Stack<Character> stack = new Stack<>();
    int i = 0;
    for (; i < length / 2; stack.push(characters[i]), i++) ;
    if (length % 2 != 0) {
      ++i; // odd length string, skip middle
    }
    for (; i < characters.length && characters[i] == stack.peek(); stack.pop(), i++) ;
    return stack.isEmpty();
  }

  /**
   * Input : 1 1 1 1 0 1 1 1 1 1
   * Output : 24
   * For {1, 1, 1, 1, 0, 1, 1, 1, 1, 1} all element are same except 0.
   * So only for zero their exist greater element
   * and for others it will be zero. for zero, on left 4th element is closest and greater than zero
   * and on right 6th element is closest and greater. so maximum
   * product will be 4*6 = 24.
   * <p>
   * Input : 5 4 3 4 5
   * Output : 8
   * For {5, 4, 3, 4, 5}, L[] = {0, 1, 2, 1, 0} and R[] = {0, 5, 4, 5, 0}
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
      while (!stack.isEmpty() && arr[i] >= arr[stack.peek()]) {
        stack.pop();
      }
      if (stack.isEmpty()) {
        left[i] = 0;
      } else {
        left[i] = stack.peek() + 1;
      }
      stack.push(i);
    }
    stack.clear();
    for (int i = arr.length - 1; i >= 0; i--) {
      while (!stack.isEmpty() && arr[i] >= arr[stack.peek()]) {
        stack.pop();
      }
      if (stack.isEmpty()) {
        right[i] = 0;
      } else {
        right[i] = stack.peek() + 1;
      }
      stack.push(i);
    }

    for (int i = 0; i <= arr.length - 1; i++) {
      int curr = left[i] * right[i];
      if (product < curr) {
        product = curr;
      }
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
    if (stack == null || stack.isEmpty()) {
      return;
    }
    T elem = stack.pop();
    printStack(stack);
    System.out.println(elem);
    stack.push(elem);
  }

  static public double toNumber(Stack<Integer> stack) {
    return toNumber(stack, 0);
  }

  private static double toNumber(Stack<Integer> stack, int i) {
    if (stack == null || stack.isEmpty()) {
      return 0;
    }
    int curr = stack.pop();
    double sum = (curr * Math.pow(10, i)) + toNumber(stack, i + 1);
    stack.push(curr);
    return sum;
  }

  /**
   * https://www.geeksforgeeks.org/merging-intervals/
   *
   * @param intervals
   * @return
   */
  @Hard
  @Important
  public static int[][] mergeIntervals(int[][] intervals) {
    if (intervals == null || intervals.length == 0 || intervals[0].length != 2) {
      return null;
    }
    Stack<int[]> stack = new Stack<>();
    for (int[] arr : intervals) {
      mergedInsert(stack, arr);
    }
    int n = stack.size();
    int[][] merged = new int[n][2];
    while (!stack.isEmpty()) {
      merged[--n] = stack.pop();
    }
    return merged;
  }

  private static void mergedInsert(Stack<int[]> stack, int[] curr) {
    if (stack.isEmpty() || stack.peek()[1] < curr[0]) {
      // current is non overlapping greater interval, simply add and proceed
      stack.push(curr);
      return;
    }
    int[] top = stack.pop();
    if (curr[1] < top[0]) {
      // current is non overlapping lesser interval, find its correct place
      mergedInsert(stack, curr);
    } else if ((top[0] <= curr[0] && curr[0] <= top[1])
        || (top[0] <= curr[1] && curr[1] <= top[1])
        || (curr[0] <= top[0] && top[0] <= curr[1])
        || (curr[0] <= top[1] && top[1] <= curr[1])) {
      // merge overlapping interval
      int x = Math.min(top[0], curr[0]);
      int y = Math.max(top[1], curr[1]);
      top = new int[] {x, y};
    }
    mergedInsert(stack, top);
  }

  /**
   * Input : stack = [4, 5, -2, -3, 11, 10, 5, 6, 20]
   * Output : Yes
   * Each of the pairs (4, 5), (-2, -3), (11, 10) and
   * (5, 6) consists of consecutive numbers.
   * <p>
   * Input : stack = [4, 6, 6, 7, 4, 3]
   * Output : No
   * (4, 6) are not consecutive.
   *
   * @param stack
   * @return
   */
  public static boolean checkPairWiseConsecutive(java.util.Stack<Integer> stack) {
    if (stack == null) {
      return false;
    }
    if (stack.size() % 2 == 1) {
      stack.pop();
    }
    while (!stack.isEmpty()) {
      Integer top1 = stack.pop();
      if (stack.isEmpty()) {
        break;
      }
      Integer top2 = stack.pop();
      if (Math.abs(top1 - top2) != 1) {
        return false;
      }
    }
    return true;
  }

  /**
   * Input : str[] = "1[b]"
   * Output : b
   * <p>
   * Input : str[] = "2[ab]"
   * Output : abab
   * <p>
   * Input : str[] = "2[a2[b]]"
   * Output : abbabb
   * <p>
   * Input : str[] = "3[b2[ca]]"
   * Output : bcacabcacabcaca
   *
   * @param str
   */
  @Important
  @Medium
  public static String decodeStringByCount(String str) {
    if (str == null || str.isEmpty()) {
      return null;
    }
    String result = "";
    Stack<Character> stack = new Stack<>();
    for (int i = str.length() - 1; i >= 0; --i) {
      char c = str.charAt(i);
      if (c == '[') {
        int number = Integer.parseInt(String.valueOf(str.charAt(--i)));
        String strToRepeat = "";
        String repeatedString = "";
        for (char top = stack.pop(); top != ']'; top = stack.pop()) {
          strToRepeat = strToRepeat + top;
        }
        for (int j = 0; j < number; j++) {
          repeatedString = repeatedString + strToRepeat;
        }
        for (int j = repeatedString.length() - 1; j >= 0; j--) {
          stack.push(repeatedString.charAt(j));
        }
      } else {
        stack.push(c);
      }
    }
    while (!stack.isEmpty()) {
      result = result + stack.pop();
    }
    return result;
  }

  /**
   * https://www.geeksforgeeks.org/form-minimum-number-from-given-sequence/
   * Input: D        Output: 21
   * Input: I        Output: 12
   * Input: DD       Output: 321
   * Input: II       Output: 123
   * Input: DIDI     Output: 21435
   * Input: IIDDD    Output: 126543
   * Input: DDIDDIID Output: 321654798
   */
  @Important
  @Medium
  public static String findMinimumNumberForGivenSequence(String seq) {
    if (seq == null || seq.isEmpty()) {
      return null;
    }

    String result = "";
    Stack<Integer> stack = new Stack<>();
    int n = 3;
    if (seq.startsWith("D")) {
      stack.push(2);
      stack.push(1);
    } else {
      stack.push(1);
      stack.push(2);
    }
    for (int i = 1; i < seq.length(); i++) {
      insertAtCorrectPosition(stack, seq, i, n);
      n++;
    }
    while (!stack.isEmpty()) {
      result = stack.pop() + result;
    }
    return result;
  }

  private static void insertAtCorrectPosition(Stack<Integer> stack, String seq, int i, int n) {
    if (stack.isEmpty()) {
      stack.push(n);
      return;
    }
    if (seq.charAt(i) == 'D' && n < stack.peek()) {
      stack.push(n);
      return;
    }
    if (seq.charAt(i) == 'I' && stack.peek() < n) {
      stack.push(n);
      return;
    }
    int top = stack.pop();
    insertAtCorrectPosition(stack, seq, i - 1, n);
    stack.push(top);
  }

  @Important
  @Medium
  public static int maxHistogramArea(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int[] nse = findNextGreaterOrSmallerElementIndexes(arr, false);
    int[] pse = findPrevGreaterOrSmallerElementIndexes(arr, false);
    int maxArea = 0;
    for (int i = 0; i < arr.length; i++) {
      int span = 1;
      if (pse[i] != -1 && nse[i] != -1) {
        span = nse[i] - pse[i] - 1;
      } else if (pse[i] != -1) {
        span = i - pse[i];
      } else if (nse[i] != -1) {
        span = nse[i] - i;
      }
      int currentArea = span * arr[i];
      maxArea = Math.max(maxArea, currentArea);
    }
    return maxArea;
  }

  @Important
  @Hard
  public static int minHistogramArea(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int[] nge = findNextGreaterOrSmallerElementIndexes(arr, true);
    int[] pge = findPrevGreaterOrSmallerElementIndexes(arr, true);
    int minArea = Integer.MAX_VALUE;
    for (int i = 0; i < arr.length; i++) {
      int span = 1;
      if (pge[i] != -1 && nge[i] != -1) {
        span = nge[i] - pge[i] - 1;
      } else if (pge[i] != -1) {
        span = i - pge[i];
      } else if (nge[i] != -1) {
        span = nge[i] - i;
      }
      int currentArea = span * arr[i];
      if (currentArea < minArea) {
        minArea = currentArea;
      }
    }
    return minArea;
  }

  /**
   * Input :
   * Pattern : ABC
   * Text : BABABCABCC
   *
   * @param str
   * @param pattern
   * @return
   */
  @Important
  @Medium
  public static int countPatternOccurences(String str, String pattern) {
    if (str == null || str.length() == 0 || pattern == null || pattern.length() == 0 ||
        pattern.length() > str.length()) {
      return 0;
    }
    int patternSize = pattern.length();

    int matchIndex = 0;
    String matchedString = "";
    String residueString = "";

    int occurences = 0;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == pattern.charAt(matchIndex)) {
        matchedString += str.charAt(i);
        matchIndex++;
        if (matchIndex == patternSize) {
          // pattern matched, remove it from string
          matchedString = "";
          matchIndex = 0;
          occurences++;
        }
      } else {
        matchIndex = 0;
        if (matchedString.length() > 0) {
          residueString += matchedString;
          matchedString = "";
          i--; // important
        } else {
          residueString += str.charAt(i);
        }
      }
    }
    if (occurences == 0) {
      return 0;
    }
    return occurences + countPatternOccurences(residueString, pattern);
  }

  /**
   * Input :
   * Pattern : ABC
   * Text : BABABCABCC
   *
   * @param str
   * @param pattern
   * @return
   */
  public static int countPatternOccurences1(String str, String pattern) {
    if (str == null || str.length() == 0 || pattern == null || pattern.length() == 0 ||
        pattern.length() > str.length()) {
      return 0;
    }

    int m = pattern.length();
    int n = str.length();
    int occurences = 0;
    String residueString = "";


    for (int i = 0; i < n; ) {
      int j = 0;
      for (; j < m && str.charAt(i + j) == pattern.charAt(j); j++) ;
      if (j == m) {
        // pattern found
        i = i + j; // move to j
        occurences++;
      } else if (j != 0) {
        // partial match
        for (int x = i; x < i + j; x++) {
          residueString += str.charAt(x);
        }
        i = i + j; // move to j
      } else {
        residueString += str.charAt(i);
        i++;
      }
    }

    if (occurences == 0) {
      return 0;
    }
    return occurences + countPatternOccurences1(residueString, pattern);
  }
}
