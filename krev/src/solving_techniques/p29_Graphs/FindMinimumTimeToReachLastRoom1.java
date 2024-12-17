package solving_techniques.p29_Graphs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 3341. Find Minimum Time to Reach Last Room I (medium)
 * https://leetcode.com/problems/find-minimum-time-to-reach-last-room-i/
 * <p>
 * #Company: 0 - 3 months Uber 10 6 months ago Google 2
 * <p>
 * There is a dungeon with n x m rooms arranged as a grid.
 * <p>
 * You are given a 2D array moveTime of size n x m, where moveTime[i][j] represents the minimum time in seconds when you can start moving to that room. You start from the room (0, 0) at time t = 0 and can move to an adjacent room. Moving between adjacent rooms takes exactly one second.
 * <p>
 * Return the minimum time to reach the room (n - 1, m - 1).
 * <p>
 * Two rooms are adjacent if they share a common wall, either horizontally or vertically.
 * <p>
 * Example 1:
 * Input: moveTime = [[0,4],[4,4]]
 * Output: 6
 * Explanation:
 * The minimum time required is 6 seconds.
 * <p>
 * At time t == 4, move from room (0, 0) to room (1, 0) in one second.
 * At time t == 5, move from room (1, 0) to room (1, 1) in one second.
 * <p>
 * Example 2:
 * Input: moveTime = [[0,0,0],[0,0,0]]
 * Output: 3
 * Explanation:
 * The minimum time required is 3 seconds.
 * <p>
 * At time t == 0, move from room (0, 0) to room (1, 0) in one second.
 * At time t == 1, move from room (1, 0) to room (1, 1) in one second.
 * At time t == 2, move from room (1, 1) to room (1, 2) in one second.
 * <p>
 * Example 3:
 * Input: moveTime = [[0,1],[1,2]]
 * Output: 3
 * <p>
 * My example after debugging:
 * [94,79,62,27,69,84],
 * [6, 32,11,82,42,30]
 * <p>
 * should return
 * 0 80 63 64 70 85
 * 7 33 34 83 71 72
 * <p>
 * Constraints:
 * 2 <= n == moveTime.length <= 50
 * 2 <= m == moveTime[i].length <= 50
 * 0 <= moveTime[i][j] <= 10^9
 */
public class FindMinimumTimeToReachLastRoom1 {
    /**
     * NOT solved by myself!
     * since I did not see that we can go in ALL directions!
     * so it is NOT DP problem!
     * it is about graphs!
     * <p>
     * idea:
     * 1) find the shortest path in the graph using PriorityQueue (min heap based on time field of element)
     * - keep set of visited cells using their idx = r * m + c
     * - each element of queue = r, c, time to reach this cell
     * 2) NOTE: grid[0][0] = 0 independently on moveTime[0][0] value!
     * 3) optimization: we set element only in case if we can improve the time
     * <p>
     * time to implement ~ 16 mins
     * <p>
     * time ~ O(n*m*log(n*m)), since we should fill all n*m cells, and in the wort case the Queue will contain all m*n elements
     * space ~ O(n*m) for grid + O(n*m) for queue => O(n*m
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 16%
     */
    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length;
        int m = moveTime[0].length;
        int[][] grid = new int[n][m];
        for (int[] row : grid) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        grid[0][0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);    //[0] - r, [1] - c, [2] minimum time to reach this cell
        Set<Integer> visited = new HashSet<>(); //store idx of cell, where idx = r * m + c <= 2500 => int

        pq.add(new int[]{0, 0, 0});
        while (!pq.isEmpty()) {
            int[] el = pq.poll();
            int r = el[0];
            int c = el[1];
            int time = el[2];

            if (r == n - 1 && c == m - 1) {
                return time;
            }

            int idx = r * m + c;
            if (visited.contains(idx)) continue;

            visited.add(idx);
            grid[r][c] = time;

            for (int[] dir : dirs) {
                int newR = r + dir[0];
                int newC = c + dir[1];
                if (0 <= newR && newR < n && 0 <= newC && newC < m) {
                    int newTime = Math.max(time, moveTime[newR][newC]) + 1;
                    //optimization: we put element to the queue only if we can improve the time
                    if (newTime < grid[newR][newC]) {
                        pq.add(new int[]{newR, newC, newTime});
                    }
                }
            }
        }

        return -1;  //if it is unreachable ...
        // or just return grid[n - 1][m - 1];
    }
}
