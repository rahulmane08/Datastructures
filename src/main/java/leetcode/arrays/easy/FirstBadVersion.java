package leetcode.arrays.easy;

/**
 * Example 1:
 * <p>
 * Input: n = 5, bad = 4
 * Output: 4
 * Explanation:
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * Then 4 is the first bad version.
 * Example 2:
 * <p>
 * Input: n = 1, bad = 1
 * Output: 1
 */
public class FirstBadVersion {
  private final int badVersion;

  public FirstBadVersion(int badVersion) {
    this.badVersion = badVersion;
  }

  public int firstBadVersion(int n) {
    return firstBadVersion(1, n);
  }

  private int firstBadVersion(int low, int high) {
    if (low > high) {
      return -1;
    }

    if (isBadVersion(low)) {
      return low;
    }

    int mid = low + (high - low) / 2;
    if (isBadVersion(mid)) {
      int left = firstBadVersion(low, mid - 1);
      if (left != -1) {
        return left;
      }
      return mid;
    }
    return firstBadVersion(mid + 1, high);
  }

  private boolean isBadVersion(int x) {
    return x == badVersion;
  }
}
