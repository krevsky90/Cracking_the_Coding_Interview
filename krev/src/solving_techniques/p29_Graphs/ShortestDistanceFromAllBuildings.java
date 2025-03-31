package solving_techniques.p29_Graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


/**
 * 317 - Shortest Distance from All Buildings (hard) (locked)
 * https://leetcode.com/problems/shortest-distance-from-all-buildings
 * OR
 * https://leetcode.ca/all/317.html
 * <p>
 * #Company: Amazon ByteDance Facebook Goldman Sachs Google Mathworks Microsoft Snapchat Splunk Uber Zenefits
 * <p>
 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance.
 * You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
 * <p>
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 * Example:
 * <p>
 * Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 * <p>
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * <p>
 * Output: 7
 * <p>
 * Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
 * the point (1,2) is an ideal empty land to build a house, as the total
 * travel distance of 3+3+1=7 is minimal. So return 7.
 * Note:
 * There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
 */
public class ShortestDistanceFromAllBuildings {
    public static void main(String[] args) {
        int[][] grid1 = {
                {1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 1},
                {0, 1, 1, 0, 0, 1},
                {1, 0, 0, 1, 0, 1},
                {1, 0, 1, 0, 0, 1},
                {1, 0, 0, 0, 0, 1},
                {0, 1, 1, 1, 1, 0}
        };
        int[][] grid2 = {{0, 1}};
        int[][] grid3 = {{1}};

        ShortestDistanceFromAllBuildings obj = new ShortestDistanceFromAllBuildings();

        System.out.println(obj.shortestDistance(grid1));
        System.out.println(obj.shortestDistance(grid2));
        System.out.println(obj.shortestDistance(grid3));
    }

    /**
     * KREVSKY SOLUTION + idea "count how many buildings have path to the 'empty' cell"
     *
     * idea:
     * 1) count total amount of buildings
     * 2) start BFS from each building and decrease the distance from current value of empty cell (initially it is 0)
     * NOTE: Also keep track for each empty cell the amount of paths from different buildings to the cell (i.e. how many times BFS methods face his cell)
     * 3) calculate min distance: find maximum negative cell, BUT this cell should be visited by ALL buildings!
     * Otherwise, the cell is not considered at all (THAT'S THE CLUE for my incorrect solution that is written BELOW!)
     *
     * time ~ O(n^2 * m^2)
     * space ~ O(n*m)
     *
     * many attempts
     *
     * BEATS ~ 26%
     */
    private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int shortestDistance(int[][] grid) {
        int houseCnt = 0;
        int n = grid.length;
        int m = grid[0].length;
        int[][] visitedCnt = new int[n][m]; //each cell has the number of buildings that has path to this cell

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    houseCnt++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    bfs(grid, i, j, visitedCnt);
                }
            }
        }

        int res = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //check only 'empty' cells that was visited by ALL buildings!
                if (grid[i][j] < 0 && visitedCnt[i][j] == houseCnt && res < grid[i][j]) {
                    res = grid[i][j];
                    System.out.println("cur cell [" + i + ", " + j + "]");
                }
            }
        }

        return res == Integer.MIN_VALUE ? -1 : -res;
    }

    private void bfs(int[][] grid, int r, int c, int[][] visitedCnt) {
        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c, 0});
        Set<Integer> visited = new HashSet<>();
        int idx = r * m + c;
        visited.add(idx);

        while (!q.isEmpty()) {
            int[] el = q.poll();

            for (int[] dir : dirs) {
                int newR = el[0] + dir[0];
                int newC = el[1] + dir[1];
                int newIdx = newR * m + newC;
                if (0 <= newR && newR < n && 0 <= newC && newC < m && grid[newR][newC] <= 0 && !visited.contains(newIdx)) {
                    //i.e. work only with emptyand non-visited cells!
                    visited.add(newIdx);
                    visitedCnt[newR][newC]++;
                    grid[newR][newC] -= el[2] + 1;
                    q.add(new int[]{newR, newC, el[2] + 1});
                }
            }
        }
    }

    /**
     * KREVSKY SOLUTION DOES NOT WORK!
     * time to solve ~ 50 mins
     * <p>
     * It does NOT work
     * because
     * {1,1,1,1,1,0}
     * {0,0,0,0,0,1}
     * {0,1,1,0,0,1}
     * {1,0,0,1,0,1}
     * {1,0,1,0,0,1}
     * {1,0,0,0,0,1}
     * {0,1,1,1,1,0}
     * will give answer = 2, since corner 0s will be transformed to -2. But obviously, it is impossible to reach all buildings from this cell!
     */

    public int shortestDistanceDoesNOTWork(int[][] grid) {
        int houseCnt = 0;
        int n = grid.length;
        int m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    houseCnt++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    if (!bfs(grid, i, j, houseCnt)) {
                        return -1;
                    }
                }
            }
        }

        int res = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] < 0 && res < grid[i][j]) {
                    res = grid[i][j];
                    System.out.println("cur cell [" + i + ", " + j + "]");
                }
            }
        }

        return res == Integer.MIN_VALUE ? -1 : -res;
    }

    private boolean bfs(int[][] grid, int r, int c, int houseCnt) {
        int n = grid.length;
        int m = grid[0].length;
        int localHouseCnt = 1;  //count the initial cell [r, c]
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c, 0});
        Set<Integer> visited = new HashSet<>();
        int idx = r * m + c;
        visited.add(idx);

        while (!q.isEmpty()) {
            int[] el = q.poll();

            for (int[] dir : dirs) {
                int newR = el[0] + dir[0];
                int newC = el[1] + dir[1];
                int newIdx = newR * m + newC;
                if (0 <= newR && newR < n && 0 <= newC && newC < m && !visited.contains(newIdx)) {
                    // not trivial condition to avoid case when we move from '1' to '1'
                    if (grid[el[0]][el[1]] <= 0 && grid[newR][newC] == 1) {
                        visited.add(newIdx);
                        // System.out.println("cur cell [" + el[0] + ", " + el[1] + "], new cell [" + newR + ", " + newC + "]");
                        localHouseCnt++;
                    }

                    if (grid[newR][newC] <= 0) {
                        visited.add(newIdx);
                        grid[newR][newC] -= el[2] + 1;
                        q.add(new int[]{newR, newC, el[2] + 1});
                    }
                }
            }
        }

        return localHouseCnt == houseCnt;
    }
}
