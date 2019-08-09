package list;

import static list.ListUtils.ArithematicUtils.addLists;
import static list.ListUtils.ArithematicUtils.subtractLists;
import static list.ListUtils.ArrangementUtils.ReverseInGroupsOfKUtils;
import static list.ListUtils.ArrangementUtils.makeMiddleAsHead;
import static list.ListUtils.ArrangementUtils.moveAllOccurencesOfKToEnd;
import static list.ListUtils.ArrangementUtils.rearrageListInZigZag;
import static list.ListUtils.ArrangementUtils.rearrageOddEvenTogether;
import static list.ListUtils.ArrangementUtils.rearrangeInPlace;
import static list.ListUtils.ArrangementUtils.rearrangeOddAtOddPositionAndEvenAtEvenPosition;
import static list.ListUtils.ArrangementUtils.reverse;
import static list.ListUtils.ArrangementUtils.reverseIteratively;
import static list.ListUtils.ArrangementUtils.reversePairs;
import static list.ListUtils.ArrangementUtils.rotateLeftBy;
import static list.ListUtils.ArrangementUtils.rotateRightBy;
import static list.ListUtils.ArrangementUtils.rotateRightInBlocks;
import static list.ListUtils.DeleteUtils.deleteAlternate;
import static list.ListUtils.DeleteUtils.deleteDupesFromSortedList;
import static list.ListUtils.DeleteUtils.deleteDupesFromUnsortedList;
import static list.ListUtils.DeleteUtils.deleteLastOccurence1;
import static list.ListUtils.DeleteUtils.deleteMiddle;
import static list.ListUtils.DeleteUtils.deleteNAfterMNodes;
import static list.ListUtils.MergeUtils.merge;
import static list.ListUtils.MergeUtils.mergeInPlaceWithoutFirstListPointerMod;
import static list.ListUtils.MergeUtils.mergeIteratively;
import static list.ListUtils.MergeUtils.mergeKLists;
import static list.ListUtils.PalindromeUtils.checkIfListIsPalindrome;
import static list.ListUtils.PalindromeUtils.lengthOfLargestPalindrome;
import static list.ListUtils.SortUtils.insertionSort;
import static list.ListUtils.SortUtils.mergeSort;
import static list.ListUtils.SortUtils.partitionAlternativelyUsingRecursion;
import static list.ListUtils.SortUtils.selectionSort;
import static list.ListUtils.SortUtils.sortAbsolutelySortedList;
import static list.ListUtils.SortUtils.sortAlternativelySortedList;
import static list.ListUtils.SortUtils.sortListOf012;
import static list.ListUtils.UnionIntersection.getIntersection;
import static list.ListUtils.UnionIntersection.getIntersectionPoint;
import static list.ListUtils.UnionIntersection.getUnion;
import static list.ListUtils.containsSublist;
import static list.ListUtils.countPairsMatchingSum;
import static list.ListUtils.detectLoop;
import static list.ListUtils.flattenMultilevelList;
import static list.ListUtils.flattenMultilevelListDepthWise;
import static list.ListUtils.getNthNode;
import static list.ListUtils.getNthNodeFromEnd;
import static list.ListUtils.insertAfterNthNodeFromEnd;
import static list.ListUtils.lexicalCompare;
import static list.ListUtils.partitionlist;
import static list.ListUtils.pointRandomToNextHighestNode;
import static list.ListUtils.printNthNodeFromEnd;
import static list.ListUtils.removeMiddlePointsOfline;
import static list.ListUtils.search;
import static list.ListUtils.searchIteratively;
import static list.ListUtils.swap;
import static list.ListUtils.swapAlternateNodes;
import static list.ListUtils.swapAlternateNodesRecursively;
import static list.ListUtils.swapKthFromStartAndEnd;
import static list.ListUtils.toDouble;

import list.ListUtils.Point;

public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {

        System.out.println("=====LL Searching=================================");
        LinkedList<Integer> list = new LinkedList<>(new Integer[]{1, 2, 3, 4});
        System.out.println("List contains 4: " + search(list.start, 4));
        System.out.println("List contains 4 (iterative): " + searchIteratively(list.start, 4));
        System.out.println("List contains 5: " + search(list.start, 5));
        System.out.println("List contains 5 (iterative): " + searchIteratively(list.start, 5));

        System.out.println("=====Delete M nodes after N nodes=================================");
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        deleteNAfterMNodes(list, 2, 2);
        System.out.println("deleted 2 nodes after 2 nodes: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        deleteNAfterMNodes(list, 6, 2);
        System.out.println("deleted 2 nodes after 6 nodes: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        deleteNAfterMNodes(list, 0, 6);
        System.out.println("deleted 6 nodes after 0 nodes: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        deleteNAfterMNodes(list, 0, 4);
        System.out.println("deleted 4 nodes after 0 nodes: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        deleteNAfterMNodes(list, 4, 2);
        System.out.println("deleted 2 nodes after 4 nodes: " + list);

        System.out.println("=====Delete alternate nodes=================================");
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        deleteAlternate(list, true);
        System.out.println("delete alternate even nodes: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4});
        deleteAlternate(list, true);
        System.out.println("delete alternate even nodes: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        deleteAlternate(list, false);
        System.out.println("delete alternate odd nodes: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        deleteAlternate(list, false);
        System.out.println("delete alternate odd nodes: " + list);

        System.out.println("=====Delete middle node=================================");
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        LinkedList<Integer> clone = (LinkedList<Integer>) list.clone();
        deleteMiddle(clone);
        System.out.println(list + " after deleting middle: " + clone);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4});
        clone = (LinkedList<Integer>) list.clone();
        deleteMiddle(clone);
        System.out.println(list + " after deleting middle: " + clone);

        System.out.println("=====Delete last occurrence=================================");
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 2, 5});
        deleteLastOccurence1(list, 2);
        System.out.println("Deleting last occurence of 2: " + list);
        deleteLastOccurence1(list, 2);
        System.out.println("Deleting last occurence of 2: " + list);
        deleteLastOccurence1(list, 5);
        System.out.println("Deleting last occurence of 2: " + list);
        deleteLastOccurence1(list, 1);
        System.out.println("Deleting last occurence of 2: " + list);

        System.out.println("=====Delete dupes=================================");
        list = new LinkedList<>(new Integer[]{1, 1, 2, 3, 3, 4, 5, 5});
        deleteDupesFromSortedList(list);
        System.out.println("After deleting dupes = " + list);
        deleteDupesFromSortedList(list);
        System.out.println("After deleting dupes = " + list);
        list = new LinkedList<>(new Integer[]{2, 3, 4, 3, 5, 4});
        deleteDupesFromUnsortedList(list);
        System.out.println("After deleting dupes = " + list);
        deleteDupesFromUnsortedList(list);
        System.out.println("After deleting dupes = " + list);

        System.out.println("=====List Arrangements=================================");
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        reverseIteratively(list);
        System.out.println(list);
        reverse(list);
        System.out.println(list);
        ReverseInGroupsOfKUtils.reverse(list, 3);
        System.out.println("After reversing in groups of 3: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        ReverseInGroupsOfKUtils.reverseIteratively(list, 3);
        System.out.println("After iteratively reversing in groups of 3: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        ReverseInGroupsOfKUtils.reverseAlternatively(list, 3);
        System.out.println("After reversing alternatively in groups of 3: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        reversePairs(list);
        System.out.println("After pair reversals: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        rearrageOddEvenTogether(list);
        System.out.println("After re-arranging even odd nodes together: " + list);
        list = new LinkedList<>(new Integer[]{1, 2});
        rearrageOddEvenTogether(list);
        System.out.println("After re-arranging even odd nodes together: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        rearrangeInPlace(list);
        System.out.println("After re-arranging in place: " + list);
        list = new LinkedList<>(new Integer[]{11, 15, 20, 5, 10});
        rearrageListInZigZag(list);
        System.out.println("After zig zag rearrangement: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        rearrageListInZigZag(list);
        System.out.println("After zig zag rearrangement: " + list);
        list = new LinkedList<>(new Integer[]{10, 1, 2, 3, 5, 6, 7, 8});
        rearrangeOddAtOddPositionAndEvenAtEvenPosition(list);
        System.out.println("list after arranging odd nodes at odd position and even at even pos: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        rearrangeOddAtOddPositionAndEvenAtEvenPosition(list);
        System.out.println("list after arranging odd nodes at odd position and even at even pos: " + list);
        list = new LinkedList<>(new Integer[]{1, 1, 1, 1, 1});
        rearrangeOddAtOddPositionAndEvenAtEvenPosition(list);
        System.out.println("list after arranging odd nodes at odd position and even at even pos: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        rotateLeftBy(list, 2);
        System.out.println("Rotate left by 2: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        rotateRightBy(list, 2);
        System.out.println("Rotate right by 2: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        makeMiddleAsHead(list);
        System.out.println("Make middle as head: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4});
        makeMiddleAsHead(list);
        System.out.println("Make middle as head: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        rotateRightInBlocks(list, 3, 1);
        System.out.println("Right rotate by 1 blocks of 3: " + list);

        // Loop detection
        System.out.println("loop present: " + detectLoop(list));
        // palindrome detection
        System.out.println("=====Palindrome questions=================================");
        list = new LinkedList<>(new Integer[]{1, 2, 3, 2, 1});
        System.out.println("is list palindrom: " + checkIfListIsPalindrome(list));
        list = new LinkedList<>(new Integer[]{1, 2, 2, 1});
        System.out.println("is list palindrom: " + checkIfListIsPalindrome(list));
        list = new LinkedList<>(new Integer[]{1, 2, 3, 1});
        System.out.println("is list palindrom: " + checkIfListIsPalindrome(list));
        list = new LinkedList<>(new Integer[]{8, 1, 2, 3, 2, 1, 9, 10});
        System.out.printf("Length of largest palindrome for list: %s : %d%n", list, lengthOfLargestPalindrome(list));
        list = new LinkedList<>(new Integer[]{8, 8, 9, 10});
        System.out.printf("Length of largest palindrome for list: %s : %d%n", list, lengthOfLargestPalindrome(list));
        list = new LinkedList<>(new Integer[]{8, 9, 10});
        System.out.printf("Length of largest palindrome for list: %s : %d%n", list, lengthOfLargestPalindrome(list));

        System.out.println("=====Print nth node from end=================================");
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        printNthNodeFromEnd(list, 3);
        printNthNodeFromEnd(list, 1);
        printNthNodeFromEnd(list, 8);
        System.out.println("=====insert after nth node from end=================================");
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        insertAfterNthNodeFromEnd(list, 3, 10);
        System.out.println("After inserting 10 from the 3rd node from end: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        insertAfterNthNodeFromEnd(list, 7, 10);
        System.out.println("After inserting 10 from the 7th node from end: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        insertAfterNthNodeFromEnd(list, 8, 10);
        System.out.println("After inserting 10 from the 8th node from end: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        insertAfterNthNodeFromEnd(list, 1, 10);
        System.out.println("After inserting 10 from the 1st node from end: " + list);


        System.out.println("=====lexical compare=================================");
        int compare = lexicalCompare(new LinkedList<>(new Character[]{'g', 'e', 'e', 'k'}),
                new LinkedList<>(new Character[]{'g', 'e', 'e', 'k'}));
        System.out.println("lexical compare: " + compare);
        compare = lexicalCompare(new LinkedList<>(new Character[]{'g', 'e', 'e', 'k', 'a'}),
                new LinkedList<>(new Character[]{'g', 'e', 'e', 'k', 'b'}));
        System.out.println("lexical compare: " + compare);
        compare = lexicalCompare(new LinkedList<>(new Character[]{'g', 'e', 'e', 'k', 'a'}),
                new LinkedList<>(new Character[]{'g', 'e', 'e', 'k'}));
        System.out.println("lexical compare: " + compare);

        System.out.println("=====count of pair matching sum=================================");
        int countPairsMatchingSum =
                countPairsMatchingSum(new LinkedList<>(new Integer[]{4, 6, 4}), new LinkedList<>(new Integer[]{5}), 9);
        System.out.println("pairs matching sum=9: " + countPairsMatchingSum);
        countPairsMatchingSum =
                countPairsMatchingSum(new LinkedList<>(new Integer[]{4, 6, 4}), new LinkedList<>(new Integer[]{5}), 11);
        System.out.println("pairs matching sum=11: " + countPairsMatchingSum);
        countPairsMatchingSum =
                countPairsMatchingSum(new LinkedList<>(new Integer[]{4, 6, 4}), new LinkedList<>(new Integer[]{5}), 20);
        System.out.println("pairs matching sum=20: " + countPairsMatchingSum);

        System.out.println("=====get N-th Node from start/ end=================================");
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println("3rd node from end: " + getNthNodeFromEnd(list, 3));
        System.out.println("3rd node from start: " + getNthNode(list, 3));

        System.out.println("=====SORTING=================================");
        list = new LinkedList<>(new Integer[]{1, -2, -3, 4, -5});
        sortAbsolutelySortedList(list);
        System.out.println("After sorting absolutely sorted list: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        sortAbsolutelySortedList(list);
        System.out.println("After sorting absolutely sorted list: " + list);
        list = new LinkedList<>(new Integer[]{6, 4, 5, 10, 7});
        list = mergeSort(list);
        System.out.println("After merge sort: " + list);
        list = new LinkedList<>(new Integer[]{6, 4, 5, 10, 7});
        selectionSort(list);
        System.out.println("After selection sort: " + list);
        list = new LinkedList<>(new Integer[]{6, 4, 5, 10, 7});
        insertionSort(list);
        System.out.println("After insertion sort: " + list);


        list = new LinkedList<>(new Integer[]{1, 4, 2, 3, 5, 2, 3});
        partitionlist(list, 3);
        System.out.println("List after partitioning by 3: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 2});
        partitionlist(list, 3);
        System.out.println("List after partitioning by 3: " + list);
        list = new LinkedList<>(new Integer[]{4, 5, 10, 7});
        partitionlist(list, 3);
        System.out.println("List after partitioning by 3: " + list);

        System.out.println("=====contains sublist=================================");
        list = new LinkedList<>(new Integer[]{1, 4, 2, 3, 5, 2, 3});
        System.out.println("Contains sublist: " + containsSublist(list, new LinkedList<>(new Integer[]{1, 4, 2})));
        System.out.println("Contains sublist: " + containsSublist(list, new LinkedList<>(new Integer[]{3, 5, 2, 3})));
        System.out.println("Contains sublist: " + containsSublist(list, new LinkedList<>(new Integer[]{4, 2, 3, 5})));
        System.out.println("Contains sublist: " + containsSublist(list, new LinkedList<>(new Integer[]{5, 2, 3, 4})));

        System.out.println("After merging: " +
                merge(new LinkedList<>(new Integer[]{1, 2, 4}), new LinkedList<>(new Integer[]{3, 5})));
        System.out.println("After merging: " +
                mergeIteratively(new LinkedList<>(new Integer[]{1, 2, 4}), new LinkedList<>(new Integer[]{3, 5})));

        LinkedList<Integer> list1 = new LinkedList<>(new Integer[]{2, 4, 7, 8, 10});
        LinkedList<Integer> list2 = new LinkedList<>(new Integer[]{1, 3, 12});
        mergeInPlaceWithoutFirstListPointerMod(list1, list2);
        System.out.printf("After merging in place,list1: %s, list2: %s%n", list1, list2);


        list = createMultilevelList();
        flattenMultilevelList(list);
        System.out.println("After flattening: " + list);

        LinkedList<Point> line = new LinkedList<>();
        line.insert(new Point(0, 10));
        line.insert(new Point(1, 10));
        line.insert(new Point(5, 10));
        line.insert(new Point(7, 10));
        line.insert(new Point(7, 5));
        line.insert(new Point(20, 5));
        line.insert(new Point(40, 5));
        removeMiddlePointsOfline(line);
        System.out.println("After removing middle points: " + line);
        line = new LinkedList<>();
        line.insert(new Point(0, 10));
        line.insert(new Point(1, 10));
        line.insert(new Point(5, 10));
        line.insert(new Point(7, 10));
        removeMiddlePointsOfline(line);
        System.out.println("After removing middle points: " + line);
        line = new LinkedList<>();
        line.insert(new Point(0, 1));
        line.insert(new Point(0, 10));
        line.insert(new Point(0, 20));
        line.insert(new Point(0, 30));
        removeMiddlePointsOfline(line);
        System.out.println("After removing middle points: " + line);

        System.out.println("=========move all occurences of k to end=======");
        list = new LinkedList<>(new Integer[]{1, 2, 2, 4, 3});
        moveAllOccurencesOfKToEnd(list, 2);
        System.out.println("moving all 2's at end: " + list);
        moveAllOccurencesOfKToEnd(list, 1);
        System.out.println("moving all 1's at end: " + list);

        LinkedList<Integer>[] lists = new LinkedList[]{
                new LinkedList<>(new Integer[]{1, 2, 10}),
                new LinkedList<>(new Integer[]{3, 5, 12}),
                new LinkedList<>(new Integer[]{6, 7, 8})};
        LinkedList<Integer> merged = mergeKLists(lists);
        System.out.println("After merging 3 lists: " + merged);

        list = createMultilevelList();
        flattenMultilevelListDepthWise(list);
        System.out.println("After flattening list depth wise: " + list);

        list = new LinkedList<>(new Integer[]{1, 10, 2, 8, 3, 7});
        list = sortAlternativelySortedList(list);
        System.out.println("After sorting alternatively sorted list: " + list);

        list = new LinkedList<>(new Integer[]{1, 10, 2, 8, 3, 7});
        LinkedList<Integer> second = partitionAlternativelyUsingRecursion(list);
        System.out.printf("After alt partitioning, first:%s, second:%s%n", list, second);

        list = new LinkedList<>(new Integer[]{1, 1, 2, 0, 0, 2, 1, 0});
        sortListOf012(list);
        System.out.println("Sorting 0,1,2: " + list);

        list = new LinkedList<>(new Integer[]{1, 1, 2});
        System.out.println("To double: " + toDouble(list, 3));

        list1 = new LinkedList<>(new Integer[]{9, 9, 9});
        list2 = new LinkedList<>(new Integer[]{1, 2});
        Double sum = addLists(list1, list2);
        System.out.printf("%s + %s = %f%n", list1, list2, sum);

        list1 = new LinkedList<>(new Integer[]{2, 2});
        list2 = new LinkedList<>(new Integer[]{1, 2, 2});
        Double diff = subtractLists(list1, list2);
        System.out.printf("%s - %s = %f%n", list1, list2, diff);
        list1 = new LinkedList<>(new Integer[]{0});
        list2 = new LinkedList<>(new Integer[]{1, 2, 2});
        diff = subtractLists(list1, list2);
        System.out.printf("%s - %s = %f%n", list1, list2, diff);
        list1 = new LinkedList<>(new Integer[]{1, 1, 2});
        list2 = new LinkedList<>(new Integer[]{1, 2, 2});
        diff = subtractLists(list1, list2);
        System.out.printf("%s - %s = %f%n", list1, list2, diff);

        list = new LinkedList<>(new Integer[]{5, 10, 2, 3});
        pointRandomToNextHighestNode(list);
        System.out.println("==========After pointing random to next highest node=========");
        for (Node<Integer> curr = list.start; curr != null; curr = curr.next) {
            System.out.println(curr + ", random: " + curr.prev);
        }

        System.out.println("==========Node swaps=========");
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        swapAlternateNodes(list);
        System.out.println("Swap alternate nodes: " + list);
        swapAlternateNodesRecursively(list);
        System.out.println("Swap alternate nodes: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        swap(list, 1, 2);
        System.out.println("after swapping 1 and 2: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        swap(list, 1, 3);
        System.out.println("after swapping 1 and 3: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        swap(list, 3, 4);
        System.out.println("after swapping 3 and 4: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        swap(list, 3, 5);
        System.out.println("after swapping 3 and 5: " + list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        swapKthFromStartAndEnd(list, 1);
        System.out.println("swapping 1st nodes from start end: " + list);
        swapKthFromStartAndEnd(list, 3);
        System.out.println("swapping 3rd nodes from start end: " + list);

        System.out.println("==========Union Intersection=========");
        list1 = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        list2 = new LinkedList<>(new Integer[]{5, 10, 3});
        System.out.println("intersection: " + getIntersection(list1, list2));
        list1 = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        list2 = new LinkedList<>(new Integer[]{5, 10, 3});
        System.out.println("union: " + getUnion(list1, list2));
        list1 = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        list2 = new LinkedList<>(new Integer[]{5, 10, 3});
        System.out.println("intersection point: " + getIntersectionPoint(list1, list2));
        list = new LinkedList<>(new Integer[]{99, 2, 3, 4, 5});
        Node<Integer> curr1 = list1.start, curr2 = list2.start;
        for (; curr1.next != null; curr1 = curr1.next) ;
        for (; curr2.next != null; curr2 = curr2.next) ;
        curr1.next = list.start;
        curr2.next = list.start;
        System.out.println("intersection point: " + getIntersectionPoint(list1, list2));
    }

    /**
     * 1-> 2 -> 3
     * |    |
     * 5 <- 4
     *
     * @return
     */
    static LinkedList createLoopedList() {
        LinkedList<Integer> list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        Node<Integer> curr = list.start;
        for (; curr.next != null; curr = curr.next) ;
        curr.next = list.start.next;
        return list;
    }

    /**
     * 1 - 2 - 3 - 9
     * |       |
     * 4 - 5   7 - 8
     * |
     * 10
     *
     * @return
     */
    static LinkedList<Integer> createMultilevelList() {
        LinkedList<Integer> list = new LinkedList<>(new Integer[]{1, 2, 3, 9});
        LinkedList<Integer> level11 = new LinkedList<>(new Integer[]{4, 5});
        LinkedList<Integer> level12 = new LinkedList<>(new Integer[]{7, 8});
        level12.start.prev = new Node<>(10);
        list.start.prev = level11.start;
        list.start.next.next.prev = level12.start;
        return list;
    }

}
