package leetcode.unionfind.medium;

import datastructures.advanced.DisjointSet;
import java.util.Arrays;
import java.util.Map;

public class LongestConsecutiveSubsequence {
  public int longestConsecutive(int[] nums) {
    DisjointSet<Integer> set = new DisjointSet<>();
    Arrays.stream(nums).forEach(set::makeSet);
    for (int num : nums) {
      if (set.contains(num + 1)) {
        set.union(num, num + 1);
      }
    }
    int length = 0;
    for (Map.Entry<Integer, Integer> entry : set.getRanks().entrySet()) {
      length = Math.max(length, entry.getValue());
    }
    return length;
  }

  public static void main(String[] args) {
    int[] nums = {100, 4, 200, 1, 3, 2};
    LongestConsecutiveSubsequence utils = new LongestConsecutiveSubsequence();
    System.out.println(utils.longestConsecutive(nums));
  }
}
