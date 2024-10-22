package leetcode.design.medium;

import java.util.Random;

public class CustomStack {
  private int[] elements;
  private final int capacity;
  int top = -1;

  public CustomStack(int maxSize) {
    if (maxSize < 1) {
      throw new IllegalArgumentException("size can be less than 1");
    }
    this.capacity = maxSize;
    this.elements = new int[capacity];
  }

  public void push(int x) {
    if (top + 1 == capacity) {
      return;
    }
    elements[++top] = x;
  }

  public int pop() {
    if (top == -1) {
      return -1;
    }
    return elements[top--];
  }

  public void increment(int k, int val) {
    for (int i = 0; i < k && i < top + 1; i++) {
      elements[i] += val;
    }
  }

  /**
   * ["CustomStack","push","push","pop","push","push","push","increment","increment","pop","pop","pop","pop"]
   * [[3],[1],[2],[],[2],[3],[4],[5,100],[2,100],[],[],[],[]]
   * @param args
   */
  public static void main(String[] args) {
    CustomStack stack = new CustomStack(3);
    stack.push(1);
    stack.push(2);
    System.out.println(stack.pop());
    stack.push(2);
    stack.push(3);
    stack.push(4);
    stack.increment(5, 100);
    stack.increment(2, 100);
  }
}
