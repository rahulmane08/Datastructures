package graph.misc;

import java.util.HashSet;
import java.util.Stack;
import java.util.UUID;

import graph.Graph;
import graph.Vertex;

public class TopologicalSort {

    public static <T> Stack<Vertex<T>> sort(Graph<T> graph) {
        if (graph == null || !graph.isDirected())
            return null;
        HashSet<UUID> visited = new HashSet<>();
        Stack<Vertex<T>> stack = new Stack<>();
        //insert into the stack by the visit times. TOPOLOGICAL SORT
        for (Vertex<T> vertex : graph.getAllVertexes()) {
            topologicalSortUtil(vertex, visited, stack);
        }
        System.out.println("topological sort = " + stack);
        return stack;
    }

    /**
     * 1. do a dfs
     * 2. add the current node to stack when all the childrens are visited
     *
     * @param vertex
     * @param visited
     * @param stack
     */
    public static <T> void topologicalSortUtil(Vertex<T> vertex, HashSet<UUID> visited, Stack<Vertex<T>> stack) {
        if (visited.contains(vertex.getId())) {
            return;
        }
        visited.add(vertex.getId());
        for (Vertex<T> adjVertex : vertex.getAdjacentVertexes()) {
            topologicalSortUtil(adjVertex, visited, stack);
        }
        stack.push(vertex);
    }

    /*private static <T> void allTopSortPathsUtil(Graph<T> graph, HashSet<UUID> visited, Stack<Vertex<T>> stack) {
        for (Vertex<T> v : graph.getAllVertexes()) {
            if (!visited.contains(v.getId()) && v.getInDegree() == 0) {

                for (Vertex<T> adj : v.getAdjacentVertexes())
                    adj.decrementInDegree();
                stack.push(v);
                visited.add(v.getId());
                allTopSortPathsUtil(graph, visited, stack);

                visited.remove(v.getId());
                stack.pop();
                for (Vertex<T> adj : v.getAdjacentVertexes())
                    adj.incrementInDegree();

                System.out.println(stack);
            }
        }
    }*/
}
