package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639b62040dbaa5118a4b5e96
 * OR
 * 543. Diameter of Binary Tree (easy)
 * https://leetcode.com/problems/diameter-of-binary-tree
 *
 * #Company: Adobe Amazon Apple Atlassian Baidu Bloomberg ByteDance Meta Google Intel Microsoft Oracle Qualtrics VMware
 * <p>
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 * The length of a path between two nodes is represented by the number of edges between them.
 * <p>
 * Example 1:
 * Input: root = [1,2,3,4,5]
 * Output: 3
 * Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 * <p>
 * Example 2:
 * Input: root = [1,2]
 * Output: 1
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10000].
 * -100 <= Node.val <= 100
 */
public class ProblemChallenge1_TreeDiameter {
    /**
     * KREVSKY SOLUTION
     * time to solve ~ 20-25 mins
     * time ~ O(n)
     * space ~ O(n)
     *
     * 1 attempt
     */
    public int diameterOfBinaryTree(TreeNode root) {
        int[] diameter = new int[1];    // Create an array to hold the diameter of the tree (to avoi of usage of field 'result' in this class)
        helper(root, diameter);
//        return result;
        return diameter[0];
    }

    private int helper(TreeNode root, int[] diameter) {
        if (root == null) return 0;

//        if (root.left == null && root.right == null) return 1; - not necessary!

        // Recursively calculate the height of the left and right subtrees
        int leftLength = helper(root.left, diameter);
        int rightLength = helper(root.right, diameter);

//        result = Math.max(result, leftLength + rightLength);
        diameter[0] = Math.max(diameter[0], leftLength + rightLength);
        // Return the maximum depth of the current node by adding 1 to the maximum depth of its deepest subtree
        return 1 + Math.max(leftLength, rightLength);
    }

//    private int result = 0;
}
