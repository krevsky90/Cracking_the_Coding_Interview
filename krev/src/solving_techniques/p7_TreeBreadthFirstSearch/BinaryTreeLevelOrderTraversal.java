package solving_techniques.p7_TreeBreadthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6394fcd622692a384ae7dbdd
 * OR
 * 102. Binary Tree Level Order Traversal
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 *
 * Problem Statement
 * Given a binary tree, populate an array to represent its level-by-level traversal.
 * You should populate the values of all nodes of each level from left to right in separate sub-arrays.
 *
 * NOTE: the same as Cracking_the_Coding_Interview\krev\src\data_structures\chapter4_trees_n_graphs\amazon_igotanoffer\medium_trees\Problem2_2_BTlevelOrderTraversal.java
 */
public class BinaryTreeLevelOrderTraversal {
    /**
     * KREVSKY
     * time to solve ~ 8 mins
     * time ~ O(n)
     * space ~ O(n)
     *
     * 2 attempts (small typos like add tempNode rather than tempNode.val)
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int length = q.size();
            List<Integer> tempLevel = new ArrayList<>(length);

            for (int i = 0; i < length; i++) {
                TreeNode tempNode = q.poll();
                tempLevel.add(tempNode.val);

                if (tempNode.left != null) q.add(tempNode.left);
                if (tempNode.right != null) q.add(tempNode.right);
            }
            result.add(tempLevel);
        }

        return result;
    }
}
