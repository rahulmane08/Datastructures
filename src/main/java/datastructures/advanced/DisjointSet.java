package datastructures.advanced;

import java.util.HashMap;
import java.util.Map;

/**
 * Space complexity = O(n)
 * Time complexity = O(m x alpha(n)) , where m = no. of operations. alpha(n) is inv ackerman function.
 * https://cs.stackexchange.com/questions/105652/what-does-o-alphan-amortized-time-mean
 * Refer to Tushar Roy video : https://www.youtube.com/watch?v=ID00PMy0-vE
 *
 */
public class DisjointSet<T> {
  private final Map<T, T> parents;
  private final Map<T, Integer> ranks;

  public DisjointSet() {
    this.parents = new HashMap<>();
    this.ranks = new HashMap<>();
  }

  /**
   * T(n) = O(1)
   *
   * @param vertex
   */
  public void makeSet(T vertex) {
    parents.putIfAbsent(vertex, vertex);
    ranks.putIfAbsent(vertex, 0);
  }

  /**
   * T(n) = T(n-1) + 1
   * Case 1.2 : O(n)
   *
   * @param vertex
   * @return
   */
  public T findSet(T vertex) {
    if (!parents.containsKey(vertex)) {
      return null;
    }

    if (vertex == parents.get(vertex)) {
      return vertex;
    }

    T parent = findSet(parents.get(vertex));
    parents.put(vertex, parent);
    return parent;
  }

  /**
   * T(n) = 2T(n-1) + 1
   * Case 1.3 :
   *
   * @param v1
   * @param v2
   */
  public void union(T v1, T v2) {
    if (v1 == v2) {
      return;
    }
    T parent1 = findSet(v1);
    T parent2 = findSet(v2);
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
