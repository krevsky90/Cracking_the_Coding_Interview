package solving_techniques.p7_TreeBreadthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63989c048d812da0aa12bff7
 * OR
 * https://www.educative.io/connect-all-siblings
 * <p>
 * Given a binary tree, connect each node with its level order successor.
 * The last node of each level should point to the first node of the next level.
 */
public class ProblemChallenge1_ConnectAllLevelOrderSiblings {
    public static void main(String[] args) {
        TreeNode n100 = new TreeNode(100);
        TreeNode n50 = new TreeNode(50);
        TreeNode n200 = new TreeNode(200);
        TreeNode n25 = new TreeNode(25);
        TreeNode n75 = new TreeNode(75);
        TreeNode n300 = new TreeNode(300);
        TreeNode n350 = new TreeNode(350);

        n100.left = n50;
        n100.right = n200;
        n50.left = n25;
        n50.right = n75;
        n200.right = n300;
        n300.right = n350;

        populateSiblingPointers(n100);

        TreeNode curNode = n100;
        String result = "";
        while (curNode.next != null) {
            result += curNode.val + " -> ";
            curNode = curNode.next;
        }

        System.out.println(result);
    }


    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 10 mins
     * time to debug 2 mins
     * time ~ O(n)
     * space ~ O(1)
     * 1 attempt
     */
    public static void populateSiblingPointers(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode curNode = q.poll();
            if (curNode.left != null) q.offer(curNode.left);
            if (curNode.right != null) q.offer(curNode.right);
            curNode.next = q.peek();
        }
    }
}