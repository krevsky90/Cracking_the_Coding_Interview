package solving_techniques.p24_UnionFind;

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
     * Union find approach
     *  time to solve ~ 44 mins
     *
     *  2 attempts:
     *  - missed validation if (board[newI][newJ] == 'O')
     *
     *  BEATS ~ 9%
     */
    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;

        UnionFind obj = new UnionFind(n*m + 1);
        //NOTE: cell n*m does not exist! but we will join real 'O' border cells and n*m-th cell, that means that we group border cells
        int borderGroup = n*m;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O' && (i == 0 || i == n - 1 || j == 0 || j == m - 1)) {
                    int idx = i*m + j;
                    obj.union(idx, borderGroup);
                }
            }
        }

        //handle 'O's that do not belong to any border
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (board[i][j] == 'O') {
                    for (int[] dir : dirs) {
                        int newI = i + dir[0];
                        int newJ = j + dir[1];
                        if (board[newI][newJ] == 'O') {
                            int newIdx = newI * m + newJ;
                            obj.union(newIdx, i*m + j);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O' && obj.find(i*m+j) != obj.find(borderGroup)) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    class UnionFind {
        private int[] parents;

        public UnionFind(int size) {
            parents = new int[size];
            for (int i = 0; i < size; i++) {
                parents[i] = i;
            }
        }

        public int find(int x) {
            if (x != parents[x]) {
                x = find(parents[x]);
            }
            return parents[x];
        }

        public void union(int x, int y) {
            int xrep = find(x);
            int yrep = find(y);
            if (xrep == yrep) return;

            parents[xrep] = yrep;
        }
    }
}
