package leetcode.graph;

import datastructures.advanced.DisjointSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class KruskalMST {
  public static void main(String[] args) {
    int[][] edges = {{1, 2, 3}, {2, 3, 1}, {3, 4, 1}, {1, 4, 1}, {4, 5, 6}, {5, 6, 2}, {3, 5, 5}, {3, 6, 4}};
    KruskalMST kruskalMST = new KruskalMST();
    List<int[]> mst = kruskalMST.mst(edges);
    for (int[] edge : mst) {
      System.out.println(Arrays.toString(edge));
    }
  }

  public List<int[]> mst(int[][] edges) {
    DisjointSet<Integer> disjointSet = new DisjointSet();
    // sort the edges : E * log(E)
    Arrays.sort(edges, Comparator.comparingInt(e -> e[2]));
    for (int[] edge : edges) {
      disjointSet.makeSet(edge[0]);
      disjointSet.makeSet(edge[1]);
    }

    List<int[]> minimumSpanningPath = new ArrayList<>();
    for (int[] edge : edges) {
      int parent1 = disjointSet.findSet(edge[0]);
      int parent2 = disjointSet.findSet(edge[1]);
      if (parent1 == parent2) {
        continue;
      }
      disjointSet.union(edge[0], edge[1]);
      minimumSpanningPath.add(edge);
    }
    return minimumSpanningPath;
  }
}
