package leetcode.matrix.medium;

import java.util.Arrays;

public class SpiralMatrix2 {
  public int[][] generateMatrix(int n) {
    int[][] matrix = new int[n][n];
    int x = 0;
    int y = 0;
    int dx = 0;
    int dy = 1;

    for (int i = 1; i <= n * n; i++) {
      matrix[x][y] = i;
      if (isInvalid(matrix, x + dx, y + dy, n)) {
        int temp = dy;
        dy = -dx;
        dx = temp;
      }
      x += dx;
      y += dy;
    }

    return matrix;
  }

  boolean isInvalid(int[][] matrix, int row, int col, int n) {
    return (row < 0 || row == n) || (col < 0 || col == n) || matrix[row][col] != 0;
  }

  public static void main(String[] args) {
    SpiralMatrix2 util = new SpiralMatrix2();
    System.out.println(Arrays.deepToString(util.generateMatrix(3)));
  }
}
