package leetcode.unionfind.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AmountOfNewAreaPaintedEachDay {
  public static void main(String[] args) {
    int[][] paint = new int[][] {{1, 4}, {4, 7}, {5, 8}};
    AmountOfNewAreaPaintedEachDay util = new AmountOfNewAreaPaintedEachDay();
    System.out.println(Arrays.toString(util.amountPainted(paint)));
  }

  public int[] amountPainted(int[][] paint) {
    Map<Integer, Integer> lengthToDayMap = new HashMap<>();
    int[] result = new int[paint.length];
    for (int i = 0; i < paint.length; i++) {
      int day = i + 1;
      int lengthCovered = 0;
      for (int length = paint[i][0]; length < paint[i][1]; length++) {
        if (!lengthToDayMap.containsKey(length)) {
          lengthToDayMap.put(length, day);
          lengthCovered++;
        }
      }
      result[i] = lengthCovered;
    }
    return result;
  }
}
