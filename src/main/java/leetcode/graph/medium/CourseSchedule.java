package leetcode.graph.medium;

import java.util.ArrayList;
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
    System.out.println(util.canFinish(1, new int[][] {}));
    System.out.println(util.canFinish(4, new int[][] {{0, 1}, {3, 1}, {3, 3}}));
    System.out.println(util.canFinish(1, new int[][] {}));
    System.out.println(util.canFinish(1, new int[][] {{1, 0}, {1, 2}, {0, 1}}));
  }

  public boolean canFinish(int numCourses, int[][] prerequisites) {
    if (prerequisites == null || prerequisites.length == 0) {
      return true;
    }
    Map<Integer, List<Integer>> graph = new HashMap<>();
    Map<Integer, Integer> inDegree = new HashMap<>();
    for (int[] edge : prerequisites) {
      if (edge[0] == edge[1]) {
        return false;
      }
      graph.compute(edge[1], (v, list) -> list == null ? new ArrayList<>() : list).add(edge[0]);
      graph.compute(edge[0], (v, list) -> list == null ? new ArrayList<>() : list);
      inDegree.compute(edge[0], (v, d) -> d == null ? 1 : d + 1);
      inDegree.compute(edge[1], (v, d) -> d == null ? 0 : d);
    }
    Queue<Integer> courseTracker = new LinkedList<>();

    // start with courses with no prerequisites
    inDegree.entrySet().stream().filter(e -> e.getValue() == 0).map(Map.Entry::getKey).forEach(courseTracker::offer);

    if (courseTracker.isEmpty()) {
      // graph has a cycle, so no starting course can be found.
      return false;
    }

    int coursesTaken = 0;
    while (!courseTracker.isEmpty()) {
      Integer currentCourse = courseTracker.poll();
      coursesTaken++;
      for (Integer nextCourse : graph.get(currentCourse)) {
        Integer currentPrerequisites = inDegree.compute(nextCourse, (v, d) -> d - 1);
        if (currentPrerequisites == 0) {
          courseTracker.offer(nextCourse);
        }
      }
    }

    return coursesTaken == graph.size() && numCourses >= coursesTaken;
  }
}
