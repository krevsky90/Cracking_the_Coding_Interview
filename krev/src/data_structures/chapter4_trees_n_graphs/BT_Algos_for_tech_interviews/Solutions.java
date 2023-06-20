package data_structures.chapter4_trees_n_graphs.BT_Algos_for_tech_interviews;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solutions {
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        n3.left = n1;
        n3.right = n4;
        n1.right = n2;

        printDepthFirstValues(n3);
        System.out.println("--------");
        printPreOrderValues(n3);
    }

    public static void printPreOrderValues(TreeNode root) {
        if (root == null) return;
        System.out.println(root.val);
        printPreOrderValues(root.left);
        printPreOrderValues(root.right);
    }

    /**
     * NOTE! this is the same as printPreOrderValues, BUT iterative implementation
     */
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
