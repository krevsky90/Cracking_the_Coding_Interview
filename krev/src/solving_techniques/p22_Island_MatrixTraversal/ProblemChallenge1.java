package solving_techniques.p22_Island_MatrixTraversal;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/638c857a34bb69a286c79f30
 * OR
 * 463. Island Perimeter (easy)
 * https://leetcode.com/problems/island-perimeter/
 * <p>
 * You are given row x col grid representing a map
 * where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.
 * <p>
 * Grid cells are connected horizontally/vertically (not diagonally).
 * The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
 * <p>
 * The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island.
 * One cell is a square with side length 1.
 * The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
 * <p>
 * Example 1:
 * Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
 * Output: 16
 * Explanation: The perimeter is the 16 yellow stripes in the image above.
 * <p>
 * Example 2:
 * Input: grid = [[1]]
 * Output: 4
 * <p>
 * Example 3:
 * Input: grid = [[1,0]]
 * Output: 4
 * <p>
 * Constraints:
 * row == grid.length
 * col == grid[i].length
 * 1 <= row, col <= 100
 * grid[i][j] is 0 or 1.
 * There is exactly one island in grid
 */
public class ProblemChallenge1 {
    /**
     * NOT FULLY SOLVED by myself
     * time to solve ~ 48 mins
     * idea:
     * 1) handle 'lake' case
     * 2) use visit matrix since we can't change the initial grid. otherwise we might turn lake into usual water
     * 3) subtract common bounds for current cell and find the amount of its edges that are part of the final perimeter
     *
     * a lot of attempts:
     * - many typos
     * - did not find idea #3 by myself
     *
     * BEATS = 8%
     *
     * DO NOT UNDERSTAND why leecode's solutions do not find lakes...
     * like this https://leetcode.com/problems/island-perimeter/solutions/3448621/two-best-solutions-by-java-in-runtime-7ms-beats-71-32/
     * they just calculate perimeter...
     */
    public int islandPerimeter(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        //we must track visited cells in separate 2D-array, since we can't change the initial grid
        //otherwise we might turn lake into usual water
        boolean[][] visit = new boolean[n][m];

        int perimeter = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !visit[i][j]) {
                    perimeter = calcPerimeter(i, j, grid, n, m, visit);
                    if (perimeter > 0) return perimeter;
                }
            }
        }

        //stub:
        return perimeter;
    }

    private int calcPerimeter(int i, int j, int[][] grid, int n, int m, boolean[][] visit) {
        //it is out of bound => return 0
        if (i < 0 || i >= n || j < 0 || j >= m) return 0;

        //if it is lake => return perimeter -1, as special value that mean that we should not consider this island at all
        if (isLake(i, j, grid, n, m)) return -1;

        //if it is water, but NOT lake => return 0
        if (grid[i][j] == 0) return 0;

        //if it is already visited cell - skip it => return 0
        if (visit[i][j]) return 0;

        int perimeter = 4;
        visit[i][j] = true;

        //subtract common bounds
        int subtract = 0;
        if (i >= 1 && grid[i - 1][j] == 1) subtract++;
        if (i <= n - 2 && grid[i + 1][j] == 1) subtract++;
        if (j >= 1 && grid[i][j - 1] == 1) subtract++;
        if (j <= m - 2 && grid[i][j + 1] == 1) subtract++;

        perimeter -= subtract; //amount of edges of current cell that will be part of perimeter

        int perimeter1 = calcPerimeter(i - 1, j, grid, n, m, visit);
        if (perimeter1 == -1) return -1;
        if (perimeter1 > 0) perimeter += perimeter1;

        int perimeter2 = calcPerimeter(i + 1, j, grid, n, m, visit);
        if (perimeter2 == -1) return -1;
        if (perimeter2 > 0) perimeter += perimeter2;

        int perimeter3 = calcPerimeter(i, j - 1, grid, n, m, visit);
        if (perimeter3 == -1) return -1;
        if (perimeter3 > 0) perimeter += perimeter3;

        int perimeter4 = calcPerimeter(i, j + 1, grid, n, m, visit);
        if (perimeter4 == -1) return -1;
        if (perimeter4 > 0) perimeter += perimeter4;

        return perimeter;
    }

    private boolean isLake(int i, int j, int[][] grid, int n, int m) {
        if (grid[i][j] == 1) return false;
        if (i <= 0 || i >= n - 1 || j <= 0 || j >= m - 1) return false;

        return grid[i][j - 1] == 1 && grid[i][j + 1] == 1 && grid[i - 1][j] == 1 && grid[i + 1][j] == 1;
    }
}
