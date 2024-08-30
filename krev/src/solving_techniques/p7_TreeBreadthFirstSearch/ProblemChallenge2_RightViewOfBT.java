package solving_techniques.p7_TreeBreadthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 199. Binary Tree Right Side View (medium)
 * https://leetcode.com/problems/binary-tree-right-side-view
 * OR
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63989ddfa1cb64009de121d1 (low)
 *
 * #Company: Adobe Amazon Apple Bloomberg ByteDance Citadel eBay Facebook Mathworks Microsoft Paypal Uber VMware
 *
 * Given the root of a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 *
 * Example 1:
 * Input: root = [1,2,3,null,5,null,4]
 * Output: [1,3,4]
 *
 * Example 2:
 * Input: root = [1,null,3]
 * Output: [1,3]
 *
 * Example 3:
 * Input: root = []
 * Output: []
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */
public class ProblemChallenge2_RightViewOfBT {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 8 mins
     *
     * time ~ O(n)
     * space ~ O(n/2) ~ O(n), where n - amount of nodes in the Tree. n/2 - will be in case of Perfect BT
     *  (i.e. each node has 2 children except leaves, all branches have the same length)
     * 1 attempt:
     */
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<Integer> result = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            TreeNode current = null;
            for (int i = 0; i < size; i++) {
               current = q.poll();
               if (current.left != null) q.add(current.left);
               if (current.right != null) q.add(current.right);
            }
            //since we finished for-loop => current = the last element that was initially in the queue
            // => the right most element of current level (which was stored in the queue)
            result.add(current.val);
        }

        return result;
    }
}