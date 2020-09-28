package queue;

import static org.junit.Assert.assertEquals;
import static queue.QueueUtils.findFirstPointForCircularTour;
import static queue.QueueUtils.findTotalMinMaxSumInSlidingWindow;
import static queue.QueueUtils.generateNBinaryNumbers;
import static queue.QueueUtils.printFirstNegativeInSlidingWindow;
import static queue.QueueUtils.reversify;
import static queue.QueueUtils.reversifyFirstKElements;
import static queue.QueueUtils.timeToRotOranges;

import java.util.LinkedList;

import org.junit.Test;

public class TestQueueUtils {
    @Test
    public void test_reversify() {
        Queue<Integer> queue = new Queue<>(5);
        for (int i = 0; i < 5; i++)
            queue.enqueue(i);
        reversify(queue);
        for (int i = 4; i >= 0; i--)
            assertEquals(i, queue.deque().intValue());
    }

    @Test
    public void test_reversifyRecursively() {
        java.util.Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < 5; i++)
            queue.offer(i);
        reversify(queue);
        for (int i = 4; i >= 0; i--)
            assertEquals(i, queue.poll().intValue());
    }

    @Test
    public void test_reversifyFirstK() {
        java.util.Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < 5; i++)
            queue.offer(i);
        reversifyFirstKElements(queue, 1);
        System.out.println(queue);
    }

    @Test
    public void test_sortQueue() {
        java.util.Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            queue.offer((int) (Math.random() * 100));
        }
        System.out.println(queue);
        new QueueUtils.SortQueueUtil(queue).sort();
        System.out.println(queue);
    }

    @Test
    public void test_printMaxInSlidingWindow() {
        QueueUtils.printMaxInSlidingWindow(new int[]{10, 9, 8, 6, 5, 4, 3, 2, 1}, 3);
    }

    @Test
    public void test_findFirstPointForCircularTour() {

        Vertex[] pumps = {
                new Vertex(4, 6),
                new Vertex(6, 5),
                new Vertex(7, 3),
                new Vertex(4, 5)};
        Vertex expected = new Vertex(6, 5);
        Vertex actual = findFirstPointForCircularTour(pumps);
        System.out.println(actual);
        assertEquals(expected, actual);

        pumps = new Vertex[]{
                new Vertex(6, 5),
                new Vertex(4, 6),
                new Vertex(7, 3),
                new Vertex(4, 5)};
        expected = new Vertex(7, 3);
        actual = findFirstPointForCircularTour(pumps);
        System.out.println(actual);
        assertEquals(expected, actual);

        pumps = new Vertex[]{
                new Vertex(1, 2),
                new Vertex(2, 3),
                new Vertex(4, 5),
                new Vertex(7, 4)};
        expected = new Vertex(7, 4);
        actual = findFirstPointForCircularTour(pumps);
        System.out.println(actual);
        assertEquals(expected, actual);

        pumps = new Vertex[]{
                new Vertex(1, 2),
                new Vertex(2, 3),
                new Vertex(4, 5),
                new Vertex(7, 5)};
        expected = null;
        actual = findFirstPointForCircularTour(pumps);
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void test_generateNBinaryNumbers() {
        generateNBinaryNumbers(10);
    }

    @Test
    public void test_timeToRotOranges() {
        int[][] orangeBox = {
                {2, 1, 0, 2, 1},
                {1, 0, 1, 2, 1},
                {1, 0, 0, 2, 1}};
        assertEquals(2, timeToRotOranges(orangeBox, 3, 4));
        orangeBox = new int[][]{
                {2, 1, 0, 2, 1},
                {0, 0, 1, 2, 1},
                {1, 0, 0, 2, 1}};
        assertEquals(1, timeToRotOranges(orangeBox, 3, 4));
        orangeBox = new int[][]{
                {2, 2, 0, 2, 2},
                {0, 0, 2, 2, 2},
                {2, 0, 0, 2, 2}};
        assertEquals(0, timeToRotOranges(orangeBox, 3, 4));
    }

    @Test
    public void test_findTotalMinMaxSumInSlidingWindow() {
        assertEquals(18, findTotalMinMaxSumInSlidingWindow(new int[]{2, 5, -1, 7, -3, -1, -2}, 4));
    }

    @Test
    public void test_printFirstNegativeInSlidingWindow() {
        printFirstNegativeInSlidingWindow(new int[]{-8, 2, 3, -6, 10}, 2);
        printFirstNegativeInSlidingWindow(new int[]{-8, 2, 3, -6, 10}, 3);
    }
}
