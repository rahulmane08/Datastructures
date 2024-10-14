package leetcode.arrays.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Task {
  private final int availableAt;
  private final int executionTime;
  private final int id;

  public Task(int availableAt, int executionTime, int id) {
    this.availableAt = availableAt;
    this.executionTime = executionTime;
    this.id = id;
  }

  public int getAvailableAt() {
    return availableAt;
  }

  public int getExecutionTime() {
    return executionTime;
  }

  public int getId() {
    return id;
  }
}

public class SingleThreadedCpu {
  public static void main(String[] args) {
    SingleThreadedCpu util = new SingleThreadedCpu();
    System.out.println(Arrays.toString(util.getOrder(new int[][] {{1, 2}, {2, 4}, {3, 2}, {4, 1}})));
  }

  public int[] getOrderWrong(int[][] tasks) {
    int n = tasks.length;
    int[] executionOrder = new int[n];
    Arrays.sort(tasks, Comparator.comparingInt(a -> a[0]));
    // calculate the total execution time
    int totalExecutionTime = 0;
    for (int[] task : tasks) {
      totalExecutionTime += task[1];
    }
    totalExecutionTime = Math.max(totalExecutionTime, tasks[n - 1][0] + tasks[n - 1][0]);

    PriorityQueue<int[]> availableTasks =
        new PriorityQueue<>(Comparator.comparingInt((int[] a) -> a[1]).thenComparingInt(a -> a[2]));
    int taskIndex = 0;
    int index = 0;

    for (int currentTime = 1; currentTime <= totalExecutionTime; ) {
      while (taskIndex < n && tasks[taskIndex][0] <= currentTime) {
        int[] nextTask = tasks[taskIndex];
        availableTasks.offer(new int[] {nextTask[0], nextTask[1], taskIndex});
        taskIndex++;
      }


      if (!availableTasks.isEmpty() && currentTime >= availableTasks.peek()[0]) {
        // start execution.
        int[] executingTask = availableTasks.poll();
        currentTime += executingTask[1];
        executionOrder[index++] = executingTask[2];
      } else {
        currentTime++;
      }
    }
    return executionOrder;
  }

  public int[] getOrder(int[][] tasks) {
    PriorityQueue<Task> availableTasks =
        new PriorityQueue<>(Comparator.comparing(Task::getAvailableAt).thenComparing(Task::getExecutionTime));
    PriorityQueue<Task> executableTasks =
        new PriorityQueue<>(Comparator.comparing(Task::getExecutionTime).thenComparing(Task::getId));
    int currentTime = Integer.MAX_VALUE;
    for (int i = 0; i < tasks.length; i++) {
      Task task = new Task(tasks[i][0], tasks[i][1], i);
      availableTasks.offer(task);
      currentTime = Math.min(currentTime, task.getExecutionTime());
    }

    int[] executionOrder = new int[tasks.length];
    int index = 0;
    while (!availableTasks.isEmpty() || !executableTasks.isEmpty()) {
      while (!availableTasks.isEmpty() && currentTime >= availableTasks.peek().getAvailableAt()) {
        executableTasks.offer(availableTasks.poll()); // move available tasks for execution.
      }

      Task executingTask;
      if (!executableTasks.isEmpty()) {
        executingTask = executableTasks.poll();
      } else {
        executingTask = availableTasks.poll();
      }
      executionOrder[index++] = executingTask.getId();
      currentTime += executingTask.getExecutionTime();
    }
    return executionOrder;
  }
}
