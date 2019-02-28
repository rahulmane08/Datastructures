package tree;

import static tree.BSTUtils.lca;
import static tree.BSTUtils.max;
import static tree.BSTUtils.maxRecursive;

public class BSTTest {
    public static void main(String[] args) {
        BinarySearchTree bst = createbst();
        BinarySearchTree leftBst = createLeftOnlyBst();
        BinarySearchTree rightBst = createRightOnlyBst();
        BinarySearchTree nullBst = createNullBst();

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
    }

    private static BinarySearchTree createbst() {
        BinarySearchTree bst = new BinarySearchTree();

        bst.insert(10);
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
        bst.insert(6);
        bst.insert(7);
        return bst;
    }

    private static BinarySearchTree createNullBst() {
        BinarySearchTree bst = new BinarySearchTree();
        return bst;
    }
}
