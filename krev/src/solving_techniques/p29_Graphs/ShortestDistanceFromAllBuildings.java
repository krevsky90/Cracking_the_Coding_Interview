package solving_techniques.p29_Graphs;

import java.util.LinkedList;
import java.util.Queue;


/**
 * 317 - Shortest Distance from All Buildings (hard) (locked)
 * https://leetcode.com/problems/shortest-distance-from-all-buildings
 * OR
 * https://leetcode.ca/all/317.html
 *
 * #Company: Amazon ByteDance Facebook Goldman Sachs Google Mathworks Microsoft Snapchat Splunk Uber Zenefits
 *
 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance.
 * You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
 *
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 * Example:
 *
 * Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 *
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 *
 * Output: 7
 *
 * Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
 *              the point (1,2) is an ideal empty land to build a house, as the total
 *              travel distance of 3+3+1=7 is minimal. So return 7.
 * Note:
 * There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
 */
public class ShortestDistanceFromAllBuildings {
    public static void main(String[] args) {
        int[][] grid1 = {
                {1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}
        };
        int[][] grid2 = {{0,1}};
        int[][] grid3 = {{1}};

        ShortestDistanceFromAllBuildings obj = new ShortestDistanceFromAllBuildings();

        System.out.println(obj.shortestDistance(grid1));
        System.out.println(obj.shortestDistance(grid2));
        System.out.println(obj.shortestDistance(grid3));
    }

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 50 mins
     *
     * idea:
     * 1) use BFS for each building incrementing the distance
     * finally we will get grid with total distance from the cell to all buildings
     * 2) select the cell with min value
     * 3) KREVSKY idea: to prevent case when we can't reach all buildings, we count total amount of '1'
     *      and amount that we can reach
     *
     *
     * 5 attempts:
     * - incorrect pre-filling of resultGrid (set Inf instead of 0)
     * - used dist instead of arr[2]
     * - forgot to set Inf for non-0 cells of resultGrid
     * - did not mark visited = true all cells which meet the conditions "if (newI >= 0 && newI < m && newJ >= 0 && newJ < n && !visited[newI][newJ])"
     *
     * NOTE: similar but slightly different solution is here https://www.youtube.com/watch?v=yjHXS2w_IvY&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=90
     * diffs:
     * - decrement 0s each time - instead of keep 'visited' grid. after that consider decremented value as empty cell
     * - to avoid additional checking 'tempBuilderCounter != totalBuildingsCounter' we can find result after every bfs
     * and if result = Integer.MAX_VALUE => we can't reach all buildings (not obvious to me)
     */
    public int shortestDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] resultGrid = new int[m][n];

        int totalBuildingsCounter = 0;
        int tempBuilderCounter = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    totalBuildingsCounter++;
                    tempBuilderCounter = bfs(grid, i, j, m, n, resultGrid);
                }
            }
        }

        if (tempBuilderCounter != totalBuildingsCounter) return -1;

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result = Math.min(result, resultGrid[i][j]);
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private int bfs(int[][] grid, int i, int j, int m, int n, int[][] resultGrid) {
        int buildingsCounter = 1;
        int[][] dirs = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j, 0});
        visited[i][j] = true;
        resultGrid[i][j] = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            int[] arr = q.poll();
            resultGrid[arr[0]][arr[1]] += arr[2];

            for (int[] dir : dirs) {
                int newI = arr[0] + dir[0];
                int newJ = arr[1] + dir[1];
                if (newI >= 0 && newI < m && newJ >= 0 && newJ < n && !visited[newI][newJ]) {
                    visited[newI][newJ] = true;
                    if (grid[newI][newJ] == 1) {
                        buildingsCounter++;
                        resultGrid[newI][newJ] = Integer.MAX_VALUE;
                    } else if (grid[newI][newJ] == 2) {
                        resultGrid[newI][newJ] = Integer.MAX_VALUE;
                    } else {
                        //i.e. 0
                        q.add(new int[]{newI, newJ, arr[2] + 1});
                    }
                }
            }
        }

        return buildingsCounter;
    }
}
