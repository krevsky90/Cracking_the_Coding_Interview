package solving_techniques.p29_Graphs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 3342. Find Minimum Time to Reach Last Room II (medium)
 * https://leetcode.com/problems/find-minimum-time-to-reach-last-room-ii
 *
 * #Company: 0 - 3 months Uber 10
 *
 * There is a dungeon with n x m rooms arranged as a grid.
 * You are given a 2D array moveTime of size n x m,
 *      where moveTime[i][j] represents the minimum time in seconds when you can start moving to that room.
 *      You start from the room (0, 0) at time t = 0 and can move to an adjacent room. Moving between adjacent rooms takes one second for one move and two seconds for the next, alternating between the two.
 * Return the minimum time to reach the room (n - 1, m - 1).
 * Two rooms are adjacent if they share a common wall, either horizontally or vertically.
 *
 * Example 1:
 * Input: moveTime = [[0,4],[4,4]]
 * Output: 7
 *
 * Explanation:
 * The minimum time required is 7 seconds.
 * At time t == 4, move from room (0, 0) to room (1, 0) in one second.
 * At time t == 5, move from room (1, 0) to room (1, 1) in two seconds.
 *
 * Example 2:
 * Input: moveTime = [[0,0,0,0],[0,0,0,0]]
 * Output: 6
 * Explanation:
 * The minimum time required is 6 seconds.
 * At time t == 0, move from room (0, 0) to room (1, 0) in one second.
 * At time t == 1, move from room (1, 0) to room (1, 1) in two seconds.
 * At time t == 3, move from room (1, 1) to room (1, 2) in one second.
 * At time t == 4, move from room (1, 2) to room (1, 3) in two seconds.
 *
 * Example 3:
 * Input: moveTime = [[0,1],[1,2]]
 * Output: 4
 *
 * Constraints:
 * 2 <= n == moveTime.length <= 750
 * 2 <= m == moveTime[i].length <= 750
 * 0 <= moveTime[i][j] <= 10^9
 */
public class FindMinimumTimeToReachLastRoom2 {
    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 20 mins
     *
     * idea:
     * 1) find the shortest path in the graph using PriorityQueue (min heap based on time field of element)
     * - keep set of visited cells using their idx = r * m + c
     * - each element of queue = r, c, time to reach this cell, number of steps to reach this cell
     * -  int ntime = Math.max(moveTime[nr][nc], grid[r][c]) + stepCost,
     *      where stepCost = numOfSteps % 2 == 0 ? 1 : 2;
     * 2) NOTE: grid[0][0] = 0 independently on moveTime[0][0] value!
     * 3) optimization: we set element only in case if we can improve the time
     *
     * 4 attempts:
     * - mixed rc and rn in  grid[nr][nc]
     * - forgot comparator in PriorityQueue
     * - incorrect formula for ntime (set min instead of max, set grid[nr][nc] instead of grid[r][c])
     *
     * BEATS ~ 18%
     */
    public int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length;
        int m = moveTime[0].length;
        int[][] grid = new int[n][m];
        for (int[] row : grid) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);    //[0] - r, [1] - c, [2] - time, [3] - numberOfSteps
        pq.add(new int[]{0, 0, 0, 0});
        Set<Integer> visited = new HashSet<>(); //keeps ids of visited cell, where idx = r*m + c

        while (!pq.isEmpty()) {
            int[] el = pq.poll();
            int r = el[0];
            int c = el[1];
            int time = el[2];
            int numOfSteps = el[3];
            int idx = r * m + c;

            if (visited.contains(idx)) continue;

            if (r == n - 1 && c == m - 1) return time;

            visited.add(idx);
            grid[r][c] = time;
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (0 <= nr && nr < n && 0 <= nc && nc < m) {
                    int stepCost = numOfSteps % 2 == 0 ? 1 : 2;
                    int ntime = Math.max(moveTime[nr][nc], grid[r][c]) + stepCost;
                    if (ntime < grid[nr][nc]) {
                        pq.add(new int[]{nr, nc, ntime, numOfSteps + 1});
                    }
                }
            }
        }

        return -1;
    }
}
