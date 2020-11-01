package algos.backtracking;

import string.StringUtils;

public class StringPermutations {
    public static void main(String[] args) {
        String str = "ABC";
        System.out.printf("Permutations of %s = %s%n", str, StringUtils.permute(str));
    }
}
