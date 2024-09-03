package datastructures.graph.mst;

import datastructures.graph.Edge;
import datastructures.graph.Graph;
import datastructures.graph.Vertex;
import java.util.Set;
import org.junit.Test;

public class KruskalMSTTest {

  @Test
  public void test_getMST() {
    Graph<String> graph = new Graph(true);
    Vertex<String> A = new Vertex<>("A");
    Vertex<String> B = new Vertex<>("B");
    Vertex<String> C = new Vertex<>("C");
    Vertex<String> D = new Vertex<>("D");

    A.addAdjacentVertex(B, 1, true);
    B.addAdjacentVertex(C, 2, true);
    B.addAdjacentVertex(D, 1, true);
    D.addAdjacentVertex(C, 1, true);
    C.addAdjacentVertex(A, 3, true);

    graph.addVertex(A);
    graph.addVertex(B);
    graph.addVertex(C);
    graph.addVertex(D);

    Set<Edge<String>> mst = KruskalMST.getMST(graph);
    mst.forEach(System.out::println);
  }
}
