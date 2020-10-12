package algos.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringPermutations {

    public static List<String> permute(String str) {
        if (str == null || str.length() == 0) {
            return Collections.emptyList();
        }
        List<String> permutations = new ArrayList<>();
        permuteUtil(permutations, str.toCharArray(), 0, str.length() - 1);
        return permutations;
    }

    private static void permuteUtil(List<String> permutations, char[] charArray, int start, int end) {
        if (start == end) {
            permutations.add(new String(charArray));
        }
        for (int i = start; i <= end; i++) {
            swap(charArray, start, i);
            permuteUtil(permutations, charArray, start + 1, end);
            swap(charArray, start, i);
        }
    }

    private static void swap(char[] charArray, int i, int j) {
        if (i == j) {
            return;
        }
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
    }

    public static void main(String[] args) {
        String str = "ABC";
        System.out.printf("Permutations of %s = %s%n", str, permute(str));
    }
}
