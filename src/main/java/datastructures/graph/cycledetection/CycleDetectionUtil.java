package datastructures.graph.cycledetection;

import datastructures.graph.DisjointSet;
import datastructures.graph.DisjointSet.Node;
import datastructures.graph.Edge;
import datastructures.graph.Graph;
import datastructures.graph.Vertex;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class CycleDetectionUtil {

  static public <T> boolean detectCycle(Graph<T> graph) {
    if (graph == null) {
      return false;
    }
    if (graph.isDirected()) {
      return detectCycleInDG(graph);
    } else {
      return detectCycleInUDG(graph);
    }
  }

  /**
   * idea is to do DFS and pass a stack to the dfs method if the path from the
   * current node ends with a sink node then detectCycleInDG(Vertex<T> vertex,
   * HashSet<Long> visited, Stack<Vertex<T>> stack) return false if there is a
   * cycle, the method will definitely comeback to the root node in which case
   * we dont proceed with the recursion but check in the path stack. Also we
   * will have to pop out the current node from stack before recursion unwinds
   * the current method call
   *
   * @param graph
   * @return
   */
  static private <T> boolean detectCycleInDG(Graph<T> graph) {
    Set<Vertex<T>> whiteSet = new HashSet<>(); // nodes are selected for dfs.
    Set<Vertex<T>> graySet = new HashSet<>(); // nodes whose dfs is ongoing.
    Set<Vertex<T>> blackSet = new HashSet<>(); // nodes whose dfs is fully complete.

    whiteSet.addAll(graph.getAllVertexes());

    while (!whiteSet.isEmpty()) {
      Vertex<T> current = whiteSet.iterator().next();
      if (detectCycleInDG(current, whiteSet, graySet, blackSet)) {
        return true;
      }
    }
    return false;
  }

  static private <T> boolean detectCycleInDG(Vertex<T> current, Set<Vertex<T>> whiteSet,
                                             Set<Vertex<T>> graySet, Set<Vertex<T>> blackSet) {
    // move current to gray set from white set and then explore it.
    moveVertex(current, whiteSet, graySet);
    for (Vertex<T> neighbor : current.getAdjacentVertexes()) {
      // if in black set means already explored so continue.
      if (blackSet.contains(neighbor)) {
        continue; // dfs already done so skip
      }
      // if in gray set then cycle found.
      if (graySet.contains(neighbor)) {
        return true;
      }
      if (detectCycleInDG(neighbor, whiteSet, graySet, blackSet)) {
        return true;
      }
    }
    // move vertex from gray set to black set when done exploring.
    moveVertex(current, graySet, blackSet);
    return false;
  }

  static private <T> void moveVertex(Vertex<T> vertex, Set<Vertex<T>> sourceSet,
                                     Set<Vertex<T>> destinationSet) {
    sourceSet.remove(vertex);
    destinationSet.add(vertex);
  }

  /**
   * 1. create a disjoint set of all vertexes in graph 2. iterate over all the
   * edges and start putting connected nodes in a single set. 3. if the two
   * nodes being added belong to same set, ie have the same set
   * representative, then there is a cycle.
   *
   * @param graph
   * @return
   */
  static private <T> boolean detectCycleInUDG(Graph<T> graph) {
    DisjointSet<UUID> set = new DisjointSet<>();

    // add all vertexes to Disjoint set
    graph.getAllVertexes().stream().map(Vertex::getId).forEach(set::makeSet);

    for (Edge<T> edge : graph.getEdges()) {
      Vertex<T> v1 = edge.getVertex1();
      Vertex<T> v2 = edge.getVertex2();

      // find the set representatives
      Node<UUID> r1 = set.findSet(v1.getId());
      Node<UUID> r2 = set.findSet(v2.getId());

      if (r1.equals(r2)) {
        System.out.println("Cycle detected");
        return true;
      }

      set.union(v1.getId(), v2.getId());
    }
    System.out.println("No cycle detected");
    return false;
  }
}
