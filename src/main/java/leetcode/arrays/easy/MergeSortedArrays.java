package leetcode.arrays.easy;

public class MergeSortedArrays {
  public int[] merge(int[] nums1, int m, int[] nums2, int n) {
    if (nums1 == null && nums2 == null) {
      return null;
    }
    if (nums1 == null) {
      return nums2;
    }
    if (nums2 == null) {
      return nums1;
    }
    int[] result = new int[m + n];
    int i = 0;
    int j = 0;
    int k = 0;
    while (i < m && j < n) {
      result[k++] = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
    }
    while (i < m) {
      result[k++] = nums1[i++];
    }
    while (j < n) {
      result[k++] = nums1[i++];
    }
    return result;
  }
}
