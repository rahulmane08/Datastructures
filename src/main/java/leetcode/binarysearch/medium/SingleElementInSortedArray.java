package leetcode.binarysearch.medium;

public class SingleElementInSortedArray {
  public int singleNonDuplicate(int[] nums) {
    // all elements occur in pairs except 1 element, hence length of array should be odd.
    // if the single element is not present, then every element will appear at even and odd-th index.
    // if we find an element whose second appearance is at a even index, it means theres a single element present before.
    // ignore the arrays that satisfy even-odd condition
    if (nums.length % 2 == 0) {
      return -1;
    }
    return findSingleElement(nums);
  }

  // [1,1,2,2,3,4,4] [1,2,2,3,3,4,4]
  private int findSingleElement(int[] nums) {
    int n = nums.length;
    int low = 0;
    int high = n - 1;
    while (low <= high) {
      int mid = (low + high) >>> 1;
      int freq = 0;
      if (mid + 1 < n && nums[mid] == nums[mid + 1]) {
        freq++;
      }
      if (mid - 1 > -1 && nums[mid] == nums[mid - 1]) {
        freq++;
      }
      if (freq == 0) {
        return nums[mid];
      }

      if (mid % 2 == 1) {

      }

      if ((mid % 2 == 1 && nums[mid - 1] == nums[mid]) || (mid % 2 == 0 && nums[mid] == nums[mid + 1])) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    int[] nums = {1, 1, 2, 2, 3, 4, 4};
    SingleElementInSortedArray util = new SingleElementInSortedArray();
    System.out.println(util.singleNonDuplicate(nums));
    nums = new int[] {1, 2, 2, 3, 3, 4, 4};
    System.out.println(util.singleNonDuplicate(nums));
    nums = new int[] {1, 1, 2, 2, 3, 3, 4};
    System.out.println(util.singleNonDuplicate(nums));
    nums = new int[] {1, 1, 2, 2, 3};
    System.out.println(util.singleNonDuplicate(nums));
  }
}
