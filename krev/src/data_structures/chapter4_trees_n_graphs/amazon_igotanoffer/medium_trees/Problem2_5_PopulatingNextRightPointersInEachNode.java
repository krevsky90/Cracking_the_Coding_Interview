package data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.medium_trees;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://igotanoffer.com/blogs/tech/tree-interview-questions
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/
 *
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 *
 * Example 2:
 * Input: root = []
 * Output: []
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 2^12 - 1].
 * -1000 <= Node.val <= 1000
 *
 * Follow-up:
 * You may only use constant extra space.
 * The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
 */

public class Problem2_5_PopulatingNextRightPointersInEachNode {
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;

        connect(n1);
        System.out.println("");
    }

    /**
     * KREVSKY SOLUTION
     * idea - BFS
     * time complexity ~ O(n)
     * spce complexiti ~ O(n) since the largest length of  q and levelList is on the deepest level
     */
    public static TreeNode connect(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        if (root != null) {
            q.add(root);
        }

        while (!q.isEmpty()) {
            List<TreeNode> levelList = new ArrayList<>();
            int qLength = q.size();
            for (int i = 0; i < qLength; i++) {
                TreeNode cur = q.poll();
                levelList.add(cur);
                if (cur.left != null) q.add(cur.left);
                if (cur.right != null) q.add(cur.right);
            }

            //populate 'next' pointer for all elements of levelList
            for (int j = 0; j + 1 < levelList.size(); j++) {
                levelList.get(j).next = levelList.get(j + 1);
            }
        }

        return root;
    }

    /**
     * ORIGINAL SOLUTION:
     * see haoyangfan's solution https://leetcode.com/problems/populating-next-right-pointers-in-each-node/solutions/37472/A-simple-accepted-solution/
     * idea ~ recursion
     * space complexity SHOULD BE CONSTANT, i.e. O(1)
     */
    public static TreeNode connectDFS(TreeNode root) {
        connectDFS(root, null);
        return root;
    }

    private static void connectDFS(TreeNode node1, TreeNode node2) {
        if (node1 == null) return;

        node1.next = node2;
        connectDFS(node1.left, node1.right);
        connectDFS(node1.right, node1.next == null ? null : node1.next.left);
    }
}