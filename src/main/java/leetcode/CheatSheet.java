package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CheatSheet {
  public static void main(String[] args) {
    // divide by 2 using shift operator results into a lower int
    System.out.println((2 + 5 >>> 1)); // 3
    System.out.println((2 + 6 >>> 1)); // 4

    // min heap
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.naturalOrder());
    for (int i = 0; i < 5; i++) {
      minHeap.offer(i);
    }

    // max heap
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    for (int i = 0; i < 5; i++) {
      maxHeap.offer(i);
    }

    // list to to array
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 1, 3, 2);
    System.out.println(Arrays.toString(list.toArray(new Integer[0])));
    System.out.println(Arrays.toString(list.toArray(new Integer[list.size()])));

    // Map counting frequencies
    Map<Integer, Long> frequencies =
        list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    System.out.println(frequencies);
    System.out.println(frequencies.entrySet().stream().max(Map.Entry.comparingByValue()).get()); // max key

    // mathematical functions on list
    System.out.println(list.stream().max(Comparator.naturalOrder())); // max
    System.out.println(list.stream().min(Comparator.naturalOrder())); // min
    list.stream().mapToInt(i -> i);
    System.out.println("sum = " + list.stream().mapToInt(i -> i).sum());
    System.out.println("max = " + list.stream().mapToInt(i -> i).max());
    System.out.println("min = " + list.stream().mapToInt(i -> i).min());
    System.out.println("min = " + list.stream().mapToInt(i -> i).average());

    // sort 2d array
    int[][] nums = {{3, 1}, {2, 2}, {1, 10}, {1, 7}, {2, 1}};
    Comparator<int[]> comparator1 = Comparator.comparing(a -> a[0]);
    Arrays.sort(nums, comparator1);
    System.out.println(Arrays.deepToString(nums));
    // multilevel sort
    Comparator<int[]> comparator2 = Comparator.comparing(a -> a[1]);
    Arrays.sort(nums, comparator1.thenComparing(comparator2));
    System.out.println(Arrays.deepToString(nums));

    // string modification
    String s = "abc";
    char[] chars = s.toCharArray();
    char temp = chars[0];
    chars[0] = chars[2];
    chars[2] = temp;
    // the above wont modify actual string
    System.out.println("String after char swap: " + new String(chars));
  }
}
