package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * 2265. Count Nodes Equal to Average of Subtree (medium)
 * https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree
 * <p>
 * #Company: Meta ???
 * <p>
 * Given the root of a binary tree, return the number of nodes where the value of the node is equal to the average of the values in its subtree.
 * <p>
 * Note:
 * The average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.
 * A subtree of root is a tree consisting of root and all of its descendants.
 * <p>
 * Example 1:
 * Input: root = [4,8,5,0,1,null,6]
 * Output: 5
 * Explanation:
 * For the node with value 4: The average of its subtree is (4 + 8 + 5 + 0 + 1 + 6) / 6 = 24 / 6 = 4.
 * For the node with value 5: The average of its subtree is (5 + 6) / 2 = 11 / 2 = 5.
 * For the node with value 0: The average of its subtree is 0 / 1 = 0.
 * For the node with value 1: The average of its subtree is 1 / 1 = 1.
 * For the node with value 6: The average of its subtree is 6 / 1 = 6.
 * <p>
 * Example 2:
 * Input: root = [1]
 * Output: 1
 * Explanation: For the node with value 1: The average of its subtree is 1 / 1 = 1.
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 1000].
 * 0 <= Node.val <= 1000
 */
public class CountNodesEqualToAverageOfSubtree {
    /**
     * KREVSKY SOLUTION:
     * idea: DFS
     * time to solve ~ 10 mins
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 100%
     */
    private int counter = 0;

    public int averageOfSubtree(TreeNode root) {
        processSubtree(root);

        return counter;
    }

    private int[] processSubtree(TreeNode root) {
        if (root == null) return new int[]{0, 0};    //[0] - sum of subtree elements, [1] - amount of subtree elements

        int sum = root.val;
        int amount = 1;

        int[] leftRes = processSubtree(root.left);
        int[] rightRes = processSubtree(root.right);

        sum += leftRes[0];
        sum += rightRes[0];

        amount += leftRes[1];
        amount += rightRes[1];

        if (sum / amount == root.val) counter++;

        return new int[]{sum, amount};
    }
}
