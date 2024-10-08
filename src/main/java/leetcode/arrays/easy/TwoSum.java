package leetcode.arrays.easy;

import interfaces.LCEasy;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
  @LCEasy
  public int twoSum(int[] arr, int N) {
    java.util.Arrays.sort(arr);// nlog(n)
    int end = arr.length - 1, start = 0;
    for (; arr[end] >= N; end--) {
    }
    int count = 0;
    while (start < end) {
      int sum = arr[start] + arr[end];
      if (sum == N) {
        ++count;
        ++start;
        --end;
      } else if (sum > N) {
        --end;
      } else {
        ++start;
      }
    }
    System.out.println("Total Pairs = " + count);
    return count;
  }

  @LCEasy
  public int twoSumWithHashing(int[] nums, int sum) {
    Map<Integer, Integer> map = new HashMap();
    int count = 0;
    for (int i = 0; i < nums.length; i++) {
      int complement = sum - nums[i];
      int j = map.getOrDefault(complement, -1);
      if (j != -1 && j != i) {
        System.out.printf("Found pair: ( %d, %d) %n)", nums[i], nums[j]);
        count++;
      }
      map.put(nums[j], j);
    }
    return count;
  }
}
