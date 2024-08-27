package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * 236. Lowest Common Ancestor of a Binary Tree (medium)
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 * #Company: Adobe Airbnb Amazon Apple Bloomberg ByteDance eBay Facebook Google Intuit LinkedIn Microsoft Oracle Paypal Pinterest Salesforce Snapchat Splunk Tencent Uber Visa Walmart Labs Yahoo Zillow
 *
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * According to the definition of LCA on Wikipedia:
 * ?The lowest common ancestor is defined between two nodes p and q as the lowest node in T
 * that has both p and q as descendants (where we allow a node to be a descendant of itself).?
 *
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 */
public class LowestCommonAncestor {
    /**
     * KREVSKY SOLUTION:
     * idea: use class variable to store the result (since I don't know how to return the result through the call stack
     *
     * time to solve ~ 15 mins
     * 1 attempt
     */
    private TreeNode ans = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        findNodes(root, p, q);

        return this.ans;

    }

    private int findNodes(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return 0;

        int result = 0;
        if (root == p || root == q) {
            result++;
        }

        result += findNodes(root.left, p, q);
        result += findNodes(root.right, p, q);

        if (result == 2 && this.ans == null) {
            this.ans = root;
        }

        return result;
    }

    /**
     * back to back SWE:
     * https://youtu.be/py3R23aAPCA?t=909
     * time ~ O(n)
     * space ~ O(h)
     *
     * idea #1: call lowestCommonAncestor recursively for left and right nodes.
     * idea #2: if lowestCommonAncestor returns null for (for example) left child => let's return the result of call for the right child (since it obviously contains both nodes!)
     *          and vise versa
     *          if left and right children are NOT rull => both of them contains p OR q nodes => one of them contains p, the other - q => common ancestor is root
     */
    public static TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;

        TreeNode leftRes = lca(root.left, p, q);
        TreeNode rightRes = lca(root.right, p, q);

        if (leftRes == null) {
            //it means both nodes belong to right sub-tree
            return rightRes;
        }

        if (rightRes == null) {
            //it means both nodes belong to left sub-tree
            return leftRes;
        }

        //IF leftRes != null and rightRes != null THEN eacch of sub-tree contains only one node (p OR q)
        //in this case common ancestor is root
        return root;
    }
}
