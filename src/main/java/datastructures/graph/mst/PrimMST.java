package datastructures.graph.mst;

import datastructures.graph.Edge;
import datastructures.graph.Graph;
import datastructures.graph.Vertex;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Spanning tree = Graph with N vertexes have N-1 edges, which means there are no cycles.
 * MST = Spanning tree with min sum of weights on the edges.
 *
 * @author rahul
 */
public class PrimMST {
  static private final Integer INF = Integer.MAX_VALUE;

  public static <T> LinkedHashSet<Edge<T>> getMST(Graph<T> graph) {
    Map<Vertex<T>, Edge<T>> minEdgesByVertex = new HashMap<>();

    //two structures to query for a vertex in constant time and get min in log(V) time.
    PriorityQueue<PrimNode> primNodeByMinEdgeWeightHeap =
        new PriorityQueue<>(Comparator.comparingInt(PrimNode::getEdgeWeight));
    HashMap<UUID, PrimNode> primNodeMap = new HashMap<>();

    boolean first = false;
    //add all vertices to map and priority queue with the first vertex being the first node with weight=0 and rest being INF
    for (Vertex<T> v : graph.getAllVertexes()) {
      PrimNode primNode;
      if (!first) {
        primNode = new PrimNode(v.getId(), 0);
        first = true;
      } else {
        primNode = new PrimNode(v.getId(), INF);
      }
      primNodeMap.put(v.getId(), primNode);
      primNodeByMinEdgeWeightHeap.add(primNode);
    }

    while (!primNodeByMinEdgeWeightHeap.isEmpty()) {
      PrimNode primNode = primNodeByMinEdgeWeightHeap.poll();
      Vertex<T> vertex = graph.getVertex(primNode.getVertexID());

      // For each vertex , get its neighbors.
      for (Edge<T> e : vertex.getEdges()) {
        Vertex<T> neighbor = e.getVertex2();
        PrimNode neighborPrim = primNodeMap.get(neighbor.getId());
        if (e.getWeight() < neighborPrim.getEdgeWeight()) {
          //if the edge weight is less that the current prim weight then update the prim weight and add back to PQ.
          primNodeByMinEdgeWeightHeap.remove(neighborPrim);
          neighborPrim.setEdgeWeight(e.getWeight());
          primNodeByMinEdgeWeightHeap.add(neighborPrim);
          minEdgesByVertex.put(neighbor, e);
        }
      }
    }
    return new LinkedHashSet<>(minEdgesByVertex.values());
  }

  /**
   * Structure to hold the vertex and the edge reaching to it having min edge.
   */
  @Data
  @ToString
  @EqualsAndHashCode(onlyExplicitlyIncluded = true)
  @AllArgsConstructor
  static class PrimNode {
    @EqualsAndHashCode.Include
    private final UUID vertexID;
    private int edgeWeight;
  }
}
