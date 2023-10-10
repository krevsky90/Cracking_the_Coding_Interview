package data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.medium_trees;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * https://igotanoffer.com/blogs/tech/tree-interview-questions
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/
 *
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *
 * The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 *
 *
 * Example 1:
 * Input: root = [1,2,5,3,4,null,6]
 * Output: [1,null,2,null,3,null,4,null,5,null,6]
 *
 * Example 2:
 * Input: root = []
 * Output: []
 *
 * Example 3:
 * Input: root = [0]
 * Output: [0]
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 2000].
 * -100 <= Node.val <= 100
 *
 */
public class Problem2_10_FlattenBTtoLinkedList {
    /**
     * good explanation: https://www.youtube.com/watch?v=NOKVBiJwkD0
     *
     * Idea:
     * 1) take tempLeft and tempRight to store root's children
     * 2) set root -> left = null
     * 3) call recursively flatten method for tempLeft and tempRight
     * After that we get two lists (from left and right tree parts)
     * 4) attach root -> right = tempLeft (that is beginning of left list)
     * 5) find the latest element (le) of left list
     * 5.2) and le -> right = tempRight
     */
    public void flatten(TreeNode root) {
        if (root == null) return;
        // 1) take tempLeft and tempRight to store root's children
        TreeNode tempLeft = root.left;
        TreeNode tempRight = root.right;
        // 2) set root -> left = null
        root.left = null;
        // 3) call recursively flatten method for tempLeft and tempRight
        flatten(tempLeft);
        flatten(tempRight);
        // 4) attach root -> right = tempLeft (that is beginning of left list)
        root.right = tempLeft;
        // 5) find the latest element (le) of left list
        TreeNode temp = root;
        while (temp.right != null) {
            temp = temp.right;
        }
        // 5.2) and le -> right = tempRight
        temp.right = tempRight;
    }
}
