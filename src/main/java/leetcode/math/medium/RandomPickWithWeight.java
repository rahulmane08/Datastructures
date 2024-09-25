package leetcode.math.medium;

import java.util.Arrays;
import java.util.Comparator;

public class RandomPickWithWeight {

  private final int[][] indexProbalities;

  private final int[] nums;

  private int currentPointer = 0;

  private int gcd;

  public RandomPickWithWeight(int[] w) {
    this.nums = w;
    this.indexProbalities = new int[w.length][2];
    computeGcd();
    computeProbability();
  }

  private void computeGcd() {
    int gcd = nums[0];
    for (int i = 1; i < nums.length; i++) {
      gcd = gcd(nums[i], gcd);
      if (gcd == 1) {
        break;
      }
    }
    this.gcd = gcd;
  }

  private void computeProbability() {
    int gcd = nums[0];

    for (int i = 0; i < nums.length; i++) { //O(n)
      indexProbalities[i][0] = i;
      indexProbalities[i][1] = nums[i] / gcd;
    }
    Comparator<int[]> comparator = Comparator.comparingInt(a -> a[1]);
    Arrays.sort(indexProbalities, comparator.reversed());
  }

  public int pickIndex() {
    int[] front = indexProbalities[currentPointer];
    int randomIndex = front[0];
    if (--front[1] == 0) {
      front[1] = nums[front[0]] / gcd;
      currentPointer = (currentPointer + 1) % nums.length;
    }
    return randomIndex;
  }

  private int gcd(int a, int b) {
    if (a == 1) {
      return 1;
    }
    if (a == 0) {
      return b;
    }
    if (a > b) {
      return gcd(a % b, b);
    }
    return gcd(b % a, a);
  }

  public static void main(String[] args) {
    RandomPickWithWeight util = new RandomPickWithWeight(new int[] {5, 5});
    for (int i = 0; i < 8; i++) {
      System.out.println(util.pickIndex());
    }
  }
}
