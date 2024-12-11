package solving_techniques.p29_Graphs.theory;

import java.util.*;

/**
 * conditions: algorithm works with directed weighted (only allow non-negative weights) graph
 * target: to find shortest lowest cost path from starting vertex to destination vertex
 */
public class DijkstrasShortestPathAlgorithm {
    public static void main(String[] args) {
        /* Let us create the example graph discussed above */
        //BUT it is better to set INFINITY (rather than 0) in adjMatrix!
        int graph[][] = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}};

        DijkstrasShortestPathAlgorithm t = new DijkstrasShortestPathAlgorithm();
//        t.dijkstra1(graph, 0);
        t.dijkstra2(graph, 0);
    }

    /**
     * THE BEST implementation:
     * PathWithMaximumProbability # maxProbability3
     */

    /**
     * info:
     * https://www.geeksforgeeks.org/java-program-for-dijkstras-shortest-path-algorithm-greedy-algo-7/
     * <p>
     * time ~ O(V^2)
     * space ~ O(V)
     */
    public void dijkstra1(int graph[][], int src) {
        int V = graph.length;
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // sptSet[i] will true if vertex i is included in the shortest path tree
        // in other words, the shortest distance from src to i is finalized
        boolean sptSet[] = new boolean[V];  //initialized by false by default

        // as I understand, it is better to use V (rather than V-1).
        // yes, the last node will not affect dist[], BUT it will affect sptSet[]
        // => if we want to have only true values in sptSet in the end of the method, we need to use V
        //
        // each time we search vertex that gives minimum distance and include the vertex to Set
        for (int i = 0; i < V; i++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first iteration.
            int u = minIndex(dist, sptSet);

            sptSet[u] = true;

            //update distances of adjacent vertices
            for (int v = 0; v < V; v++) {
                if (!sptSet[v]  //v is not in the shortest path tree (i.e. the distance from src to v is not finalized)
                        && graph[u][v] != 0 //u and v are neighbour vertices. BUT it is better to set INFINITY (rather than 0) in adjMatrix!
                        && dist[u] != Integer.MAX_VALUE //looks like it may be useful for graph that is not fully tied
                        && dist[u] + graph[u][v] < dist[v]  //there is shorter path then currently stored
                ) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        //optional part:
        printSolution(dist, V);
    }

    private int minIndex(int[] dist, boolean[] sptSet) {
        int minDistance = Integer.MAX_VALUE;
        int minIdx = -1;

        for (int i = 0; i < dist.length; i++) {
            if (sptSet[i] == false && minDistance > dist[i]) {
                minDistance = dist[i];
                minIdx = i;
            }
        }

        return minIdx;
    }

    private static void printSolution(int dist[], int V) {
        System.out.println("Vertex   Distance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i + " tt " + dist[i]);
    }

    /**
     * Optimized implementation: using Min Heap (PriorityQueue)
     * based on https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-in-java-using-priorityqueue/
     *
     * graph[i][j] - shows weight of the edge from i to j vertex. if i,j are not connected => graph[i][j] = INFINITY
     *
     * Time Complexity: O(V + E*logV), BUT if we use adjacent list, not matrix! (since we traverse through all edges instead of "for (int u = 0; u < n; u++)")
     * Space Complexity: O(V + E)
     *
     */
    public void dijkstra2(int[][] graph, int src) {
        int n = graph.length;

        int[] dist = new int[n];
        Set<Integer> settle = new HashSet<>();  //vertices that are included into the shortest path tree
        //min heap: stores int[] where [0] - distance from src to the vertex, [1] - vertex. top element has min distance
        //this Heap takes time O(logn) to add() or poll() element => it is used instead of minIndex(..) method that takes time ~ O(n)
        //NOTE: the same vertex can be added to pq several times (with different distance value). The record with min distance will be popped
        // => this vertex will be included into settle
        //so if we face with this vertex in the pq again - just do nothing
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        pq.add(new int[]{0, src});

        //while we count the shortest path for all vertices:
        while (settle.size() != n) {
            if (pq.isEmpty()) {
                //it means that there is not change to build the shortest path tree for all vertices => break and show what we could calculate
                break;  //or return
            }

            int[] tempMin = pq.poll();
            int v = tempMin[1];
            //since the same vertex can be added to pq several times (with different distance value)
            if (settle.contains(v)) {
                continue;
            }
            settle.add(v);
            //update neighbours. traverse through all vertices and update only neighbours
            for (int u = 0; u < n; u++) {
                if (!settle.contains(u) && graph[v][u] != 0) {  //NOTE: it is better to mark not tied vertices by INFINITY value
                    dist[u] = Math.min(dist[u], dist[v] + graph[v][u]);
                    pq.add(new int[]{dist[u], u});
                }
            }
        }

        printSolution(dist, n);
    }

}