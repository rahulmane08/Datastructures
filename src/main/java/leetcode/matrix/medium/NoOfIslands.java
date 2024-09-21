package leetcode.matrix.medium;

import java.util.LinkedList;
import java.util.Queue;

public class NoOfIslands {
  int[][] moves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }
    int m = grid.length;
    int n = grid[0].length;
    int totalIslands = 0;
    Queue<Integer> bfs = new LinkedList<>();

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '1') {
          grid[i][j] = '0'; // mark visited
          totalIslands++;
          bfs.offer(i * n + j);
          while (!bfs.isEmpty()) {
            Integer curr = bfs.poll();
            int currI = curr / n;
            int currJ = curr % n;
            if (currI > 0 && grid[currI - 1][currJ] == '1') {
              bfs.offer((currI - 1) * n + currJ);
              grid[currI - 1][currJ] = '0';
            }

            if (currI < m - 1 && grid[currI + 1][currJ] == '1') {
              bfs.offer((currI + 1) * n + currJ);
              grid[currI + 1][currJ] = '0';
            }

            if (currJ > 0 && grid[currI][currJ - 1] == '1') {
              bfs.offer(currI * n + currJ - 1);
              grid[currI][currJ - 1] = '0';
            }

            if (currJ < n - 1 && grid[currI][currJ + 1] == '1') {
              bfs.offer(currI * n + currJ + 1);
              grid[currI][currJ + 1] = '0';
            }
          }
        }
      }
    }
    return totalIslands;
  }

  public int numIslandsDfs(char[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;
    int totalIslands = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == '1') {
          dfs(grid, i, j, rows, cols);
          totalIslands++;
        }
      }
    }
    return totalIslands;
  }

  public void dfs(char[][] grid, int i, int j, int rows, int cols) {
    if (!isValid(grid, i, j, rows, cols)) {
      return;
    }
    grid[i][j] = '0';
    for (int [] move : moves) {
      dfs(grid, i + move[0], j + move[1], rows, cols);
    }
  }

  boolean isValid(char[][] grid, int i, int j, int rows, int cols) {
    return i >= 0 && i < rows && j >= 0 && j < cols && grid[i][j] == '1';
  }

  public static void main(String[] args) {
    char[][] grid = {
        {'1', '1', '1', '1', '0'},
        {'1', '1', '0', '1', '0'},
        {'1', '1', '0', '0', '0'},
        {'0', '0', '0', '0', '0'}
    };

    NoOfIslands util = new NoOfIslands();
    System.out.println(util.numIslandsDfs(grid));

    grid = new char[][] {
        {'1', '1', '0', '0', '0'},
        {'1', '1', '0', '0', '0'},
        {'0', '0', '1', '0', '0'},
        {'0', '0', '0', '1', '1'}
    };
    System.out.println(util.numIslandsDfs(grid));

    grid = new char[][] {{'1', '1', '1'}, {'0', '1', '0'}, {'1', '1', '1'}};
    System.out.println(util.numIslandsDfs(grid));
  }
}
