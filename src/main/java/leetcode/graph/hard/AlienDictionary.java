package leetcode.graph.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] consists of only lowercase English letters.
 * <p>
 * https://leetcode.com/problems/alien-dictionary/description/?envType=problem-list-v2&envId=topological-sort
 */
public class AlienDictionary {
  public static void main(String[] args) {
    String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
    AlienDictionary util = new AlienDictionary();
    System.out.println(util.alienOrder(words));
    words = new String[] {"z", "x"};
    System.out.println(util.alienOrder(words));
    words = new String[] {"z", "x", "z"};
    System.out.println(util.alienOrder(words));
    words = new String[] {"z", "z"};
    System.out.println(util.alienOrder(words));
    words = new String[] {"zy", "zx"};
    System.out.println(util.alienOrder(words));
  }

  public String alienOrder(String[] words) {
    // graph + indegree map
    Map<Character, Set<Character>> graph = new HashMap<>();
    Map<Character, Integer> inDegrees = new HashMap<>();

    for (int i = 0; i < words.length - 1; i++) {
      String curr = words[i];
      String next = words[i + 1];
      int minLength = Math.min(curr.length(), next.length());
      for (int j = 0; j < minLength; j++) {
        char from = curr.charAt(j);
        char to = next.charAt(j);
        if (from != to) {
          boolean added = graph.compute(from, (vertex, list) -> list != null ? list : new HashSet<>()).add(to);
          graph.compute(to, (vertex, list) -> list != null ? list : new HashSet<>());
          if (added) {
            inDegrees.compute(from, (vertex, degree) -> degree == null ? 0 : degree);
            inDegrees.compute(to, (vertex, degree) -> degree == null ? 1 : degree + 1);
          }
          break;
        } else {
          graph.compute(from, (vertex, list) -> list != null ? list : new HashSet<>());
          inDegrees.compute(from, (vertex, degree) -> degree == null ? 0 : degree);
        }
      }
    }
    if (graph.isEmpty()) {
      return words[0];
    }

    return createAlienOrder(graph, inDegrees);
  }

  private String createAlienOrder(Map<Character, Set<Character>> graph, Map<Character, Integer> inDegrees) {
    StringBuilder alienOrder = new StringBuilder();
    Queue<Character> bfs = new LinkedList<>();
    inDegrees.entrySet().stream().filter(e -> e.getValue() == 0)
        .map(e -> e.getKey())
        .forEach(bfs::offer);
    while (!bfs.isEmpty()) {
      Character curr = bfs.poll();
      for (Character neighbor : graph.getOrDefault(curr, new HashSet<>())) {
        Integer neighborInDegree = inDegrees.compute(neighbor, (vertex, degree) -> degree - 1);
        if (neighborInDegree == 0) {
          bfs.offer(neighbor);
        }
      }
      alienOrder.append(curr);
    }
    return alienOrder.length() == graph.keySet().size() ? alienOrder.toString() : "";
  }
}
