package list;

import static list.ListUtils.ArrangementUtils.moveAllOccurencesOfKToEnd;
import static list.ListUtils.ArrangementUtils.rearrageOddEvenTogether;
import static list.ListUtils.ArrangementUtils.rearrangeInPlace;
import static list.ListUtils.ArrangementUtils.reversePairs;
import static list.ListUtils.ArrangementUtils.reversePairsRecursively;
import static list.ListUtils.ArrangementUtils.rotateInBlocks;
import static list.ListUtils.ArrangementUtils.rotateLeftBy;
import static list.ListUtils.ArrangementUtils.rotateRightBy;
import static list.ListUtils.InsertDeleteUtils.deleteAlternate;
import static list.ListUtils.InsertDeleteUtils.deleteAlternateRecursive;
import static list.ListUtils.InsertDeleteUtils.deleteDupesFromSortedList;
import static list.ListUtils.InsertDeleteUtils.deleteIfRightIsHigher;
import static list.ListUtils.InsertDeleteUtils.deleteIfRightIsHigherRecursive;
import static list.ListUtils.InsertDeleteUtils.deleteLastOccurence;
import static list.ListUtils.InsertDeleteUtils.deleteMiddle;
import static list.ListUtils.InsertDeleteUtils.deleteNAfterMNodes;
import static list.ListUtils.PalindromeUtils.checkIfListIsPalindrome;
import static list.ListUtils.PalindromeUtils.lengthOfLargestPalindrome;
import static list.ListUtils.SwapUtils.swap;
import static list.ListUtils.areIdentical;
import static list.ListUtils.containsSublist;
import static list.ListUtils.flattenMultilevelList;
import static list.ListUtils.flattenMultilevelListDepthWise;
import static list.ListUtils.getMiddle;
import static list.ListUtils.partitionlist;
import static list.ListUtils.pointRandomToNextHigherNode;
import static list.ListUtils.removeMiddlePoints;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import list.ListUtils.ArrangementUtils.ReverseInGroupsOfKUtils;
import list.ListUtils.Point;
import org.junit.Test;

public class TestListUtils {

    @Test
    public void testLinkedList() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(5);

        assertEquals(0, list.indexOf(1));
        assertEquals(5, list.size());
        assertEquals(1, list.start.data.intValue());
        assertEquals(1, list.nodeAt(0).data.intValue());
        assertEquals(5, list.nodeAt(4).data.intValue());
        assertNull(list.nodeAt(99));

        int size = list.size();
        for (int i = 1; i <= 4; i++) {
            assertTrue(list.remove(i));
            assertFalse(list.contains(i));
            assertEquals(--size, list.size());
            assertEquals(i + 1, list.start.data.intValue());
        }
        assertEquals(0, list.indexOf(5));
        for (int i = 1; i < 10; i++) {
            list.insert(i);
        }
        assertEquals(10, list.size());
        assertFalse(list.remove(99));
    }

    @Test
    public void testDoublyLinkedList() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(5);

        assertEquals(0, list.indexOf(1));
        assertEquals(5, list.size());
        assertEquals(1, list.start.data.intValue());
        int size = list.size();
        for (int i = 1; i <= 4; i++) {
            assertTrue(list.remove(i));
            assertFalse(list.contains(i));
            assertEquals(--size, list.size());
            assertEquals(i + 1, list.start.data.intValue());
        }
        assertEquals(0, list.indexOf(5));
        for (int i = 1; i < 10; i++) {
            list.insert(i);
        }
        assertEquals(10, list.size());
        assertFalse(list.remove(99));
    }

    @Test
    public void test_getMiddle() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(1);

        Node<Integer> middle = getMiddle(list);
        assertNotNull(middle);
        assertEquals(1, middle.data.intValue());

        list.insert(2);
        middle = getMiddle(list);
        assertNotNull(middle);
        assertEquals(1, middle.data.intValue());

        list.insert(3);
        list.insert(4);
        middle = getMiddle(list);
        assertNotNull(middle);
        assertEquals(2, middle.data.intValue());

        list.insert(5);
        middle = getMiddle(list);
        assertNotNull(middle);
        assertEquals(3, middle.data.intValue());

    }

    @Test
    public void test_deleteMiddle() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(1);
        deleteMiddle(list);
        assertNull(list.start);
        assertEquals(0, list.size());

        list.insert(1);
        list.insert(2);
        deleteMiddle(list);
        assertEquals(1, list.size());
        assertEquals(2, list.start.data.intValue());

        list.insert(3);
        list.insert(4);
        list.insert(5);
        list.insert(6);
        deleteMiddle(list);
        assertEquals(4, list.size());
        assertEquals(-1, list.indexOf(4));

        deleteMiddle(list);
        assertEquals(3, list.size());
        assertEquals(-1, list.indexOf(3));
    }

    @Test
    public void test_deleteMiddleRecursively() {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.insert(i);
        }
        new ListUtils.InsertDeleteUtils.DeleteMiddleRecursiveUtil<>(list).deleteMiddle();
        assertEquals(4, list.size());
        assertEquals(-1, list.indexOf(2));

        new ListUtils.InsertDeleteUtils.DeleteMiddleRecursiveUtil<>(list).deleteMiddle();
        assertEquals(3, list.size());
        assertEquals(-1, list.indexOf(3));

        new ListUtils.InsertDeleteUtils.DeleteMiddleRecursiveUtil<>(list).deleteMiddle();
        assertEquals(2, list.size());
        assertEquals(-1, list.indexOf(1));

        new ListUtils.InsertDeleteUtils.DeleteMiddleRecursiveUtil<>(list).deleteMiddle();
        assertEquals(1, list.size());
        assertEquals(-1, list.indexOf(4));

        new ListUtils.InsertDeleteUtils.DeleteMiddleRecursiveUtil<>(list).deleteMiddle();
        assertEquals(0, list.size());
        assertNull(list.start);
    }

    @Test
    public void test_listIsPalindrome() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(2);
        list.insert(1);
        assertTrue(checkIfListIsPalindrome(list));
        list.remove(3);
        assertTrue(checkIfListIsPalindrome(list));
        list.remove(2);
        assertTrue(checkIfListIsPalindrome(list));
        list.remove(2);
        assertTrue(checkIfListIsPalindrome(list));
        list.remove(1);
        assertTrue(checkIfListIsPalindrome(list));
    }

    @Test
    public void test_deleteDupesInSortedList() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(1);
        list.insert(1);
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(3);
        list.insert(4);
        list.insert(5);

        deleteDupesFromSortedList(list);
        assertEquals(5, list.size());
        deleteDupesFromSortedList(list);
        assertEquals(5, list.size());
    }

    @Test
    public void test_Swap() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(0);
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(5);
        list.insert(6);

        swap(list, 0, 100);
        assertEquals(0, list.start.data.intValue());
        assertEquals(0, list.indexOf(0));

        swap(list, 100, 0);
        assertEquals(0, list.start.data.intValue());
        assertEquals(0, list.indexOf(0));

        swap(list, 0, 0);
        assertEquals(0, list.start.data.intValue());
        assertEquals(0, list.indexOf(0));

        // first and last
        swap(list, 0, 6);
        assertEquals(6, list.start.data.intValue());
        assertEquals(0, list.indexOf(6));
        assertEquals(6, list.indexOf(0));

        // middle
        swap(list, 2, 5);
        assertEquals(6, list.start.data.intValue());
        assertEquals(2, list.indexOf(5));
        assertEquals(5, list.indexOf(2));

        // adjacent
        swap(list, 3, 4);
        assertEquals(6, list.start.data.intValue());
        assertEquals(4, list.indexOf(3));
        assertEquals(3, list.indexOf(4));

        // adjacent
        swap(list, 3, 4);
        assertEquals(6, list.start.data.intValue());
        assertEquals(3, list.indexOf(3));
        assertEquals(4, list.indexOf(4));
    }

    @Test
    public void test_deleteNAfterMNodes1() {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i <= 10; i++) {
            list.insert(i);
        }
        deleteNAfterMNodes(list, 0, 0);
        assertEquals(11, list.size());

        deleteNAfterMNodes(list, 0, 2);
        assertEquals(9, list.size());
        assertEquals(2, list.start.data.intValue());

        deleteNAfterMNodes(list, 2, 2);
        assertEquals(7, list.size());
        assertEquals(2, list.start.data.intValue());
        assertEquals(-1, list.indexOf(4));
        assertEquals(-1, list.indexOf(5));

        deleteNAfterMNodes(list, 10, 2);
        assertEquals(7, list.size());
        assertEquals(2, list.start.data.intValue());

        deleteNAfterMNodes(list, 5, 2);
        assertEquals(5, list.size());
        assertEquals(2, list.start.data.intValue());
        assertEquals(-1, list.indexOf(9));
        assertEquals(-1, list.indexOf(10));

        deleteNAfterMNodes(list, 0, 5);
        assertEquals(0, list.size());
        assertNull(list.start);
    }

    @Test
    public void test_deleteIfRightIsHigher() {
        LinkedList<Integer> list = new LinkedList<>(new Integer[]{12, 15, 10, 11, 5, 6, 2, 3});
        deleteIfRightIsHigher(list);
        LinkedList<Integer> output = new LinkedList<>(new Integer[]{15, 11, 6, 3});
        assertTrue(list.equals(output));

        list = new LinkedList<>(new Integer[]{6, 5, 4, 3, 2, 1, 0});
        deleteIfRightIsHigher(list);
        output = new LinkedList<>(new Integer[]{6});
        assertTrue(list.equals(output));
    }

    @Test
    public void test_deleteIfRightIsHigherRecursive() {
        LinkedList<Integer> list = new LinkedList<>(new Integer[]{12, 15, 10, 11, 5, 6, 2, 3});
        deleteIfRightIsHigherRecursive(list);
        LinkedList<Integer> output = new LinkedList<>(new Integer[]{15, 11, 6, 3});
        assertTrue(list.equals(output));

        list = new LinkedList<>(new Integer[]{6, 5, 4, 3, 2, 1, 0});
        deleteIfRightIsHigherRecursive(list);
        output = new LinkedList<>(new Integer[]{6});
        assertTrue(list.equals(output));
    }

    // arrangement tests
    @Test
    public void test_reversePairs() {
        LinkedList<Integer> list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        reversePairsRecursively(list);
        LinkedList<Integer> output = new LinkedList<>(new Integer[]{2, 1, 4, 3, 5});
        assertEquals(output, list);

        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        reversePairs(list);
        assertEquals(output, list);

        list = new LinkedList<>(new Integer[]{1, 2, 3, 4});
        reversePairsRecursively(list);
        output = new LinkedList<>(new Integer[]{2, 1, 4, 3});
        assertEquals(output, list);

        list = new LinkedList<>(new Integer[]{1, 2, 3, 4});
        reversePairs(list);
        assertEquals(output, list);
    }

    @Test
    public void test_rotation() {
        LinkedList<Integer> list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6});
        rotateLeftBy(list, 4);
        LinkedList<Integer> output = new LinkedList<>(new Integer[]{5, 6, 1, 2, 3, 4});
        assertEquals(output, list);

        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6});
        rotateLeftBy(list, 1);
        output = new LinkedList<>(new Integer[]{2, 3, 4, 5, 6, 1});
        assertEquals(output, list);

        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6});
        rotateRightBy(list, 4);
        output = new LinkedList<>(new Integer[]{3, 4, 5, 6, 1, 2});
        assertEquals(output, list);

        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6});
        rotateRightBy(list, 1);
        output = new LinkedList<>(new Integer[]{6, 1, 2, 3, 4, 5});
        assertEquals(output, list);
    }

    @Test
    public void test_rotateInBlocks() {
        LinkedList<Integer> list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        LinkedList<Integer> output = new LinkedList<>(new Integer[]{3, 1, 2, 6, 4, 5, 9, 7, 8});
        rotateInBlocks(list, 3, 1);
        assertEquals(output, list);

        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
        output = new LinkedList<>(new Integer[]{2, 3, 4, 1, 6, 7, 8, 5, 10, 11, 12, 9, 14, 15, 13});
        rotateInBlocks(list, 4, -1);
        assertEquals(output, list);
    }

    @Test
    public void test_rearrageOddEvenTogether() {
        LinkedList<Integer> list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        LinkedList<Integer> output = new LinkedList<>(new Integer[]{1, 3, 5, 2, 4});
        rearrageOddEvenTogether(list);
        assertEquals(output, list);

        list = new LinkedList<>(new Integer[]{1, 2, 3, 4});
        output = new LinkedList<>(new Integer[]{1, 3, 2, 4});
        rearrageOddEvenTogether(list);
        assertEquals(output, list);
    }

    @Test
    public void test_rearrangeInPlace() {
        LinkedList<Integer> list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        LinkedList<Integer> output = new LinkedList<>(new Integer[]{1, 5, 2, 4, 3});
        rearrangeInPlace(list);
        assertEquals(output, list);
    }

    @Test
    public void test_ReverseInGroupsOfKUtils() {
        LinkedList<Integer> list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        LinkedList<Integer> output = new LinkedList<>(new Integer[]{3, 2, 1, 6, 5, 4, 8, 7});
        ReverseInGroupsOfKUtils.reverse(list, 3);
        assertEquals(output, list);

        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        ReverseInGroupsOfKUtils.reverseIteratively(list, 3);
        assertEquals(output, list);
    }

    @Test
    public void test_moveAllOccurencesOfKToEnd() {
        LinkedList<Integer> list = new LinkedList<>(new Integer[]{1, 1, 2, 2, 4, 2, 5});
        LinkedList<Integer> output = new LinkedList<>(new Integer[]{1, 1, 4, 5, 2, 2, 2});
        moveAllOccurencesOfKToEnd(list, 2);
        assertEquals(output, list);

        list = new LinkedList<>(new Integer[]{1, 1, 2, 2, 4, 2, 5});
        output = new LinkedList<>(new Integer[]{2, 2, 4, 2, 5, 1, 1});
        moveAllOccurencesOfKToEnd(list, 1);
        assertEquals(output, list);

        list = new LinkedList<>(new Integer[]{1, 1, 1, 1});
        output = new LinkedList<>(new Integer[]{1, 1, 1, 1});
        moveAllOccurencesOfKToEnd(list, 1);
        assertEquals(output, list);
    }

    @Test
    public void test_deleteAlternateRecursive() {
        LinkedList<Integer> list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6});
        LinkedList<Integer> output = new LinkedList<>(new Integer[]{1, 3, 5});
        deleteAlternate(list, true);
        assertEquals(output, list);

        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6});
        output = new LinkedList<>(new Integer[]{1, 3, 5});
        deleteAlternateRecursive(list);
        assertEquals(output, list);
    }

    @Test
    public void test_deleteLastOccurence() {
        LinkedList<Integer> list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 2, 6});
        deleteLastOccurence(list, 2);
        assertEquals(new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6}), list);

        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 2, 6});
        deleteLastOccurence(list, 6);
        assertEquals(new LinkedList<>(new Integer[]{1, 2, 3, 4, 5}), list);

        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 2, 6});
        deleteLastOccurence(list, 1);
        assertEquals(new LinkedList<>(new Integer[]{2, 3, 4, 5}), list);

        list = new LinkedList<>(new Integer[]{2, 2, 2});
        deleteLastOccurence(list, 2);
        assertEquals(2, list.size());
        deleteLastOccurence(list, 2);
        assertEquals(1, list.size());
        deleteLastOccurence(list, 2);
        assertEquals(0, list.size());
    }

    // merging
    @Test
    public void test_mergeAlternatively() {
        LinkedList<Integer> list1 = new LinkedList<>(new Integer[]{1, 2, 3});
        LinkedList<Integer> list2 = new LinkedList<>(new Integer[]{4, 5, 6});
        LinkedList<Integer> mergedList = ListUtils.MergeUtils.mergeAlternatively(list1, list2);
        assertEquals(new LinkedList<>(new Integer[]{1, 4, 2, 5, 3, 6}), mergedList);

        list1 = new LinkedList<>(new Integer[]{1, 2, 3});
        list2 = new LinkedList<>(new Integer[]{4, 5, 6});
        mergedList = ListUtils.MergeUtils.mergeAlternativelyIterative(list1, list2);
        assertEquals(new LinkedList<>(new Integer[]{1, 4, 2, 5, 3, 6}), mergedList);
    }

    @Test
    public void test_mergeSorted() {
        LinkedList<Integer> list1 = new LinkedList<>(new Integer[]{1, 2, 3, 10});
        LinkedList<Integer> list2 = new LinkedList<>(new Integer[]{4, 5, 6});
        LinkedList<Integer> mergedList = ListUtils.MergeUtils.merge(list1, list2);
        assertEquals(new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 10}), mergedList);

        list1 = new LinkedList<>(new Integer[]{1, 2, 3, 10});
        list2 = new LinkedList<>(new Integer[]{4, 5, 6});
        mergedList = ListUtils.MergeUtils.mergeIteratively(list1, list2);
        assertEquals(new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 10}), mergedList);
    }

    @Test
    public void test_mergeInPlace() {
        LinkedList<Integer> list1 = new LinkedList<>(new Integer[]{2, 4, 7, 8, 10});
        LinkedList<Integer> list2 = new LinkedList<>(new Integer[]{1, 3, 12});
        ListUtils.MergeUtils.mergeInPlace(list1, list2);
        assertEquals(new LinkedList<>(new Integer[]{1, 2, 3, 4, 7}), list1);
        assertEquals(new LinkedList<>(new Integer[]{8, 10, 12}), list2);
    }

    @Test
    public void test_mergeKLists() {
        LinkedList<Integer> list1 = new LinkedList<>(new Integer[]{1, 10, 20});
        LinkedList<Integer> list2 = new LinkedList<>(new Integer[]{4, 6, 9});
        LinkedList<Integer> list3 = new LinkedList<>(new Integer[]{3, 5, 8});
        LinkedList<Integer> list4 = new LinkedList<>(new Integer[]{7, 15, 16});
        LinkedList<Integer> mergedList = ListUtils.MergeUtils.mergeKLists(new LinkedList[]{list1, list2, list3, list4});
        LinkedList<Integer> output = new LinkedList<>(new Integer[]{1, 3, 4, 5, 6, 7, 8, 9, 10, 15, 16, 20});
        assertEquals(output, mergedList);
    }

    @Test
    public void test_containsSublist() {
        LinkedList<Integer> list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 10});
        assertTrue(containsSublist(list, new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 10})));
        assertTrue(containsSublist(list, new LinkedList<>(new Integer[]{1, 2, 3, 4})));
        assertTrue(containsSublist(list, new LinkedList<>(new Integer[]{4, 5, 6, 10})));
        assertTrue(containsSublist(list, new LinkedList<>(new Integer[]{3, 4, 5, 6})));
        assertFalse(containsSublist(list, new LinkedList<>(new Integer[]{1, 1, 2, 3, 4, 5, 6, 10})));
        assertFalse(containsSublist(list, new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 10, 11})));
    }

    // Test sorting
    @Test
    public void test_mergeSort() {
        LinkedList<Integer> list = new LinkedList<>(new Integer[]{3, 1, 2, 6, 4, 5, 9, 7, 8});
        list = ListUtils.SortUtils.mergeSort(list);
        System.out.println(list);
    }

    @Test
    public void test_selectionSort() {
        LinkedList<Integer> list = new LinkedList<>(new Integer[]{3, 1, 2, 6, 4, 5, 9, 7, 8});
        ListUtils.SortUtils.selectionSort(list);
        System.out.println(list);
    }

    @Test
    public void test_sortAbsolutelySortedList() {
        LinkedList<Integer> list = new LinkedList<>(new Integer[]{1, -2, -3, 4, 5, -6, 10});
        ListUtils.SortUtils.sortAbsolutelySortedList(list);
        assertEquals(new LinkedList<>(new Integer[]{-6, -3, -2, 1, 4, 5, 10}), list);
        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 10});
        ListUtils.SortUtils.sortAbsolutelySortedList(list);
        assertEquals(new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 10}), list);
    }

    @Test
    public void test_partitionAlternatively() {
        LinkedList<Integer> list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6});
        LinkedList<Integer> second = ListUtils.SortUtils.partitionAlternatively(list);
        assertEquals(new LinkedList<>(new Integer[]{1, 3, 5}), list);
        assertEquals(new LinkedList<>(new Integer[]{2, 4, 6}), second);

        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6});
        second = ListUtils.SortUtils.partitionAlternativelyUsingRecursion(list);
        assertEquals(new LinkedList<>(new Integer[]{1, 3, 5}), list);
        assertEquals(new LinkedList<>(new Integer[]{2, 4, 6}), second);

        list = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        second = ListUtils.SortUtils.partitionAlternativelyUsingRecursion(list);
        assertEquals(new LinkedList<>(new Integer[]{1, 3, 5}), list);
        assertEquals(new LinkedList<>(new Integer[]{2, 4}), second);
    }

    @Test
    public void test_sortAlternativelySortedList() {
        LinkedList<Integer> list = new LinkedList<>(new Integer[]{1, 6, 2, 5, 3, 4});
        ListUtils.SortUtils.sortAlternativelySortedList(list);
        assertEquals(new LinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6}), list);
    }

    @Test
    public void test_areIdentical() {
        assertTrue(areIdentical(
                new LinkedList<>(new Integer[]{1, 6, 2, 5, 3, 4}),
                new LinkedList<>(new Integer[]{1, 6, 2, 5, 3, 4})));
        assertFalse(areIdentical(
                new LinkedList<>(new Integer[]{1, 6, 2, 5, 4}),
                new LinkedList<>(new Integer[]{1, 6, 2, 5, 3, 4})));
    }

    @Test
    public void test_lengthOfLargestPalindrome() {
        LinkedList<Integer> list = new LinkedList<>(new Integer[]{2, 3, 7, 3, 2, 12, 24});
        assertEquals(5, lengthOfLargestPalindrome(list));
        list = new LinkedList<>(new Integer[]{12, 4, 4, 3, 14});
        assertEquals(2, lengthOfLargestPalindrome(list));
        list = new LinkedList<>(new Integer[]{12, 4, 4, 4, 3, 14});
        assertEquals(3, lengthOfLargestPalindrome(list));
        list = new LinkedList<>(new Integer[]{12, 4, 4, 4, 4, 3, 14});
        assertEquals(4, lengthOfLargestPalindrome(list));
    }

    @Test
    public void test_partitionlist() {
        LinkedList<Integer> list = new LinkedList<>(new Integer[]{1, 4, 3, 2, 5, 2, 3});
        partitionlist(list, 3);
        assertEquals(new LinkedList<>(new Integer[]{1, 2, 2, 3, 3, 4, 5}), list);

        list = new LinkedList<>(new Integer[]{1, 4, 2, 10});
        partitionlist(list, 3);
        assertEquals(new LinkedList<>(new Integer[]{1, 2, 4, 10}), list);

        list = new LinkedList<>(new Integer[]{10, 4, 20, 10, 3});
        partitionlist(list, 3);
        assertEquals(new LinkedList<>(new Integer[]{3, 10, 4, 20, 10}), list);
    }

    @Test
    public void test_decimalToBinary() {
        assertEquals(new LinkedList<>(new Integer[]{1, 1, 0}), ListUtils.ArithematicUtils.decimalToBinary(6));
        assertEquals(new LinkedList<>(new Integer[]{1, 0, 0, 1}), ListUtils.ArithematicUtils.decimalToBinary(9));
    }

    @Test
    public void test_removeMiddlePoints1() {
        LinkedList<Point> list = new LinkedList<>(new Point[]{
                new Point(0, 10),
                new Point(1, 10),
                new Point(5, 10),
                new Point(7, 10),
                new Point(7, 5),
                new Point(20, 5),
                new Point(40, 5)
        });
        removeMiddlePoints(list);
        LinkedList<Point> output = new LinkedList<>(new Point[]{
                new Point(0, 10),
                new Point(7, 10),
                new Point(7, 5),
                new Point(40, 5)
        });
        assertEquals(output, list);


        list = new LinkedList<>(new Point[]{
                new Point(0, 10),
                new Point(7, 10),
                new Point(7, 5),
                new Point(40, 5)
        });
        removeMiddlePoints(list);
        output = new LinkedList<>(new Point[]{
                new Point(0, 10),
                new Point(7, 10),
                new Point(7, 5),
                new Point(40, 5)
        });
        assertEquals(output, list);
    }

    @Test
    public void test_flattenListDepthwise() {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.prev = new Node(7);
        head.next.prev.prev = new Node(9);
        head.next.prev.prev.prev = new Node(14);
        head.next.prev.prev.prev.prev = new Node(15);
        head.next.prev.prev.prev.prev.next = new Node(23);
        head.next.prev.prev.prev.prev.next.prev = new Node(24);
        head.next.prev.next = new Node(8);
        head.next.prev.next.prev = new Node(16);
        head.next.prev.next.prev.prev = new Node(17);
        head.next.prev.next.prev.prev.next = new Node(18);
        head.next.prev.next.prev.prev.next.next = new Node(19);
        head.next.prev.next.prev.prev.next.next.next
                = new Node(20);
        head.next.prev.next.prev.prev.next.next.next.prev
                = new Node(21);
        head.next.prev.next.next = new Node(10);
        head.next.prev.next.next.prev = new Node(11);
        head.next.prev.next.next.next = new Node(12);
        LinkedList<Integer> list = new LinkedList<>(head);
        flattenMultilevelListDepthWise(list);
        LinkedList<Integer> output =
                new LinkedList<>(new Integer[]{1, 2, 7, 9, 14, 15, 23, 24, 8, 16, 17, 18, 19, 20, 21, 10, 11, 12, 3, 4});
        assertEquals(output, list);
    }

    /**
     * Input:
     * 1 - 2 - 3 - 4
     * |
     * 7 -  8 - 10 - 12
     * |    |    |
     * 9    16   11
     * |    |
     * 14   17 - 18 - 19 - 20
     * |                    |
     * 15 - 23             21
     * |
     * 24
     */
    @Test
    public void test_flattenList() {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.prev = new Node(7);
        head.next.prev.prev = new Node(9);
        head.next.prev.prev.prev = new Node(14);
        head.next.prev.prev.prev.prev = new Node(15);
        head.next.prev.prev.prev.prev.next = new Node(23);
        head.next.prev.prev.prev.prev.next.prev = new Node(24);
        head.next.prev.next = new Node(8);
        head.next.prev.next.prev = new Node(16);
        head.next.prev.next.prev.prev = new Node(17);
        head.next.prev.next.prev.prev.next = new Node(18);
        head.next.prev.next.prev.prev.next.next = new Node(19);
        head.next.prev.next.prev.prev.next.next.next
                = new Node(20);
        head.next.prev.next.prev.prev.next.next.next.prev
                = new Node(21);
        head.next.prev.next.next = new Node(10);
        head.next.prev.next.next.prev = new Node(11);
        head.next.prev.next.next.next = new Node(12);
        LinkedList<Integer> list = new LinkedList<>(head);
        flattenMultilevelList(list);
        System.out.println(list);
    }

    @Test
    public void test_pointRandomToNextHigherNode() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(5);
        list.insert(10);
        list.insert(2);
        list.insert(3);
        pointRandomToNextHigherNode(list);
        for (Node<Integer> curr = list.start; curr != null; curr = curr.next) {
            System.out.print(curr.data + " ");
        }
        System.out.println();
        for (Node<Integer> curr = list.start; curr != null; curr = curr.random) {
            System.out.print(curr.data + " ");
        }
        System.out.println();
    }
}
