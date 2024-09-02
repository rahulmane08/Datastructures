package datastructures.graph.cycledetection;

import datastructures.graph.Graph;
import datastructures.graph.Vertex;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 * Hamiltonian Cycle in a graoh a cycle from first vertex to last vertex such that every node is visited only once.
 * (0)--(1)--(2)
 * |   / \   |
 * |  /   \  |
 * | /     \ |
 * (3)-------(4)
 * hamiltonian cycle: {0, 3, 4, 2, 1, 0}
 * <p>
 * (0)--(1)--(2)
 * |   / \   |
 * |  /   \  |
 * | /     \ |
 * (3)      (4)
 * no hamiltonian cycle as 1 is visited twice
 *
 * @author rahul
 */
public class HamiltonianCycleDetector {
  static public <T> List<Vertex<T>> getHamiltonianCycle(Graph<T> graph) {
    if (graph == null || graph.getAllVertexes().isEmpty()) {
      return null;
    }

    Vertex<T> startVertex = graph.getAllVertexes().get(0);
    int totalVertexes = graph.getAllVertexes().size();
    HashSet<UUID> visited = new HashSet<>();
    List<Vertex<T>> result = new ArrayList<>();
    if (hasHamiltonianCycle(startVertex, startVertex, totalVertexes, visited, result)) {
      System.out.println("Hamiltonian cycle = " + result);
    } else {
      System.out.println("No hamiltonian cycle");
    }
    return result;
  }

  static private <T> boolean hasHamiltonianCycle(Vertex<T> start, Vertex<T> curr, int totalVertexes,
                                                 HashSet<UUID> visited, List<Vertex<T>> result) {
    visited.add(curr.getId());
    result.add(curr);

    for (Vertex<T> adjVertex : curr.getAdjacentVertexes()) {
      if (start.equals(adjVertex) && totalVertexes == result.size()) {
        return true;
      }

      if (!visited.contains(adjVertex.getId())) {
        if (hasHamiltonianCycle(start, adjVertex, totalVertexes, visited, result)) {
          return true;
        }
      }
    }
    // back track, remove them from visited.
    visited.remove(curr.getId());
    result.remove(curr);
    return false;
  }
}
