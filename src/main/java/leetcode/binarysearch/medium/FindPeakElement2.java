package leetcode.binarysearch.medium;

public class FindPeakElement2 {
  public int[] findPeakGrid(int[][] mat) {
    int rows = mat.length;
    int cols = mat[0].length;
    int low = 0;
    int high = cols - 1;
    while (low <= high) {
      int mid = (low + high) >>> 1;
    }
    return null;
  }

  private boolean isPeakElement(int [][] mat, int cols, int row, int col) {
    int currentElem = mat[row][col];
    if ((col - 1 == 0 || mat[row][col - 1] < currentElem) && (col + 1 == cols || mat[row][col + 1] < currentElem)) {
      return true;
    }
    return false;
  }

  private int findMaxColIndex(int[][] mat, int rows, int cols, int row) {
    int maxColIndex = 0;
    int maxElem = mat[row][0];
    for (int i = 0; i < cols; i++) {
      if (mat[row][i] > maxElem) {
        maxColIndex = i;
        maxElem = mat[row][i];
      }
    }
    return maxColIndex;
  }
}
