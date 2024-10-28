package leetcode.greedy.easy;

import java.util.Arrays;

public class AssignCookies {
  public int findContentChildren(int[] g, int[] s) {
    Arrays.sort(g);
    Arrays.sort(s);
    int cookies = 0;
    int greed = 0;
    while (cookies < s.length && greed < g.length) {
      if (s[cookies] >= g[greed]) {
        greed++;
      }
      cookies++;
    }
    return greed;
  }
}
