package leetcode.arrays.medium;

public class MaximumGoodSubarraySum {
  public long maximumSubarraySum(int[] nums, int k) {
    int i = 0;
    int sum = 0;
    int start = 0;
    int end = 0;
    while (end < nums.length) {

    }
    return sum;
  }

  public static void main(String[] args) {
    MaximumGoodSubarraySum util = new MaximumGoodSubarraySum();
    int[] nums = new int[] {1, 2, 3, 4, 5, 6};
    System.out.println(util.maximumSubarraySum(nums, 2));
  }
}
