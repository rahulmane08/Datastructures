package graph.shortestpath;

import java.util.*;
import java.util.Map.Entry;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

/**
 * Given a source vertex this algorithm tries to find all the shorted paths from source to all other vertexes.
 *
 * @author rahul
 */
public class DjikstraShortestPath {
    private static final int INF = Integer.MAX_VALUE;

    private static final Comparator<Entry<UUID, Integer>> weightComparator = Comparator.comparingInt(Entry::getValue);

    /*
     * 1. for each vertex set the minweight as INF, for start vertex set it as 0 and parents(start,NULL)
     * 2. for each edge, find the neighbor and if its not in unvisited then skip
     * 3. else and compute if u+uv<v'
     * 4. Find min node
     *
     */
    static public <T> Map<List<Vertex<T>>, Integer> getShortestPaths(Graph<T> graph, Vertex<T> start) {
        Map<Vertex<T>, Vertex<T>> parents = new HashMap<>();
        Map<Vertex<T>, Integer> shortestWeightForVertex = new HashMap<>();
        Map<UUID, Integer> unvisited = new HashMap<>();

        //presently populate visited with all vertices with INF weight
        for (Vertex<T> v : graph.getAllVertexes())
            unvisited.put(v.getId(), INF);

        //set the start node to 0 and its parent as null
        unvisited.put(start.getId(), 0);
        parents.put(start, null);

        while (!unvisited.isEmpty()) {
            Entry<UUID, Integer> minEntry = Collections.min(unvisited.entrySet(), weightComparator);
            Vertex<T> minVertex = graph.getVertex(minEntry.getKey());
            int U = minEntry.getValue();

            //remove the node from unvisited and add it to shortestPath with the weight
            unvisited.remove(minVertex.getId());
            shortestWeightForVertex.put(minVertex, U);

            for (Edge<T> e : minVertex.getEdges()) {
                Vertex<T> adj = (minVertex.equals(e.getVertex1()) ? e.getVertex2() : e.getVertex1());

                // first check if the adjacent node is unvisited
                if (!unvisited.containsKey(adj.getId()))
                    continue;

                //now check if the total weight from start is less that the current weight
                int V = unvisited.get(adj.getId());
                int UV = e.getWeight(); // first initialise to edge weight between current and adjacent node
                if (U + UV < V) {
                    unvisited.put(adj.getId(), U + UV);
                    parents.put(adj, minVertex);
                }
            }
        }

        Map<List<Vertex<T>>, Integer> result = formShortestPaths(parents, shortestWeightForVertex);
        return result;
    }


    public static <T> Map<List<Vertex<T>>, Integer> formShortestPaths(Map<Vertex<T>, Vertex<T>> parents,
                                                                      Map<Vertex<T>, Integer> shortestWeightForVertex) {
        Map<List<Vertex<T>>, Integer> result = new HashMap<>();
        for (Entry<Vertex<T>, Integer> e : shortestWeightForVertex.entrySet()) {
            int minWeight = e.getValue();
            List<Vertex<T>> s = new ArrayList<>();
            s.add(e.getKey());
            for (Vertex<T> parent = parents.get(e.getKey()); parent != null; ) {
                s.add(parent);
                parent = parents.get(parent);
            }
            Collections.reverse(s);
            System.out.println("Path = " + s + " , minWeight = " + minWeight);
            result.put(s, minWeight);
        }
        return result;
    }
}
