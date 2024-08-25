package solving_techniques.p29_Graphs;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 863. All Nodes Distance K in Binary Tree (medium)
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree
 * <p>
 * #Company: Amazon Bloomberg DiDi Facebook Google Hulu Microsoft Oracle Uber
 * <p>
 * Given the root of a binary tree, the value of a target node target,
 * and an integer k, return an array of the values of all nodes that have a distance k from the target node.
 * <p>
 * You can return the answer in any order.
 * <p>
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
 * Output: [7,4,1]
 * Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
 * <p>
 * Example 2:
 * Input: root = [1], target = 1, k = 3
 * Output: []
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 500].
 * 0 <= Node.val <= 500
 * All the values Node.val are unique.
 * target is the value of one of the nodes in the tree.
 * 0 <= k <= 1000
 */
public class AllNodesDistanceKinBinaryTree {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) consider tree as graph
     * 2) perform BFS for graph and target vertex
     * <p>
     * time to solve ~ 10 (to think) + 23 (to implement)
     * time ~ O(E+V). E - num of edges, V  - num of vertices
     * space ~ O(V)
     * <p>
     * 3 attempts:
     * <p>
     * <p>
     * BEATS ~ 21%
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < 501; i++) {
            graph.add(new ArrayList<>());
        }

        //step #1: convert tree to graph
        convertTreeToGraph(root, graph);

        //step #2: BFS for graph
        List<Integer> result = new ArrayList<>();
        int v = target.val;
        int level = 0;
        boolean[] visited = new boolean[501];

        Queue<int[]> q = new LinkedList<>(); //put pair: {value, level}
        q.add(new int[]{v, 0});
        visited[v] = true;

        while (!q.isEmpty()) {
            int[] pair = q.poll();
            int tmpValue = pair[0];
            int tmpLevel = pair[1];
            if (tmpLevel == k) {
                result.add(tmpValue);
            } else if (tmpLevel < k) {
                List<Integer> adjNodes = graph.get(tmpValue);
                for (int node : adjNodes) {
                    if (!visited[node]) {
                        visited[node] = true;
                        q.add(new int[]{node, tmpLevel + 1});
                    }
                }
            }
        }

        return result;
    }

    /**
     * NOTE: or you can copy the implementation from
     * solving_techniques/p29_Graphs/FindDistanceInBT.java # dfsTree
     **/
    private void convertTreeToGraph(TreeNode root, List<List<Integer>> graph) {
        //use DFS
        if (root == null) return;

        convertTreeToGraph(root.left, graph);

        if (root.left != null) {
            graph.get(root.val).add(root.left.val);
            graph.get(root.left.val).add(root.val);
        }

        convertTreeToGraph(root.right, graph);

        if (root.right != null) {
            graph.get(root.val).add(root.right.val);
            graph.get(root.right.val).add(root.val);
        }
    }
}
