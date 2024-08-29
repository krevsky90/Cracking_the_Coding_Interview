package solving_techniques.p7_TreeBreadthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.*;

/**
 * 314. Binary Tree Vertical Order Traversal (medium) (locked)
 * https://leetcode.com/problems/binary-tree-vertical-order-traversal
 * OR
 * https://leetcode.ca/2016-10-09-314-Binary-Tree-Vertical-Order-Traversal/
 *
 * #Company: Adobe Amazon Bloomberg ByteDance Databricks Expedia Facebook Google Mathworks Microsoft Oracle Snapchat
 *
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 * Examples 1:
 * Input: [3,9,20,null,null,15,7]
 *    3
 *   /\
 *  /  \
 *  9  20
 *     /\
 *    /  \
 *   15   7
 *
 * Output:
 * [
 *   [9],
 *   [3,15],
 *   [20],
 *   [7]
 * ]
 *
 * Examples 2:
 * Input: [3,9,8,4,0,1,7]
 *      3
 *     /\
 *    /  \
 *    9   8
 *   /\  /\
 *  /  \/  \
 *  4  01   7
 *
 * Output:
 * [
 *   [4],
 *   [9],
 *   [3,0,1],
 *   [8],
 *   [7]
 * ]
 *
 * Examples 3:
 * Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)
 *      3
 *     /\
 *    /  \
 *    9   8
 *   /\  /\
 *  /  \/  \
 *  4  01   7
 *     /\
 *    /  \
 *    5   2
 *
 * Output:
 * [
 *   [4],
 *   [9,5],
 *   [3,0,1],
 *   [8,2],
 *   [7]
 * ]
 *
 *  *      3
 *  *     /\
 *  *    /  \
 *  *    9   8
 *  *   /\  /\
 *  *  /  \/  \
 *  *  4  01   7
 */
public class BinaryTreeVerticalOrderTraversal {
    public static void main(String[] args) {
        TreeNode n3 = new TreeNode(3);
        TreeNode n9 = new TreeNode(9);
        TreeNode n8 = new TreeNode(8);
        TreeNode n4 = new TreeNode(4);
        TreeNode n0 = new TreeNode(0);
        TreeNode n1 = new TreeNode(1);
        TreeNode n7 = new TreeNode(7);
        n3.left = n9;
        n3.right = n8;
        n9.left = n4;
        n9.right = n0;
        n8.left = n1;
        n8.right = n7;

        BinaryTreeVerticalOrderTraversal obj = new BinaryTreeVerticalOrderTraversal();
        List<List<Integer>> res1 = obj.verticalOrder(n3);
        System.out.println("");
    }

    /**
     * KREVSKY SOLUTION:
     * idea:
     * the same https://www.youtube.com/watch?v=xpXoHCFYC5c&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=30
     * 1) BFS
     * 2) store minmax for vertical levels
     * 3) store map "level -> list of values on this level"
     * 4) traverse from min level to max level and save all values to the resulted list
     *
     * time to solve ~ 25 mins
     * time ~ O(n) - traverse through the tree + O(2*depth of tree) = O(n) + (2*n) - in the worst case => O(n)
     * space ~ O(n/2) - for queue + O(n) - for map => O(n)
     *
     * 1 attempt:
     *
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        int[] minmax = {Integer.MAX_VALUE, Integer.MIN_VALUE};
        Map<Integer, List<Integer>> map = new HashMap<>();  //vertical level number -> list of TreeNode's values

        bfs(root, map, minmax);

        List<List<Integer>> result = new ArrayList<>();
        for (int i = minmax[0]; i <= minmax[1]; i++) {
            result.add(map.get(i));
        }

        return result;
    }

    private void bfs(TreeNode root, Map<Integer, List<Integer>> map, int[] minmax) {
        if (root == null) return;

        Queue<Pair> q = new LinkedList<>();    //TreeNode's value -> vertical level
        q.add(new Pair(root, 0));

        while (!q.isEmpty()) {
            Pair pair = q.poll();

            //1
            if (!map.containsKey(pair.level)) {
                map.put(pair.level, new ArrayList<>());
            }
            map.get(pair.level).add(pair.node.val);

            //2
            minmax[0] = Math.min(minmax[0], pair.level);
            minmax[1] = Math.max(minmax[1], pair.level);

            //3 bfs
            if (pair.node.left != null) {
                q.add(new Pair(pair.node.left, pair.level - 1));
            }

            if (pair.node.right != null) {
                q.add(new Pair(pair.node.right, pair.level + 1));
            }
        }
    }

//    pair = (7, 2)
//    map: 0 -> 3,0,1, -1 -> 9, 1-> 8, -2 -> 4, 2 -> 7
//    minmax = -2 2
//    q =

    class Pair {
        TreeNode node;
        int level;

        Pair(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }
}
