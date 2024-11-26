package solving_techniques.p22_Island_MatrixTraversal;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/638c7f5bb2790984e1934f98
 * OR
 * 1254. Number of Closed Islands
 * https://leetcode.com/problems/number-of-closed-islands (medium)
 * <p>
 * Solve leetcode's description
 * Given a 2D grid consists of 0s (land) and 1s (water).
 * An island is a maximal 4-directionally connected group of 0s
 * and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
 * <p>
 * Return the number of closed islands.
 * <p>
 * Example 1:
 * Input: grid = [[1,1,1,1,1,1,1,0],
 * [1,0,0,0,0,1,1,0],
 * [1,0,1,0,1,1,1,0],
 * [1,0,0,0,0,1,0,1],
 * [1,1,1,1,1,1,1,0]]
 * Output: 2
 * Explanation:
 * Islands in gray are closed because they are completely surrounded by water (group of 1s).
 * <p>
 * Example 2:
 * Input: grid = [[0,0,1,0,0],
 * [0,1,0,1,0],
 * [0,1,1,1,0]]
 * Output: 1
 * <p>
 * Example 3:
 * Input: grid = [[1,1,1,1,1,1,1],
 * [1,0,0,0,0,0,1],
 * [1,0,1,1,1,0,1],
 * [1,0,1,0,1,0,1],
 * [1,0,1,1,1,0,1],
 * [1,0,0,0,0,0,1],
 * [1,1,1,1,1,1,1]]
 * Output: 2
 * <p>
 * Constraints:
 * 1 <= grid.length, grid[0].length <= 100
 * 0 <= grid[i][j] <=1
 */
public class NumberOfClosedIslands {
    /**
     * KREVSKY SOLUTION:
     * 1) use DFS
     * 2) change value of current cell to mark it as visited
     * <p>
     * time to solve ~ 18 mins
     * time ~ O(n*m)
     * space O(n*m) - worst case: matrix is filled by '1'
     * <p>
     * 1 attempt
     */
    public int closedIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0 && isClosedIslandDFS(i, j, grid, n, m)) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean isClosedIslandDFS(int i, int j, int[][] grid, int n, int m) {
        if (grid[i][j] == 1) return true;
        if (i <= 0 || i >= n - 1 || j <= 0 || j >= m - 1) return false;

        grid[i][j] = 1; //mark as visited

        boolean way1 = isClosedIslandDFS(i - 1, j, grid, n, m);
        boolean way2 = isClosedIslandDFS(i + 1, j, grid, n, m);
        boolean way3 = isClosedIslandDFS(i, j - 1, grid, n, m);
        boolean way4 = isClosedIslandDFS(i, j + 1, grid, n, m);

        return way1 && way2 && way3 && way4;
    }

    /**
     * KREVSKY SOLUTION:
     * 1) use BFS
     * 2) keep track visited[][]
     * <p>
     * time to solve ~ 13 mins
     *
     * BEATS ~ 28%
     *
     * 2 attempts:
     * - forgot visited[newI][newJ] = true;
     */
    private int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int closedIslandBFS(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int result = 0;
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0 && !visited[i][j]) {
                    if (isClosedIslandBFS(grid, n, m, i, j, visited)) {
                        result++;
                    }
                }
            }
        }

        return result;
    }

    private boolean isClosedIslandBFS(int[][] grid, int n, int m, int i, int j, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        visited[i][j] = true;

        boolean result = true;

        while (!q.isEmpty()) {
            int[] cell = q.poll();

            for (int[] dir : dirs) {
                int newI = cell[0] + dir[0];
                int newJ = cell[1] + dir[1];

                if (!(0 <= newI && newI < n && 0 <= newJ && newJ < m)) {
                    result = false;
                    continue;
                }

                if (!visited[newI][newJ] && grid[newI][newJ] == 0) {
                    visited[newI][newJ] = true;
                    q.add(new int[]{newI, newJ});
                }
            }
        }

        return result;
    }
}
