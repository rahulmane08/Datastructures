package leetcode.graph.medium;

import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 0, 0, 0
 * 0, 1, 1
 * 0, 0, 0
 * <p>
 * h = 1
 */
public class SafeWalkthruGrid {
  private final int[][] moves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

  private static String getKey(int row, int col, int health) {
    String key = row + "-" + col + "-" + health;
    return key;
  }

  public static void main(String[] args) {
    List<List<Integer>> grid = asList(asList(0, 0, 0), asList(1, 1, 1), asList(0, 0, 0));
    SafeWalkthruGrid util = new SafeWalkthruGrid();
    System.out.println(util.findSafeWalk(grid, 1));
  }

  public boolean findSafeWalk(List<List<Integer>> grid, int health) {
    int rows = grid.size();
    int cols = grid.get(0).size();
    int[][] board = new int[rows][cols];
    boolean[][] visited = new boolean[rows][cols];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        board[i][j] = grid.get(i).get(j);
      }
    }
    return isPossibleToReachEnd(board, health - board[0][0], 0, 0, rows, cols, visited, new HashMap<>());
  }

  private boolean isPossibleToReachEnd(int[][] board,
                                       int health,
                                       int row, int col,
                                       int rows, int cols,
                                       boolean[][] visited,
                                       Map<String, Boolean> cache) {
    String key = getKey(row, col, health);
    if (!cache.containsKey(key)) {
      visited[row][col] = true;
      if (row == rows - 1 && col == cols - 1) {
        cache.put(key, true);
        return true;
      }
      for (int[] move : moves) {
        int nextX = row + move[0];
        int nextY = col + move[1];
        if (isValid(nextX, nextY, rows, cols) && !visited[nextX][nextY] && health - board[nextX][nextY] > 0) {
          if (isPossibleToReachEnd(board, health - board[nextX][nextY], nextX, nextY, rows, cols, visited, cache)) {
            cache.put(key, true);
            return true;
          }
        }
      }
      visited[row][col] = false;
      cache.put(key, false);
    }
    return cache.get(key);
  }

  private boolean isValid(int row,
                          int col,
                          int rows,
                          int cols) {
    return row >= 0 && row < rows && col >= 0 && col < cols;
  }
}
