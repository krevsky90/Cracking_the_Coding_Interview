package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * 101. Symmetric Tree
 * https://leetcode.com/problems/symmetric-tree/
 *
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 */
public class SymmetricTree {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 10 mins
     * 1 attempt
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode leftRoot, TreeNode rightRoot) {
        if (leftRoot == null && rightRoot == null) return true;
        if (leftRoot == null && rightRoot != null) return false;
        if (leftRoot != null && rightRoot == null) return false;
        if (leftRoot.val != rightRoot.val) return false;

        return isSymmetric(leftRoot.left, rightRoot.right) && isSymmetric(leftRoot.right, rightRoot.left);
    }
}
