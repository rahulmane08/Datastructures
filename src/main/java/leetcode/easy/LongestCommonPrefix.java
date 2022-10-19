package leetcode.easy;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        String prefix = "";
        int length = Integer.MAX_VALUE;
        for (String s: strs) {
            length = Math.min(length, s.length());
        }

        outer: for (int i = 0; i < length; i++) {
            for (int j = 0; j < strs.length - 1; j++) {
                if (strs[j].charAt(i) != strs[j+1].charAt(i)) {
                    break outer;
                }
            }
            prefix += strs[0].charAt(i);
        }
        return prefix;
    }

    public static void main(String[] args) {
        LongestCommonPrefix l = new LongestCommonPrefix();
        System.out.println(l.longestCommonPrefix(new String[]{"flower","flow","flight"}));
    }
}