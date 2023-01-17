package data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.easy_trees;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * https://igotanoffer.com/blogs/tech/tree-interview-questions
 * https://leetcode.com/problems/merge-two-binary-trees/description/
 * <p>
 * You are given two binary trees root1 and root2.
 * Imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not. You need to merge the two trees into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of the new tree.
 * Return the merged tree.
 * Note: The merging process must start from the root nodes of both trees.
 * <p>
 * Example 1:
 * Input: root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
 * Output: [3,4,5,5,4,null,7]
 * <p>
 * Example 2:
 * Input: root1 = [1], root2 = [1,2]
 * Output: [2,2]
 * <p>
 * Constraints:
 * The number of nodes in both trees is in the range [0, 2000].
 * -10^4 <= Node.val <= 10^4
 */
public class Problem1_7_MergeTwoBinaryTrees {
    /**
     * KREVSKY SOLUTION - START
     * time to solve ~ 40 mins
     */
    public TreeNode mergeTreesKREV(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        TreeNode root = new TreeNode(root1.val + root2.val);
        merge(root, root1.left, root2.left, true);
        merge(root, root1.right, root2.right, false);
        return root;
    }

    private void merge(TreeNode root, TreeNode child1, TreeNode child2, boolean isLeft) {
        TreeNode newNode = null;
        if (child1 == null && child2 == null) {
            return;
        } else if (child1 == null && child2 != null) {
            newNode = new TreeNode(child2.val);
        } else if (child1 != null && child2 == null) {
            newNode = new TreeNode(child1.val);
        } else {
            newNode = new TreeNode(child1.val + child2.val);
        }

        if (isLeft) {
            root.left = newNode;
        } else {
            root.right = newNode;
        }

        if (newNode != null) {
            merge(newNode, child1 == null ? null : child1.left, child2 == null ? null : child2.left, true);
            merge(newNode, child1 == null ? null : child1.right, child2 == null ? null : child2.right, false);
        }
    }
    /**
     * KREVSKY SOLUTION - END
     */

    /**
     * OFFICIAL SOLUTION - without creating of real new tree! Just update the first one!
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null)
            return t2;
        if (t2 == null)
            return t1;
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

    /**
     * KREVSKY SOLUTION BASED ON ORIGINAL IDEA
     */
    public TreeNode mergeTreesKREV2(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        TreeNode root = new TreeNode(root1.val + root2.val);
        root.left = mergeTreesKREV2(root1.left, root2.left);
        root.right = mergeTreesKREV2(root1.right, root2.right);
        return root;
    }
}
