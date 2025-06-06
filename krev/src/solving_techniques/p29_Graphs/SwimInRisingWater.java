package solving_techniques.p29_Graphs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 778. Swim in Rising Water (hard)
 * https://leetcode.com/problems/swim-in-rising-water
 *
 * #Company (06.06.2025): 0 - 3 months Meta 3 Google 2 WeRide 2 6 months ago Uber 8 DoorDash 5 Amazon 4 Apple 3 PhonePe 3 Microsoft 2 Bloomberg 2 DE Shaw 2
 *
 * You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).
 *
 * The rain starts to fall. At time t, the depth of the water everywhere is t.
 * You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t.
 * You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.
 *
 * Return the least time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).
 *
 * Example 1:
 * Input: grid = [[0,2],[1,3]]
 * Output: 3
 * Explanation:
 * At time 0, you are in grid location (0, 0).
 * You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
 * You cannot reach point (1, 1) until time 3.
 * When the depth of water is 3, we can swim anywhere inside the grid.
 *
 * Example 2:
 * Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 * Output: 16
 * Explanation: The final route is shown.
 * We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 *
 * Constraints:
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 50
 * 0 <= grid[i][j] < n2
 * Each value grid[i][j] is unique.
 */
public class SwimInRisingWater {
    public static void main(String[] args) {
        SwimInRisingWater obj = new SwimInRisingWater();

        int[][] grid = {{0, 1, 2, 3, 4}, {24, 23, 22, 21, 5}, {12, 13, 14, 15, 16}, {11, 17, 18, 19, 20}, {10, 9, 8, 7, 6}};
        obj.swimInWater(grid);
    }

    /**
     * KREVSKY SOLUTION:
     * similar to FindMinimumTimeToReachLastRoom1
     * idea:
     * BFS + priority queue + visited set
     *
     * time ~ O(n*n*log(n^2)) ~ O (n^2 * logn)
     * space  ~ O(n^2)
     *
     * BEATS ~ 30%
     */
    public int swimInWater(int[][] grid) {
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};

        if (grid == null || grid.length == 0 && grid[0].length == 0) {
            return 0;
        }

        int n = grid.length;
        //time ~ O(n*n*log(n^2)) ~ O (n^2 * logn)
        //space  ~ O(n^2)
        int[][] res = new int[n][n];
        for (int[] resRow : res) {
            Arrays.fill(resRow, Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);   //[0] - r, [1] - c, [2] - time to reach
        Set<Integer> visited = new HashSet<>(); //id = r*n + c;
        visited.add(0);

        pq.add(new int[]{0,0,grid[0][0]});

        while (!pq.isEmpty()) {
            int[] el = pq.poll();
            int r = el[0];
            int c = el[1];
            int time = el[2];

            if (r == n - 1 && c == n - 1) return time;

            res[r][c] = time;

            for (int[] dir : dirs) {
                int newR = r + dir[0];
                int newC = c + dir[1];
                int newId = newR*n + newC;
                if (0 <= newR && newR < n && 0 <= newC && newC < n && !visited.contains(newId)) {
                    int newTime = Math.max(time, grid[newR][newC]);
                    if (newTime < res[newR][newC]) {
                        visited.add(newId);
                        pq.add(new int[]{newR, newC, newTime});
                    }
                }
            }
        }

        return res[n-1][n-1];
    }

    /**
     * Optimized: do NOT store res[][], just keep variable ans
     */
    public int swimInWater2(int[][] grid) {
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};

        if (grid == null || grid.length == 0 && grid[0].length == 0) {
            return 0;
        }

        int n = grid.length;
        //time ~ O(n*n*log(n^2)) ~ O (n^2 * logn)
        //space  ~ O(n^2)
        int ans = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);   //[0] - r, [1] - c, [2] - time to reach
        Set<Integer> visited = new HashSet<>(); //id = r*n + c;
        visited.add(0);

        pq.add(new int[]{0,0,grid[0][0]});

        while (!pq.isEmpty()) {
            int[] el = pq.poll();
            int r = el[0];
            int c = el[1];
            int time = el[2];
            ans = Math.max(ans, time);

            if (r == n - 1 && c == n - 1) return ans;

            for (int[] dir : dirs) {
                int newR = r + dir[0];
                int newC = c + dir[1];
                int newId = newR*n + newC;
                if (0 <= newR && newR < n && 0 <= newC && newC < n && !visited.contains(newId)) {
                    int newTime = Math.max(time, grid[newR][newC]);
//                    if (newTime < res[newR][newC]) {
                        visited.add(newId);
                        pq.add(new int[]{newR, newC, newTime});
//                    }
                }
            }
        }

        return ans;
    }
}
