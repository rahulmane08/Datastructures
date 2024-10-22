package datastructures.advanced;

import java.util.HashMap;
import java.util.Map;

/**
 * Space complexity = O(n)
 * Time complexity = O(m x alpha(n)) , where m = no. of operations. alpha(n) is inv ackerman function.
 * https://cs.stackexchange.com/questions/105652/what-does-o-alphan-amortized-time-mean
 * Refer to Tushar Roy video : https://www.youtube.com/watch?v=ID00PMy0-vE
 */
public class DisjointSet<T> {
  private final Map<T, T> parents;
  private final Map<T, Integer> ranks;
  private int count;
  private int size;

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
    if (!parents.containsKey(vertex)) {
      parents.putIfAbsent(vertex, vertex);
      ranks.putIfAbsent(vertex, 1);
      this.count++;
      this.size++;
    }
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
      makeSet(vertex);
      return vertex;
    }

    if (vertex == parents.get(vertex)) {
      return vertex;
    }

    T currentParent = parents.get(vertex);
    T newParent = findSet(currentParent);
    if (currentParent != newParent) {
      ranks.put(currentParent, 1); // path compressed.
    }
    parents.put(vertex, newParent); // path compression.
    return newParent;
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
      ranks.put(parent1, rank1 + rank2);
      ranks.put(parent2, 1);
    } else {
      parents.put(parent1, parent2);
      ranks.put(parent2, rank2 + rank1);
      ranks.put(parent1, 1);
    }
    this.count--; // union will reduce the # of disjoint sets.
  }

  public boolean contains(T elem) {
    return parents.containsKey(elem);
  }

  public int rank(T elem) {
    return ranks.get(elem);
  }

  public Map<T, T> getParents() {
    return parents;
  }

  public Map<T, Integer> getRanks() {
    return ranks;
  }

  public int getCount() {
    return count;
  }

  public int getSize() {
    return size;
  }
}
