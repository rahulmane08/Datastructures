package queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import stack.Stack;

public class QueueUtils {
    static public <T> void reversify(Queue<T> queue) {
        if (queue == null)
            return;
        Stack<T> stack = new Stack<>(queue.capacity());
        while (!queue.isEmpty())
            stack.push(queue.deque());
        while (!stack.isEmpty())
            queue.enqueue(stack.pop());
    }

    static public void reversify(java.util.Queue<Integer> queue) {
        if (queue == null || queue.isEmpty())
            return;
        int top = queue.poll();
        reversify(queue);
        queue.offer(top);
    }

    static public void reversifyFirstKElements(java.util.Queue<Integer> queue, int k) {
        if (queue == null || queue.isEmpty() || k < 1)
            return;
        if (k > queue.size())
            k = queue.size();
        int i = 0;
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        for (; i < k; i++) {
            stack.push(queue.poll());
        }
        while (!stack.isEmpty())
            queue.offer(stack.pop());
        for (; i < queue.size(); i++) {
            queue.offer(queue.poll());
        }
    }

    /**
     * Idea is to keep the max elements index in the deque and remove the smaller ones until higher one is found
     * As the window moves, an element is removed from dq if :
     * 1. front elements index is lesser than window start.
     * 2. if its less than current element at index i.
     * By this logic the max element is always at the front of dq.
     * <p>
     * 1. populate first window
     * 2. start with next element in the array.
     * 3. first check if the max in prev window is outside window start window:dq.peekFirst() < i - k
     * 4. then check if the current element is greater than current window elements if yes, pop them out and add current
     *
     * @param arr
     * @param k
     */
    static public void printMaxInSlidingWindow(int [] arr, int k) {
        if (arr == null)
            return;
        int n = arr.length;
        if (k >= n)
            k = n;
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i=0; i < (n-k+1); i++) {
            int max = Integer.MIN_VALUE;
            while (!dq.isEmpty() && i > dq.peekFirst())
                dq.pollFirst();
            for (int j=i; j< i+k; j++) {
                if (arr[j] > max)
                    max = arr[j];

                while (!dq.isEmpty() && arr[dq.peekLast()] < arr[j])
                    dq.pollLast();
                dq.offer(j);
            }
            System.out.printf("Window = [%d], max = %d%n",i, max);
        }
    }

    /**
     * 0: Empty cell
     * 1: Cells have fresh oranges
     * 2: Cells have rotten oranges
     * A rotten orange at index [i,j] can rot other fresh orange at indexes [i-1,j], [i+1,j], [i,j-1], [i,j+1]
     *
     * @param orangeBox
     * @param m
     * @param n
     */
    static public void printTimeToRotOranges(int[][] orangeBox, int m, int n) {
        Queue<Vertex> vertices = new Queue<Vertex>(m * n);
        /** put all rotten orange vertices in the queue to begin with **/
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (orangeBox[i][j] == 2)
                    vertices.enqueue(new Vertex(i, j));
        /** enter a Dummy vertex to mark end of current time frame **/
        Vertex dummy = new Vertex();
        vertices.enqueue(dummy);
        int time = 0;

        /** bfs over the queue **/
        while (!vertices.isEmpty()) {
            boolean flag = false;
            while (!(vertices.top() == dummy)) {
                Vertex curr = vertices.deque();
                /** right orange **/
                Vertex right = new Vertex(curr.x + 1, curr.y);
                Vertex left = new Vertex(curr.x - 1, curr.y);
                Vertex up = new Vertex(curr.x, curr.y - 1);
                Vertex down = new Vertex(curr.x, curr.y + 1);
                Vertex[] neighbors = new Vertex[]{left, right, up, down};
                for (Vertex sibling : neighbors) {
                    if (isValidIndex(m, n, sibling.x, sibling.y) && orangeBox[sibling.x][sibling.y] == 1) {
                        if (!flag) {
                            flag = true;
                            ++time;
                        }
                        orangeBox[sibling.x][sibling.y] = 2;
                        vertices.enqueue(sibling);
                    }
                }
            }
            vertices.deque();
            if (!vertices.isEmpty())
                vertices.enqueue(dummy);
        }
        System.out.println("Time frame to rot all oranges = " + time);
    }

    static private boolean isValidIndex(int m, int n, int x, int y) {
        return !((x < 0) || (x >= m) || (y < 0) || (y >= n));
    }

    static public void printLargestMultipleOf3(int[] arr) {
        int n = arr.length;
        int[] largestMultiple = new int[arr.length];
        Arrays.sort(arr);
        Queue<Integer> q0 = new Queue<>(arr.length);
        Queue<Integer> q1 = new Queue<>(arr.length);
        Queue<Integer> q2 = new Queue<>(arr.length);
        boolean cantForm = false;
        int sum = 0;
        for (int i : arr) {
            sum += i;
            if (i % 3 == 0)
                q0.enqueue(i);
            else if (i % 3 == 1)
                q1.enqueue(i);
            else
                q2.enqueue(i);
        }
        if (sum % 3 == 1) {
            if (!q1.isEmpty())
                q1.deque();
            else {
                if (!q2.isEmpty()) {
                    q2.deque();
                    if (!q2.isEmpty())
                        q2.deque();
                    else
                        cantForm = true;
                } else
                    cantForm = true;
            }
        } else if (sum % 3 == 2) {
            if (!q2.isEmpty())
                q2.deque();
            else {
                if (!q1.isEmpty()) {
                    q1.deque();
                    if (!q2.isEmpty())
                        q1.deque();
                    else
                        cantForm = true;
                } else
                    cantForm = true;
            }
        }
        if (cantForm) {
            System.out.println("No Largest multiple of 3 cant be formed");
            return;
        }
        List<Queue<Integer>> queues = Arrays.asList(new Queue[]{q0, q1, q2});
        int i = 0;
        for (Queue<Integer> currQ : queues)
            while (!currQ.isEmpty()) {
                largestMultiple[i++] = currQ.deque();
            }
        Arrays.sort(largestMultiple);
        System.out.print("Largest multiple of 3 = ");
        for (int j = largestMultiple.length - 1; j >= 0; j--)
            System.out.print(largestMultiple[j]);
        System.out.println();
    }
}
