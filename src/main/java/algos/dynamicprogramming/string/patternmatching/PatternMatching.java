package algos.dynamicprogramming.string.patternmatching;

public class PatternMatching {

    private boolean match;

    public PatternMatching(String str, String pattern) {
        solve(str, pattern);
    }

    public static void main(String[] args) {
        PatternMatching matching = new PatternMatching("xaylmz", "x?y");
        System.out.println(matching.isMatch());
    }

    private void solve(String str, String pattern) {
        if (str == null && pattern == null) {
            return;
        }

        if (str.isEmpty() && pattern.isEmpty()) {
            this.match = true;
            return;
        }

        String concisePattern = "" + pattern.charAt(0);
        for (int i = 1; i < pattern.length(); i++) {
            if (pattern.charAt(i) == '*' && pattern.charAt(i) == pattern.charAt(i - 1)) {
                continue;
            }
            concisePattern += pattern.charAt(i);
        }

        int m = str.length();
        int n = concisePattern.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        if (pattern.charAt(0) == '*') {
            dp[0][1] = true;
        }
        dp[0][0] = true; // empty string match

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str.charAt(i - 1) == pattern.charAt(j - 1) || pattern.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pattern.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }

        this.match = dp[m][n];
    }

    public boolean isMatch() {
        return match;
    }
}
