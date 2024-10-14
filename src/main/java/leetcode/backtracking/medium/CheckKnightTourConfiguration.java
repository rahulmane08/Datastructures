package leetcode.backtracking.medium;

/**
 * https://leetcode.com/problems/check-knight-tour-configuration/description/
 */
public class CheckKnightTourConfiguration {
  private final int[][] moves = {{1, 2}, {1, -2}, {2, 1}, {2, -1}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};

  public static void main(String[] args) {
    int[][] grid = new int[][] {{0, 3, 6}, {5, 8, 1}, {2, 7, 4}};
    CheckKnightTourConfiguration util = new CheckKnightTourConfiguration();
    System.out.println(util.checkValidGrid(grid));
  }

  public boolean checkValidGrid(int[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;
    int startX = 0;
    int startY = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == 0) {
          startX = i;
          startY = j;
        }
      }
    }
    System.out.println(startX + "," + startY);
    return traverse(grid, startX, startY, rows, cols, 0);
  }

  private boolean traverse(int[][] grid, int row, int col, int rows, int cols, int moveNumber) {
    if (moveNumber == rows * cols - 1) {
      return true;
    }
    for (int[] move : moves) {
      int nextX = row + move[0];
      int nextY = col + move[1];
      if (isValid(grid, nextX, nextY, rows, cols, moveNumber + 1)) {
        if (traverse(grid, nextX, nextY, rows, cols, moveNumber + 1)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean isValid(int[][] grid, int row, int col, int rows, int cols, int moveNumber) {
    return row >= 0 && row < rows && col >= 0 && col < cols && grid[row][col] == moveNumber;
  }
}
