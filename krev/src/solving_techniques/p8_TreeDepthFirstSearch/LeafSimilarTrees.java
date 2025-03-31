package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 872. Leaf-Similar Trees (easy)
 * https://leetcode.com/problems/leaf-similar-trees
 * <p>
 * #Company (31.03.2025):
 * <p>
 * Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.
 * <p>
 * For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
 * <p>
 * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
 * <p>
 * Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
 * <p>
 * Example 1:
 * Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
 * Output: true
 * <p>
 * Example 2:
 * Input: root1 = [1,2,3], root2 = [1,3,2]
 * Output: false
 * <p>
 * Constraints:
 * The number of nodes in each tree will be in the range [1, 200].
 * Both of the given trees will have values in the range [0, 200].
 */
public class LeafSimilarTrees {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 11 mins
     * <p>
     * idea: usual in-order DFS traversal and gathering leafs
     * NOTE: compare NOT Integers, but intValue()!
     * for the Integer (wrapper class) JVM does some optimizations to save space
     * like storing Integer within range -128 to 127 share same memory address.
     * Instead, you should compare their intValue().
     * <p>
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 2 attempts:
     * - compared Integers (but not intValue()) by !=
     *
     * BEATS ~ 100%
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaves1 = new ArrayList<>();
        List<Integer> leaves2 = new ArrayList<>();
        dfs(root1, leaves1);
        dfs(root2, leaves2);

        if (leaves1.size() != leaves2.size()) {
            return false;
        }

        for (int i = 0; i < leaves1.size(); i++) {
            // for the Integer (wrapper class) JVM does some optimizations to save space
            // like storing Integer within range -128 to 127 share same memory address.
            // Instead, you should compare their intValue().
            if (leaves1.get(i).intValue() != leaves2.get(i).intValue()) {
                return false;
            }
        }

        return true;
    }

    private void dfs(TreeNode root, List<Integer> leaves) {
        if (root.left == null && root.right == null) {
            leaves.add(root.val);
            return;
        }

        if (root.left != null) dfs(root.left, leaves);
        if (root.right != null) dfs(root.right, leaves);
    }
}
