package leetcode.matrix.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Case 1: array empty, return 0
 * Case 2: array has no oranges, return 0
 * Case 3: array has all fresh oranges return -1
 * Case 4: array has rotten oranges but couldnt rot all, return -1
 * Case 5: array has rotten oranges, could rot all, return time.
 */
public class RottingOranges {

  public static void main(String[] args) {
    RottingOranges util = new RottingOranges();
    System.out.println(util.orangesRottingBfs(new int[][] {{0}}));
  }

  /**
   * T(n) = m*n
   *
   * @param grid
   * @return
   */
  public int orangesRottingBfs(int[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;
    Queue<int[]> queue = new LinkedList<>();
    int totalMinutes = 0;
    int freshOranges = 0;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == 2) {
          queue.offer(new int[] {i, j, 0});
        } else if (grid[i][j] == 1) {
          freshOranges++;
        }
      }
    }

    int[][] directions = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    while (!queue.isEmpty()) {
      int[] rottenOrange = queue.poll();
      int x = rottenOrange[0];
      int y = rottenOrange[1];
      int minute = rottenOrange[2];
      totalMinutes = minute;

      for (int[] direction : directions) {
        int neighborX = x + direction[0];
        int neighborY = y + direction[1];
        if (neighborX > -1 && neighborX < rows && neighborY > -1 && neighborY < cols &&
            grid[neighborX][neighborY] == 1) {
          grid[neighborX][neighborY] = 2; // rot orange
          queue.offer(new int[] {neighborX, neighborY, minute + 1});
          freshOranges--;
        }
      }
    }

    return freshOranges == 0 ? totalMinutes : -1;
  }
}
