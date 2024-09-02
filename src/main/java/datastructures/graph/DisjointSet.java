package datastructures.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. DisjointSet is formed of nodes belonging to same or different sets. Each set has a single representative.
 * 2. Each Node has a data , rank and parent pointer to parent Node
 * 3. Three main operations
 * a. makeSet:  whenever a node is added node.parent=node, which means it doesnt have a parent yet.
 * b. findSet: always gives the representative of the set. The representative of the set follows the prop = node.parent=node
 * c. union: adding two nodes of same set (same representative) returns false. If they belong to different sets, then get their respective
 * representatives:
 * if(parent1.rank>=parent2.rank)
 * {
 * parent1.rank=(parent1.rank>parent2.rank)?parent1.rank+1:parent2.rank;
 * parent2.parent=parent1;
 * }
 * else
 * parent1.parent=parent2;
 *
 * @param <T>
 * @author rahul
 */
public class DisjointSet<T> {
  Map<T, Node<T>> map = new HashMap<>();

  public void makeSet(T data) {
    Node<T> node = new Node<>(data);
    node.rank = 0;
    node.parent = node;
    map.put(data, node);
  }

  public Node<T> findSet(T data) {
    return findSet(map.get(data));
  }

  public Node<T> findSet(Node<T> node) {
    if (node == null) {
      return null;
    }
    if (node.parent == node) {
      return node;
    }
    return (node.parent = findSet(node.parent));
  }

  public boolean union(T data1, T data2) {
    if (data1 == null || data2 == null) {
      return false;
    }

    if (data1.equals(data2)) {
      return false;
    }

    Node<T> parent1 = findSet(data1);
    Node<T> parent2 = findSet(data2);

    if (parent1 == null || parent2 == null) {
      return false;
    }

    if (parent1.rank > parent2.rank) {
      parent2.parent = parent1;
    } else if (parent2.rank > parent1.rank) {
      parent1.parent = parent2;
    } else {
      // make any 1 as parent and increment the rank of new parent
      parent2.parent = parent1;
      parent1.rank += 1;
    }
    return true;
  }

  static public class Node<T> {
    T data;
    int rank;
    Node parent;

    public Node(T data) {
      super();
      this.data = data;
    }
  }
}
