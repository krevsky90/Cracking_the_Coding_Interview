package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * 814. Binary Tree Pruning
 * https://leetcode.com/problems/binary-tree-pruning
 *
 * #Company: Apple
 *
 * Given the root of a binary tree, return the same tree
 * where every subtree (of the given tree) not containing a 1 has been removed.
 * A subtree of a node node is node plus every node that is a descendant of node.
 *
 * Example 1:
 * Input: root = [1,null,0,0,1]
 * Output: [1,null,0,null,1]
 * Explanation:
 * Only the red nodes satisfy the property "every subtree not containing a 1".
 * The diagram on the right represents the answer.
 *
 * Example 2:
 * Input: root = [1,0,1,0,0,0,1]
 * Output: [1,null,1,null,1]
 *
 * Example 3:
 * Input: root = [1,1,0,1,1,0,1,0]
 * Output: [1,1,0,1,1,null,1]
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 200].
 *
 * Node.val is either 0 or 1.
 */
public class BinaryTreePruning {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 10 mins
     * <p>
     * 1 attempt
     * <p>
     * BEATS = 100%
     */
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return null;

        boolean canBeRemoved = root.val == 0;

        TreeNode left = pruneTree(root.left);
        root.left = left;
        canBeRemoved &= left == null;

        TreeNode right = pruneTree(root.right);
        root.right = right;
        canBeRemoved &= right == null;

        return canBeRemoved ? null : root;
    }
}
