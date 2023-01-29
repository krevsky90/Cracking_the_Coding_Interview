package data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.medium_trees;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://igotanoffer.com/blogs/tech/tree-interview-questions
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
 *
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).
 */
public class Problem2_3_BTzigzagLevelOrderTraversal {
    //   7
    //  6      9
    // 5 8  13   10
    //4         11  20

    /**
     * KREVSKY IDEA:
     *  Queue:
     *  -> |last|....|first| ->
     * 1) if we poll from the end (i.e. last), we need to add/offer to the head (i.e. first) in the same order as tree is painted, i.e. left and then right node
     * 2) if we poll from the head (i.e. first), we need to add/offer to the end (i.e. last). To save the order, we need to add right and then left node
     *
     */
    public List<List<Integer>> zigzagLevelOrderKREV(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) return result;

        Deque<TreeNode> q = new LinkedList<>();
        q.offerLast(root);

        boolean leftToRight = true;
        while (!q.isEmpty()) {
            int qLength = q.size();
            List<Integer> tempList = new LinkedList<>();
            // q = 20 9
            // ltr = false
            // qLength = 3
            // tempList = 4 11 20
            // curNode = 20
            // result = [[7],[9,6],[5  8 10],[4 11 20]]
            if (leftToRight) {
                for (int i = 0; i < qLength; i++) {
                    TreeNode curNode = q.pollLast();
                    tempList.add(curNode.val);
                    if (curNode.left != null) q.offerFirst(curNode.left);
                    if (curNode.right != null) q.offerFirst(curNode.right);
                }
            } else {
                for (int i = 0; i < qLength; i++) {
                    TreeNode curNode = q.pollFirst();
                    tempList.add(curNode.val);
                    if (curNode.right != null) q.offerLast(curNode.right);
                    if (curNode.left != null) q.offerLast(curNode.left);
                }
            }

            leftToRight = !leftToRight;
            result.add(tempList);
        }

        return result;
    }
}
