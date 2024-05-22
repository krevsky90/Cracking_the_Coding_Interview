package solving_techniques.p29_Graphs.theory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KruskalAlgorithm {
    public static void main(String[] args) {
        int n = 4;
        List<int[]> edges = Arrays.asList(
                new int[]{0, 1, 10},
                new int[]{0, 2, 6},
                new int[]{0, 3, 5},
                new int[]{1, 3, 15},
                new int[]{2, 3, 4}
        );

        List<int[]> result = new KruskalAlgorithm().kruskals(n, edges);

        int minCost = 0;
        for (int[] edge : result) {
            System.out.println(edge[0] + " -> " + edge[1] + ": " + edge[2]);
            minCost += edge[2];
        }

        System.out.println("minCost = " + minCost);
    }

    /**
     * Kruskal algorithm of Minimum Spanning Tree
     * info:
     * https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/
     *
     * target: to find minimum spanning tree. i.e. we need to connect all vertices and the total length of this graph should be minimal
     *
     * algorithm:
     * 1) Sort all the edges in non-decreasing order of their weight.
     * 2) Pick the smallest edge. Check if it forms a cycle with the spanning tree formed so far. If the cycle is not formed, include this edge. Else, discard it.
     * 3) Repeat step#2 until there are (V-1) edges in the spanning tree.
     *
     * NOTE: to perform validation on step #2 (i.e. to identify the loop), we need to use Union Find approach
     *
     * n - number of vertices
     * edges - list of int[], where [0] - src vertex, [1] - dest vertex, [2] - weight of the edge
     *
     * Time Complexity: O(E * logE) or O(E * logV)
     *
     * Sorting of edges takes O(E * logE) time.
     * After sorting, we iterate through all edges and apply the find-union algorithm. The find and union operations can take at most O(logV) time.
     * So overall complexity is O(E * logE + E * logV) time.
     * The value of E can be at most O(V2), so O(logV) and O(logE) are the same. Therefore, the overall time complexity is O(E * logE) or O(E*logV)
     * Auxiliary Space: O(V + E), where V is the number of vertices and E is the number of edges in the graph.
     */
    public List<int[]> kruskals(int n, List<int[]> edges) {
        List<int[]> result = new ArrayList<>();

        //for union find:
        int[] parent = new int[n];
        int[] size = new int[n];
        //initially each subset contains only vertex i
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        //1) sort all edges in acs order. time ~ O(E*logE)
        edges.sort((a,b) -> a[2] - b[2]);

        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];

            int xrep = find(parent, x);
            int yrep = find(parent, y);

            if (xrep != yrep) {
                //join x and y
                union(parent, size, x, y);
                //add edge to the result
                result.add(edge);
            }
        }

        return result;
    }

    private int find(int[] parent, int x) {
        if (parent[x] == x) {
            return x;
        } else {
            parent[x] = find(parent, parent[x]);
            return parent[x];
        }
    }

    private void union(int[] parent, int[] size, int x, int y) {
        int xrep = find(parent, x);
        int yrep = find(parent, y);

        if (xrep != yrep) {
            int xsize = size[xrep];
            int ysize = size[yrep];

            if (xsize < ysize) {
                parent[xrep] = yrep;
                size[yrep] += xrep;
            } else {
                parent[yrep] = xrep;
                size[xrep] += yrep;
            }
        }
    }
}