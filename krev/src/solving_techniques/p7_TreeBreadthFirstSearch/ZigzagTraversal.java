package solving_techniques.p7_TreeBreadthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.*;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63979996c8fbc9fe77f1feb0
 * OR
 * 103. Binary Tree Zigzag Level Order Traversal
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 *
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
 * (i.e., from left to right, then right to left for the next level and alternate between).
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[20,9],[15,7]]
 *
 * Example 2:
 * Input: root = [1]
 * Output: [[1]]
 *
 * Example 3:
 * Input: root = []
 * Output: []
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 2000].
 * -100 <= Node.val <= 100
 *
 */
public class ZigzagTraversal {
    //NOTE: the same as src\data_structures\chapter4_trees_n_graphs\amazon_igotanoffer\medium_trees\Problem2_3_BTzigzagLevelOrderTraversal.java
    public static void main(String[] args) {
        Deque<Integer> q = new LinkedList<>(Arrays.asList(1, 2, 3, 4));
        q.offerFirst(0);
        System.out.println(q.peekFirst());  //0
        q.offerLast(5);
        System.out.println(q.peekLast());   //5
    }

    /**
     * KREVSKY SOLUTION:
     * idea: use Deque and combine pollFirst + offerLast and vice versa
     * time to solve ~ 30 mins
     * ~5 attempts (a lot of typos)
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (root == null) return result;

        boolean leftToRight = true;

        Deque<TreeNode> q = new LinkedList<>();
        q.offerLast(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> tempList = new LinkedList<>();
            if (leftToRight) {
                for (int i = 0; i < size; i++) {
                    TreeNode tempNode = q.pollFirst();
                    tempList.add(tempNode.val);

                    if (tempNode.left != null) q.offerLast(tempNode.left);
                    if (tempNode.right != null) q.offerLast(tempNode.right);
                }
            } else {
                for (int i = 0; i < size; i++) {
                    TreeNode tempNode = q.pollLast();
                    tempList.add(tempNode.val);
                    if (tempNode.right != null) q.offerFirst(tempNode.right);
                    if (tempNode.left != null) q.offerFirst(tempNode.left);
                }
            }

            result.add(tempList);
            leftToRight = !leftToRight;
        }

        return result;
    }

}
