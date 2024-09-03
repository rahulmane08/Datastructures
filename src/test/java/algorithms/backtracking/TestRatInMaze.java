package algorithms.backtracking;

import static algorithms.backtracking.hard.RatInMaze.solve;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestRatInMaze {

  @Test
  public void test() {
    int[][] maze = new int[][] {
        {1, 0, 0, 0},
        {1, 1, 0, 1},
        {0, 1, 0, 0},
        {1, 1, 1, 1}};
    assertTrue(solve(maze));
    maze[3][1] = 0;
    assertFalse(solve(maze));
  }
}
