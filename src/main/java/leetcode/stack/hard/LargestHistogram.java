package leetcode.stack.hard;

import java.util.Stack;

public class LargestHistogram {
  public int largestRectangleArea(int[] heights) {
    if (heights == null || heights.length == 0) {
      return 0;
    }
    int[] nse = findNextSmallerElementIndexes(heights);
    int[] pse = findPrevSmallerElementIndexes(heights);
    int maxArea = 0;
    for (int i = 0; i < heights.length; i++) {
      int span = 1;
      if (pse[i] != -1 && nse[i] != -1) {
        span = nse[i] - pse[i] - 1;
      } else if (pse[i] == -1 && nse[i] == -1) {
        span = heights.length;
      } else if (nse[i] != -1) {
        span = heights.length - i;
      } else {
        span = i - pse[i];
      }
      int currentArea = span * heights[i];
      maxArea = Math.max(maxArea, currentArea);
    }
    return maxArea;
  }

  int[] findNextSmallerElementIndexes(int[] arr) {
    int n = arr.length;
    int[] ngse = new int[n];
    Stack<Integer> s = new Stack<>();
    for (int i = n - 1; i >= 0; i--) {
      ngse[i] = -1;
      for (; !s.isEmpty() && arr[s.peek()] >= arr[i]; s.pop()) ;
      if (!s.isEmpty()) {
        ngse[i] = s.peek();
      }
      s.push(i);
    }
    return ngse;
  }

  int[] findPrevSmallerElementIndexes(int[] arr) {
    int n = arr.length;
    int[] pgse = new int[n];
    Stack<Integer> s = new Stack<>();
    for (int i = 0; i < n; i++) {
      pgse[i] = -1;
      for (; !s.isEmpty() && arr[s.peek()] >= arr[i]; s.pop()) ;
      if (!s.isEmpty()) {
        pgse[i] = s.peek();
      }
      s.push(i);
    }
    return pgse;
  }

  public static void main(String[] args) {
    LargestHistogram util = new LargestHistogram();
    System.out.println(util.largestRectangleArea(new int[] {2, 3}));
  }
}
