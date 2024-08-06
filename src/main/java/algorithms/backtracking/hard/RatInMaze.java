package algorithms.backtracking.hard;

public class RatInMaze {

    public static boolean solve(int[][] maze) {
        if (maze == null) {
            return false;
        }

        return solve(maze, 0, 0, maze.length, maze[0].length);
    }

    private static boolean solve(int[][] maze, int x, int y, int M, int N) {
        // mouse reached destination
        if (x == M - 1 && y == N - 1 && maze[x][y] == 1) {
            return true;
        }

        if (isValid(x, y, M, N) && maze[x][y] == 1) {
            // proceed only if current cell can be occupied by mouse.
            return solve(maze, x + 1, y, M, N) || solve(maze, x, y + 1, M, N);
        }

        return false;
    }

    private static boolean isValid(int x, int y, int M, int N) {
        return x >= 0 && x != M && y >= 0 && y != N;
    }
}
