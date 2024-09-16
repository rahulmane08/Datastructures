package leetcode.graph.hard;

import static java.util.Collections.emptySet;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/alien-dictionary/description/?envType=problem-list-v2&envId=topological-sort
 */
public class AlienDictionary {
  public String alienOrder(String[] words) {
    int n = words.length;
    Map<Character, Set<Character>> graph = new HashMap<>();

    int max = Arrays.stream(words).map(String::length).max(Comparator.naturalOrder()).get();
    Character start = null;

    for (int i = 0; i < max; i++) {
      Character first = null;
      for (int j = 0; j < n; j++) {
        if (i < words[j].length()) {
          char curr = words[j].charAt(i);
          start = start == null ? curr : start;
          if (first == null) {
            first = curr;
            continue;
          }
          if (first != curr) {
            graph.compute(first, (c, list) -> list == null ? new HashSet<>() : list).add(curr);
            first = curr;
          }
        }
      }
    }
    return populateAlienOrder(graph);
  }

  String populateAlienOrder(Map<Character, Set<Character>> graph) {
    Map<Character, Integer> inDegrees = new HashMap<>();
    graph.keySet().stream().forEach(character -> inDegrees.put(character, 0));
    graph.values().stream().flatMap(Set::stream)
        .forEach(character -> inDegrees.compute(character, (c, degree) -> degree == null ? 1 : degree + 1));
    Queue<Character> queue = new LinkedList<>();
    inDegrees.entrySet().stream().filter(entry -> entry.getValue() == 0).map(Map.Entry::getKey).forEach(queue::offer);
    if (queue.isEmpty()) {
      return ""; //cycle detected
    }
    StringBuilder alienOrder = new StringBuilder();
    while (!queue.isEmpty()) {
      Character curr = queue.poll();
      alienOrder.append(curr);
      for (Character neighbor : graph.getOrDefault(curr, emptySet())) {
        Integer inDegree = inDegrees.compute(neighbor, (c, d) -> d = d - 1);
        if (inDegree == 0) {
          queue.offer(neighbor);
        }
      }
    }
    return alienOrder.toString();
  }

//  boolean populateAlienOrder(Map<Character, Set<Character>> graph,
//                             Character start,
//                             StringBuilder alienOrder,
//                             Set<Character> visited) {
//    if (visited.contains(start)) {
//      return true;
//    }
//    visited.add(start);
//    for (Character neighbor : graph.getOrDefault(start, emptySet())) {
//      if (!populateAlienOrder(graph, neighbor, alienOrder, visited)) {
//        return false;
//      }
//    }
//    if (visited.contains(start)) {
//      return false; // cycle detected.
//    }
//    alienOrder.append(start);
//    return true;
//  }

  public static void main(String[] args) {
    String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
    AlienDictionary util = new AlienDictionary();
    System.out.println(util.alienOrder(words));
  }
}
