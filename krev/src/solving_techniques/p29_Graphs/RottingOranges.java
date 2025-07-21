package solving_techniques.p29_Graphs;

import solving_techniques.p29_Graphs.NetworkDelayTime.Pair;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 994. Rotting Oranges (medium)
 * https://leetcode.com/problems/rotting-oranges/
 * <p>
 * #Company (21.07.2025): 0 - 3 months Amazon 25 Lyft 14 Google 6 Meta 6 Microsoft 2 Apple 2 Oracle 2 Rakuten 2 TikTok 2 PayPal 2 0 - 6 months Wix 7 Bloomberg 3 Walmart Labs 3 Zoho 3 eBay 3 C3.ai 3 Adobe 2 Uber 2 6 months ago Intuit 8 Samsung 6 Goldman Sachs 5 Flipkart 4 Docusign 4 ByteDance 3 Salesforce 3 PhonePe 3 Nutanix 3 Informatica 3
 * <p>
 * You are given an m x n grid where each cell can have one of three values:
 * <p>
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 * <p>
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 * <p>
 * Example 1:
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 * <p>
 * Example 2:
 * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
 * <p>
 * Example 3:
 * Input: grid = [[0,2]]
 * Output: 0
 * Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 * <p>
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] is 0, 1, or 2.
 */
public class RottingOranges {
    /**
     * KREVSKY SOLUTION:
     * idea: use multi-source BFS
     * <p>
     * time to solve ~ 17 mins
     * <p>
     * time ~ O(n*m)
     * space ~ O(n*m)
     * <p>
     * 2 attempts:
     * - did not handle case when q is empty, but there are fresh oranges. example {{1}}
     * <p>
     * BEATS ~ 100%
     */
    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        if (n == 0) return 0;
        int m = grid[0].length;

        Queue<int[]> q = new LinkedList<>();


        boolean hasFreshOrange = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    q.add(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    hasFreshOrange = true;
                }
            }
        }

        if (!hasFreshOrange) return 0;
        //i.e. is q is empty and there are fresh oranges
        if (q.isEmpty()) return -1;

        //bfs level by level
        int result = 0;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] el = q.poll();

                for (int[] dir : dirs) {
                    int newR = el[0] + dir[0];
                    int newC = el[1] + dir[1];
                    //find fresh oranges
                    if (0 <= newR && newR < n && 0 <= newC && newC < m && grid[newR][newC] == 1) {
                        grid[newR][newC] = 2;   //mark as rotten
                        q.add(new int[]{newR, newC});
                    }
                }
            }
            result++;
        }

        //check if there are fresh oranges
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }

        return result - 1;
    }

    /**
     * Official solution:
     * idea: (-1, -1) element means the end of processing round.
     * during processing we turn fresh orange to rotten AND decrease amount of fresh oranges (and avoid additional loop like in my solution)
     */
    public int orangesRottingOfficial(int[][] grid) {
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque();

        // Step 1). build the initial set of rotten oranges
        int freshOranges = 0;
        int ROWS = grid.length, COLS = grid[0].length;

        for (int r = 0; r < ROWS; ++r)
            for (int c = 0; c < COLS; ++c)
                if (grid[r][c] == 2)
                    queue.offer(new Pair(r, c));
                else if (grid[r][c] == 1)
                    freshOranges++;

        // Mark the round / level, _i.e_ the ticker of timestamp
        queue.offer(new Pair(-1, -1));

        // Step 2). start the rotting process via BFS
        int minutesElapsed = -1;
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> p = queue.poll();
            int row = p.getKey();
            int col = p.getValue();
            if (row == -1) {
                // We finish one round of processing
                minutesElapsed++;
                // to avoid the endless loop
                if (!queue.isEmpty())
                    queue.offer(new Pair(-1, -1));
            } else {
                // this is a rotten orange
                // then it would contaminate its neighbors
                for (int[] d : directions) {
                    int neighborRow = row + d[0];
                    int neighborCol = col + d[1];
                    if (neighborRow >= 0 && neighborRow < ROWS &&
                            neighborCol >= 0 && neighborCol < COLS) {
                        if (grid[neighborRow][neighborCol] == 1) {
                            // this orange would be contaminated
                            grid[neighborRow][neighborCol] = 2;
                            freshOranges--;
                            // this orange would then contaminate other oranges
                            queue.offer(new Pair(neighborRow, neighborCol));
                        }
                    }
                }
            }
        }

        // return elapsed minutes if no fresh orange left
        return freshOranges == 0 ? minutesElapsed : -1;
    }
}
