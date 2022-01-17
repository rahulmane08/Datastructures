package math;

import interfaces.Hard;
import interfaces.Important;

public class Math {
    static public int gcd(int a, int b) {
        if (a == 0)
            return b;
        if (a == 1)
            return a;
        if (a > b)
            return gcd(a % b, b);
        return gcd(b % a, a);
    }

    static public <T extends Comparable<T>> T min(T... args) {
        if (args == null || args.length == 0)
            throw new IllegalArgumentException("No arguments");
        T min = args[0];
        for (int i = 1; i < args.length && args[i].compareTo(min) < 0; i++)
            min = args[i];
        return min;
    }

    static public <T extends Comparable<T>> T max(T... args) {
        if (args == null || args.length == 0)
            throw new IllegalArgumentException("No arguments");
        T max = args[0];
        for (int i = 1; i < args.length && args[i].compareTo(max) > 0; i++)
            max = args[i];
        return max;
    }

    @Hard
    @Important
    static public int parseInt(String s) {
        if (s == null || s.isEmpty()) {
            throw new NumberFormatException("string is null");
        }

        boolean negative = false;
        int n = s.length();
        int start = 0;
        int ceil = Integer.MAX_VALUE;
        if (s.charAt(0) == '-') {
            n--;
            negative = true;
            start++;
            ceil = Integer.MIN_VALUE;
        }

        if ((int) java.lang.Math.pow(10, n - start) % 10 != 0) {
            throw new NumberFormatException("Illegal string: " + s);
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(start + i);
            if (c < 48 || c > 57) {
                throw new NumberFormatException("Illegal string: " + s);
            }
            int x = c - 48;
            int pow = (int) java.lang.Math.pow(10, (n - start) - i);
            int y = java.lang.Math.abs(ceil / pow);

            if (x > y) {
                throw new NumberFormatException("Illegal string: " + s);
            }
            ceil -= y * pow;
            result += x * pow;
        }
        return negative ? -result : result / 10;
    }

    public static double pow(int x, int y) {
        double power = powerUtil(x, y);
        if (y < 1) {
            return 1 / power;
        }
        return power;
    }

    private static double powerUtil(int x, int y) {
        if (y == 0) {
            return 1;
        }
        if (y % 2 == 0) {
            return pow(x * x, y / 2);
        }
        return x * pow(x * x, y / 2);
    }

    public static int[] fibonacci(int n) {
        if (n == 0) {
            return null;
        }
        int[] dp = new int[n];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp;
    }

    public static int floorSqrt(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (n <= 1) {
            return n;
        }

        int start = 1;
        int end = n / 2;
        int sqrt = 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int square = mid * mid;
            if (square == n) {
                sqrt = mid;
                break;
            } else if (square < n) {
                start = mid + 1;
                sqrt = mid;
            } else {
                end = mid - 1;
            }
        }
        return sqrt;
    }

    public static int ceilSqrt(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (n <= 1) {
            return n;
        }

        int start = 1, end = n / 2;
        int sqrt = 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int square = mid * mid;
            if (square == n) {
                return mid;
            } else if (square < n) {
                start = mid + 1;
            } else {
                end = mid - 1;
                sqrt = mid;
            }
        }
        return sqrt;
    }

    public static void main(String[] args) {
        /*System.out.println(parseInt("1234"));
        System.out.println(parseInt("-2147483648"));
        System.out.println(Integer.parseInt("0.888"));*/
        System.out.println(floorSqrt(169));
        System.out.println(ceilSqrt(169));
        System.out.println(floorSqrt(28));
        System.out.println(ceilSqrt(28));
    }
}
