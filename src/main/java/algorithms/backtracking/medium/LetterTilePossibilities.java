package algorithms.backtracking.medium;

import java.util.ArrayList;
import java.util.List;

public class LetterTilePossibilities {
    public int numTilePossibilities(String tiles) {
        List<String> possibilities = new ArrayList<>();
        char[] chars = tiles.toCharArray();
        for (int i = 0; i < tiles.length(); i++) {
            for (int j = i; j < tiles.length(); j++) {
                find(chars, possibilities, i, j);
            }
        }
        System.out.println(possibilities);
        return possibilities.size();
    }

    private void find(char[] chars, List<String> possibilities, int start, int end) {
        if (start == end) {
            StringBuilder s = new StringBuilder();
            for (int i = start; i <= end; i++) {
                s.append(chars[i]);
            }
            possibilities.add(s.toString());
            return;
        }
        for (int i = start; i <= end; i++) {
            swap(chars, start, i);
            find(chars, possibilities, start + 1, end);
            swap(chars, start, i);
        }
    }

    private void swap(char[] chars, int i, int j) {
        if (i == j) {
            return;
        }
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        LetterTilePossibilities util = new LetterTilePossibilities();
        System.out.println(util.numTilePossibilities("ABC"));
    }
}
