package patternsearching;

import java.util.ArrayList;
import java.util.List;

public class PatternSearchUtil {

    /**
     * Time complexity : O(m*(n-m+1))
     *
     * @param pattern
     * @param text
     * @return
     */
    public static List<Integer> searchNaive(String pattern, String text) {
        List<Integer> indexes = new ArrayList<>();
        if (text == null || text.isEmpty()) {
            return indexes;
        }

        if (pattern == null || pattern.isEmpty() || pattern.length() > text.length()) {
            return indexes;
        }

        int M = pattern.length();
        int N = text.length();
        for (int i = 0; i < N - M + 1; i++) {
            int j = 0;
            for (; j < M && text.charAt(i + j) == pattern.charAt(j); j++) ;

            if (j == M) {
                indexes.add(i);
            }
        }
        return indexes;
    }
}
