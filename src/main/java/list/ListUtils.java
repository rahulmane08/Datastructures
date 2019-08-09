package list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import array.ArrayUtils;

public class ListUtils {
    private ListUtils() {
    }

    public static LinkedList<Integer> toList(Integer[] arr) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i : arr)
            list.insert(i);
        return list;
    }

    private static Integer[] toIntArray(int number) {
        List<Integer> i = new ArrayList<>();
        for (int temp = number % 10; number > 0; ) {
            i.add(temp);
            number /= 10;
            temp = number % 10;
        }
        return i.toArray(new Integer[i.size()]);
    }

    public static <T extends Number> Double toDouble(LinkedList<T> list, int size) {
        if (list == null || list.start == null)
            return 0d;
        return convertToInteger(list.start, size);
    }

    private static <T extends Number> Double convertToInteger(Node<T> start, int size) {
        if (start == null)
            return 0d;
        return start.data.doubleValue() * Math.pow(10, size - 1) + convertToInteger(start.next, size - 1);
    }

    /**
     * Input : 1->4->3->2->5->2->3,
     * <p>
     * x = 3
     * <p>
     * Output: 1->2->2->3->3->4->5
     * <p>
     * Input : 1->4->2->10
     * <p>
     * x = 3
     * <p>
     * Output: 1->2->4->10
     * <p>
     * Input : 10->4->20->10->3
     * <p>
     * x = 3
     * <p>
     * Output: 3->10->4->20->10
     *
     * @param list
     * @param x
     */

    static public void partitionlist(LinkedList<Integer> list, int x) {
        if (list == null || list.start == null)
            return;
        Node<Integer> smallerStart = null, equalStart = null, greaterStart = null;
        Node<Integer> smallerEnd = null, equalEnd = null, greaterEnd = null;
        Node<Integer> curr = list.start;
        Node<Integer> newStart = null;
        while (curr != null) {
            Node<Integer> temp = null;
            Node<Integer> next = curr.next;
            if (curr.data < x) {
                if (smallerStart == null)
                    smallerStart = smallerEnd = curr;
                if (smallerEnd != curr) {
                    smallerEnd.next = curr;
                    smallerEnd = curr;
                }
            } else if (curr.data > x) {
                if (greaterStart == null)
                    greaterStart = greaterEnd = curr;
                if (greaterEnd != curr) {
                    greaterEnd.next = curr;
                    greaterEnd = curr;
                }
            } else {
                if (equalStart == null)
                    equalStart = equalEnd = curr;
                if (equalEnd != curr) {
                    equalEnd.next = curr;
                    equalEnd = curr;
                }
            }
            curr.next = null;
            curr = next;
        }
        if (smallerStart != null) {
            newStart = smallerStart;
            smallerEnd.next = (equalStart != null ? equalStart : greaterStart);
        }
        if (equalStart != null) {
            if (newStart == null)
                newStart = equalStart;
            equalEnd.next = greaterStart;
        }
        if (greaterStart != null) {
            if (newStart == null)
                newStart = greaterStart;
        }

        list.start = newStart;
    }

    /**
     * Input : list1 = 10->20
     * <p>
     * list2 = 5->10->20
     * <p>
     * Output : LIST FOUND
     * <p>
     * Input : list1 = 1->2->3->4
     * <p>
     * list2 = 1->2->1->2->3->4
     * <p>
     * Output : LIST FOUND
     * <p>
     * Input : list1 = 1->2->3->4
     * <p>
     * list2 = 1->2->2->1->2->3
     * <p>
     * Output : LIST NOT FOUND
     */

    static public boolean containsSublist(LinkedList<Integer> mainList, LinkedList<Integer> subList) {
        if (mainList == null || subList == null || mainList.start == null || subList.start == null)
            return false;
        boolean flag = false;
        Node<Integer> subStart = subList.start;
        Node<Integer> curr = mainList.start;
        while (curr != null) {
            if (flag && subStart == null)
                return true;
            /**
             * * sublist ended and all of it is present in mail list, break the
             * loop and return true *
             */
            if (curr.data == subStart.data) {
                flag = true;
                subStart = subStart.next;
            } else {
                flag = false;
                subStart = subList.start;
                /** * mismatch, hence reset it to start */
            }
            curr = curr.next;
        }
        /**
         * * sublist was matching with main list, but main list ended and
         * some part of sublist is still yet to be checked, hence false
         */
        if (flag && subStart != null)
            flag = false;
        return flag;
    }

    static public void printPairsMatchingSum(DoublyLinkedList<Integer> sortedDLL, int sum) {
        if (sortedDLL == null || sortedDLL.start == null)
            return;
        Node<Integer> start, end;
        start = end = sortedDLL.start;
        while (end.next != null)
            end = end.next;
        while (start != end) {
            int currSum = start.data + end.data;
            if (currSum == sum) {
                System.out.println("Found a pair(" + start.data + "," + end.data + ")");
                start = start.next;
                if (start == end)
                    break;
                end = end.prev;
                if (start == end)
                    break;
            } else if (currSum > sum)
                end = end.prev;
            else
                start = start.next;
        }
    }

    /**
     * Input : 0->0->0->1->1->0->0->1->0
     * <p>
     * Output : 50
     * <p>
     * Input : 1->0->0
     * <p>
     * Output : 4
     *
     * @param list
     * @return
     */

    static public double binaryToDecimal(LinkedList<Integer> list) {
        if (list == null || list.start == null)
            return 0;
        int n = list.size();
        return computeDecimal(list.start, n);
    }

    static private double computeDecimal(Node<Integer> start, int n) {
        if (start == null)
            return 0;
        return (start.data * Math.pow(2, n - 1)) + computeDecimal(start.next, --n);
    }

    static public LinkedList<Integer> decimalToBinary(int decimal) {
        LinkedList<Integer> binary = new LinkedList<Integer>();
        int res = 0;
        do {
            res = decimal % 2;
            binary.insert(res);
            decimal = decimal / 2;
        } while (decimal != 0);
        ArrangementUtils.reverse(binary);
        return binary;
    }


    /**
     * Mergesort a dll
     */
    static public <T> DoublyLinkedList<T> divideMiddle(DoublyLinkedList<T> mainList) {
        if (mainList == null || mainList.start == null)
            return null;
        Node<T> slow, fast;
        slow = mainList.start;
        fast = mainList.start.next;
        while (fast != null) {
            fast = fast.next;
            if (fast == null)
                break;
            fast = fast.next;
            if (fast != null)
                slow = slow.next;
        }
        Node<T> temp = slow.next;
        slow.next = null;
        temp.prev = null;
        return new DoublyLinkedList<>(temp);
    }

    static public DoublyLinkedList<Integer> mergeSort(DoublyLinkedList<Integer> dll) {
        if (dll == null || dll.start == null)
            return null;
        DoublyLinkedList<Integer> second = divideMiddle(dll);
        mergeSort(dll);
        mergeSort(second);
        return merge(dll, second);
    }

    static public DoublyLinkedList<Integer> merge(DoublyLinkedList<Integer> dll1, DoublyLinkedList<Integer> dll2) {
        if (dll1 == null && dll2 == null)
            return null;
        if (dll2 == null)
            return dll1;
        if (dll1 == null)
            return dll2;
        Node<Integer> mergedNode = merge(dll1.start, dll2.start);
        if (mergedNode != null)
            return new DoublyLinkedList<>(mergedNode);
        return null;
    }

    static public Node<Integer> merge(Node<Integer> first, Node<Integer> second) {
        if (first == null && second == null)
            return null;
        if (second == null)
            return first;
        if (first == null)
            return second;
        Node<Integer> curr;
        if (first.data < second.data) {
            curr = first;
            curr.next = merge(first.next, second);
            curr.next.prev = curr;
        } else {
            curr = second;
            curr.next = merge(first, second.next);
            curr.next.prev = curr;
        }
        return curr;
    }

    /**
     * Input: 1->2->3->4
     * <p>
     * Output: 1->3->2->4
     * <p>
     * Input: 10->22->30->43->56->70
     * <p>
     * Output: 10->30->56->22->43->70
     */

    static public void arrangeOddEvenIndexesTogether(LinkedList<Integer> list) {
        if (list == null || list.start == null)
            return;
        Node<Integer> odd, even, evenStart;
        odd = list.start;
        even = odd.next;
        evenStart = even;
        if (even == null)
            return;
        int i = 1;
        while (even != null && even.next != null) {
            if (i % 2 == 1) {
                odd.next = even.next;
                odd = odd.next;
            } else {
                even.next = odd.next;
                even = even.next;
            }
            ++i;
        }
        odd.next = evenStart;
        if (even != null)
            even.next = null;
    }

    /**
     * Input: list1 = g->e->e->k->s->a
     * list2 = g->e->e->k->s->b
     * Output: -1
     * <p>
     * Input: list1 = g->e->e->k->s->a
     * list2 = g->e->e->k->s
     * Output: 1
     * <p>
     * Input: list1 = g->e->e->k->s
     * list2 = g->e->e->k->s
     * Output: 0
     *
     * @param list1
     * @param list2
     * @return
     */
    static public int lexicalCompare(LinkedList<Character> list1, LinkedList<Character> list2) {
        if (list1 == null && list2 == null)
            return 0;
        if (list2 == null)
            return 1;
        if (list1 == null)
            return -1;
        return lexicalDiff(list1.start, list2.start);
    }

    static private int lexicalDiff(Node<Character> start1, Node<Character> start2) {

        if (start1 == null && start2 == null)

            return 0;

        if (start2 == null)

            return 1;

        if (start1 == null)

            return -1;

        int diff = start1.data - start2.data;

        if (diff != 0)

            return diff;

        return lexicalDiff(start1.next, start2.next);

    }

    /**
     * 1-2-3-4-null
     *
     * @param list
     * @param data1
     * @param data2
     * @param <T>
     */

    static public <T> void swap(LinkedList<T> list, Node<T> first, Node<T> second) {
        if (first == null || second == null)
            return;
        swap(list, first.data, second.data);
    }

    static public <T> void swap(LinkedList<T> list, T data1, T data2) {
        if (list == null || list.start == null)
            return;
        if (data1 == null || data2 == null)
            return;
        if (data1.equals(data2))
            return;
        Node<T> prevFirst, prevSecond, first, second;
        prevFirst = prevSecond = first = second = null;
        if (list.start.data.equals(data1) || list.start.data.equals(data2)) {
            T dataToSearch = null;
            if (list.start.data.equals(data1))
                dataToSearch = data2;
            else
                dataToSearch = data1;
            for (prevSecond = list.start;
                 prevSecond.next != null && !prevSecond.next.data.equals(dataToSearch); prevSecond = prevSecond.next)
                ;
            if (prevSecond.next == null)
                return; // one of the two is not present.
            first = list.start; // first is list start
            second = prevSecond.next;
            list.start = second; // new list start becomes second.
        } else {
            // both nodes arent list start
            for (Node<T> curr = list.start; (prevFirst == null || prevSecond == null); curr = curr.next) {
                if (curr.next != null && curr.next.data.equals(data1) || curr.next.data.equals(data2)) {
                    if (prevFirst == null)
                        prevFirst = curr;
                    else
                        prevSecond = curr;
                }
            }
            if (prevFirst != null)
                first = prevFirst.next;
            if (prevSecond != null)
                second = prevSecond.next;
        }
        if (first == null || second == null)
            return; // one of the two is null;

        if (first.next == second) {
            // adjacent node case.
            first.next = second.next;
            second.next = first;
        } else {
            // 1-2-3-4-5, swap(2,4)
            Node<T> firstNext = first.next; // firstNext = 3
            first.next = second.next; // 1-2-5, 3-4
            prevSecond.next = first; //  (1,3) -2-5, 4
            second.next = firstNext; // (1,4-3)- 2-5
        }
        if (prevFirst != null) // this can be null if first if first node of list
            prevFirst.next = second; // 1-4-3-2-5
    }

    private static <T> Node<T> findPrevToActual(Node<T> start, T data) {
        while (start != null && start.next != null && !start.next.data.equals(data)) {
            start = start.next;
        }
        return start.next == null ? null : start; // node not found.
    }

    /**
     * Input: List1: 10->15->4->20 lsit2: 8->4->2->10 Output: Intersection List:
     * 4->10 Union List: 2->8->20->4->15->10
     */

    static public void unionIntersection(LinkedList<Integer> list1, LinkedList<Integer> list2) {
        if ((list1 == null || list1.start == null) && (list2 == null && list2.start == null))
            return;
        LinkedList<Integer> union, intersection;
        if (list1 == null || list1.start == null)
            union = intersection = list2;
        else if (list2 == null && list2.start == null)
            union = intersection = list1;
        else {
            union = new LinkedList<>();
            intersection = new LinkedList<>();
            list1 = SortUtils.mergeSort(list1);
            list2 = SortUtils.mergeSort(list2);
            LinkedList<Integer> mergedList = MergeUtils.merge(list1, list2);
            Node<Integer> curr = mergedList.start, prev = null;
            int repeat = 0;
            while (curr != null && curr.next != null) {
                if (curr.data == curr.next.data) {
                    ++repeat;
                    curr = curr.next;
                } else {
                    if (repeat > 0) {
                        Node<Integer> temp = curr.next;
                        curr.next = null;
                        intersection.insert(curr.data);
                        curr = temp;
                        if (prev == null)
                            mergedList.start = curr;
                        else
                            prev.next = curr;
                        repeat = 0;
                    } else {
                        prev = curr;
                        curr = curr.next;
                    }
                }
            }

        }
        System.out.println("Union = " + union);
        System.out.println("Intersection = " + intersection);
    }

    static public <T> void swapKthFromStartAndEnd(LinkedList<T> list, int k) {
        if (list == null || list.start == null)
            return;
        if (k < 1)
            return;
        int i = 1;
        Node<T> first = null, second = null, curr = list.start;
        while (curr != null) {
            if (i == k) {
                first = curr;
                second = list.start;
            }
            if (i > k) {
                second = second.next;
            }
            i++;
            curr = curr.next;
        }
        swap(list, first, second);
    }

    static public boolean identical(LinkedList<Integer> list1, LinkedList<Integer> list2) {
        if (list1 == null && list2 == null)
            return true;
        return checkIfEqual(list1.start, list2.start);
    }

    static private boolean checkIfEqual(Node<Integer> start1, Node<Integer> start2) {
        if (start1 == null && start2 == null)
            return true;
        if (start1 == null || start2 == null)
            return false;
        return (start1.data == start2.data) && checkIfEqual(start1.next, start2.next);
    }

    public static <T> boolean search(Node<T> node, T data) {
        if (node == null)
            return false;
        return (data.equals(node.data)) || search(node.next, data);
    }

    public static <T> boolean searchIteratively(Node<T> node, T data) {
        while (node != null) {
            if (node.data.equals(data))
                return true;
            node = node.next;
        }
        return false;
    }

    public static <T> void printNthNodeFromEnd(LinkedList list, int N) {
        if (list == null || list.start == null || N < 1)
            return;
        Node<T> first = null, second = list.start;
        int i = 1;
        while (second != null) {
            if (i == N)
                first = list.start;
            else if (i > N)
                first = first.next;
            second = second.next;
            i++;
        }
        System.out.printf("Node at position:%d from the end is %d%n", N, (first != null ? first.data : null));
    }

    /**
     * @param list
     * @param <T>
     * @return
     */
    public static <T> boolean detectLoop(LinkedList<T> list) {
        if (list == null || list.start == null)
            return false;
        Node<T> slow = list.start;
        Node<T> fast = list.start;
        while (slow != null && fast != null && fast.next != null) {
            slow = fast.next;
            fast = fast.next.next;
            if (fast == slow)
                return true;
        }
        return false;
    }

    public static <T> int countLoopLength(LinkedList<T> list) {
        if (list == null || list.start == null)
            return 0;
        Node<T> slow = list.start;
        Node<T> fast = list.start;
        while (slow != null && fast != null && fast.next != null) {
            slow = fast.next;
            fast = fast.next.next;
            if (fast == slow) {
                int i = 0;
                do {
                    fast = fast.next;
                    i++;
                } while (fast != slow);
                return i;
            }
        }
        return 0;
    }


    // ================ LOOPED LIST========================

    /**
     * This method is also dependent on Floyd’s Cycle detection algorithm.
     * Detect Loop using Floyd’s Cycle detection algorithm and get the pointer to a loop node.
     * Count the number of nodes in loop. Let the count be k.
     * Fix one pointer to the head and another to a kth node from the head.
     * Move both pointers at the same pace, they will meet at loop starting node.
     * Get a pointer to the last node of the loop and make next of it as NULL.
     *
     * @param list
     * @param <T>
     */
    public static <T> void deleteLoop(LinkedList<T> list) {
        int loopLength = countLoopLength(list);
        if (loopLength == 0)
            return;
        Node<T> first = list.start;
        Node<T> second = list.start;
        for (int i = 0; i < loopLength; i++)
            second = second.next;
        while (first != second) {
            first = first.next;
            second = second.next;
        }
        while (second.next != first)
            second = second.next;
        second.next = null;
    }

    public static <T> Node<T> getMiddle(LinkedList<T> list) {
        if (list == null || list.start == null)
            return null;
        Node<T> slow, fast;
        slow = list.start;
        fast = list.start;
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

    // lists identical test
    public static <T> boolean areIdentical(LinkedList<T> list1, LinkedList<T> list2) {
        if (list1 == null && list2 == null)
            return true;
        if (list1 == null || list2 == null)
            return false;
        return checkIfIdentical(list1.start, list2.start);
    }

    private static <T> boolean checkIfIdentical(Node<T> start1, Node<T> start2) {
        if (start1 == null && start2 == null)
            return true;
        if (start1 == null || start2 == null)
            return false;
        return (start1.data == start2.data) && checkIfIdentical(start1.next, start2.next);
    }

    public static <T> void insertAfterNthNodeFromEnd(LinkedList<T> list, int n, T data) {
        if (list == null || list.start == null)
            return;
        Node<T> nthNode = list.start, curr = list.start;

        for (int i = 1; i <= n - 1 && curr != null; i++) {
            curr = curr.next;
        }

        if (curr == null) {
            // hit the list end, cant add the node
            return;
        }

        while (curr.next != null) {
            curr = curr.next;
            nthNode = nthNode.next;
        }

        Node<T> nodeToInsert = new Node<>(data);
        nodeToInsert.next = nthNode.next;
        nthNode.next = nodeToInsert;
    }

    public static int countPairsMatchingSum(LinkedList<Integer> list1, LinkedList<Integer> list2, int sum) {
        if (list1 == null || list1.start == null || list2 == null || list2.start == null)
            return 0;
        HashMap<Integer, Integer> frequencies = new HashMap<>();
        for (Node<Integer> curr = list1.start; curr != null; curr = curr.next) {
            Integer freq = frequencies.get(curr.data);
            if (freq == null)
                frequencies.put(curr.data, 1);
            else
                frequencies.put(curr.data, freq + 1);
        }
        int count = 0;
        for (Node<Integer> curr = list2.start; curr != null; curr = curr.next) {
            int elem = sum - curr.data;
            count += frequencies.getOrDefault(elem, 0);
        }
        return count;
    }

    public static <T> Node<T> getNthNodeFromEnd(LinkedList<T> list, int n) {
        if (list == null || list.start == null)
            return null;
        AtomicInteger count = new AtomicInteger(0);
        return nthNodeUtilFromEnd(list.start, n, count);
    }

    private static <T> Node<T> nthNodeUtilFromEnd(Node<T> curr, int n, AtomicInteger count) {
        if (curr.next == null) {
            count.set(1);
            return curr;
        }
        Node<T> next = nthNodeUtilFromEnd(curr.next, n, count);
        if (count.get() == n)
            return next;
        count.incrementAndGet();
        return curr;
    }

    public static <T> Node<T> getNthNode(LinkedList<T> list, int n) {
        if (list == null || list.start == null)
            return null;
        return nthNodeUtil(list.start, n, 1);
    }

    private static <T> Node<T> nthNodeUtil(Node<T> curr, int n, int count) {
        if (curr == null)
            return null;
        if (count == n)
            return curr;
        return nthNodeUtil(curr.next, n, count + 1);
    }

    public static <T> void flattenMultilevelList(LinkedList<T> list) {
        if (list == null || list.start == null)
            return;
        Node<T> prev = null;
        Node<T> curr = list.start;
        Node<T> nextStart = null;
        Node<T> nextEnd = null;
        while (curr != null) {
            if (curr.prev != null) {
                if (nextStart == null) {
                    nextStart = nextEnd = curr.prev;

                } else {
                    nextEnd.next = curr.prev;
                }
                while (nextEnd != null && nextEnd.next != null)
                    nextEnd = nextEnd.next;
                curr.prev = null;
            }
            prev = curr;
            curr = curr.next;
            if (curr == null) {
                prev.next = nextStart;
                if (nextStart != null) {
                    curr = nextStart;
                    nextStart = nextEnd = null;
                }

            }
        }

    }

    public static <T> void flattenMultilevelListDepthWise(LinkedList<T> list) {
        if (list == null || list.start == null)
            return;
        flattenMultilevelListDepthWise(list.start);
    }

    private static <T> Node<T> flattenMultilevelListDepthWise(Node<T> start) {
        if (start == null)
            return null;
        Node<T> next = start.next;
        start.next = flattenMultilevelListDepthWise(start.prev);
        Node<T> end = start;
        while (end.next != null)
            end = end.next;
        end.next = flattenMultilevelListDepthWise(next);
        start.prev = null;
        return start;
    }

    /**
     * (0,10)->(1,10)->(5,10)->(7,10)
     * |
     * (7,5)->(20,5)->(40,5)
     *
     * @param list
     */
    public static void removeMiddlePointsOfline(LinkedList<Point> list) {
        if (list == null || list.start == null || list.start.next == null)
            return;
        Node<Point> start, end, prev;
        start = list.start;
        prev = null;
        end = start;
        boolean xChange = false;
        boolean check = false;
        if (start.data.x != start.next.data.x)
            xChange = true;
        while (end != null) {
            check = xChange;
            Node<Point> next = end.next;
            if (next != null) {
                if (xChange) {
                    if (end.data.y != next.data.y) {
                        check = false;
                    }

                } else {
                    if (end.data.x != next.data.x)
                        check = true;
                }
            } else {
                start.next = end;
                if (start != prev)
                    prev.next = null;
            }
            if (xChange != check) {
                start.next = end;
                if (start != prev)
                    prev.next = null;
                start = end;
                prev = null;
                xChange = check;
            } else {
                prev = end;
                end = end.next;
            }
        }
    }

    public static <T extends Comparable<T>> void pointRandomToNextHighestNode(LinkedList<T> list) {
        if (list == null || list.start == null)
            return;
        ArrangementUtils.reverse(list);
        Node<T> max = null;
        for (Node<T> curr = list.start; curr != null; curr = curr.next) {
            if (max == null) {
                curr.prev = null;
                max = curr;
                continue;
            }
            curr.prev = max;
            if (max.data.compareTo(curr.data) < 0)
                max = curr;
        }
        ArrangementUtils.reverse(list);
    }

    public static <T> void swapAlternateNodes(LinkedList<T> list) {
        if (list == null || list.start == null)
            return;
        Node<T> curr = list.start;
        Node<T> prev = null;
        while (curr != null) {
            Node<T> next = curr.next;
            if (next != null) {
                curr.next = next.next;
                if (list.start == curr)
                    list.start = next;
                next.next = curr;
                if (prev != null)
                    prev.next = next;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    public static <T> void swapAlternateNodesRecursively(LinkedList<T> list) {
        if (list == null || list.start == null)
            return;
        list.start = swapAlternateNodesRecursivelyUtil(list.start);
    }

    private static <T> Node<T> swapAlternateNodesRecursivelyUtil(Node<T> start) {
        if (start == null)
            return null;
        Node<T> next = start.next;
        if (next != null) {
            start.next = swapAlternateNodesRecursivelyUtil(next.next);
            next.next = start;
            return next;
        }
        return start;
    }

    public static class DeleteUtils {

        /**
         * 1->2->3->4->5, m = 2, n = 2
         *
         * @param list
         * @param m
         * @param n
         */
        public static void deleteNAfterMNodes(LinkedList<Integer> list, int m, int n) {
            if (list == null || list.start == null || m < 0 || n < 0)
                return;
            if (n == 0)
                return; // nothing to delete
            Node<Integer> curr = list.start;
            for (int i = 1; i < m && curr != null; i++)
                curr = curr.next;
            if (curr == null)
                return; // hit the end of list.
            Node end = curr; // store the new end of first part of list
            for (int i = (m == 0) ? 1 : 0; i < n && curr.next != null; i++)
                curr = curr.next;
            if (m == 0) {
                list.start = curr.next;
            } else
                end.next = curr.next;
            curr.next = null;
        }

        static public <T> void deleteAlternate(LinkedList<T> list, boolean even) {
            if (list == null || list.start == null)
                return;

            if (!even) {
                list.start = list.start.next;
            }
            Node<T> curr = list.start;
            while (curr != null && curr.next != null) {
                Node<T> next = curr.next;
                curr.next = next.next;
                curr = curr.next;
                next.next = null;
            }
        }

        /**
         * 1. Reverse the list.
         * <p>
         * 2. Traverse the reversed list. Keep max till now. If next node < max,
         * then delete the next node, otherwise max = next node.
         * <p>
         * 3. Reverse the list again to retain the original order. Time Complexity:
         * O(n)
         *
         * @param list
         */
        static public void deleteIfRightIsHigher(LinkedList<Integer> list) {
            if (list == null || list.start == null)
                return;
            ArrangementUtils.reverse(list);
            Node<Integer> current, max, temp;
            current = max = list.start;
            while (current != null && current.next != null) {
                if (current.next.data < max.data) {
                    temp = current.next;
                    current.next = temp.next;
                    temp.next = null;
                } else {
                    max = current;
                    current = current.next;
                }
            }
            ArrangementUtils.reverse(list);
        }

        // DELETION ALGOS.
        static public <T> void deleteMiddle(LinkedList<T> list) {
            if (list == null || list.start == null)
                return;
            Node<T> slow = list.start;
            Node<T> fast = list.start;
            Node<T> prev = null;
            int i = 1;
            while (fast != null) {
                fast = fast.next;
                if (fast == null)
                    break;
                i++;
                fast = fast.next;
                if (fast != null) {
                    i++;
                    prev = slow;
                    slow = slow.next;
                }
            }
            if (i % 2 == 0)
                slow.next = slow.next.next;
            else
                prev.next = slow.next;
        }

        private static <T> void shiftLeftAndDeleteLast(LinkedList<T> list, Node<T> x) {
            if (x == null)
                return; // no such element.
            if (x.next == null) { // last node
                Node end = list.start;
                while (end.next != x)
                    end = end.next;
                end.next = null;
                return;
            }
            while (x.next != null) {
                Node<T> y = x.next;
                x.data = y.data;
                if (y.next == null) {
                    x.next = null;
                } else {
                    x = y;
                }
            }
        }

        static public <T> void deleteLastOccurence1(LinkedList<T> list, T data) {
            if (list == null || list.start == null)
                return;
            Node<T> x = null;
            for (Node<T> curr = list.start; curr != null; curr = curr.next) {
                if (curr.data.equals(data))
                    x = curr;
            }
            shiftLeftAndDeleteLast(list, x);
        }

        /**
         * ip: 1-2-3-4-2-5, data = 2
         * op: 1-2-3-4-5
         *
         * @param list
         * @param data
         * @param <T>
         */
        static public <T> void deleteLastOccurence(LinkedList<T> list, T data) {
            if (list == null || list.start == null)
                return;
            Node<T> prev = null;
            for (Node<T> curr = list.start; curr != null; curr = curr.next) {
                if (curr.next != null && curr.next.data.equals(data))
                    prev = curr;
            }
            if (prev == null) {
                if (list.start.data.equals(data)) {
                    prev = list.start;
                    list.start = list.start.next;
                    prev.next = null;
                    return;
                }
                return; // no such element
            }
            Node<T> node = prev.next;
            prev.next = node.next;
            node.next = null;
        }

        static public <T> void deleteDupesFromSortedList(LinkedList<T> list) {
            if (list == null || list.start == null)
                return;
            boolean dupeFound = false;
            Node<T> start, end;
            start = end = list.start;
            while (end != null) {
                if (end.next != null && end.next.data == start.data) {
                    dupeFound = true; // mark dupe
                    end = end.next; // only move end
                } else {
                    if (dupeFound) {
                        start.next = end.next; // delete the intermediate dupes
                        dupeFound = false; // reset the flag
                        start = start.next; // reset the start to new element
                        end.next = null; // delete
                        end = start; // reset the end to new element
                    } else {
                        // no dupes just keep moving both pointers
                        start = start.next;
                        end = end.next;
                    }
                }
            }
        }

        static public <T> void deleteDupesFromUnsortedList(LinkedList<T> list) {
            if (list == null || list.start == null)
                return;
            Set<T> visited = new HashSet<>();
            Node<T> curr = list.start;
            Node<T> prev = null;
            while (curr != null) {
                if (visited.contains(curr.data)) {
                    prev.next = curr.next;
                    curr.next = null;
                    curr = prev.next;
                } else {
                    visited.add(curr.data);
                    prev = curr;
                    curr = curr.next;
                }
            }
        }
    }

    public static class ArrangementUtils {
        public static <T> void reverseIteratively(LinkedList<T> list) {
            if (list == null || list.start.next == null)
                return;
            Node<T> prev = null, curr = list.start;
            while (curr != null) {
                Node<T> next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            list.start = prev;
        }

        public static <T> void reverse(LinkedList<T> list) {
            reversify(null, list.start, list);
        }

        private static <T> void reversify(Node<T> prev, Node<T> curr, LinkedList<T> list) {
            if (curr == null) {
                list.start = prev;
                return;
            }
            reversify(curr, curr.next, list);
            curr.next = prev;
        }

        /**
         * Input : 1->2->3->4->5
         * <p>
         * Output: 2->1->4->3->5
         *
         * @param list
         */

        static public <T> void reversePairs(LinkedList<T> list) {
            Node<T> curr = list.start;
            while (curr != null) {
                Node<T> nextNode = curr.next;
                if (nextNode == null)
                    break;
                T temp = nextNode.data;
                nextNode.data = curr.data;
                curr.data = temp;
                nextNode = nextNode.next;
                curr = nextNode;
            }
        }

        /**
         * Input: [1 3 8 2 7 5 6 4]
         * <p>
         * Output: [1 8 2 7 3 6 4 5]
         * <p>
         * Input: [1 2 3 4 5 6 7]
         * <p>
         * Output: [1 7 2 6 3 5 4]
         * <p>
         * Input: [1 6 2 5 3 4]
         * <p>
         * Output: [1 6 2 5 3 4]
         *
         * @param arr
         * @return
         */

        static public <T extends Comparable<T>> LinkedList<T> rearrangeArrayIntoAlternateMinMaxList(T[] arr) {
            Arrays.sort(arr);
            int n = arr.length;
            int start = 0;
            int end = n - 1;
            LinkedList<T> list = new LinkedList<>();
            while (start < end) {
                list.insert(arr[start++]);
                list.insert(arr[end--]);
            }
            if (n % 2 != 0)
                list.insert(arr[start]);
            return list;
        }

        /**
         * if the given linked list is 10->20->30->40->50->60 and k is 4, the list
         * should be modified to 50->60->10->20->30->40
         *
         * @param list
         * @param n
         */
        static public <T> void rotateLeftBy(LinkedList<T> list, int n) {
            if (list == null || list.start == null)
                return;
            if (n == list.size())
                return;
            if (n > list.size())
                n = n % list.size();
            Node<T> curr = list.start;
            for (int i = 1; i < n; i++)
                curr = curr.next;
            Node<T> newStart = curr.next;
            curr.next = null;
            curr = newStart;
            while (curr.next != null)
                curr = curr.next;
            curr.next = list.start;
            list.start = newStart;
        }

        /**
         * if the given linked list is 10->20->30->40->50->60 and k is 4, the list
         * should be modified to 30->40->50->60->10->20->NULL
         *
         * @param list
         * @param n
         */
        static public <T> void rotateRightBy(LinkedList<T> list, int n) {
            if (list == null || list.start == null)
                return;
            if (n == list.size())
                return;
            if (n > list.size())
                n = n % list.size();
            Node<T> curr = list.start;
            for (int i = 1; i < n; i++)
                curr = curr.next;
            Node<T> start = list.start, end = null;
            while (curr.next != null) {
                end = start;
                start = start.next;
                curr = curr.next;
            }
            end.next = null;
            curr.next = list.start;
            list.start = start;
        }

        /**
         * 1-> 2-> 3-> 4-> 5-> NULL
         * Op: 1-3-5-2-4-NULL
         *
         * @param list
         * @param <T>
         */
        public static <T> void rearrageOddEvenTogether(LinkedList<T> list) {
            if (list == null || list.start.next == null)
                return;
            Node<T> odd = list.start;
            Node<T> even = odd.next;
            Node<T> evenStart = even;
            boolean oddRound = true;
            while (odd.next != null && even.next != null) {
                if (oddRound) {
                    odd.next = even.next;
                    odd = odd.next;
                } else {
                    even.next = odd.next;
                    even = even.next;
                }
                oddRound = !oddRound;
            }
            odd.next = evenStart; // set the odd.next to the start of even
            even.next = null; // set the even.next = NULL to end the list
        }

        /**
         * Given a singly linked list L0 -> L1 -> … -> Ln-1 -> Ln.
         * Rearrange the nodes in the list so that the new formed list is : L0 -> Ln -> L1 -> Ln-1 -> L2 -> Ln-2 …
         * You are required to do this in-place without altering the nodes’ values.
         * Exam
         * ples:
         * <p>
         * Input:  1 -> 2 -> 3 -> 4
         * Output: 1 -> 4 -> 2 -> 3
         * <p>
         * Input:  1 -> 2 -> 3 -> 4 -> 5
         * Output: 1 -> 5 -> 2 -> 4 -> 3
         *
         * @param list
         * @param <T>
         */
        public static void rearrangeInPlace(LinkedList<Integer> list) {
            if (list == null || list.start.next == null)
                return;
            int n = list.size();
            int[] arr = new int[n];
            int i = 0;
            for (Node<Integer> curr = list.start; curr != null; curr = curr.next)
                arr[i++] = curr.data;
            for (int j = 1; j < n; j = j + 2) {
                ArrayUtils.rightRotateByReversal(arr, 1, j, n - 1);
            }
            i = 0;
            for (Node<Integer> curr = list.start; curr != null; curr = curr.next)
                curr.data = arr[i++];
        }

        /**
         * Input:  11->15->20->5->10
         * Output: 11->20->5->15->10
         *
         * @param list
         */
        public static void rearrageListInZigZag(LinkedList<Integer> list) {
            if (list == null || list.start == null)
                return;
            Node<Integer> curr = list.start;
            boolean smallerThanNext = true;
            while (curr.next != null) {
                Node<Integer> nextNode = curr.next;
                Node<Integer> target = null;
                if (smallerThanNext && curr.data > nextNode.data) { // if current is not smaller than next, correct the next
                    for (target = nextNode; target != null && target.data < curr.data; target = target.next) ;
                    if (target == null)
                        target = curr;
                } else if (!smallerThanNext && curr.data < nextNode.data) { // if current is not greater than next, correct the next
                    for (target = nextNode; target != null && target.data > curr.data; target = target.next) ;
                    if (target == null)
                        target = curr;
                }
                if (target != null) {
                    int temp = target.data;
                    target.data = nextNode.data;
                    nextNode.data = temp;
                }
                smallerThanNext = !smallerThanNext;
                curr = nextNode;
            }
        }

        /**
         * Input : 11 -> 20 -> 40 -> 55 -> 77 -> 80 -> NULL
         * Output : 11 -> 20 -> 55 -> 40 -> 77 -> 80 -> NULL
         * 20, 40, 80 occur in even positions and 11, 55, 77
         * occur in odd positions.
         * <p>
         * Input : 10 -> 1 -> 2 -> 3 -> 5 -> 6 -> 7 -> 8 -> NULL
         * Output : 1 -> 10 -> 3 -> 2 -> 5 -> 6 -> 7 -> 8 -> NULL
         * 1, 3, 5, 7 occur in odd positions and 10, 2, 6, 8 occur
         * at even positions in the list
         *
         * @param list
         */
        public static void rearrangeOddAtOddPositionAndEvenAtEvenPosition(LinkedList<Integer> list) {
            if (list == null || list.start == null)
                return;
            Node<Integer> curr = list.start;
            for (int i = 1; curr != null; i++) {
                Node<Integer> nextNode = curr.next;
                Node<Integer> target = null;
                if ((i % 2) == 1 && curr.data % 2 != 1) {
                    for (target = nextNode; target != null && target.data % 2 != 1; target = target.next) ;
                }
                if ((i % 2) == 0 && curr.data % 2 != 0) {
                    for (target = nextNode; target != null && target.data % 2 != 0; target = target.next) ;
                }
                if (target != null) {
                    int temp = target.data;
                    target.data = curr.data;
                    curr.data = temp;
                }
                curr = nextNode;
            }
        }

        public static <T> void makeMiddleAsHead(LinkedList<T> list) {
            if (list == null || list.start == null || list.start.next == null)
                return;
            Node<T> middle = getMiddle(list);
            LinkedList<T> secondHalf = null;
            if (middle.next != null) {
                secondHalf = new LinkedList<>(middle.next);
                middle.next = null;
            }
            rotateRightBy(list, 1);
            Node<T> curr = list.start;
            while (curr.next != null)
                curr = curr.next;
            curr.next = secondHalf.start;
        }

        public static <T> void rotateRightInBlocks(LinkedList<T> list, int k, int r) {
            if (list == null || list.start == null || k < 0 || r < 0)
                return;
            if (k == 1) {
                rotateRightBy(list, r);
                return;
            }
            list.start = rotateRightInBlocksUtil(list.start, k, r);
        }

        private static <T> Node<T> rotateRightInBlocksUtil(Node<T> start, int k, int r) {
            if (start == null)
                return null;
            Node<T> curr = start;
            for (int i = 1; i < k && curr.next != null; curr = curr.next, i++) ;
            Node<T> next = curr.next;
            curr.next = null;
            LinkedList<T> block = new LinkedList<>(start);
            rotateRightBy(block, r);
            for (curr = block.start; curr.next != null; curr = curr.next) ;
            curr.next = rotateRightInBlocksUtil(next, k, r);
            return block.start;
        }

        /**
         * Input  : 1 -> 2 -> 2 -> 4 -> 3
         * key = 2
         * Output : 1 -> 4 -> 3 -> 2 -> 2
         * <p>
         * Input  : 6 -> 6 -> 7 -> 6 -> 3 -> 10
         * key = 6
         * Output : 7 -> 3 -> 10 -> 6 -> 6 -> 6
         *
         * @param list
         * @param k
         * @param <T>
         */
        public static <T> void moveAllOccurencesOfKToEnd(LinkedList<T> list, T k) {
            if (list == null || list.start == null)
                return;
            int freq = 0;
            Node<T> curr = list.start;
            Node<T> prev = null;
            while (curr != null) {
                Node<T> next = curr.next;
                if (curr.data.equals(k)) {
                    freq++;
                    if (prev != null) {
                        prev.next = next;
                    }
                    if (list.start == curr) {
                        list.start = next;
                    }
                    curr.next = null;
                } else {
                    prev = curr;
                }
                curr = next;
            }
            for (int i = 0; i < freq; i++) {
                prev.next = new Node<>(k);
                prev = prev.next;
            }
        }

        public static class ReverseInGroupsOfKUtils {
            public static <T> void reverse(LinkedList<T> list, int k) {
                list.start = reverseInGroup(list.start, k);
            }

            private static <T> Node<T> reverseInGroup(Node<T> start, int k) {
                if (start == null)
                    return null;
                Node<T> curr = start, prev = null, next = null;
                for (int i = 0; i < k && curr != null; i++) {
                    next = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = next;
                }
                start.next = reverseInGroup(next, k);
                return prev;
            }

            public static <T> void reverseAlternatively(LinkedList<T> list, int k) {
                list.start = reverseAlternativelyInGroup(list.start, k, true);
            }

            private static <T> Node<T> reverseAlternativelyInGroup(Node<T> start, int k, boolean reverse) {
                if (start == null)
                    return null;
                Node<T> curr = start, prev = null, next = null;
                for (int i = 0; i < k && curr != null; i++) {
                    next = curr.next;
                    if (reverse) // reverse the list only if current recursion is for reversal
                        curr.next = prev;
                    prev = curr;
                    curr = next;
                }
                if (!reverse) {
                    // no reversal hence point prev to the start of next reversed group.
                    prev.next = reverseAlternativelyInGroup(next, k, !reverse);
                    return start; // return the start of current non-reversed group.
                }
                start.next = reverseAlternativelyInGroup(next, k, !reverse);
                return prev;
            }

            /**
             * 1-> 2-> 3-> 4-> 5-> 6 ->7-> NULL
             *
             * @param list
             * @param k
             */
            public static <T> void reverseIteratively(LinkedList<T> list, int k) {
                Node<T> curr = list.start, prev = null, prevEnd = null;
                boolean startSet = false;
                while (curr != null) {
                    Node<T> start = curr;
                    // reverse group of size k.
                    for (int i = 0; i < k && curr != null; i++) {
                        Node<T> next = curr.next;
                        curr.next = prev;
                        prev = curr;
                        curr = next;
                    }
                    // reversed k nodes, prev = last node, curr = start of next group.
                    if (!startSet) {
                        list.start = prev;
                        startSet = true;
                        start.next = curr; // point the current group start next to start of next group
                    } else {
                        prevEnd.next = prev; // second group onwards point the end.next of previous group to start of curr gp
                    }
                    prevEnd = start; // stores the end of prev reversed group.
                    prev = null; // reset prev
                }
            }
        }
    }

    public static class SortUtils {

        // ############## SORTING ALGOS
        // ############################################################################################################

        static public <T extends Comparable<T>> LinkedList<T> mergeSort(LinkedList<T> list) {
            if (list == null || list.start == null)
                return null;
            LinkedList<T> second = partition(list);
            if (second != null) {
                list = mergeSort(list);
                second = mergeSort(second);
            }
            return MergeUtils.merge(list, second);
        }

        static public <T extends Comparable<T>> LinkedList<T> partition(LinkedList<T> mainList) {
            if (mainList == null || mainList.start == null)
                return null;
            Node<T> middle = getMiddle(mainList);
            Node<T> temp = middle.next;
            middle.next = null;
            if (temp != null)
                return new LinkedList<>(temp);
            return null;
        }

        /**
         * Input : 1 -> -10 output: -10 -> 1
         * <p>
         * Input : 1 -> -2 -> -3 -> 4 -> -5 output: -5 -> -3 -> -2 -> 1 -> 4
         * <p>
         * Input : -5 -> -10 Output: -10 -> -5
         * <p>
         * Input : 5 -> 10 output: 5 -> 10
         *
         * @param list
         */
        static public void sortAbsolutelySortedList(LinkedList<Integer> list) {
            if (list == null || list.start == null)
                return;
            Node<Integer> prev = list.start;
            Node<Integer> curr = prev.next;
            while (curr != null) {
                if (curr.data < prev.data) {
                    prev.next = curr.next;
                    curr.next = list.start;
                    list.start = curr;
                    curr = prev;
                } else {
                    prev = curr;
                    curr = curr.next;
                }
            }
        }

        static public <T extends Comparable<T>> LinkedList<T> sortAlternativelySortedList(LinkedList<T> list) {
            if (list == null || list.start == null)
                return null;
            LinkedList<T> secondList = partitionAlternatively(list);
            ArrangementUtils.reverse(secondList);
            return MergeUtils.merge(list, secondList);
        }

        private static <T extends Comparable<T>> LinkedList<T> partitionAlternatively(LinkedList<T> list) {
            Node<T> curr = list.start;
            Node<T> second = curr.next;
            while (curr != null) {
                Node<T> next = curr.next;
                if (next != null) {
                    curr.next = next.next;
                    if (curr.next != null)
                        next.next = curr.next.next;
                    else
                        next.next = null;
                }
                curr = curr.next;
            }
            return new LinkedList<>(second);
        }

        public static <T> LinkedList<T> partitionAlternativelyUsingRecursion(LinkedList<T> list) {
            if (list == null || list.start == null)
                return null;
            Node<T> second = partitionAlternativelyUsingRecursion(list.start);
            return new LinkedList<>(second);
        }

        private static <T> Node<T> partitionAlternativelyUsingRecursion(Node<T> curr) {
            if (curr == null) {
                return null;
            }
            Node<T> next = curr.next;
            if (next != null) {
                curr.next = next.next;
                next.next = partitionAlternativelyUsingRecursion(curr.next);
                return next;
            }
            return null;
        }

        public static void sortListOf012(LinkedList<Integer> list) {
            if (list == null || list.start == null)
                return;
            int n1, n2, n3;
            n1 = n2 = n3 = 0;
            for (Node<Integer> curr = list.start; curr != null; curr = curr.next) {
                if (curr.data == 0)
                    n1++;
                else if (curr.data == 1)
                    n2++;
                else
                    n3++;
            }
            for (Node<Integer> curr = list.start; curr != null; curr = curr.next) {
                if (n1 > 0) {
                    curr.data = 0;
                    n1--;
                } else if (n2 > 0) {
                    curr.data = 1;
                    n2--;
                } else {
                    curr.data = 2;
                    n3--;
                }
            }
        }

        public static <T extends Comparable<T>> void selectionSort(LinkedList<T> list) {
            if (list == null || list.start == null)
                return;
            list.start = selectionSortUtil(list, list.start);
        }

        private static <T extends Comparable<T>> Node<T> selectionSortUtil(LinkedList<T> list, Node<T> start) {
            if (start == null)
                return null;
            Node<T> min = start;
            for (Node<T> curr = start; curr != null; curr = curr.next)
                if (curr.data.compareTo(min.data) < 0)
                    min = curr;
            swap(list, min.data, start.data);
            min.next = selectionSortUtil(list, min.next);
            return min;
        }

        public static <T extends Comparable<T>> void insertionSort(LinkedList<T> list) {
            if (list == null || list.start == null)
                return;
            Node<T> curr = list.start.next;
            Node<T> prev = list.start;
            // 5-3-4-2-1
            while (curr != null) {
                if (curr.data.compareTo(list.start.data) < 0) {
                    prev.next = curr.next;
                    curr.next = list.start;
                    list.start = curr;
                    curr = prev.next;
                } else {
                    // 3-4-5-7-1
                    Node<T> x = list.start;
                    for (; x.next.data.compareTo(curr.data) < 0; x = x.next) ;
                    if (x != prev) {
                        prev.next = curr.next;
                        curr.next = x.next;
                        x.next = curr;
                        curr = prev.next;
                    } else {
                        prev = curr;
                        curr = curr.next;
                    }
                }
            }
        }
    }

    public static class PalindromeUtils {

        public static <T> boolean checkIfListIsPalindrome(LinkedList<T> list) {
            if (list == null || list.start == null)
                return false;

            Node<T> middle = getMiddle(list);
            if (middle.next == null)
                return true; // only one node, its always a palindrome
            LinkedList<T> secondHalf = new LinkedList<>(middle.next);
            ArrangementUtils.reverse(secondHalf);
            boolean check = true;
            for (Node<T> x = list.start, y = secondHalf.start; y != null; y = y.next, x = x.next)
                if (x.data != y.data) {
                    check = false;
                    break;
                }
            ArrangementUtils.reverse(secondHalf);
            return check;
        }

        /**
         * Input  : List = 2->3->7->3->2->12->24
         * Output : 5
         * The longest palindrome list is 2->3->7->3->2
         * <p>
         * Input  : List = 12->4->4->3->14
         * Output : 2
         * The longest palindrome list is 4->4
         *
         * @param list
         * @param <T>
         * @return
         */
        public static <T> int lengthOfLargestPalindrome(LinkedList<T> list) {
            int result = 0;
            Node<T> prev = null, curr = list.start;
            while (curr != null) {
                Node<T> next = curr.next;
                result = Math.max(result, countSame(curr, next) + 1);
                result = Math.max(result, 2 * countSame(prev, next) + 1);
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            list.start = prev;
            ArrangementUtils.reverse(list);
            return result;
        }

        private static <T> int countSame(Node<T> n1, Node<T> n2) {
            int count = 0;
            while (n1 != null && n2 != null && n1.data == n2.data) {
                ++count;
                n1 = n1.next;
                n2 = n2.next;
            }
            return count;
        }
    }

    public static class MergeUtils {

        static public <T extends Comparable<T>> LinkedList<T> merge(LinkedList<T> list1, LinkedList<T> list2) {
            if ((list1 == null || list1.start == null) && (list2 == null || list2.start == null))
                return null;
            if (list2 == null || list2.start == null)
                return list1;
            if (list1 == null || list1.start == null)
                return list2;
            Node<T> resultNode = merge(list1.start, list2.start);
            return new LinkedList<>(resultNode);
        }

        static <T extends Comparable<T>> Node<T> merge(Node<T> start1, Node<T> start2) {
            Node<T> curr = null;
            if (start1 == null)
                return start2;
            if (start2 == null)
                return start1;
            if (start1.data.compareTo(start2.data) < 0) {
                curr = start1;
                curr.next = merge(start1.next, start2);
            } else {
                curr = start2;
                curr.next = merge(start1, start2.next);
            }
            return curr;
        }

        /**
         * 1-2-4
         * 3-5
         * 1-2-3-4-5
         *
         * @param list1
         * @param list2
         * @param <T>
         * @return
         */
        static <T extends Comparable<T>> LinkedList<T> mergeIteratively(LinkedList<T> list1, LinkedList<T> list2) {
            if ((list1 == null || list1.start == null) && (list2 == null || list2.start == null))
                return null;
            if (list2 == null || list2.start == null)
                return list1;
            if (list1 == null || list1.start == null)
                return list2;

            Node<T> prev = null;
            Node<T> start1 = list1.start;
            Node<T> start2 = list2.start;
            Node<T> curr = null;
            Node<T> start = start1.data.compareTo(start2.data) < 0 ? start1 : start2;
            while (start1 != null && start2 != null) {
                if (start1.data.compareTo(start2.data) < 0) {
                    curr = start1;
                    start1 = start1.next;

                } else {
                    curr = start2;
                    start2 = start2.next;

                }
                if (prev != null) {
                    prev.next = curr;
                }
                prev = curr;
            }
            if (start2 != null)
                prev.next = start2;
            if (start1 != null)
                prev.next = start1;
            list1.start = null;
            list2.start = null;
            return new LinkedList<>(start);
        }

        /**
         * Input:
         * First List: 2->4->7->8->10
         * Second List: 1->3->12
         * <p>
         * Output:
         * First List: 1->2->3->4->7
         * Second List: 8->10->12
         */

        static public void mergeInPlace(LinkedList<Integer> list1, LinkedList<Integer> list2) {
            if ((list1 == null || list1.start == null) || (list2 == null || list2.start == null))
                return;
            int size = list1.size();
            list1.start = merge(list1.start, list2.start);
            Node<Integer> curr = list1.start;
            for (int i = 1; i <= size - 1; i++)
                curr = curr.next;
            list2.start = curr.next;
            curr.next = null;
        }

        static public <T extends Comparable<T>> void mergeInPlaceWithoutFirstListPointerMod(LinkedList<T> list1, LinkedList<T> list2) {
            if ((list1 == null || list1.start == null) || (list2 == null || list2.start == null))
                return;
            Node<T> start1 = list1.start;
            Node<T> start2 = list2.start;
            while (start1 != null) {
                if (start1.data.compareTo(start2.data) > 0) {
                    // swap the value.
                    T temp = start2.data;
                    start2.data = start1.data;
                    start1.data = temp;
                }
                // list 2 may be unsorted after swap, so place the first node in its right position.
                Node<T> curr = start2.next;
                Node<T> prev = start2;
                while (curr != null && prev.data.compareTo(curr.data) > 0) {
                    T temp = curr.data;
                    curr.data = prev.data;
                    prev.data = temp;
                    curr = curr.next;
                    prev = prev.next;
                }
                start1 = start1.next;
            }
        }

        /**
         * For example, if first list is 5->7->17->13->11 and second is
         * 12->10->2->4->6,
         * <p>
         * the first list should become 5->12->7->10->17->2->13->4->11->6 and second
         * list should become empty.
         * <p>
         * The nodes of second list should only be inserted when there are positions
         * available.
         * <p>
         * For example, if the first list is 1->2->3 and second list is
         * 4->5->6->7->8, then first list should become 1->4->2->5->3->6 and second
         * list to 7->8.
         *
         * @param list1
         * @param list2
         * @return
         */

        static public LinkedList<Integer> mergeAlternatively(LinkedList<Integer> list1, LinkedList<Integer> list2) {
            if ((list1 == null || list1.start == null) && (list2 == null || list2.start == null))
                return null;
            if (list2 == null)
                return list1;
            if (list1 == null)
                return list2;
            Node<Integer> resultNode = null;
            resultNode = mergeAlternatively(list1.start, list2.start, 0);
            return new LinkedList<>(resultNode);
        }

        private static Node<Integer> mergeAlternatively(Node<Integer> start1, Node<Integer> start2, int round) {
            Node<Integer> curr = null;
            if (start1 == null)
                return start2;
            if (start2 == null)
                return start1;
            if (round % 2 == 0) {
                curr = start1;
                curr.next = mergeAlternatively(start1.next, start2, ++round);
            } else {
                curr = start2;
                curr.next = mergeAlternatively(start1, start2.next, ++round);
            }
            return curr;
        }

        public static <T extends Comparable<T>> LinkedList<T> mergeKLists(LinkedList<T>[] lists) {
            for (int i = 1; i < lists.length; i++) {
                lists[0] = merge(lists[0], lists[i]);
            }
            return lists[0];
        }
    }

    public static class ArithematicUtils {
        public static Double subtractLists(LinkedList<Integer> leftOperand, LinkedList<Integer> rightOperand) {
            int resultSize = 0;
            LinkedList<Integer> result = new LinkedList<>();
            Node<Integer> start1 = leftOperand.start;
            Node<Integer> start2 = rightOperand.start;
            int size1 = 0;
            int size2 = 0;
            while (start1 != null || start2 != null) {
                result.insert(new Node<>(0));
                if (start1 != null) {
                    size1++;
                    start1 = start1.next;
                }
                if (start2 != null) {
                    size2++;
                    start2 = start2.next;
                }
            }
            int finalCarry = 0;
            Node<Integer> temp = null;
            if (size1 != size2) {
                Node<Integer> next = leftOperand.start;
                if (size1 > size2)
                    next = rightOperand.start;
                int diff = Math.abs(size1 - size2);
                for (int i = 0; i < diff; i++) {
                    Node<Integer> x = new Node<>(0);
                    x.next = next;
                    next = x;
                    if (temp == null)
                        temp = next;
                }
                if (size1 > size2) {
                    resultSize = size1;
                    finalCarry = subtract(leftOperand.start, next, result.start);
                } else {
                    resultSize = size2;
                    finalCarry = subtract(next, rightOperand.start, result.start);
                }
                temp.next = null;
            } else {
                resultSize = size1;
                finalCarry = subtract(leftOperand.start, rightOperand.start, result.start);
            }

            Double number = toDouble(result, resultSize);
            if (finalCarry < 0) {
                number = -1 * (Math.pow(10, resultSize) - number);
            }
            return number;
        }

        private static int subtract(Node<Integer> start1, Node<Integer> start2, Node<Integer> result) {
            if (result == null)
                return 0;
            int diff = start1.data - start2.data + subtract(start1.next, start2.next, result.next);
            if (diff < 0) {
                result.data = 10 + diff;
                return -1;
            }
            result.data = diff;
            return 0;
        }


        public static Double addLists(LinkedList<Integer> leftOperand, LinkedList<Integer> rightOperand) {
            int resultSize = 0;
            LinkedList<Integer> result = new LinkedList<>();
            Node<Integer> start1 = leftOperand.start;
            Node<Integer> start2 = rightOperand.start;
            int size1 = 0;
            int size2 = 0;
            while (start1 != null || start2 != null) {
                result.insert(new Node<>(0));
                if (start1 != null) {
                    size1++;
                    start1 = start1.next;
                }
                if (start2 != null) {
                    size2++;
                    start2 = start2.next;
                }
            }
            int finalCarry = 0;
            Node<Integer> temp = null;
            if (size1 != size2) {
                Node<Integer> next = leftOperand.start;
                if (size1 > size2)
                    next = rightOperand.start;
                int diff = Math.abs(size1 - size2);
                for (int i = 0; i < diff; i++) {
                    Node<Integer> x = new Node<>(0);
                    x.next = next;
                    next = x;
                    if (temp == null)
                        temp = next;
                }
                if (size1 > size2) {
                    resultSize = size1;
                    finalCarry = add(leftOperand.start, next, result.start);
                } else {
                    resultSize = size2;
                    finalCarry = add(next, rightOperand.start, result.start);
                }
                temp.next = null;
            } else {
                resultSize = size1;
                finalCarry = add(leftOperand.start, rightOperand.start, result.start);
            }
            Double number = toDouble(result, resultSize);
            if (finalCarry == 1)
                number = Math.pow(10, resultSize) + number;
            return number;
        }

        public static int add(Node<Integer> start1, Node<Integer> start2, Node<Integer> result) {
            if (result == null)
                return 0;
            int sum = start1.data + start2.data + add(start1.next, start2.next, result.next);
            int carry = sum / 10;
            if (sum >= 10)
                sum = sum % 10;
            result.data = sum;
            return carry;
        }
    }

    public static class Point {
        final int x;
        final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static class UnionIntersection {
        public static <T extends Comparable<T>> LinkedList<T> getIntersection(LinkedList<T> list1, LinkedList<T> list2) {
            if ((list1 == null || list1.start == null) && (list2 == null || list2.start == null))
                return null;
            list1 = SortUtils.mergeSort(list1);
            list2 = SortUtils.mergeSort(list2);
            LinkedList<T> intersection = new LinkedList<>();
            intersect(list1.start, list2.start, intersection);
            return intersection;
        }

        private static <T extends Comparable<T>> void intersect(Node<T> start1, Node<T> start2, LinkedList<T> intersection) {
            if (start1 == null || start2 == null)
                return;
            if (start1.data.compareTo(start2.data) < 0)
                intersect(start1.next, start2, intersection);
            else if (start1.data.compareTo(start2.data) > 0)
                intersect(start1, start2.next, intersection);
            else {
                intersection.insert(start1.data);
                intersect(start1.next, start2.next, intersection);
            }
        }

        public static <T extends Comparable<T>> LinkedList<T> getUnion(LinkedList<T> list1, LinkedList<T> list2) {
            if ((list1 == null || list1.start == null) && (list2 == null || list2.start == null))
                return null;
            list1 = SortUtils.mergeSort(list1);
            list2 = SortUtils.mergeSort(list2);
            LinkedList<T> intersection = new LinkedList<>();
            union(list1.start, list2.start, intersection);
            return intersection;
        }

        private static <T extends Comparable<T>> void union(Node<T> start1, Node<T> start2, LinkedList<T> union) {
            if (start1 == null && start2 == null)
                return;
            if (start1 == null) {
                for (Node<T> curr = start2; curr != null; curr = curr.next)
                    union.insert(curr.data);
                return;
            }
            if (start2 == null) {
                for (Node<T> curr = start1; curr != null; curr = curr.next)
                    union.insert(curr.data);
                return;
            }

            if (start1.data.compareTo(start2.data) < 0) {
                union.insert(start1.data);
                union(start1.next, start2, union);
            } else if (start1.data.compareTo(start2.data) > 0) {
                union.insert(start2.data);
                union(start1, start2.next, union);
            } else {
                union.insert(start1.data);
                union(start1.next, start2.next, union);
            }
        }

        public static <T> Node<T> getIntersectionPoint(LinkedList<T> list1, LinkedList<T> list2) {
            if ((list1 == null || list1.start == null) && (list2 == null || list2.start == null))
                return null;
            int a = 0, b = 0;
            Node<T> c1 = list1.start, c2 = list2.start;
            while (c1 != null || c2 != null) {
                if (c1 != null) {
                    a++;
                    c1 = c1.next;
                }

                if (c2 != null) {
                    b++;
                    c2 = c2.next;
                }
            }
            int d = Math.abs(a - b);
            c1 = list1.start;
            c2 = list2.start;
            if (a > b)
                for (int i = 0; i < d; i++, c1 = c1.next) ;
            else if (a < b)
                for (int i = 0; i < d; i++, c2 = c2.next) ;

            while (c1 != null && c2 != null) {
                if (c1 == c2)
                    return c1;
                c1 = c1.next;
                c2 = c2.next;
            }


            return null;
        }

    }
}