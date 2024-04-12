package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 94. Binary Tree Inorder Traversal
 * https://leetcode.com/problems/binary-tree-inorder-traversal
 */
public class BinaryTreeInorderTraversal {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 10 mins
     *
     * 2 attempts:
     * - typo: "predecessor != current" - wrong. "predecessor.right != current"  - correct
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        TreeNode current = root;
        while (current != null) {
            if (current.left == null) {
                result.add(current.val);
                current = current.right;
            } else {
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    predecessor.right = current;
                    current = current.left;
                } else {
                    result.add(current.val);
                    predecessor.right = null;
                    current = current.right;
                }
            }
        }

        return result;
    }
}
