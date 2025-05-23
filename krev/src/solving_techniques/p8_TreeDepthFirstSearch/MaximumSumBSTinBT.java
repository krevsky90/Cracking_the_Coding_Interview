package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * 1373. Maximum Sum BST in Binary Tree (hard)
 * https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/
 * <p>
 * #Company (21.05.2025): 0 - 3 months Meta 2 0 - 6 months Microsoft 2 6 months ago Amazon 8 Google 3 Bloomberg 2 Uber 2 Zepto 2
 * <p>
 * Given a binary tree root, return the maximum sum of all keys of any sub-tree which is also a Binary Search Tree (BST).
 * <p>
 * Assume a BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * <p>
 * Example 1:
 * Input: root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
 * Output: 20
 * Explanation: Maximum sum in a valid Binary search tree is obtained in root node with key equal to 3.
 * <p>
 * Example 2:
 * Input: root = [4,3,null,1,2]
 * Output: 2
 * Explanation: Maximum sum in a valid Binary search tree is obtained in a single root node with key equal to 2.
 * <p>
 * Example 3:
 * Input: root = [-4,-2,-5]
 * Output: 0
 * Explanation: All values are negatives. Return an empty BST.
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 4 * 10^4].
 * -4 * 10^4 <= Node.val <= 4 * 10^4
 */
public class MaximumSumBSTinBT {
    /**
     * NOT SOLVED properly by myself
     * time to spend ~ 70 mins
     * <p>
     * idea: for each node we need to keep:
     * isBST - is this node is root of BST sub-tree
     * sum - sum if its sub-tree (we can calculate it even if parent of its sub-tree is not BST
     * minValue - min node in sub-tree
     * maxValue - max node in sub-tree
     * <p>
     * criterion of BST subtree is:
     * left subTree is BST & right subtree isBST && maxValue of left subtree < node.val < minValue of right subtree
     * <p>
     * if current subtree is BST, then update maxSum
     * <p>
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * BEATS ~ 51%
     */
    class NodeWrapper {
        final TreeNode node;
        int minValue;
        int maxValue;
        int sum;
        boolean isBST = true;

        public NodeWrapper(TreeNode node) {
            this.node = node;
            this.minValue = node.val;
            this.maxValue = node.val;
            this.sum = node.val;
        }
    }

    private int maxSum = 0;

    public int maxSumBST(TreeNode root) {
        findMaxSum(root);
        return maxSum;
    }

    public NodeWrapper findMaxSum(TreeNode root) {
        if (root == null) return null;

        NodeWrapper nodeW = new NodeWrapper(root);

        if (root.left != null) {
            NodeWrapper leftW = findMaxSum(root.left);
            nodeW.minValue = Math.min(nodeW.minValue, leftW.minValue);
            nodeW.maxValue = Math.max(nodeW.maxValue, leftW.maxValue);
            nodeW.sum += leftW.sum; //we can update or NOT update sum since if leftW is NOT BST root, then nodeW.isBST => maxSum won't be updated
            nodeW.isBST = leftW.isBST && (leftW.maxValue < root.val);
        }

        if (root.right != null) {
            NodeWrapper rightW = findMaxSum(root.right);
            nodeW.minValue = Math.min(nodeW.minValue, rightW.minValue);
            nodeW.maxValue = Math.max(nodeW.maxValue, rightW.maxValue);
            nodeW.sum += rightW.sum; //we can update or NOT update sum since if rightW is NOT BST root, then nodeW.isBST => maxSum won't be updated
            nodeW.isBST &= rightW.isBST && (root.val < rightW.minValue);
        }

        if (nodeW.isBST) {
            maxSum = Math.max(maxSum, nodeW.sum);
        }

        return nodeW;
    }

    /**
     * or shorter solution, but with the same logic
     */
    public int maxSumBSTShorter(TreeNode root) {
        helper(root);
        return maxSum;
    }

    public int[] helper(TreeNode root) {
        if (root == null) return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0}; // {min, max, sum}, return values that won't bound parent's node
        int[] left = helper(root.left);
        int[] right = helper(root.right);

        if (left[1] < root.val && root.val < right[0]) {
            int sum = root.val + left[2] + right[2];
            maxSum = Math.max(maxSum, sum);
            int min = Math.min(root.val, left[0]);
            int max = Math.max(root.val, right[1]);
            return new int[]{min, max, sum};
        }

        // since current subtree is NOT BST => any higher subtree can't be BST
        // that's why we return values that bound parent's node in such way,
        // that the condition "if (left[1] < root.val && root.val < right[0])" will NEVER give true
        return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, 0};
    }
}
