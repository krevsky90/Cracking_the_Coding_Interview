package solving_techniques.p29_Graphs;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.*;

/**
 * https://leetcode.com/problems/find-distance-in-a-binary-tree (medium) (blocked)
 * 1740. find-distance-in-a-binary-tree
 *
 * info: https://leetcode.ca/all/1740.html
 *
 * Given the root of a binary tree and two integers p and q, return the distance between the nodes of value p and value q in the tree.
 * The distance between two nodes is the number of edges on the path from one to the other.
 *
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 0
 * Output: 3
 * Explanation: There are 3 edges between 5 and 0: 5-3-1-0.
 *
 * Example 2:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 7
 * Output: 2
 * Explanation: There are 2 edges between 5 and 7: 5-2-7.
 *
 * Example 3:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 5
 * Output: 0
 * Explanation: The distance between a node and itself is 0.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * 0 <= Node.val <= 10^9
 * All Node.val are unique.
 * p and q are values in the tree.
 *
 */
public class FindDistanceInBT {
    public static void main(String[] args) {
        FindDistanceInBT obj = new FindDistanceInBT();
        TreeNode n0 = new TreeNode(0);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);

        n3.left = n5;
        n3.right = n1;
        n5.left = n6;
        n5.right = n2;
        n2.left = n7;
        n2.right = n4;
        n1.left = n0;
        n1.right = n8;

        System.out.println(obj.findDistanceInBT(n3, 5, 0)); //expected 3
    }

    /**
     * SOLUTION: using graph BFS
     * info: https://www.youtube.com/watch?v=eGFZc3SwcoE&list=PLUPSMCjQ-7odm9bh0iW6WOCfS6wiJETnt&index=6
     * idea:
     * 1) build graph from tree
     * 2) use BFS traversal for the graph
     *  time to implement ~ 17 mins
     *
     * 2 attempts:
     * - did not use level in queue's elements
     */
    public int findDistanceInBT(TreeNode root, int p, int q) {
        if (p == q) return 0;

        //1. build graph
        Map<Integer, List<Integer>> adjLists = new HashMap<>();
        //use DFS and build graph (i.e. fill adjLists)
        dfsTree(root, adjLists);

        //2. use BFS traversal to find the distance in the graph
        Queue<int[]> queue = new LinkedList<>();    //contains array: <value, level>
        queue.add(new int[]{p,0});
        boolean[] visited = new boolean[10000]; //since number of nodes <= 10^4

        while (!queue.isEmpty()) {
            int[] temp = queue.poll();  //temp[0] - value. temp[1] - level
            visited[temp[0]] = true;
            if (temp[0] == q) return temp[1];

            for (int v : adjLists.get(temp[0])) {
                if (!visited[v]) {
                    queue.add(new int[]{v,temp[1] + 1});
                }
            }
        }

        return -1;
    }

    private void dfsTree(TreeNode root, Map<Integer, List<Integer>> adjLists) {
        if (root != null) {
            adjLists.putIfAbsent(root.val, new ArrayList<>());

            if (root.left != null) {
                adjLists.putIfAbsent(root.left.val, new ArrayList<>());

                adjLists.get(root.val).add(root.left.val);
                adjLists.get(root.left.val).add(root.val);  //undirected graph

                dfsTree(root.left, adjLists);
            }

            if (root.right != null) {
                adjLists.putIfAbsent(root.right.val, new ArrayList<>());

                adjLists.get(root.val).add(root.right.val);
                adjLists.get(root.right.val).add(root.val);  //undirected graph

                dfsTree(root.right, adjLists);
            }
        }
    }

    //OR use
    private void bfsTree(TreeNode root, Map<Integer, List<Integer>> adjLists) {
        if (root != null) {
            adjLists.putIfAbsent(root.val, new ArrayList<>());

            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            while (!q.isEmpty()) {
                TreeNode node = q.poll();

                TreeNode leftNode = node.left;
                if (leftNode != null) {
                    adjLists.putIfAbsent(leftNode.val, new ArrayList<>());

                    adjLists.get(node.val).add(leftNode.val);
                    adjLists.get(leftNode.val).add(node.val);

                    q.add(leftNode);
                }

                TreeNode rightNode = node.right;
                if (rightNode != null) {
                    adjLists.putIfAbsent(rightNode.val, new ArrayList<>());

                    adjLists.get(node.val).add(rightNode.val);
                    adjLists.get(rightNode.val).add(node.val);

                    q.add(rightNode);
                }
            }
        }
    }
}
