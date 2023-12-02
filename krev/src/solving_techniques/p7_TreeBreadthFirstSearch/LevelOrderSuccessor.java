package solving_techniques.p7_TreeBreadthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639894f2e4cab4072de783e2
 * OR
 * https://www.geeksforgeeks.org/level-order-successor-of-a-node-in-binary-tree/
 * <p>
 * Given a binary tree and a node, find the level order successor of the given node in the tree.
 * The level order successor is the node that appears right after the given node in the level order traversal.
 */

public class LevelOrderSuccessor {
    public static void main(String[] args) {
        TreeNode n20 = new TreeNode(20);
        TreeNode n10 = new TreeNode(10);
        TreeNode n26 = new TreeNode(26);
        TreeNode n4 = new TreeNode(4);
        TreeNode n18 = new TreeNode(18);
        TreeNode n24 = new TreeNode(24);
        TreeNode n27 = new TreeNode(27);
        TreeNode n14 = new TreeNode(14);
        TreeNode n19 = new TreeNode(19);
        TreeNode n13 = new TreeNode(13);
        TreeNode n15 = new TreeNode(15);

        n20.left = n10;
        n20.right = n26;
        n10.left = n4;
        n10.right = n18;
        n26.left = n24;
        n26.right = n27;
        n18.left = n14;
        n18.right = n19;
        n14.left = n13;
        n14.right = n15;

        TreeNode key1 = n24;
        TreeNode res1 = levelOrderSuccessor(n20, key1);
        System.out.println(res1 != null ? res1.val : null);

        TreeNode key2 = n4;
        TreeNode res2 = levelOrderSuccessor(n20, key2);
        System.out.println(res2 != null ? res2.val : null);
    }

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 20 mins
     * time ~ O(n)
     * space ~ O(n) - since for perfect tree amount of nodes will be 1,2,4,8,..2^h, where h - height of the tree. total sum = n, 2^h ~ n/2
     *
     * NOTE: geeksforgeeks considers special case where tree = root and root = key, but the common algorithm also covers this case
     */
    public static TreeNode levelOrderSuccessor(TreeNode root, TreeNode key) {
        if (root == null) return null;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode tempNode = q.poll();

            if (tempNode == key) {
                return q.poll();
            }

            if (tempNode.left != null) q.add(tempNode.left);
            if (tempNode.right != null) q.add(tempNode.right);
        }

        return null;
    }
}
