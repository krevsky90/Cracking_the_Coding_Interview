package data_structures.chapter4_trees_n_graphs.extra;

import data_structures.chapter4_trees_n_graphs.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.youtube.com/watch?v=eltLoCIDIes&list=PLNmW52ef0uwtUY4OFRF0eV1mlT5lKhe_j&index=3
 * Byte by Byte: Interview Question: Tree Level Order
 * <p>
 * Given a tree, write a function that prints out the nodes of the tree on level order
 *
 * tree:
 *      1
 *    /   \
 *   2     3
 *  / \   / \
 * 4   5 6   7
 *
 * traverse(tree): 1 2 3 4 5 6 7
 */
public class TreeLevelOrder {

    //Breadth first search
    public static void traverse(Node n) {
        if (n == null) return;
        Queue<Node> q = new LinkedList<>();
        q.add(n);
        while (!q.isEmpty()) {
            Node curr = q.remove();
            System.out.println(curr.value + " ");
            if (curr.left != null) q.add(curr.left);
            if (curr.right != null) q.add(curr.right);
        }
    }
}
