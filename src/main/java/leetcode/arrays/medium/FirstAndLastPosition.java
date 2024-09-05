package leetcode.arrays.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/explore/interview/card/uber/289/array-and-string/1683/
 */
public class FirstAndLastPosition {
  public static void main(String[] args) {
    FirstAndLastPosition solution = new FirstAndLastPosition();
    int[] result = solution.searchRange(new int[] {5, 7, 7, 8, 8, 10}, 6);
    System.out.println(Arrays.toString(result));
  }

  public int[] searchRange(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return new int[] {-1, -1};
    }

    List<Integer> indexes = new ArrayList<>();

    populate(nums, target, 0, nums.length - 1, indexes);

    if (indexes.isEmpty()) {
      return new int[] {-1, -1};
    }

    if (indexes.size() == 1) {
      return new int[] {indexes.get(0), indexes.get(0)};
    }

    indexes.sort(Integer::compareTo);
    return new int[] {indexes.get(0), indexes.get(indexes.size() - 1)};
  }

  private void populate(int[] nums, int target, int start, int end, List<Integer> indexes) {
    if (start > end) {
      return;
    }

    if (nums[start] == target && nums[end] == target) {
      indexes.add(start);
      indexes.add(end);
      return;
    }

    int mid = start + (end - start) / 2;
    if (nums[mid] == target) {
      indexes.add(mid);
      populate(nums, target, start, mid - 1, indexes);
      populate(nums, target, mid + 1, end, indexes);
    } else if (nums[mid] < target) {
      populate(nums, target, mid + 1, end, indexes);
    } else {
      populate(nums, target, start, mid - 1, indexes);
    }
  }
}
