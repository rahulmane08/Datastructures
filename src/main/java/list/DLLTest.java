package list;

import static list.DLLUtils.ArrangementUtils.reverse;
import static list.DLLUtils.ArrangementUtils.reverseIteratively;
import static list.DLLUtils.ArrangementUtils.rotateLeftBy;
import static list.DLLUtils.ArrangementUtils.rotateRightBy;
import static list.DLLUtils.DeleteUtils.deleteAllOccurences;
import static list.DLLUtils.DeleteUtils.deleteDupesFromSortedList;
import static list.DLLUtils.DeleteUtils.deleteDupesFromUnsortedList;
import static list.DLLUtils.MergeUtils.merge;
import static list.DLLUtils.SortUtils.insertionSort;
import static list.DLLUtils.SortUtils.mergeSort;
import static list.DLLUtils.SortUtils.sortBiotonicList;
import static list.DLLUtils.printPairsMatchingSum;
import static list.DLLUtils.swap;

public class DLLTest {
    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>(new Integer[]{1, 2, 3});
        reverse(list);
        System.out.println("After reversal: " + list);
        reverseIteratively(list);
        System.out.println("After reversal iteratively: " + list);
        list.delete(1);
        System.out.println("delete 1: " + list);
        list.delete(3);
        System.out.println("delete 3: " + list);
        list.delete(2);
        System.out.println("delete 2: " + list);

        list = new DoublyLinkedList<>(new Integer[]{1, 2, 3});
        swap(list, 1, 2);
        System.out.println("After swapping 1 and 2: " + list);
        swap(list, 3, 2);
        System.out.println("After swapping 3 and 2: " + list);
        swap(list, 2, 3);
        System.out.println("After swapping 2 and 3: " + list);
        swap(list, 1, 3);
        System.out.println("After swapping 1 and 3: " + list);

        list = new DoublyLinkedList<>(new Integer[]{1, 3, 10});
        DoublyLinkedList<Integer> list1 = new DoublyLinkedList<>(new Integer[]{1, 4, 11});
        list = merge(list, list1);
        System.out.println("After merging: " + list);
        list = new DoublyLinkedList<>(new Integer[]{5, 4, 3, 21, 1});
        mergeSort(list);
        System.out.println("After merge sorting: " + list);
        list = new DoublyLinkedList<>(new Integer[]{5, 4, 3, 21, 1, 6});
        insertionSort(list);
        System.out.println("After insertion sorting: " + list);

        list = new DoublyLinkedList<>(new Integer[]{5, 4, 3, 2, 1});
        printPairsMatchingSum(list, 5);

        list = new DoublyLinkedList<>(new Integer[]{5, 5, 5, 4, 3, 2, 1});
        deleteDupesFromSortedList(list);
        System.out.println("After dupe deleting: "+ list);
        list = new DoublyLinkedList<>(new Integer[]{5, 5, 5, 4, 4, 4,3, 3, 2, 1});
        deleteDupesFromSortedList(list);
        System.out.println("After dupe deleting: "+ list);
        list = new DoublyLinkedList<>(new Integer[]{1,1,1,1,1});
        deleteDupesFromSortedList(list);
        System.out.println("After dupe deleting: "+ list);
        list = new DoublyLinkedList<>(new Integer[]{5, 4, 3, 21, 5, 1, 6, 6});
        deleteDupesFromUnsortedList(list);
        System.out.println("After dupe deleting: "+ list);
        list = new DoublyLinkedList<>(new Integer[]{5, 4, 3, 21, 5, 1, 6, 6});
        deleteAllOccurences(list, 5);
        System.out.println("After deleting all 5's: "+ list);
        deleteAllOccurences(list, 6);
        System.out.println("After deleting all 6's: "+ list);
        list = new DoublyLinkedList<>(new Integer[]{1,1,1,1});
        deleteAllOccurences(list, 1);
        System.out.println("After deleting all 1's: "+ list);

        list = new DoublyLinkedList<>(new Integer[]{10,20,30,40,50,60});
        rotateLeftBy(list, 4);
        System.out.println("After rotate left by 4: "+ list);
        rotateLeftBy(list, 6);
        System.out.println("After rotate left by 6: "+ list);
        rotateLeftBy(list, 8);
        System.out.println("After rotate left by 8: "+ list);

        list = new DoublyLinkedList<>(new Integer[]{10,20,30,40,50,60});
        rotateRightBy(list, 4);
        System.out.println("After rotate right by 4: "+ list);
        rotateRightBy(list, 6);
        System.out.println("After rotate right by 6: "+ list);
        rotateRightBy(list, 8);
        System.out.println("After rotate right by 8: "+ list);

        list = new DoublyLinkedList<>(new Integer[]{1,2,3,7,6,5});
        sortBiotonicList(list);
        System.out.println("After biotonic list sort: "+ list);
        list = new DoublyLinkedList<>(new Integer[]{1,2,3,7});
        sortBiotonicList(list);
        System.out.println("After biotonic list sort: "+ list);
        list = new DoublyLinkedList<>(new Integer[]{5,4,3,2,1});
        sortBiotonicList(list);
        System.out.println("After biotonic list sort: "+ list);
    }
}
