package datastructures.graph.misc;

import datastructures.graph.Graph;
import datastructures.graph.Vertex;
import java.util.Stack;
import org.junit.Assert;
import org.junit.Test;

public class TopologicalSortTest {

  @Test
  public void test() {
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

    Stack<Vertex<Integer>> stack = TopologicalSort.sort(graph);
    Assert.assertEquals(c, stack.peek());
  }
}
