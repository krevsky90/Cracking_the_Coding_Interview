package data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.medium_trees;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://igotanoffer.com/blogs/tech/tree-interview-questions
 * https://leetcode.com/problems/binary-tree-level-order-traversal/description/
 * <p>
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 */
public class Problem2_2_BTlevelOrderTraversal {
    /**
     * KREVSKY SOLUTION
     * DFS
     * Time ~ O(n), Space ~ O(n)
     */
    public List<List<Integer>> levelOrderKREV(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        collect(result, 0, root);

        return result;
    }

    private void collect(List<List<Integer>> result, int level, TreeNode node) {
        if (node != null) {
            if (result.size() < level + 1) {
                result.add(new LinkedList<Integer>());
            }
            result.get(level).add(node.val);

            collect(result, level + 1, node.left);
            collect(result, level + 1, node.right);
        }
    }

    /**
     * https://leetcode.com/problems/binary-tree-level-order-traversal/solutions/3102866/best-solution-explained-with-illustrations/
     * BFS
     * Time ~ O(n), Space ~ O(n)
     */
    public List<List<Integer>> levelOrderBFS(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if (root != null) {
            q.offer(root);
        }

        while (!q.isEmpty()) {
            List<Integer> levelList = new LinkedList<>();
            int qLength = q.size(); //for root it is 1
            for (int i = 0; i < qLength; i++) {
                TreeNode curNode = q.poll();
                levelList.add(curNode.val);
                if (curNode.left != null) q.offer(curNode.left);
                if (curNode.right != null) q.offer(curNode.right);
            }
            result.add(levelList);
        }

        return result;
    }
}
