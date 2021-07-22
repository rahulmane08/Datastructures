package queue;

import java.util.Deque;
import java.util.*;

import interfaces.Important;
import interfaces.Medium;
import stack.Stack;

public class QueueUtils {
    static public <T> void reversify(Queue<T> queue) {
        if (queue == null)
            return;
        Stack<T> stack = new Stack<>(queue.capacity());
        while (!queue.isEmpty())
            stack.push(queue.poll());
        while (!stack.isEmpty())
            queue.offer(stack.pop());
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
     * Input: arr[] = {1, 2, 3, 1, 4, 5, 2, 3, 6}, K = 3
     * Output: 3 3 4 5 5 5 6
     * Explanation:
     * Maximum of 1, 2, 3 is 3
     * Maximum of 2, 3, 1 is 3
     * Maximum of 3, 1, 4 is 4
     * Maximum of 1, 4, 5 is 5
     * Maximum of 4, 5, 2 is 5
     * Maximum of 5, 2, 3 is 5
     * Maximum of 2, 3, 6 is 6
     * <p>
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
    @Important
    @Medium
    static public void printMaxInSlidingWindow(int[] arr, int k) {
        if (arr == null)
            return;
        int n = arr.length;
        if (k >= n)
            k = n;
        Deque<Integer> dq = new ArrayDeque<>();
        int i = 0;
        for (; i < k; i++) {
            while (!dq.isEmpty() && arr[dq.peekLast()] <= arr[i]) {
                dq.pollLast();
            }
            dq.offerLast(i);
        }
        System.out.printf("max in window(%d, %d) = %d%n", 0, k - 1, arr[dq.peekFirst()]);
        for (; i < n; i++) {
            while (!dq.isEmpty() && dq.peekFirst() < i - k + 1) {
                dq.pollFirst();
            }
            while (!dq.isEmpty() && arr[dq.peekLast()] <= arr[i]) {
                dq.pollLast();
            }
            dq.offerLast(i);
            System.out.printf("max in window(%d, %d) = %d%n", i - k + 1, i, arr[dq.peekFirst()]);
        }
    }

    @Important
    static public int findTotalMinMaxSumInSlidingWindow(int[] arr, int k) {
        int sum = 0;
        int n = arr.length;
        if (k >= n)
            k = n;
        Deque<Integer> maxDq = new ArrayDeque<>();
        Deque<Integer> minDq = new ArrayDeque<>();
        int i = 0;
        for (; i < k; i++) {
            while (!maxDq.isEmpty() && arr[maxDq.peekLast()] <= arr[i]) {
                maxDq.pollLast();
            }
            maxDq.offerLast(i);

            while (!minDq.isEmpty() && arr[minDq.peekLast()] >= arr[i]) {
                minDq.pollLast();
            }
            minDq.offerLast(i);
        }
        sum = arr[maxDq.peekFirst()] + arr[minDq.peekFirst()];
        for (; i < n; i++) {
            while (!maxDq.isEmpty() && maxDq.peekFirst() < (i - k + 1)) {
                maxDq.pollFirst();
            }

            while (!maxDq.isEmpty() && arr[maxDq.peekLast()] <= arr[i]) {
                maxDq.pollLast();
            }
            maxDq.offerLast(i);

            while (!minDq.isEmpty() && minDq.peekFirst() < (i - k + 1)) {
                minDq.pollFirst();
            }

            while (!minDq.isEmpty() && arr[minDq.peekLast()] >= arr[i]) {
                minDq.pollLast();
            }
            minDq.offerLast(i);

            sum += arr[maxDq.peekFirst()] + arr[minDq.peekFirst()];
        }
        return sum;
    }

    public static void printFirstNegativeInSlidingWindow(int[] arr, int k) {
        if (arr == null)
            return;
        int n = arr.length;
        if (k >= n)
            k = n;
        java.util.Queue<Integer> queue = new LinkedList<>();
        int i = 0;
        for (; i < k; i++) {
            if (queue.isEmpty() && arr[i] < 0) {
                queue.offer(i);
            }
        }
        System.out.printf("first negative in window(%d, %d) = %d%n", 0, k - 1, queue.isEmpty() ? 0 : arr[queue.peek()]);
        for (; i < n; i++) {
            while (!queue.isEmpty() && queue.peek() < (i - k + 1)) {
                queue.poll();
            }

            if (queue.isEmpty() && arr[i] < 0) {
                queue.offer(i);
            }
            System.out.printf("first negative in window(%d, %d) = %d%n", i - k + 1, i, queue.isEmpty() ? 0 : arr[queue.peek()]);
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
    @Important
    static public int timeToRotOranges(int[][] orangeBox, int m, int n) {
        Queue<Vertex> vertices = new Queue<Vertex>(m * n);
        /** put all rotten orange vertices in the queue to begin with **/
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (orangeBox[i][j] == 2)
                    vertices.offer(new Vertex(i, j));
        /** enter a Dummy vertex to mark end of current time frame **/
        Vertex dummy = new Vertex();
        vertices.offer(dummy);
        int time = 0;

        /** bfs over the queue **/
        while (!vertices.isEmpty()) {
            boolean flag = false;
            while (vertices.first() != dummy) {
                Vertex curr = vertices.poll();
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
                        vertices.offer(sibling);
                    }
                }
            }
            vertices.poll();
            if (!vertices.isEmpty())
                vertices.offer(dummy);
        }
        System.out.println("Time frame to rot all oranges = " + time);
        return time;
    }

    static private boolean isValidIndex(int m, int n, int x, int y) {
        return !((x < 0) || (x >= m) || (y < 0) || (y >= n));
    }

    @Important
    static public void printLargestMultipleOf3(int[] arr) {
        int[] largestMultiple = new int[arr.length];
        Arrays.sort(arr);
        Queue<Integer> q0 = new Queue<>(arr.length);
        Queue<Integer> q1 = new Queue<>(arr.length);
        Queue<Integer> q2 = new Queue<>(arr.length);
        boolean possible = false;
        int sum = 0;

        for (int i : arr) {
            sum += i;
            if (i % 3 == 0)
                q0.offer(i);
            else if (i % 3 == 1)
                q1.offer(i);
            else
                q2.offer(i);
        }

        if (sum % 3 == 1) {
            if (!q1.isEmpty()) {
                q1.poll();
                possible = true;
            } else if (!q2.isEmpty()) {
                q2.poll();
                if (!q2.isEmpty()) {
                    q2.poll();
                    possible = true;
                }
            }
        } else if (sum % 3 == 2) {
            if (!q2.isEmpty()) {
                q2.poll();
                possible = true;
            } else if (!q1.isEmpty()) {
                q1.poll();
                if (!q1.isEmpty()) {
                    q1.poll();
                    possible = true;
                }
            }
        } else {
            possible = true;
        }

        if (!possible) {
            System.out.println("No Largest multiple of 3 cant be formed");
            return;
        }
        List<Queue<Integer>> queues = Arrays.asList(q0, q1, q2);
        int i = 0;
        for (Queue<Integer> currQ : queues)
            while (!currQ.isEmpty()) {
                largestMultiple[i++] = currQ.poll();
            }
        Arrays.sort(largestMultiple);
        System.out.print("Largest multiple of 3 = ");
        for (int j = largestMultiple.length - 1; j >= 0; j--)
            System.out.print(largestMultiple[j]);
        System.out.println();
    }

    /**
     * {4, 6}, {6, 5}, {7, 3} and {4, 5}
     *
     * @param pumps
     * @return
     */
    @Important
    @Medium
    public static Vertex findFirstPointForCircularTour(Vertex[] pumps) {
        if (pumps == null || pumps.length == 0)
            return null;
        int n = pumps.length;
        int start = 0;
        int end = 1;
        int spare_petrol = pumps[0].x - pumps[0].y;
        while (end != start) {

            while (spare_petrol < 0 && start <= end) {
                spare_petrol -= pumps[start].x - pumps[start].y;
                if (++start == n) {
                    return null; // reached the end of pumps and no spare petrol
                }
            }
            spare_petrol += pumps[end].x - pumps[end].y;
            end = (end + 1) % n;
        }
        if (spare_petrol < 0) {
            return null;
        }
        return pumps[start];
    }

    public static void generateNBinaryNumbers(int N) {
        java.util.Queue<String> queue = new LinkedList();
        queue.offer("1");
        for (int i = 0; i < N; i++) {
            String top = queue.poll();
            System.out.println(top);
            queue.offer(top + "0");
            queue.offer(top + "1");
        }
    }

    public static class SortQueueUtil {
        private final java.util.Queue<Integer> queue;
        private int rearValue = -1;

        public SortQueueUtil(java.util.Queue<Integer> queue) {
            this.queue = queue;
        }

        public void sort() {
            if (this.queue.isEmpty()) {
                return;
            }
            int front = queue.poll();
            sort();
            sortedInsert(front);
        }

        private void sortedInsert(int elem) {
            int size = queue.size();

            if (size == 0 || this.rearValue <= elem) {
                queue.offer(elem);
                this.rearValue = elem;
            } else {
                int i = 0;
                int currentRear = -1;
                for (; i < size && queue.peek() < elem; i++) {
                    queue.offer(queue.poll());
                }
                queue.offer(elem);
                for (; i < size; i++) {
                    currentRear = queue.peek();
                    queue.offer(queue.poll());
                }
                this.rearValue = currentRear;
            }
        }
    }

    public static class StackUsingQueue<T> {
        private Queue<T> queue;

        public T pop() {
            if (queue.isEmpty())
                throw new RuntimeException("Stack is empty , nothing to pop");
            return queue.poll();
        }

        public void push(T elem) {
            int size = queue.size();
            queue.offer(elem);
            if (size > 0) {
                for (int i = 0; i < size - 1; i++) {
                    T curr = queue.poll();
                    queue.offer(curr);
                }
            }
        }
    }
}
