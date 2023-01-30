package data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.medium_trees;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.*;

public class Problem2_6_KthSmallestElementInBST {
    /**
     * KREVSKY SOLUTION:
     * idea - use priority queue
     * time to solve = 14 mins, 1 attempt
     * time complexity ~ (N*logN), because each insertion to priorityQueue takes O(logN). total amount of insertions is N
     */
    public int kthSmallestKREVPriorityQueue(TreeNode root, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>();

        inOrderTraversal(root, q);
        Integer result = null;
        for (int i = 0; i < k; i++) {
            result = q.poll();
        }

        return result.intValue();
    }

    private void inOrderTraversal(TreeNode root, PriorityQueue<Integer> q) {
        if (root != null) {
            inOrderTraversal(root.left, q);
            q.offer(root.val);
            inOrderTraversal(root.right, q);
        }
    }

    /**
     * KREVSKY SOLUTION
     * idea - use stack to store ALL the elements of the tree (first of all we need to pop the largest elements).
     * This should take O(n) time
     * After that we need poll the elements from this stack k times to find the result
     *
     * time to solve = 6 mins, 1 attempt
     * <p>
     * NOTE: in my case it is sufficient to use simple ArrayList! see kthSmallestUsingArrayList method
     */
    public int kthSmallestKREVStack(TreeNode root, int k) {
        Stack<Integer> stack = new Stack<>();

        fillStack(root, stack);
        Integer result = null;
        for (int i = 0; i < k; i++) {
            result = stack.pop();
        }

        return result.intValue();
    }

    //takes time ~ O(n)
    private void fillStack(TreeNode root, Stack<Integer> stack) {
        if (root != null) {
            fillStack(root.right, stack);
            stack.push(root.val);
            fillStack(root.left, stack);
        }
    }

    /**
     * Approach 1: Recursive Inorder Traversal
     * https://leetcode.com/problems/kth-smallest-element-in-a-bst/solutions/284145/kth-smallest-element-in-a-bst/
     * Time complexity : O(N) to build a traversal.
     * Space complexity : O(N)
     */
    public int kthSmallestUsingArrayList(TreeNode root, int k) {
        List<Integer> nums = inorder(root, new ArrayList<>());
        return nums.get(k - 1);
    }

    private List<Integer> inorder(TreeNode root, List<Integer> arr) {
        if (root == null) return arr;
        inorder(root.left, arr);
        arr.add(root.val);
        inorder(root.right, arr);
        return arr;
    }

    /**
     * Approach 2: Iterative Inorder Traversal
     * This way one could speed up the solution because there is no need to build the entire inorder traversal, and one could stop after the kth element.
     * Time complexity: O(H+k), where H is a tree height
     * Space complexity: O(H), where average H = logN, worst H = N
     */
    public int kthSmallest(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<>();

        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (--k == 0) return root.val;
            root = root.right;
        }
    }
}
