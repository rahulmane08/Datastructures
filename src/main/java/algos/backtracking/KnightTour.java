package algos.backtracking;

import java.util.Arrays;

public class KnightTour {

    private static final int[] xMoves = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] yMoves = {1, 2, 2, 1, -1, -2, -2, -1};

    public static boolean solve(int M, int N) {
        int move = 1;
        int[][] board = new int[M][N];
        if (solve(board, move, 0, 0, M, N)) {
            System.out.println("solution found");
            System.out.println(Arrays.deepToString(board));
            return true;
        }
        return false;
    }

    private static boolean solve(int[][] board, int move, int x, int y, int M, int N) {
        if (move == M * N) {
            return true;
        }

        for (int i = 0; i < 8; i++) {
            int currX = x + xMoves[i];
            int currY = y + yMoves[i];
            if (isValid(board, currX, currY, M, N)) {
                board[currX][currY] = move;
                if (solve(board, move + 1, currX, currY, M, N)) {
                    return true;
                }
                board[currX][currY] = 0;
            }
        }
        return false;
    }

    private static boolean isValid(int[][] arr, int x, int y, int M, int N) {
        if (x < 0 || x >= M || y < 0 || y >= N || arr[x][y] != 0) {
            return false;
        }
        return true;
    }
}
