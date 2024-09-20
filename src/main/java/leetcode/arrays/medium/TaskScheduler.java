package leetcode.arrays.medium;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/task-scheduler/description/
 */
public class TaskScheduler {

  public int leastInterval(char[] tasks, int n) {
    int[] frequencies = new int[26];
    for (char task : tasks) {
      frequencies[task - 'A']++;
    }
    Arrays.sort(frequencies);
    int maxIndex = 0;
    int startIndexForANewTask = 0;
    for (int i = 25; i > -1; i--) {
      if (frequencies[i] != 0) {
        int frequency = frequencies[i] - 1;
        int currentTaskMaxIndex = startIndexForANewTask + frequency * (n + 1);
        maxIndex = Math.max(maxIndex, currentTaskMaxIndex);
        startIndexForANewTask++;
      }
    }
    return maxIndex + 1;
  }

  public static void main(String[] args) {
    TaskScheduler util = new TaskScheduler();
    char[] tasks = {'A', 'C', 'A', 'B', 'D', 'B'};
    System.out.println(util.leastInterval(tasks, 1));
  }
}
