package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6399d2d989924acc4bea0939
 * OR
 * 113. Path Sum II
 * https://leetcode.com/problems/path-sum-ii/
 *
 * Given the root of a binary tree and an integer targetSum,
 * return all root-to-leaf paths where the sum of the node values in the path equals targetSum.
 * Each path should be returned as a list of the node values, not node references.
 * NOTE: a root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.
 *
 * Example 1:
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: [[5,4,11,2],[5,8,4,5]]
 * Explanation: There are two paths whose sum equals targetSum:
 * 5 + 4 + 11 + 2 = 22
 * 5 + 8 + 4 + 5 = 22
 *
 * Example 2:
 * Input: root = [1,2,3], targetSum = 5
 * Output: []
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 5000].
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 */
public class AllPathsForSum {
    /**
     * KREVSKY
     * time to solve + time to debug ~ 20 + 11 mins = 31 mins
     * The idea:
     * to keep stack (i.e. LinkedList tempPath) and add/remove the elements to/from it during DFS traversal
     * time ~ O(n), where n - amount of nodes
     * space ~ O(logN) - on average, O(n) - worst case
     *
     * 2 attempts (used incorrect method poll (that is = pollFirst()) instead of pollLast(), to feel the difference - see main method)
     *
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        pathSum(root, targetSum, result, null);

        return result;
    }

    // root = 1
    // targetSum 5
    // result = [[5, 4, 11, 2], [5, 8, 4, 5]]
    // tempPath = []
    private void pathSum(TreeNode root, int targetSum, List<List<Integer>> result, LinkedList<Integer> tempPath) {
        if (root == null) return;

        if (tempPath == null) tempPath = new LinkedList<>();

        tempPath.add(root.val);
        if (root.val == targetSum && root.left == null && root.right == null) {
            result.add(new ArrayList<>(tempPath));
            tempPath.pollLast();   //i.e. remove the last inserted value, i.e. root.val
            return;
        }

        pathSum(root.left, targetSum - root.val, result, tempPath);
        pathSum(root.right, targetSum - root.val, result, tempPath);

        tempPath.pollLast();   //i.e. remove the last inserted value, i.e. root.val
        return;
    }


    public static void main(String[] args) {
        LinkedList<Integer> ll = new LinkedList<Integer>();
        ll.addAll(Arrays.asList(1, 2, 3, 4));

        //test pollLast()
        while (!ll.isEmpty()) {
            ll.stream().forEach(System.out::print);
            System.out.println();
            ll.pollLast();
        }

        //test poll() = pollFirst()
        ll.addAll(Arrays.asList(1, 2, 3, 4));
        while (!ll.isEmpty()) {
            ll.stream().forEach(System.out::print);
            System.out.println();
            ll.poll();
        }
    }
}