package leetcode.graph.medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 * <p>
 * The distance between two adjacent cells is 1.
 */
public class ZeroOneMatrix {
  int[][] moves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

  public static void main(String[] args) {
    int[][] matrix = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}, {1, 1, 1}};
    ZeroOneMatrix util = new ZeroOneMatrix();
    System.out.println(Arrays.deepToString(util.updateMatrix(matrix)));
  }

  public int[][] updateMatrix(int[][] mat) {
    int rows = mat.length;
    int cols = mat[0].length;
    int[][] result = new int[rows][cols];
    boolean[][] visited = new boolean[rows][cols];

    Queue<int[]> bfs = new LinkedList<>();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (mat[i][j] == 0) { // gather all 0's to the bfs, mark them visited so they never get picked up again.
          bfs.offer(new int[] {i, j, 0});
          visited[i][j] = true;
        }
      }
    }

    while (!bfs.isEmpty()) {
      int[] curr = bfs.poll();
      int row = curr[0];
      int col = curr[1];
      int dist = curr[2];
      result[row][col] = dist;

      for (int[] move : moves) {
        int neighborX = row + move[0];
        int neighborY = col + move[1];
        if (isValid(mat, visited, neighborX, neighborY, rows, cols)) {
          // get the neighbor 1's
          visited[neighborX][neighborY] = true;
          bfs.offer(new int[] {neighborX, neighborY, dist + 1});
        }
      }
    }
    return result;
  }

  boolean isValid(int[][] mat, boolean[][] visited, int row, int col, int rows, int cols) {
    return 0 <= row && row < rows && 0 <= col && col < cols && mat[row][col] == 1 && !visited[row][col];
  }
}
