package solving_techniques.p24_UnionFind;

import java.util.*;

/**
 * 323. Number of Connected Components in an Undirected Graph (medium) (locked)
 * https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph
 * <p>
 * #Company (3.02.2025): 0 - 3 months General Motors 4 Google 3 Amazon 3 LinkedIn 2 6 months ago Meta 2 X 2
 * <p>
 * You have a graph of n nodes. You are given an integer n and an array edges
 * where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.
 * <p>
 * Return the number of connected components in the graph.
 * <p>
 * Example 1:
 * Input: n = 5, edges = [[0,1],[1,2],[3,4]]
 * Output: 2
 * <p>
 * Example 2:
 * Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
 * Output: 1
 * <p>
 * Constraints:
 * 1 <= n <= 2000
 * 1 <= edges.length <= 5000
 * edges[i].length == 2
 * 0 <= ai <= bi < n
 * ai != bi
 * There are no repeated edges.
 */
public class NumberOfConnectedComponentsInAnUndirectedGraph {
    /**
     * KREVSKY SOLUTION:
     * idea: use Union Find
     * time to solve ~ 11 mins
     * <p>
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 3 attempts:
     * - typos
     * - put i to set, but not findParent(parents, i)
     *
     * BEATS ~ 74%
     */
    public int countComponents(int n, int[][] edges) {
        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            union(parents, x, y);
        }

        Set<Integer> set = new HashSet<>();
        for (int i : parents) {
            set.add(findParent(parents, i));
        }

        return set.size();
    }

    private int findParent(int[] parents, int x) {
        if (x != parents[x]) {
            parents[x] = findParent(parents, parents[x]);
        }
        return parents[x];
    }

    private void union(int[] parents, int x, int y) {
        int repX = findParent(parents, x);
        int repY = findParent(parents, y);
        if (repX != repY) {
            parents[repX] = repY;
        }
    }
}
