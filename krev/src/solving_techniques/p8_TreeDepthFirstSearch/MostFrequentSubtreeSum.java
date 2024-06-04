package solving_techniques.p8_TreeDepthFirstSearch;


import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 508. Most Frequent Subtree Sum
 * https://leetcode.com/problems/most-frequent-subtree-sum/
 *
 * #Company: Apple
 *
 * Given the root of a binary tree, return the most frequent subtree sum.
 * If there is a tie, return all the values with the highest frequency in any order.
 *
 * The subtree sum of a node is defined as the sum of all the node values
 * formed by the subtree rooted at that node (including the node itself).
 *
 * Example 1:
 * Input: root = [5,2,-3]
 * Output: [2,-3,4]
 *
 * Example 2:
 * Input: root = [5,2,-5]
 * Output: [2]
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * -10^5 <= Node.val <= 10^5
 *
 */
public class MostFrequentSubtreeSum {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) count sum and save its frequency to map
     * 2) find the sums which have max frequency and return them
     * time to solve ~ 15-20 mins
     *
     * time ~ O(n)
     * space ~ O(n)
     *
     * 1 attempt:
     *
     * BEATS = 16%
     *
     */
    public int[] findFrequentTreeSum(TreeNode root) {
        //sum -> freq map
        Map<Integer, Integer> map = new HashMap<>();
        dfs(root, map);

        //find max freq
        int maxFreq = Integer.MIN_VALUE;
        for (int f : map.values()) {
            maxFreq = Math.max(maxFreq, f);
        }

        //find all sums with maxFreq
        List<Integer> res = new ArrayList<>();
        for (int key : map.keySet()) {
            if (map.get(key) == maxFreq) res.add(key);
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    //NOTE: root can't be null!
    private int dfs(TreeNode root, Map<Integer, Integer> map){
        int res = root.val;
        if (root.left != null) {
            res += dfs(root.left, map);
        }

        if (root.right != null) {
            res += dfs(root.right, map);
        }

        map.put(res, map.getOrDefault(res, 0) + 1);
        return res;
    }
}
