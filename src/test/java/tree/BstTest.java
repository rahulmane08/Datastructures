package tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static tree.BSTUtils.getCommonNodes;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import tree.BSTUtils.KthSmallestLargestUtil;

public class BstTest {

    private BinarySearchTree bst;
    private BinarySearchTree leftBst;
    private BinarySearchTree rightBst;
    private BinarySearchTree nullBst;

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

    /**
     *                9
     *              /    \
     *             5      12
     *           /  \    /   \
     *          3    6  11    14
     *                       /
     *                      13
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
}
