package data_structures.chapter4_trees_n_graphs.BT_Algos_for_tech_interviews;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solutions {
    public static void printDepthFirstValues(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            System.out.println(current.val);
            if (current.right != null) stack.push(current.right);
            if (current.left != null) stack.push(current.left);
        }
    }

    public static int sumTreeDFS(TreeNode root) {
        if (root == null) return 0;

        return root.val + sumTreeDFS(root.left) + sumTreeDFS(root.right);
    }

    public static int sumTreeBFS(TreeNode root) {
        if (root == null) return 0;
        int sum = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            sum += current.val;
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        return sum;
    }

    public static int minValueTreeDFS(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;

        int minLeft = root.left == null ? Integer.MAX_VALUE : minValueTreeDFS(root.left);
        int minRight = root.right == null ? Integer.MAX_VALUE : minValueTreeDFS(root.right);
        int minValue = Math.min(root.val, minLeft);
        minValue = Math.min(minValue, minRight);
        return minValue;
    }

    public static int maxPath(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;

        return root.val + Math.max(maxPath(root.left), maxPath(root.right));
    }
}
