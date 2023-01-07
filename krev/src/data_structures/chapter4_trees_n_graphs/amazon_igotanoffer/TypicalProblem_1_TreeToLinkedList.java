package data_structures.chapter4_trees_n_graphs.amazon_igotanoffer;

/**
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/
 * <p>
 * Given the root of a binary tree, flatten the tree into a "linked list":
 * <p>
 * The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 * <p>
 * Follow up: Can you flatten the tree in-place (with O(1) extra space)?
 */
public class TypicalProblem_1_TreeToLinkedList {
    /**
     * SOLUTION: https://www.youtube.com/watch?v=NOKVBiJwkD0
     */
    public void flatten(TreeNode root) {
        if (root == null) return;

        TreeNode tempLeft = root.left;
        TreeNode tempRight = root.right;
        root.left = null;
        flatten(tempLeft);
        flatten(tempRight);
        root.right = tempLeft;
        TreeNode temp = root;
        while (temp.right != null) {
            temp = temp.right;
        }

        temp.right = tempRight;
    }
}
