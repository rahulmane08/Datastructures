package leetcode.graph.hard;

/**
 *
 */
public class LongestIncreasingPathInMatrix {

  int[][] moves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

  public static void main(String[] args) {
    int[][] matrix = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
    LongestIncreasingPathInMatrix util = new LongestIncreasingPathInMatrix();
    System.out.println(util.longestIncreasingPath(matrix));
  }

  public int longestIncreasingPath(int[][] matrix) {
    int rows = matrix.length;
    int cols = matrix[0].length;
    int[][] dp = new int[rows][cols];

    int maxLength = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        maxLength = Math.max(maxLength, compute(matrix, dp, i, j, rows, cols));
      }
    }
    return maxLength;
  }

  int compute(int[][] matrix, int[][] dp, int row, int col, int rows, int cols) {
    if (dp[row][col] == 0) {
      int curr = matrix[row][col];
      int maxLength = 0;
      for (int[] move : moves) {
        int neighborX = row + move[0];
        int neighborY = col + move[1];
        if (isValid(matrix, curr, neighborX, neighborY, rows, cols)) {
          maxLength = Math.max(maxLength, compute(matrix, dp, neighborX, neighborY, rows, cols));
        }
      }
      dp[row][col] = 1 + maxLength;
    }
    return dp[row][col];
  }

  boolean isValid(int[][] matrix, int curr, int row, int col, int rows, int cols) {
    return 0 <= row && row < rows && 0 <= col && col < cols && matrix[row][col] > curr;
  }
}
