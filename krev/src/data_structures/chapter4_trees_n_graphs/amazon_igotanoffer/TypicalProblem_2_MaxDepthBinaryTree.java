package data_structures.chapter4_trees_n_graphs.amazon_igotanoffer;

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
 *
 * Given the root of a binary tree, return its maximum depth.
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 */
public class TypicalProblem_2_MaxDepthBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        int leftMaxDepth = 1 + maxDepth(root.left);
        int rightMaxDepth = 1 + maxDepth(root.right);
        return leftMaxDepth > rightMaxDepth ? leftMaxDepth : rightMaxDepth;
    }
}
