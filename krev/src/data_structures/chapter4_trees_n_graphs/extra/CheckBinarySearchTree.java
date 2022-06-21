package data_structures.chapter4_trees_n_graphs.extra;

import data_structures.chapter4_trees_n_graphs.Node;

/**
 * https://www.youtube.com/watch?v=dB9aikwZttY&list=PLNmW52ef0uwtUY4OFRF0eV1mlT5lKhe_j
 * Byte by Byte: Interview Question: Binary Search Tree
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
 *
 */
public class CheckBinarySearchTree {
    public static boolean isBST(Node n) {
        return isBST(n, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private static boolean isBST(Node n, int min, int max) {
        if (n == null) return true;
        if (n.value < min || n.value > max) return false;
        return isBST(n.left, min, n.value) && isBST(n.right, n.value + 1, max);
    }
}


