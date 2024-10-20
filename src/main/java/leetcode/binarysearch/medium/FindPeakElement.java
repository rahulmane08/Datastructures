package leetcode.binarysearch.medium;

public class FindPeakElement {
  public int findPeakElement(int[] nums) {
    int n = nums.length;
    if (n == 1) {
      return 0;
    }

    int low = 0;
    int high = n - 1;

    while (low <= high) {
      int mid = (low + high) >>> 1;
      if (isPeakElement(nums, mid)) {
        return mid;
      }

      if (mid - 1 == 0 || nums[mid - 1] < nums[mid]) {
        // increasing slope, hence left space is increasingly sorted, check right space
        low = mid + 1;
      } else if (mid + 1 == n - 1 || nums[mid] > nums[mid + 1]) {
        // decreasing slope, hence right space is increasingly sorted, check left space
        high = mid - 1;
      }
    }
    return -1;
  }

  private boolean isPeakElement(int[] nums, int index) {
    return (index == 0 || nums[index - 1] < nums[index]) && (index == nums.length - 1 || nums[index + 1] < nums[index]);
  }

  public static void main(String[] args) {
    FindPeakElement util = new FindPeakElement();
    System.out.println(util.findPeakElement(new int[] {1, 2}));
  }
}
