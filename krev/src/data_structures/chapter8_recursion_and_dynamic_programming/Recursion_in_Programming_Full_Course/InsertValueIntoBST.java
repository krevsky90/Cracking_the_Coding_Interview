package data_structures.chapter8_recursion_and_dynamic_programming.Recursion_in_Programming_Full_Course;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * https://youtu.be/IJDJ0kBx2LM?t=5358
 */
public class InsertValueIntoBST {
    public TreeNode insert(TreeNode root, int data) {
        if (root == null) {
            root = new TreeNode(data);
            return root;
        }

        if (root.val < data) {
            root.right = insert(root.right, data);
        } else {
            root.left = insert(root.left, data);
        }

        return root;
    }
}
