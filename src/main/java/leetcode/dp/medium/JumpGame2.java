package leetcode.dp.medium;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.min;

import java.util.Arrays;

public class JumpGame2 {
  public int jump(int[] nums) {
    Integer[] dp = new Integer[nums.length];
    Arrays.fill(dp, -1);
    dp[nums.length - 1] = 0;
    int minJumps = topDown1(nums, 0, dp);
    return minJumps == MAX_VALUE ? -1 : minJumps;
  }

  /**
   * [2, 3, 1, 1, 4]
   * <p>
   * [2, 1, 2, 0, 0, 4]
   * dp = [-1, -1, -1, -1, -1, -1]
   * <p>
   * when index = 3 or 4, the for loop wont execute and recursion tree wont proceed.
   * In this case the MAX_VAL will be set in dp array and returned.
   * At index 2, the recursion tree output for index 3 and 4 will return MAX_VAL,
   * so we dont increment the value
   * and set MAX_VAL for index 2.
   * <p>
   * Now if 4th index was 1
   * for topDown(2)
   * = topDown(3)  = MAX_VAL, 1 + topDown(4) -> 1 + topDown(5) = 1 + 0 = 1
   * = min(MAX_VAL, 1)
   * = 1 -> add 1 = 2.
   *
   * @param nums
   * @param index
   * @param dp
   * @return
   */
  private int topDown(int[] nums, int index, Integer[] dp) {
    if (index >= nums.length) {
      return 0;
    }
    if (dp[index] == -1) {
      int minJumps = MAX_VALUE;
      for (int i = 1; i <= nums[index]; i++) { // try 1,2,..max allowed jumps...
        int jumps = topDown(nums, index + i, dp);
        minJumps = Math.min(minJumps, jumps);
      }

      // if the recursion tree is giving max_val which means path to the end is impossible.
      if (minJumps != MAX_VALUE) {
        dp[index] = 1 + minJumps;
      } else {
        dp[index] = minJumps;
      }
    }
    return dp[index];
  }

  private int topDown1(int[] nums, int index, Integer[] dp) {
    if (index >= nums.length) {
      return 0;
    }
    if (dp[index] == -1) {
      dp[index] = MAX_VALUE;
      for (int jumpBy = 1; jumpBy <= nums[index]; jumpBy++) {
        int result = topDown1(nums, index + jumpBy, dp);
        if (result != MAX_VALUE) {
          dp[index] = min(dp[index], 1 + result);
        }
      }
    }
    return dp[index];
  }

  public static void main(String[] args) {
    JumpGame2 util = new JumpGame2();
    System.out.println(util.jump(new int[] {3, 1, 1, 0, 4}));
  }
}
