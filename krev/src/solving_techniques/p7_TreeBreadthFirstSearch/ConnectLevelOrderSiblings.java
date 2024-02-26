package solving_techniques.p7_TreeBreadthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63989951eb898f72291da2c7
 * OR
 * 116. Populating Next Right Pointers in Each Node
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
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
 * Example 1:
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A),
 * your function should populate each next pointer to point to its next right node, just like in Figure B.
 * The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
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
 *
 */
public class ConnectLevelOrderSiblings {
    //NOTE: problem is the same as
    //src/data_structures/chapter4_trees_n_graphs/amazon_igotanoffer/medium_trees/Problem2_5_PopulatingNextRightPointersInEachNode.java

    /**
     * KREVSKY SOLUTION:
     * idea: is the same as Problem2_5_PopulatingNextRightPointersInEachNode # connect
     *
     * time to solve ~ 11 mins
     * 1 attempt
     */
    public TreeNode connect(TreeNode root) {
        if (root == null) return root;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int qLen = queue.size();
            TreeNode prev = null;
            for (int i = 0; i < qLen; i++) {
                TreeNode current = queue.poll();
                if (prev != null) prev.next = current;
                prev = current;

                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
        }

        return root;
    }

    //reg recursive method that uses space ~ O(1)
    //see Problem2_5_PopulatingNextRightPointersInEachNode # connectDFS
}
