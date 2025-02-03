package solving_techniques.p29_Graphs;

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
     * idea: use BFS or each non visited edge
     * <p>
     * time to solve ~ 7 mins
     * <p>
     * 2 attempts:
     * <p>
     * BEATS ~ 32%
     */
    public int countComponents(int n, int[][] edges) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adjMap.put(i, new ArrayList<>());
        }

        for (int[] e : edges) {
            adjMap.get(e[0]).add(e[1]);
            adjMap.get(e[1]).add(e[0]);
        }

        boolean[] visited = new boolean[n];
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(i, visited, adjMap);
                result++;
            }
        }

        return result;
    }

    private void bfs(int i, boolean[] visited, Map<Integer, List<Integer>> adjMap) {
        Queue<Integer> q = new LinkedList<>();
        q.add(i);
        visited[i] = true;
        while (!q.isEmpty()) {
            int v = q.poll();

            for (int temp : adjMap.getOrDefault(v, new ArrayList<>())) {
                if (!visited[temp]) {
                    q.add(temp);
                    visited[temp] = true;
                }
            }
        }
    }

    //OR use DFS:
    //BEATS ~ 39%
    private void dfs(int i, boolean[] visited, Map<Integer, List<Integer>> adjMap) {
        visited[i] = true;
        for (int temp : adjMap.getOrDefault(i, new ArrayList<>())) {
            if (!visited[temp]) {
                dfs(temp, visited, adjMap);
            }
        }
    }
}
