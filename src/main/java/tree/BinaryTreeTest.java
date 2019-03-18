package tree;

import static tree.TreeUtils.Traversals.levelOrderTraversal;
import static tree.TreeUtils.Traversals.verticalTopDownTraversal;
import static tree.TreeUtils.areTreesIdentical;
import static tree.TreeUtils.checkIfSubtree;
import static tree.TreeUtils.checkIfSumTree;
import static tree.TreeUtils.countLeafNodes;
import static tree.TreeUtils.countLeafNodesRecursively;
import static tree.TreeUtils.diameter;
import static tree.TreeUtils.exists;
import static tree.TreeUtils.findLevelWithMaxSum;
import static tree.TreeUtils.findMax;
import static tree.TreeUtils.heightInterative;
import static tree.TreeUtils.heightRecursive;
import static tree.TreeUtils.isStrictTree;
import static tree.TreeUtils.lca;
import static tree.TreeUtils.lengthOfLongestPathOfNode;
import static tree.TreeUtils.printAllAncestors;
import static tree.TreeUtils.printAllLeaves;
import static tree.TreeUtils.printAllPathsMatchingSum;
import static tree.TreeUtils.printAllRootToLeafPaths;
import static tree.TreeUtils.printFirstPathMatchingSum;
import static tree.TreeUtils.printFirstRootToLeafPath;
import static tree.TreeUtils.printFirstRootToLeafPath1;
import static tree.TreeUtils.printKthLevelNodes;
import static tree.TreeUtils.printVerticalSum;
import static tree.TreeUtils.topView;

import java.util.Stack;

public class BinaryTreeTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        BinaryTree bt = new BinaryTree();

        bt.insert(50);
        bt.insert(8);
        bt.insert(2);
        bt.insert(3);
        bt.insert(5);
        bt.insert(9);
        bt.insert(90);
        bt.insert(100);
        levelOrderTraversal(bt.root);
        System.out.println("Max of BT = " + findMax(bt.root));
        System.out.println("90 exists: " + exists(90, bt.root));
        System.out.println("30 exists: " + exists(30, bt.root));
        System.out.println("100 exists: " + exists(100, bt.root));

        System.out.println("height iteratively = " + heightInterative(bt.root));
        System.out.println("height recursively = " + heightRecursive(bt.root));

        System.out.println("leaf nodes iteratively = " + countLeafNodes(bt.root));
        System.out.println("leaf nodes recursively = " + countLeafNodesRecursively(bt.root));

        System.out.println("length of longest path for root: " + lengthOfLongestPathOfNode(bt.root));
        System.out.println("Diameter of tree = " + diameter(bt.root));
        System.out.println("Ravi Diameter of tree = " + diameter(bt.root));

        System.out.println("Level with max sum = " + findLevelWithMaxSum(bt.root));
        System.out.println("====printAllRootToLeafPaths====");
        printAllRootToLeafPaths(bt.root, new Stack<>());
        System.out.println("====printFirstRootToLeafPath====");
        printFirstRootToLeafPath(bt.root, new Stack<>());
        System.out.println("====printFirstRootToLeafPath1====");
        Stack<Integer> s = new Stack<>();
        printFirstRootToLeafPath1(bt.root, s);
        while (!s.isEmpty()) {
            System.out.print(s.pop() + " ");
        }
        System.out.println();
        System.out.println("====printAllPathsMatchingSum====");
        printAllPathsMatchingSum(bt.root, new Stack<>(), 61);
        System.out.println("====printFirstPathMatchingSum====");
        printFirstPathMatchingSum(bt.root, new Stack<>(), 61);

        System.out.println("lca (50,8): " + lca(bt.root, 50, 8));
        System.out.println("lca (50,101): " + lca(bt.root, 50, 101));
        System.out.println("lca (8,3): " + lca(bt.root, 8, 3));
        System.out.println("lca (3,8): " + lca(bt.root, 3, 8));
        System.out.println("lca (3,90): " + lca(bt.root, 3, 90));
        System.out.println("lca (3,101): " + lca(bt.root, 3, 101));
        System.out.println("lca (3,2): " + lca(bt.root, 3, 2));
        System.out.println("lca (3,9): " + lca(bt.root, 3, 9));

        System.out.println("====printAllAncestors====");
        printAllAncestors(bt.root);
        System.out.println("====printAllLeaves====");
        printAllLeaves(bt.root);
        System.out.println("====printVerticalSum====");
        printVerticalSum(bt.root);

        System.out.println("are trees identical = " + areTreesIdentical(bt.root, bt.root));
        System.out.println("========printKthLevelNodes=====");
        printKthLevelNodes(bt.root, 2);
        System.out.println("\nis strict tree = " + isStrictTree(bt.root));

        topView(bt.root);
        BinaryTree bt1 = (BinaryTree) bt.clone();
        Node x = new Node(1);
        x.left = bt1.root;
        bt1.root = x;
        topView(bt1.root);

        // sum tree
        BinaryTree sumTree = new BinaryTree();
        sumTree.insert(26);
        sumTree.insert(10);
        sumTree.insert(3);
        sumTree.insert(4);
        sumTree.insert(6);
        sumTree.insert(3);
        System.out.println("Is sum tree = " + checkIfSumTree(sumTree.root));
        System.out.println("Is sum tree = " + checkIfSumTree(bt.root));

        System.out.println("========check one tree subtree of other=====");
        BinaryTree bt2 = new BinaryTree();
        bt2.insert(8);
        bt2.insert(3);
        bt2.insert(4);
        bt2.insert(2);
        bt2.insert(5);
        BinaryTree bt3 = new BinaryTree();
        bt3.insert(3);
        bt3.insert(2);
        bt3.insert(5);
        System.out.println("is subtree: " + checkIfSubtree(bt2.root, bt3.root));

        BinaryTree leftTree = new BinaryTree();
        leftTree.root = new Node(9);
        leftTree.root.left = new Node(8);
        leftTree.root.left.left = new Node(7);
        System.out.println(lca(leftTree.root, 8, 7));

        verticalTopDownTraversal(bt.root);
    }
}
