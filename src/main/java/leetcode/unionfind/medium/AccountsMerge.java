package leetcode.unionfind.medium;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class AccountsMerge {

  public static void main(String[] args) {
    List<List<String>> accounts = asList(
        asList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
        asList("John", "johnsmith@mail.com", "john00@mail.com"),
        asList("Mary", "mary@mail.com"),
        asList("John", "johnnybravo@mail.com"));
    AccountsMerge util = new AccountsMerge();
    System.out.println(util.accountsMerge(accounts));
  }

  /**
   * 0 x -> (A, B)
   * 1 x -> (C, D)
   * 2 x -> (A, E)
   * <p>
   * A -> (0, 2)
   * B -> (0)
   * C -> (1)
   * D -> (1)
   *
   * @param accounts
   * @return
   */

  public List<List<String>> accountsMerge(List<List<String>> accounts) {
    List<List<String>> result = new ArrayList<>();
    Map<String, List<Integer>> organisedAccounts = new HashMap<>();
    for (int index = 0; index < accounts.size(); index++) {
      List<String> account = accounts.get(index);
      for (int i = 1; i < account.size(); i++) {
        String email = account.get(i);
        organisedAccounts.compute(email, (e, list) -> list == null ? new ArrayList<>() : list).add(index);
      }
    }
    for (List<Integer> indexes : organisedAccounts.values()) {
      String name = accounts.get(indexes.get(0)).get(0);
      List<String> mergedDetails = new ArrayList<>();
      mergedDetails.add(name);
      TreeSet<String> sortedEmails = new TreeSet<>();
      for (int index : indexes) {
        List<String> account = accounts.get(index);
        for (int i = 1; i < account.size(); i++) {
          sortedEmails.add(account.get(i));
        }
      }
      mergedDetails.addAll(sortedEmails);
      result.add(mergedDetails);
    }
    return result;
  }
}
