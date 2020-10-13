package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

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
        Vertex a = vertexesMap.getOrDefault(id1, null);
        Vertex b = vertexesMap.getOrDefault(id2, null);
        if (a == null || b == null) {
            throw new IllegalArgumentException("vertex absent");
        }

       Edge<T> edge = a.addAdjacentVertex(b, weight, this.isDirected);
       vertexesMap.put(a.getId(), a);
       vertexesMap.put(b.getId(), b);
       edges.add(edge);

       a.setIndex(vertexIndex.getAndIncrement());
       b.setIndex(vertexIndex.getAndIncrement());
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

    public Vertex<T> getVertex(UUID id) {
        return vertexesMap.get(id);
    }

    public Collection<Vertex<T>> getAllVertexes() {
        return vertexesMap.values();
    }
}
