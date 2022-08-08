package datastructures.graph;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

@Data
public class Graph<T> {
    private final boolean isDirected;
    private List<Edge<T>> edges = new ArrayList<>();
    private Map<UUID, Vertex<T>> vertexesMap = new HashMap<>();
    private AtomicInteger vertexIndex = new AtomicInteger(0);

    public Graph() {
        this(false);
    }

    public Graph(boolean isDirected) {
        super();
        this.isDirected = isDirected;
    }

    public void addEdge(UUID id1, UUID id2, int weight) {
        Vertex<T> a = vertexesMap.getOrDefault(id1, null);
        Vertex<T> b = vertexesMap.getOrDefault(id2, null);
        if (a == null || b == null) {
            throw new IllegalArgumentException("vertex absent");
        }

        Edge<T> edge = a.addAdjacentVertex(b, weight, this.isDirected);
        vertexesMap.put(a.getId(), a);
        vertexesMap.put(b.getId(), b);
        edges.add(edge);
    }

    public void addVertex(Vertex<T> vertex) {
        if (vertex == null || vertexesMap.containsKey(vertex.getId())) {
            return;
        }

        if (CollectionUtils.isNotEmpty(vertex.getEdges())) {
            boolean match = vertex.getEdges().stream().map(Edge::isDirected)
                    .allMatch(edgeDirection -> this.isDirected == edgeDirection);
            if (!match) {
                throw new IllegalArgumentException("Graph Vertex direction mismatch");
            }
            vertex.getEdges().forEach(edges::add);
        }
        vertexesMap.put(vertex.getId(), vertex);
        vertex.setIndex(vertexIndex.getAndIncrement());
    }

    public void removeVertex(Vertex<T> v) {
        if (v == null || !vertexesMap.containsKey(v.getId())) {
            return;
        }
        Vertex<T> vertex = vertexesMap.get(v.getId());
        List<Edge<T>> inEdges = vertex.getAdjacentVertexes().stream()
                .map(adj -> adj.removeAdjacentVertex(vertex)).collect(Collectors.toList());
        Set<Edge<T>> outEdges = vertex.removeAllAdjacentVertexes();

        edges.removeAll(inEdges);
        edges.removeAll(outEdges);
        vertexesMap.remove(vertex.getId());
    }

    public long getOutdegree(Vertex<T> v) {
        return getOutdegree(v.getId());
    }

    private long getOutdegree(UUID id) {
        Vertex<T> vertex = vertexesMap.get(id);
        if (vertex == null) {
            return 0;
        }
        return vertex.getAdjacentVertexes().size();
    }

    public long getIndegree(Vertex<T> v) {
        return getIndegree(v.getId());
    }

    private long getIndegree(UUID id) {
        Vertex<T> vertex = vertexesMap.get(id);
        if (vertex == null) {
            return 0;
        }
        return getAllVertexes().stream()
                .filter(adj -> adj != vertex && adj.isAdjacent(vertex))
                .count();
    }

    public Vertex<T> getVertex(UUID id) {
        return vertexesMap.get(id);
    }

    public List<Vertex<T>> getAllVertexes() {
        return vertexesMap.values().stream().collect(Collectors.toList());
    }
}
