package solving_techniques.p7_TreeBreadthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 662. Maximum Width of Binary Tree (medium)
 * https://leetcode.com/problems/maximum-width-of-binary-tree
 * <p>
 * #Company (25.07.2025): 0 - 3 months Meta 3 Amazon 3 Bloomberg 2 0 - 6 months Google 6 6 months ago TikTok 5 Microsoft 4 Flipkart 3 Apple 2 Uber 2
 * <p>
 * Given the root of a binary tree, return the maximum width of the given tree.
 * The maximum width of a tree is the maximum width among all levels.
 * The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes),
 * where the null nodes between the end-nodes
 * that would be present in a complete binary tree extending down to that level are also counted into the length calculation.
 * It is guaranteed that the answer will in the range of a 32-bit signed integer.
 * <p>
 * Example 1:
 * Input: root = [1,3,2,5,3,null,9]
 * Output: 4
 * Explanation: The maximum width exists in the third level with length 4 (5,3,null,9).
 * <p>
 * Example 2:
 * Input: root = [1,3,2,5,null,null,9,6,null,7]
 * Output: 7
 * Explanation: The maximum width exists in the fourth level with length 7 (6,null,null,null,null,null,7).
 * <p>
 * Example 3:
 * Input: root = [1,3,2,5]
 * Output: 2
 * Explanation: The maximum width exists in the second level with length 2 (3,2).
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 3000].
 * -100 <= Node.val <= 100
 */
public class MaximumWidthOfBT {
    /**
     * NOT solved by myself
     * <p>
     * time to spend ~ 1h
     * <p>
     * idea:
     * 1) use BFS with queue of pairs: treeNode & idx
     * 2) set idx to left Pair as 2*idx + 1, right - as 2*idx + 2
     * 3) traversing tree level by level, calculate width as delta between idx of the first and the last element of the level
     * and update (if necessary) max width
     * <p>
     * time ~ O(n)
     * space ~ O(n)
     *
     * BEATS ~ 999%
     */
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        //time ~ O(n)
        //space ~ O(n)
        //use bfs pre-order traversal
        Deque<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));
        int maxWidth = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            Pair first = q.peekFirst();
            Pair p = null;
            for (int i = 0; i < size; i++) {
                p = q.poll();
                TreeNode tempNode = p.node;
                int tempIdx = p.idx;

                if (tempNode.left != null) {
                    q.add(new Pair(tempNode.left, 2*tempIdx + 1));
                }

                if (tempNode.right != null) {
                    q.add(new Pair(tempNode.right, 2*tempIdx + 2));
                }
            }

            maxWidth = Math.max(maxWidth, p.idx - first.idx + 1);
        }

        return maxWidth;
    }

    class Pair {
        TreeNode node;
        int idx;

        public Pair(TreeNode node, int idx) {
            this.node = node;
            this.idx = idx;
        }
    }
}
