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

  public static void main(String[] args) {
    RandomPickWithWeight util = new RandomPickWithWeight(new int[] {1, 3});
    for (int i = 0; i < 8; i++) {
      System.out.println(util.pickIndex());
    }
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
    int insertPosition = getInsertPosition(random);
    return insertPosition == runningWeights.length ? insertPosition - 1 : insertPosition;
  }

  private int getInsertPosition(int target) {
    int low = 0;
    int high = runningWeights.length - 1;
    while (low <= high) {
      int mid = (low + high) >>> 1;
      if (runningWeights[mid] == target) {
        return mid;
      } else if (target < runningWeights[mid]) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return low + 1;
  }
}
