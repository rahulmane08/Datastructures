package leetcode.graph.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CourseSchedule2 {
  public static void main(String[] args) {
    CourseSchedule2 util = new CourseSchedule2();
    System.out.println(Arrays.toString(util.findOrder(4, new int[][] {{1, 0}, {2, 0}, {3, 1}, {3, 2}})));
    System.out.println(Arrays.toString(util.findOrder(4, new int[][] {{1, 0}, {3, 2}})));
  }

  public int[] findOrder(int numCourses, int[][] prerequisites) {
    if (prerequisites == null || prerequisites.length == 0) {
      return new int[] {0};
    }
    int[] courseOrder = new int[numCourses];
    Map<Integer, List<Integer>> graph = new HashMap<>();
    Map<Integer, Integer> inDegrees = new HashMap<>();

    // bfs queue only for courses with no pre-req, i.e. courses with indegree = 0;
    Queue<Integer> coursesWithNoPrerequisites = new LinkedList<>();
    graph.keySet().stream()
        .filter(course -> !inDegrees.containsKey(course)).forEach(coursesWithNoPrerequisites::offer);

    int index = 0;
    while (!coursesWithNoPrerequisites.isEmpty()) {
      Integer currentCourse = coursesWithNoPrerequisites.poll();
      courseOrder[index++] = currentCourse;
      for (Integer nextCourse : graph.getOrDefault(currentCourse, Collections.emptyList())) {
        Integer inDegree = inDegrees.compute(nextCourse, (c, d) -> d - 1);
        if (inDegree == 0) {
          coursesWithNoPrerequisites.offer(nextCourse);
        }
      }
    }
    return courseOrder;
  }

  int populateGraph(int[][] prerequisites,
                    final Map<Integer, List<Integer>> graph,
                    final Map<Integer, Integer> inDegrees) {
    int maxCourses = 0;
    for (int[] a : prerequisites) {
      graph.compute(a[1], (pre, courses) -> courses == null ? new ArrayList<>() : courses).add(a[0]);
      inDegrees.compute(a[0], (course, degree) -> degree == null ? 1 : degree + 1);
      maxCourses++;
    }
    return maxCourses;
  }
}
