package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.Node;
import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * 110. Balanced Binary Tree
 * https://leetcode.com/problems/balanced-binary-tree
 * <p>
 * Given a binary tree, determine if it is
 * height-balanced
 * <p>
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: true
 * <p>
 * Example 2:
 * Input: root = [1,2,2,3,3,null,null,4,4]
 * Output: false
 * <p>
 * Example 3:
 * Input: root = []
 * Output: true
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [0, 5000].
 * -10^4 <= Node.val <= 10^4
 */
public class BalancedBinaryTree {
    /**
     * KREVSKY SOLUTION #1:
     * idea #1: check isBalanced for left and right nodes
     * idea #2: is each subtree is balanced, it DOES NOT mean that the root is balanced => count heights of left and right subtrees
     * time to solve ~ 15 mins
     *
     * 2 attempt:
     * - did not consider isBalanced for left and right
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        if (!isBalanced(root.left) || !isBalanced(root.right)) return false;

        return Math.abs(maxHeight(root.left) - maxHeight(root.right)) <= 1;
    }

    private int maxHeight(TreeNode root) {
        if (root == null) return 0;

        //optional row that increases performance (from BEATS = 34% to 100%)
        if (root.right == null && root.left == null) return 1;

        int leftHeight = maxHeight(root.left);
        int rightHeight = maxHeight(root.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * SOLUTION #2:
     * see src/data_structures/chapter4_trees_n_graphs/Problem4_4.java
     */

    /**
     * SOLUTION #3:
     * info: https://www.youtube.com/watch?v=nOcFiGl5Vy4&list=PLNmW52ef0uwtUY4OFRF0eV1mlT5lKhe_j&index=6
     */
    protected static int getHeight(Node n) {
        if (n == null) return 0;

        int leftH = getHeight(n.left);
        int rightH = getHeight(n.right);

        if (leftH == -1 || rightH == -1) return -1;
        if (Math.abs(leftH - rightH) > 1) return -1;

        return Math.max(leftH, rightH) + 1; //+1 is because we need to tak into account n node itself (for exampl, look at the '9' node tree from the description)
    }

    public static boolean isBalanced3(Node n) {
        return getHeight(n) != -1;
    }
}
