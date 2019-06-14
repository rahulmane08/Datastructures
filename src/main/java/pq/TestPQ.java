package pq;

import java.util.Arrays;

public class TestPQ {
    public static void main(String[] args) {
        MaxPriorityQueue<Integer> pq = new MaxPriorityQueue<>(2);

        for (int i = 0; i < 10; i++) {
            pq.add(i);
            System.out.println("max = " + pq.peek());
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("poll = " + pq.poll());
            System.out.printf("pq array = %s, size = %d %n", Arrays.toString(pq.toArray()), pq.getSize());
        }
    }
}
