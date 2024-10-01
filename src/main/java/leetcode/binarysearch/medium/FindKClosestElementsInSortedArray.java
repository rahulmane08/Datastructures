package leetcode.binarysearch.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class FindKClosestElementsInSortedArray {
  public List<Integer> findClosestElements(int[] arr, int k, int x) {
    int n = arr.length;
    int[] range = {-1, -1};
    populateRange(arr, x, 0, n - 1, range);
    Deque<Integer> closestElements = new LinkedList<>();
    int left = range[0];
    int right = range[1];
    while (left > -1 && right != -1 && right < n && closestElements.size() < k) {
      int a = x - arr[left];
      int b = arr[right] - x;
      if (a == b) {
        closestElements.offerFirst(arr[left]);
        if (a == 0) {
          right++;
        }
        left--;
      } else if (a < b) {
        closestElements.offerFirst(arr[left--]);
      } else {
        closestElements.offerLast(arr[right++]);
      }
    }
    while (left > -1 && closestElements.size() < k) {
      closestElements.offerFirst(arr[left--]);
    }

    while (right < n && closestElements.size() < k) {
      closestElements.offerLast(arr[right++]);
    }

    return new ArrayList<>(closestElements);
  }

  private void populateRange(int[] arr, int x, int low, int high, int[] range) {
    if (low > high) {
      return;
    }
    int mid = (low + high) >>> 1;
    if (arr[mid] == x) {
      range[0] = range[1] = mid;
    } else if (x < arr[mid]) {
      range[1] = mid;
      populateRange(arr, x, low, mid - 1, range);
    } else {
      range[0] = mid;
      populateRange(arr, x, mid + 1, high, range);
    }
  }

  public List<Integer> findClosestElements1(int[] arr, int k, int x) {
    if (arr.length == k) {
      return Arrays.stream(arr).boxed().toList();
    }
    if (x < arr[0]) {
      return Arrays.stream(arr).limit(k).boxed().toList();
    }
    if (x > arr[arr.length - 1]) {
      return Arrays.stream(arr).skip(arr.length - k).boxed().toList();
    }

    int position = getInsertPosition(arr, x);

    return Collections.emptyList();
  }

  private int getInsertPosition(int[] nums, int target) {
    int low = 0;
    int high = nums.length - 1;
    while (low <= high) {
      int mid = (low + high) >>> 1;
      if (nums[mid] == target) {
        return mid;
      } else if (target < nums[mid]) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return low + 1;
  }

  public static void main(String[] args) {
    int[] arr = {1, 2, 6, 7, 8};
    FindKClosestElementsInSortedArray util = new FindKClosestElementsInSortedArray();
    System.out.println(util.findClosestElements1(arr, 4, 0));
    System.out.println(util.findClosestElements1(arr, 4, 6));
    System.out.println(util.findClosestElements1(arr, 4, 5));
    System.out.println(util.findClosestElements1(arr, 2, 3));
  }
}
