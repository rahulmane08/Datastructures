package algorithms.backtracking.medium;

/**
 * https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/description/
 */
public class KthSortedHappyString {

    private char[] happy = new char[]{'a', 'b', 'c'};

    public String getHappyString(int n, int k) {
        if (n == 1) {
            if (k > happy.length) {
                return String.valueOf(happy[k]);
            }
            return String.valueOf(happy[k-1]);
        }

        return null;
    }
}
