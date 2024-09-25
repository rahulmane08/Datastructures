package leetcode.graph.hard;

import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import leetcode.graph.Graph;
import leetcode.graph.KosarajuSCC;

/**
 * https://leetcode.com/problems/maximum-employees-to-be-invited-to-a-meeting/description/?envType=problem-list-v2&envId=topological-sort&difficulty=HARD
 */
public class MaximumEmployeesInvitedToMeeting {
  public int maximumInvitations(int[] favorite) {
    Graph graph = new Graph(true);
    for (int i = 0; i < favorite.length; i++) {
      graph.addEdge(new int[] {i, favorite[i]});
    }
    KosarajuSCC kosarajuSCC = new KosarajuSCC();
    List<Stack<Integer>> allStronglyConnectedComponents = kosarajuSCC.getAllStronglyConnectedComponents(graph);
    return allStronglyConnectedComponents.stream().map(Stack::size).max(Comparator.naturalOrder()).get();
  }
}
