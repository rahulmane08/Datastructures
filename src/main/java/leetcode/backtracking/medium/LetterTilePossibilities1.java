package leetcode.backtracking.medium;

import java.util.LinkedHashSet;
import java.util.Set;

public class LetterTilePossibilities1 {
  public static void main(String[] args) {
    LetterTilePossibilities1 util = new LetterTilePossibilities1();
    System.out.println(util.numTilePossibilities("AAB"));
  }

  public int numTilePossibilities(String tiles) {
    Set<String> set = new LinkedHashSet<>();
    boolean[] vis = new boolean[tiles.length()];
    permute(tiles, "", set, vis);
    return set.size() - 1;
  }

  public void permute(String tiles, String curr, Set<String> set, boolean[] vis) {
    set.add(curr);
    System.out.println(set);
    for (int i = 0; i < tiles.length(); i++) {
      if (!vis[i]) {
        vis[i] = true;
        permute(tiles, curr + tiles.charAt(i), set, vis);
        vis[i] = false;
      }
    }
  }
}
