package algorithms.backtracking;

import java.util.Arrays;

public class NQueens {

    public static boolean solve(int N) {
        int[][] board = new int[N][N];
        if (solve(board, N, 0)) {
            System.out.println("Solution found");
            System.out.println(Arrays.deepToString(board));
            return true;
        }
        return false;
    }

    private static boolean solve(int[][] board, int N, int col) {
        if (col >= N) {
            return true;
        }

        for (int i = 0; i < N; i++) {
            if (isValid(board, i, col, N)) {
                board[i][col] = 1;
                if (solve(board, N, col + 1)) {
                    return true;
                }
                board[i][col] = 0;
            }
        }
        return false;
    }

    private static boolean isValid(int[][] board, int row, int col, int N) {
        int i, j;

        /* Check this row on left side */
        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;

        /* Check upper diagonal on left side */
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        /* Check lower diagonal on left side */
        for (i = row, j = col; j >= 0 && i < N; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }
}
