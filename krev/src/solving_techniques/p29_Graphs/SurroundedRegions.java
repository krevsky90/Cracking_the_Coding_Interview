package solving_techniques.p29_Graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 130. Surrounded Regions (medium)
 * https://leetcode.com/problems/surrounded-regions
 * <p>
 * #Company (6.07.2025): 0 - 3 months Amazon 6 Google 2 Bloomberg 2 Nutanix 2 6 months ago Meta 11 Microsoft 8 TikTok 6 Adobe 4 Urban Company 3 Apple 2 Flipkart 2 Goldman Sachs 2 Uber 2 Arcesium 2
 * <p>
 * You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:
 * <p>
 * Connect: A cell is connected to adjacent cells horizontally or vertically.
 * Region: To form a region connect every 'O' cell.
 * Surround: The region is surrounded with 'X' cells if you can connect the region with 'X' cells and none of the region cells are on the edge of the board.
 * To capture a surrounded region, replace all 'O's with 'X's in-place within the original board. You do not need to return anything.
 * <p>
 * Example 1:
 * Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * Explanation:
 * In the above diagram, the bottom region is not captured because it is on the edge of the board and cannot be surrounded.
 * <p>
 * Example 2:
 * Input: board = [["X"]]
 * Output: [["X"]]
 * <p>
 * Constraints:
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] is 'X' or 'O'.
 */
public class SurroundedRegions {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 15 mins
     * <p>
     * DFS ~ 1 attempt
     * BEATS ~ 80%
     * <p>
     * BFS ~ TLE, because I did not mark board[..][..] = 'E' before putting element to the queue
     */
    public void solve(char[][] board) {
        //1. find all border '0'
        List<int[]> borderZeros = new ArrayList<>();
        int n = board.length;
        int m = board[0].length;

        for (int i = 0; i < n; i++) {
            borderZeros.add(new int[]{i, 0});
            borderZeros.add(new int[]{i, m - 1});
        }

        for (int j = 1; j < m - 1; j++) {
            borderZeros.add(new int[]{0, j});
            borderZeros.add(new int[]{n - 1, j});
        }

        //2. mark all '0' that are connected to borderZeros as 'E'
        for (int[] p : borderZeros) {
            bfs(board, p[0], p[1], n, m);
        }

        //3. flip 'O' -> 'X' and 'E' -> 'O'
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                if (board[i][j] == 'E') board[i][j] = 'O';
            }
        }
    }

    private void bfs(char[][] board, int r, int c, int n, int m) {
        if (board[r][c] != 'O') return;

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        board[r][c] = 'E';  //like 'mark as visited'

        while (!q.isEmpty()) {
            int[] p = q.poll();
            int x = p[0];
            int y = p[1];

            for (int[] dir : dirs) {
                int newR = x + dir[0];
                int newC = y + dir[1];
                if (0 <= newR && newR < n && 0 <= newC && newC < m && board[newR][newC] == 'O') {
                    board[newR][newC] = 'E';  //like 'mark as visited'
                    q.add(new int[]{newR, newC});
                }
            }
        }
    }

    private void dfs(char[][] board, int r, int c, int n, int m) {
        if (board[r][c] != 'O') return;

        board[r][c] = 'E';  //like 'mark as visited'
        if (r > 0) dfs(board, r - 1, c, n, m);
        if (r < n - 1) dfs(board, r + 1, c, n, m);
        if (c > 0) dfs(board, r, c - 1, n, m);
        if (c < m - 1) dfs(board, r, c + 1, n, m);
    }
}
