package solving_techniques.p29_Graphs;

import java.util.*;

/**
 * 2973. Find Number of Coins to Place in Tree Nodes (hard)
 * https://leetcode.com/problems/find-number-of-coins-to-place-in-tree-nodes
 *
 * You are given an undirected tree with n nodes labeled from 0 to n - 1, and rooted at node 0.
 * You are given a 2D integer array edges of length n - 1,
 *      where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
 *
 * You are also given a 0-indexed integer array cost of length n, where cost[i] is the cost assigned to the ith node.
 *
 * You need to place some coins on every node of the tree.
 * The number of coins to be placed at node i can be calculated as:
 *
 * If size of the subtree of node i is less than 3, place 1 coin.
 * Otherwise, place an amount of coins equals to the maximum product of cost values assigned to 3 distinct nodes in the subtree of node i.
 * If this product is negative, place 0 coins.
 * Return an array coin of size n such that coin[i] is the number of coins placed at node i.
 *
 * Example 1:
 *
 * Input: edges = [[0,1],[0,2],[0,3],[0,4],[0,5]], cost = [1,2,3,4,5,6]
 * Output: [120,1,1,1,1,1]
 * Explanation: For node 0 place 6 * 5 * 4 = 120 coins. All other nodes are leaves with subtree of size 1, place 1 coin on each of them.
 *
 * Example 2:
 * Input: edges = [[0,1],[0,2],[1,3],[1,4],[1,5],[2,6],[2,7],[2,8]], cost = [1,4,2,3,5,7,8,-4,2]
 * Output: [280,140,32,1,1,1,1,1,1]
 * Explanation: The coins placed on each node are:
 * - Place 8 * 7 * 5 = 280 coins on node 0.
 * - Place 7 * 5 * 4 = 140 coins on node 1.
 * - Place 8 * 2 * 2 = 32 coins on node 2.
 * - All other nodes are leaves with subtree of size 1, place 1 coin on each of them.
 *
 * Example 3:
 * Input: edges = [[0,1],[0,2]], cost = [1,2,-2]
 * Output: [0,1,1]
 * Explanation: Node 1 and 2 are leaves with subtree of size 1, place 1 coin on each of them.
 * For node 0 the only possible product of cost is 2 * 1 * -2 = -4. Hence place 0 coins on node 0.
 *
 * Constraints:
 * 2 <= n <= 2 * 10^4
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * cost.length == n
 * 1 <= |cost[i]| <= 10^4
 * The input is generated such that edges represents a valid tree.
 */
public class FindNumberOfCoinsToPlaceInTreeNodes {
    /**
     * info:
     * https://www.youtube.com/watch?v=hKHkX1NQ2KY&list=PLUPSMCjQ-7odm9bh0iW6WOCfS6wiJETnt
     *
     * NOT SOLVED by myself
     * a lot of ideas (see comments below).
     * The biggest problem is to find max product of 3 numbers
     * other ideas I got it by myself
     *
     * time to implement ~ 50 mins
     *
     * a lot of attempts
     *
     * BEATS = 30%
     */
    public static void main(String[] args) {
        int[][] edges = {{0,1},{0,2},{0,3},{0,4},{0,5}};
        int[] cost = {1,2,3,4,5,6};

        long[] res = new FindNumberOfCoinsToPlaceInTreeNodes().placedCoins(edges, cost);
        System.out.println("");
    }

    // max product of 3 elements of subtree can be in 2 cases:
    //  a) mupliply 3 largest positive numbers
    //  b) multiply 2 negative numbers and the largest positive number
    List<Queue<Integer>> largestVals = new ArrayList<>();  //NOT ONLY POSITIVE! each element contains the (<= 2) smallest negative numbers, minHeap
    List<Queue<Integer>> negativesVals = new ArrayList<>(); //each element contains the (<= 2) smallest negative numbers, maxHeap
    //to save the result
    long[] result;
    //to mark nodes as visited
    boolean[] visited;
    //to store amount of nodes in subtree
    int[] subTreeSize;

    public long[] placedCoins(int[][] edges, int[] cost) {
        int n = cost.length;
        result = new long[n];
        visited = new boolean[n];
        subTreeSize = new int[n];

        //0. build adj lists
        List<List<Integer>> adjLists = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjLists.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adjLists.get(edge[0]).add(edge[1]);
            adjLists.get(edge[1]).add(edge[0]);
        }

        for (int i = 0; i < n; i++) {
            largestVals.add(new PriorityQueue<>());
            negativesVals.add(new PriorityQueue<>(Collections.reverseOrder()));
        }

        //2. use DFS for our graph starting from 0 (since it is said that "rooted at node 0")
        dfs(0, adjLists, cost);

        return result;
    }

    private void dfs(int root, List<List<Integer>> adjLists, int[] cost) {
        visited[root] = true;

        int counter = 1;
        largestVals.get(root).add(cost[root]);
        if (cost[root] < 0) negativesVals.get(root).add(cost[root]);

        for(int v : adjLists.get(root)) {
            if (!visited[v]) {
                dfs(v, adjLists, cost);
                counter += subTreeSize[v];

                for (int tempCost : largestVals.get(v)) {
                    largestVals.get(root).add(tempCost);
                    if (largestVals.get(root).size() > 3) {
                        largestVals.get(root).poll();
                    }
                }

                for (int tempCost : negativesVals.get(v)) {
                    negativesVals.get(root).add(tempCost);
                    if (negativesVals.get(root).size() > 2) {
                        negativesVals.get(root).poll();
                    }
                }
            }
        }

        subTreeSize[root] = counter;

        if (counter < 3) {
            result[root] = 1;
        } else {
            long largestValsProduct = 1;
            int maxCost = -1;
            for (int c : largestVals.get(root)) {
                largestValsProduct *= c;
                maxCost = Math.max(maxCost, c);
            }

            long maxResult = largestValsProduct;

            if (negativesVals.get(root).size() == 2) {
                long negativesValsProduct = 1;
                for (int c : negativesVals.get(root)) {
                    negativesValsProduct *= c;
                }
                negativesValsProduct *= maxCost;
                maxResult = Math.max(largestValsProduct, negativesValsProduct);
            }

            if (maxResult < 0) {
                result[root] = 0;
            } else {
                result[root] = maxResult;
            }
        }
    }
}
