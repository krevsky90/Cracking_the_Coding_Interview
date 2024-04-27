package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639b65080dbaa5118a4b5fe4
 * OR
 * 124. Binary Tree Maximum Path Sum (hard)
 * https://leetcode.com/problems/binary-tree-maximum-path-sum
 * <p>
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them.
 * A node can only appear in the sequence at most once.
 * Note that the path does not need to pass through the root.
 * <p>
 * The path sum of a path is the sum of the node's values in the path.
 * <p>
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 * <p>
 * Example 1:
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 * <p>
 * Example 2:
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 3 * 10^4].
 * -1000 <= Node.val <= 1000
 */
public class ProblemChallenge2_PathWithMaximumSum {
    /**
     * KREVSKY SOLUTION:
     * idea #1: is the same as src/solving_techniques/p8_TreeDepthFirstSearch/ProblemChallenge1_TreeDiameter.java
     * idea #2: we don't need negative branches/values
     * <p>
     * time to solve ~ 9 + 20 (searched idea #2) mins
     * <p>
     * 2 attempts:
     * - did not get idea #2
     *
     * BEATS = 100%
     */
    public int maxPathSum(TreeNode root) {
        int[] result = new int[1];
        result[0] = Integer.MIN_VALUE;

        maxPathSum(root, result);

        return result[0];
    }

    private int maxPathSum(TreeNode root, int[] result) {
        if (root == null) return Integer.MIN_VALUE;

        int left = maxPathSum(root.left, result);
        int right = maxPathSum(root.right, result);

        int tempMaxPathSum = root.val;
        if (left > 0) tempMaxPathSum += left;
        if (right > 0) tempMaxPathSum += right;

        result[0] = Math.max(result[0], tempMaxPathSum);

        int maxBranch = Math.max(left, right);
        int ret = root.val;
        if (maxBranch > 0) ret += maxBranch;

        return ret;
    }

}
