package leetcode.binarysearch.medium;

public class SingleElementInSortedArray {
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

  public int singleNonDuplicate(int[] nums) {
    // all elements occur in pairs except 1 element, hence length of array should be odd.
    // if the single element is not present, then every element will appear at even and odd-th index.
    // if we find an element whose second appearance is at a even index, it means theres a single element present before.
    // ignore the arrays that satisfy even-odd condition
    if (nums.length % 2 == 0) {
      return -1; // process only odd length arrays.
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
      if (isSingleElement(nums, mid)) {
        return nums[mid];
      }

      if ((mid % 2 == 1 && nums[mid - 1] == nums[mid]) || (mid % 2 == 0 && nums[mid] == nums[mid + 1])) {
        // low to mid array is clean, so move to the next half.
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return -1;
  }

  private boolean isSingleElement(int[] nums, int index) {
    int freq = 0;
    if (index - 1 > -1 && nums[index] == nums[index - 1]) {
      freq++;
    }
    if (index + 1 < nums.length && nums[index] == nums[index + 1]) {
      freq++;
    }
    return freq == 0;
  }
}
