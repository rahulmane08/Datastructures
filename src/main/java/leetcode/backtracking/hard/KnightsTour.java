package leetcode.backtracking.hard;

import java.util.Arrays;

/**
 * Check if the knight can cover all tiles on the board.
 * <p>
 * https://leetcode.com/problems/the-knights-tour/description/
 * <p>
 * Time complexity = O(M x N)
 */
public class KnightsTour {

  private final int[][] moves = {{1, 2}, {1, -2}, {2, 1}, {2, -1}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};

  public static void main(String[] args) {
    KnightsTour util = new KnightsTour();
    System.out.println(Arrays.deepToString(util.tourOfKnight(5, 5, 0, 0)));
  }

  public int[][] tourOfKnight(int m, int n, int r, int c) {
    int rows = m;
    int cols = n;
    int row = r;
    int col = c;

    int[][] board = new int[rows][cols];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        board[i][j] = -1;
      }
    }
    compute(board, row, col, rows, cols, 0);
    return board;
  }

  boolean compute(int[][] board, int row, int col, int rows, int cols, int currentMoveNumber) {
    board[row][col] = currentMoveNumber;
    if (currentMoveNumber == rows * cols - 1) {
      return true;
    }

    for (int[] move : moves) {
      int r = row + move[0];
      int c = col + move[1];
      if (isValid(board, r, c, rows, cols)) {
        if (compute(board, r, c, rows, cols, currentMoveNumber + 1)) {
          return true;
        }
      }
    }
    board[row][col] = -1; // backtrack
    return false;
  }

  private boolean isValid(int[][] board, int row, int col, int rows, int cols) {
    return row >= 0 && row < rows && col >= 0 && col < cols && board[row][col] == -1;
  }
}
