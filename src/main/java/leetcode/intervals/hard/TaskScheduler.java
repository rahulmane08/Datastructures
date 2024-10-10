package leetcode.intervals.hard;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/task-scheduler/description/
 */
public class TaskScheduler {

  class Task {
    Character id;
    Integer freq;
    Integer scheduleTime;
  }

  /**
   * AAABBC
   * A = 3
   * B = 2
   * C = 1
   * <p>
   * n = 5;
   * <p>
   * ct = 0
   * A,
   * A : 2,
   *
   * @param tasks
   * @param n
   * @return
   */
  public int leastInterval(char[] tasks, int n) {
    if (n == 0) {
      return tasks.length;
    }
    Map<Character, Integer> frequencies = new HashMap<>();
    for (char c : tasks) {
      frequencies.compute(c, (task, freq) -> freq == null ? 1 : freq + 1);
    }
    Comparator<Task> comparator = Comparator.comparing((Task t) -> t.freq);
    PriorityQueue<Task> maxHeap = new PriorityQueue<>(comparator.reversed());
    Queue<Task> waitQ = new LinkedList<>();
    for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
      Task task = new Task();
      task.scheduleTime = 0;
      task.freq = entry.getValue();
      task.id = entry.getKey();
      maxHeap.offer(task);
    }
    int currentTime = 0;
    while (!maxHeap.isEmpty() || !waitQ.isEmpty()) {
      currentTime++;
      Task currentTask;
      if (!maxHeap.isEmpty()) {
        currentTask = maxHeap.poll();
      } else {
        currentTask = waitQ.poll();
      }
      if (currentTask.scheduleTime <= currentTime) {
        if (--currentTask.freq != 0) {
          currentTask.scheduleTime += currentTime + n;
          waitQ.offer(currentTask);
        }
      } else {
        waitQ.offer(currentTask);
      }
    }
    return currentTime - 1;
  }

  public static void main(String[] args) {
    TaskScheduler util = new TaskScheduler();
    char[] tasks = {'A', 'C', 'A', 'B', 'D', 'B'};
    System.out.println(util.leastInterval(tasks, 5));
  }
}
