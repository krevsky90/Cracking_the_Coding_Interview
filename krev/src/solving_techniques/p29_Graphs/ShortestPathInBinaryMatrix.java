package solving_techniques.p29_Graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1091. Shortest Path in Binary Matrix (medium)
 * https://leetcode.com/problems/shortest-path-in-binary-matrix
 * <p>
 * #Company: Amazon Facebook Google Uber
 * <p>
 * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix.
 * If there is no clear path, return -1.
 * <p>
 * A clear path in a binary matrix is a path from the top-left cell
 * (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
 * <p>
 * All the visited cells of the path are 0.
 * All the adjacent cells of the path are 8-directionally connected
 * (i.e., they are different and they share an edge or a corner).
 * The length of a clear path is the number of visited cells of this path.
 * <p>
 * Example 1:
 * Input: grid = [[0,1],[1,0]]
 * Output: 2
 * <p>
 * Example 2:
 * Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
 * Output: 4
 * <p>
 * Example 3:
 * Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
 * Output: -1
 * <p>
 * Constraints:
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 100
 * grid[i][j] is 0 or 1
 */
public class ShortestPathInBinaryMatrix {
    /**
     * NOT SOLVED by myself (forgot BFS implementation)
     * info:
     * https://leetcode.com/problems/shortest-path-in-binary-matrix/solutions/5623834/the-most-intuitive-solution-you-will-find-interview-prep/
     * <p>
     * time to implement ~ 10 mins
     * time ~ O(n*n)
     * space ~ O(n*3) ~ O(n)
     * <p>
     * 2 attempts:
     * - typo: while (q.isEmpty())  - forgot "!"
     * <p>
     * BEATS ~ 99%
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, 1});    // {i-th, j-th, distance}
        grid[0][0] = 2; //mark as visited

        while (!q.isEmpty()) {
            int[] tempCell = q.poll();
            int y = tempCell[0];
            int x = tempCell[1];
            int dist = tempCell[2];

            if (y == n - 1 && x == n - 1) {
                return dist;
            }

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int newX = x + j;
                    int newY = y + i;
                    if (newX >= 0 && newX < n && newY >= 0 && newY < n && grid[newY][newX] == 0) {
                        q.add(new int[]{newY, newX, dist + 1});
                        grid[newY][newX] = 2;   //mark as visited
                    }
                }
            }
        }

        return -1;  // If no path is found from source to destination
    }

    /**
     * SOLUTION #2:
     * info:
     * https://leetcode.com/problems/shortest-path-in-binary-matrix/solutions/2043319/why-use-bfs-search-every-possible-path-vs-search-a-possible-path/
     *
     * time ~ O(n*n)
     * space ~ O(n*n)  - because of visited matrix
     */
    public int shortestPathBinaryMatrix2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        int ans = 0;

        int row = grid.length;
        int col = grid[0].length;

        if (grid[0][0] == 1 || grid[row - 1][col - 1] == 1) {
            return -1;
        }

        int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        boolean[][] visited = new boolean[row][col];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            ans++;  //NOTE! it really provides min path!

            for (int i = 0; i < size; i++) {
                int[] curPos = queue.poll();

                if (curPos[0] == row - 1 && curPos[1] == col - 1) {
                    return ans;
                }

                for (int[] dir : dirs) {
                    int nextX = curPos[0] + dir[0];
                    int nextY = curPos[1] + dir[1];

                    if (nextX < 0 || nextX >= row || nextY < 0 || nextY >= col || visited[nextX][nextY] || grid[nextX][nextY] == 1) {
                        continue;
                    }

                    visited[nextX][nextY] = true;
                    queue.offer(new int[]{nextX, nextY});
                }
            }
        }

        return -1;
    }
}
