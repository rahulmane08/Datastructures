package algos.dynamicprogramming;

public class LongestCommonSubstring {
    private int length = 0;
    private String substring = "";

    public LongestCommonSubstring(String str1, String str2) {
        solve(str1, str2);
    }

    public static void main(String[] args) {
        LongestCommonSubstring lcs = new LongestCommonSubstring("longest", "stone");
        System.out.printf("Length = %s, Substring = %s%n", lcs.getLength(), lcs.getSubstring());
    }

    private void solve(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return;
        }

        int m = str1.length();
        int n = str2.length();
        int[][] lcs = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    lcs[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    lcs[i][j] = 1 + lcs[i - 1][j - 1];
                    if (this.length < lcs[i][j]) {
                        this.length = lcs[i][j];
                        this.substring += str1.charAt(i-1);
                    }
                } else {
                    lcs[i][j] = 0;
                }
            }
        }
    }

    public int getLength() {
        return length;
    }

    public String getSubstring() {
        return substring;
    }
}
