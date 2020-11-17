package matrix;

import utils.Utils;

public class MatrixUtils {

    static public int findPairWithMaxDiff(int mat[][], int N) {
        int[][] maxArray = new int[N][N];
        int maxDiff = Integer.MIN_VALUE;

        int max = mat[N - 1][N - 1]; //init max to last element


        /* mat[3][3]
         * 1,2,3
         * 4,5,6
         * 7,8,9
         *
         * maxArr[3][3]
         * 0,0,0
         * 0,0,0
         * 0,0,9
         */


        //fill the entire last row of maxArr with the max element of last row in actual arr
        for (int j = N - 2; j >= 0; j--) {
            if (mat[N - 1][j] > max)
                max = mat[N - 1][j];
            maxArray[N - 1][j] = max;
        }


        for (int j = N - 2; j >= 0; j--) {
            if (mat[j][N - 1] > max)
                max = mat[j][N - 1];
            maxArray[j][N - 1] = max;
        }

        /**
         * maxArr[3][3]
         * 0,0,9
         * 0,0,9
         * 9,9,9
         */

        for (int i = N - 2; i >= 0; i--) {
            for (int j = N - 2; j >= 0; j--) {
                // Update maxValue
                if (maxArray[i + 1][j + 1] - mat[i][j] > max)
                    max = maxArray[i + 1][j + 1] - mat[i][j];

                // set maxArr (i, j)
                maxArray[i][j] = Math.max(mat[i][j], Math.max(maxArray[i][j + 1], maxArray[i + 1][j]));
            }
        }

        return maxDiff;
    }

    public static class PrintUtils {

        public static void printZigzagOrder(int[][] arr) {
            if (arr == null) {
                return;
            }
            int level = 0;
            int m = arr.length;
            int n = arr[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (level % 2 == 0) {
                        System.out.print(arr[i][j] + "  ");
                    } else {
                        System.out.print(arr[i][n - 1 - j] + "  ");
                    }
                }
                System.out.println();
                level++;
            }
        }

        /**
         * 1     2     3     4
         * 5     6     7     8
         * 9    10    11    12
         * 13    14    15    16
         * 17    18    19    20
         * Diagonal printing of the above arr is
         * 1
         * 5     2
         * 9     6     3
         * 13    10     7     4
         * 17    14    11     8
         * 18    15    12
         * 19    16
         * 20
         */
        static public void printDiagonally(int arr[][]) {
            int m = arr.length - 1;
            int n = arr[0].length - 2;
            int LINES = m + n + 1;

            for (int line = 0; line < LINES; line++) {
                int row_idx = Math.min(line, m);
                int col_idx = Math.max(0, line - n);
                int elements = Utils.min(line, n - col_idx, m) + 1;
                ;
                for (int i = 0; i < elements; i++) {
                    int x = (row_idx - i);
                    int y = Math.abs(i - col_idx);
                    System.out.print(arr[x][y] + " ");
                }
                System.out.println();
            }
        }

        static void printSpiralOrder(int arr[][]) {
            int TOP = 0;
            int BOTTOM = arr.length - 1;
            int LEFT = 0;
            int RIGHT = arr[0].length - 1;
            int dir = 0;

            while (TOP <= BOTTOM && LEFT <= RIGHT) {
                if (dir == 0) {
                    for (int i = LEFT; i <= RIGHT; i++) {
                        System.out.print(arr[TOP][i] + " ");
                    }
                    TOP++;
                } else if (dir == 1) {
                    for (int i = TOP; i <= BOTTOM; i++) {
                        System.out.print(arr[i][RIGHT] + " ");
                    }
                    RIGHT--;
                } else if (dir == 2) {
                    for (int i = RIGHT; i >= LEFT; i--) {
                        System.out.print(arr[BOTTOM][i] + " ");
                    }
                    BOTTOM--;
                } else if (dir == 3) {
                    for (int i = BOTTOM; i >= TOP; i--) {
                        System.out.print(arr[i][LEFT] + " ");
                    }
                    LEFT++;
                }
                dir = (dir + 1) % 4;
            }
            System.out.println();
        }
    }

    public static class ArrangementUtils {
        static public void rotateMatrix(int mat[][], int N) {
            // Consider all squares one by one
            for (int x = 0; x < N / 2; x++) {
                // Consider elements in group of 4 in
                // current square
                for (int y = x; y < N - x - 1; y++) {
                    // store current cell in temp variable
                    int temp = mat[x][y];

                    // move values from right to top
                    mat[x][y] = mat[y][N - 1 - x];

                    // move values from bottom to right
                    mat[y][N - 1 - x] = mat[N - 1 - x][N - 1 - y];

                    // move values from left to bottom
                    mat[N - 1 - x][N - 1 - y] = mat[N - 1 - y][x];

                    // assign temp to left
                    mat[N - 1 - y][x] = temp;
                }
            }
        }
    }
}
