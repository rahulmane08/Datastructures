package leetcode.math.medium;

public class Power {
  public double myPow(double x, int n) {
    double power = power3(x, Math.abs(n));
    if (n < 0) {
      return 1 / power;
    }
    return power;
  }

  /**
   * a = 1, b = 2
   * @param x
   * @param n
   * @return
   */
  private double power3(double x, int n) {
    if (n == 0) {
      return 1;
    }
    double power = power3(x, n / 2);
    return n % 2 == 0 ? power * power : x * power * power;
  }

  /**
   * This gives TLE :)
   * @param x
   * @param n
   * @return
   */
  private double power1(double x, int n) {
    double power = 1;
    for (int i = 0; i < n; i++) {
      power *= x;
    }
    return power;
  }

  /**
   * Stack is going to overflow for very high powers
   * @param x
   * @param n
   * @return
   */
  private double power(double x, int n) {
    if (n == 0) {
      return 1;
    }
    return x * myPow(x, n - 1);
  }

  public static void main(String[] args) {
    Power util = new Power();
    System.out.println(util.myPow(2, 10));
    System.out.println(util.myPow(2.1, 3));
    System.out.println(util.myPow(2, -2));
    System.out.println(util.myPow(0.00001, 2147483647));
  }
}
