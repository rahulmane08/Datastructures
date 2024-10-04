package leetcode.matrix.medium;

import java.util.LinkedList;
import java.util.Queue;

public class NoOfIslands {
  int[][] moves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }
    int rows = grid.length;
    int cols = grid[0].length;
    int totalIslands = 0;
    Queue<Integer> bfs = new LinkedList<>();

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (isValid(grid, i, j, rows, cols)) {
          grid[i][j] = '0'; // mark visited
          totalIslands++;
          bfs.offer(i * cols + j);
          while (!bfs.isEmpty()) {
            Integer curr = bfs.poll();
            for (int[] move : moves) {
              int row = (curr / cols) + move[0];
              int col = (curr % cols) + move[1];
              if (isValid(grid, row, col, rows, cols)) {
                grid[row][col] = '0';
                bfs.offer(row * cols + col);
              }
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
        if (isValid(grid, i, j, rows, cols)) {
          dfs(grid, i, j, rows, cols);
          totalIslands++;
        }
      }
    }
    return totalIslands;
  }

  public void dfs(char[][] grid, int i, int j, int rows, int cols) {
    grid[i][j] = '0';
    for (int[] move : moves) {
      if (isValid(grid, i + move[0], j + move[1], rows, cols)) {
        dfs(grid, i + move[0], j + move[1], rows, cols);
      }
    }
  }

  boolean isValid(char[][] grid, int i, int j, int rows, int cols) {
    return i >= 0 && i < rows && j >= 0 && j < cols && grid[i][j] == '1';
  }

  public static void main(String[] args) {
    NoOfIslands util = new NoOfIslands();

    char[][] grid = {
        {'1', '1', '1', '1', '0'},
        {'1', '1', '0', '1', '0'},
        {'1', '1', '0', '0', '0'},
        {'0', '0', '0', '0', '0'}
    };

    System.out.println(util.numIslandsDfs(grid));

    grid = new char[][] {
        {'1', '1', '1', '1', '0'},
        {'1', '1', '0', '1', '0'},
        {'1', '1', '0', '0', '0'},
        {'0', '0', '0', '0', '0'}
    };
    System.out.println(util.numIslands(grid));

    grid = new char[][] {
        {'1', '1', '0', '0', '0'},
        {'1', '1', '0', '0', '0'},
        {'0', '0', '1', '0', '0'},
        {'0', '0', '0', '1', '1'}
    };
    System.out.println(util.numIslandsDfs(grid));
    grid = new char[][] {
        {'1', '1', '0', '0', '0'},
        {'1', '1', '0', '0', '0'},
        {'0', '0', '1', '0', '0'},
        {'0', '0', '0', '1', '1'}
    };
    System.out.println(util.numIslands(grid));

    grid = new char[][] {
        {'1', '1', '1'},
        {'0', '1', '0'},
        {'1', '1', '1'}
    };
    System.out.println(util.numIslandsDfs(grid));
    grid = new char[][] {
        {'1', '1', '1'},
        {'0', '1', '0'},
        {'1', '1', '1'}
    };
    System.out.println(util.numIslands(grid));
  }
}
