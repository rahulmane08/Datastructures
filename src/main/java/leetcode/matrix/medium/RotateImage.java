package leetcode.matrix.medium;

import java.util.Arrays;

public class RotateImage {
  public static void main(String[] args) {
    int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    RotateImage util = new RotateImage();
    util.rotate1(matrix);
    System.out.println(Arrays.deepToString(matrix));
  }

  public void rotate(int[][] matrix) {
    int N = matrix.length;
    for (int x = 0; x < N / 2; x++) {
      for (int y = x; y < N - 1 - x; y++) {
        int temp = matrix[x][y];
        matrix[x][y] = matrix[N - 1 - y][x];
        matrix[N - 1 - y][x] = matrix[N - 1 - x][N - 1 - y];
        matrix[N - 1 - x][N - 1 - y] = matrix[y][N - 1 - x];
        matrix[y][N - 1 - x] = temp;
      }
    }
  }

  public void rotate1(int[][] matrix) {
    int N = matrix.length;
    // calculate transpose
    for (int i = 0; i < N; i++) {
      for (int j = 0; j <= i; j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
      }
    }

    for (int i = 0; i < N; i++) {
      for (int a = 0, b = N - 1; a < b; a++, b--) {
        int temp = matrix[i][a];
        matrix[i][a] = matrix[i][b];
        matrix[i][b] = temp;
      }
    }
  }
}
