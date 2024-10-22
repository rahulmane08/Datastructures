package leetcode.binarysearch.medium;

import datastructures.advanced.DisjointSet;
import java.util.Arrays;
import java.util.Comparator;

public class EarliestTimeForEveryoneToBeFriends {
  public int earliestAcq(int[][] logs, int n) {
    Arrays.sort(logs, Comparator.comparingInt(a -> a[0]));
    int length = logs.length;
    int low = 0;
    int high = length - 1;
    int minIndex = -1;
    while (low <= high) {
      int mid = (low + high) >>> 1;
      if (areAllFriendsAtTime(logs, n, mid)) {
        minIndex = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return minIndex != -1 ? logs[minIndex][0] : -1;
  }

  private boolean areAllFriendsAtTime(int[][] logs, int n, int index) {
    DisjointSet<Integer> set = new DisjointSet<>();
    for (int i = 0; i <= index; i++) {
      int a = logs[i][1];
      int b = logs[i][2];
      Integer p1 = set.findSet(a);
      Integer p2 = set.findSet(b);
      if (p1 != p2) {
        set.union(a, b);
      }
    }
    return set.getCount() == 1 && set.getSize() == n;
  }

  public static void main(String[] args) {
    int[][] logs =
        new int[][] {{20190101, 0, 1}, {20190104, 3, 4}, {20190107, 2, 3}, {20190211, 1, 5}, {20190224, 2, 4},
            {20190301, 0, 3}, {20190312, 1, 2}, {20190322, 4, 5}};
    EarliestTimeForEveryoneToBeFriends util = new EarliestTimeForEveryoneToBeFriends();
    System.out.println(util.earliestAcq(logs, 6));
  }
}
