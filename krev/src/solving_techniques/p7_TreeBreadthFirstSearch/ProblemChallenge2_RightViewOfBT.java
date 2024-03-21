package solving_techniques.p7_TreeBreadthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63989ddfa1cb64009de121d1 (low)
 * OR
 * 199. Binary Tree Right Side View
 * https://leetcode.com/problems/binary-tree-right-side-view/ (medium)
 *
 * Given the root of a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 */
public class ProblemChallenge2_RightViewOfBT {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 8 mins
     *
     * time ~ O(n)
     * space ~ O(n/2) ~ O(n), where n - amount of nodes in the Tree. n/2 - will be in case of Perfect BT
     *  (i.e. each node has 2 children except leaves, all branches have the same length)
     * 1 attempt:
     */
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<Integer> result = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            TreeNode current = null;
            for (int i = 0; i < size; i++) {
               current = q.poll();
               if (current.left != null) q.add(current.left);
               if (current.right != null) q.add(current.right);
            }
            result.add(current.val);
        }

        return result;
    }
}