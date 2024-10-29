package leetcode.math.easy;

import leetcode.math.medium.Power;

public class NthRoot {
  public int nthRoot(int x, int n) {
    Power power = new Power();
    int low = 1;
    int high = x;
    while (low < high) {
      int mid = (low + high) >>> 1;
      double pow = power.myPow(mid, n);
      if (pow == x) {
        return mid;
      }

      if (pow > x) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    NthRoot util = new NthRoot();
    System.out.println(util.nthRoot(64, 3));
  }
}
