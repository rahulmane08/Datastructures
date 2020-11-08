package tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static tree.TreeUtils.PathQueryUtils.distanceBetweenAnyTwoNodes;
import static tree.TreeUtils.PrintUtils.printIn2DRotateLeftBy90;
import static tree.TreeUtils.PrintUtils.printIn2DRotateLeftBy90Transpose;
import static tree.TreeUtils.Traversals.diagonalTraversal;
import static tree.TreeUtils.Traversals.diagonalTraversalIterative;
import static tree.TreeUtils.Traversals.levelOrderTraversal;
import static tree.TreeUtils.Traversals.preOrderTraversal;
import static tree.TreeUtils.TreeSerializeDeserializeUtil.deserialize;
import static tree.TreeUtils.TreeSerializeDeserializeUtil.serialize;
import static tree.TreeUtils.addTrees;
import static tree.TreeUtils.CheckerUtils.areTreesIdentical;
import static tree.TreeUtils.CheckerUtils.areTreesMirrors;
import static tree.TreeUtils.ViewUtils.bottomView;
import static tree.TreeUtils.CheckerUtils.checkIfSubTree;
import static tree.TreeUtils.CheckerUtils.checkIfSumTree;
import static tree.TreeUtils.convertToSumTree1;
import static tree.TreeUtils.countLeafNodes;
import static tree.TreeUtils.countLeafNodesRecursively;
import static tree.TreeUtils.exists;
import static tree.TreeUtils.PathQueryUtils.findLevelWithMaxSum;
import static tree.TreeUtils.findMax;
import static tree.TreeUtils.heightInterative;
import static tree.TreeUtils.heightRecursive;
import static tree.TreeUtils.image;
import static tree.TreeUtils.CheckerUtils.checkIfStrictTree;
import static tree.TreeUtils.lca;
import static tree.TreeUtils.ViewUtils.leftView;
import static tree.TreeUtils.PathQueryUtils.printAllAncestors;
import static tree.TreeUtils.PathQueryUtils.printAllLeaves;
import static tree.TreeUtils.PathQueryUtils.printAllPathsMatchingSum;
import static tree.TreeUtils.PathQueryUtils.printAllRootToLeafPaths;
import static tree.TreeUtils.PathQueryUtils.printAncestorsOfGivenNode;
import static tree.TreeUtils.PathQueryUtils.printFirstPathMatchingSum;
import static tree.TreeUtils.PathQueryUtils.printFirstRootToLeafPath;
import static tree.TreeUtils.PathQueryUtils.printKthLevelNodes;
import static tree.TreeUtils.printVerticalSum;
import static tree.TreeUtils.ViewUtils.rightView;
import static tree.TreeUtils.size;
import static tree.TreeUtils.sizeIterative;
import static tree.TreeUtils.ViewUtils.topView;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;
import tree.TreeUtils.DeepestHorrizontalLevelSumUtil;
import tree.TreeUtils.CheckerUtils.InternalNodeWithOneChildChecker;
import tree.TreeUtils.OrderedArrays;
import tree.TreeUtils.PathQueryUtils.MaxSumOfAny2LeafPathUtil;
import tree.TreeUtils.PathQueryUtils.MaxSumOfAnyPathUtil;
import tree.TreeUtils.PathQueryUtils.MaxSumOfNonAdjacentNodesUtil;
import tree.TreeUtils.PathQueryUtils.MaxSumOfRootToLeafPathsUtil;
import tree.TreeUtils.CheckerUtils.SumTreeChecker;
import tree.TreeUtils.PathQueryUtils.SumOfAllLeftLeafNodes;
import tree.TreeUtils.TreeSerializeDeserializeUtil;

public class TreeUtilsTest {

    BinaryTree bt;

    /**
     * ```````````50
     * ``````` ``/   \
     * ```````8      2
     * ``````/ \    /  \
     * ````3   5  9    90
     * ``/
     * 100
     */
    @Before
    public void init() {
        bt = new BinaryTree();
        bt.insert(50);
        bt.insert(8);
        bt.insert(2);
        bt.insert(3);
        bt.insert(5);
        bt.insert(9);
        bt.insert(90);
        bt.insert(100);
    }

    @Test
    public void testMax() {
        assertEquals(100, findMax(bt.root));
    }

    @Test
    public void testLOT() {
        levelOrderTraversal(bt.root);
    }

    @Test
    public void testExists() {
        assertTrue(exists(90, bt.root));
        assertTrue(exists(3, bt.root));
        assertFalse(exists(101, bt.root));
    }

    @Test
    public void testHeight() {
        assertEquals(4, heightRecursive(bt.root));
        assertEquals(4, heightInterative(bt.root));
    }

    @Test
    public void testSize() {
        assertEquals(8, size(bt.root));
        assertEquals(8, sizeIterative(bt.root));
    }

    @Test
    public void testCountLeaves() {
        assertEquals(4, countLeafNodes(bt.root));
        assertEquals(4, countLeafNodesRecursively(bt.root));
    }

    @Test
    public void testAreMirror() {
        BinaryTree testTree = new BinaryTree();
        testTree.insert(1);
        testTree.insert(2);
        testTree.insert(3);
        testTree.insert(4);
        BinaryTree mirrorTree = new BinaryTree();
        mirrorTree.insert(1);
        mirrorTree.insert(3);
        mirrorTree.insert(2);
        mirrorTree.root.right.right = new Node(4);
        assertTrue(areTreesMirrors(testTree.root, mirrorTree.root));
    }

    @Test
    public void testAreIdentical() {
        assertTrue(areTreesIdentical(bt.root, bt.root));
    }

    @Test
    public void testImage() {
        BinaryTree testTree = new BinaryTree();
        testTree.insert(1);
        testTree.insert(2);
        testTree.insert(3);
        testTree.insert(4);
        BinaryTree mirrorTree = new BinaryTree();
        mirrorTree.insert(1);
        mirrorTree.insert(2);
        mirrorTree.insert(3);
        mirrorTree.insert(4);
        image(mirrorTree.root);
        assertTrue(areTreesMirrors(testTree.root, mirrorTree.root));
    }

    @Test
    public void testDiameter() {
        BinaryTree testTree = new BinaryTree();
        testTree.insert(1);
        Node right = new Node(3);
        Node left = new Node(2);
        left.left = new Node(4);
        left.right = new Node(5);
        left.right.right = new Node(6);
        left.right.right.right = new Node(7);
        left.right.right.right.right = new Node(8);
        testTree.root.left = left;
        testTree.root.right = right;
        assertEquals(7, new TreeUtils.DiameterOptimised(testTree.root).getDiameter());
    }

    @Test
    public void test_printAllRootToLeafPaths() {
        printAllRootToLeafPaths(bt.root, new Stack<>());
        printAllRootToLeafPaths(bt.root, "");
    }

    @Test
    public void test_printFirstRootToLeafPath() {
        printFirstRootToLeafPath(bt.root, new Stack<>());
    }

    @Test
    public void test_printAllPathsMatchingSum() {
        BinaryTree testTree = new BinaryTree();
        testTree.insert(1);
        testTree.root.left = new Node(4);
        testTree.root.left.left = new Node(3);
        testTree.root.left.right = new Node(5);

        testTree.root.right = new Node(3);
        testTree.root.right.left = new Node(4);
        testTree.root.right.right = new Node(10);

        printAllPathsMatchingSum(testTree.root, new Stack<>(), 8);
        printFirstPathMatchingSum(testTree.root, new Stack<>(), 8);
    }

    @Test
    public void test_lca() {
        assertEquals(new Node(8), lca(bt.root, 3, 5));
        assertEquals(new Node(8), lca(bt.root, 100, 5));
        assertEquals(new Node(8), lca(bt.root, 8, 5));
        assertEquals(new Node(50), lca(bt.root, 100, 90));
        assertEquals(new Node(90), lca(bt.root, 90, 90));
        assertNull(lca(bt.root, 1000, 1001));
        assertEquals(new Node(90), lca(bt.root, 90, 10001));
    }

    @Test
    public void test_printAllLeaves() {
        printAllLeaves(bt.root);
    }

    @Test
    public void test_printAllAncestors() {
        printAllAncestors(bt.root);
    }

    @Test
    public void test_printAncestorsOfGivenNode() {
        System.out.println("ancestors of 8");
        printAncestorsOfGivenNode(bt.root, 8);
        System.out.println("ancestors of 100");
        printAncestorsOfGivenNode(bt.root, 100);
        System.out.println("ancestors of missing 101");
        printAncestorsOfGivenNode(bt.root, 101);
    }

    @Test
    public void test_checkIfAVLTree() {
        assertTrue(new TreeUtils.CheckerUtils.AVLChecker(bt.root).isCheck());

        BinaryTree testTree = new BinaryTree();
        testTree.insert(1);
        testTree.root.left = new Node(4);
        testTree.root.left.left = new Node(3);
        testTree.root.left.left.left = new Node(2);
        testTree.root.right = new Node(5);
        assertFalse(new TreeUtils.CheckerUtils.AVLChecker(testTree.root).isCheck());
    }

    @Test
    public void test_printVerticalSum() {
        printVerticalSum(bt.root);
    }

    @Test
    public void test_isStrictTree() {
        assertFalse(checkIfStrictTree(bt.root));

        BinaryTree testTree = new BinaryTree();
        testTree.insert(1);
        testTree.root.left = new Node(4);
        testTree.root.right = new Node(5);

        assertTrue(checkIfStrictTree(testTree.root));
    }

    @Test
    public void test_printKthLevelNodes() {
        System.out.println("nodes at level 2:");
        printKthLevelNodes(bt.root, 2);
        System.out.println("\nnodes at level 1:");
        printKthLevelNodes(bt.root, 1);
        System.out.println("\nnodes at level 20:");
        printKthLevelNodes(bt.root, 20);
    }

    @Test
    public void test_topView() {
        topView(bt.root);
    }

    @Test
    public void test_bottomView() {
        bottomView(bt.root);
    }

    @Test
    public void test_leftView() {
        leftView(bt.root);
    }

    @Test
    public void test_rightView() {
        rightView(bt.root);
    }

    @Test
    public void test_checkIfEachInternalHasOneChild() {
        assertFalse(new InternalNodeWithOneChildChecker(bt.root).isCheck());
        BinaryTree testTree = new BinaryTree();
        testTree.insert(1);
        testTree.root.left = new Node(4);
        testTree.root.left.left = new Node(3);
        testTree.root.left.left.left = new Node(2);
        testTree.root.right = new Node(5);
        testTree.root.right.right = new Node(10);
        assertTrue(new InternalNodeWithOneChildChecker(testTree.root).isCheck());
    }

    @Test
    public void test_findLevelWithMaxSum() {
        assertEquals(2, findLevelWithMaxSum(bt.root));
    }

    @Test
    public void test_PathWithMaxSumUtils() {
        MaxSumOfRootToLeafPathsUtil pathWithMaxSumUtils = new MaxSumOfRootToLeafPathsUtil(bt.root);
        assertNotEquals(0, pathWithMaxSumUtils.getMaxSum());
        assertTrue(!pathWithMaxSumUtils.getPathWithMaxSum().isEmpty());
        System.out.printf("max sum = %s, max sum path = %s%n",
                pathWithMaxSumUtils.getMaxSum(), pathWithMaxSumUtils.getPathWithMaxSum());
        BinaryTree testTree = new BinaryTree();
        testTree.insert(1);
        testTree.root.left = new Node(4);
        testTree.root.left.left = new Node(3);
        testTree.root.left.right = new Node(5);
        testTree.root.right = new Node(2);
        testTree.root.right.left = new Node(6);
        testTree.root.right.right = new Node(10);

        pathWithMaxSumUtils = new MaxSumOfRootToLeafPathsUtil(testTree.root);
        assertNotEquals(0, pathWithMaxSumUtils.getMaxSum());
        assertTrue(!pathWithMaxSumUtils.getPathWithMaxSum().isEmpty());
        System.out.printf("max sum = %s, max sum path = %s%n",
                pathWithMaxSumUtils.getMaxSum(), pathWithMaxSumUtils.getPathWithMaxSum());
    }

    @Test
    public void testIfSumTree() {
        assertFalse(checkIfSumTree(bt.root));
        assertFalse(new SumTreeChecker(bt.root).isCheck());

        BinaryTree testTree = new BinaryTree();
        testTree.insert(26);
        testTree.insert(10);
        testTree.insert(3);
        testTree.insert(4);
        testTree.insert(6);
        testTree.insert(3);
        assertTrue(checkIfSumTree(testTree.root));
        assertTrue(new SumTreeChecker(testTree.root).isCheck());
    }

    @Test
    public void test_convertSumTree1() {
        BinaryTree testTree = new BinaryTree();
        testTree.insert(10);
        testTree.insert(-2);
        testTree.insert(6);
        testTree.insert(8);
        testTree.insert(-4);
        testTree.insert(7);
        testTree.insert(5);
        convertToSumTree1(testTree.root);
        assertEquals(20, testTree.root.data);
    }

    @Test
    public void test_checkIfSubtree() {
        BinaryTree testTree = new BinaryTree();
        testTree.insert(2);
        assertTrue(checkIfSubTree(bt.root, testTree.root));
        testTree.insert(9);
        assertTrue(checkIfSubTree(bt.root, testTree.root));
        testTree.insert(90);
        assertTrue(checkIfSubTree(bt.root, testTree.root));
        testTree.insert(900);
        assertFalse(checkIfSubTree(bt.root, testTree.root));

        testTree = new BinaryTree();
        testTree.insert(8);
        assertTrue(checkIfSubTree(bt.root, testTree.root));
        testTree.insert(3);
        assertTrue(checkIfSubTree(bt.root, testTree.root));
        testTree.insert(5);
        assertTrue(checkIfSubTree(bt.root, testTree.root));
        testTree.insert(100);
        assertTrue(checkIfSubTree(bt.root, testTree.root));

        testTree = new BinaryTree();
        testTree.insert(50);
        testTree.insert(8);
        testTree.root.left.right = new Node(5);
        assertTrue(checkIfSubTree(bt.root, testTree.root));

        testTree = new BinaryTree();
        testTree.insert(8);
        testTree.insert(3);
        testTree.insert(5);
        testTree.insert(100);
        testTree.insert(101);
        assertFalse(checkIfSubTree(bt.root, testTree.root));
    }

    @Test
    public void test_postOrderIterative() {
        BinaryTree testTree = new BinaryTree();
        testTree.insert(8);
        testTree.insert(5);
        testTree.insert(11);
        testTree.insert(3);
        testTree.insert(7);
        testTree.insert(9);
        testTree.insert(12);
        Node four = new Node(4);
        testTree.root.left.left.right = four;
        four.right = new Node(15);
        four.right.right = new Node(16);
        four.left = new Node(14);
        TreeUtils.Traversals.postOrderIterative(testTree.root);
    }

    @Test
    public void test_deepestNodesSum() {
        DeepestHorrizontalLevelSumUtil util = new DeepestHorrizontalLevelSumUtil(bt.root);
        assertEquals(Arrays.asList(100), util.getDeepestNodes());
        assertEquals(3, util.getDeepestLevel());
        assertEquals(100, util.getDeepestNodesSum());

        BinaryTree testTree = new BinaryTree();
        testTree.insert(1);
        testTree.insert(2);
        testTree.insert(3);
        testTree.insert(4);
        testTree.insert(5);
        testTree.insert(6);
        testTree.insert(7);
        util = new DeepestHorrizontalLevelSumUtil(testTree.root);
        assertEquals(Arrays.asList(4, 5, 6, 7), util.getDeepestNodes());
        assertEquals(2, util.getDeepestLevel());
        assertEquals(22, util.getDeepestNodesSum());
    }

    @Test
    public void test_addTrees() {
        assertNull(addTrees(null, null));
        Node root1 = new Node(1);
        assertEquals(root1, addTrees(root1, null));
        assertEquals(root1, addTrees(null, root1));
        root1.left = new Node(3);
        root1.right = new Node(2);
        root1.left.left = new Node(5);

        Node root2 = new Node(2);
        root2.left = new Node(1);
        root2.right = new Node(3);
        root2.left.right = new Node(4);
        root2.right.right = new Node(7);

        Node mergedNode = addTrees(root1, root2);
        int[] arr = OrderedArrays.getInstance().toInorderArray(mergedNode);
        int[] expected = {5, 4, 4, 3, 5, 7};
        assertTrue(Arrays.equals(expected, arr));
    }

    @Test
    public void test_SumOfRootToLeafPathsUtil() {
        BinaryTree testTree = new BinaryTree();
        testTree.insert(3);
        testTree.insert(4);
        testTree.insert(5);
        testTree.insert(5);
        testTree.insert(4);
        testTree.root.right.right = new Node(7);
        assertEquals(1046, new TreeUtils.PathQueryUtils.SumOfRootToLeafPathsUtil(testTree.root).getSum(), 0);
    }

    @Test
    public void test_SumOfRootToLeafPathsUtil1() {
        BinaryTree testTree = new BinaryTree();
        testTree.insert(3);
        testTree.insert(4);
        testTree.insert(5);
        testTree.insert(5);
        testTree.insert(4);
        testTree.root.right.right = new Node(7);
        assertEquals(1046, new TreeUtils.PathQueryUtils.SumOfRootToLeafPathsUtil1(testTree.root).getSum(), 0);
    }

    @Test
    public void test_MaxSumForAnyPathUtil() {
        assertEquals(253, new MaxSumOfAnyPathUtil(bt.root).getMaxSum());
    }

    @Test
    public void test_MaxSumOfAny2LeafPathUtil() {
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(-15);
        tree.root.left = new Node(5);
        tree.root.right = new Node(6);
        tree.root.left.left = new Node(-8);
        tree.root.left.right = new Node(1);
        tree.root.left.left.left = new Node(2);
        tree.root.left.left.right = new Node(6);
        tree.root.right.left = new Node(3);
        tree.root.right.right = new Node(9);
        tree.root.right.right.right = new Node(0);
        tree.root.right.right.right.left = new Node(4);
        tree.root.right.right.right.right = new Node(-1);
        tree.root.right.right.right.right.left = new Node(10);
        assertEquals(27, new MaxSumOfAny2LeafPathUtil(tree.root).getMaxSum());
    }

    @Test
    public void test_MaxSumOfNonAdjacentNodesUtil() {
        System.out.println(new MaxSumOfNonAdjacentNodesUtil(bt.root).getMaxSum());

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.right.left = new Node(4);
        root.right.right = new Node(5);
        root.left.left = new Node(1);
        System.out.print(new MaxSumOfNonAdjacentNodesUtil(root).getMaxSum());
    }

    /**
     * ```````````50
     * ``````` ``/   \
     * ```````8      2
     * ``````/ \    /  \
     * ````3   5  9    90
     * ``/
     * 100
     */
    @Test
    public void test_distanceBetweenAnyTwoNodes() {
        assertEquals(2, distanceBetweenAnyTwoNodes(bt.root, 8, 2));
        assertEquals(3, distanceBetweenAnyTwoNodes(bt.root, 8, 90));
        assertEquals(5, distanceBetweenAnyTwoNodes(bt.root, 100, 90));
        assertEquals(1, distanceBetweenAnyTwoNodes(bt.root, 2, 90));
        assertEquals(0, distanceBetweenAnyTwoNodes(bt.root, 90, 90));
        assertEquals(-1, distanceBetweenAnyTwoNodes(bt.root, 90, 1000));
    }

    @Test
    public void test_diagonalTraversal() {
        Node root = new Node(8);
        root.left = new Node(3);
        root.right = new Node(10);
        root.left.left = new Node(1);
        root.left.right = new Node(6);
        root.right.right = new Node(14);
        root.right.right.left = new Node(13);
        root.left.right.left = new Node(4);
        root.left.right.right = new Node(7);

        diagonalTraversalIterative(root);
        diagonalTraversal(root);
    }

    @Test
    public void test_printIn2D() {
        printIn2DRotateLeftBy90(bt.root);
        printIn2DRotateLeftBy90Transpose(bt.root);
    }

    @Test
    public void test_printBoundaryNodes() {
        TreeUtils.PrintBoundaryNodesUtil.printBoundaryNodes(bt.root);
    }

    @Test
    public void test_SumOfAllLeftLeafNodes() {
        System.out.println(new SumOfAllLeftLeafNodes(bt.root).getSum());
    }

    @Test
    public void test_serializeDeserialize() {
        List<Integer> list = serialize(bt.root);
        System.out.println(list);
        Node root = deserialize(list);
        preOrderTraversal(root);
    }
}
