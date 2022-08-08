package datastructures.graph.shortestpath;

import datastructures.graph.Graph;
import datastructures.graph.GraphUtils;

public class FloydWarshallShortestPath {
    private static final int INF = Integer.MAX_VALUE;

    public static <T> int[][] getShortestPaths(Graph<T> graph) {
        if (graph == null)
            return null;

        Integer[][] distance = GraphUtils.getAdjacencyMatrix(graph);
        int[][] path = new int[distance.length][distance.length];
        int V = graph.getAllVertexes().size();
        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                if (distance[i][j] != INF && i != j)
                    path[i][j] = i;
                else
                    path[i][j] = -1;

        for (int k = 0; k < V; k++)
            for (int i = 0; i < V; i++)
                for (int j = 0; j < V; j++) {
                    if (distance[i][k] == INF || distance[j][k] == INF)
                        continue;
                    if (distance[i][j] > distance[i][k] + distance[j][k]) {
                        distance[i][j] = distance[i][k] + distance[j][k];
                        path[i][j] = path[k][j];
                    }
                }

        for (int i = 0; i < V; i++)
            if (distance[i][i] < 0)
                throw new RuntimeException("Graph has a negative weight cycle and hence solution not possible");


        return path;

    }
}
