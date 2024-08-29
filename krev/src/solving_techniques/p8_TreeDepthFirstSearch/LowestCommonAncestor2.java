package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;
import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.medium_trees.Problem2_7_LowestCommonAncestorOfBT;

/**
 * 1644. Lowest Common Ancestor of a Binary Tree II (medium) (locked)
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii
 * OR
 * https://leetcode.ca/2020-05-31-1644-Lowest-Common-Ancestor-of-a-Binary-Tree-II
 *
 * #Company: LinkedIn Microsoft Facebook
 *
 * Given the root of a binary tree, return the lowest common ancestor (LCA) of two given nodes, p and q.
 * If either node p or q does not exist in the tree, return null. All values of the nodes in the tree are unique.
 *
 * According to the definition of LCA on Wikipedia:
 * "The lowest common ancestor of two nodes p and q in a binary tree T is the lowest node
 *      that has both p and q as descendants (where we allow a node to be a descendant of itself)".
 * A descendant of a node x is a node y that is on the path from node x to some leaf node.
 *
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 *
 * Example 2:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5. A node can be a descendant of itself according to the definition of LCA.
 *
 * Example 3:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 10
 * Output: null
 * Explanation: Node 10 does not exist in the tree, so return null.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * -10^9 <= Node.val <= 10^9
 * All Node.val are unique.
 * p != q
 */
public class LowestCommonAncestor2 {
    /**
     * NOTE: the same solution as here: Problem2_7_LowestCommonAncestorOfBT # lowestCommonAncestor1
     */
    private static TreeNode result1 = null;

    public static void main(String[] args) {
        TreeNode n3 = new TreeNode(3);
        TreeNode n5 = new TreeNode(5);
        TreeNode n1 = new TreeNode(1);
        TreeNode n6 = new TreeNode(6);
        TreeNode n2 = new TreeNode(2);
        TreeNode n0 = new TreeNode(0);
        TreeNode n8 = new TreeNode(8);
        TreeNode n7 = new TreeNode(7);
        TreeNode n4 = new TreeNode(4);
        TreeNode n10 = new TreeNode(10);    //not in the tree!
        n3.left = n5;
        n3.right = n1;
        n5.left = n6;
        n5.right = n2;
        n1.left = n0;
        n1.right = n8;
        n2.left = n7;
        n2.right = n4;

        Problem2_7_LowestCommonAncestorOfBT obj = new Problem2_7_LowestCommonAncestorOfBT();
        result1 = null;
        TreeNode res = obj.lowestCommonAncestor1(n3, n5, n1);
        System.out.println(res == null ? "null" : res.val);
        result1 = null;
        res = obj.lowestCommonAncestor1(n3, n5, n4);
        System.out.println(res == null ? "null" : res.val);
        result1 = null;
        res = obj.lowestCommonAncestor1(n3, n5, n10);
        System.out.println(res == null ? "null" : res.val);
    }

    private boolean recurseTree(TreeNode currentNode, TreeNode p, TreeNode q) {
        // If reached the end of a branch, return false.
        if (currentNode == null) {
            return false;
        }

        // Left Recursion. If left recursion returns true, set left = 1 else 0
        int left = this.recurseTree(currentNode.left, p, q) ? 1 : 0;

        // Right Recursion
        int right = this.recurseTree(currentNode.right, p, q) ? 1 : 0;

        // If the current node is one of p or q
        int mid = (currentNode == p || currentNode == q) ? 1 : 0;


        // If any two of the flags left, right or mid become True
        if (mid + left + right == 2) {
            this.result1 = currentNode;
        }

        // Return true if any one of the three bool values is True.
        return mid + left + right > 0;
    }

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        // Traverse the tree
        this.recurseTree(root, p, q);
        return this.result1;
    }
}
