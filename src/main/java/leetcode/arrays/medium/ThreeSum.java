package leetcode.arrays.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ThreeSum {
  static public int threeSum(int[] arr, int sum) {
    java.util.Arrays.sort(arr);// nlog(n)
    int count = 0;
    int fixed, left, right;
    for (int i = 0; i < arr.length; i++) {
      fixed = i;
      left = i + 1;
      right = arr.length - 1;

      while (left < right) {
        int curSum = arr[fixed] + arr[left] + arr[right];
        if (curSum == sum) {
          System.out.printf("Triplet (%d,%d,%d)%n", arr[fixed], arr[left], arr[right]);
          left++;
          right--;
          count++;
        } else if (curSum > sum) {
          right--;
        } else {
          left++;
        }
      }
    }
    System.out.println("Total Pairs = " + count);
    return count;
  }

  static public int threeSumWithHashing(int[] arr, int sum) {
    HashSet<Integer> dupes = new HashSet<>();
    Map<Integer, Integer> complements = new HashMap<>();
    int count = 0;
    for (int fixed = 0; fixed < arr.length; fixed++) {
      if (dupes.contains(arr[fixed])) {
        continue;
      }
      dupes.add(arr[fixed]);
      for (int i = fixed + 1; i < arr.length; i++) {
        int complement = sum - (arr[fixed] + arr[i]);
        if (complements.getOrDefault(complement, -1) == fixed) {
          System.out.printf("Triplet (%d,%d,%d)%n", arr[fixed], arr[i], complement);
          count++;
        }
        complements.put(arr[i], fixed);
      }
    }
    return count;
  }
}
