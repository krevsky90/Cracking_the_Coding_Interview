package data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.medium_trees;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://igotanoffer.com/blogs/tech/tree-interview-questions
 * https://leetcode.com/problems/binary-tree-right-side-view/description/
 *
 * Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 * Example 1:
 * Input: root = [1,2,3,null,5,null,4]
 * Output: [1,3,4]
 *
 * Example 2:
 * Input: root = [1,null,3]
 * Output: [1,3]
 *
 * Example 3:
 * Input: root = []
 * Output: []
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */
public class Problem2_11_BTRightSideView {
    /**
     * KREVSKY SOLUTION:
     * idea = the same as Problem2_2_BTlevelOrderTraversal + take the rightmost element of each level
     * time to solve = 50 mins (25 thinking + 15 mins solving + 10 debugging)
     * 2 attempts (syntax errors)
     * time complexity ~ O(n) (since we traverse all nodes)
     * space complexity ~ O(n) (since we store all nodes)
     *
     * A little bit not optimal since we can use q.size() instead of curLevelLength (see src/data_structures/chapter4_trees_n_graphs/amazon_igotanoffer/medium_trees/Problem2_2_BTlevelOrderTraversal.java)
     */
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<List<TreeNode>> data = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int curLevelLength = 1;
        while (!q.isEmpty()) {
            List<TreeNode> tempArr = new ArrayList<>();
            int nextLevelLength = 0;
            for (int i = 0; i < curLevelLength; i++) {
                TreeNode node = q.poll();
                tempArr.add(node);
                if (node.right != null) {
                    q.offer(node.right);
                    nextLevelLength++;
                }

                if (node.left != null) {
                    q.offer(node.left);
                    nextLevelLength++;
                }
            }

            curLevelLength = nextLevelLength;
            data.add(tempArr);
        }

        //return the first element of each array of data-array
        List<Integer> result = new ArrayList<>(data.size());
        for (List<TreeNode> tempList : data) {
            result.add(tempList.get(0).val);
        }

        return result;
    }

    /**
     * idea is the same
     * + store only the rightmost element of each level (not the whole level! since all elements go through the queue)
     * + use q.size()
     */
    public List<Integer> rightSideView2(TreeNode root) {
        if (root == null) return new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        List<Integer> result = new ArrayList<>();
        while (!q.isEmpty()) {
            Integer rightMostVal = null;
            int curLevelLength = q.size();
            for (int i = 0; i < curLevelLength; i++) {
                TreeNode node = q.poll();
                rightMostVal = node.val;
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            result.add(rightMostVal);
        }

        return result;
    }
}
