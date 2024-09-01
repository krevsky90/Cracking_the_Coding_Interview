package data_structures.chapter4_trees_n_graphs.extra;

import data_structures.chapter4_trees_n_graphs.Node;

/**
 * 98. Validate Binary Search Tree (medium)
 * https://leetcode.com/problems/validate-binary-search-tree
 *
 * NOTE: the same as src/data_structures/chapter4_trees_n_graphs/Problem4_5.java
 *
 * #Company: Adobe Amazon Apple Asana Bloomberg Capital One Facebook Goldman Sachs Google LinkedIn Mathworks Microsoft Oracle Salesforce TripAdvisor Uber Visa VMware Walmart Labs Yandex
 *
 * <p>
 * Given a binary tree, write a function to test if the tree is a binary search tree (BST)
 *
 * Picture to help:
 *      5
 *    / | \
 *   2  |  7
 *  / \ | /|\
 * 1   3|4 | 8
 *
 */
public class CheckBinarySearchTree {
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(1);
        n1.left = n2;
        System.out.println(isBSTByInOrderTraversal(n1));
    }

    /**
     * SOLUTION #1
     * info:
     * https://www.youtube.com/watch?v=dB9aikwZttY&list=PLNmW52ef0uwtUY4OFRF0eV1mlT5lKhe_j
     *
     */
    public boolean isValidBST(Node root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(Node root, long min, long max) {
        if (root == null) return true;
        if (!(min < root.value && root.value < max)) return false;

        return isValidBST(root.left, min, root.value) && isValidBST(root.right, root.value, max);
    }


    /**
     * SOLUTION #2:
     * To cope with case when nodes can have value = Integer.MIN_VALUE / Integer.MAX_VALUE
     * we need to use Morris In-Order Traversal that should give us sorted ascending array (in case if Tree is BST
     */

    public static boolean isBSTByInOrderTraversal(Node n) {
        Node prev = null;
        while (n != null) {
            if (n.left == null) {
                //check if previously 'printed' element is less than current element to be 'printed'
                if (prev != null && prev.value >= n.value) return false;
                prev = n;
                n = n.right;
            } else {
                //find predecessor
                Node predecessor = n.left;
                while (predecessor.right != null && predecessor.right != n) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    //create loop
                    predecessor.right = n;
                    n = n.left;
                } else {
                    //check if previously 'printed' element is less than current element to be 'printed'
                    if (prev != null && prev.value >= n.value) return false;

                    //break loop
                    predecessor.right = null;
                    prev = n;
                    n = n.right;
                }
            }
        }

        return true;
    }
}


