package leetcode.matrix.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/spiral-matrix/solutions/5513240/video-explanation/
 */
public class SpiralMatrix1 {

  /**
   * https://www.youtube.com/watch?v=RSjo4A8WfQ8
   * <p>
   * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
   * Output: [1,2,3,6,9,8,7,4,5]
   *
   * @param matrix
   * @return
   */
  public List<Integer> spiralOrder(int[][] matrix) {
    int rows = matrix.length;
    int cols = matrix[0].length;
    int x = 0;
    int y = 0;
    int dx = 1;
    int dy = 0;
    List<Integer> res = new ArrayList<>();

    for (int i = 0; i < rows * cols; i++) {
      res.add(matrix[y][x]);
      matrix[y][x] = -101; // the range of numbers in matrix is from -100 to 100

      if (!(0 <= x + dx && x + dx < cols && 0 <= y + dy && y + dy < rows) || matrix[y + dy][x + dx] == -101) {
        int temp = dx;
        dx = -dy;
        dy = temp;
      }

      x += dx;
      y += dy;
    }

    return res;
  }

  public static void main(String[] args) {
    int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    SpiralMatrix1 util = new SpiralMatrix1();
    System.out.println(util.spiralOrder(matrix));
  }
}
