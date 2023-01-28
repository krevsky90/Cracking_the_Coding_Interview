package data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.easy_trees;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * https://igotanoffer.com/blogs/tech/tree-interview-questions
 * https://leetcode.com/problems/binary-tree-preorder-traversal/description/
 *
 * Given the root of a binary tree, return the preorder traversal of its nodes' values.
 */
public class Problem1_12_BTpreorder {
    /**
     * KREVSKY SOLUTION
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root != null) {
            result.add(root.val);
            result.addAll(preorderTraversal(root.left));
            result.addAll(preorderTraversal(root.right));
        }

        return result;
    }
}
