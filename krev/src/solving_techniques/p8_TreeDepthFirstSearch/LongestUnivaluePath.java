package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * 687. Longest Univalue Path (medium)
 * https://leetcode.com/problems/longest-univalue-path/
 *
 * #Company: Amazon Apple Google Meta
 *
 * Given the root of a binary tree, return the length of the longest path,
 * where each node in the path has the same value. This path may or may not pass through the root.
 * The length of the path between two nodes is represented by the number of edges between them.
 *
 * Example 1:
 * Input: root = [5,4,5,1,1,null,5]
 * Output: 2
 * Explanation: The shown image shows that the longest path of the same value (i.e. 5).
 *
 * Example 2:
 * Input: root = [1,4,5,4,4,null,5]
 * Output: 2
 * Explanation: The shown image shows that the longest path of the same value (i.e. 4).
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 10^4].
 * -1000 <= Node.val <= 1000
 * The depth of the tree will not exceed 1000.
 *
 */
public class LongestUnivaluePath {
    /**
     * KREVSKY SOLUTION
     * time to think + time to implement = 22 + 7 = 29 mins
     * time to debug: 20 mins
     * total time to solve ~ 50 mins
     *
     * time ~ O(n)
     * space ~ O(1)
     *
     * 3 attempts:
     * 1) typo new int[0] instead of arr[1]
     * 2) incorrect logic that affected case: [1,null,1,1,1,1,1,1]
     *        arr[0] = Math.max(arr[0], result);
     *        return result;
     *     fixed as
     *        arr[0] = Math.max(arr[0], leftResult + rightResult);
     *        return Math.max(leftResult, rightResult);
     *
     */
    public int longestUnivaluePath(TreeNode root) {
        int[] arr = new int[1]; //to store the result during recursive calls
        getPath(root, arr);
        return arr[0];
    }

    public int getPath(TreeNode root, int[] arr) {
        if (root == null) return 0;

        int leftResult = 0;
        int rightResult = 0;
        if (root.left != null) {
            if (root.val == root.left.val) {
                leftResult = 1 + getPath(root.left, arr);
            } else {
                //do not use returned value, just search max path as if root.left is real root of the tree
                getPath(root.left, arr);
            }
        }

        if (root.right != null) {
            if (root.val == root.right.val) {
                rightResult = 1 + getPath(root.right, arr);
            } else {
                //do not use returned value, just search max path as if root.right is real root of the tree
                getPath(root.right, arr);
            }
        }

        //update pathMax:
        arr[0] = Math.max(arr[0], leftResult + rightResult);
        return Math.max(leftResult, rightResult);
    }

    /**
     * KREVSKY SOLUTION #2
     * time to solve ~ 20 mins
     */
    public int result = 0;

    public int longestUnivaluePath2(TreeNode root) {
        if (root == null) return 0;

        longestUnivaluePath2(root, null);

        return result;
    }

    private int longestUnivaluePath2(TreeNode node, TreeNode parent) {
        if (node == null) return 0;

        int left = longestUnivaluePath2(node.left, node);
        int right = longestUnivaluePath2(node.right, node);

        result = Math.max(result, left + right);

        if (parent == null || node.val == parent.val) {
            return 1 + Math.max(left, right);
        } else {
            return 0;
        }
    }
}