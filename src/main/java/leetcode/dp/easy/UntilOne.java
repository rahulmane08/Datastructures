package leetcode.dp.easy;

import java.util.HashMap;
import java.util.Map;

public class UntilOne {
  public static int untilOne(int n, Map<Integer, Integer> cache) {
    if (n == 1) {
      return 0;
    }

    if (!cache.containsKey(n)) {
      int count = (n % 2 == 0) ? 1 + untilOne(n / 2, cache) : 1 + untilOne(3 * n + 1, cache);
      cache.put(n, count);
    }

    return cache.get(n);
  }

  public static void main(String[] args) {
    System.out.println(untilOne(4, new HashMap<>()));
  }
}
