package data_structures.chapter8_recursion_and_dynamic_programming.Recursion_in_Programming_Full_Course;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * https://youtu.be/IJDJ0kBx2LM?t=5711
 */
public class PrintAllLeafNodes {
    public void print(TreeNode root) {
        if (root == null) return;
        if (root.next == null && root.right == null) System.out.print(root.val + ", ");

        print(root.left);
        print(root.right);
    }
}
