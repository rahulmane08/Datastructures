package leetcode.backtracking.hard;

import java.util.ArrayList;
import java.util.List;

public class NQueens {

  public static void main(String[] args) {
    NQueens util = new NQueens();
    System.out.println(util.solveNQueens(4));
  }

  public List<List<String>> solveNQueens(int n) {
    List<List<String>> results = new ArrayList<>();
    int[][] board = new int[n][n];
    for (int col = 0; col < n; col++) {
      compute(board, 0, col, n, results);
    }
    return results;
  }

  void compute(int[][] board, int row, int col, int n, List<List<String>> results) {
    board[row][col] = 1;
    if (row == n - 1) {
      results.add(toResultString(board, n));
    } else {
      for (int j = 0; j < n; j++) {
        if (isValid(board, row + 1, j, n)) {
          compute(board, row + 1, j, n, results);
        }
      }
    }
    board[row][col] = 0;
  }

  private List<String> toResultString(int[][] board, int n) {
    List<String> result = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      StringBuilder row = new StringBuilder();
      for (int j = 0; j < n; j++) {
        if (board[i][j] == 1) {
          row.append("Q");
        } else {
          row.append(".");
        }
      }
      result.add(row.toString());
    }
    return result;
  }

  /**
   * checks queen is NOT under attack.
   *
   * @return
   */
  private boolean isValid(int[][] board, int row, int col, int n) {
    if (row < 0 || row == n || col < 0 || col == n) {
      return false;
    }

    // horrizontal
    // down
    for (int i = row; i < n; i++) {
      if (board[i][col] == 1) {
        return false;
      }
    }

    // up
    for (int i = row; i > -1; i--) {
      if (board[i][col] == 1) {
        return false;
      }
    }

    // vertical
    // right
    for (int j = col; j < n; j++) {
      if (board[row][j] == 1) {
        return false;
      }
    }

    // left
    for (int j = col; j > -1; j--) {
      if (board[row][j] == 1) {
        return false;
      }
    }

    // forward diagonal
    // down
    for (int i = row, j = col; i < n && j < n; i++, j++) {
      if (board[i][j] == 1) {
        return false;
      }
    }

    // up
    for (int i = row, j = col; i > -1 && j > -1; i--, j--) {
      if (board[i][j] == 1) {
        return false;
      }
    }

    // backward diagonal
    // down
    for (int i = row, j = col; i < n && j > -1; i++, j--) {
      if (board[i][j] == 1) {
        return false;
      }
    }

    // up
    for (int i = row, j = col; i > -1 && j < n; i--, j++) {
      if (board[i][j] == 1) {
        return false;
      }
    }

    return true;
  }
}
