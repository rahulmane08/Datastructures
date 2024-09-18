package leetcode.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Space complexity = O(n)
 * Time complexity = O(m) , where = no. of operations.
 * Refer to Tushar Roy video : https://www.youtube.com/watch?v=ID00PMy0-vE
 */
public class DisjointSet {
  private final Map<Integer, Integer> parents;
  private final Map<Integer, Integer> ranks;
  private final Set<Integer> vertexes;

  public DisjointSet(Set<Integer> vertexes) {
    this.vertexes = vertexes;
    this.parents = new HashMap<>();
    this.ranks = new HashMap<>();
    vertexes.forEach(this::makeSet);
  }

  /**
   * T(n) = O(1)
   * @param vertex
   */
  void makeSet(Integer vertex) {
    parents.putIfAbsent(vertex, vertex);
    ranks.putIfAbsent(vertex, 0);
  }

  /**
   * T(n) = T(n-1) + 1
   * Case 1.2 : O(n)
   * @param vertex
   * @return
   */
  public Integer findSet(Integer vertex) {
    if (!parents.containsKey(vertex)) {
      return null;
    }

    if (vertex == parents.get(vertex)) {
      return vertex;
    }

    Integer parent = findSet(parents.get(vertex));
    parents.put(vertex, parent);
    return parent;
  }

  /**
   * T(n) = 2T(n-1) + 1
   * Case 1.3 :
   * @param v1
   * @param v2
   */
  public void union(Integer v1, Integer v2) {
    if (v1 == v2) {
      return;
    }
    int parent1 = findSet(v1);
    int parent2 = findSet(v2);
    if (parent1 == parent2) {
      return;
    }
    int rank1 = ranks.get(parent1);
    int rank2 = ranks.get(parent2);
    if (rank1 > rank2) {
      parents.put(parent2, parent1);
      ranks.put(parent1, rank1 + 1);
    } else {
      parents.put(parent1, parent2);
      ranks.put(parent2, rank2 + 1);
    }
  }
}
