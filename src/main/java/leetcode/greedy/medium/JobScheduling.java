package leetcode.greedy.medium;

import java.util.Arrays;
import java.util.Comparator;

public class JobScheduling {
  public int maxProfit(int[][] jobs) {
    Arrays.sort(jobs, Comparator.comparingInt((int[] a) -> a[0]).reversed());

    int maxTime = 0;
    for (int i = 0; i < jobs.length; i++) {
      maxTime = Math.max(maxTime, jobs[i][1]);
    }

    int[] times = new int[maxTime + 1];
    Arrays.fill(times, -1);

    int profit = 0;
    for (int i = 0; i < jobs.length; i++) {
      for (int j = jobs[i][1]; j > 0; j--) {
        if (times[j] == -1) {
          times[j] = 1;
          profit += jobs[i][0];
          break;
        }
      }
    }
    return profit;
  }

  public static void main(String[] args) {
    JobScheduling util = new JobScheduling();
    System.out.println(util.maxProfit(new int[][] {{10, 1}, {15, 3}, {20, 1}}));
  }
}
