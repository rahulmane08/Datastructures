package leetcode.binarysearch.medium;

import java.util.Arrays;

/**
 * You are given an integer array ribbons, where ribbons[i] represents the length of the ith ribbon, and an integer k.
 * You may cut any of the ribbons into any number of segments of positive integer lengths, or perform no cuts at all.
 * <p>
 * For example, if you have a ribbon of length 4, you can:
 * Keep the ribbon of length 4,
 * Cut it into one ribbon of length 3 and one ribbon of length 1,
 * Cut it into two ribbons of length 2,
 * Cut it into one ribbon of length 2 and two ribbons of length 1, or
 * Cut it into four ribbons of length 1.
 * Your goal is to obtain k ribbons of all the same positive integer length. You are allowed to throw away any excess ribbon as a result of cutting.
 * <p>
 * Return the maximum possible positive integer length that you can obtain k ribbons of, or 0 if you cannot obtain k ribbons of the same length.
 * <p>
 * Example 1:
 * <p>
 * Input: ribbons = [9,7,5], k = 3
 * Output: 5
 * Explanation:
 * - Cut the first ribbon to two ribbons, one of length 5 and one of length 4.
 * - Cut the second ribbon to two ribbons, one of length 5 and one of length 2.
 * - Keep the third ribbon as it is.
 * Now you have 3 ribbons of length 5.
 * Example 2:
 * <p>
 * Input: ribbons = [7,5,9], k = 4
 * Output: 4
 * Explanation:
 * - Cut the first ribbon to two ribbons, one of length 4 and one of length 3.
 * - Cut the second ribbon to two ribbons, one of length 4 and one of length 1.
 * - Cut the third ribbon to three ribbons, two of length 4 and one of length 1.
 * Now you have 4 ribbons of length 4.
 * Example 3:
 * <p>
 * Input: ribbons = [5,7,9], k = 22
 * Output: 0
 * Explanation: You cannot obtain k ribbons of the same positive integer length.
 */
public class CuttingRibbons {
  public int maxLength(int[] ribbons, int k) {
    int maxLength = 0;
    int low = 1; // ribbon of atleast 1 length.
    int high = Arrays.stream(ribbons).min()
        .getAsInt(); // the ribbons have to be of equal length, hence they should at max be the size of min ribbon.
    while (low <= high) {
      int mid = (low + high) >>> 1;
      if (canCut(ribbons, k, mid)) {
        maxLength = mid;
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return maxLength;
  }

  private boolean canCut(int[] ribbons, int k, int minSize) {
    int count = 0;
    for (int length : ribbons) {
      count += length / minSize;
    }
    return count >= k;
  }

  public static void main(String[] args) {
    CuttingRibbons util = new CuttingRibbons();
    System.out.println(util.maxLength(new int[] {9, 7, 5}, 3));
    System.out.println(util.maxLength(new int[] {7, 5, 9}, 4));
    System.out.println(util.maxLength(new int[] {9, 7, 5}, 22));
  }
}
