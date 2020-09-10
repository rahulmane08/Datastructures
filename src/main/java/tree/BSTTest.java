package tree;

import static tree.BSTUtils.CorrectBST;
import static tree.BSTUtils.TreeNodesWithSumOfGreaterNodes;
import static tree.BSTUtils.ceil;
import static tree.BSTUtils.checkIfPreorderIsBST;
import static tree.BSTUtils.createBalancedBST;
import static tree.BSTUtils.floor;
import static tree.BSTUtils.hasOneChildrenForEachNode;
import static tree.BSTUtils.isBST;
import static tree.BSTUtils.lca;
import static tree.BSTUtils.max;
import static tree.BSTUtils.maxRecursive;
import static tree.BSTUtils.printMergeBSTs;
import static tree.BSTUtils.printRange;

import java.util.Arrays;

import pq.HeapUtils;
import tree.TreeUtils.DiameterOptimised;
import tree.TreeUtils.MorrisTraversals;
import tree.TreeUtils.OrderedArrays;
import tree.TreeUtils.Traversals;

public class BSTTest {
    public static void main(String[] args) {
        BinarySearchTree bst = createbst();
        BinarySearchTree leftBst = createLeftOnlyBst();
        BinarySearchTree rightBst = createRightOnlyBst();
        BinarySearchTree nullBst = createNullBst();
        BinaryTree bt = createBT();

        System.out.println("=====MAX======");
        System.out.println("BST Max = " + max(bst.root));
        System.out.println("Left BST Max = " + max(leftBst.root));
        System.out.println("Right BST Max = " + max(rightBst.root));

        System.out.println("=====MAX RECURSIVE======");
        System.out.println("BST Max = " + maxRecursive(bst.root));
        System.out.println("Left BST Max = " + max(leftBst.root));
        System.out.println("Right BST Max = " + max(rightBst.root));

        System.out.println("=====SEARCH======");
        System.out.println("BST search (10) = " + bst.search(10));
        System.out.println("BST search (99) = " + bst.search(99));
        System.out.println("Left BST search (3) = " + leftBst.search(3));
        System.out.println("Left BST search (99) = " + leftBst.search(99));
        System.out.println("Right BST search (7) = " + rightBst.search(7));
        System.out.println("Right BST search (99) = " + rightBst.search(99));

        System.out.println("=====LCA======");
        System.out.println("BST lca(3,13) = " + lca(bst.root, 3, 13));
        System.out.println("BST lca(5,3) = " + lca(bst.root, 5, 3));
        System.out.println("BST lca(3,5) = " + lca(bst.root, 3, 5));

        System.out.println("=====Inorder Predecessors/ Successors======");
        BSTUtils.InorderPredecssorSuccessor predecssorSuccessor = new BSTUtils.InorderPredecssorSuccessor(bst.root, 10);
        System.out.printf("%d's inorder predecessor: %s, successor: %s%n ",
                10, predecssorSuccessor.inorderPredecessor(), predecssorSuccessor.inorderSuccessor());
        predecssorSuccessor = new BSTUtils.InorderPredecssorSuccessor(bst.root, 2);
        System.out.printf("%d's inorder predecessor: %s, successor: %s%n ",
                2, predecssorSuccessor.inorderPredecessor(), predecssorSuccessor.inorderSuccessor());
        predecssorSuccessor = new BSTUtils.InorderPredecssorSuccessor(bst.root, 11);
        System.out.printf("%d's inorder predecessor: %s, successor: %s%n ",
                11, predecssorSuccessor.inorderPredecessor(), predecssorSuccessor.inorderSuccessor());

        System.out.println("=======IS BST=========");
        System.out.println("Is BST: " + isBST(bst.root));
        System.out.println("Is BT BST: " + isBST(bt.root));

        System.out.println("=======Print Range (10, 15)=========");
        printRange(bst.root, 10, 15, true);
        System.out.println("=======Print Range (15, 10)=========");
        printRange(bst.root, 15, 10, true);
        System.out.println("=======Print Range (11, 14) exclusive=========");
        printRange(bst.root, 11, 14, false);
        System.out.println("=======Print Range (11, 14) inclusive=========");
        printRange(bst.root, 11, 14, true);

        System.out.println("=======FLOOR=========");
        System.out.println("Floor (10): " + floor(bst.root, 10));
        System.out.println("Floor (3): " + floor(bst.root, 3));
        System.out.println("Floor (5): " + floor(bst.root, 5));
        System.out.println("Floor (4): " + floor(bst.root, 4));
        System.out.println("Floor (10): " + floor(bst.root, 10));
        System.out.println("Floor (3): " + floor(bst.root, 3));
        System.out.println("Floor (1): " + floor(bst.root, 1));
        System.out.println("Floor (12): " + floor(bst.root, 12));
        System.out.println("Floor (11): " + floor(bst.root, 11));
        System.out.println("Floor (13): " + floor(bst.root, 13));
        System.out.println("Floor (99): " + floor(bst.root, 99));
        System.out.println("Floor (10): " + floor(bst.root, 10));

        System.out.println("=======FLOOR RIGHT BST=========");
        System.out.println("Floor (6): " + floor(rightBst.root, 6));
        System.out.println("Floor (8): " + floor(rightBst.root, 8));

        System.out.println("=======CEIL=========");
        System.out.println("Ceil (10): " + ceil(bst.root, 10));
        System.out.println("Ceil (3): " + ceil(bst.root, 3));
        System.out.println("Ceil (5): " + ceil(bst.root, 5));
        System.out.println("Ceil (4): " + ceil(bst.root, 4));
        System.out.println("Ceil (10): " + ceil(bst.root, 10));
        System.out.println("Ceil (3): " + ceil(bst.root, 3));
        System.out.println("Ceil (1): " + ceil(bst.root, 1));
        System.out.println("Ceil (12): " + ceil(bst.root, 12));
        System.out.println("Ceil (11): " + ceil(bst.root, 11));
        System.out.println("Ceil (13): " + ceil(bst.root, 13));
        System.out.println("Ceil (99): " + ceil(bst.root, 99));
        System.out.println("Ceil (10): " + ceil(bst.root, 10));

        System.out.println("=======Diameter=========");
        System.out.println("diamater : " + TreeUtils.diameter(bst.root));
        DiameterOptimised diameterOptimised = new DiameterOptimised(bst.root);
        // diameter.computeDiameter(bst.root);
        System.out.println("diamater optimised: " + diameterOptimised.getDiameter());

        BinarySearchTree bst1 = new BinarySearchTree();
        bst1.insert(20);
        bst1.insert(9);
        bst1.insert(8);
        bst1.insert(7);
        bst1.insert(6);
        bst1.insert(10);
        bst1.insert(11);
        bst1.insert(12);
        bst1.insert(21);

        diameterOptimised = new DiameterOptimised(bst1.root);
        System.out.println("Ravi diamater : " + diameterOptimised.getDiameter());

        System.out.println("=======Check Preordered array is BST=========");
        int[] arr = {5, 3, 2, 4, 6};
        System.out.printf("arr: %s, is a BST:%s%n", Arrays.toString(arr), checkIfPreorderIsBST(arr));

        System.out.println("=======Balanced BST from array=========");
        Node balancedBstRoot = createBalancedBST(new int[]{4, 3, 1, 2, 5});
        System.out.println(Arrays.toString(OrderedArrays.getInstance().toInorderArray(balancedBstRoot)));

        System.out.println("=======Only one children for each node=========");
        System.out.println("BST = " + hasOneChildrenForEachNode(bst.root));
        System.out.println("Left BST = " + hasOneChildrenForEachNode(leftBst.root));
        System.out.println("Right BST = " + hasOneChildrenForEachNode(rightBst.root));

        System.out.println("=======Print merged bst: left and right bst=========");
        printMergeBSTs(leftBst.root, rightBst.root);
        System.out.println("=======Print merged bst: right and left bst=========");
        printMergeBSTs(rightBst.root, leftBst.root);
        System.out.println("=======Print merged bst: left and null bst=========");
        printMergeBSTs(leftBst.root, null);
        System.out.println("=======Print merged bst: null and right bst=========");
        printMergeBSTs(null, rightBst.root);
        System.out.println("=======Print merged bst: two same bst's=========");
        printMergeBSTs(bst.root, bst.root);
        System.out.println("=======Print merged bst: bst and leftBst=========");
        printMergeBSTs(bst.root, leftBst.root);

        System.out.println("=======Convert to Tree with sum of higher nodes=========");
        BinarySearchTree bst2 = new BinarySearchTree(new int[]{1, 2, 3, 4, 5});
        new TreeNodesWithSumOfGreaterNodes().convertToTreeWithMaxNodeSum(bst2.root);
        TreeUtils.Traversals.inOrderIterative(bst2.root);

        System.out.println("=======Correct BST with two swapped nodes=========");
        bst2 = new BinarySearchTree();
        bst2.root = new Node(10);
        bst2.root.right = new Node(8);
        bst2.root.left = new Node(5);
        bst2.root.left.left = new Node(2);
        bst2.root.left.right = new Node(20);
        System.out.println("Before correction");
        Traversals.inOrderIterative(bst2.root);
        new CorrectBST().correctBst(bst2.root);
        System.out.println("After correction");
        Traversals.inOrderIterative(bst2.root);
        bst2.root.left.right = new Node(11);
        System.out.println("Before correction");
        Traversals.inOrderIterative(bst2.root);
        new CorrectBST().correctBst(bst2.root);
        System.out.println("After correction");
        Traversals.inOrderIterative(bst2.root);

        System.out.println("=======Morris traversals=========");
        BinarySearchTree bst3 = new BinarySearchTree();
        bst3.insert(5);
        bst3.insert(2);
        bst3.insert(1);
        bst3.insert(3);
        bst3.insert(6);
        MorrisTraversals.inOrderTraversal(bst3.root);

        System.out.println("=======Find Largest BST=========");
        System.out.println("====FOR BST");
        BSTUtils.LargestBstInBT largestBstInBT = new BSTUtils.LargestBstInBT();
        largestBstInBT.findLargestBst(bst.root);
        System.out.println("Height of largest BST: " + largestBstInBT.getHeightOfBst());
        System.out.println("Root of largest BST: " + largestBstInBT.getLargestBstRoot());

        BinaryTree bt2 = new BinaryTree();
        bt2.root = new Node(-99);
        bt2.root.left = leftBst.root;
        bt2.root.right = bst.root;
        System.out.println("====For BT with left smaller BST and right larger BST");
        largestBstInBT = new BSTUtils.LargestBstInBT();
        largestBstInBT.findLargestBst(bt2.root);
        System.out.println("Height of largest BST: " + largestBstInBT.getHeightOfBst());
        System.out.println("Root of largest BST: " + largestBstInBT.getLargestBstRoot());


        bt2.root = new Node(-99);
        bt2.root.right = leftBst.root;
        bt2.root.left = bst.root;
        System.out.println("====For BT with left larger BST and right smaller BST");
        largestBstInBT = new BSTUtils.LargestBstInBT();
        largestBstInBT.findLargestBst(bt2.root);
        System.out.println("Height of largest BST: " + largestBstInBT.getHeightOfBst());
        System.out.println("Root of largest BST: " + largestBstInBT.getLargestBstRoot());

        System.out.println("====For Left BST");
        largestBstInBT = new BSTUtils.LargestBstInBT();
        largestBstInBT.findLargestBst(leftBst.root);
        System.out.println("Height of largest BST: " + largestBstInBT.getHeightOfBst());
        System.out.println("Root of largest BST: " + largestBstInBT.getLargestBstRoot());


        System.out.println("====For BT with no BST");
        largestBstInBT = new BSTUtils.LargestBstInBT();
        largestBstInBT.findLargestBst(createBT().root);
        System.out.println("Height of largest BST: " + largestBstInBT.getHeightOfBst());
        System.out.println("Root of largest BST: " + largestBstInBT.getLargestBstRoot());

        System.out.println("=====Convert BST to Min Heap========");
        BinarySearchTree bst4 = createbst();
        HeapUtils.convertBstToMinHeap(bst4.root);
        int[] minheap = OrderedArrays.getInstance().toPreorderArray(bst4.root);
        System.out.println("Is tree converted to min heap: " +
                HeapUtils.checkIfArrayIsMinHeap(minheap, minheap.length, 0));
        System.out.println("Tree to min heap: " + Arrays.toString(minheap));

        System.out.println("=====Convert BST to Min Heap========");
        bst4 = createbst();
        HeapUtils.convertBstToMaxHeap(bst4.root);
        int[] maxHeap = OrderedArrays.getInstance().toPreorderArray(bst4.root);
        System.out.println("Is tree converted to max heap: " + HeapUtils.checkIfArrayIsMaxHeap(maxHeap));
        System.out.println("Tree to max heap: " + Arrays.toString(maxHeap));

    }

    private static BinaryTree createBT() {
        BinaryTree bt = new BinaryTree();
        bt.insert(1);
        bt.insert(99);
        bt.insert(2);
        bt.insert(8);
        bt.insert(9);
        bt.insert(50);
        bt.insert(51);
        return bt;
    }

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
