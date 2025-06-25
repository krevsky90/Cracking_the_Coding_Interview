package solving_techniques.p11_BinarySearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * 270. Closest Binary Search Tree Value (easy) (locked)
 * https://leetcode.com/problems/closest-binary-search-tree-value
 * <p>
 * #Company (23.02.2025): 0 - 3 months Meta 26 Fractal Analytics 2 6 months ago Google 4 Oracle 4 Microsoft 3 Snap 2
 * <p>
 * Given the root of a binary search tree and a target value, return the value in the BST that is closest to the target.
 * If there are multiple answers, print the smallest.
 * <p>
 * Example 1:
 * Input: root = [4,2,5,1,3], target = 3.714286
 * Output: 4
 * <p>
 * Example 2:
 * Input: root = [1], target = 4.428571
 * Output: 1
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * 0 <= Node.val <= 10^9
 * -10^9 <= target <= 10^9
 */
public class ClosestBSTValue {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 7 mins
     * <p>
     * time ~ O(logH)
     * space ~ O(1)
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 100%
     */
    public int closestValue(TreeNode root, double target) {
        //time ~ O(h)
        //space ~ O(1)
        double diff = Double.MAX_VALUE;
        int closestNode = -100500;

        TreeNode cur = root;
        while (cur != null) {
            double tempDiff = Math.abs(cur.val - target);
            if (tempDiff == 0) return cur.val;

            if (tempDiff < diff || (tempDiff == diff && cur.val < closestNode)) {
                diff = tempDiff;
                closestNode = cur.val;
            }


            if (cur.val < target) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }

        return closestNode;
    }
}
