package datastructures.graph.misc;

import datastructures.graph.Graph;
import datastructures.graph.Vertex;
import java.util.List;
import java.util.Set;
import org.junit.Test;

public class KosarajuStronglyConnectedComponentTest {

  @Test
  public void test() {
    Graph graph = new Graph(true);
    Vertex<Integer> _1 = new Vertex<>(1);
    Vertex<Integer> _2 = new Vertex<>(2);
    Vertex<Integer> _3 = new Vertex<>(3);
    Vertex<Integer> _4 = new Vertex<>(4);

    graph.addVertex(_1);
    graph.addVertex(_2);
    graph.addVertex(_3);
    graph.addVertex(_4);

    graph.addEdge(_1.getId(), _2.getId(), 0);
    graph.addEdge(_2.getId(), _3.getId(), 0);
    graph.addEdge(_3.getId(), _1.getId(), 0);
    graph.addEdge(_3.getId(), _4.getId(), 0);

    List<Set<Vertex<Integer>>> components = KosarajuStronglyConnectedComponent.getComponents(graph);
    components.forEach(System.out::println);
  }
}
