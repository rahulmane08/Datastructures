package datastructures.graph;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Data
@EqualsAndHashCode
public class Edge<T> {
    private final Vertex<T> vertex1;
    private final Vertex<T> vertex2;
    private final boolean isDirected;
    @Setter
    private int weight;

    public Edge(Vertex<T> vertex1, Vertex<T> vertex2) {
        this(vertex1, vertex2, false);
    }

    public Edge(Vertex<T> vertex1, Vertex<T> vertex2, boolean isDirected, int weight) {
        this(vertex1, vertex2, isDirected);
        this.weight = weight;
    }

    public Edge(Vertex<T> vertex1, Vertex<T> vertex2, boolean isDirected) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.isDirected = isDirected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge<?> edge = (Edge<?>) o;

        if (isDirected != edge.isDirected) return false;
        if (weight != edge.weight) return false;
        if (vertex1 != null ? !vertex1.equals(edge.vertex1) : edge.vertex1 != null) return false;
        return vertex2 != null ? vertex2.equals(edge.vertex2) : edge.vertex2 == null;
    }

    @Override
    public int hashCode() {
        int result = vertex1 != null ? vertex1.hashCode() : 0;
        result = 31 * result + (vertex2 != null ? vertex2.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s--(%s)--%s %s", vertex1, weight, isDirected ? ">" : "", vertex2);
    }
}
