package datastructures.graph;

import java.util.Arrays;

import org.junit.Test;

public class GraphUtilTest {

    @Test
    public void test_getAdjacencyMatrix() {
        Graph graph = new Graph(true);
        Vertex<Integer> a = new Vertex<>(1);
        Vertex<Integer> b = new Vertex<>(2);
        Vertex<Integer> c = new Vertex<>(3);
        Vertex<Integer> d = new Vertex<>(4);

        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);

        graph.addEdge(a.getId(), b.getId(), 0);
        graph.addEdge(b.getId(), c.getId(), 0);
        graph.addEdge(c.getId(), a.getId(), 0);
        graph.addEdge(c.getId(), d.getId(), 0);

        Integer[][] adjacencyMatrix = GraphUtils.getAdjacencyMatrix(graph);
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            System.out.println(Arrays.toString(adjacencyMatrix[i]));
        }
    }
}
