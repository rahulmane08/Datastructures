package list;

import java.util.HashSet;
import java.util.Set;

public class DLLUtils {

    public static <T> void swap(DoublyLinkedList<T> list, T data1, T data2) {
        if (list == null || list.start == null)
            return;
        if (data1 == null || data2 == null)
            return;
        if (data1.equals(data2))
            return;

        Node<T> first = null;
        Node<T> second = null;

        for (Node<T> curr = list.start; curr != null; curr = curr.next) {
            if (curr.data.equals(data1) || curr.data.equals(data2)) {
                if (first == null) {
                    first = curr;
                } else {
                    second = curr;
                }
            }
        }

        if (first == null || second == null) {
            return;
        }

        if (list.start == first) {
            list.start = second;
        } else if (list.start == second) {
            list.start = first;
        }

        Node<T> prevFirst = first.prev;
        Node<T> prevSecond = second.prev;
        Node<T> nextToFirst = first.next;
        Node<T> nextToSecond = first.next;

        second.prev = prevFirst;
        if (prevFirst != null) {
            prevFirst.next = second;
        }

        first.next = nextToSecond;
        if (nextToSecond != null) {
            nextToSecond.prev = first;
        }

        if (nextToFirst != second) {
            second.next = nextToFirst;
            nextToFirst.prev = second;
        } else {
            second.next = first;
            first.prev = second;
        }
    }

    public static <T> Node<T> getMiddle(Node<T> start) {
        if (start == null)
            return null;
        Node<T> slow, fast;
        slow = start;
        fast = start;
        while (fast != null) {
            fast = fast.next;
            if (fast == null)
                break;
            fast = fast.next;
            if (fast != null)
                slow = slow.next;
        }
        return slow;
    }

    public static void printPairsMatchingSum(DoublyLinkedList<Integer> list, int sum) {
        if (list == null)
            return;
        SortUtils.mergeSort(list);
        Node<Integer> start = list.start;
        Node<Integer> end = list.start;
        for (; end.next != null; end = end.next) ;
        while (start != end && end.next != start) {
            if (start.data + end.data == sum) {
                System.out.printf("Found a pair(%d,%d)%n", start.data, end.data);
                start = start.next;
                end = end.prev;
            } else if (start.data + end.data < sum) {
                start = start.next;
            } else {
                end = end.prev;
            }
        }
    }

    public static class ArrangementUtils {
        public static <T> void reverse(DoublyLinkedList<T> doublyLinkedList) {
            if (doublyLinkedList == null || doublyLinkedList.start == null)
                return;
            reverse(doublyLinkedList.start, doublyLinkedList);
        }

        private static <T> void reverse(Node<T> curr, DoublyLinkedList<T> doublyLinkedList) {
            if (curr.next == null) {
                doublyLinkedList.start = curr;
                curr.prev = null;
                return;
            }
            Node<T> next = curr.next;
            reverse(next, doublyLinkedList);
            curr.next = curr.prev;
            curr.prev = next;
            next.next = curr;
        }

        public static <T> void reverseIteratively(DoublyLinkedList<T> doublyLinkedList) {
            Node<T> curr = doublyLinkedList.start;
            while (curr != null) {
                Node<T> prev = curr.prev;
                Node<T> next = curr.next;
                curr.next = prev;
                curr.prev = next;
                if (prev != null)
                    prev.prev = curr;
                prev = curr;
                curr = next;
                if (curr == null) {
                    doublyLinkedList.start = prev;
                    prev.prev = null;
                }
            }
        }

        /**
         * if the given linked list is 10->20->30->40->50->60 and k is 4, the list
         * should be modified to 50->60->10->20->30->40
         *
         * @param list
         * @param n
         */
        static public <T> void rotateLeftBy(DoublyLinkedList<T> list, int n) {
            if (list == null || list.start == null || n < 0)
                return;
            Node<T> curr = list.start;
            int i = 1;
            for (; i <= n; i++) {
                curr = curr.next;
                if (curr == null)
                    curr = list.start;
            }
            if (curr == list.start)
                return; // n == 0 or n == size of list, nothing todo.
            curr.prev.next = null;
            curr.prev = null;
            Node<T> end = curr;
            for (; end.next != null; end = end.next) ;
            end.next = list.start;
            list.start.prev = end;
            list.start = curr;
        }

        /**
         * if the given linked list is 10->20->30->40->50->60 and k is 4, the list
         * should be modified to 30->40->50->60->10->20->NULL
         *
         * @param list
         * @param n
         */
        static public <T> void rotateRightBy(DoublyLinkedList<T> list, int n) {
            if (list == null || list.start == null)
                return;
            int i = 1;
            Node<T> curr = list.start;
            for (; i <= n; i++) {
                curr = curr.next;
                if (curr == null)
                    curr = list.start;
            }
            if (curr == list.start)
                return;
            Node<T> end = list.start;
            for (; curr.next != null; curr = curr.next, end = end.next) ;
            Node<T> start = end.next;
            start.prev = null;
            end.next = null;
            curr.next = list.start;
            list.start.prev = curr;
            list.start = start;
        }
    }

    public static class SortUtils {
        // ############## SORTING ALGOS
        // ############################################################################################################


        public static <T> Node<T> partition(Node<T> start) {
            if (start == null)
                return null;
            Node<T> middle = getMiddle(start);
            Node<T> temp = middle.next;
            middle.next = null;
            if (temp != null) {
                temp.prev = null;
                return temp;
            }
            return null;
        }

        public static <T extends Comparable<T>> void mergeSort(DoublyLinkedList<T> list) {
            if (list == null)
                return;
            list.start = mergeSort(list.start);
        }

        private static <T extends Comparable<T>> Node<T> mergeSort(Node<T> start) {
            if (start == null)
                return null;
            Node<T> middle = partition(start);
            if (middle != null) {
                start = mergeSort(start);
                middle = mergeSort(middle);
            }
            return MergeUtils.merge(start, middle);
        }

        public static <T extends Comparable<T>> void insertionSort(DoublyLinkedList<T> list) {
            if (list == null || list.start == null)
                return;
            Node<T> curr = list.start.next;
            while (curr != null) {
                Node<T> next = curr.next;
                Node<T> prev = curr.prev;
                if (curr.data.compareTo(list.start.data) < 0) {
                    prev.next = next;
                    if (next != null)
                        next.prev = prev;
                    curr.next = list.start;
                    list.start.prev = curr;
                    curr.prev = null;
                    list.start = curr;
                } else {
                    Node<T> x = curr.prev;
                    for (; x.data.compareTo(curr.data) > 0; x = x.prev) ;
                    if (x.next != curr) {
                        curr.prev = x;
                        curr.next = x.next;
                        x.next.prev = curr;
                        x.next = curr;
                        prev.next = next;
                        if (next != null)
                            next.prev = prev;
                    }
                }
                curr = next;
            }
        }

        public static <T extends Comparable<T>> void sortBiotonicList(DoublyLinkedList<T> list) {
            if (list == null)
                return;
            Node<T> curr = list.start;
            while (curr != null && curr.next != null && curr.data.compareTo(curr.next.data) < 0) {
                curr = curr.next;
            }
            if (curr == list.start) {
                mergeSort(list);
                return;
            }
            if (curr.next == null)
                return; // entire list is sorted.
            Node<T> next = curr.next;
            next.prev = null;
            curr.next = null;
            next = mergeSort(next);
            list.start = MergeUtils.merge(list.start, next);
        }
    }

    public static class MergeUtils {
        public static <T extends Comparable<T>> DoublyLinkedList<T> merge(DoublyLinkedList<T> list1, DoublyLinkedList<T> list2) {
            if (list1 == null && list2 == null)
                return null;
            return new DoublyLinkedList<>(merge(list1.start, list2.start));
        }

        public static <T extends Comparable<T>> Node<T> merge(Node<T> start1, Node<T> start2) {
            if (start1 == null && start2 == null)
                return null;
            if (start1 == null)
                return start2;
            if (start2 == null)
                return start1;
            Node<T> start = null, next = null;
            if (start1.data.compareTo(start2.data) < 0) {
                start = start1;
                next = merge(start1.next, start2);
            } else {
                start = start2;
                next = merge(start1, start2.next);
            }
            if (next != null)
                next.prev = start;
            start.next = next;
            return start;
        }
    }

    public static class DeleteUtils {
        static public <T> void deleteDupesFromSortedList(DoublyLinkedList<T> list) {
            if (list == null)
                return;
            for (Node<T> curr = list.start; curr != null; curr = curr.next) {
                Node<T> end = curr.next;
                while (end != null && curr.data.equals(end.data)) {
                    end = end.next;
                }
                if (curr.next != end) {
                    curr.next.prev = null;
                    curr.next = end;
                    if (end != null) {
                        end.prev.next = null;
                        end.prev = curr;
                    }
                }
            }
        }

        static public <T> void deleteDupesFromUnsortedList(DoublyLinkedList<T> list) {
            if (list == null)
                return;
            Set<T> visited = new HashSet<>();
            Node<T> curr = list.start;
            while (curr != null) {
                Node<T> next = curr.next;
                if (visited.contains(curr.data)) {
                    curr.prev.next = curr.next;
                    if (curr.next != null)
                        curr.next.prev = curr.prev;
                    curr.prev = null;
                    curr.next = null;
                } else {
                    visited.add(curr.data);
                }
                curr = next;
            }
        }

        static public <T> void deleteAllOccurences(DoublyLinkedList<T> list, T k) {
            if (list == null || k == null)
                return;
            for (Node<T> curr = list.start; curr != null; ) {
                Node<T> next = curr.next;
                if (curr.data.equals(k)) {
                    if (curr.prev != null) {
                        curr.prev.next = curr.next;
                    }

                    if (next != null) {
                        next.prev = curr.prev;
                    }
                    curr.prev = null;
                    curr.next = null;
                    if (list.start == curr)
                        list.start = next;
                }
                curr = next;
            }
        }
    }
}
