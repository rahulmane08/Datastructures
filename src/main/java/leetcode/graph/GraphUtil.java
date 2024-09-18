package leetcode.graph;

import java.util.Map;
import java.util.stream.Collectors;

public class GraphUtil {
  public static Map<Integer, Integer> vertexesWithInDegree(Graph graph, int indegree) {
    return graph.getInDegrees().entrySet().stream().filter(e -> e.getValue() == indegree)
        .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
  }
}
