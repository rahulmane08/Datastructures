package leetcode.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TarjanArticulationPoint {

  private final Set<Integer> articulationPoints;
  private final HashSet<Integer> visited;
  private final Map<Integer, Integer> visitTimes;
  private final Map<Integer, Integer> lowTimes;
  private final Map<Integer, Integer> parents;
  private final Graph graph;
  private int time;

  public TarjanArticulationPoint(int[][] edges) {
    this.graph = new Graph(edges, false);
    this.visited = new HashSet<>();
    this.visitTimes = new HashMap<>();
    this.lowTimes = new HashMap<>();
    this.parents = new HashMap<>();
    this.articulationPoints = new HashSet<>();
    this.time = 0;
  }

  public Set<Integer> articulationPoints() {
    dfs(graph.getVertexes().stream().findFirst().get());
    return articulationPoints;
  }

  private void dfs(int vertex) {
    this.time++;
    visited.add(vertex);
    visitTimes.put(vertex, time);
    lowTimes.put(vertex, time);
    int childCount = 0;
    boolean isArticulationPoint = false;
    for (Integer neighbor : graph.getNeighbors(vertex)) {
      if (!visited.contains(neighbor)) {
        childCount++;
        parents.put(neighbor, vertex);
        dfs(neighbor);
        Integer visitTimeCurrVertex = visitTimes.get(vertex);
        Integer lowTimeNeighborVertex = lowTimes.get(neighbor);
        if (visitTimeCurrVertex <= lowTimeNeighborVertex) {
          isArticulationPoint = true;
        } else {
          lowTimes.put(vertex, lowTimes.get(neighbor));
        }
      } else {
        //BACKEDGE CONDITION, UPDATE THE LOW TIME FROM THE BACKEDGE VERTEXES VISIT TIME
        lowTimes.put(vertex, Math.min(visitTimes.get(neighbor), lowTimes.get(vertex)));
      }
    }

    if ((isArticulationPoint && parents.get(vertex) != null) || (parents.get(vertex) == null && childCount >= 2)) {
      articulationPoints.add(vertex);
    }
  }
}
