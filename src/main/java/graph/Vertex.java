package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Adjacency List implementation.
 *
 * @param <T>
 */
@Getter
public class Vertex<T> {
    private final UUID id;
    private final T data;
    private List<Edge<T>> edges = new ArrayList<>();
    private List<Vertex<T>> adjacentVertexes = new ArrayList<>();
    private int inDegree = 0;
    private int outDegree = 0;

    @Setter
    private int index = -1; // for adjacency matrix conversion

    public Vertex(T data) {
        this.id = UUID.randomUUID();
        this.data = data;
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }

    public List<Vertex<T>> getAdjacentVertexes() {
        return adjacentVertexes;
    }

    public void incrementInDegree() {
        ++inDegree;
    }

    public void incrementOutDegree() {
        ++outDegree;
    }

    public void decrementInDegree() {
        --inDegree;
    }

    public void decrementOutDegree() {
        --outDegree;
    }

    public Edge<T> addAdjacentVertex(Vertex<T> v, int weight, boolean isDirected) {
        Edge edge = new Edge(this, v, isDirected, weight);
        edges.add(edge);
        adjacentVertexes.add(v);
        if (isDirected) {
            incrementOutDegree();
            v.incrementInDegree();
        }
        return edge;
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
}
