package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * 235. Lowest Common Ancestor of a Binary Search Tree
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * <p>
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
 * <p>
 * According to the definition of LCA on Wikipedia:
 * ?The lowest common ancestor is defined between two nodes p and q as the lowest node in T
 * that has both p and q as descendants (where we allow a node to be a descendant of itself).?
 * <p>
 * Example 1:
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * Output: 6
 * Explanation: The LCA of nodes 2 and 8 is 6.
 * <p>
 * Example 2:
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 * <p>
 * Example 3:
 * Input: root = [2,1], p = 2, q = 1
 * Output: 2
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [2, 10^5].
 * -10^9 <= Node.val <= 10^9
 * All Node.val are unique.
 * p != q
 * p and q will exist in the BST.
 */
public class LowestCommonAncestorOfBST {
    /**
     * KREVSKY SOLUTION:
     * did NOT use properties of BST => solution is the same as for src/solving_techniques/p8_TreeDepthFirstSearch/LowestCommonAncestor # lca
     * time to solve ~ 11 mins
     * <p>
     * time ~ O(n), where n - amount of nodes
     * 1 attempt
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p) return p;
        if (root == q) return q;

        TreeNode lcsLeft = lowestCommonAncestor(root.left, p, q);
        if (lcsLeft == null) {
            //it means both nodes are in the right part
            return lowestCommonAncestor(root.right, p, q);
        }

        TreeNode lcsRight = lowestCommonAncestor(root.right, p, q);
        if (lcsRight == null) {
            //it means both nodes are in the left part
            return lcsLeft;
        }

        //if we are here, it means that each part of subtree with root has p or q node => common node = root
        return root;
    }

    /**
     * time to solve ~ 5 mins
     * <p>
     * time ~ O(h), where h - height of BST (i.e. logn <= h <= n)
     * <p>
     * 1 attempt
     */
    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root.val == p.val || root.val == q.val) return root;

        if (p.val < root.val && q.val < root.val) return lowestCommonAncestorBST(root.left, p, q);
        if (p.val > root.val && q.val > root.val) return lowestCommonAncestorBST(root.right, p, q);

        return root;
    }

    /**
     * NO recursion solution:
     * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/solutions/4709659/diagram-100-beat-space-o-1-time-o-log-h-ino-recursion/
     *
     * time ~ O(h), where h - height of BST (i.e. logn <= h <= n)
     * space ~ O(1), since no recursion
     */
    public TreeNode lowestCommonAncestorBSTWithoutRecursion(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if ((root == p || root == q) || (root.val > p.val && root.val < q.val) || (root.val < p.val && root.val > q.val))
                return root;
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            }
        }
        return root;    //should never happen, as I understand (if both nodes p and q are in BST)
    }
}
