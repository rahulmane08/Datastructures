package graph.mst;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.UUID;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import lombok.Data;
import lombok.Getter;

/**
 * Structure to hold the vertex and the edge reaching to it having min edge.
 *
 * @author rahul
 */
@Getter
class PrimNode {
    private final UUID vertexID;
    private int edgeWeight;

    public PrimNode(UUID vertexID) {
        super();
        this.vertexID = vertexID;
    }

    public void setEdgeWeight(int edgeWeight) {
        this.edgeWeight = edgeWeight;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((vertexID == null) ? 0 : vertexID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PrimNode other = (PrimNode) obj;
        if (vertexID == null) {
            if (other.vertexID != null)
                return false;
        } else if (!vertexID.equals(other.vertexID))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PrimNode [vertexID=" + vertexID + ", edgeWeight=" + edgeWeight
                + "]";
    }


}

/**
 * Spanning tree = Graph with N vertexes have N-1 edges, which means there are no cycles.
 * MST = Spanning tree with min sum of weights on the edges.
 *
 * @author rahul
 */
public class PrimMST {
    static private final Integer INF = Integer.MAX_VALUE;
    //Comparator based on the weight of PrimNode
    static private Comparator<PrimNode> weightComparator = new Comparator<PrimNode>() {

        @Override
        public int compare(PrimNode o1, PrimNode o2) {
            Integer w1 = o1.getEdgeWeight();
            Integer w2 = o2.getEdgeWeight();
            return w1.compareTo(w2);
        }
    };


    public static <T> void printMST(Graph<T> graph) {
        Map<Vertex<T>, Edge<T>> minEdgesByVertex = new HashMap<>();

        //two structures to query for a vertex in constant time and get min in log(V) time.
        PriorityQueue<PrimNode> primNodeByMinEdgeWeightHeap = new PriorityQueue<>(weightComparator);
        HashMap<UUID, PrimNode> primNodeMap = new HashMap<>();

        boolean start = false;
        //add all vertices to map and priority queue with the first vertex being the start node with weight=0 and rest being INF
        for (Vertex<T> v : graph.getAllVertexes()) {
            PrimNode primNode = new PrimNode(v.getId());
            if (!start) {
                primNode.setEdgeWeight(0);
                start = true;
            } else
                primNode.setEdgeWeight(INF);
            ;
            primNodeMap.put(v.getId(), primNode);
            primNodeByMinEdgeWeightHeap.add(primNode);
        }

        /**
         * 1. pop the min node from the Min heap
         * 2. get all its edges and using each edge find the neighbor
         * 3. check if the edge weight is < neighors weight in the
         *
         */
        while (!primNodeByMinEdgeWeightHeap.isEmpty()) {
            PrimNode primNode = primNodeByMinEdgeWeightHeap.poll();
            Vertex<T> vertex = graph.getVertex(primNode.getVertexID());
            for (Edge<T> e : vertex.getEdges()) {
                Vertex<T> neighbor = e.getVertex2();
                PrimNode neighborPrim = primNodeMap.get(neighbor.getId());
                if (e.getWeight() < neighborPrim.getEdgeWeight()) {
                    //if the edge weight is less that the current prim weight then update the prim weight and add back to PQ.
                    primNodeByMinEdgeWeightHeap.remove(neighborPrim);
                    neighborPrim.setEdgeWeight(e.getWeight());
                    primNodeByMinEdgeWeightHeap.add(neighborPrim);
                    minEdgesByVertex.put(neighbor, e);
                }
            }


        }
        for (Vertex<T> v : minEdgesByVertex.keySet())
            System.out.println(minEdgesByVertex.get(v));


    }
}
