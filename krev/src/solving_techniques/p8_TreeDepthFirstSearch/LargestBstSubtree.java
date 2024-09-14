package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * 333. Largest BST Subtree (medium) (locked)
 * https://leetcode.com/problems/largest-bst-subtree
 * OR
 * https://leetcode.ca/2016-10-28-333-Largest-BST-Subtree/
 *
 * #Company: Amazon Apple Google Lyft Microsoft
 *
 * Given the root of a binary tree, find the largest subtree, which is also a Binary Search Tree (BST),
 * where the largest means subtree has the largest number of nodes.
 *
 * A Binary Search Tree (BST) is a tree in which all the nodes follow the below-mentioned properties:
 *
 * The left subtree values are less than the value of their parent (root) node's value.
 * The right subtree values are greater than the value of their parent (root) node's value.
 * Note: A subtree must include all of its descendants.
 *
 * Example 1:
 * Input: root = [10,5,15,1,8,null,7]
 * Output: 3
 * Explanation: The Largest BST Subtree in this case is the highlighted one.
 * The return value is the subtree's size, which is 3.
 *
 * Example 2:
 * Input: root = [4,2,7,2,3,5,null,2,null,null,null,null,null,1]
 * Output: 2
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 10^4].
 * -10^4 <= Node.val <= 10^4
 *
 * Follow up: Can you figure out ways to solve it with O(n) time complexity?
 */
public class LargestBstSubtree {
    public static void main(String[] args) {
        TreeNode n10 = new TreeNode(10);
        TreeNode n5 = new TreeNode(5);
        TreeNode n15 = new TreeNode(15);
        TreeNode n1 = new TreeNode(1);
        TreeNode n8 = new TreeNode(8);
        TreeNode n7 = new TreeNode(7);
        n10.left = n5;
        n10.right = n15;
        n5.left = n1;
        n5.right = n8;
        n15.right = n7;

        LargestBstSubtree obj = new LargestBstSubtree();
        int res = obj.largestBSTSubtree(n10);
        System.out.println(res);
    }

    private int result = 0;

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 20 mins
     * idea: post-order traversal + isBST validation
     *
     * NOTE: tested only on one example!
     * but the idea is similar to https://leetcode.ca/2016-10-28-333-Largest-BST-Subtree/
     */
    public int largestBSTSubtree(TreeNode root) {
        isBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        return result;
    }

    private int isBST(TreeNode root, long min, long max) {
        if (root == null) return 0;

        int left = isBST(root.left, min, root.val);
        int right = isBST(root.right, root.val, max);

        if (left == -1 || right == -1 || !(min < root.val && root.val < max)) return -1;

        result = Math.max(result, 1 + left + right);
        return result;
    }
}
