package data_structures.chapter4_trees_n_graphs.amazon_igotanoffer;

/**
 * https://leetcode.com/problems/invert-binary-tree/description/
 * <p>
 * Given the root of a binary tree, invert the tree (left and right parts), and return its root.
 */
public class TypicalProblem_3_InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        invertTree(root.left);
        invertTree(root.right);

        TreeNode tempNode = root.left;
        root.left = root.right;
        root.right = tempNode;

        return root;
    }
}
