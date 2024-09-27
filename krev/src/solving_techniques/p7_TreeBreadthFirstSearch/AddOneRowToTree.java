package solving_techniques.p7_TreeBreadthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 623. Add One Row to Tree (medium)
 * https://leetcode.com/problems/add-one-row-to-tree
 * <p>
 * #Company: Gilt Groupe Microsoft Facebook
 * <p>
 * Given the root of a binary tree and two integers val and depth,
 * add a row of nodes with value val at the given depth depth.
 * <p>
 * Note that the root node is at depth 1.
 * <p>
 * The adding rule is:
 * 1) Given the integer depth, for each not null tree node cur at the depth depth - 1,
 * create two tree nodes with value val as cur's left subtree root and right subtree root.
 * 2) cur's original left subtree should be the left subtree of the new left subtree root.
 * 3) cur's original right subtree should be the right subtree of the new right subtree root.
 * 4) If depth == 1 that means there is no depth = 'depth - 1' at all,
 * then create a tree node with value val as the new root of the whole original tree,
 * and the original tree is the new root's left subtree.
 * <p>
 * Example 1:
 * Input: root = [4,2,6,3,1,5], val = 1, depth = 2
 * Output: [4,1,1,2,null,null,6,3,1,5]
 * <p>
 * Example 2:
 * Input: root = [4,2,null,3,1], val = 1, depth = 3
 * Output: [4,2,null,1,1,3,null,null,1]
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * The depth of the tree is in the range [1, 10^4].
 * -100 <= Node.val <= 100
 * -10^5 <= val <= 10^5
 * 1 <= depth <= the depth of tree + 1
 */
public class AddOneRowToTree {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 15 mins
     * idea: BFS + change left and right pointers for the nodes of 'depth - 1' layer
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 2 attempts:
     * - forgot to add root to Queue
     * <p>
     * BEATS ~ 100%
     */
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return newRoot;
        }

        Queue<TreeNode> q = new LinkedList<>();
        int tempDepth = 1;
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            if (tempDepth == depth - 1) {
                for (int i = 0; i < size; i++) {
                    TreeNode tempNode = q.poll();
                    TreeNode newLeft = new TreeNode(val);
                    TreeNode newRight = new TreeNode(val);
                    newLeft.left = tempNode.left;
                    newRight.right = tempNode.right;
                    tempNode.left = newLeft;
                    tempNode.right = newRight;
                }
                return root;
            } else {
                for (int i = 0; i < size; i++) {
                    TreeNode tempNode = q.poll();
                    if (tempNode.left != null) q.add(tempNode.left);
                    if (tempNode.right != null) q.add(tempNode.right);
                }
                tempDepth++;
            }
        }

        return root;
    }
}
