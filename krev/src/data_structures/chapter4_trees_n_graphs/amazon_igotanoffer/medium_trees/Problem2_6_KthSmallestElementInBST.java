package data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.medium_trees;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.*;

/**
 * https://igotanoffer.com/blogs/tech/tree-interview-questions
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
 *
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 */

public class Problem2_6_KthSmallestElementInBST {
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        n3.left = n1;
        n3.right = n4;
        n1.right = n2;

        int result = getKthSmallestElementOfBST(n3, 3);
        System.out.println(result);
    }

    /**
     * Approach 0 - THE MOST OPTIMIZED
     * Iterative Inorder Traversal
     * info: https://www.youtube.com/watch?v=5LUXSvjmGCw
     *
     * This way one could speed up the solution because there is no need to build the entire inorder traversal, and one could stop after the kth element.
     * Time complexity: O(H+k), where H is a tree height
     * Space complexity: O(H), where average H = logN, worst H = N
     */
    public static int getKthSmallestElementOfBST(TreeNode root, int k) {
        if (root == null) return Integer.MIN_VALUE;	//corner case or error

        int counter = 0;
        Stack<TreeNode> stack = new Stack<>();  //or LinkedList
        TreeNode current = root;
        while (!stack.isEmpty() || current != null) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            //we found the leftmost element of branch of the root
            //let's remove element from the stack and increment counter (of visited nodes)
            current = stack.pop();
            counter++;
            if (counter == k) return current.val;

            //then go to right node of current! (rather than root! that was my mistake)
            current = current.right;
        }

        return Integer.MIN_VALUE;	//means we didn't find the result
    }

    /**
     * Approach 1:
     * KREVSKY SOLUTION:
     * idea - use priority queue
     * time to solve = 14 mins, 1 attempt
     * time complexity ~ (N*logN), because each insertion to priorityQueue takes O(logN). total amount of insertions is N
     */
    public static int kthSmallestKREVPriorityQueue(TreeNode root, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>();

        inOrderTraversal(root, q);
        Integer result = null;
        for (int i = 0; i < k; i++) {
            result = q.poll();
        }

        return result.intValue();
    }

    private static void inOrderTraversal(TreeNode root, PriorityQueue<Integer> q) {
        if (root != null) {
            inOrderTraversal(root.left, q);
            q.offer(root.val);
            inOrderTraversal(root.right, q);
        }
    }

    /**
     * Approach 2.1:
     * KREVSKY SOLUTION
     * idea - use stack to store ALL the elements of the tree (first of all we need to pop the largest elements).
     * This should take O(n) time
     * After that we need poll the elements from this stack k times to find the result
     *
     * time to solve = 6 mins, 1 attempt
     * <p>
     * NOTE: in my case it is sufficient to use simple ArrayList! see kthSmallestUsingArrayList method
     */
    public static int kthSmallestKREVStack(TreeNode root, int k) {
        Stack<Integer> stack = new Stack<>();

        fillStack(root, stack);
        Integer result = null;
        for (int i = 0; i < k; i++) {
            result = stack.pop();
        }

        return result.intValue();
    }

    //takes time ~ O(n)
    private static void fillStack(TreeNode root, Stack<Integer> stack) {
        if (root != null) {
            fillStack(root.right, stack);
            stack.push(root.val);
            fillStack(root.left, stack);
        }
    }

    /**
     * Approach 2.2
     * KREVSKY SOLUTION
     * idea - the same as initial (to fill array by inOrder traversal), BUT optimized - once we get k-th value - return it
     * time ~ O(k)
     * space ~ O(k)
     */
    public static int kthSmallestListStorageOptimized(TreeNode root, int k) {
        return preOrder2(root, new ArrayList<>(), k);
    }

    private static int preOrder2(TreeNode root, List<Integer> list, int k) {
        if (root == null) return Integer.MIN_VALUE;
        int left = preOrder2(root.left, list, k);
        if (left != Integer.MIN_VALUE) return left;

        list.add(root.val);
        if (list.size() == k) return root.val;

        int right = preOrder2(root.right, list, k);
        if (right != Integer.MIN_VALUE) return right;

        return Integer.MIN_VALUE;	//instead of nothing
    }

    /**
     * Approach 3:
     * Recursive Inorder Traversal
     * https://leetcode.com/problems/kth-smallest-element-in-a-bst/solutions/284145/kth-smallest-element-in-a-bst/
     * Time complexity : O(N) to build a traversal.
     * Space complexity : O(N)
     */
    public static int kthSmallestUsingArrayList(TreeNode root, int k) {
        List<Integer> nums = inorder(root, new ArrayList<>());
        return nums.get(k - 1);
    }

    private static List<Integer> inorder(TreeNode root, List<Integer> arr) {
        if (root == null) return arr;
        inorder(root.left, arr);
        arr.add(root.val);
        inorder(root.right, arr);
        return arr;
    }
}