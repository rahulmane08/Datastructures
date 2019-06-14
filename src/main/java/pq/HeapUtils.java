package pq;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import tree.Node;
import tree.TreeUtils;
import utils.Swapper;

public class HeapUtils {
    public static void maxHeapify(int arr[], int n, int i) {
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

    public static void minHeapify(int arr[], int n, int i) {
        int smallest = i;
        int left = 2 * 1 + 1;
        int right = 2 * i + 2;
        if (left < n && arr[left] < arr[i])
            smallest = left;
        if (right < n && arr[right] < arr[i])
            smallest = right;
        if (smallest != i) {
            Swapper.swap(arr, i, smallest);
            maxHeapify(arr, n, smallest);
        }
    }

    public static int kthLargest(int[] arr, int k) {
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

    public static boolean checkIfBtreeIsAMaxHeap(Node root) {
        if (root == null) {
            return true;
        }
        boolean isHeap = true;
        if (root.left != null) {
            isHeap = root.data > root.left.data;
        }
        if (root.right != null) {
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

            // case1(left side heap has more elements)
            if (maxHeap.size() > minHeap.size()) {
                if (x < med) {
                    minHeap.add(maxHeap.remove());
                    maxHeap.add(x);
                } else
                    minHeap.add(x);
                med = (double) (maxHeap.peek() + minHeap.peek()) / 2;
            }

            // case2(both heaps are balanced)
            else if (maxHeap.size() == minHeap.size()) {
                if (x < med) {
                    maxHeap.add(x);
                    med = (double) maxHeap.peek();
                } else {
                    minHeap.add(x);
                    med = (double) minHeap.peek();
                }
            }

            // case3(right side heap has more elements)
            else {
                if (x > med) {
                    maxHeap.add(minHeap.remove());
                    minHeap.add(x);
                } else
                    maxHeap.add(x);
                med = (double) (maxHeap.peek() + minHeap.peek()) / 2;

            }
            System.out.printf("median after reading %d elements: %f%n", i, med);
        }
    }

    public static void convertBstToMinHeap(Node root) {
        if (root == null)
            return;
        int[] inorderedArr = TreeUtils.OrderedArrays.getInstance().toInorderArray(root);
        TreeUtils.OrderedArrays.getInstance().fillTreeWithPreOrderArr(root, inorderedArr);
    }

    public static int[] mergeMaxHeaps(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 == null)
            return null;
        int n = 0;
        int[] arr = null;
        if (arr2 == null) {
            n = arr1.length;
            arr = new int[n];
            System.arraycopy(arr1, 0, arr, 0, arr1.length);
        } else if (arr1 == null) {
            n = arr2.length;
            arr = new int[n];
            System.arraycopy(arr2, 0, arr, arr1.length, arr2.length);
        } else {
            n = arr1.length + arr2.length;
            arr = new int[n];
            System.arraycopy(arr1, 0, arr, 0, arr1.length);
            System.arraycopy(arr2, 0, arr, arr1.length, arr2.length);
        }

        for (int i = (n / 2) - 1; i >= 0; i--)
            maxHeapify(arr, n, i);
        return arr;
    }

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

    private static void addIfPossible(int k, MinPriorityQueue<Integer> minPq, int sum) {
        if (minPq.getSize() < k) {
            minPq.add(sum);
        } else {
            if (minPq.peek() < sum) {
                minPq.poll();
                minPq.add(sum);
            }
        }
    }

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
        char[] charArr = str.toCharArray();
        for (char c : charArr) {
            if (charFreq.get(c) != null) {
                int count = charFreq.get(c);
                ++count;
                charFreq.put(c, count);
            } else {
                charFreq.put(c, 1);
            }
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
     * This Min Heap based solution has same time complexity which is O(nk Log k). But for different sized arrays,
     * this solution works much better.
     * Following is detailed algorithm.
     * 1. Create an output array of size n*k.
     * 2. Create a min heap of size k and insert 1st element in all the arrays into the heap
     * 3. Repeat following steps n*k times.
     * a) Get minimum element from heap (minimum is always at root) and store it in output array.
     * b) Replace heap root with next element from the array from which the element is extracted.
     * If the array doesn’t have any more elements, then replace root with infinite. After replacing the root, heapify the tree.
     */
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
}
