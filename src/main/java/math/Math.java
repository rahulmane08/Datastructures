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
}
