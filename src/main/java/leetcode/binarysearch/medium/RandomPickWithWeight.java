package leetcode.binarysearch.medium;

import java.util.Random;

public class RandomPickWithWeight {

  private final int[] runningWeights;
  private final int min, max;

  public RandomPickWithWeight(int[] w) {
    this.runningWeights = new int[w.length];
    initializeRunningWeights(w);
    this.min = runningWeights[0];
    this.max = runningWeights[w.length - 1];
  }

  private void initializeRunningWeights(int[] w) {
    int sum = 0;
    for (int i = 0; i < w.length; i++) {
      sum += w[i];
      runningWeights[i] = sum;
    }
  }

  public int pickIndex() {
    int random = min + new Random().nextInt(max - min + 1);
    return getRandomIndex(random, 0, runningWeights.length - 1);
  }

  /**
   * [1, 3, 6, 10, 16]
   * 2
   * @param random
   * @param low
   * @param high
   * @return
   */
  private int getRandomIndex(int random, int low, int high) {
    if (low > high) {
      return -1;
    }
    int mid = (low + high) >>> 1;
    if (random < runningWeights[mid]) {
      int left = getRandomIndex(random, low, mid - 1);
      if (left != -1) {
        return left;
      }
    } else if (runningWeights[mid] < random){
      int right = getRandomIndex(random, mid + 1, high);
      if (right != -1) {
        return right;
      }
    }
    return mid;
  }

  public static void main(String[] args) {
    RandomPickWithWeight util = new RandomPickWithWeight(new int[] {1, 3});
    for (int i = 0; i < 8; i++) {
      System.out.println(util.pickIndex());
    }
  }
}
