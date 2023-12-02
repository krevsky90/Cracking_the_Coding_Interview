package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6399e360a0e842698079404f
 * OR
 * Cracking_the_Coding_Interview\krev\src\data_structures\chapter4_trees_n_graphs\Problem4_12.java
 * OR
 * 437. Path Sum III
 * https://leetcode.com/problems/path-sum-iii/submissions/
 *
 * Given a binary tree and a number ?S?,
 * find all paths in the tree such that the sum of all the node values of each path equals ?S?.
 * Please note that the paths can start or end at any node but all paths must follow direction from parent to child (top to bottom).
 */
public class CountPathsForSum {
    /**
     * KREVSKY SOLUTION #1:
     * copied from my solution for leetcode
     * time to solve ~ 4-15 mins
     *
     * time ~ O(n*logn) - o(n*n)
     * space ~ O(n)
     *
     * 4+ attempts:
     * 1) targetSum should be long, but not int!
     * 2) check root == null in pathSum method, since we set root.left and right further
     * 3) counter(..) method should add the results of counter from left and right side to count = root.val == targetSum ? 1 : 0, rather than just stop method and quit!
     */
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        int result = 0;
        result += pathSum(root.left, targetSum);
        result += pathSum(root.right, targetSum);
        result += counter(root, targetSum);

        return result;
    }


    private int counter(TreeNode root, long targetSum) {
        if (root == null) return 0;

        int count = 0;
        if (root.val == targetSum)  {
            count = 1;
        }

        count += counter(root.right, targetSum - root.val);
        count += counter(root.left, targetSum - root.val);

        return count;
    }
}