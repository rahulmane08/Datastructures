package leetcode.graph.medium;

import java.util.Arrays;

public class ZeroOneMatrix {
  int[][] moves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

  public int[][] updateMatrix(int[][] mat) {
    int rows = mat.length;
    int cols = mat[0].length;
    int[][] dp = new int[rows][cols];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (mat[i][j] != 0) {
          dp[i][j] = -1;
        }
      }
    }
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (mat[i][j] == 1) {
          dfs(mat, dp, i, j, rows, cols);
        }
      }
    }
    return dp;
  }

  private int dfs(int[][] matrix, int[][] dp, int row, int col, int rows, int cols) {
    if (dp[row][col] == 0) {
      return 0;
    }
    if (dp[row][col] == -1) {
      dp[row][col] = -2; // ongoing dfs.
      int distance = rows * cols + 1;
      for (int[] move : moves) {
        int nextRow = row + move[0];
        int nextCol = col + move[1];
        if (isValid(nextRow, nextCol, rows, cols) && dp[nextRow][nextCol] != -2) {
          distance = Math.min(distance, dfs(matrix, dp, nextRow, nextCol, rows, cols));
        }
      }
      dp[row][col] = 1 + distance;
    }
    return dp[row][col];
  }

  boolean isValid(int row, int col, int rows, int cols) {
    return 0 <= row && row < rows && 0 <= col && col < cols;
  }

  public static void main(String[] args) {
    int[][] matrix = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}, {1, 1, 1}};
    ZeroOneMatrix util = new ZeroOneMatrix();
    System.out.println(Arrays.deepToString(util.updateMatrix(matrix)));
  }
}
