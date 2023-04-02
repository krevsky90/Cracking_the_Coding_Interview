package data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.medium_trees;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * https://igotanoffer.com/blogs/tech/tree-interview-questions
 * https://leetcode.com/problems/path-sum-iii/description/
 *
 *Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
 *
 * The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).
 *
 * Example 1:
 * Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * Output: 3
 * Explanation: The paths that sum to 8 are shown.
 *
 * Example 2:
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: 3
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 1000].
 * -10^9 <= Node.val <= 10^9
 * -1000 <= targetSum <= 1000
 */
public class Problem2_13_PathSum3 {
    /**
     * https://www.youtube.com/watch?v=uZzvivFkgtM
     *
     * NOT SOLVED!
     **/
    public int pathSum1(TreeNode root, int targetSum) {
        if (root == null) return 0;
        int result = 0;
        result += pathSum1(root.left, targetSum);
        result += pathSum1(root.right, targetSum);
        result += counter1(root, targetSum);

        return result;
    }

    //NOTE: targetSum is long for case when tree contains 10^9 elements and overcome int max/min
    private int counter1(TreeNode root, long targetSum) {
        if (root == null) return 0;

        int count = 0;
        if (root.val == targetSum)  {
            count = 1;
        }

        count += counter1(root.right, targetSum - root.val);
        count += counter1(root.left, targetSum - root.val);

        return count;
    }

    /**
     * Optimized solution see here src/data_structures/chapter4_trees_n_graphs/Problem4_12.java
     */
}
