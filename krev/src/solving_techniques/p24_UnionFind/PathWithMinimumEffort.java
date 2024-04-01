package solving_techniques.p24_UnionFind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6543a4075479c8caeec6bb4b
 * OR
 * 1631. Path With Minimum Effort
 * https://leetcode.com/problems/path-with-minimum-effort
 *
 * You are a hiker preparing for an upcoming hike.
 * You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col).
 * You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed).
 * You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
 *
 * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
 * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 *
 * Example 1:
 * Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
 * Output: 2
 * Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
 * This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
 *
 * Example 2:
 * Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
 * Output: 1
 * Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells,
 *      which is better than route [1,3,5,3,5].
 *
 * Example 3:
 * Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * Output: 0
 * Explanation: This route does not require any effort.
 *
 * Constraints:
 * rows == heights.length
 * columns == heights[i].length
 * 1 <= rows, columns <= 100
 * 1 <= heights[i][j] <= 10^6
 */
public class PathWithMinimumEffort {
    /**
     * NOT SOLVED by myself:
     * time to implement ~ 50 mins
     * ideas:
     * 1) transform heights[][] to list of edges, where each edge = int[3]
     * 2) sort edges by its length
     * 3) use UnionFind to connect start and end vertices by adding edges, starting from the shortest
     * 3.2) the last added edge is the answer since it is the biggest edge
     *
     * a lot of attempts
     */
    class UnionFind {
        public int[] sizeArr;
        public int[] parent;

        public UnionFind(int size) {
            parent = new int[size];
            sizeArr = new int[size];

            for (int i = 0; i < size; i++) {
                parent[i] = i;
                sizeArr[i] = 1;
            }
        }

        public void union(int x, int y) {
            int xrep = find(x);
            int yrep = find(y);

            if (xrep == yrep) return;

            int xrank = sizeArr[xrep];
            int yrank = sizeArr[yrep];

            if (xrank < yrank) {
                parent[xrep] = yrep;
                sizeArr[yrep] += sizeArr[xrep];
            } else {
                parent[yrep] = xrep;
                sizeArr[yrep] += sizeArr[xrep];
            }
        }

        public int find(int x) {
            if (parent[x] == x) {
                return x;
            } else {
                parent[x] = find(parent[x]);
                return parent[x];
            }
        }
    }

    public int minimumEffortPath(int[][] heights) {
        //1. convert matrix to the list of edges, where each edge = int[],
        //where [0] - number of src node, [1] - number of dest node, [2] - effort (i.e. delta of heights)
        //NOTE: number of (i,j)-th node is i*heights[0].length + j
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < heights.length; i++) {
            //draw vertical edges
            if (i > 0) {
                for (int j = 0; j < heights[0].length; j++) {
                    edges.add(new int[]{(i-1)*heights[0].length + j, i*heights[0].length + j, Math.abs(heights[i-1][j] - heights[i][j])});
                }
            }
            //draw horizontal edges
            for (int j = 1; j < heights[0].length; j++) {
                edges.add(new int[]{i*heights[0].length + j - 1, i*heights[0].length + j, Math.abs(heights[i][j-1] - heights[i][j])});
            }
        }

        //2. Sort edges in ACS order by edges[i][0]
        Collections.sort(edges, (a, b) -> a[2] - b[2]);

        //3. Connect start point (i.e. verticle with idx = 0) and end point (idx = heights.length*heights[0].length - 1)
        // by adding the edges (starting from the shortest oone) and check each time if start and end becomes connected
        int startIdx = 0;
        int endIdx = heights.length*heights[0].length - 1;

        UnionFind uf = new UnionFind(endIdx + 1);
        int i = 0;
        while (uf.find(startIdx) != uf.find(endIdx)) {
            uf.union(edges.get(i)[0], edges.get(i)[1]);
            i++;
        }

        if (i == 0) {
            //if heights = 1x1
            return 0;
        } else {
            //since the edges are sorted in ASC order, it means that edges[i-1] was the last and thr greatest edge that helped to connect startIdx and endIdx
            return edges.get(i-1)[2];
        }
    }
}
