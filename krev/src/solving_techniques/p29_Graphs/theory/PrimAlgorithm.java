package solving_techniques.p29_Graphs.theory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * target: to find minimum spanning tree. i.e. we need to connect all vertices and the total length of this graph should be minimal
 * <p>
 * info and examples: https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/
 * BUT I implemented according to https://www.youtube.com/watch?v=mJcZjjKzeqk
 * <p>
 * n - number of vertices
 * edges - list of int[], where [0] - src vertex, [1] - dest vertex, [2] - weight of the edge
 * <p>
 * we track
 * 1) List<int[]> mst (i.e. result) - list of edges that form MST (minimum spanning tree)
 * 2) PriorityQueue - minHeap - contains elements like {src vertex, dest vertex, weight of the edge). NOTE: initial element has src = -1 (since we start algorithm from some random vertex)
 * 3) boolean[] inMST - list of vertices that are in MST. NOTE: we mark the vertex as 'inMST' only after popping edge (with dest = vertex) from minHeap
 *
 * Time complexity : O(E Log V)
 * Auxiliary Space :O(V)
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        List<int[]> edges1 = Arrays.asList(
                new int[]{0, 1, 5},
                new int[]{1, 2, 3},
                new int[]{0, 2, 1}
            );
        int n1 = 3;

        List<int[]> edges2 = Arrays.asList(
                new int[]{0, 1, 4},
                new int[]{0, 7, 8},
                new int[]{1, 7, 11},
                new int[]{2, 3, 7},
                new int[]{2, 8, 2},
                new int[]{7, 8, 7},
                new int[]{7, 6, 1},
                new int[]{6, 8, 6},
                new int[]{6, 5, 2},
                new int[]{2, 5, 4},
                new int[]{3, 5, 14},
                new int[]{3, 4, 9},
                new int[]{5, 4, 10}
            );
        int n2 = 9;

        new PrimAlgorithm().prim(n2, edges2);   //expected minCost = 37
    }

    public List<int[]> prim(int n, List<int[]> edges) {
        List<int[]> mst = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
        boolean[] inMST = new boolean[n];

        //0. initiate
        int startVertex = 0;    //choose some of existing vertex
        pq.add(new int[]{-1, startVertex, 0});  //-1 is src vertex for startVertex. i.e. we consider that there is no src vertex

        //1. transform edges to adjList: each row contains int[], where [0] - # of vertex, [1] - weight of the edge
        List<List<int[]>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(new int[]{edge[1], edge[2]});
            //since graph is not directed
            adjList.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }

        //2. calculation of mst and minCost
        int minCost = 0;
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int curVertex = top[1];

            if (!inMST[curVertex]) {
                if (top[0] != -1) {
                    mst.add(new int[]{top[0], curVertex});
                    minCost += top[2];
                }

                inMST[curVertex] = true;

                //handle adjacent vertices if they are not visited
                for (int[] adjElement : adjList.get(curVertex)) {
                    int adjVertex = adjElement[0];
                    if (!inMST[adjVertex]) {
                        pq.add(new int[]{curVertex, adjVertex, adjElement[1]});
                    }
                }
            }
        }

        System.out.println(minCost);
        return mst;
    }
}
