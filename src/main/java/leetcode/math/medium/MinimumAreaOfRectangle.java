package leetcode.math.medium;

import java.util.HashSet;
import java.util.Set;

public class MinimumAreaOfRectangle {

  /**
   * Input: points = [[1,1],[1,3],[3,1],[3,3],[2,2]]
   * Output: 4
   *
   * O(n^2)
   * @param points
   * @return
   */
  public int minAreaRect(int[][] points) {
    Set<String> pointSet = new HashSet<>();
    for (int[] point : points) {
      pointSet.add(point[0] + "," + point[1]);
    }

    int minArea = Integer.MAX_VALUE;
    for (int i = 0; i < points.length; i++) {
      int[] current = points[i];
      int x1 = current[0];
      int y1 = current[1]; // [1,1]

      for (int j = i + 1; j < points.length; j++) {
        int[] next = points[j]; // [3,1] - found horrizontal point
        int x2 = next[0];
        int y2 = next[1];

        // check for diagonal points and see if they for a rectangle
        if (x1 != x2 && y1 != y2) {
          if (pointSet.contains(x1 + "," + y2) && pointSet.contains(x2 + "," + y1)) {
            minArea = Math.min(minArea, Math.abs(x1 - x2) * Math.abs(y1 - y2));
          }
        }
      }
    }

    return minArea != Integer.MAX_VALUE ? minArea : 0;
  }

  public static void main(String[] args) {

  }
}
