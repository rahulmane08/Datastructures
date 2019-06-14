package pq;

import static pq.HeapUtils.checkIfArrayIsMaxHeap;
import static pq.HeapUtils.findKthLargestContiguousSum;
import static pq.HeapUtils.mergeMaxHeaps;
import static pq.HeapUtils.minCostOfConnectingRopes;
import static pq.HeapUtils.printMedian;
import static pq.HeapUtils.printStringWithNonRepeatingChars;
import static pq.HeapUtils.sortAlmostSorted;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int k = 3;
        int[] arr = {1, 10, 9, 8, 2};
        System.out.printf("%d largest element in arr: %s: %d%n", k, Arrays.toString(arr), HeapUtils.kthLargest(arr, k));

        System.out.printf("%s is a max heap: %s%n", Arrays.toString(arr), checkIfArrayIsMaxHeap(arr));
        arr = new int[]{10, 9, 8, 7, 6, 5};
        System.out.printf("%s is a max heap: %s%n", Arrays.toString(arr), checkIfArrayIsMaxHeap(arr));

        arr = new int[]{4, 3, 2, 6};
        System.out.printf("min cost of connecting ropes:%s, cost:%d%n", Arrays.toString(arr), minCostOfConnectingRopes(arr));

        int[][] matrix = {
                {1, 3},
                {2, 4},
                {6, 8}};
        System.out.println("Sorting k sorted arrays into one: " +
                Arrays.toString(HeapUtils.MergeKSortedArraysIntoOneUtil.mergeSortedArraysIntoOne(matrix)));

        arr = new int[]{4, 3, 2, 6};
        MinPriorityQueue<Integer> minPq = new MinPriorityQueue<>(arr.length);
        for (int i : arr) {
            minPq.add(i);
        }
        while (!minPq.isEmpty())
            System.out.println(minPq.poll());

        printMedian(new int[]{5, 15, 10, 20, 3});

        int[] mergedMaxHeap = mergeMaxHeaps(new int[]{5, 2, 3}, new int[]{10, 8, 9, 6, 5});
        System.out.println("is merged heap max heap: " + checkIfArrayIsMaxHeap(mergedMaxHeap));

        arr = new int[]{20, -5, -1};
        k = 3;
        System.out.printf("%d max contiguous sum of arr:%s = %d%n",
                k, Arrays.toString(arr), findKthLargestContiguousSum(arr, k));
        arr = new int[]{10, -10, 20, -40};
        k = 6;
        System.out.printf("%d max contiguous sum of arr:%s = %d%n",
                k, Arrays.toString(arr), findKthLargestContiguousSum(arr, k));

        arr = new int[]{1, 2, 3, 6, 5, 4, 7, 8, 9};
        sortAlmostSorted(arr);
        System.out.println("Sorted arr " + Arrays.toString(arr));
        arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        sortAlmostSorted(arr);
        System.out.println("Sorted arr " + Arrays.toString(arr));
        arr = new int[]{1};
        sortAlmostSorted(arr);
        System.out.println("Sorted arr " + Arrays.toString(arr));
        arr = new int[]{1, 2, 3, 6, 4, 5, 7, 8, 9};
        sortAlmostSorted(arr);
        System.out.println("Sorted arr " + Arrays.toString(arr));
        arr = new int[]{1, 2, 3, 4, 6, 5, 7, 8, 9};
        sortAlmostSorted(arr);
        System.out.println("Sorted arr " + Arrays.toString(arr));
        arr = new int[]{5, 4, 3, 2};
        sortAlmostSorted(arr);
        System.out.println("Sorted arr " + Arrays.toString(arr));

        String str = "aaaabb";
        printStringWithNonRepeatingChars(str);
        str = "aaabb";
        printStringWithNonRepeatingChars(str);
        str = "aaabc";
        printStringWithNonRepeatingChars(str);
    }
}
