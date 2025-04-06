package solving_techniques.p8_TreeDepthFirstSearch.theory;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.Stack;

public class InOrderTraversalUsingStack {
    public static void inorderTraversal(TreeNode root) {
        if (root == null) {
            return; // If the tree is empty, do nothing
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            // Traverse to the leftmost node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // Process the node at the top of the stack
            current = stack.pop();
            System.out.print(current.val + " ");

            // Move to the right subtree
            current = current.right;
        }
    }
}
