package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/explore/interview/card/uber/289/array-and-string/1685/
 */
public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        String line = "";
        String space = " ";
        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            if (line.isEmpty()) {
                line += word;
                continue;
            }


        }

        return result;
    }
}
