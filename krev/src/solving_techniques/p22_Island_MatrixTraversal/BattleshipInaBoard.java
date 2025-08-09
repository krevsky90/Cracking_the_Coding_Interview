package solving_techniques.p22_Island_MatrixTraversal;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 419. Battleships in a Board (medium)
 * https://leetcode.com/problems/battleships-in-a-board
 * <p>
 * #Company (9.08.2025): 0 - 3 months Meta 4 0 - 6 months Amazon 5 Google 2 6 months ago Microsoft 5 Tinkoff 3 Microstrategy 2
 * <p>
 * Given an m x n matrix board where each cell is a battleship 'X' or empty '.',
 * return the number of the battleships on board.
 * <p>
 * Battleships can only be placed horizontally or vertically on board.
 * In other words, they can only be made of the shape 1 x k (1 row, k columns) or k x 1 (k rows, 1 column),
 * where k can be of any size. At least one horizontal or vertical cell separates between two battleships
 * (i.e., there are no adjacent battleships).
 * <p>
 * Example 1:
 * Input: board = [["X",".",".","X"],[".",".",".","X"],[".",".",".","X"]]
 * Output: 2
 * <p>
 * Example 2:
 * Input: board = [["."]]
 * Output: 0
 * <p>
 * Constraints:
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] is either '.' or 'X'.
 * <p>
 * Follow up: Could you do it in one-pass, using only O(1) extra memory and without modifying the values board?
 */
public class BattleshipInaBoard {
    /**
     * Follow up: Could you do it in one-pass, using only O(1) extra memory and without modifying the values board?
     * NOT SOLVED by myself
     * idea: https://leetcode.com/problems/battleships-in-a-board/solutions/6646340/master-the-no-extra-space-trick-for-counting-battleships/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days
     *
     * find (and count) all top-left cells of the ships
     */
    public int countBattleshipsNoExtraMemory(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return 0;

        //dfs solution:
        int n = board.length;
        int m = board[0].length;
        int counter = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'X'
                        && (i == 0 || board[i - 1][j] != 'X')
                        && (j == 0 || board[i][j - 1] != 'X')
                ) {
                    counter++;
                }

            }
        }

        return counter;
    }

    /**
     * KREVSKY SOLUTION:
     * idea: BFS or DFS
     * time ~ 8 or 6 mins
     * <p>
     * 1 attempt
     * BEATS ~ 93% or 100%
     */
    public int countBattleshipsKrev(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return 0;

        //dfs solution:
        int n = board.length;
        int m = board[0].length;
        int counter = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'X') {
                    counter++;
                    bfs(board, i, j, n, m);
//                    dfs(board, i, j, n, m);
                }
            }
        }

        return counter;
    }

    private void dfs(char[][] board, int i, int j, int n, int m) {
        if (0 <= i && i < n && 0 <= j && j < m && board[i][j] == 'X') {
            board[i][j] = 'Y';  //replace = mark as visited

            dfs(board, i - 1, j, n, m);
            dfs(board, i + 1, j, n, m);
            dfs(board, i, j - 1, n, m);
            dfs(board, i, j + 1, n, m);
        }
    }

    private void bfs(char[][] board, int i, int j, int n, int m) {
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(i, j));
        board[i][j] = 'Y';

        while (!q.isEmpty()) {
            Pair p = q.poll();

            for (int[] dir : dirs) {
                int newR = p.row + dir[0];
                int newC = p.col + dir[1];
                if (0 <= newR && newR < n && 0 <= newC && newC < m && board[newR][newC] == 'X') {
                    q.add(new Pair(newR, newC));
                    board[newR][newC] = 'Y';
                }
            }
        }
    }

    class Pair {
        int row;
        int col;

        Pair(int r, int c) {
            this.row = r;
            this.col = c;
        }
    }


}
