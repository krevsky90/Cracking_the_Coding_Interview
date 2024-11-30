package solving_techniques.p7_TreeBreadthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1609. Even Odd Tree (medium)
 * https://leetcode.com/problems/even-odd-tree
 * <p>
 * #Company: Meta Bloomberg
 * <p>
 * A binary tree is named Even-Odd if it meets the following conditions:
 * <p>
 * The root of the binary tree is at level index 0, its children are at level index 1, their children are at level index 2, etc.
 * For every even-indexed level, all nodes at the level have odd integer values in strictly increasing order (from left to right).
 * For every odd-indexed level, all nodes at the level have even integer values in strictly decreasing order (from left to right).
 * Given the root of a binary tree, return true if the binary tree is Even-Odd, otherwise return false.
 * <p>
 * Example 1:
 * Input: root = [1,10,4,3,null,7,9,12,8,6,null,null,2]
 * Output: true
 * Explanation: The node values on each level are:
 * Level 0: [1]
 * Level 1: [10,4]
 * Level 2: [3,7,9]
 * Level 3: [12,8,6,2]
 * Since levels 0 and 2 are all odd and increasing and levels 1 and 3 are all even and decreasing, the tree is Even-Odd.
 * <p>
 * Example 2:
 * Input: root = [5,4,2,3,3,7]
 * Output: false
 * Explanation: The node values on each level are:
 * Level 0: [5]
 * Level 1: [4,2]
 * Level 2: [3,3,7]
 * Node values in level 2 must be in strictly increasing order, so the tree is not Even-Odd.
 * <p>
 * Example 3:
 * Input: root = [5,9,1,3,5,7]
 * Output: false
 * Explanation: Node values in the level 1 should be even integers.
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^5].
 * 1 <= Node.val <= 10^6
 */
public class EvenOddTree {
    /**
     * KREVSKY SOLUTION #1
     * time to solve ~ 10 mins
     * <p>
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 2 attempts:
     * - forgot q.add(root)
     * <p>
     * BEATS ~ 16%
     */
    public boolean isEvenOddTree1(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        boolean even = true;
        if (root == null) return false; //just in case

        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (even) {
                    if (node.val % 2 == 1 && (list.isEmpty() || list.get(list.size() - 1) < node.val)) {
                        list.add(node.val);
                    } else {
                        return false;
                    }
                } else {
                    if (node.val % 2 == 0 && (list.isEmpty() || list.get(list.size() - 1) > node.val)) {
                        list.add(node.val);
                    } else {
                        return false;
                    }
                }

                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            even = !even;
        }

        return true;
    }

    /**
     * Optimized, since we don't need to store the list with level. only prev value
     * BEATS ~ 78%
     */
    public boolean isEvenOddTree2(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        boolean even = true;
        if (root == null) return false; //just in case

        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            int prev = even ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (even) {
                    if (!(node.val % 2 == 1 && prev < node.val)) {
                        return false;
                    }
                } else {
                    if (!(node.val % 2 == 0 && prev > node.val)) {
                        return false;
                    }
                }

                prev = node.val;

                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            even = !even;
        }

        return true;
    }

}
