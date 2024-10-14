package leetcode.intervals.hard;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import leetcode.intervals.Interval;

public class EmployeeFreeTime {
  public static void main(String[] args) {
    List<List<Interval>> intervals =
        asList(
            asList(new Interval(1, 2), new Interval(5, 6)),
            asList(new Interval(1, 3), new Interval(4, 10))
        );
    EmployeeFreeTime util = new EmployeeFreeTime();
    System.out.println(util.employeeFreeTime(intervals));

    intervals =
        asList(
            asList(new Interval(1, 3), new Interval(6, 7)),
            List.of(new Interval(2, 4)),
            asList(new Interval(2, 5), new Interval(9, 12))
        );
    System.out.println(util.employeeFreeTime(intervals));
  }

  public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
    List<Interval> result = new ArrayList<>();
    Comparator<Interval> comparator = Comparator.comparingInt((Interval i) -> i.start).thenComparingInt(i -> i.end);
    PriorityQueue<Interval> sortedScheduleQ = new PriorityQueue<>(comparator);
    for (List<Interval> employeeSchedule : schedule) { // N log(N)
      for (Interval slot : employeeSchedule) {
        sortedScheduleQ.offer(slot);
      }
    }

    while (!sortedScheduleQ.isEmpty()) { // n log(n)
      Interval first = sortedScheduleQ.poll(); // log(n)
      if (sortedScheduleQ.isEmpty()) {
        break;
      }
      Interval second = sortedScheduleQ.poll(); // log(n)
      if (isOverlapping(first, second)) {
        second = mergeIntervals(first, second);
      } else {
        result.add(difference(first, second));
      }
      sortedScheduleQ.offer(second); // log(n)
    }
    return result;
  }

  private Interval mergeIntervals(Interval first, Interval second) {
    return new Interval(Math.min(first.start, second.start), Math.max(first.end, second.end));
  }

  private Interval difference(Interval first, Interval second) {
    return new Interval(first.end, second.start);
  }

  private boolean isOverlapping(Interval first, Interval second) {
    return (first.start <= second.start && second.start <= first.end)
        || (first.start <= second.end && second.end <= first.end)
        || (second.start <= first.start && first.start <= second.end)
        || (second.start <= first.end && first.end <= second.end);
  }
}
