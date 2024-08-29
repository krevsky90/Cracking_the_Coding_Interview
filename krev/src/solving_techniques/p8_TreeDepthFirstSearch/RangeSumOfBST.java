package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * 938. Range Sum of BST (easy)
 * https://leetcode.com/problems/range-sum-of-bst
 * <p>
 * #Company: Amazon Apple Facebook Google Microsoft Oracle Yandex
 * <p>
 * Given the root node of a binary search tree and two integers low and high,
 * return the sum of values of all nodes with a value in the inclusive range [low, high].
 * <p>
 * Example 1:
 * Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
 * Output: 32
 * Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.
 * <p>
 * Example 2:
 * Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
 * Output: 23
 * Explanation: Nodes 6, 7, and 10 are in the range [6, 10]. 6 + 7 + 10 = 23.
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 2 * 10^4].
 * 1 <= Node.val <= 10^5
 * 1 <= low <= high <= 10^5
 * All Node.val are unique.
 */
public class RangeSumOfBST {
    /**
     * KREVSKY SOLUTION:
     * idea: in-order traversal of BST gives sorted increasing sequence
     * <p>
     * time to solve ~ 6 mins
     * time ~ O(n)
     * space ~ O(1)
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 58%
     */
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;

        int result = 0;
        result += rangeSumBST(root.left, low, high);
        if (low <= root.val && root.val <= high) result += root.val;
        result += rangeSumBST(root.right, low, high);

        return result;
    }
}
