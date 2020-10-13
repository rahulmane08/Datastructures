package graph;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Edge<T> {
    private final boolean isDirected;
    private final Vertex<T> vertex1;
    private final Vertex<T> vertex2;
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
        if (!vertex1.equals(edge.vertex1)) return false;
        return vertex2.equals(edge.vertex2);
    }

    @Override
    public int hashCode() {
        int result = (isDirected ? 1 : 0);
        result = 31 * result + vertex1.hashCode();
        result = 31 * result + vertex2.hashCode();
        return result;
    }
}
