package graph.misc;

import java.util.*;

import graph.Graph;
import graph.GraphUtils;
import graph.Vertex;

/**
 * A directed graph is strongly connected if there is a path between all pairs of vertices.
 * <p>
 * Its a 2 pass algorithm.
 * 1. Time Complexity: O(V+E)
 * 2. Space Complexity: O(V)
 */
public class KosarajuStronglyConnectedComponent {

    static public <T> List<Set<Vertex<T>>> getComponents(Graph<T> graph) {
        if (graph == null) {
            return null;
        }

        // 1st pass
        Stack<Vertex<T>> stack = TopologicalSort.sort(graph);

        //transpose the graph
        Graph<T> graphT = GraphUtils.transpose(graph);

        //clear the visited to now do second pass
        HashSet<UUID> visited = new HashSet<>();

        //create a list of sets that holds the scc's
        List<Set<Vertex<T>>> result = new ArrayList<>();

        // 2nd pass
        // pop each element from stack , the ones with highest visit times will be at the top,
        // and then do DFS in the transpose
        while (!stack.isEmpty()) {
            //get the vertex from the transposed graph
            Vertex<T> vertex = graphT.getVertex(stack.pop().getId());
            Set<Vertex<T>> set = new LinkedHashSet<>();
            dfsUtilForTranspose(vertex, visited, set);
            if (!set.isEmpty()) {
                result.add(set);
            }
        }
        return result;
    }


    private static <T> void dfsUtilForTranspose(Vertex<T> vertex, HashSet<UUID> visited, Set<Vertex<T>> set) {
        if (visited.contains(vertex.getId())) {
            return;
        }
        visited.add(vertex.getId());
        for (Vertex<T> adjVertex : vertex.getAdjacentVertexes()) {
            dfsUtilForTranspose(adjVertex, visited, set);
        }
        set.add(vertex);
    }
}
