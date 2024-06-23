package solving_techniques.p29_Graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 323. Number of Connected Components in an Undirected Graph
 * https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/ (medium) (locked)
 * <p>
 * info text: https://leetcode.ca/all/323.html
 * <p>
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to find the number of connected components in an undirected graph.
 * <p>
 * Example 1:
 * Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]
 * <p>
 * 0          3
 * |          |
 * 1 --- 2    4
 * <p>
 * Output: 2
 * <p>
 * Example 2:
 * Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
 * <p>
 * 0           4
 * |           |
 * 1 --- 2 --- 3
 * <p>
 * Output:  1
 *
 * NOTE: the same as src/solving_techniques/p24_UnionFind/NumberOfProvinces.java
 */
public class NumberOfConnectedComponents {
    public static void main(String[] args) {
        int n1 = 5;
        int[][] edges1 ={{0,1},{1,2},{3,4}};
        System.out.println(numberOfConnectedComponents(n1, edges1));    //expected 2
        System.out.println(numberOfConnectedComponentsUnionFind(n1, edges1));    //expected 2

        int n2 = 5;
        int[][] edges2 ={{0,1},{1,2},{2,3},{3,4}};
        System.out.println(numberOfConnectedComponents(n2, edges2));    //expected 1
        System.out.println(numberOfConnectedComponentsUnionFind(n2, edges2));    //expected 1
    }

    /**
     * SOLUTION #1: DFS
     */
    public static int numberOfConnectedComponents(int n, int[][] edges) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                result++;
                dfs(adjList, i, visited);
            }
        }

        return result;
    }

    private static void dfs(List<List<Integer>> adjList, int i, boolean[] visited) {
        if (visited[i]) return;

        visited[i] = true;
        for (Integer v : adjList.get(i)) {
            dfs(adjList, v, visited);
        }
    }

    /**
     * SOLUTION #2: union find
     */
    public static int numberOfConnectedComponentsUnionFind(int n, int[][] edges) {
        int[] parents = new int[n];
        int[] sizes = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            sizes[i] = 1;
        }

        for (int[] edge : edges) {
            union(parents, sizes, edge[0], edge[1]);
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
//            set.add(parents[i]);  NOTE: since parents array gets updates with lag, it is better to use find() method eash time
            set.add(find(parents, i));
        }

        return set.size();
    }

    private static int find(int[] parents, int i) {
        if (parents[i] == i) {
            return i;
        } else {
            return parents[i] = find(parents, parents[i]);
        }
    }

    private static void union(int[] parents, int[] sizes, int x, int y) {
        int xrep = find(parents, x);
        int yrep = find(parents, y);
        if (xrep == yrep) return;

        if (sizes[xrep] > sizes[yrep]) {
            parents[yrep] = xrep;
            sizes[xrep] += sizes[yrep];
        } else {
            parents[xrep] = yrep;
            sizes[yrep] += sizes[xrep];
        }
    }
}