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

  public boolean searchRecursively(int[][] matrix, int target) {
    int rows = matrix.length;
    ;
    int cols = matrix[0].length; // columns

    int left = 0;
    int right = rows * cols - 1;
    return search(matrix, left, right, target, cols);
  }

  boolean search(int[][] matrix, int left, int right, int target, int cols) {
    if (left > right) {
      return false;
    }
    int mid = (left + right) >>> 1;
    int i = mid / cols;
    int j = mid % cols;
    if (matrix[i][j] == target) {
      return true;
    } else if (target < matrix[i][j]) {
      return search(matrix, left, mid - 1, target, cols);
    } else {
      return search(matrix, mid + 1, right, target, cols);
    }
  }

  public static void main(String[] args) {
    int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    Search2DMatrix util = new Search2DMatrix();
    for (int i = 0; i < 11; i++) {
      System.out.println(util.searchRecursively(matrix, i));
    }
  }
}