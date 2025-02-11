package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * 298. Binary Tree Longest Consecutive Sequence (medium) (locked)
 * https://leetcode.com/problems/binary-tree-longest-consecutive-sequence
 * <p>
 * #Company (10.02.2025): 6 months ago Google 4 Walmart Labs 2
 * <p>
 * Given the root of a binary tree, return the length of the longest consecutive sequence path.
 * <p>
 * A consecutive sequence path is a path where the values increase by one along the path.
 * <p>
 * Note that the path can start at any node in the tree, and you cannot go from a node to its parent in the path.
 * <p>
 * Example 1:
 * Input: root = [1,null,3,2,4,null,null,null,5]
 * Output: 3
 * Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
 * <p>
 * Example 2:
 * Input: root = [2,null,3,2,null,1]
 * Output: 2
 * Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 3 * 10^4].
 * -3 * 10^4 <= Node.val <= 3 * 10^4
 */
public class BinaryTreeLongestConsecutiveSequence {
    /**
     * KREVSKY SOLUTION:
     * idea: DFS + keep track max length
     * <p>
     * time to solve ~ 20 mins
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 3 attempts:
     * - incorrect understanding of the description (not >, but +1)
     * - missed Math.max for ans[0]
     *
     * BEATS ~ 79%
     */
    public int longestConsecutive(TreeNode root) {
        int[] ans = new int[1];
        dfs(root, ans);
        return ans[0];
    }

    public int dfs(TreeNode root, int[] ans) {
        if (root == null) return 0;

        int leftRes = dfs(root.left, ans);
        int rightRes = dfs(root.right, ans);

        int tempAns = 1;
        if (!(root.left != null && root.val + 1 == root.left.val)) {
            //since we can't use leftRes, let's set it 0
            leftRes = 0;
        }

        if (!(root.right != null && root.val + 1 == root.right.val)) {
            //since we can't use rightRes, let's set it 0
            rightRes = 0;
        }

        tempAns += Math.max(leftRes, rightRes);
        ans[0] = Math.max(ans[0], tempAns);
        return tempAns;
    }
}
