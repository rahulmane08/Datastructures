package leetcode.math.easy;

public class Sqrt {
  public int mySqrt(int x) {
    int start = 1;
    int end = x / 2;
    int sqrt = 1;
    while (start <= end) {
      int mid = start + (end - start) / 2;
      int square = mid * mid;
      if (square == x) {
        sqrt = mid;
        break;
      } else if (square < x) {
        start = mid + 1;
        sqrt = mid;
      } else {
        end = mid - 1;
      }
    }
    return sqrt;
  }
}
