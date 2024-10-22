package leetcode.string.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
  public List<List<String>> groupAnagrams(String[] strs) {
    List<List<String>> result = new ArrayList<>();
    Map<String, List<String>> map = new HashMap<>();

    for (String s : strs) {
      char[] chars = s.toCharArray();
      Arrays.sort(chars);
      String sorted = new String(chars);
      map.compute(sorted, (k, v) -> v != null ? v : new ArrayList<>()).add(s);
    }

    result.addAll(map.values());
    if (result.isEmpty()) {
      result.add(Arrays.asList(""));
    }
    return result;
  }
}
