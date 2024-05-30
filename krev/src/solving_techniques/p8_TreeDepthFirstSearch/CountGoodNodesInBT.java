package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * 1448. Count Good Nodes in Binary Tree
 * https://leetcode.com/problems/count-good-nodes-in-binary-tree
 * <p>
 * Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.
 * Return the number of good nodes in the binary tree.
 * <p>
 * Example 1:
 * Input: root = [3,1,4,3,null,1,5]
 * Output: 4
 * Explanation: Nodes in blue are good.
 * Root Node (3) is always a good node.
 * Node 4 -> (3,4) is the maximum value in the path starting from the root.
 * Node 5 -> (3,4,5) is the maximum value in the path
 * Node 3 -> (3,1,3) is the maximum value in the path.
 * <p>
 * Example 2:
 * Input: root = [3,3,null,4,2]
 * Output: 3
 * Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
 * <p>
 * Example 3:
 * Input: root = [1]
 * Output: 1
 * Explanation: Root is considered as good.
 * <p>
 * Constraints:
 * The number of nodes in the binary tree is in the range [1, 10^5].
 * Each node's value is between [-10^4, 10^4].
 */
public class CountGoodNodesInBT {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 15 mins
     * idea: use DFS and keep track max value of the path.
     * if cur node has >= value => update max value and increase the number of good nodes
     * <p>
     * time ~ O(n)
     * space ~ O(n) - recursion stack
     * <p>
     * 1 attempt
     * <p>
     * BEATS = 82%
     */
    public int goodNodes(TreeNode root) {
        return goodNodes(root, Integer.MIN_VALUE);
    }

    private int goodNodes(TreeNode cur, int maxVal) {
        if (cur == null) return 0;

        int res = 0;
        if (cur.val >= maxVal) {
            maxVal = cur.val;
            res++;
        }

        res += goodNodes(cur.left, maxVal);
        res += goodNodes(cur.right, maxVal);

        return res;
    }
}
