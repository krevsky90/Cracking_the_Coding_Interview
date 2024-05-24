package solving_techniques.p29_Graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/651a80b8f0749a59d1e0228d
 * OR
 * https://leetcode.com/problems/find-if-path-exists-in-graph (easy)
 *
 * Given an undirected graph, represented as a list of edges.
 * Each edge is illustrated as a pair of integers [u, v],
 * signifying that there's a mutual connection between node u and node v.
 *
 * Given this graph, a starting node start, and a destination node end,
 * your task is to ascertain if a path exists between the starting node and the destination node.
 *
 * Example 1:
 * Input: 4, [[0,1],[1,2],[2,3]], 0, 3
 * Expected Output: true
 * Explanation: There's a path from node 0 -> 1 -> 2 -> 3.
 */
public class Problem1_FindIfPathExistsInGraph {
    public static void main(String[] args) {
        Problem1_FindIfPathExistsInGraph obj = new Problem1_FindIfPathExistsInGraph();
        int[][] edges1 = {{2,6},{4,7},{1,2},{3,5},{7,9},{6,4},{9,8},{0,1},{3,0}};
        int n1 = 10;
        int src1 = 3;
        int dest1 = 5;

        System.out.println(obj.validPath1(n1, edges1, src1, dest1)); //expected true

    }
    /**
     * KREVSKY SOLUTION #1: union-find
     * idea:
     * 1) apply union to all edges
     * 2) check if find(src) and find(dest) returns the same representative
     *
     * time to solve ~ 20 mins
     * time ~ O(n*logn)? since we call union-method n times,
     * and union method calls find-method (which is logn on average)
     *
     * 2 attempts:
     * - incorrect result expression (compared subsets[src] == subsets[dest]).
     *
     * BEATS = 78%
     */
    public boolean validPath1(int n, int[][] edges, int source, int destination) {
        int[] subsets = new int[n];
        int[] sizes = new int[n];
        for (int i = 0; i < n; i++) {
            subsets[i] = i;
            sizes[i] = 1;
        }

        //apply union-find to all edges
        for (int[] edge : edges) {
            union(subsets, sizes, edge[0], edge[1]);
        }

        //NOTE: do NOT compare subsets[src] and subsets[dest], since subsets array is changed with lag, by find method
        boolean res = find(subsets, source) == find(subsets, destination);
        return res;
    }

    int find(int[] subsets, int x) {
        if (subsets[x] == x) {
            return x;
        } else {
            return subsets[x] = find(subsets, subsets[x]);
        }
    }

    void union(int[] subsets, int[] sizes, int x, int y) {
        int xrep = find(subsets, x);
        int yrep = find(subsets, y);

        if (xrep == yrep) return;

        int xsize = sizes[x];
        int ysize = sizes[y];
        if (xsize < ysize) {
            subsets[xrep] = yrep;
            sizes[yrep] += sizes[xrep];
        } else {
            subsets[yrep] = xrep;
            sizes[xrep] += sizes[yrep];
        }
    }

    /**
     * KREVSKY SOLUTION #2: BFS
     *
     * 1 attempt
     *
     * BEATS = 66%
     */
    public boolean validPath2(int n, int[][] edges, int source, int destination) {
        //0. convert to adj list
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        //1. apply BFS
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.add(source);
        while (!q.isEmpty()) {
            int temp = q.poll();
            if (temp == destination) return true;

            if (visited[temp]) continue;

            visited[temp] = true;

            for (Integer adjV : adjList.get(temp)) {
                if (!visited[adjV]) {
                    q.add(adjV);
                }
            }
        }

        return false;
    }
}
