package algorithms.backtracking.medium;

import java.util.*;

public class LetterCombinationsPhoneNumber {
    private final static Map<String, String> dialPad = new HashMap<>();
    static {
        dialPad.put("2", "abc");
        dialPad.put("3", "def");
        dialPad.put("4", "ghi");
        dialPad.put("5", "jkl");
        dialPad.put("6", "mno");
        dialPad.put("7", "pqrs");
        dialPad.put("8", "tuv");
        dialPad.put("9", "wxyz");
    }

    public List<String> letterCombinations(String digits) {
        List<String> output = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        for (char c: digits.toCharArray()) {
            String s = dialPad.get(String.valueOf(c));
            if (s != null) {
                alphabets.add(s);
            }
        }
        addCombinations(alphabets, output, "", digits.length(), 0);
        return output;
    }

    private void addCombinations(List<String> alphabets, List<String> output, String prefix, int count, int currentIndex) {
        if (count == prefix.length()) {
            if (!prefix.isEmpty()) {
                output.add(prefix);
            }
            return;
        }

        if (currentIndex == alphabets.size()) {
            return;
        }

        String currentStr = alphabets.get(currentIndex);
        for (char c : currentStr.toCharArray()) {
            addCombinations(alphabets, output, prefix + c, count, currentIndex + 1);
        }
    }

    public static void main(String[] args) {
        LetterCombinationsPhoneNumber util = new LetterCombinationsPhoneNumber();
        System.out.println(util.letterCombinations("23"));
    }
}
