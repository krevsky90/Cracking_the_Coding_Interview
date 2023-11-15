package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6398a0a974c44bdc88129966
 * OR
 * 112. Path Sum
 * https://leetcode.com/problems/path-sum/
 *
 * Problem Statement
 * Given a binary tree and a number ?S?,
 * find if the tree has a path from root-to-leaf such that the sum of all the node values of that path equals ?S?.
 */
public class BinaryTreePathSum {
    /**
     * KREVSKY
     * time to solve ~ 4 mins
     * time ~ O(n)
     * space ~ O(logN) - on average, O(n) - worst case
     * 2 attempts (forget validation that root is leaf in the second 'if')
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false; //this path is not succeeded

        //if we found path with target sum and current (root) node is leaf => return true
        if (root.val == targetSum && root.left == null && root.right == null) {
            return true;
        }

        return hasPathSum(root.left, targetSum - root.val)
                || hasPathSum(root.right, targetSum - root.val);
    }
}
