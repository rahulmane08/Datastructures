package leetcode.arrays.medium;

/**
 * [3,2,1,4,7,3,2,5]
 * [2,2,2]
 * [2,1,2,1,2,3,2,1] : more than 1 mountain
 * [3,2,1] decreasing slope
 * [1,2,3] increasing slope
 * [1,2,3,3,2]
 */
public class LongestMountainInArray {
  public static void main(String[] args) {
    int[] arr = {3, 2, 1, 4, 7, 3, 2, 5};
    LongestMountainInArray util = new LongestMountainInArray();
    System.out.println(util.longestMountain(arr));
    arr = new int[] {2, 2, 2};
    System.out.println(util.longestMountain(arr));
    arr = new int[] {3, 2, 1};
    System.out.println(util.longestMountain(arr));
    arr = new int[] {1, 2, 3};
    System.out.println(util.longestMountain(arr));
    arr = new int[] {2, 1, 2, 1, 2, 3, 2, 1};
    System.out.println(util.longestMountain(arr));
  }

  public int longestMountain(int[] arr) {
    int length = 0;
    int peak = 1;
    int left = 0;
    int right = 0;
    while (peak < arr.length - 1) {
      if (arr[peak - 1] < arr[peak] && arr[peak] > arr[peak + 1]) {
        for (left = peak; left > 0 && arr[left] > arr[left - 1]; left--) ;
        for (right = peak; right < arr.length - 1 && arr[right] > arr[right + 1]; right++) ;
        length = Math.max(length, right - left + 1);
      }
      peak++;
    }
    return length;
  }
}
