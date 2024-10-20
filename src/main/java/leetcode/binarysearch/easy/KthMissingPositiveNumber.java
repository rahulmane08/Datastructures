package leetcode.binarysearch.easy;

/**
 * Example 1:
 * <p>
 * Input: arr = [2,3,4,7,11], k = 5
 * Output: 9
 * Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
 * [2,3,4,7,11], k = 5
 * mps = [1,1,1,3,7]
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: arr = [1,2,3,4], k = 2
 * Output: 6
 * Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 */
public class KthMissingPositiveNumber {
  public int findKthPositive(int[] arr, int k) {
    int[] missingNumbers = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      missingNumbers[i] = arr[i] - (i + 1);
    }
    int[] range = new int[] {-1, -1};
    populateRange(arr, k, 0, arr.length - 1, range);

    return -1;
  }

  private void populateRange(int[] arr, int k, int low, int high, int[] range) {
    if (low > high) {
      return;
    }
    int mid = (low + high) >>> 1;
    if (arr[mid] == k) {
      range[0] = range[1] = mid;
    } else if (k < arr[mid]) {
      range[1] = mid;
      populateRange(arr, k, low, mid - 1, range);
    } else {
      range[0] = mid;
      populateRange(arr, k, mid + 1, high, range);
    }
  }

  public static void main(String[] args) {
    KthMissingPositiveNumber util = new KthMissingPositiveNumber();
    util.findKthPositive(new int[] {2, 3, 4, 7, 11}, 1);
  }
}
