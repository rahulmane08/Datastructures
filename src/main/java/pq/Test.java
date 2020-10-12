package pq;

import static pq.HeapUtils.CheckerUtils.checkIfArrayIsMaxHeap;

public class Test {
    public static void main(String[] args) {
        /*int k = 3;
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
        printMedian1(new int[]{5, 15, 10, 20, 3});

        int[] mergedMaxHeap = mergeMaxHeaps(new Integer[][]{5, 2, 3}, new Integer[][]{10, 8, 9, 6, 5});
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

        arr = new int[]{198, 76, 544, 123, 154, 675};
        System.out.printf("min product of %d nos. of arr:%s = %d%n", 2, Arrays.toString(arr), minProductOfKNumbers(arr, 2));
        arr = new int[]{11, 8, 5, 7, 5, 100};
        System.out.printf("min product of %d nos. of arr:%s = %d%n", 4, Arrays.toString(arr), minProductOfKNumbers(arr, 4));

        arr = new int[]{1, 23, 12, 9, 30, 2, 50};
        printKLargestElements(arr, 3);

        printKMaxSumsOfTwoEquallySizedArrays(new int[]{1, 4, 2, 3}, new int[]{2, 5, 1, 6}, 4);

        arr = new int[]{5, 7, 5, 5, 1, 2, 2};
        k = 3;
        System.out.printf("Removing %d elements from arr: %s the max number of distinct elements: %d%n",
                k, Arrays.toString(arr), maxNumberOfDistinctElementsAfterKRemovals(arr, k));
        arr = new int[]{1, 2, 3, 4, 5, 6, 7};
        k = 5;
        System.out.printf("Removing %d elements from arr: %s the max number of distinct elements: %d%n",
                k, Arrays.toString(arr), maxNumberOfDistinctElementsAfterKRemovals(arr, k));

        arr = new int[]{2, 3, 15, 5, 4, 45, 80, 6, 150, 77, 120};
        printAllElementsLessThanXInMinHeap(arr, 15);
        printAllElementsLessThanXInMinHeap(arr, 80);


        arr = new int[]{3, 1, 4, 4, 5, 2, 6, 1};
        k = 2;
        System.out.printf("Top %d elements by frequency in arr:%s%n", k, Arrays.toString(arr));
        printTopKNumbersWithMaxFrequency(arr, k);
        arr = new int[]{7, 10, 11, 5, 2, 5, 5, 7, 11, 8, 9};
        k = 4;
        System.out.printf("Top %d elements by frequency in arr:%s%n", k, Arrays.toString(arr));
        printTopKNumbersWithMaxFrequency(arr, k);

        printMinProductOfKIntegers(new int[]{198, 76, 544, 123, 154, 675}, 2);
        printMinProductOfKIntegers(new int[]{11, 8, 5, 7, 5, 100}, 4);

        Collection<Long> sizes = Arrays.asList(5l);
        System.out.println(sizes.stream().allMatch(x -> x.intValue() == 5));*/
    }
}
