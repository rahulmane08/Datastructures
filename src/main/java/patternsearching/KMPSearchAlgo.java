package patternsearching;

import java.util.ArrayList;
import java.util.List;

public class KMPSearchAlgo {

    /*
        Compute longest prefix suffix
        a b c a b y
        0 0 0 1 2 0
     */
    private static int[] computeLps(String str) {
        int[] lps = new int[str.length()];
        for (int j = 0, i = 1; i < str.length(); ) {
            if (str.charAt(j) == str.charAt(i)) {
                lps[i] = j + 1;
                j++;
                i++;
            } else {
                if (j == 0) {
                    lps[i] = 0;
                    i++;
                } else {
                    j = lps[j - 1];
                }
            }
        }
        return lps;
    }

    public static List<Integer> search(String pattern, String text) {
        List<Integer> indexes = new ArrayList<>();
        if (text == null || text.isEmpty()) {
            return indexes;
        }

        if (pattern == null || pattern.isEmpty() || pattern.length() > text.length()) {
            return indexes;
        }
        int[] lps = computeLps(pattern);
        int n = text.length();
        int m = pattern.length();
        for (int i = 0, j = 0; i < n && j < m; ) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                if (j == 0) {
                    i++;
                } else {
                    j = lps[j - 1];
                }
            }

            if (j == m) {
                indexes.add(i - m);
            }
        }
        return indexes;
    }

    public static void main(String[] args) {
        System.out.println(search("abcaby", "abxabcabcaby"));
    }
}
