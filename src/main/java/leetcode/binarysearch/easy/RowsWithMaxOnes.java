package leetcode.binarysearch.easy;

public class RowsWithMaxOnes {
  public int[] rowAndMaximumOnes(int[][] mat) {
    int [] result = new int[2];
    for (int i = 0; i < mat.length; i++) {
      int lowerBound = lowerBoundOf1(mat[i]);
      int totalOnes = mat[0].length - lowerBound;
      if (totalOnes > result[1]) {
        result[0] = i;
        result[1] = totalOnes;
      }
    }
    return result;
  }

  // [0,0,1,1,1]
  private int lowerBoundOf1(int[] nums) {
    int low = 0;
    int high = nums.length - 1;
    while (low <= high) {
      int mid = (low + high) >>> 1;
      if (1 <= nums[mid]) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return low;
  }
}
