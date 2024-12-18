package solving_techniques.p29_Graphs;

import java.util.*;

/**
 * 934. Shortest Bridge (medium)
 * https://leetcode.com/problems/shortest-bridge
 *
 * #Company: 0 - 3 months Uber 5 Meta 3 Amazon 2 TikTok 2 Docusign 2 0 - 6 months Google 2 Microsoft 2 McKinsey 2 6 months ago Flipkart 18 Snap 6 Apple 3 Yahoo 3 Bloomberg 2
 *
 * You are given an n x n binary matrix grid where 1 represents land and 0 represents water.
 *
 * An island is a 4-directionally connected group of 1's not connected to any other 1's. There are exactly two islands in grid.
 *
 * You may change 0's to 1's to connect the two islands to form one island.
 *
 * Return the smallest number of 0's you must flip to connect the two islands.
 *
 * Example 1:
 * Input: grid = [[0,1],[1,0]]
 * Output: 1
 *
 * Example 2:
 * Input: grid = [[0,1,0],[0,0,0],[0,0,1]]
 * Output: 2
 *
 * Example 3:
 * Input: grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * Output: 1
 *
 * Constraints:
 * n == grid.length == grid[i].length
 * 2 <= n <= 100
 * grid[i][j] is either 0 or 1.
 * There are exactly two islands in grid.
 */
public class ShortestBridge {
    public static void main(String[] args) {
        ShortestBridge obj = new ShortestBridge();

        int[][] grid = {{0, 1}, {1, 0}};
        int res = obj.shortestBridge(grid);
        System.out.println(res);
    }

    /**
     * KREVSKY SOLUTION:
     * time to think + implement + debug ~ 8 + 15 + 20 ~ 42 mins
     * idea:
     * 1) use BFS and Priority Queue = Dijkstra
     * 2) if new cell has 1, but of the same island => do not increase the distance
     * 2.1) otherwise (if cell is 0 or we have found 1 of the other island (in this case dist > 0) => set dist+1
     * 3) stop condition: we poll element that is '1' and dist > 0
     * <p>
     * time ~ O((N^2)*log(N^2)
     * space ~ O(N^2)
     */
    public int shortestBridge(int[][] grid) {
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    return bfs(grid, i, j);
                }
            }
        }

        return -1;  //if it is impossible (only 1 island / no islands)
    }

    private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private int bfs(int[][] grid, int i, int j) {
        int n = grid.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);    //[0] - r, [1] - c, [2] - distance. min heap sorted by distance

        pq.add(new int[]{i, j, 0});
        Set<Integer> visited = new HashSet<>();

        while (!pq.isEmpty()) {
            int[] el = pq.poll();
            int r = el[0];
            int c = el[1];
            int dist = el[2];
            int idx = r * n + c;

            if (grid[r][c] == 1 && dist > 0) {
                return dist - 1;
            }

            if (visited.contains(idx)) continue;

            visited.add(idx);

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                int nidx = nr * n + nc;
                if (0 <= nr && nr < n && 0 <= nc && nc < n && !visited.contains(nidx)) {
                    if (grid[nr][nc] == 1 && dist == 0) {
                        pq.add(new int[]{nr, nc, dist});
                    } else {
                        pq.add(new int[]{nr, nc, dist + 1});
                    }
                }
            }
        }

        return -1;
    }

    /**
     * Official solution:
     * idea:
     * 1) find all cells of island A (using BFS and DFS)
     * 2) for each cell of island A go in 4 directions and if is it 0, then mark it as -cur_dist (initially it will be -1)
     * and store these neighbour cells to the other queue
     * 3) if we didn't find '1' of the other island, do bfs for new queue using the same approach (if we face 0 => set -2)
     * etc
     *
     * time  ~ O(N^2)
     * space ~ O(N^2)
     */
    private List<int[]> bfsQueue;

    // Recursively check the neighboring land cell of current cell grid[x][y] and add all
    // land cells of island A to bfsQueue.
    private void dfs(int[][] grid, int x, int y, int n) {
        grid[x][y] = 2;
        bfsQueue.add(new int[]{x, y});
        for (int[] pair : new int[][]{{x + 1, y}, {x - 1, y}, {x, y + 1}, {x, y - 1}}) {
            int curX = pair[0], curY = pair[1];
            if (0 <= curX && curX < n && 0 <= curY && curY < n && grid[curX][curY] == 1) {
                dfs(grid, curX, curY, n);
            }
        }
    }

    // Find any land cell, and we treat it as a cell of island A.
    public int shortestBridgeOfficial(int[][] grid) {
        int n = grid.length;
        int firstX = -1, firstY = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    firstX = i;
                    firstY = j;
                    break;
                }
            }
        }

        // Add all land cells of island A to bfsQueue.
        bfsQueue = new ArrayList<>();
        dfs(grid, firstX, firstY, n);

        int distance = 0;
        while (!bfsQueue.isEmpty()) {
            List<int[]> newBfs = new ArrayList<>();
            for (int[] pair : bfsQueue) {
                int x = pair[0], y = pair[1];
                for (int[] nextPair : new int[][]{{x + 1, y}, {x - 1, y}, {x, y + 1}, {x, y - 1}}) {
                    int curX = nextPair[0], curY = nextPair[1];
                    if (0 <= curX && curX < n && 0 <= curY && curY < n) {
                        if (grid[curX][curY] == 1) {
                            return distance;
                        } else if (grid[curX][curY] == 0) {
                            newBfs.add(nextPair);
                            grid[curX][curY] = -1;
                        }
                    }
                }
            }

            // Once we finish one round without finding land cells of island B, we will
            // start the next round on all water cells that are 1 cell further away from
            // island A and increment the distance by 1.
            bfsQueue = newBfs;
            distance++;
        }

        return distance;
    }
}