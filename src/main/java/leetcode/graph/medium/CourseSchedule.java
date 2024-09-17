package leetcode.graph.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * {{1, 0}, {2, 0}, {3, 1}}
 * <p>
 * 0 -> {1, 2}
 * 1 -> {3}
 */
public class CourseSchedule {
  public static void main(String[] args) {
    CourseSchedule util = new CourseSchedule();
    System.out.println(util.canFinish(2, new int[][] {{0, 1}}));
    System.out.println(util.canFinish(3, new int[][] {{1, 0}, {2, 1}}));
    System.out.println(util.canFinish(2, new int[][] {{0, 1}, {1, 0}}));
    System.out.println(util.canFinish(5, new int[][] {{1, 4}, {2, 4}, {3, 1}, {3, 2}}));
    System.out.println(util.canFinish(4, new int[][] {{0, 1}, {3, 2}}));

//    System.out.println(util.canFinishDfs(2, new int[][] {{0, 1}}));
//    System.out.println(util.canFinishDfs(3, new int[][] {{1, 0}, {2, 1}}));
//    System.out.println(util.canFinishDfs(2, new int[][] {{0, 1}, {1, 0}}));
//    System.out.println(util.canFinishDfs(5, new int[][] {{1, 4}, {2, 4}, {3, 1}, {3, 2}}));
//    System.out.println(util.canFinishDfs(4, new int[][] {{0, 1}, {3, 2}}));
  }

  /*public boolean canFinishDfs(int numCourses, int[][] prerequisites) {
    if (prerequisites == null || prerequisites.length == 0) {
      return true;
    }
    Map<Integer, List<Integer>> graph = new HashMap<>();
    Map<Integer, Integer> inDegrees = new HashMap<>();
    populateGraph(prerequisites, graph, inDegrees);
    Stack<Integer> topSort = topSort(graph);
    System.out.println("topSort : " + topSort);
    return topSort.size() == numCourses;
  }*/

  public boolean canFinish(int numCourses, int[][] prerequisites) {
    if (prerequisites == null || prerequisites.length == 0) {
      return true;
    }
    Map<Integer, List<Integer>> graph = new HashMap<>();
    Map<Integer, Integer> inDegrees = new HashMap<>();
    populateGraph(prerequisites, graph, inDegrees);

    // bfs queue only for courses with no pre-req, i.e. courses with indegree = 0;
    Queue<Integer> coursesWithNoPrerequisites = new LinkedList<>();

    graph.keySet().stream()
        .filter(course -> !inDegrees.containsKey(course)).forEach(coursesWithNoPrerequisites::offer);
    if (coursesWithNoPrerequisites.isEmpty()) {
      return false; // cycle present in graph.
    }

    while (!coursesWithNoPrerequisites.isEmpty()) {
      Integer currentCourse = coursesWithNoPrerequisites.poll();
      numCourses--;
      for (Integer nextCourse : graph.getOrDefault(currentCourse, Collections.emptyList())) {
        Integer inDegree = inDegrees.compute(nextCourse, (c, d) -> d - 1);
        if (inDegree == 0) {
          coursesWithNoPrerequisites.offer(nextCourse);
        }
      }
    }
    return numCourses == 0;
  }

  Map<Integer, List<Integer>> populateGraph(int[][] prerequisites,
                                            final Map<Integer, List<Integer>> graph,
                                            final Map<Integer, Integer> inDegrees) {
    Arrays.stream(prerequisites)
        .forEach(a -> {
          graph.compute(a[1], (pre, courses) -> courses == null ? new ArrayList<>() : courses).add(a[0]);
          inDegrees.compute(a[0], (course, degree) -> degree == null ? 1 : degree + 1);
        });
    return graph;
  }
}
