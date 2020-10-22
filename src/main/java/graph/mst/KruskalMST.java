package graph.mst;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import graph.DisjointSet;
import graph.DisjointSet.Node;
import graph.Edge;
import graph.Graph;
import graph.Vertex;
import interfaces.Greedy;

/**
 * Spanning tree: Is a subgraph of N nodes in the graph that are connected by N-1 edges.
 * MST : A spanning tree with edges with minimum weights
 * <p>
 * Algo:
 * 1. Sort all edges in non decreasing order
 * 2. Add all vertex to disjoint set.
 * 3. Each edge join the vertices in djs IF THEY DONT HAVE SAME PARENT.
 * 4. This makes sure the path between 2 nodes is traversed only once and doing it for edges in non decreasing order
 * make sure we get the path for min weighted edges.
 */
@Greedy
public class KruskalMST {

    static private final Comparator<Edge> weightComparator = Comparator.comparingInt(Edge::getWeight);

    static public <T> void printMST(Graph<T> graph) {
        Set<Edge<T>> mstEdges = getMST(graph);
        for (Edge<T> e : mstEdges) {
            System.out.println(e);
        }
    }

    public static <T> Set<Edge<T>> getMST(Graph<T> graph) {
        if (graph == null)
            return null;

        // sort edges in non-decreasing order.
        TreeSet<Edge<T>> sortedEdges = new TreeSet<>(weightComparator);
        sortedEdges.addAll(graph.getEdges());

        // makeset from all vertexes.
        DisjointSet<UUID> set = new DisjointSet<>();
        graph.getAllVertexes().stream().map(Vertex::getId).forEach(set::makeSet);

        Set<Edge<T>> result = new LinkedHashSet<>();

        // Form a disjoint set that will be equal to MST.
        for (Edge<T> edge : sortedEdges) {
            Vertex<T> v1 = edge.getVertex1();
            Vertex<T> v2 = edge.getVertex2();

            //find the set representatives
            Node<UUID> r1 = set.findSet(v1.getId());
            Node<UUID> r2 = set.findSet(v2.getId());

            if (r1.equals(r2)) {
                continue;
            }
            set.union(v1.getId(), v2.getId());
            result.add(edge);
        }
        return result;
    }
}
