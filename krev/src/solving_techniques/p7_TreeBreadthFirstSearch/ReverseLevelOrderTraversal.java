package solving_techniques.p7_TreeBreadthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.*;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6397900a721fdbdbc77e92ad
 * OR
 * 107. Binary Tree Level Order Traversal II
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 *
 * Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values. (i.e., from left to right, level by level from leaf to root).
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[15,7],[9,20],[3]]
 *
 * Example 2:
 * Input: root = [1]
 * Output: [[1]]
 *
 * Example 3:
 * Input: root = []
 * Output: []
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 2000].
 * -1000 <= Node.val <= 1000
 */
public class ReverseLevelOrderTraversal {
    /**
     * KREVSKY SOLUTION
     * time to solve ~ 9 mins
     * idea: the same as BinaryTreeLevelOrderTraversal, but + Collections.reverse(result)
     * 1 attempt
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();

        if (root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            int size = q.size();
            List<Integer> tempList = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                tempList.add(node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            result.add(tempList);
        }

        Collections.reverse(result);
        return result;
    }
}
