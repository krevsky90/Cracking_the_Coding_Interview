package solving_techniques.p7_TreeBreadthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.*;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6397aabd009f4ccc0648b8ca
 * OR
 * 637. Average of Levels in Binary Tree (easy)
 * https://leetcode.com/problems/average-of-levels-in-binary-tree
 * <p>
 * Given the root of a binary tree, return the average value of the nodes on each level in the form of an array.
 * Answers within 10^(-5) of the actual answer will be accepted.
 * <p>
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [3.00000,14.50000,11.00000]
 * Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
 * Hence return [3, 14.5, 11].
 * <p>
 * Example 2:
 * Input: root = [3,9,20,15,7]
 * Output: [3.00000,14.50000,11.00000]
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * -2^31 <= Node.val <= 2^31 - 1
 */
public class LevelAveragesInBinaryTree {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 5 mins
     * additional idea: how to round:
     * 1) use Math.floor
     * 2) * and / by the same 10^ number => will get 5 signs after comma
     * <p>
     * 1 attempt
     *
     * BEATS = 96%
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            double sum = 0.0d;
            for (int i = 0; i < size; i++) {
                TreeNode curNode = q.poll();
                sum += curNode.val;

                if (curNode.right != null) q.add(curNode.right);
                if (curNode.left != null) q.add(curNode.left);
            }
            result.add(Math.floor(100000 * sum / size) / 100000);
        }
        return result;
    }
}
