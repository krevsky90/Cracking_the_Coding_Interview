package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1110. Delete Nodes And Return Forest (medium)
 * https://leetcode.com/problems/delete-nodes-and-return-forest/
 * <p>
 * #Company (19.04.2025): 0 - 3 months Meta 7 Google 4 6 months ago Amazon 4 Apple 2 TikTok 2
 * <p>
 * Given the root of a binary tree, each node in the tree has a distinct value.
 * <p>
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 * <p>
 * Return the roots of the trees in the remaining forest. You may return the result in any order.
 * <p>
 * Example 1:
 * Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * Output: [[1,2,null,4],[6],[7]]
 * <p>
 * Example 2:
 * Input: root = [1,2,4,null,3], to_delete = [3]
 * Output: [[1,2,4]]
 * <p>
 * Constraints:
 * The number of nodes in the given tree is at most 1000.
 * Each node has a distinct value between 1 and 1000.
 * to_delete.length <= 1000
 * to_delete contains distinct values between 1 and 1000.
 */
public class DeleteNodesAndReturnForest {
    /**
     * KREVSKY SOLUTION
     * time to solve ~ 25 mins
     * <p>
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 2 attempts:
     * - did not remove parent -> child link if I remove child
     * <p>
     * BEATS ~ 96%
     */
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> result = new ArrayList<>();
        Set<Integer> removed = new HashSet<>();
        for (int i : to_delete) {
            removed.add(i);
        }
        dfs(root, removed, null, result);
        return result;
    }

    private void dfs(TreeNode root, Set<Integer> removed, TreeNode parent, List<TreeNode> result) {
        if (root == null) return;

        if (removed.contains(root.val)) {
            if (parent != null) {
                if (parent.left == root) parent.left = null;
                if (parent.right == root) parent.right = null;
            }
            dfs(root.left, removed, null, result);
            dfs(root.right, removed, null, result);
        } else {
            if (parent == null) {
                result.add(root);
            }

            dfs(root.left, removed, root, result);
            dfs(root.right, removed, root, result);
        }
    }
}
