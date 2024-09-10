package leetcode.matrix.medium;

public class Search2DMatrix {
  public boolean searchMatrix(int[][] matrix, int target) {
    int m = matrix.length; // rows
    if (m == 0) {
      return false;
    }
    int n = matrix[0].length; // columns
    int left = 0;
    int right = m * n - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;
      int midRow = mid / n;
      int midCol = mid % n;
      int midElem = matrix[midRow][midCol];
      if (midElem == target) {
        return true;
      } else if (target < midElem) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return false;
  }
}
