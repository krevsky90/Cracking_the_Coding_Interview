package solving_techniques.treeBreadthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6397ac3f197fbea348eb0f04
 * OR
 * 111. Minimum Depth of Binary Tree
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/
 *
 * Problem Statement
 * Find the minimum depth of a binary tree.
 * The minimum depth is the number of nodes along the shortest path from the root node to the nearest leaf node.
 */
public class MinimumDepthOfBT {
    /**
     * KREVSKY
     * time to solve ~ 11 mins
     * time ~ O(n) in case if tree is linked list, n - number of nodes
     * space ~ O(n):
     *      if BT is full BT (each node has 2 children), then each level has 1, 2, 4, 8, ..., 2^k elements
     *      total sum of elements = 2^(k+1) - 1 (geometric progression) = n
     *      then 2^k = (n + 1)/2 => max queue size (i.e. 2^k) ~ O((n + 1)/2) ~ O(n)
     * 2 attempts (1 logical error)
     */
    public int minDepthBFS(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int minDepth = 0;

        while (!q.isEmpty()) {
            int length = q.size();
            minDepth++;

            for (int i = 0; i < length; i++) {
                TreeNode tempNode = q.poll();
                if (tempNode.left == null && tempNode.right == null) {
                    return minDepth;
                }

                if (tempNode.left != null) q.offer(tempNode.left);
                if (tempNode.right != null) q.offer(tempNode.right);
            }
        }

        //should not reach this row
        return minDepth;
    }

    //NOTE: alternative approach - DFS:
    public int minDepthDFS(TreeNode root) {
        if (root == null) return 0;

        if (root.left == null && root.right == null) {
            return 1;
        } else if (root.left == null && root.right != null) {
            return minDepthDFS(root.right) + 1;
        } else if (root.left != null && root.right == null) {
            return minDepthDFS(root.left) + 1;
        } else {
            return Math.min(minDepthDFS(root.left), minDepthDFS(root.right)) + 1;
        }
    }

}
