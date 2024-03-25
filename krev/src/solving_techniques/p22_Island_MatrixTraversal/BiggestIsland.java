package solving_techniques.p22_Island_MatrixTraversal;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6388d8940cc1849dcbc27fe3
 * OR
 * 695. Max Area of Island
 * https://leetcode.com/problems/max-area-of-island (medium)
 *
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land)
 * connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 * The area of an island is the number of cells with a value 1 in the island.
 * Return the maximum area of an island in grid. If there is no island, return 0.
 *
 * Example 1:
 * Input: grid = [
 * [0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0],
 * [0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]
 * ]
 * Output: 6
 * Explanation: The answer is not 11, because the island must be connected 4-directionally.
 * Example 2:
 *
 * Input: grid = [[0,0,0,0,0,0,0,0]]
 * Output: 0
 *
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] is either 0 or 1.
 *
 */
public class BiggestIsland {
    /**
     * KREVSKY SOLUTION:
     * time to solve 14 mins
     *
     * idea: is the same as solving_techniques/p22_Island_MatrixTraversal/NumberOfIslands.java # markIslandBFS, but added 'area' variable
     *
     * time ~ O(n*m)
     * space O(min(n*m)) - the worst case, when the matrix is completely filled with land cells, the size of the queue can grow up to min(n*m)
     *
     * 2 attempts:
     * - had to change 'return ' to 'continue'
     */
    //will use BFS + mark visited elements as '0'
    public int maxAreaOfIsland(int[][] grid) {
        // if (grid == null || grid.length == 0) return 0;

        int n = grid.length;
        int m = grid[0].length;

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    result = Math.max(result, countArea(grid, i, j, n, m));
                }
            }
        }

        return result;
    }

    private int countArea(int[][] grid, int i, int j, int n, int m) {
        int area = 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});

        while (!q.isEmpty()) {
            int[] pair = q.poll();
            int row = pair[0];
            int col = pair[1];
            if (row < 0 || row >= n || col < 0 || col >= m) continue;   //ignore not valid cell

            if (grid[row][col] == 0) continue;  //current cell is NOT piece of island OR it is already visited

            grid[row][col] = 0;   //mark as visited

            area++; //count the area of (col,row) cell of island

            //BFS
            q.add(new int[]{row+1,col});
            q.add(new int[]{row-1,col});
            q.add(new int[]{row,col+1});
            q.add(new int[]{row,col-1});
        }
        return area;
    }
}