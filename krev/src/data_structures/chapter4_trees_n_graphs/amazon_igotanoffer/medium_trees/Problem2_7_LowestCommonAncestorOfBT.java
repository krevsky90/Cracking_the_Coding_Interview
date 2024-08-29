package data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.medium_trees;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.*;

public class Problem2_7_LowestCommonAncestorOfBT {
    /**
     * KREVSKY SOLUTION
     * time ~ O(n), n - amount of nodes
     * space ~ O(logN) - 2 stacks on average
     * time to solve = 30 mins
     * 1 attempt
     * not optimal, but straightforward
     */
    public TreeNode lowestCommonAncestorKREV(TreeNode root, TreeNode p, TreeNode q) {
        //p = 4
        //q = 5
        //stackP = 3524
        //stackQ = 35
        Stack<TreeNode> stackP = new Stack<>();
        Stack<TreeNode> stackQ = new Stack<>();
        preOrderTraversalKREV(root, p, stackP);
        preOrderTraversalKREV(root, q, stackQ);
        TreeNode result = null;

        while (!stackP.isEmpty() && !stackQ.isEmpty()) {
            TreeNode tempP = stackP.pop();
            TreeNode tempQ = stackQ.pop();
            if (tempP.val == tempQ.val) {
                result = tempP;
            } else {
                break;
            }
        }

        return result;
    }

    private boolean preOrderTraversalKREV(TreeNode root, TreeNode n, Stack<TreeNode> stack) {
        if (root != null) {
            if (root.val == n.val) {
                stack.push(root);
                return true;
            }

            boolean leftFound = preOrderTraversalKREV(root.left, n, stack);
            if (leftFound) {
                stack.push(root);
                return true;
            }

            boolean rightFound = preOrderTraversalKREV(root.right, n, stack);
            if (rightFound) {
                stack.push(root);
                return true;
            }
        }

        return false;
    }

    /**
     * back to back SWE:
     * https://youtu.be/py3R23aAPCA?t=909
     * time ~ O(n)
     * space ~ O(h)
     */
    public static TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root.val == p.val || root.val == q.val) return root;

        TreeNode leftNode = lca(root.left, p, q);
        TreeNode rightNode = lca(root.right, p, q);

        if (leftNode == null) return rightNode; //if p,q belongs to right subtree
        if (rightNode == null) return leftNode; //if p,q belongs to left subtree

        return root;    //if p and q belongs to different subtrees
    }

    /**
     * OFFICIAL SOLUTION #1: Recursive Approach
     * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/solutions/194159/lowest-common-ancestor-of-a-binary-tree/
     * like my optimized solution
     */

    private static TreeNode result1 = null;

    public static void main(String[] args) {
        TreeNode n3 = new TreeNode(3);
        TreeNode n5 = new TreeNode(5);
        TreeNode n1 = new TreeNode(1);
        TreeNode n6 = new TreeNode(6);
        TreeNode n2 = new TreeNode(2);
        TreeNode n0 = new TreeNode(0);
        TreeNode n8 = new TreeNode(8);
        TreeNode n7 = new TreeNode(7);
        TreeNode n4 = new TreeNode(4);
        TreeNode n10 = new TreeNode(10);    //not in the tree!
        n3.left = n5;
        n3.right = n1;
        n5.left = n6;
        n5.right = n2;
        n1.left = n0;
        n1.right = n8;
        n2.left = n7;
        n2.right = n4;

        Problem2_7_LowestCommonAncestorOfBT obj = new Problem2_7_LowestCommonAncestorOfBT();
        result1 = null;
        TreeNode res = obj.lowestCommonAncestor1(n3, n5, n1);
        System.out.println(res == null ? "null" : res.val);
        result1 = null;
        res = obj.lowestCommonAncestor1(n3, n5, n4);
        System.out.println(res == null ? "null" : res.val);
        result1 = null;
        res = obj.lowestCommonAncestor1(n3, n5, n10);
        System.out.println(res == null ? "null" : res.val);
    }

    private boolean recurseTree(TreeNode currentNode, TreeNode p, TreeNode q) {
        // If reached the end of a branch, return false.
        if (currentNode == null) {
            return false;
        }

        // Left Recursion. If left recursion returns true, set left = 1 else 0
        int left = this.recurseTree(currentNode.left, p, q) ? 1 : 0;

        // Right Recursion
        int right = this.recurseTree(currentNode.right, p, q) ? 1 : 0;

        // If the current node is one of p or q
        int mid = (currentNode == p || currentNode == q) ? 1 : 0;


        // If any two of the flags left, right or mid become True
        if (mid + left + right == 2) {
            this.result1 = currentNode;
        }

        // Return true if any one of the three bool values is True.
        return mid + left + right > 0;
    }

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        // Traverse the tree
        this.recurseTree(root, p, q);
        return this.result1;
    }

    /**
     * OFFICIAL SOLUTION #2: Iterative using parent pointers
     * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/solutions/194159/lowest-common-ancestor-of-a-binary-tree/
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Stack for tree traversal
        Deque<TreeNode> stack = new ArrayDeque<>();

        // HashMap for parent pointers
        Map<TreeNode, TreeNode> parent = new HashMap<>();

        parent.put(root, null);
        stack.push(root);

        // Iterate until we find both the nodes p and q
        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = stack.pop();
            // While traversing the tree, keep saving the parent pointers.
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }

        // Ancestors set() for node p.
        Set<TreeNode> ancestors = new HashSet<>();

        // Process all ancestors for node p using parent pointers.
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }

        // The first ancestor of q which appears in
        // p's ancestor set() is their lowest common ancestor.
        while (!ancestors.contains(q)) {
            q = parent.get(q);
        }

        return q;
    }
}
