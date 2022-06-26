package data_structures.chapter4_trees_n_graphs.extra;

import data_structures.chapter4_trees_n_graphs.Node;

/**
 * https://www.youtube.com/watch?v=r2Vn6ztdSP0&list=PLNmW52ef0uwtUY4OFRF0eV1mlT5lKhe_j&index=5
 * Byte by Byte: Interview Question: Longest Consecutive Branch
 * <p>
 * Given a tree, write a function to find the length of the longest branch of nodes
 * in increasing consecutive order.
 *
 * tree:
 *      0
 *    /   \
 *   1     2
 *  / \   / \
 * 1   2 1   3
 *
 * length = 3
 */
public class LongestConsecutiveBranch {

    /**
     * KREVSKY SOLUTION (just implementation of getLength(n) method
     */
    public static int getLengthKrev(Node n) {
        return getLength(n, Integer.MIN_VALUE, 0);
    }

    /**
     * ORIGINAL SOLUTION (just implementation of getLength(n) method
     */
    public static int getLengthOrig(Node n) {
        if (n == null) return 0;
        return Math.max(getLength(n.left, n.value, 1), getLength(n.right, n.value, 1));
    }

    protected static int getLength(Node n, int prevValue, int length) {
        if (n == null) {
            return length;
        } else if (n.value > prevValue) {
            int rightL = getLength(n.right, n.value, length + 1);
            int leftL = getLength(n.left, n.value,length + 1);
            return Math.max(leftL, rightL);
        } else {
            int rightL = getLength(n.right, n.value, 1);    //length = 1 since we start new increasing branch
            int leftL = getLength(n.left, n.value,1);      //length = 1 since we start new increasing branch
            return Math.max(length, Math.max(leftL, rightL));   //max of 3 lengths it because length might be longer than new subbranches that starts from n node
        }
    }
}