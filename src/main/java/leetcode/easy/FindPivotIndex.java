package leetcode.easy;

/**
 * Given an array of integers nums, calculate the pivot index of this array.
 *
 * The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.
 *
 * If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. This also applies to the right edge of the array.
 *
 * Return the leftmost pivot index. If no such index exists, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,7,3,6,5,6]
 * Output: 3
 * Explanation:
 * The pivot index is 3.
 * Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
 * Right sum = nums[4] + nums[5] = 5 + 6 = 11
 * Example 2:
 *
 * Input: nums = [1,2,3]
 * Output: -1
 * Explanation:
 * There is no index that satisfies the conditions in the problem statement.
 * Example 3:
 *
 * Input: nums = [2,1,-1]
 * Output: 0
 * Explanation:
 * The pivot index is 0.
 * Left sum = 0 (no elements to the left of index 0)
 * Right sum = nums[1] + nums[2] = 1 + -1 = 0
 */
public class FindPivotIndex {

    static class Test {

    }
    public int pivotIndex(int[] nums) {
        if (nums == null) {
            return -1;
        }
        int leftSum = 0;
        int rightSum = 0;
        for (int i = 0; i < nums.length; i++) {
            rightSum += nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            if (i > 0) {
                leftSum += nums[i-1];
            }
            rightSum -= nums[i];
            if (leftSum == rightSum) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Test t = new Test();
        FindPivotIndex util = new FindPivotIndex();
        System.out.println(util.pivotIndex(new int[]{1,7,3,6,5,6}));
        System.out.println(util.pivotIndex(new int[]{1,2,3}));
        System.out.println(util.pivotIndex(new int[]{2, 1, -1}));
        System.out.println(util.pivotIndex(new int[]{0}));
    }
}
