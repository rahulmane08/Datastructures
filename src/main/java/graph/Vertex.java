package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

/**
 * Adjacency List implementation.
 *
 * @param <T>
 */
@Getter
public class Vertex<T> {
    private final T data;
    private final UUID id;
    private final Set<Edge<T>> edges = new HashSet<>();
    private final Set<Vertex<T>> adjacentVertexes = new HashSet<>();

    @Setter
    private int index = -1; // for adjacency matrix conversion

    public Vertex(T data) {
        this.id = UUID.randomUUID();
        this.data = data;
    }

    public Vertex(UUID uuid, T data) {
        this.id = uuid;
        this.data = data;
    }

    public Set<Edge<T>> getEdges() {
        return edges;
    }

    public Set<Vertex<T>> getAdjacentVertexes() {
        return adjacentVertexes;
    }

    public Edge<T> addAdjacentVertex(Vertex<T> v, int weight, boolean isDirected) {
        if (v == null) {
            return null;
        }
        Edge edge = new Edge(this, v, isDirected, weight);
        edges.add(edge);
        adjacentVertexes.add(v);
        if (!isDirected) {
            v.getAdjacentVertexes().add(this);
        }
        return edge;
    }

    public Edge<T> removeAdjacentVertex(Vertex<T> v) {
        if (v == null) {
            return null;
        }

        // remove the edge from edges
        Edge<T> edge = null;
        for (Iterator<Edge<T>> iter = edges.iterator(); iter.hasNext(); ) {
            edge = iter.next();
            if (edge.getVertex1() == this && edge.getVertex2() == v) {
                iter.remove();
                break;
            }
        }

        // remove adjacent node from adjacentVertexes
        for (Iterator<Vertex<T>> iter = adjacentVertexes.iterator(); iter.hasNext(); ) {
            Vertex<T> adj = iter.next();
            if (adj.getId().equals(v.getId())) {
                iter.remove();
                break;
            }
        }

        return edge;
    }

    public boolean isAdjacent(Vertex<T> v) {
        return this.adjacentVertexes.contains(v);
    }

    public Set<Edge<T>> removeAllAdjacentVertexes() {
        List<Edge<T>> allEdges = new ArrayList<>(edges);
        edges.clear();
        adjacentVertexes.clear();
        return edges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex<?> vertex = (Vertex<?>) o;

        return id.equals(vertex.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }
}
