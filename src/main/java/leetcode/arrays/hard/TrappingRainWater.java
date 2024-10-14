package leetcode.arrays.hard;

public class TrappingRainWater {
  public static void main(String[] args) {
    TrappingRainWater util = new TrappingRainWater();
    System.out.println(util.trap(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
  }

  public int trap(int[] arr) {
    if (arr == null) {
      return 0;
    }

    int n = arr.length;
    int[] maxWaterCapacity = new int[n];
    int max = 0;
    for (int i = 0; i < n; i++) {
      max = Math.max(max, arr[i]);
      maxWaterCapacity[i] = max;
    }

    max = 0;
    for (int i = n - 1; i >= 0; i--) {
      max = Math.max(max, arr[i]);
      maxWaterCapacity[i] = Math.min(max, maxWaterCapacity[i]);
    }

    int totalCapacity = 0;
    for (int i = 0; i < n; i++) {
      totalCapacity += (maxWaterCapacity[i] - arr[i]);
    }
    return totalCapacity;
  }
}
