package leetcode.unionfind.medium;

import datastructures.advanced.DisjointSet;

/**
 * 1 <= stones.length <= 1000
 * 0 <= xi, yi <= 104
 */
public class MostStonesRemovedWithSameRowOrColumn {
  public static void main(String[] args) {
    int[][] stones = {{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}};
    MostStonesRemovedWithSameRowOrColumn util = new MostStonesRemovedWithSameRowOrColumn();
    System.out.println(util.removeStones(stones));
    stones = new int[][] {{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}};
    System.out.println(util.removeStones(stones));
  }

  public int removeStones(int[][] stones) {
    int totalStones = stones.length;
    if (totalStones == 1) {
      return 0;
    }

    DisjointSet<Integer> set = new DisjointSet<>();
    int offset = 1001; // constraint max + 1.
    for (int i = 0; i < totalStones; i++) {
      int x = stones[i][0];
      int y = stones[i][1] + offset;
      set.makeSet(x);
      set.makeSet(y);
      set.union(x, y);
    }
    return totalStones - set.getCount();
  }
}
