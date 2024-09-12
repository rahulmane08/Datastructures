package leetcode.backtracking.medium;

import java.util.HashSet;
import java.util.Set;

public class LetterTilePossibilities1 {
  public int numTilePossibilities(String tiles) {
    Set<String> set = new HashSet<>();
    return set.size() - 1;
  }
}
