package math;

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

    public static void main(String[] args) {
        System.out.println(parseInt("1234"));
        System.out.println(parseInt("-2147483648"));
        System.out.println(Integer.parseInt("0.888"));
    }
}
