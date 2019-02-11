package queue;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

public class TestQueueUtils {
    @Test
    public void test_reversify() {
        Queue<Integer> queue = new Queue<>(5);
        for (int i=0 ; i<5 ; i++)
            queue.enqueue(i);
        QueueUtils.reversify(queue);
        for (int i=4 ; i>=0 ; i--)
            Assert.assertEquals(i, queue.deque().intValue());
    }

    @Test
    public void test_reversifyRecursively() {
        java.util.Queue<Integer> queue = new LinkedList<>();
        for (int i=0 ; i<5 ; i++)
            queue.offer(i);
        QueueUtils.reversify(queue);
        for (int i=4 ; i>=0 ; i--)
            Assert.assertEquals(i, queue.poll().intValue());
    }

    @Test
    public void test_reversifyFirstK() {
        java.util.Queue<Integer> queue = new LinkedList<>();
        for (int i=0 ; i<5 ; i++)
            queue.offer(i);
        QueueUtils.reversifyFirstKElements(queue, 1);
        System.out.println(queue);
    }
}
