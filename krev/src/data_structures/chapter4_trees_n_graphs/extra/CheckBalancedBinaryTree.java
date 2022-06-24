package data_structures.chapter4_trees_n_graphs.extra;

import data_structures.chapter4_trees_n_graphs.Node;

/**
 * https://www.youtube.com/watch?v=nOcFiGl5Vy4&list=PLNmW52ef0uwtUY4OFRF0eV1mlT5lKhe_j&index=6
 * Byte by Byte: Interview Question: Balanced Binary Tree
 * <p>
 * Given a binary tree, write a function to determine whether the tree is balanced
 *
 * Picture to help:
 *        1
 *     /    \
 *   2        3
 *  / \     /   \
 * 4   5   6     7
 * \      / \   / \
 *  8    9  10 11  12
 *      /
 *     13
 *
 */
public class CheckBalancedBinaryTree {
    protected static int getHeight(Node n) {
        if (n == null) return 0;

        int leftH = getHeight(n.left);
        int rightH = getHeight(n.right);

        if (leftH == -1 || rightH == -1) return -1;
        if (Math.abs(leftH - rightH) > 1) return -1;

        return Math.max(leftH, rightH) + 1; //+1 is because we need to tak into account n node itself (for exampl, look at the '9' node tree from the description)
    }

    public static boolean isBalanced(Node n) {
        return getHeight(n) != -1;
    }
}
