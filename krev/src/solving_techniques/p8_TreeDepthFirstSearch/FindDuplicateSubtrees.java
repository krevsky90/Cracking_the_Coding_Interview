package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 652. Find Duplicate Subtrees (medium)
 * https://leetcode.com/problems/find-duplicate-subtrees
 * <p>
 * #Company (11.02.2025): 0 - 3 months Amazon 3 Yandex 3 PhonePe 2 0 - 6 months Google 5 Bloomberg 3 6 months ago TikTok 4 Microsoft 3 Adobe 2 Oracle 2
 * <p>
 * Given the root of a binary tree, return all duplicate subtrees.
 * <p>
 * For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 * <p>
 * Two trees are duplicate if they have the same structure with the same node values.
 * <p>
 * Example 1:
 * Input: root = [1,2,3,4,null,2,4,null,null,4]
 * Output: [[2,4],[4]]
 * <p>
 * Example 2:
 * Input: root = [2,1,1]
 * Output: [[1]]
 * <p>
 * Example 3:
 * Input: root = [2,2,2,3,null,3,null]
 * Output: [[2,3],[3]]
 * <p>
 * Constraints:
 * The number of the nodes in the tree will be in the range [1, 5000]
 * -200 <= Node.val <= 200
 */
public class FindDuplicateSubtrees {
    /**
     * Optimized solution:
     * use map: hash -> occurrences
     * and if occurrence  == 2 => add rott to result list instantly
     *
     * BEATS ~ 58%
     */
    public List<TreeNode> findDuplicateSubtrees2(TreeNode root) {
        if (root == null) return new ArrayList<>();

        Map<String, Integer> map = new HashMap<>(); //hash to occurrence
        List<TreeNode> result = new ArrayList<>();

        dfs2(root, map, result);

        return result;
    }

    private String dfs2(TreeNode root, Map<String, Integer> map, List<TreeNode> result) {
        if (root == null) return "#";

        String left = dfs2(root.left, map, result);
        String right = dfs2(root.right, map, result);

        String res = root.val + "," + left + "," + right;

        int newVal = map.getOrDefault(res, 0) + 1;
        if (newVal == 2) {
            result.add(root);
        }
        map.put(res, newVal);

        return res;
    }

    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) used DFS pre-order traversal
     * 2) calculate hash to each subtree and keep map: node -> hash of its subtree
     * <p>
     * time to solve ~ 30-35 mins
     * <p>
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * several attempts:
     * - in case root = null we need to return smth (for eg #, but NOT empty string!)
     * <p>
     * BEATS ~ 25%
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        if (root == null) return new ArrayList<>();

        Map<TreeNode, String> map = new HashMap<>();

        dfs(root, map);

        Map<String, TreeNode> hashToRepresentativeNode = new HashMap<>();
        for (Map.Entry<TreeNode, String> e : map.entrySet()) {
            if (hashToRepresentativeNode.containsKey(e.getValue())) {
                hashToRepresentativeNode.put(e.getValue(), e.getKey());
            } else {
                hashToRepresentativeNode.put(e.getValue(), null);
            }
        }

        List<TreeNode> list = new ArrayList<>();
        for (TreeNode tn : hashToRepresentativeNode.values()) {
            if (tn != null) list.add(tn);
        }

        return list;
    }

    private String dfs(TreeNode root, Map<TreeNode, String> map) {
        if (root == null) return "#";

        String left = dfs(root.left, map);
        String right = dfs(root.right, map);

        String res = root.val + "," + left + "," + right;
        map.put(root, res);
        return res;
    }
}
