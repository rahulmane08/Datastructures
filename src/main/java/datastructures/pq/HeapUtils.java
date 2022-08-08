package datastructures.pq;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;

import datastructures.array.ArrayUtils;
import interfaces.Hard;
import interfaces.Important;
import interfaces.Medium;
import datastructures.tree.Node;
import datastructures.tree.TreeUtils;
import utils.Swapper;

public class HeapUtils {
    public static void maxHeapify(Integer[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && arr[left] > arr[largest])
            largest = left;
        if (right < n && arr[right] > arr[largest])
            largest = right;
        if (largest != i) {
            Swapper.swap(arr, i, largest);
            maxHeapify(arr, n, largest);
        }
    }

    public static void minHeapify(Integer[] arr, int n, int i) {
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && arr[left] < arr[smallest])
            smallest = left;
        if (right < n && arr[right] < arr[smallest])
            smallest = right;
        if (smallest != i) {
            Swapper.swap(arr, i, smallest);
            maxHeapify(arr, n, smallest);
        }
    }

    /**
     * if we are given 4 ropes of lengths 4, 3, 2 and 6. We can connect the ropes in following ways.
     * 1) First connect ropes of lengths 2 and 3. Now we have three ropes of lengths 4, 6 and 5.
     * 2) Now connect ropes of lengths 4 and 5. Now we have two ropes of lengths 6 and 9.
     * 3) Finally connect the two ropes and all ropes have connected.
     * <p>
     * Total cost for connecting all ropes is 5 + 9 + 15 = 29.
     * This is the optimized cost for connecting ropes.
     * Other ways of connecting ropes would always have same or more cost.
     * For example, if we connect 4 and 6 first (we get three strings of 3, 2 and 10),
     * then connect 10 and 3 (we get two strings of 13 and 2). Finally we connect 13 and 2.
     * Total cost in this way is 10 + 13 + 15 = 38.
     *
     * @param arr
     * @return
     */
    public static int minCostOfConnectingRopes(int[] arr) {
        if (arr == null)
            return -1;
        if (arr.length == 1)
            return arr[0];
        int minCost = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.naturalOrder());
        for (int cost : arr) {
            pq.add(cost);
        }
        while (!pq.isEmpty()) {
            int top = pq.poll();
            if (pq.isEmpty()) {
                break;
            }
            top += pq.poll();
            minCost += top;
            pq.add(top);
        }
        return minCost;
    }

    public static int heightOfBinaryHeap(int n) {
        return (int) (Math.ceil(Math.log(n + 1) / Math.log(2)) - 1);
    }

    /**
     * Given that integers are being read from a data stream.
     * Find median of all the elements read so far starting from the first integer till the last integer.
     * This is also called Median of Running Integers.
     * The data stream can be any source of data, example: a file, an array of integers, input stream etc.
     * <p>
     * What is Median?
     * <p>
     * Median can be defined as the element in the data set which separates the higher half of the data sample from the lower half.
     * In other words we can get the median element as, when the input size is odd, we take the middle element of sorted data.
     * If the input size is even, we pick average of middle two elements in sorted stream.
     * <p>
     * Time Complexity: If we omit the way how stream was read, complexity of median finding is O(N log N),
     * as we need to read the stream, and due to heap insertions/deletions.
     *
     * @param a
     */
    @Important
    @Hard
    public static void printMedian(int[] a) {

        double med = a[0];

        // max heap to store the maxHeap half elements
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>
                (Collections.reverseOrder());

        // min heap to store the minHeap half elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        maxHeap.add(a[0]);
        System.out.println("median after reading 1 element: " + med);

        // reading elements of stream one by one
        /* At any time we try to make heaps balanced and
            their sizes differ by at-most 1. If heaps are
            balanced,then we declare median as average of
            min_heap_right.top() and max_heap_left.top()
            If heaps are unbalanced,then median is defined
            as the top element of heap of larger size */
        for (int i = 1; i < a.length; i++) {

            int x = a[i];

            if (maxHeap.size() > minHeap.size()) {
                // case1(left side heap has more elements)
                if (x < med) {
                    minHeap.add(maxHeap.remove());
                    maxHeap.add(x);
                } else
                    minHeap.add(x);
                med = (double) (maxHeap.peek() + minHeap.peek()) / 2;
            } else if (maxHeap.size() < minHeap.size()) {
                // case2 (right side heap has more elements)
                if (x > med) {
                    maxHeap.add(minHeap.remove());
                    minHeap.add(x);
                } else
                    maxHeap.add(x);
                med = (double) (maxHeap.peek() + minHeap.peek()) / 2;

            } else {
                // case2(both heaps are balanced)
                if (x < med) {
                    maxHeap.add(x);
                    med = (double) maxHeap.peek();
                } else {
                    minHeap.add(x);
                    med = (double) minHeap.peek();
                }
            }

            System.out.printf("median after reading %d elements: %f%n", i, med);
        }
    }

    @Important
    @Hard
    public static void printMedian1(int[] arr) {
        if (arr == null || arr.length == 0)
            return;
        int n = arr.length;
        MinPriorityQueue<Integer> minPq = new MinPriorityQueue<>(n / 2);
        MaxPriorityQueue<Integer> maxPq = new MaxPriorityQueue<>(n / 2);
        maxPq.add(arr[0]);
        double median = arr[0];
        System.out.println("Median after reading 1 elements:" + median);
        for (int i = 1; i < n; i++) {
            int x = arr[i];
            if (minPq.getSize() == maxPq.getSize()) {
                if (x < median) {
                    maxPq.add(x);
                    median = maxPq.peek();
                } else {
                    minPq.add(x);
                    median = minPq.peek();
                }
                System.out.printf("Median after reading %d elements:%f%n", i, median);
            } else if (maxPq.getSize() > minPq.getSize()) {
                if (x < median) {
                    minPq.add(maxPq.poll());
                    maxPq.add(x);
                } else {
                    minPq.add(x);
                }
                median = (double) (minPq.peek() + maxPq.peek()) / 2;
                System.out.printf("Median after reading %d elements:%f%n", i, median);
            } else {
                if (x > median) {
                    maxPq.add(minPq.poll());
                    minPq.add(x);
                } else {
                    maxPq.add(x);
                }
                median = (double) (minPq.peek() + maxPq.peek()) / 2;
                System.out.printf("Median after reading %d elements:%f%n", i, median);
            }
        }
    }

    public static void convertBstToMinHeap(Node root) {
        if (root == null)
            return;
        int[] inorderedArr = TreeUtils.OrderedArrays.getInstance().toInorderArray(root);
        TreeUtils.OrderedArrays.getInstance().fillTreeWithPreOrderArr(root, inorderedArr);
    }

    /**
     * 1. get inordered array from tree
     * 2. reverse
     * 3. create a pre-ordered tree
     *
     * @param root
     */
    public static void convertBstToMaxHeap(Node root) {
        if (root == null)
            return;
        int[] inorderedArr = TreeUtils.OrderedArrays.getInstance().toInorderArray(root);
        ArrayUtils.reverse(inorderedArr);
        TreeUtils.OrderedArrays.getInstance().fillTreeWithPreOrderArr(root, inorderedArr);
    }

    @Important
    public static Integer[] mergeMaxHeaps(Integer[] arr1, Integer[] arr2) {
        if (arr1 == null && arr2 == null)
            return null;
        int n = 0;
        Integer[] arr = null;
        if (arr2 == null) {
            n = arr1.length;
            arr = new Integer[n];
            System.arraycopy(arr1, 0, arr, 0, arr1.length);
        } else if (arr1 == null) {
            n = arr2.length;
            arr = new Integer[n];
            System.arraycopy(arr2, 0, arr, arr1.length, arr2.length);
        } else {
            n = arr1.length + arr2.length;
            arr = new Integer[n];
            System.arraycopy(arr1, 0, arr, 0, arr1.length);
            System.arraycopy(arr2, 0, arr, arr1.length, arr2.length);
        }

        for (int i = (n / 2) - 1; i >= 0; i--)
            maxHeapify(arr, n, i);
        return arr;
    }

    /**
     * Input: a[] = {20, -5, -1}
     * k = 3
     * Output: 14
     * Explanation: All sum of contiguous
     * subarrays are (20, 15, 14, -5, -6, -1)
     * so the 3rd largest sum is 14.
     * <p>
     * Input: a[] = {10, -10, 20, -40}
     * k = 6
     * Output: -10
     * Explanation: The 6th largest sum among
     * sum of all contiguous subarrays is -10.
     *
     * @param arr
     * @param k
     * @return
     */
    @Important
    @Hard
    public static int findKthLargestContiguousSum(int[] arr, int k) {
        if (arr == null) {
            return -1;
        }

        MinPriorityQueue<Integer> minPq = new MinPriorityQueue<>(k);
        for (int i = 0; i < arr.length; i++) {
            int sum = arr[i];
            addIfPossible(k, minPq, sum);
            for (int j = i + 1; j < arr.length; j++) {
                sum += arr[j];
                addIfPossible(k, minPq, sum);
            }
        }
        return minPq.peek();
    }

    // minPq that keeps a track of top k largest elements
    private static void addIfPossible(int k, MinPriorityQueue<Integer> minPq, int x) {
        if (minPq.getSize() < k) {
            minPq.add(x);
        } else {
            if (minPq.peek() < x) {
                minPq.poll();
                minPq.add(x);
            }
        }
    }

    // maxPq that keeps a track of top k smallest elements
    private static void addIfPossible(int k, MaxPriorityQueue<Integer> maxPq, int x) {
        if (maxPq.getSize() < k) {
            maxPq.add(x);
        } else {
            if (maxPq.peek() > x) {
                maxPq.poll();
                maxPq.add(x);
            }
        }
    }

    /**
     * Input : arr[] = {6, 5, 3, 2, 8, 10, 9}
     * k = 3
     * Output : arr[] = {2, 3, 5, 6, 8, 9, 10}
     * <p>
     * Input : arr[] = {10, 9, 8, 7, 4, 70, 60, 50}
     * k = 4
     * Output : arr[] = {4, 7, 8, 9, 10, 50, 60, 70}
     *
     * @param arr
     */
    @Important
    @Medium
    static public void sortAlmostSorted(int[] arr) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;
        boolean startFound = false;
        boolean endFound = false;
        for (int i = 0; i < n; i++) {
            if (startFound && endFound)
                break;
            if (start >= end)
                return; // array is already sorted
            if (!startFound && arr[i] < arr[i + 1])
                start++;
            else
                startFound = true;
            if (!endFound && arr[n - i - 2] < arr[n - i - 1])
                end--;
            else
                endFound = true;
        }
        while (end + 1 < n && arr[start] > arr[end + 1])
            end++;

        MinPriorityQueue<Integer> pq = new MinPriorityQueue(end - start + 1);
        for (int i = start; i <= end; i++)
            pq.add(arr[i]);


        for (int i = start; i <= end; i++)
            arr[i] = pq.poll();
    }

    /**
     * Input: aaabc
     * Output: abaca
     * <p>
     * Input: aaabb
     * Output: ababa
     * <p>
     * Input: aa
     * Output: Not Possible
     * <p>
     * Input: aaaabc
     * Output: Not Possible
     *
     * @param str
     */
    static public void printStringWithNonRepeatingChars(String str) {
        Map<Character, Integer> charFreq = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            charFreq.compute(str.charAt(i), (k, v) -> v == null ? 1 : v + 1);
        }
        java.util.PriorityQueue<Map.Entry<Character, Integer>> pq =
                new java.util.PriorityQueue<>((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        for (Map.Entry<Character, Integer> entry : charFreq.entrySet())
            pq.offer(entry);
        String output = "";
        Map.Entry<Character, Integer> prevEntry = null;
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> currEntry = pq.poll();
            Character curr = currEntry.getKey();
            output += curr;
            if (prevEntry != null && prevEntry.getValue() > 0)
                pq.offer(prevEntry);
            currEntry.setValue(currEntry.getValue() - 1);
            prevEntry = currEntry;
        }
        if (output.length() != str.length()) {
            System.out.println("Not possible");
            return;
        }
        System.out.println(output);
    }

    /**
     * Input : arr[] = {5, 7, 5, 5, 1, 2, 2}, k = 3
     * Output : 4
     * Remove 2 occurrences of element 5 and
     * 1 occurrence of element 2.
     * <p>
     * Input : arr[] = {1, 2, 3, 4, 5, 6, 7}, k = 5
     * Output : 2
     * <p>
     * Input : arr[] = {1, 2, 2, 2}, k = 1
     * Output : 1
     *
     * @param arr
     * @param k
     * @return
     */
    public static int maxNumberOfDistinctElementsAfterKRemovals(int[] arr, int k) {
        if (arr == null)
            return -1;
        int n = arr.length;
        if (0 < k && k < n) {
            Map<Integer, Integer> frequencies = new HashMap<>();
            for (int i = 0; i < n; i++) {
                frequencies.compute(arr[i], (key, v) -> v == null ? 1 : v + 1);
            }

            java.util.PriorityQueue<Map.Entry<Integer, Integer>> pq =
                    new java.util.PriorityQueue<>((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
            for (Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
                pq.offer(entry);
            }

            for (int i = 0; i < k; i++) {
                Map.Entry<Integer, Integer> current = pq.poll();
                if (current.getValue() != 1) {
                    // re add if there are more occurences with freq - 1.
                    current.setValue(current.getValue() - 1);
                    pq.offer(current);
                }
            }
            return pq.size();
        }
        return -1;
    }

    /**
     * Input : 198 76 544 123 154 675
     * k = 2
     * Output : 9348
     * We get minimum product after multiplying
     * 76 and 123.
     * <p>
     * Input : 11 8 5 7 5 100
     * k = 4
     * Output : 1400
     */
    public static long minProductOfKNumbers(int[] arr, int k) {
        if (arr == null)
            return 0;
        MaxPriorityQueue<Integer> maxPriorityQueue = new MaxPriorityQueue<>(k);
        for (int x : arr)
            addIfPossible(k, maxPriorityQueue, x);
        long prod = 1;
        while (!maxPriorityQueue.isEmpty())
            prod = prod * maxPriorityQueue.poll();
        return prod;
    }

    /**
     * if given array is [1, 23, 12, 9, 30, 2, 50]
     * and you are asked for the largest 3 elements i.e., k = 3 then your program should print 50, 30 and 23.
     *
     * @param arr
     * @param k
     */
    public static void printKLargestElements(int[] arr, int k) {
        if (arr == null || k > arr.length)
            return;
        MinPriorityQueue<Integer> minPriorityQueue = new MinPriorityQueue<>(k);
        int i = 0;
        for (; i < k; i++)
            minPriorityQueue.add(arr[i]);
        for (; i < arr.length; i++) {
            if (arr[i] > minPriorityQueue.peek()) {
                minPriorityQueue.poll();
                minPriorityQueue.add(arr[i]);
            }
        }
        System.out.print(k + " largest elements in array:[");
        while (!minPriorityQueue.isEmpty()) {
            System.out.print(minPriorityQueue.poll() + " ");
        }
        System.out.println("]");
    }

    /**
     * Examples : Consider the below min heap as
     * common input two both below examples.
     * 2
     * /     \
     * 3        15
     * /    \     / \
     * 5       4  45  80
     * / \     / \
     * 6   150 77 120
     * <p>
     * Input  : x = 15
     * Output : 2 3 5 6 4
     * <p>
     * Input  : x = 80
     * Output : 2 3 5 6 4 77 15 45
     *
     * @param arr
     * @param x
     */
    public static void printAllElementsLessThanXInMinHeap(int[] arr, int x) {
        if (CheckerUtils.checkIfArrayIsMinHeap(arr)) {
            System.out.printf("Elements in %s less than %d: [", Arrays.toString(arr), x);
            printAllElementsLessThanXInMinHeapUtil(arr, x, 0, arr.length - 1);
            System.out.println("]");
        }
    }

    private static void printAllElementsLessThanXInMinHeapUtil(int[] arr, int x, int index, int n) {
        if (index > n || arr[index] >= x)
            return;
        System.out.print(arr[index] + " ");
        printAllElementsLessThanXInMinHeapUtil(arr, x, 2 * index + 1, n);
        printAllElementsLessThanXInMinHeapUtil(arr, x, 2 * index + 2, n);
    }

    public static void printTopKNumbersWithMaxFrequency(int[] arr, int k) {
        if (arr == null)
            return;
        if (k < 0)
            return;
        if (k > arr.length)
            k = arr.length;
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            frequencies.compute(arr[i], (key, v) -> v == null ? 1 : v + 1);
        }

        // min priority queue
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(k,
                Comparator.comparingInt((ToIntFunction<Map.Entry<Integer, Integer>>) Map.Entry::getValue)
                        .thenComparingInt(Map.Entry::getKey));

        for (Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
            if (pq.size() < k)
                pq.add(entry);
            else {
                boolean qualifies = (pq.peek().getValue() < entry.getValue())
                        || (pq.peek().getValue() == entry.getValue() && pq.peek().getKey() < entry.getKey());
                if (qualifies) {
                    pq.poll();
                    pq.add(entry);
                }
            }
        }
        printReverse(pq, entry -> System.out.printf("Element: %d, freq: %d%n", entry.getKey(), entry.getValue()));
    }

    private static <T> void printReverse(PriorityQueue<T> pq, Consumer<T> printFunction) {
        if (pq.isEmpty())
            return;
        T top = pq.poll();
        printReverse(pq, printFunction);
        printFunction.accept(top);
        pq.add(top);
    }

    public static void printMinProductOfKIntegers(int[] arr, int k) {
        if (arr == null || arr.length < k)
            return;
        MaxPriorityQueue<Integer> pq = new MaxPriorityQueue<>(k);
        for (int i : arr) {
            addIfPossible(k, pq, i);
        }
        int product = 1;
        while (!pq.isEmpty())
            product *= pq.poll();
        System.out.printf("Min product of %d elements in arr: %s = %d%n", k, Arrays.toString(arr), product);
    }

    public static int getLeafStartIndex(int[] arr) {
        return (int) Math.floor(arr.length / 2) + 1;
    }

    @Important
    public static void largestDerangement(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        MaxPriorityQueue<Integer> maxPq = new MaxPriorityQueue<>(arr.length);
        for (int i : arr) {
            maxPq.add(i);
        }
        for (int i = 0; i < arr.length; i++) {
            int top = maxPq.poll();
            if (top != arr[i]) {
                arr[i] = top;
            } else {
                // last element but at the same position
                if (maxPq.isEmpty()) {
                    Swapper.swap(arr, i - 1, i);
                } else {
                    int next = maxPq.poll();
                    arr[i] = next;
                    maxPq.add(top);
                }
            }
        }
    }

    @Important
    public static void smallestDerangement(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        MinPriorityQueue<Integer> minPq = new MinPriorityQueue<>(arr.length);
        for (int i : arr) {
            minPq.add(i);
        }
        for (int i = 0; i < arr.length; i++) {
            int top = minPq.poll();
            if (top != arr[i]) {
                arr[i] = top;
            } else {
                // last element but at the same position
                if (minPq.isEmpty()) {
                    Swapper.swap(arr, i - 1, i);
                } else {
                    int next = minPq.poll();
                    arr[i] = next;
                    minPq.add(top);
                }
            }
        }
    }

    public static class KthSmallestLargestUtil {

        /**
         * Time complexity: O(k * log(n))
         * Space complexity: k
         *
         * @param arr
         * @param k
         * @return
         */
        @Important
        public static int kthLargestUsingMaxHeap(int[] arr, int k) {
            if (arr == null)
                return -1;
            int n = arr.length;
            if (k > n)
                return -1;

            MaxPriorityQueue<Integer> pq = new MaxPriorityQueue<>(n);
            for (int i : arr)
                pq.add(i);
            int kthLargest = -1;
            for (int i = 0; i < k; i++)
                kthLargest = pq.poll();
            return kthLargest;
        }

        /**
         * Time complexity = log(k) + (n-k)*log(k)
         *
         * @param arr
         * @param k
         * @return
         */
        @Important
        public static int kthLargestUsingMinHeap(Integer[] arr, int k) {
            if (arr == null || arr.length == 0 || k > arr.length || k < 1)
                return -1;
            int n = arr.length;
            /**
             * min heapify first k elements ie create a Min heap of first K elements
             * log(k)
             * By doing this we already find the kth largest as the min element in the array of k elements.
             * Now is the next element is larger than this min, which means the min is not the kth largest element anymore
             * but its the next largest, so we swap the new element with min of heap and min heapify again so that we get the next
             * min at the 0th positiong which means its the new kth min.
             */
            int i;
            for (i = (k / 2) - 1; i >= 0; i--) {
                minHeapify(arr, k, i);
            }
            // check the remaining elements with the top of Min heap.
            // (n-k)*log(k)
            for (i = k; i < n; i++) {
                if (arr[i] > arr[0]) {
                    Swapper.swap(arr, 0, i); // remove the current min
                    minHeapify(arr, k, 0);  // this will get the 2nd min , 3rd min...
                }
            }
            return arr[0];
        }

        /**
         * Time complexity = log(k) + (n-k)*log(k)
         *
         * @param arr
         * @param k
         * @return
         */
        @Important
        public static int kthSmallestUsingMaxHeap(Integer[] arr, int k) {
            if (arr == null || arr.length == 0 || k > arr.length || k < 1)
                return -1;
            int n = arr.length;
            // min heapify first k elements
            // log(k)
            int i;
            for (i = (k / 2) - 1; i >= 0; i--) {
                maxHeapify(arr, k, i);
            }
            // check the remaining elements with the top of Min heap.
            // (n-k)*log(k)
            for (i = k; i < n; i++) {
                if (arr[i] < arr[0]) {
                    Swapper.swap(arr, 0, i);
                    maxHeapify(arr, k, 0);
                }
            }
            return arr[0];
        }
    }

    public static class CheckerUtils {

        public static boolean checkIfBtreeIsAMaxHeap(Node root) {
            if (root == null) {
                return true;
            }
            boolean isHeap = true;
            if (root.left != null) {
                isHeap = root.data > root.left.data;
            }
            if (isHeap && root.right != null) {
                isHeap = root.data > root.right.data;
            }
            return isHeap && checkIfBtreeIsAMaxHeap(root.left) && checkIfBtreeIsAMaxHeap(root.right);
        }

        public static boolean checkIfArrayIsMaxHeap(int[] arr) {
            return checkIfArrayIsMaxHeap(arr, arr.length, 0);
        }

        public static boolean checkIfArrayIsMaxHeap(int[] arr, int n, int i) {
            if (arr == null) {
                return false;
            }
            if (i > n / 2) {
                return true;
            }
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            boolean isHeap = true;
            if (left < n) {
                isHeap = arr[i] > arr[left];
            }
            if (isHeap && right < n) {
                isHeap = arr[i] > arr[right];
            }
            return isHeap && checkIfArrayIsMaxHeap(arr, n, left) && checkIfArrayIsMaxHeap(arr, n, right);
        }

        public static boolean checkIfArrayIsMinHeap(int[] arr) {
            return checkIfArrayIsMinHeap(arr, arr.length, 0);
        }

        public static boolean checkIfArrayIsMinHeap(int[] arr, int n, int i) {
            if (arr == null) {
                return false;
            }
            if (i > n / 2) {
                return true;
            }
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            boolean isHeap = true;
            if (left < n) {
                isHeap = arr[i] < arr[left];
            }
            if (isHeap && right < n) {
                isHeap = arr[i] < arr[right];
            }
            return isHeap && checkIfArrayIsMinHeap(arr, n, left) && checkIfArrayIsMinHeap(arr, n, right);
        }
    }

    /**
     * This Min Heap based solution has same time complexity which is O(nk Log k). But for different sized arrays,
     * this solution works much better.
     * Following is detailed algorithm.
     * 1. Create an output array of size n*k.
     * 2. Create a min heap of size k and insert 1st element in all the arrays into the heap
     * 3. Repeat following steps n*k times.
     * a) Get minimum element from heap (minimum is always at root) and store it in output array.
     * b) Replace heap root with next element from the array from which the element is extracted.
     * If the array doesn’t have any more elements, then replace root with infinite.
     * After replacing the root, heapify the tree.
     */
    @Important
    public static class MergeKSortedArraysIntoOneUtil {
        public static int[] mergeSortedArraysIntoOne(int[][] arr) {
            if (arr == null)
                return null;
            int n = arr.length;
            int k = arr[0].length;
            PriorityQueue<Node> pq = new PriorityQueue<>(k, Comparator.comparingInt(Node::getData));
            for (int i = 0; i < n; i++)
                pq.add(new Node(arr[i][0], i, 0));
            int[] sorted = new int[n * k];
            int idx = 0;
            while (!pq.isEmpty()) {
                Node minNode = pq.poll();
                sorted[idx++] = minNode.data;

                //extract the next node from same array
                if (minNode.j + 1 < k) {
                    pq.add(new Node(arr[minNode.i][minNode.j + 1], minNode.i, minNode.j + 1));
                }
            }
            return sorted;
        }

        private static class Node {
            int data;
            int i;
            int j;

            Node(int data, int i, int j) {
                this.data = data;
                this.i = i;
                this.j = j;
            }

            public int getData() {
                return data;
            }
        }
    }

    /**
     * Input :  A[] : {3, 2}
     * B[] : {1, 4}
     * K : 2 [Number of maximum sum
     * combinations to be printed]
     * Output : 7    // (A : 3) + (B : 4)
     * 6    // (A : 2) + (B : 4)
     * <p>
     * Input :  A[] : {4, 2, 5, 1}
     * B[] : {8, 0, 3, 5}
     * K : 3
     * Output : 13   // (A : 5) + (B : 8)
     * 12   // (A : 4) + (B :  8)
     * 10   // (A : 2) + (B : 8)
     */
    public static class PrintKMaxSumsOfTwoEquallySizedArraysUtil {
        private final int[] arr1;
        private final int[] arr2;
        private int k;
        private final MaxPriorityQueue<Node> pq = new MaxPriorityQueue<>(k);
        private final HashSet<Node> visited = new HashSet<>();
        private final List<Integer> result = new ArrayList<>();

        public PrintKMaxSumsOfTwoEquallySizedArraysUtil(int[] arr1, int[] arr2, int k) {
            this.arr1 = arr1;
            this.arr2 = arr2;
            this.k = k;
            compute();
        }

        private void compute() {
            if (this.arr1 == null || this.arr2 == null)
                return;
            if (this.arr1.length != this.arr2.length)
                return;
            int n = this.arr1.length;

            Arrays.sort(arr1);
            Arrays.sort(arr2);
            addIfValid(n - 1, n - 1);

            for (int i = 0; i < k && !pq.isEmpty(); i++) {
                Node top = pq.poll();
                result.add(top.data);

                addIfValid(top.x - 1, top.y);
                addIfValid(top.x, top.y - 1);
            }
        }

        public List<Integer> getResult() {
            return result;
        }

        private void addIfValid(int i, int j) {
            if (i >= 0 && j >= 0) {
                int sum = this.arr1[i] + this.arr2[j];
                Node node = new Node(i, j, sum);
                this.pq.add(node);
                this.visited.add(node);
            }
        }

        private static class Node implements Comparable<Node> {
            int x;
            int y;
            int data;

            public Node(int x, int y, int data) {
                this.x = x;
                this.y = y;
                this.data = data;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Node node = (Node) o;
                return x == node.x &&
                        y == node.y;
            }

            @Override
            public int compareTo(Node o) {
                return Integer.valueOf(this.data).compareTo(o.data);
            }
        }
    }
}
