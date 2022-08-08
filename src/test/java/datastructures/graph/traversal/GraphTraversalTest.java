package datastructures.graph.traversal;

import datastructures.graph.Graph;
import datastructures.graph.Vertex;
import org.junit.Before;
import org.junit.Test;

public class GraphTraversalTest {

    private Graph<Integer> graph;

    @Before
    public void init() {
        graph = new Graph<>(true);
        Vertex<Integer> a = new Vertex<>(0);
        Vertex<Integer> b = new Vertex<>(1);
        Vertex<Integer> c = new Vertex<>(2);
        Vertex<Integer> d = new Vertex<>(3);
        Vertex<Integer> e = new Vertex<>(5);

        a.addAdjacentVertex(b, 0, true);
        a.addAdjacentVertex(c, 0, true);
        b.addAdjacentVertex(c, 0, true);
        c.addAdjacentVertex(a, 0, true);
        c.addAdjacentVertex(d, 0, true);
        d.addAdjacentVertex(d, 0, true); // create a self cycle

        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);
    }

    @Test
    public void test_traversals() {
        GraphTraversal.bfs(graph);
        GraphTraversal.dfs(graph);
    }
}
