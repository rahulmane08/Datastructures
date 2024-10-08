package leetcode.backtracking.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Find all paths the rat can use to exit the maze.
 * <p>
 * Time Complexity: O(4^(m*n)), because on every cell we have to try 3 different directions ,
 * as we will not check for the cell from which we have visited in the last move.
 * Auxiliary Space: O(m*n), Maximum Depth of the recursion tree(auxiliary space).
 * <p>
 * T(n) = 4 T(n-1) + 1
 * a = 4, b = 1.
 * here n = MxN
 * Case 1.3 = 4 ^ (MN)
 */
public class RatInMaze {

  private Map<String, int[]> moves = new HashMap<>();

  public RatInMaze() {
    moves.put("R", new int[] {0, 1});
    moves.put("L", new int[] {0, -1});
    moves.put("D", new int[] {1, 0});
    moves.put("U", new int[] {-1, 0});
  }

  public List<String> findAllPaths(int[][] maze) {
    int rows = maze.length;
    int cols = maze[0].length;
    int[][] visited = new int[rows][cols];
    List<String> paths = new ArrayList<>();
    find(maze, 0, 0, rows, cols, "", paths, visited);
    return paths;
  }

  void find(int[][] maze, int row, int col, int rows, int cols, String path, List<String> paths, int[][] visited) {
    if (row == rows - 1 && col == cols - 1) {
      paths.add(path);
      return;
    }
    // mark the current tile as visited.
    visited[row][col] = 1;
    // Do the DFS in all valid directions.
    for (String move : moves.keySet()) {
      int[] directions = moves.get(move);
      int x = row + directions[0];
      int y = col + directions[1];
      if (isValid(maze, x, y, rows, cols, visited)) {
        find(maze, x, y, rows, cols, path + move, paths, visited);
      }
    }
    // mark the current tile as unvisited , backtrack, so that it can be considered again in another valid path.
    visited[row][col] = 0;
  }

  private boolean isValid(int[][] maze, int row, int col, int rows, int cols, int[][] visited) {
    return row >= 0 && row != rows && col >= 0 && col != cols && maze[row][col] == 1 && visited[row][col] != 1;
  }

  public static void main(String[] args) {
    int[][] maze = {
        {1, 0, 0},
        {1, 1, 1},
        {0, 1, 1}
    };
    RatInMaze util = new RatInMaze();
    System.out.println(util.findAllPaths(maze));
  }
}
