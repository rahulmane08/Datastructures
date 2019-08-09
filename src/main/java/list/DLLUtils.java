package list;

public class DLLUtils {

    public static <T> void swap(DoublyLinkedList<T> list, T data1, T data2) {
        if (list == null || list.start == null)
            return;
        if (data1 == null || data2 == null)
            return;
        if (data1.equals(data2))
            return;
        Node<T> first = null, second = null;
        if (list.start.data.equals(data1) || list.start.data.equals(data2)) {
            first = list.start;
            T dataToSearch = first.data.equals(data1) ? data2 : data1;
            for (second = list.start; !second.data.equals(dataToSearch); second = second.next) ;
            if (second == null)
                return; // one of the nodes missing
            list.start = second;
        } else {
            for (Node<T> curr = list.start; curr != null; curr = curr.next) {
                if (curr.data.equals(data1))
                    first = curr;
                else if (curr.data.equals(data2))
                    second = curr;
            }
        }
        if (first == null || second == null)
            return; // one of the nodes missing
        Node<T> prevFirst = first.prev,
                prevSecond = second.prev,
                nextSecond = second.next,
                nextFirst = first.next;
        if (nextFirst == second) {
            second.prev = prevFirst;
            first.next = second.next;
            second.next = first;
            first.prev = second;
        } else {
            second.prev = prevFirst;
            first.next = nextSecond;
            second.next = nextFirst;
            nextFirst.prev = second;
            prevSecond.next = first;
            first.prev = prevSecond;
        }
        if (prevFirst != null)
            prevFirst.next = second;
        if (nextSecond != null)
            nextSecond.prev = first;
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
            for (Node<T> curr = list.start; curr.next != null; curr = curr.next) {
                Node<T> end = curr.next;
                while (curr.data.equals(end.data)) {
                    end = end.next;
                }
                if (curr.next != end) {
                    curr.next.prev = null;
                    curr.next = end;
                    end.prev.next = null;
                    end.prev = curr;
                }
            }
        }
    }
}
