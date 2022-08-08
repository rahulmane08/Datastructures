package datastructures.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static datastructures.tree.BSTUtils.ceil;
import static datastructures.tree.BSTUtils.checkIfPreorderIsBST;
import static datastructures.tree.BSTUtils.convertToSortedCLL;
import static datastructures.tree.BSTUtils.convertToSortedDLL;
import static datastructures.tree.BSTUtils.createBstUsingPreorderSequence;
import static datastructures.tree.BSTUtils.floor;
import static datastructures.tree.BSTUtils.getCommonNodes;
import static datastructures.tree.BSTUtils.inorderPredecessor;
import static datastructures.tree.BSTUtils.inorderSuccessor;
import static datastructures.tree.BSTUtils.isBST;
import static datastructures.tree.BSTUtils.isBST1;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import datastructures.tree.BSTUtils.KthSmallestLargestUtil;

public class BstUtilsTest {

    private BinarySearchTree bst;
    private BinarySearchTree leftBst;
    private BinarySearchTree rightBst;
    private BinarySearchTree nullBst;

    /**
     * 9
     * /    \
     * 5       12
     * /  \    /   \
     * 3    6  11    14
     * /
     * 13
     *
     * @return
     */
    private static BinarySearchTree createbst() {
        BinarySearchTree bst = new BinarySearchTree();

        bst.insert(9);
        bst.insert(5);
        bst.insert(3);
        bst.insert(6);
        bst.insert(12);
        bst.insert(11);
        bst.insert(14);
        bst.insert(13);
        return bst;
    }

    private static BinarySearchTree createLeftOnlyBst() {
        BinarySearchTree bst = new BinarySearchTree();
        // bst.insert(9);
        bst.insert(5);
        bst.insert(4);
        bst.insert(3);
        return bst;
    }

    private static BinarySearchTree createRightOnlyBst() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(5);
        bst.insert(7);
        bst.insert(9);
        return bst;
    }

    private static BinarySearchTree createNullBst() {
        BinarySearchTree bst = new BinarySearchTree();
        return bst;
    }

    @Before
    public void init() {
        bst = createbst();
        leftBst = createLeftOnlyBst();
        rightBst = createRightOnlyBst();
        nullBst = createNullBst();
    }

    @Test
    public void test_KthSmallestLargest() {
        KthSmallestLargestUtil util = new KthSmallestLargestUtil(bst.root, 3);
        assertNotNull(util.getSmallest());
        assertEquals(6, util.getSmallest().data);
        assertNotNull(util.getLargest());
        assertEquals(12, util.getLargest().data);

        util = new KthSmallestLargestUtil(bst.root, 20);
        assertNull(util.getSmallest());
        assertNull(util.getLargest());

        util = new KthSmallestLargestUtil(leftBst.root, 3);
        assertNotNull(util.getSmallest());
        assertEquals(5, util.getSmallest().data);
        assertNotNull(util.getLargest());
        assertEquals(3, util.getLargest().data);
    }

    @Test
    public void test_getCommonNodes() {
        List<Integer> commonNodes = getCommonNodes(bst.root, leftBst.root);
        System.out.println(commonNodes);
        assertTrue(!commonNodes.isEmpty());
        commonNodes = getCommonNodes(bst.root, rightBst.root);
        System.out.println(commonNodes);
        assertTrue(!commonNodes.isEmpty());
    }

    @Test
    public void test_convertToSortedDLL() {
        Node head = convertToSortedDLL(bst.root);
        assertNotNull(head);
        for (Node curr = head; curr.right != null; curr = curr.right) {
            System.out.println(curr.data);
        }
    }

    @Test
    public void test_convertToSortedCLL() {
        Node head = convertToSortedCLL(bst.root);
        assertNotNull(head);
        Node curr = head;
        do {
            System.out.println(curr.data);
            curr = curr.right;
        } while (curr != head);
    }

    /**
     * 9
     * /    \
     * 5       12
     * /  \    /   \
     * 3    6  11    14
     * /
     * 13
     *
     * @return
     */
    @Test
    public void test_ceil() {
        assertEquals(Integer.valueOf(11), ceil(bst.root, 9));
        assertEquals(Integer.valueOf(14), ceil(bst.root, 13));
        assertEquals(Integer.valueOf(12), ceil(bst.root, 11));
        assertEquals(Integer.valueOf(9), ceil(bst.root, 6));
        assertEquals(Integer.valueOf(5), ceil(bst.root, 4));
        assertNull(ceil(bst.root, 99));
    }

    @Test
    public void test_floor() {
        assertEquals(Integer.valueOf(11), floor(bst.root, 12));
        assertEquals(Integer.valueOf(9), floor(bst.root, 11));
        assertEquals(Integer.valueOf(5), floor(bst.root, 6));
        assertEquals(Integer.valueOf(6), floor(bst.root, 9));
        assertEquals(Integer.valueOf(12), floor(bst.root, 13));
        assertNull(floor(bst.root, 1));
    }

    @Test
    public void test_checkIfPreorderIsBST() {
        assertTrue(checkIfPreorderIsBST(new int[]{4, 2, 1, 3, 7, 6, 9}));
        assertFalse(checkIfPreorderIsBST(new int[]{4, 2, 1, 3, 2, 6, 9}));
        assertFalse(checkIfPreorderIsBST(new int[]{4, 2, 1, 3, 7, 8, 9}));
    }

    /**
     * 9
     * /    \
     * 5       12
     * /  \    /   \
     * 3    6  11    14
     * /
     * 13
     *
     * @return
     */
    @Test
    public void test_KthSmallestLargestUtil() {
        KthSmallestLargestUtil util = new KthSmallestLargestUtil(bst.root, 1);
        assertEquals(3, util.getSmallest().data);
        assertEquals(14, util.getLargest().data);

        util = new KthSmallestLargestUtil(bst.root, 100);
        assertNull(util.getLargest());
        assertNull(util.getSmallest());

        util = new KthSmallestLargestUtil(bst.root, 8);
        assertEquals(14, util.getSmallest().data);
        assertEquals(3, util.getLargest().data);
    }

    @Test
    public void test_InorderPredecssorSuccessor() {
        assertEquals(3, inorderPredecessor(bst.root, 5).data);
        assertEquals(14, inorderPredecessor(bst.root, 15).data);
        assertEquals(6, inorderPredecessor(bst.root, 9).data);
        assertNull(inorderPredecessor(bst.root, 3));

        assertEquals(3, inorderSuccessor(bst.root, 1).data);
        assertEquals(11, inorderSuccessor(bst.root, 9).data);
        assertNull(inorderSuccessor(bst.root, 15));
    }

    @Test
    public void test_IsBST() {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.right.left = new Node(6);
        root.right.right = new Node(20);

        assertFalse(isBST(root));
        root.right.left.data = 12;
        assertTrue(isBST(root));
    }

    @Test
    public void test_IsBST1() {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.right.left = new Node(6);
        root.right.right = new Node(20);

        assertFalse(isBST1(root));
        root.right.left.data = 12;
        assertTrue(isBST1(root));
    }

    @Test
    public void test_createBstUsingPreorderSequence() {
        Node root = createBstUsingPreorderSequence(new Integer[]{3, 1, 2, 5, 4, 6});
        assertNotNull(root);
        TreeUtils.Traversals.preOrderTraversal(root);
    }
}
