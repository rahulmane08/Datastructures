package leetcode.misc.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ABC, BAC , ABC, BAC, CAB
 * (A, 1, 2) (A, 2, 2) (B, 1, 2) (B, 2, 2) (B, 3, 1) (C, 1, 1) (C, 3, 4)
 * Rank 1 -> A : 2 , B : 2, C : 1
 * Rank 2 -> A : 2 , B : 2
 * Rank 3 : C : 3, B : 1
 * A and B has a tie 2 votes each for rank 1, hence sort by rank counts
 */
public class RankTeamByVotes {

  public static void main(String[] args) {
    RankTeamByVotes util = new RankTeamByVotes();
    String[] votes = new String[] {"ABC", "BAC", "ABC", "BAC", "CAB"};
    System.out.println(util.rankTeams(votes));
    votes = new String[] {"ABC", "BAC", "ABC", "BAC", "CBA"};
    System.out.println(util.rankTeams(votes));
    votes = new String[] {"ABC", "BAC", "BAC", "BAC", "CAB"};
    System.out.println(util.rankTeams(votes));
    votes = new String[] {"ABC", "BAC", "BAC", "ABC"};
    System.out.println(util.rankBasedOnFirstArrival(votes));
  }

  /**
   * Character -> rank -> votes
   *
   * @param votes
   * @return
   */
  public String rankTeams(String[] votes) {
    int ranks = votes[0].length();
    Map<Character, int[]> result = new HashMap<>();
    for (String vote : votes) {
      for (int i = 0; i < vote.length(); i++) {
        char contender = vote.charAt(i);
        result.compute(contender, (c, votesByRank) -> votesByRank != null ? votesByRank : new int[ranks])[i]++;
      }
    }
    /**
     * {
     *    A : [2,3,0],
     *    B : [2,2,1],
     *    C : [1,0,4]
     * }
     */
    Comparator<Map.Entry<Character, int[]>> comparator = (entry1, entry2) -> {
      char contender1 = entry1.getKey();
      int[] results1 = entry1.getValue();
      char contender2 = entry2.getKey();
      int[] results2 = entry2.getValue();
      for (int i = 0; i < ranks; i++) {
        if (results1[i] != results2[i]) {
          return Integer.compare(results2[i], results1[i]); // reverse sort.
        }
      }
      return Character.compare(contender1, contender2); // natural sort on lexical order.
    };

    List<Map.Entry<Character, int[]>> entries = result.entrySet().stream().collect(Collectors.toList());
    entries.sort(comparator);
    StringBuilder voteResult = new StringBuilder();
    for (Character c : entries.stream().map(Map.Entry::getKey).toList()) {
      voteResult.append(c);
    }
    return voteResult.toString();
  }

  public String rankTeamsOptimized(String[] votes) {
    int ranks = votes[0].length();
    int[][] voteTable = new int[26][ranks + 1];
    for (String vote : votes) {
      for (int i = 1; i < vote.length(); i++) {
        char contender = vote.charAt(i);
        voteTable[contender - 'A'][0] = contender;
        voteTable[contender - 'A'][i]++;
      }
    }
    Arrays.sort(voteTable, (votes1, votes2) -> {
      for (int i = 1; i < ranks; i++) {
        int compare = Integer.compare(votes2[i], votes1[1]);
        if (compare != 0) {
          return compare;
        }
      }
      return votes1[0] - votes2[0];
    });
    return "";
  }

  public String rankBasedOnFirstArrival(String[] votes) {
    int ranks = votes[0].length();
    Map<Character, long[][]> votesByRankAndTimestamp = new HashMap<>();
    for (String vote : votes) {
      for (int i = 0; i < vote.length(); i++) {
        Character contender = vote.charAt(i);
        long[][] stats = votesByRankAndTimestamp.compute(contender,
            (c, statistics) -> statistics == null ? new long[ranks][2] : statistics);
        stats[i][0]++;
        stats[i][1] = System.currentTimeMillis();
      }
    }
    Comparator<Map.Entry<Character, long[][]>> comparator = (entry1, entry2) -> {
      Character contender1 = entry1.getKey();
      Character contender2 = entry2.getKey();
      long[][] stats1 = entry1.getValue();
      long[][] stats2 = entry2.getValue();
      for (int i = 0; i < ranks; i++) {
        int compare = Long.compare(stats2[i][0], stats1[i][0]);
        if (compare == 0) {
          compare = Long.compare(stats1[i][1], stats2[i][1]);
        }
        if (compare != 0) {
          return compare;
        }
      }

      return Character.compare(contender1, contender2);
    };
    List<Map.Entry<Character, long[][]>> entries =
        votesByRankAndTimestamp.entrySet().stream().collect(Collectors.toList());
    entries.sort(comparator);
    StringBuilder voteResult = new StringBuilder();
    for (Character c : entries.stream().map(Map.Entry::getKey).toList()) {
      voteResult.append(c);
    }
    return voteResult.toString();
  }
}
