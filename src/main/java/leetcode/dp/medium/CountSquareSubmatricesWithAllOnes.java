package leetcode.dp.medium;

public class CountSquareSubmatricesWithAllOnes {

  private final int[][] moves = {{0, 1}, {1, 0}, {1, 1}};

  public static void main(String[] args) {
    CountSquareSubmatricesWithAllOnes util = new CountSquareSubmatricesWithAllOnes();
    int[][] matrix = {
        {1, 1},
        {1, 1}
    };
    System.out.println(util.countSquares(matrix));

    matrix = new int[][] {
        {0, 1, 1, 1},
        {1, 1, 1, 1},
        {0, 1, 1, 1}
    };
    System.out.println(util.countSquares(matrix));

    matrix = new int[][] {
        {1, 0, 1},
        {1, 1, 0},
        {1, 1, 0}
    };
    System.out.println(util.countSquares(matrix));
  }

  /**
   * matrix =
   * {1, 1},
   * {1, 1}
   *
   * in this case we do a dfs starting from 0,0 in 3 directions.
   * for (1,1) -> all 3 paths give 0, so count = 1, dp(1,1) = 1
   * for (0,1) -> gives 0, 0, 1 for 3 paths, min = 0, dp(0,1) = 1
   * for (1,0) -> gives 1, 0, 0 for 3 paths, min = 0, dp(1,0) = 1
   * for(0,0) -> gives 1,1,1 for 3 paths, min = 1, dp(0,0) = 2
   * @param matrix
   * @return
   */

  public int countSquares(int[][] matrix) {
    int rows = matrix.length;
    int cols = matrix[0].length;
    int[][] dp = new int[rows][cols];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        dp[i][j] = -1;
      }
    }

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (matrix[i][j] == 1) {
          topDown(matrix, i, j, rows, cols, dp);
        }
      }
    }

    int count = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (dp[i][j] != -1) {
          count += dp[i][j];
        }
      }
    }
    return count;
  }

  private int topDown(int[][] matrix, int i, int j, int rows, int cols, int[][] dp) {
    if (!isValid(matrix, i, j, rows, cols)) {
      return 0;
    }

    if (dp[i][j] == -1) {
      int count = rows * cols;
      for (int[] move : moves) {
        count = Math.min(count, topDown(matrix, i + move[0], j + move[1], rows, cols, dp));
      }
      dp[i][j] = 1 + count;
    }
    return dp[i][j];
  }

  private boolean isValid(int[][] matrix, int i, int j, int rows, int cols) {
    return i < rows && j < cols && matrix[i][j] == 1;
  }
}
