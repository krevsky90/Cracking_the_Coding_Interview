package solving_techniques.p7_TreeBreadthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 958. Check Completeness of a Binary Tree
 * https://leetcode.com/problems/check-completeness-of-a-binary-tree/
 * #Company: Apple
 * <p>
 * Given the root of a binary tree, determine if it is a complete binary tree.
 * <p>
 * In a complete binary tree, every level, except possibly the last,
 * is completely filled, and all nodes in the last level are as far left as possible.
 * It can have between 1 and 2h nodes inclusive at the last level h.
 * <p>
 * Example 1:
 * Input: root = [1,2,3,4,5,6]
 * Output: true
 * Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}),
 * and all nodes in the last level ({4, 5, 6}) are as far left as possible.
 * <p>
 * Example 2:
 * Input: root = [1,2,3,4,5,null,7]
 * Output: false
 * Explanation: The node with value 7 isn't as far left as possible.
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 100].
 * 1 <= Node.val <= 1000
 */
public class CheckCompletenessOfBT {
    /**
     * KREVSKY SOLUTION:
     * idea #1: if some child is null => all the following children of the same level should be null
     * idea #2: if some child is null => the next level should be empty
     * <p>
     * time to solve ~ 20 mins
     * <p>
     * time ~ O(n)
     * space ~ O(n/2) ~ O(n)
     * <p>
     * 2 attempts:
     * - missed idea #2
     * <p>
     * BEATS = 84%
     */
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        boolean nullExists = false;
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (cur.left != null) {
                    if (nullExists) {
                        return false;
                    }
                    q.add(cur.left);
                } else {
                    nullExists = true;
                }

                if (cur.right != null) {
                    if (nullExists) {
                        return false;
                    }
                    q.add(cur.right);
                } else {
                    nullExists = true;
                }
            }
        }
        return true;
    }

    /**
     * SOLUTION #2:
     * info: https://leetcode.com/problems/check-completeness-of-a-binary-tree/solutions/4630526/simple-java-code-beats-100/
     * idea: add null nodes to queue => if we have already put null => we can't put not null!
     */
    public boolean isCompleteTree2(TreeNode root) {
        boolean nullExists = false;
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode currNode = queue.poll();
            if (currNode == null) {
                nullExists = true;
            } else {
                if (nullExists) return false;
                queue.offer(currNode.left);
                queue.offer(currNode.right);
            }
        }
        return true;
    }
}