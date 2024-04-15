package solving_techniques.p20_TopologicalSort;

import java.util.*;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a5ccee923d029fb1c4b0fe (hard)
 * OR
 * 310. Minimum Height Trees (medium)
 * https://leetcode.com/problems/minimum-height-trees/
 * <p>
 * A tree is an undirected graph in which any two vertices are connected by exactly one path.
 * In other words, any connected graph without simple cycles is a tree.
 * <p>
 * Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates
 * that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root.
 * When you select a node x as the root, the result tree has height h. Among all possible rooted trees,
 * those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
 * <p>
 * Return a list of all MHTs' root labels. You can return the answer in any order.
 * <p>
 * The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 * <p>
 * Example 1:
 * Input: n = 4, edges = [[1,0],[1,2],[1,3]]
 * Output: [1]
 * Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
 * <p>
 * Example 2:
 * Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
 * Output: [3,4]
 * <p>
 * Constraints:
 * 1 <= n <= 2 * 10^4
 * edges.length == n - 1
 * 0 <= ai, bi < n
 * ai != bi
 * All the pairs (ai, bi) are distinct.
 * The given input is guaranteed to be a tree and there will be no repeated edges.
 */
public class ProblemChallenge2_MinimumHeightTrees {
    /**
     * KREVSKY SOLUTION #1: topological sort
     * <p>
     * additional ideas:
     * 1) to REMOVE polled node from the initial maps! it helps us to write stop-condition for this problem
     * for other problem it won't be worse
     * 2) to use for-loop inside while-loop to cut the edges level by level
     * <p>
     * time to solve ~ 58 mins
     * <p>
     * a lot of attempts:
     * - the main problem is to write stop-condition, since it is impossible (or hard for me) to distinct cases:
     * "initially there are 2 nodes in sourceQueue"
     * "during the process of exclusion of the nodes we got the moment when sourceQueue contains 2 elements"
     * <p>
     * BEATS = 5%
     * <p>
     * NOTE: btw, here is the same solution, but using arrays an more simple structure in general https://leetcode.com/problems/minimum-height-trees/solutions/2401788/java-runtime-12-ms-faster-than-99-90/
     * it beats 99%
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) return Arrays.asList(0);
        if (n == 2) return Arrays.asList(0, 1);

        Map<Integer, Integer> verticleToCounterMap = new HashMap<>();   //count the nodes that are connected to this particular node
        Map<Integer, List<Integer>> verticleToClosestNodesMap = new HashMap<>();    //store mapping to the list of nodes that are connected to this particular node

        for (int i = 0; i < n; i++) {
            verticleToCounterMap.put(i, 0);
            verticleToClosestNodesMap.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            verticleToCounterMap.put(edge[0], verticleToCounterMap.get(edge[0]) + 1);
            verticleToCounterMap.put(edge[1], verticleToCounterMap.get(edge[1]) + 1);  //since we have undirected graph
            verticleToClosestNodesMap.get(edge[0]).add(edge[1]);
            verticleToClosestNodesMap.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> sourceQueue = new LinkedList<>();
        //find all 'leaves', i.e. vertices that have only  connection. they are
        for (Integer verticle : verticleToCounterMap.keySet()) {
            if (verticleToCounterMap.get(verticle) == 1) {
                sourceQueue.add(verticle);
            }
        }

        while (sourceQueue.size() >= 2) {
            int size = sourceQueue.size();
            for (int i = 0; i < size; i++) {
                Integer node = sourceQueue.poll();
                Integer connectedNode = verticleToClosestNodesMap.get(node).get(0);     //current list should have the only value
                verticleToClosestNodesMap.remove(node);  //optional since we check the size of verticleToCounterMap. but to have consistency it is better to remove all occurences
                verticleToCounterMap.remove(node);

                verticleToCounterMap.put(connectedNode, verticleToCounterMap.get(connectedNode) - 1);
                verticleToClosestNodesMap.get(connectedNode).remove(node);
                if (verticleToCounterMap.get(connectedNode) == 1) {
                    sourceQueue.add(connectedNode);
                }
            }

            if (verticleToCounterMap.size() <= 2) {
                break;
            }
        }

        return new ArrayList<>(verticleToCounterMap.keySet());
    }

    //https://leetcode.com/problems/minimum-height-trees/solutions/1135345/straightforward-solution-with-finding-center-of-the-tree-algorithm/
    //the same idea, but simple solution, without queue and with simple stop-condition
    public List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        int[] degree = new int[n];
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
            degree[edge[0]]++;
            degree[edge[1]]++;
        }

        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] <= 1) {
                leaves.add(i);
                degree[i] = 0;
            }
        }

        int c = leaves.size();

        while (c < n) {
            List<Integer> newLeaves = new ArrayList<>();
            for (int leaf : leaves) {
                for (int neighbor : map.get(leaf)) {
                    degree[neighbor]--;
                    if (degree[neighbor] == 1) {
                        newLeaves.add(neighbor);
                    }
                }
                degree[leaf] = 0;
            }
            c += newLeaves.size();
            leaves = newLeaves;
        }

        return leaves;
    }
}