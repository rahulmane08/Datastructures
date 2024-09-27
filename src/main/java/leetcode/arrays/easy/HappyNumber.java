package leetcode.arrays.easy;

public class HappyNumber {
  public boolean isHappy(int n) {
    int slow = squaredDigitSum(n);
    if (slow == 1) {
      return true;
    }
    int fast = squaredDigitSum(slow);
    if (fast == 1) {
      return true;
    }

    while (slow != fast) {
      slow = squaredDigitSum(slow);
      fast = squaredDigitSum(squaredDigitSum(fast));
    }
    return slow == 1;
  }

  private int squaredDigitSum(int n) {
    int sum = 0;
    while (n != 0) {
      int lsd = n % 10;
      sum += lsd * lsd;
      n = n / 10;
    }
    return sum;
  }
}
