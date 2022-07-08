package data_structures.chapter4_trees_n_graphs.extra;

import java.util.Random;

/**
 * https://www.youtube.com/watch?v=r2Vn6ztdSP0&list=PLNmW52ef0uwtUY4OFRF0eV1mlT5lKhe_j&index=5
 * Byte by Byte: Interview Question: Random Binary Tree
 * <p>
 * Implement a binary tree with a method getRandomNode() that returns a random node
 * <p>
 * tree:
 *      4
 *    /   \
 *   2     6
 *  / \   / \
 * 1   3 5   7
 */
public class RandomBinaryTree {
    /**
     * ORIGINAL SOLUTION
     */
    RandomNode root;
    Random rand;

    public int getRandomNode() {
        if (root == null) throw new NullPointerException();
        int k = rand.nextInt(root.children + 1);
        return getRandomNode(root, k);
    }

    protected int getRandomNode(RandomNode node, int count) {
        if (count == countChildren(node.left)) {
            return node.value;
        } else if (count < countChildren(node.left)) {
            //go to left
            return getRandomNode(node.left, count);
        } else {
            //go to right
            return getRandomNode(node.right, count - countChildren(node.left) - 1);
        }
    }

    protected int countChildren(RandomNode n) {
        if (n == null) return 0;
        return n.children + 1;
    }

    public RandomNode add(int v) {
        root = add(v, root);
        return root;
    }

    public RandomNode add(int v, RandomNode node) {
        if (node == null) {
            return new RandomNode(v);
        }
        if (v > node.value) {
            node.right = add(v, node.right);
        } else if (v < node.value) {
            node.left = add(v, node.left);
        }
        node.children++;
        return node;
    }

    class RandomNode {
        public int value;
        public RandomNode left;
        public RandomNode right;
        public int children;

        public RandomNode(int v) {
            this.value = v;
        }
    }
}