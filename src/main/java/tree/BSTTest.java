package tree;

import static tree.BSTUtils.ceil;
import static tree.BSTUtils.floor;
import static tree.BSTUtils.isBST;
import static tree.BSTUtils.lca;
import static tree.BSTUtils.max;
import static tree.BSTUtils.maxRecursive;
import static tree.BSTUtils.printRange;

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
        printRange(bst.root, 10, 15);
        System.out.println("=======Print Range (15, 10)=========");
        printRange(bst.root, 15, 10);

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
