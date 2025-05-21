package solving_techniques.p8_TreeDepthFirstSearch.theory;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.Stack;

public class PreOrderTraversalUsingStack {
    public static void preOrderTraversal(TreeNode root) {
        if (root == null) {
            return; // If the tree is empty, do nothing
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            System.out.println(current.val);    //print/handle node

            //NOTE: first step is to put right element, and then - left
            //because we will take them in reverse order (left, then right)
            if (current.right != null) stack.add(current.right);
            if (current.left != null) stack.add(current.left);
        }
    }
}
