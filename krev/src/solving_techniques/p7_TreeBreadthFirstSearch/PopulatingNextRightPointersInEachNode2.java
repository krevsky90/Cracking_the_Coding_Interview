package solving_techniques.p7_TreeBreadthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 117. Populating Next Right Pointers in Each Node II
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii
 * <p>
 * Given a binary tree
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * <p>
 * Initially, all next pointers are set to NULL.
 * <p>
 * Example 1:
 * Input: root = [1,2,3,4,5,null,7]
 * Output: [1,#,2,3,#,4,5,7,#]
 * Explanation: Given the above binary tree (Figure A),
 * your function should populate each next pointer to point to its next right node, just like in Figure B.
 * The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 * <p>
 * Example 2:
 * Input: root = []
 * Output: []
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [0, 6000].
 * -100 <= Node.val <= 100
 * <p>
 * Follow-up:
 * You may only use constant extra space.
 * The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
 */
public class PopulatingNextRightPointersInEachNode2 {
    public TreeNode connect(TreeNode root) {
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
            //no need to set 'next' pointer to the last element of current level, since it points to null by default
        }

        return root;
    }
}
