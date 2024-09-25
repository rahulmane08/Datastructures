package leetcode.dp.medium;

/**
 * https://leetcode.com/problems/jump-game/description/
 * <p>
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * <p>
 * Input: nums = [3,2,1,0,4]
 * Output: false
 */
public class JumpGame {
  public boolean canJump(int[] nums) {
    int n = nums.length;
    boolean[] dp = new boolean[n];
    int target = n - 1;
    for (int i = n - 1; i > -1; i--) {
      if (i + nums[i] >= target) {
        dp[i] = true;
        target = i;
      }
    }
    return dp[0];
  }

  public static void main(String[] args) {
    JumpGame util = new JumpGame();
    System.out.println(util.canJump(new int[] {2, 3, 1, 1, 4}));
    System.out.println(util.canJump(new int[] {3, 2, 1, 0, 4}));
  }
}