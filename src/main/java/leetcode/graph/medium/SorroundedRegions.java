package leetcode.graph.medium;

import java.util.LinkedList;
import java.util.Queue;

public class SorroundedRegions {
  int[][] moves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

  public void solve(char[][] board) {
    int rows = board.length;
    int cols = board[0].length;
    boolean[][] visited = new boolean[rows][cols];
    Queue<int[]> invalidBfs = new LinkedList<>();
    Queue<int[]> validBfs = new LinkedList<>();

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (board[i][j] == 'O' && isBoundary(i, j, rows, cols)) {
          invalidBfs.offer(new int[] {i, j});
          visited[i][j] = true;
        } else if (board[i][j] == 'X') {
          validBfs.offer(new int[] {i, j});
          visited[i][j] = true;
        }
      }
    }

    while (!invalidBfs.isEmpty()) {
      int[] curr = invalidBfs.poll();
      for (int[] move : moves) {
        int neighborX = curr[0] + move[0];
        int neighborY = curr[1] + move[1];
        if (isValid(board, visited, neighborX, neighborY, rows, cols) && board[neighborX][neighborY] == 'O') {
          visited[neighborX][neighborY] = true;
          invalidBfs.offer(new int[] {neighborX, neighborY});
        }
      }
    }

    while (!validBfs.isEmpty()) {
      int[] curr = validBfs.poll();
      for (int[] move : moves) {
        int neighborX = curr[0] + move[0];
        int neighborY = curr[1] + move[1];
        if (isValid(board, visited, neighborX, neighborY, rows, cols) && board[neighborX][neighborY] == 'O') {
          board[neighborX][neighborY] = 'X';
          visited[neighborX][neighborY] = true;
          invalidBfs.offer(new int[] {neighborX, neighborY});
        }
      }
    }
  }

  private boolean isBoundary(int row, int col, int rows, int cols) {
    return row == 0 || row == rows - 1 || col == 0 || col == cols - 1;
  }

  boolean isValid(char[][] board, boolean[][] visited, int row, int col, int rows, int cols) {
    return 0 <= row && row < rows && 0 <= col && col < cols && visited[row][col] == false;
  }

  public static void main(String[] args) {

  }
}
