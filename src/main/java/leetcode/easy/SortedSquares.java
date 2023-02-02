package leetcode.easy;

/**
 * Example 1:
 *
 * Input: nums = [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * Explanation: After squaring, the array becomes [16,1,0,9,100].
 * After sorting, it becomes [0,1,9,16,100].
 * Example 2:
 *
 * Input: nums = [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 */
public class SortedSquares {
    public int[] sortedSquares(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        int start = 0;
        int end = nums.length - 1;
        int index = end;
        int [] result = new int[nums.length];
        while (start <= end) {
            if (Math.abs(nums[start]) < Math.abs(nums[end])) {
                result[index--] = nums[end] * nums[end];
                end--;
            } else {
                result[index--] = nums[start] * nums[start];
                start++;
            }
        }
        return result;
    }
}
