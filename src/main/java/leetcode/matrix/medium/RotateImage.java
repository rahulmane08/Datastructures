package leetcode.matrix.medium;

public class RotateImage {
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
}
